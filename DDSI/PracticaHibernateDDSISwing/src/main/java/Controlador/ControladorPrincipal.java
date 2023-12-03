package Controlador;

import Modelo.Actividad;
import Modelo.ActividadDAO;
import Modelo.Monitor;
import Modelo.MonitorDAO;
import Modelo.Socio;
import Vista.VistaMensaje;
import Vista.VistaMonitor;
import Vista.VistaSocio;
import Vista.PanelMonitores;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import Modelo.SocioDAO;
import Vista.vistaPrincipal;
import Vista.PanelPrincipal;
import Vista.PanelSocios;
import Vista.VMensaje;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import org.hibernate.HibernateException;

/**
 *
 * @author rafaa
 */
public class ControladorPrincipal implements ActionListener {

    private final SessionFactory sessionFactory;
    private Session session;
    private Transaction tr;
    private final vistaPrincipal vistaP;
    private final PanelMonitores pMonitores;
    private final PanelPrincipal pPrincipal;
    private final PanelSocios pSocios;
    private final UtilTablasMonitor uTablasM;
    private final MonitorDAO monitorDAO;
    private  final VMensaje vMensaje;

    private final ControladorSocios controladorS;
    private final ControladorMonitor controladorM;

//    private final vistaLogin vLoginMenu;
    public ControladorPrincipal(SessionFactory s) {

        //Inixcializar Frame Principal
        vistaP = new vistaPrincipal();
        vistaP.getContentPane().setLayout(new CardLayout());
        vistaP.setLocationRelativeTo(null);
        vistaP.setVisible(true);
        addListeners();

        //Inicializar y agregar al Frame Principal los Paneles
        pPrincipal = new PanelPrincipal();
        pMonitores = new PanelMonitores();
        pSocios = new PanelSocios();
        vistaP.add(pPrincipal);
        vistaP.add(pMonitores);
        vistaP.add(pSocios);

        muestraPanel("pPrincipal");

        //Inicializar las clases que se encargan de las tablas
        uTablasM = new UtilTablasMonitor(pMonitores);

        //logica del Controlador
        sessionFactory = s;
        monitorDAO = new MonitorDAO();

        //inicializo otros controladores
        controladorS = new ControladorSocios(pSocios, sessionFactory);
        controladorM = new ControladorMonitor(pMonitores, sessionFactory);
        
        vMensaje = new VMensaje();
        vMensaje.MensajeInfo(pPrincipal, "Conexion correcta");
        
        

    }

//    private void menu1() {
//        int opcion;
//
//        var sociodao = new SocioDAO();
//        var actividaddao = new ActividadDAO(sessionFactory.openSession());
//        var sc = new Scanner(System.in);
//        do {
//            VistaSocio.muestraMenu();
//            opcion = sc.nextInt();
//            switch (opcion) {
//                case 1 -> {
//                    try {
//                        session = sessionFactory.openSession();
//                        tr = session.beginTransaction();
//                        var socios = sociodao.getSociosHQL(session);
//                        for (Socio socio : socios) {
//                            VistaSocio.muestraSocio(socio);
//                        }
//                    } catch (HibernateException e) {
//                        tr.rollback();
//                        VistaMensaje.mensajeConsola("Error en la petición de socios " + e.getMessage());
//                    } finally {
//                        if (session != null && session.isOpen()) {
//                            session.close();
//                        }
//                    }
//                }
//
//                case 2 -> {
//                    try {
//                        session = sessionFactory.openSession();
//                        tr = session.beginTransaction();
//                        var socios = sociodao.getSociosSQL(session);
//                        for (Socio socio : socios) {
//                            VistaSocio.muestraSocio(socio);
//                        }
//                    } catch (HibernateException e) {
//                        tr.rollback();
//                        VistaMensaje.mensajeConsola("Error en la petición de socios " + e.getMessage());
//                    } finally {
//                        if (session != null && session.isOpen()) {
//                            session.close();
//                        }
//                    }
//
//                }
//
//                case 3 -> {
//                    try {
//                        session = sessionFactory.openSession();
//                        tr = session.beginTransaction();
//                        var socios = sociodao.getSociosNamedQuery(session);
//                        for (Socio socio : socios) {
//                            VistaSocio.muestraSocio(socio);
//                        }
//                    } catch (HibernateException e) {
//                        tr.rollback();
//                        VistaMensaje.mensajeConsola("Error en la petición de socios " + e.getMessage());
//                    } finally {
//                        if (session != null && session.isOpen()) {
//                            session.close();
//                        }
//                    }
//                }
//
//                case 4 -> {
//                    try {
//                        session = sessionFactory.openSession();
//                        tr = session.beginTransaction();
//                        var camposSocios = sociodao.getNomTelSocios(session);
//                        for (Object[] camposSocio : camposSocios) {
//                            VistaSocio.muestraCampos(camposSocio);
//                        }
//                    } catch (HibernateException e) {
//                        tr.rollback();
//                        VistaMensaje.mensajeConsola("Error en la petición de socios " + e.getMessage());
//                    } finally {
//                        if (session != null && session.isOpen()) {
//                            session.close();
//                        }
//                    }
//                }
//                case 5 -> {
//                    try {
//                        session = sessionFactory.openSession();
//                        tr = session.beginTransaction();
//                        sc.nextLine();
//                        VistaMensaje.mensajeConsola("Introduzca la categoria:");
//                        String categoria = sc.next();
//                        char categoriaChar = categoria.charAt(0);
//                        var camposSocios = sociodao.getSociosCategoria(session, categoriaChar);
//                        for (Object[] socioNomCat : camposSocios) {
//                            VistaSocio.muestraCampos(socioNomCat);
//                        }
//                    } catch (HibernateException e) {
//                        tr.rollback();
//                        VistaMensaje.mensajeConsola("Error en la petición de socios " + e.getMessage());
//                    } finally {
//                        if (session != null && session.isOpen()) {
//                            session.close();
//                        }
//                    }
//                }
//                case 6 -> {
//                    try {
//                        session = sessionFactory.openSession();
//                        tr = session.beginTransaction();
//                        sc.nextLine();
//                        VistaMensaje.mensajeConsola("Introduzca el codigo de la actividad:");
//                        var idActividad = sc.next();
//                        var monitor = actividaddao.getMonitorResponsable(idActividad);
//                        VistaMonitor.muestraMonitor(monitor);
//                    } catch (HibernateException e) {
//                        tr.rollback();
//                        VistaMensaje.mensajeConsola("Error en la petición de socios " + e.getMessage());
//                    } finally {
//                        if (session != null && session.isOpen()) {
//                            session.close();
//                        }
//                    }
//                }
//                case 7 -> {
//                    try {
//                        session = sessionFactory.openSession();
//                        tr = session.beginTransaction();
//                        sc.nextLine();
//                        VistaMensaje.mensajeConsola("Introduzca el codigo de la actividad:");
//                        var idActividad = sc.next();
//                        var sociosActividad = actividaddao.getSociosActividad(idActividad);
//                        for (Socio s : sociosActividad) {
//                            VistaSocio.muestraSocio(s);
//                        }
//                    } catch (HibernateException e) {
//                        tr.rollback();
//                        VistaMensaje.mensajeConsola("Error en la petición de socios " + e.getMessage());
//                    } finally {
//                        if (session != null && session.isOpen()) {
//                            session.close();
//                        }
//                    }
//                }
//                case 8 -> {
//                    try {
//                        session = sessionFactory.openSession();
//                        tr = session.beginTransaction();
//
//                        var s = new Socio("S011", "Rafa", "49957379K", "1/1/0001", 'A');
//                        sociodao.insertarSocio(session, s);
//                    } catch (HibernateException e) {
//                        tr.rollback();
//                        VistaMensaje.mensajeConsola("Error en la petición de socios " + e.getMessage());
//                    } finally {
//                        if (session != null && session.isOpen()) {
//                            session.close();
//                        }
//                    }
//                }
//                case 9 -> {
//                    try {
//                        session = sessionFactory.openSession();
//                        tr = session.beginTransaction();
//
//                        sociodao.eliminarSocio(session, "S011");
//                    } catch (HibernateException e) {
//                        tr.rollback();
//                        VistaMensaje.mensajeConsola("Error en la petición de socios " + e.getMessage());
//                    } finally {
//                        if (session != null && session.isOpen()) {
//                            session.close();
//                        }
//                    }
//                }
//                case 10 -> {
//                    try {
//                        session = sessionFactory.openSession();
//                        tr = session.beginTransaction();
//
//                        sc.nextLine();
//                        VistaMensaje.mensajeConsola("Introduzca el numero del socio:");
//                        String numSocio = sc.next();
//                        VistaMensaje.mensajeConsola("Introduzca la nueva categoria del socio:");
//                        String categoria = sc.next();
//                        char categoriaChar = categoria.charAt(0);
//                        sociodao.actualizarCategoria(session, numSocio, categoriaChar);
//                    } catch (HibernateException e) {
//                        tr.rollback();
//                        VistaMensaje.mensajeConsola("Error en la petición de socios " + e.getMessage());
//                    } finally {
//                        if (session != null && session.isOpen()) {
//                            session.close();
//                        }
//                    }
//                }
//                case 11 -> {
//                    try {
//                        session = sessionFactory.openSession();
//                        tr = session.beginTransaction();
//
//                        sc.nextLine();
//                        VistaMensaje.mensajeConsola("Introduzca el numero del socio:");
//                        String numSocio = sc.next();
//                        VistaMensaje.mensajeConsola("Introduzca en codigo de la actividad:");
//                        var idActividad = sc.next();
//                        sociodao.addActividad(session, numSocio, idActividad);
//                    } catch (HibernateException e) {
//                        tr.rollback();
//                        VistaMensaje.mensajeConsola("Error en la petición de socios " + e.getMessage());
//                    } finally {
//                        if (session != null && session.isOpen()) {
//                            session.close();
//                        }
//                    }
//                }
//
//                case 12 -> {
//                    try {
//                        session = sessionFactory.openSession();
//                        tr = session.beginTransaction();
//
//                        sc.nextLine();
//                        VistaMensaje.mensajeConsola("Introduzca el numero del socio:");
//                        String numSocio = sc.next();
//                        VistaMensaje.mensajeConsola("Introduzca en codigo de la actividad:");
//                        var idActividad = sc.next();
//                        sociodao.removeActividad(session, numSocio, idActividad);
//                    } catch (HibernateException e) {
//                        tr.rollback();
//                        VistaMensaje.mensajeConsola("Error en la petición de socios " + e.getMessage());
//                    } finally {
//                        if (session != null && session.isOpen()) {
//                            session.close();
//                        }
//                    }
//                }
//                case 13 -> {
//                    try {
//                        session = sessionFactory.openSession();
//                        tr = session.beginTransaction();
//
//                        sc.nextLine();
//                        VistaMensaje.mensajeConsola("Introduzca en codigo de la actividad:");
//                        var idActividad = sc.next();
//                        var socios = actividaddao.getSociosActividad(idActividad);
//                        for (Socio socio : socios) {
//                            Object[] camposSocio = {socio.getNombre(), socio.getTelefono()};
//                            VistaSocio.muestraCampos(camposSocio);
//                        }
//                    } catch (HibernateException e) {
//                        tr.rollback();
//                        VistaMensaje.mensajeConsola("Error en la petición de socios " + e.getMessage());
//                    } finally {
//                        if (session != null && session.isOpen()) {
//                            session.close();
//                        }
//                    }
//                }
//                case 14 -> {
//                    try {
//                        session = sessionFactory.openSession();
//                        tr = session.beginTransaction();
//
//                        sc.nextLine();
//                        VistaMensaje.mensajeConsola("Introduzca en numero del socio:");
//                        var numSocio = sc.next();
//                        var actividades = sociodao.getActividades(session, numSocio);
//
//                        for (Actividad a : actividades) {
//                            Object[] camposActividad = {a.getNombre(), a.getPrecioBaseMes()};
//                            VistaSocio.muestraCampos(camposActividad);
//                        }
//                    } catch (HibernateException e) {
//                        tr.rollback();
//                        VistaMensaje.mensajeConsola("Error en la petición de socios " + e.getMessage());
//                    } finally {
//                        if (session != null && session.isOpen()) {
//                            session.close();
//                        }
//                    }
//                }
//
//            }
//        } while (opcion != 0);
//    }

