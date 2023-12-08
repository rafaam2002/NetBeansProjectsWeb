package Controlador;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import Modelo.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
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
@WebServlet(urlPatterns = {"/usuarios/*"})
public class Controlador1 extends HttpServlet {

    @PersistenceContext(unitName = "WebApplication2PU")
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
//        System.out.println("La peticion se esta controlando");
        String accion = request.getPathInfo();
        String vista;
        Query consulta;
        List<Usuarios> usuarios;
        switch (accion) {
            case "/showUsers":
                System.out.println("Entra en showUsers");
                consulta = em.createNamedQuery("Usuarios.findAll", Usuarios.class);
                usuarios = consulta.getResultList();

                request.setAttribute("usuarios", usuarios);
                System.out.println("Usuario1 : " + usuarios.get(0).getNombre());

                vista = "/WEB-INF/showUsers.jsp";
                break;
            case "/newUser":

                vista = "/newUser.jsp";
                break;
            case "/persistUser":
                System.out.println("Entrando en el persist");
                String name = request.getParameter("nombre");
                String numero = request.getParameter("numero");
                Usuarios user = new Usuarios();
                int numeroInt;
                if (name != null && numero != null) {
                    user.setNombre(name);
                    user.setApellidos(name);
                    numeroInt = Integer.parseInt(numero);
                    user.setNumero(numeroInt);
                }
                try {
                    persist(user);
                    System.out.println("Usuario creado correctamente");
                } catch (Exception ex) {
                    System.out.println("Error en el persist " + ex.getMessage());
                }
                consulta = em.createNamedQuery("Usuarios.findAll", Usuarios.class);
                usuarios = consulta.getResultList();
                System.out.println("Usuario1 : " + usuarios.get(0).getNombre());

                request.setAttribute("usuarios", usuarios);
                System.out.println("Se introduce el atributo");
                vista = "/showUsers.jsp";
                System.out.println("Se guarda la vista");
                break;
            default:
                vista = "/index.html";
        }

        RequestDispatcher rd = request.getRequestDispatcher(vista);
        rd.forward(request, response);
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
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

}
