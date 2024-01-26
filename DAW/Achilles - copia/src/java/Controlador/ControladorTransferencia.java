/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Transferencia;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

/**
 *
 * @author rafaa
 */
@WebServlet(name = "ControladorTransferencia", urlPatterns = {"/ControladorTransferencia/*"})
public class ControladorTransferencia extends HttpServlet {

    @PersistenceContext(unitName = "AchillesPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

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
            out.println("<title>Servlet ControladorTransferencia</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorTransferencia at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String vista = "/WEB-INF/main.jsp";
        Usuario user, userRec;
        Query query;
        String numCuenta, fechaFormateada;
        LocalDate fechaActual;
        DateTimeFormatter formatter;

        switch (accion) {
            case "/hacerTransferencia":
                user = (Usuario) request.getAttribute("user");
                numCuenta = Long.toString(user.getNumCuenta());
                request.setAttribute("numCuenta", numCuenta);
                request.setAttribute("nickUsuario", user.getNick());
//                first-name
                String nombreRec = request.getParameter("nombre_destinatario");
                String apellidosRec = request.getParameter("apellidos_destinatario");
                String numCuentaString = request.getParameter("iban");
                double importe = Double.parseDouble(request.getParameter("importe"));
                String concepto = request.getParameter("concepto");
                String fecha = request.getParameter("fecha");
                long numCuentaRec = Long.parseLong(numCuentaString);
                query = em.createNamedQuery("Usuario.findByNumCuenta", Usuario.class);
                query.setParameter("numCuenta", numCuentaRec);
                userRec = (Usuario) query.getSingleResult();

                //si el emisor y receptor son el mismo no se puede hacer 
                if (!user.equals(userRec)) {
                    //el nombre y apellidos introducidos debe ser el mismo que el del receptor
                    if (nombreRec.equals(userRec.getNombre())) {
                        if (apellidosRec.equals(userRec.getApellido())) {
                            Transferencia transferencia = new Transferencia();
                            fechaActual = LocalDate.now();
                            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            fechaFormateada = fechaActual.format(formatter);
                            transferencia.setFecha(fechaFormateada);
                            transferencia.setCantidad(importe);
                            transferencia.setEmiTransferencia(user);
                            transferencia.setRecTransferencia(userRec);

                            user.setDineroDouble(user.getDineroDouble() - importe);
                            userRec.setDineroDouble(userRec.getDineroDouble() + importe);
                            user.gettEnviadas().add(transferencia);
                            userRec.gettRecividas().add(transferencia);

                            persist(transferencia);
                            update(user);
                            update(userRec);
                            System.out.println("Transferencia realizada entre " + user.getNombre() + " y " + userRec.getNombre());
                        }
                    }
                }
                break;
        }
        RequestDispatcher rd = request.getRequestDispatcher(vista);
        rd.forward(request, response);
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

}
