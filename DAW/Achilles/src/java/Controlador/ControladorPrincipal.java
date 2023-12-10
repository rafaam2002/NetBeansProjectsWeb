/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Bizum;
import Modelo.Transferencia;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transaction;

/**
 *
 * @author rafaa
 */
@WebServlet(name = "ControladorDatos", urlPatterns = {"/ControladorPrincipal/*"})
public class ControladorPrincipal extends HttpServlet {

    @PersistenceContext(unitName = "AchillesPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    private final LocalDate fInicioAplicacion = LocalDate.of(2023, 12, 1);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorDatos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorDatos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        String accion = request.getPathInfo();
        String vista = "/index.html";
        Query query;
        Usuario user;
        HttpSession session;
        long idUsuario;

        //si siempre tengo que estar buscando al usuario lo puedo hacer fuera del switch?
        switch (accion) {
            case "/getDatosGraficas":
                session = request.getSession();

                idUsuario = (long) session.getAttribute("idUsuario");
                query = em.createNamedQuery("Usuario.findById", Usuario.class);
                query.setParameter("id", idUsuario);

                user = (Usuario) query.getSingleResult();

                Object[] datos = getMovimientosUsuario(user); //datos: [0] array con datos de la grafica principal
                //[1] ingresos mensual, [2] gasto mensual
                JsonObject datosJson = transformarMovimientosAJson((double[]) datos[0], (double) datos[1],
                        (double) datos[2], user.getDineroDouble(), (double) datos[3]);

                // Configurar la respuesta
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // Escribir los datos JSON en la respuesta
                try ( PrintWriter out = response.getWriter()) {
                    out.print(datosJson);
                }

                break;

            case "/main":
                System.err.println("Entrando en accion main");
                vista = "/WEB-INF/main.jsp";

                break;

            case "/getContactos":
                session = request.getSession();
                idUsuario = (long) session.getAttribute("idUsuario");
                query = em.createNamedQuery("Usuario.findById", Usuario.class);
                query.setParameter("id", idUsuario);

                user = (Usuario) query.getSingleResult();

                List<Usuario> contactos = user.getContactos();
                request.setAttribute("contactos", contactos);
                vista = "/WEB-INF/contactos.jsp";
                break;

            case "/nuevoContacto":

                vista = "/WEB-INF/nuevoContacto.jsp";
                
                break;

            case "/addContacto":
                String nick = request.getParameter("nick");

                session = request.getSession();
                idUsuario = (long) session.getAttribute("idUsuario");
                query = em.createNamedQuery("Usuario.findById", Usuario.class);
                query.setParameter("id", idUsuario);
                user = (Usuario) query.getSingleResult();
                
                query = em.createNamedQuery("Usuario.findByNick", Usuario.class);
                query.setParameter("nick", nick);

                try {
                    Usuario newContacto = (Usuario) query.getSingleResult();
                    user.getContactos().add(newContacto);
                    persist(user);
                    request.setAttribute("mensaje", "Contacto agregado correctamente");
                } catch (NoResultException  ex) {
                    request.setAttribute("mensaje", "El nick que ha introducido no pertenece a ningún"
                            + "usuario");
                }
                
                vista = "/WEB-INF/nuevoContacto.jsp";
                break;

        }
        RequestDispatcher rd = request.getRequestDispatcher(vista);
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    private Object[] getMovimientosUsuario(Usuario user) {
        LocalDate fHoy = LocalDate.now();

        // Define el formato de fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("/dd/MM/yyyy");

        // Calcula la diferencia en días
        int difDiasEntreIniAppYHoy = (int) ChronoUnit.DAYS.between(fInicioAplicacion, fHoy);

        List<Transferencia> tEnviadas = user.gettEnviadas();
        List<Transferencia> tRecividas = user.gettRecividas();
        List<Bizum> bEnviados = user.getbEnviados();
        List<Bizum> bRecividos = user.getbRecividos();

        double[] datosXdia = new double[difDiasEntreIniAppYHoy]; //se inicializa a 0 automaticamente en java 

        //Para graficaPie
        double ingresosMes = 0;
        double gastosMes = 0;

        //Capital semana pasada
        double capitalSemanaPasada;

        //Bucle que va sumando los movimientos a la posicion de datosXdia correspondiente
        LocalDate fecha;
        Transferencia trans;
        Bizum bizum;
        String fString;
        int difDias;
        int i = 0;
        while (i < tEnviadas.size() || i < tRecividas.size() || i < bEnviados.size() || i < bRecividos.size()) {
            if (i < tEnviadas.size()) {
                trans = tEnviadas.get(i);
                fString = trans.getFecha();
                fecha = LocalDate.parse(fString, formatter);
                difDias = (int) ChronoUnit.DAYS.between(fInicioAplicacion, fecha);
                datosXdia[difDias] -= trans.getCantidad();

                //graficaPe
                //Posteriormente ajustar los dias con una funcion dependiendo del mes (30 o 31 o 28)
                if (difDiasEntreIniAppYHoy - difDias < 30) {
                    gastosMes -= trans.getCantidad();
                }
            }
            if (i < tRecividas.size()) {
                trans = tRecividas.get(i);
                fString = trans.getFecha();
                fecha = LocalDate.parse(fString, formatter);
                difDias = (int) ChronoUnit.DAYS.between(fInicioAplicacion, fecha);
                datosXdia[difDias] += trans.getCantidad();

                //graficaPe
                if (difDiasEntreIniAppYHoy - difDias < 30) {
                    ingresosMes += trans.getCantidad();
                }
            }
            if (i < bEnviados.size()) {
                bizum = bEnviados.get(i);
                fString = bizum.getFecha();
                fecha = LocalDate.parse(fString, formatter);
                difDias = (int) ChronoUnit.DAYS.between(fInicioAplicacion, fecha);
                datosXdia[difDias] -= bizum.getCantidad();

                //graficaPe
                if (difDiasEntreIniAppYHoy - difDias < 30) {
                    ingresosMes -= bizum.getCantidad();
                }
            }
            if (i < bEnviados.size()) {
                bizum = bEnviados.get(i);
                fString = bizum.getFecha();
                fecha = LocalDate.parse(fString, formatter);
                difDias = (int) ChronoUnit.DAYS.between(fInicioAplicacion, fecha);
                datosXdia[difDias] += bizum.getCantidad();

                //graficaPe
                if (difDiasEntreIniAppYHoy - difDias < 30) {
                    ingresosMes += bizum.getCantidad();
                }
            }
        }

        if (difDiasEntreIniAppYHoy >= 7) {
            capitalSemanaPasada = datosXdia[datosXdia.length - 7];
        } else {
            capitalSemanaPasada = 0;
        }
        //Creo datos para pasarlo todo
        Object[] datos = new Object[4];
        datos[0] = datosXdia;
        datos[1] = ingresosMes;
        datos[2] = gastosMes;
        datos[3] = capitalSemanaPasada;
        return datos;
    }

    private JsonObject transformarMovimientosAJson(double[] datosPrincipal, double ingresosMes,
            double gastosMes, double capital, double capitalSemanaPasada) {

        JsonObjectBuilder jBuilder = Json.createObjectBuilder();

        //Tabla Principal
        //datos
        JsonArrayBuilder jsBuilderDatos = Json.createArrayBuilder();

        //EjeX
        JsonArrayBuilder jsBuilderEjeX = Json.createArrayBuilder();
        final String[] diasSemana = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        for (int i = 0; i < datosPrincipal.length; i++) {
            jsBuilderDatos.add(datosPrincipal[i]);
            jsBuilderEjeX.add(diasSemana[i % 7]);
        }

        JsonArray datosJsonArray = jsBuilderDatos.build();
        JsonArray ejeXJsonArray = jsBuilderEjeX.build();

        jBuilder.add("datos", datosJsonArray);
        jBuilder.add("ejeX", ejeXJsonArray);

        //Tabla Pie
        JsonArrayBuilder jsBuilderDatosPie = Json.createArrayBuilder();
        jsBuilderDatosPie.add(capital);
        jsBuilderDatosPie.add(ingresosMes);
        jsBuilderDatosPie.add(gastosMes);
        JsonArray datosPieJson = jsBuilderDatosPie.build();

        jBuilder.add("capitalIngresosGastos", datosPieJson);

        //Capital semana Pasada
        jBuilder.add("capitalSemanaPasada", capitalSemanaPasada);

        return jBuilder.build();
    }

}
