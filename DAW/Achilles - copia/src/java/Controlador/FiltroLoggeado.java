/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rafaa
 */
@WebFilter(filterName = "FiltroLoggeado", urlPatterns = {"/ControladorPrincipal/*", "/ControladorLogin/logout", 
    "/ControladorLogin/eliminarCuenta","/ControladorTransferencia/*"})
public class FiltroLoggeado implements Filter {

    @PersistenceContext(unitName = "AchillesPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public FiltroLoggeado() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

//        System.out.println("El filtro esta funcionando");
        HttpSession session;
        HttpServletRequest req = (HttpServletRequest) request;
//        if (req.getPathInfo().equals("/logout")) {
//            System.out.println("Filtro activado desde ControladorLogin/logout");
//        }
        Query query;
        Usuario user;
        session = req.getSession();

        try {
            long idUsuario = (long) session.getAttribute("idUsuario");
//            System.out.println("Filtro: idUsuario = " + idUsuario);
            query = em.createNamedQuery("Usuario.findById", Usuario.class);
            query.setParameter("id", idUsuario);
            user = (Usuario) query.getSingleResult();
//            System.err.println("Filtro: nombre usuario = " + user.getNombre());
            req.setAttribute("user", user);
//            System.out.println("Filtro Pasa a Controlador");
            //tengo un comentario en controladorLogin para ver si entra pero no entra
            chain.doFilter(request, response);
        } catch (IOException | ServletException | NullPointerException e) {
            RequestDispatcher rd = request.getRequestDispatcher("/index.html");
            System.out.println("Filtro: El usuario no esta logeado, la sesion no pertenece a ningun usuario");
            System.out.println("Filtro: e =  " + e.getMessage());
            rd.forward(request, response);
        }

    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("FiltroLoggeado:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FiltroLoggeado()");
        }
        StringBuffer sb = new StringBuffer("FiltroLoggeado(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

//    public void persist(Object object) {
//        try {
//            Context ctx = new InitialContext();
//            UserTransaction utx = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
//            utx.begin();
//            EntityManager em = (EntityManager) ctx.lookup("java:comp/env/persistence/LogicalName");
//            em.persist(object);
//            utx.commit();
//        } catch (Exception e) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
//            throw new RuntimeException(e);
//        }
//    }

}
