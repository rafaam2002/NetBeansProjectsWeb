/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Bizum;
import Modelo.MensajeEntity;
import Modelo.Transferencia;
import Modelo.Usuario;
import Socket.Mensaje;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
import javax.persistence.EntityNotFoundException;
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
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import org.json.JSONArray;
import org.json.JSONObject;

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
        Usuario user, userRec, otro;
        List<Usuario> contactos;
        String paramString, numCuenta;
        HttpSession session;
        long idUsuario;
        switch (accion) {
            case "/getDatosGraficas":
                user = (Usuario) request.getAttribute("user");

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
                user = (Usuario) request.getAttribute("user");
                request.setAttribute("nickUsuario", user.getNick());
                numCuenta = Long.toString(user.getNumCuenta());
                request.setAttribute("numCuenta", numCuenta);
                System.out.println("Entrando en accion main");
                vista = "/WEB-INF/main.jsp";

                break;

            case "/getContactos":
                user = (Usuario) request.getAttribute("user");
                request.setAttribute("nickUsuario", user.getNick());
                numCuenta = Long.toString(user.getNumCuenta());
                request.setAttribute("numCuenta", numCuenta);

                contactos = user.getContactos();
                request.setAttribute("contactos", contactos);
                vista = "/WEB-INF/contactos.jsp";
                break;

            case "/nuevoContacto":
                user = (Usuario) request.getAttribute("user");
                request.setAttribute("nickUsuario", user.getNick());
                numCuenta = Long.toString(user.getNumCuenta());
                request.setAttribute("numCuenta", numCuenta);
                vista = "/WEB-INF/nuevoContacto.jsp";

                break;

            case "/addContacto":
                String nick = request.getParameter("nick");
                user = (Usuario) request.getAttribute("user");
                numCuenta = Long.toString(user.getNumCuenta());
                request.setAttribute("numCuenta", numCuenta);
                query = em.createNamedQuery("Usuario.findByNick", Usuario.class);
                query.setParameter("nick", nick);
                JsonObject textJson;
                try {
                    Usuario newContacto = (Usuario) query.getSingleResult();
                    if (!newContacto.equals(user)) {//compruebo que no escriba su propio nick
                        //compruebo que no esté ya es su lista de contactos
                        boolean repetido = false;
                        for (Usuario contacto : user.getContactos()) {
                            if (contacto.getNick().equals(newContacto.getNick())) {
                                repetido = true;
                            }
                        }
                        if (!repetido) {
                            user.getContactos().add(newContacto);
                            update(user);
                            textJson = transformarRespNewContacto("Contacto agregado correctamente");
                        } else {
                            textJson = transformarRespNewContacto("Este usuario ya pertenece a su lista de contactos");
                        }
                    } else {
                        textJson = transformarRespNewContacto("Ha introducido su propio nombre de usuario");
                    }

                } catch (NoResultException ex) {
                    textJson = transformarRespNewContacto("El nombre de usuario no existe");
                }

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // Escribir los datos JSON en la respuesta
                try ( PrintWriter out = response.getWriter()) {
                    out.print(textJson);
                }

//                vista = "/WEB-INF/nuevoContacto.jsp";
                break;
            case "/hacerBizum":
                user = (Usuario) request.getAttribute("user");
                request.setAttribute("nickUsuario", user.getNick());
                numCuenta = Long.toString(user.getNumCuenta());
                request.setAttribute("numCuenta", numCuenta);
                contactos = user.getContactos();
                request.setAttribute("contactos", contactos);
                vista = "/WEB-INF/hacerBizum.jsp";
                break;
            case "/cantidadBizum":
                paramString = request.getParameter("idUsuario");

                if (paramString != null) {
                    idUsuario = Long.parseLong(paramString);
                    query = em.createNamedQuery("Usuario.findById", Usuario.class);
                    query.setParameter("id", idUsuario);
                    userRec = (Usuario) query.getSingleResult();
                    if (userRec.isBizumActive()) {
                        session = request.getSession();
                        session.setAttribute("idRecBizum", idUsuario);
                        user = (Usuario) request.getAttribute("user");
                        request.setAttribute("nickUsuario", user.getNick());
                        numCuenta = Long.toString(user.getNumCuenta());
                        request.setAttribute("numCuenta", numCuenta);
                        vista = "/WEB-INF/cantidadBizum.jsp";
                    }
                }
                break;
            case "/transferencia":
                user = (Usuario) request.getAttribute("user");
                numCuenta = Long.toString(user.getNumCuenta());
                request.setAttribute("numCuenta", numCuenta);
                request.setAttribute("nickUsuario", user.getNick());
                vista = "/WEB-INF/transferencia.jsp";
                break;

            case "/conversaciones":
                user = (Usuario) request.getAttribute("user");
                numCuenta = Long.toString(user.getNumCuenta());
                request.setAttribute("numCuenta", numCuenta);
                request.setAttribute("nickUsuario", user.getNick());
                request.setAttribute("user", user);
                request.setAttribute("contactos", user.getContactos());
                request.setAttribute("mensajesEnviados", user.getmEnviados());
                request.setAttribute("mensajesRecividos", user.getmRecividos());

                vista = "/WEB-INF/conversaciones.jsp";
                break;
            case "/getConversacion":
                user = (Usuario) request.getAttribute("user");
                paramString = request.getParameter("nick");
                query = em.createNamedQuery("Usuario.findByNick", Usuario.class);
                query.setParameter("nick", paramString);
                otro = (Usuario) query.getSingleResult();

                JSONObject respuesta = new JSONObject();
                JSONArray mEnviados = new JSONArray();
                JSONArray mRecividos = new JSONArray();

                for (MensajeEntity msjEntity : user.getmEnviados()) {
                    if (msjEntity.getRecMensaje().equals(otro)) {
                        JSONObject jsonEnviado = new JSONObject();
                        jsonEnviado.put("text", msjEntity.getText());
                        jsonEnviado.put("fecha", msjEntity.getFecha());
                        mEnviados.put(jsonEnviado);

                    }
                }
                for (MensajeEntity msjEntity : user.getmRecividos()) {
                    if (msjEntity.getEmiMensaje().equals(otro)) {
                        JSONObject jRecivido = new JSONObject();
                        jRecivido.put("text", msjEntity.getText());
                        jRecivido.put("fecha", msjEntity.getFecha());
                        jRecivido.put("nEmisor", msjEntity.getEmiMensaje().getNick());
                        mRecividos.put(jRecivido);
                    }
                }
                respuesta.put("mEnviados", mEnviados);
                respuesta.put("mRecividos", mRecividos);

                // Configurar la respuesta del servlet
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // Enviar el objeto JSON como respuesta
                PrintWriter out = response.getWriter();
                out.print(respuesta.toString());
                out.flush();

                break;

        }
        if (!accion.equals("/addContacto") && !accion.equals("/getDatosGraficas") && !accion.equals("/getConversacion")) {
            RequestDispatcher rd = request.getRequestDispatcher(vista);
            rd.forward(request, response);
        }

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
        String accion = request.getPathInfo();
        String vista = "/index.html";
        Query query;
        Usuario user, userRecBizum, userRec;
        HttpSession session;
        double cantidad;
        Bizum bizum;
        long idUsuarioRec;
        LocalDate fechaActual;
        DateTimeFormatter formatter;
        String fechaFormateada, concepto,numCuenta;
        StringBuilder requestBody;

        switch (accion) {

            case "/guardarBizum":

                user = (Usuario) request.getAttribute("user");
                numCuenta = Long.toString(user.getNumCuenta());
                request.setAttribute("numCuenta", numCuenta);
                cantidad = Double.parseDouble(request.getParameter("cantidad"));
                concepto = request.getParameter("concepto");
                System.out.println(cantidad + " " + concepto);
                session = request.getSession();
                idUsuarioRec = (long) session.getAttribute("idRecBizum");
                query = em.createNamedQuery("Usuario.findById", Usuario.class);
                query.setParameter("id", idUsuarioRec);
                userRecBizum = (Usuario) query.getSingleResult();
                if (concepto != null && cantidad > 0) {
                    bizum = new Bizum();
                    fechaActual = LocalDate.now();
                    formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    fechaFormateada = fechaActual.format(formatter);
                    bizum.setFecha(fechaFormateada);

                    bizum.setCantidad(cantidad);
                    bizum.setConcepto(concepto);
                    bizum.setEmiBizum(user);
                    bizum.setRecBizum(userRecBizum);
                    user.setDineroDouble(user.getDineroDouble() - cantidad);
                    userRecBizum.setDineroDouble(userRecBizum.getDineroDouble() - cantidad);
                    user.getbEnviados().add(bizum);
                    userRecBizum.getbRecividos().add(bizum);

                    persist(bizum);
                    update(user);
                    update(userRecBizum);

                }
                session.removeAttribute("idRecBizum");
                vista = "/WEB-INF/main.jsp";
//                response.sendRedirect("/Achilles/ControladorPrincipal/main");
                break;
            case "/persistirMensajes":

                System.out.println("Persistir mensajes");
                requestBody = new StringBuilder();

                try ( BufferedReader reader = request.getReader()) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        requestBody.append(line);
                    }
                }

                // En este punto, requestBody contiene el cuerpo de la solicitud como una cadena JSON