    private void addListeners() {
        vistaP.GestionMonitores.addActionListener(this);
        vistaP.SalirAplicacion.addActionListener(this);
        vistaP.GestionSocios.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "SalirAplicacion" -> {
                vistaP.dispose();
                System.exit(0);
            }
            case "GestionMonitores" -> {

                muestraPanel("pMonitores");
                controladorM.init();
            }
            case "GestionSocios" -> {
                muestraPanel("pSocios");
                controladorS.init();
            }
            default -> {
                VistaMensaje.mensajeConsola("No se ha reconocido el evento, revisa los nombres de las variables de las vista");
            }

        }
    }

    private void muestraPanel(String panel) {

        pPrincipal.setVisible(false);
        pMonitores.setVisible(false);
        pSocios.setVisible(false);

        switch (panel) {
            case "pMonitores" -> {
                pMonitores.setVisible(true);
            }
            case "pSocios" -> {
                pSocios.setVisible(true);
            }
            case "pPrincipal" -> {
                pPrincipal.setVisible(true);
            }
            default -> {
                VistaMensaje.mensajeConsola("El panel indicado no existe");
            }
        }
    }

    private ArrayList<Monitor> pideMonitores() throws Exception {
        ArrayList<Monitor> monitores = monitorDAO.listaMonitores(session);
        return monitores;
    }

}
