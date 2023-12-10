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
import java.time.LocalDate;
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
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
@WebServlet(name = "ControladorDatos", urlPatterns = {"/ControladorDatos/*"})
public class ControladorDatos extends HttpServlet {

    @PersistenceContext(unitName = "AchillesPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    private final LocalDate fInicioAplicacion = LocalDate.of(2023, 12, 10); 

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
        Query query;
        Usuario user;
        switch (accion) {
            case "/getDatos":
                HttpSession session = request.getSession();

                long idUsuario = (long) session.getAttribute("idUsuario");
                query = em.createNamedQuery("Usuario.findById", Usuario.class);
                query.setParameter("id", idUsuario);

                user = (Usuario) query.getSingleResult();

                double[] datos = getMovimientosUsuario(user);
                JsonObject datosJson = transformarMovimientosAJson(user);

                // Configurar la respuesta
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // Escribir los datos JSON en la respuesta
                try ( PrintWriter out = response.getWriter()) {
                    out.print(datosJson);
                }

                break;
            default:
                throw new AssertionError();
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

    private double[] getMovimientosUsuario(Usuario user) {
        Query query;
        LocalDate fHoy = LocalDate.now();
        Transferencia t;
       
        // Calcula la diferencia en días
        int diferenciaEnDias = (int)ChronoUnit.DAYS.between(fInicioAplicacion, fHoy);
        
        List<Transferencia> tEnviadas = user.gettEnviadas();
        List<Transferencia> tRecividas = user.gettRecividas();
        List<Bizum> bEnviados = user.getbEnviados();
        List<Bizum> bRecividos = user.getbRecividos();
        
        double[] datosXdia  = new double[diferenciaEnDias]; //se inicializa a 0 automaticamente en java 
        int i = 0;
        
        while( i < tEnviadas.size() || i < tRecividas.size() || i < bEnviados.size() || i < bRecividos.size()){
            if(i < tEnviadas.size()){
                t = tEnviadas.get(i);
                t.getFecha();
            }
        }

    }

    private JsonObject transformarMovimientosAJson(Usuario user) {

        JsonObjectBuilder jBuilder = Json.createObjectBuilder();
        long datos[] = {1, 2, 3, 4};

        JsonArrayBuilder daJsBuilder = Json.createArrayBuilder();

        daJsBuilder.add(1.0);
        daJsBuilder.add(2.0);
        daJsBuilder.add(3.0);
        daJsBuilder.add(4.0);
        daJsBuilder.add(5.0);
//        daJsBuilder.add(1.0);
//        daJsBuilder.add(2.0);
//        daJsBuilder.add(3.0);
//        daJsBuilder.add(4.0);
//        daJsBuilder.add(5.0);
//        daJsBuilder.add(1.0);
//        daJsBuilder.add(2.0);
//        daJsBuilder.add(3.0);
//        daJsBuilder.add(4.0);

        JsonArray datosJson = daJsBuilder.build();

        jBuilder.add("datos", datosJson);
        return jBuilder.build();
    }

}