//                String datosRecibidos = requestBody.toString();
                JSONArray jsonArray = new JSONArray(requestBody.toString());
                System.out.println("jsonArraylength: " + jsonArray.length());

                for (int i = 0; i < jsonArray.length(); i++) {

                    System.err.println("se mete en bucle for de jsonArray.length");
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                     if (i == 0) {
//                        System.out.println("persiste: " + jsonObject.getString("nEmisor") );
//
//                    }
                    System.out.println("jsonObject: " + jsonObject);
                    Mensaje msj = new Mensaje();
                    msj.setnEmisor(jsonObject.getString("nEmisor"));
                    msj.setnReceptor(jsonObject.getString("nReceptor"));
                    msj.setText(jsonObject.getString("text"));
                    msj.setFecha(jsonObject.getString("fecha"));
//                    System.out.println(msj);
                    //si el mensaje ya se envio por otro usuario no se hace nada y se borra de ya enviados
                    //ya que los mensajes se enviaran dos veces, por el emisor y por el receptor
                    MensajeEntity msjEntity = new MensajeEntity();
                    query = em.createNamedQuery("Usuario.findByNick");
                    query.setParameter("nick", msj.getnEmisor());
                    user = (Usuario) query.getSingleResult();
                    query = em.createNamedQuery("Usuario.findByNick");
                    query.setParameter("nick", msj.getnReceptor());
                    userRec = (Usuario) query.getSingleResult();
                    msjEntity.setEmiMensaje(user);
                    msjEntity.setRecMensaje(userRec);
                    msjEntity.setText(msj.getText());
                    msjEntity.setFecha(msj.getFecha());

                    user.getmEnviados().add(msjEntity);
                    userRec.getmRecividos().add(msjEntity);
                    persist(msjEntity);
                    update(user);
                    update(userRec);

                }
                break;
        }
        if (!accion.equals("/guardarBizum") || !accion.equals("/persistirMensajes")) {
            RequestDispatcher rd = request.getRequestDispatcher(vista);
            rd.forward(request, response);
        }

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

    private void update(Object object) {
        try {
            utx.begin();

            if (object != null) {
                // Copiar los cambios de la instancia proporcionada a la instancia existente
                em.merge(object);
                System.out.println("El usuario se esta actualizando");
            }

            utx.commit();
        } catch (EntityNotFoundException | IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    private Object[] getMovimientosUsuario(Usuario user) {
        LocalDate fHoy = LocalDate.now();

        // Define el formato de fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Calcula la diferencia en días
        int difDiasEntreIniAppYHoy = (int) ChronoUnit.DAYS.between(fInicioAplicacion, fHoy) + 1;

        List<Transferencia> tEnviadas = user.gettEnviadas();
        List<Transferencia> tRecividas = user.gettRecividas();
        List<Bizum> bEnviados = user.getbEnviados();
        List<Bizum> bRecividos = user.getbRecividos();

        double[] datosXdia = new double[difDiasEntreIniAppYHoy]; //se inicializa a 0 automaticamente en java 
        double[] seguimientoTotal = new double[difDiasEntreIniAppYHoy];
        seguimientoTotal[0] = 3000.0;
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
            //guardo cada movimiento en su dia correspondiente 
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
            if (i < bRecividos.size()) {
                bizum = bRecividos.get(i);
                fString = bizum.getFecha();
                fecha = LocalDate.parse(fString, formatter);
                difDias = (int) ChronoUnit.DAYS.between(fInicioAplicacion, fecha);
                datosXdia[difDias] += bizum.getCantidad();

                //graficaPe
                if (difDiasEntreIniAppYHoy - difDias < 30) {
                    ingresosMes += bizum.getCantidad();
                }
            }
            i++;
        }
        for (int j = 0; j < seguimientoTotal.length - 1; j++) {
            //si el movimiento se hizo el primer dia hay que sumarlo en la primera iteracion
            if (j == 0) {
                seguimientoTotal[j] += datosXdia[j];
            }
            seguimientoTotal[j + 1] = seguimientoTotal[j] + datosXdia[j + 1];
        }

        if (difDiasEntreIniAppYHoy >= 7) {
            capitalSemanaPasada = datosXdia[datosXdia.length - 7];
        } else {
            capitalSemanaPasada = 0;
        }
        //Creo datos para pasarlo todo
        Object[] datos = new Object[4];
        datos[0] = seguimientoTotal;
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
        //Empieza el viernes porque el dia que empezo la aplicacion fue un viernes
        final String[] diasSemana = {"Fri", "Sat", "Sun", "Mon", "Tue", "Wed", "Thu"};

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

    private JsonObject transformarRespNewContacto(String text) {
        JsonObjectBuilder jBuilder = Json.createObjectBuilder();
        jBuilder.add("text", text);
        return jBuilder.build();
    }

}
