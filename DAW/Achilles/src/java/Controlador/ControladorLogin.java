/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
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

/**
 *
 * @author rafaa
 */
@WebServlet(name = "ControladorLogin", urlPatterns = {"/ControladorLogin/*"})
public class ControladorLogin extends HttpServlet {

    @PersistenceContext(unitName = "AchillesPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    private final Random rdm = new Random();

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
            out.println("<title>Servlet ControladorLogin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorLogin at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);

        String accion = request.getPathInfo();
        String vista, nick, password;
        Usuario user = new Usuario();
        Query query;

        switch (accion) {
            case "/newUser":
                String name = request.getParameter("nombre");
                String apellidos = request.getParameter("apellidos");
                nick = request.getParameter("nick");
                String numTel = request.getParameter("num_tel");
                password = request.getParameter("password");
                String checkPassword = request.getParameter("password_check");
                if (nickNoRepetido(nick) && password.equals(checkPassword) && numTelNoRepetido(numTel)
                        && name != null) {
                    user.setNombre(name);
                    user.setApellido(apellidos);
                    user.setNick(nick);
                    user.setNumTel(numTel);
                    user.setPassword(password);
                    user.setDineroDouble(0.0);
                    user.setBizumActive(false);
                    user.setNumCuenta(generarNumCuenta());
                    vista = "/WEB-INF/main.jsp";
//                    persist(user);
                } else {
                    vista = "/nuevoUsuario.html";
                }

                System.out.println(name);

                break;
            case "/login":
                nick = request.getParameter("nick");
                password = request.getParameter("password");
                if (password != null && nick != null) {
                    try {
                        query = em.createNamedQuery("Usuario.findByNick", Usuario.class);
                        query.setParameter("nick", nick);
                        user = (Usuario) query.getSingleResult();
                        if (password.equals(user.getPassword())) {
                            vista = "/WEB-INF/main.jsp";
                        } else {
                            vista = "/index.html";
                        }
                    } catch (NoResultException ex) {
                        System.out.println("El usuario no existe " + ex.getMessage());
                    }

                } else {
                    vista = "/index.html";
                }

            default:
                vista = "/index.html";
        }
        System.err.println(vista);
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

    private boolean nickNoRepetido(String nick) {
        Query query;
        query = em.createNamedQuery("Usuario.findByNick", Long.class);
        query.setParameter("nick", nick);
        return query.getResultList().isEmpty();
    }

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

    private boolean numTelNoRepetido(String numTel) {
        Query query;
        query = em.createNamedQuery("Usuario.findByNumTel", Long.class);
        query.setParameter("numTel", numTel);
        return query.getResultList().isEmpty();
    }

    private long generarNumCuenta() {
        long nuevoNumCuenta;
        do {
            nuevoNumCuenta = Math.abs(rdm.nextLong());  // Genera número aleatorio de 64 bits
        } while (numeroCuentaExistente(nuevoNumCuenta));
        
        return nuevoNumCuenta;
    }

    private boolean numeroCuentaExistente(long nuevoNumCuenta) {
         Query query = em.createNamedQuery("Usuario.findByNumCuenta", Long.class);
        query.setParameter("numCuenta", nuevoNumCuenta);

        return !query.getResultList().isEmpty();
    }

}
