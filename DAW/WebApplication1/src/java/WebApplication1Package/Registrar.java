/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package WebApplication1Package;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author rafaa
 */
@WebServlet(name = "Registrar", urlPatterns = {"/Registrar"})
public class Registrar extends HttpServlet {

    @Resource(name = "resource1")
    private DataSource resource1;

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
        try {
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String strNumTel = request.getParameter("num_tel");
            String pswd = request.getParameter("password");
            String pswdCheck = request.getParameter("password_check");

            Usuario user = new Usuario();
            user.setNombre(nombre);
            user.setApellidos(apellidos);
            user.setNumero(Integer.parseInt(strNumTel));
            user.setPassword(pswd);
            user.setPasswordCheck(pswdCheck);

            //Establecer conexion
            Context c = new InitialContext();
            resource1 = (DataSource) c.lookup("jdbc/myDatasource");

            //Preparar sentencia
            try {
                Connection conn = resource1.getConnection();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO USUARIOS (NOMBRE,EDAD) VALUES  (?,?)");
                ps.setString(1, user.getNombre());
                ps.setInt(2, 22222);

                //Ejecutar sentencia y Mostrar el estado de esta       
                int filasAfectadas = ps.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("Insercion correcta");
                } else {
                    System.out.println("Fallo de insercion");
                }

                ps.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error " + ex.getMessage());
            }
            System.out.println("Nombre: " + nombre);

            //Respuesta 
            response.setContentType("text/html;chartset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html");
                out.println("<html");
                out.println("<head>");
                out.println("<title>Respuesta</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1> Perro </h1>");
                out.println("</body");
                out.println("</html");
            }

        } catch (NumberFormatException ex) {
            System.out.println("Error " + ex.getMessage());
        } catch (NamingException ex) {
            System.out.println("Error " + ex.getMessage());
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

}
