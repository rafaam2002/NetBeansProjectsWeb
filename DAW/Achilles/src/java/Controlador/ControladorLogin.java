/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Pregunta1;
import Modelo.Pregunta2;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;

//import javax.mail.Session as MailSession;
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
import org.json.JSONObject;

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

    private String campoIncorrecto = "";
    private String motivoCampoIncorrecto;
    private Usuario userRecuperacion;

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
        String vista, nickRecuperacion;
        HttpSession session;
        Usuario user;
        Query query;
        vista = "/index.html";
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
            case "/getPreguntasUsuario":
                nickRecuperacion = request.getParameter("nick");
                try {
                    query = em.createNamedQuery("Usuario.findByNick", Usuario.class);
                    query.setParameter("nick", nickRecuperacion);
                    userRecuperacion = (Usuario) query.getSingleResult();
                    JSONObject respuesta = new JSONObject();
                    respuesta.put("p1", userRecuperacion.getPregunta1().getPregunta());
                    respuesta.put("p2", userRecuperacion.getPregunta2().getPregunta());
                    // Configurar la respuesta del servlet
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    // Enviar el objeto JSON como respuesta
                    PrintWriter out = response.getWriter();
                    out.print(respuesta.toString());
                    out.flush();
                } catch (Exception e) {
                    userRecuperacion = null;
                }
                break;
            default:
                vista = "/index.html";
        }
        if (!accion.equals("/getPreguntasUsuario")) {
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
//        processRequest(request, response);
//        System.out.println("Entra en controladorLogin doPost");
        String accion = request.getPathInfo();
        String vista, nick, password, numCuenta, banner;
        Usuario user;
        Query query;
        vista = "/index.html";

        switch (accion) {
            case "/newUser":
                String name = request.getParameter("nombre");
                String apellidos = request.getParameter("apellidos");
                nick = request.getParameter("nick");
                String numTel = request.getParameter("num_tel");
                password = request.getParameter("password");
                String checkPassword = request.getParameter("password_check");
                String bizumActive = request.getParameter("bizum");
                String indexP1 = request.getParameter("pregunta_1");
                String indexP2 = request.getParameter("pregunta_2");
                String respuestaP1 = request.getParameter("respuesta_1");
                String respuestaP2 = request.getParameter("respuesta_2");

                query = em.createNamedQuery("Pregunta1.findById", Pregunta1.class);
                query.setParameter("id", Long.valueOf(indexP1));
                Pregunta1 p1 = (Pregunta1) query.getSingleResult();
                query = em.createNamedQuery("Pregunta2.findById", Pregunta2.class);
                query.setParameter("id", Long.valueOf(indexP2));
                Pregunta2 p2 = (Pregunta2) query.getSingleResult();

                if (nickNoRepetido(nick) && password.equals(checkPassword) && numTelNoRepetido(numTel)
                        && name != null && checkPreguntasRespondidas(indexP1, indexP2)) {
                    try {
                        user = new Usuario();
                        user.setNombre(name);
                        user.setApellido(apellidos);
                        user.setNick(nick);
                        user.setNumTel(numTel);
                        user.setPassword(cifrarMD5(password));
                        user.setDineroDouble(3000.0);
                        user.setBizumActive(false);
                        user.setNumCuenta(generarNumCuenta());
                        user.setPregunta1(p1);
                        user.setPregunta2(p2);
                        user.setRespuestaP1(cifrarMD5(respuestaP1.toLowerCase()));
                        user.setRespuestaP2(cifrarMD5(respuestaP2.toLowerCase()));
                        p1.getUsuarios().add(user);
                        p2.getUsuarios().add(user);

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
            case "/cambiarContrasenia":
                if (userRecuperacion != null) {
                    String r1 = request.getParameter("respuesta_1").toLowerCase();
                    String r2 = request.getParameter("respuesta_2").toLowerCase();
                    String nPwd = request.getParameter("nueva_pwd");
                    String nPwdRepeticion = request.getParameter("nueva_pwd_repeticion");
                    try {
                        if (userRecuperacion.getRespuestaP1().equals(cifrarMD5(r1))
                                && userRecuperacion.getRespuestaP2().equals(cifrarMD5(r2))
                                && nPwd.equals(nPwdRepeticion)) {
                            userRecuperacion.setPassword(cifrarMD5(nPwd));
                            update(userRecuperacion);
                        } else {
                            campoIncorrecto = " ";
                            motivoCampoIncorrecto = "Porfavor rellene todos los campos correctamente";
                        }
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    vista = "/index.html";
                } else {
                    campoIncorrecto = "nick";
                    motivoCampoIncorrecto = "debe indicar su nick";
                }
                if (!campoIncorrecto.isEmpty()) {
                    banner = "<div class=\"flex justify-center gap-x-6 bg-zinc-900 px-6 py-2.5\">\n"
                            + "            <p class=\" text-sm leading-6 text-red-500\">\n"
                            + "                <a href=\"#\">\n"
                            + "                    <strong class=\"font-semibold\">Campo " + campoIncorrecto + " no válido</strong><svg viewBox=\"0 0 2 2\" class=\"mx-2 inline h-0.5 w-0.5 fill-current\" aria-hidden=\"true\"><circle cx=\"1\" cy=\"1\" r=\"1\" /></svg>" + motivoCampoIncorrecto + "\n"
                            + "                </a>\n"
                            + "            </p>\n"
                            + "        </div>";
                    campoIncorrecto = "";
                    request.setAttribute("banner", banner);
                    vista = "/recuperacionPassword.jsp";
                    campoIncorrecto = "";
                }

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
                = em.createNamedQuery("Usuario.findByNick", Usuario.class
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

    private String cifrarMD5(String password) throws NoSuchAlgorithmException {

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
        return (cifrarMD5(password).equals(userPassword));
    }

    private boolean checkPreguntasRespondidas(String indexP1, String indexP2) {
        if (!indexP1.isEmpty() && !indexP2.isEmpty()) {
            return true;
        } else {
            campoIncorrecto = "Preguntas";
            motivoCampoIncorrecto = "Debes responder a las dos preguntas";
            return false;
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
