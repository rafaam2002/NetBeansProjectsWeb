/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import javax.mail.Session as MailSession;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

    String campoIncorrecto = "";
    String motivoCampoIncorrecto;

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
        String accion = request.getPathInfo();
        String vista;
        HttpSession session;
        Usuario user;
        //        Query query;
        //        System.out.println("Entra en controladorLogin doget");
        switch (accion) {
            case "/logout":
                System.out.println("Esta hacienod logout");
                session = request.getSession();
                session.removeAttribute("idUsuario");
                session.invalidate();
                vista = "/index.html";
                break;
            case "/eliminarCuenta":
                System.out.println("Esta eliminando cuenta");
                session = request.getSession();
                session.removeAttribute("idUsuario");
                user = (Usuario) request.getAttribute("user");
                delete(user);
                vista = "/index.html";
                break;
            default:
                vista = "/index.html";
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
//        processRequest(request, response);
//        System.out.println("Entra en controladorLogin doPost");
        String accion = request.getPathInfo();
        String vista, nick, password, numCuenta, banner, oldPwd;
        Usuario user;
        Query query;

        switch (accion) {
            case "/newUser":
                String name = request.getParameter("nombre");
                String apellidos = request.getParameter("apellidos");
                nick = request.getParameter("nick");
                String numTel = request.getParameter("num_tel");
                password = request.getParameter("password");
                String checkPassword = request.getParameter("password_check");
                String bizumActive = request.getParameter("bizum");
                if (nickNoRepetido(nick) && password.equals(checkPassword) && numTelNoRepetido(numTel)
                        && name != null) {
                    try {
                        user = new Usuario();
                        user.setNombre(name);
                        user.setApellido(apellidos);
                        user.setNick(nick);
                        user.setNumTel(numTel);
                        user.setPassword(cifrarPassword(password));
                        user.setDineroDouble(3000.0);
                        user.setBizumActive(false);
                        user.setNumCuenta(generarNumCuenta());
                        if (bizumActive != null && bizumActive.equals("active")) {
                            user.setBizumActive(true);
                        } else {
                            user.setBizumActive(false);
                        }
                        persist(user);
                        //Busco el usuario creado para encontrar su id ya que este se genera automaticamente
                        query
                                = em.createNamedQuery("Usuario.findByNick", Usuario.class
                                );
                        query.setParameter("nick", nick);
                        user = (Usuario) query.getSingleResult();
                        numCuenta = Long.toString(user.getNumCuenta());
                        request.setAttribute("numCuenta", numCuenta);
                        HttpSession session = request.getSession();
                        session.setAttribute("idUsuario", user.getId());
                        request.setAttribute("nickUsuario", nick);
                        vista = "/WEB-INF/main.jsp";
                    } catch (Exception ex) { //NoSuchAlgorithmException ex
                        vista = "/index.html";
                        System.out.println("No se ha podido crear MessageDigest");
                        Logger
                                .getLogger(ControladorLogin.class
                                        .getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    if (campoIncorrecto.isEmpty()) {
                        campoIncorrecto = "Contraseña";
                        motivoCampoIncorrecto = "La contraseña repetida con coincide";
                    }
                    banner = "<div class=\"flex justify-center gap-x-6 bg-zinc-900 px-6 py-2.5\">\n"
                            + "            <p class=\" text-sm leading-6 text-red-500\">\n"
                            + "                <a href=\"#\">\n"
                            + "                    <strong class=\"font-semibold\">Campo " + campoIncorrecto + " no válido</strong><svg viewBox=\"0 0 2 2\" class=\"mx-2 inline h-0.5 w-0.5 fill-current\" aria-hidden=\"true\"><circle cx=\"1\" cy=\"1\" r=\"1\" /></svg>" + motivoCampoIncorrecto + "\n"
                            + "                </a>\n"
                            + "            </p>\n"
                            + "        </div>";
                    campoIncorrecto = "";
                    request.setAttribute("banner", banner);
                    vista = "/nuevoUsuario.jsp";
                }

                break;
            case "/login":
                nick = request.getParameter("nick");
                password = request.getParameter("password");

                if (password != null && nick != null) {
                    try {
                        query = em.createNamedQuery("Usuario.findByNick", Usuario.class);
                        query.setParameter("nick", nick);
                        user = (Usuario) query.getSingleResult();
                        if (checkearPassword(password, user.getPassword())) { //checkearPassword(password,user.getPassword())
                            HttpSession session = request.getSession();
                            session.setAttribute("idUsuario", user.getId());
                            request.setAttribute("nickUsuario", nick);
                            numCuenta = Long.toString(user.getNumCuenta());
                            request.setAttribute("numCuenta", numCuenta);
                            vista = "/WEB-INF/main.jsp";
                        } else {
                            System.out.println("password incorrecta");
                            vista = "/index.html";
                        }
                    } catch (NoResultException | NoSuchAlgorithmException ex) {
                        System.out.println("El usuario no existe " + ex.getMessage());
                        vista = "/index.html";
                    }

                } else {
                    System.out.println("Password o nick null");
                    vista = "/index.html";
                }
                break;
            case "/recuperarPassword":
                String correoRec = request.getParameter("email");
                try {
                    String asunto = "Recuperacion de contraseña";
                    String mensaje = "Su contraseña es: " + "<campocontrasenia>";
                    String emailFrom = "jmleal2010@gmail.com";
                    String passwordFrom = "fapzbrxdcvuxomop";//qfoqpagpibtinels
                    //hbywselthdgwfock : rm2.1

//                    Properties props = new Properties();
//                    props.setProperty("mail.smtp.host", "smtp.gmail.com");
//                    props.setProperty("mail.smtp.starttls.enable", "true");
//                    props.setProperty("mail.smtp.port", "587");
//                    props.setProperty("mail.smtp.user", emailFrom);
//                    props.setProperty("mail.smtp.auth", "true");
//                    props.setProperty("mail.smtp.password", "fapzbrxdcvuxomop");
//                    props.setProperty("mail.smtp.protocols", "TLSv1.2");
//                    props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
                    Properties prop = System.getProperties();
                    prop.put("mail.smtp.starttls.enable", "true");
                    prop.put("mail.smtp.host", "smtp.gmail.com");
                    prop.put("mail.smtp.user", emailFrom);
                    prop.put("mail.smtp.password", passwordFrom);
                    prop.put("mail.smtp.port", "587");
                    prop.put("mail.smtp.auth", "true");

//                    javax.mail.Session mSession = javax.mail.Session.getDefaultInstance(props);
                    javax.mail.Session mSession = javax.mail.Session.getDefaultInstance(prop,null);

                    try {
                        MimeMessage mCorreo = new MimeMessage(mSession);
                        mCorreo.setFrom(new InternetAddress(emailFrom));
                        mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(correoRec));
                        mCorreo.setSubject(asunto);
                        mCorreo.setText(mensaje, "ISO-8859-1", "html");

                        Transport t = mSession.getTransport("smtp");
                        t.connect("smtp.gmail.com", emailFrom, passwordFrom);
                        t.sendMessage(mCorreo, mCorreo.getAllRecipients());
                        t.close();
                        System.out.println("Correo enviado");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

//                    Properties props = new Properties();
//                    props.setProperty("mail.smtp.host", "smtp.gmail.com");
//                    props.setProperty("mail.smtp.startlls.enable", "true");
//                    props.setProperty("mail.smtp.port", "587");
//                    props.setProperty("mail.smtp.user", emailForm);
//                    props.setProperty("mail.smtp.auth", "true");
//                    props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
//
//                    javax.mail.Session sesion = javax.mail.Session.getDefaultInstance(props);
////                    qfoq pagp ibti nels    
//                    String correoEmi = "achillesonlinebank@gmail.com";
//                    String passEmi = " qfoqpagpibtinels";
//                    String asunto = "Recuperacion de contraseña";
//                    String mensaje = "Su contraseña es: " + "<campocontrasenia>";
//
//                    MimeMessage message = new MimeMessage(sesion);
//                    message.setFrom(new InternetAddress(correoEmi));
//
//                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoRec));
//                    message.setSubject(asunto);
//                    message.setText(mensaje);
//
//                    Transport t = sesion.getTransport("smtp");
//                    t.connect(correoEmi, passEmi);
//                    t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
//                    t.close();
//
//                    System.out.println("Correo enviado");
                } catch (Exception e) {
                    System.out.println("---------------------------");
                    System.out.println("Excepcion: " + e.getMessage());
                }

                vista = "/index.html";
                break;

            default:
                vista = "/index.html";
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

    private boolean nickNoRepetido(String nick) {
        Query query;
        query
                = em.createNamedQuery("Usuario.findByNick", Long.class
                );
        query.setParameter("nick", nick);
        if (query.getResultList().isEmpty()) {
            return true;
        } else {
            campoIncorrecto = "Nick";
            motivoCampoIncorrecto = "El nick introduccido ya pertenece a otro usuario";
            return false;
        }
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

    public void delete(Object object) {
        try {
            utx.begin();

            // Si no está gestionada, se hace un merge para adjuntarla al contexto de persistencia
            if (!em.contains(object)) {
                object = em.merge(object);
            }
            em.remove(object);

            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    private boolean numTelNoRepetido(String numTel) {
        Query query;
        query
                = em.createNamedQuery("Usuario.findByNumTel", Long.class
                );
        query.setParameter("numTel", numTel);
        if (query.getResultList().isEmpty()) {
            return true;
        } else {
            campoIncorrecto = "Número de teléfono";
            motivoCampoIncorrecto = "El número ya pertenece a otro usuario";
            return false;
        }
    }

    private long generarNumCuenta() {
        long nuevoNumCuenta;
        do {
            // Generar un número aleatorio de 20 dígitos
            nuevoNumCuenta = (long) (Math.random() * 9_000_000_000_000_000_000L) + 1_000_000_000_000_000_000L;
        } while (numeroCuentaExistente(nuevoNumCuenta));

        return nuevoNumCuenta;
    }

    private boolean numeroCuentaExistente(long nuevoNumCuenta) {
        Query query = em.createNamedQuery("Usuario.findByNumCuenta", Long.class
        );
        query.setParameter("numCuenta", nuevoNumCuenta);

        return !query.getResultList().isEmpty();
    }

    private String cifrarPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    private boolean checkearPassword(String password, String userPassword) throws NoSuchAlgorithmException {
        return (cifrarPassword(password).equals(userPassword));
    }

}
