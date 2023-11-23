package Controlador;

import Modelo.Actividad;
import Modelo.ActividadDAO;
import Modelo.Socio;
import Vista.VistaMensaje;
import Vista.VistaMonitor;
import Vista.VistaSocio;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import Modelo.SocioDAO;

/**
 *
 * @author rafaa
 */
public class ControladorPrincipal {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction tr;

    public ControladorPrincipal(SessionFactory sesionFactory) {
        this.sessionFactory = sesionFactory;
        // this.sesion = sessionFactory.openSession();
        menu1();
    }

    private void menu1() {
        int opcion;

        session = sessionFactory.openSession();

        var sociodao = new SocioDAO();
        var actividaddao = new ActividadDAO(sessionFactory.openSession());
        var sc = new Scanner(System.in);
        do {
            VistaSocio.muestraMenu();
            opcion = sc.nextInt();
            switch (opcion) {
                case 1 -> {
                    try {
                        var socios = sociodao.getSociosHQL(session);
                        for (Socio socio : socios) {
                            VistaSocio.muestraSocio(socio);
                        }
                    } catch (Exception e) {
                        VistaMensaje.mensajeConsola("No se pudieron mostrar los socios");
                        //Para que hay que crear los finally?
                    }
                }

                case 2 -> {
                    try {
                        var socios = sociodao.getSociosSQL(session);
                        for (Socio socio : socios) {
                            VistaSocio.muestraSocio(socio);
                        }
                    } catch (Exception e) {
                        VistaMensaje.mensajeConsola("No se pudieron mostrar los socios");
                    }
                }

                case 3 -> {
                    try {
                        var socios = sociodao.getSociosNamedQuery(session);
                        for (Socio socio : socios) {
                            VistaSocio.muestraSocio(socio);
                        }
                    } catch (Exception e) {
                        VistaMensaje.mensajeConsola("No se pudieron mostrar los socios");
                    }
                    try {

                    } catch (Exception e) {
                        VistaMensaje.mensajeConsola("No se pudieron mostrar los socios");
                    }
                }

                case 4 -> {
                    try {
                        var camposSocios = sociodao.getNomTelSocios(session);
                        for (Object[] camposSocio : camposSocios) {
                            VistaSocio.muestraCampos(camposSocio);
                        }
                    } catch (Exception e) {
                        VistaMensaje.mensajeConsola("No se pudieron mostrar los campos");
                        //Para que hay que crear los finally?
                    }
                }
                case 5 -> {
                    try {
                        sc.nextLine();
                        VistaMensaje.mensajeConsola("Introduzca la categoria:");
                        String categoria = sc.next();
                        char categoriaChar = categoria.charAt(0);
                        var camposSocios = sociodao.getSociosCategoria(session, categoriaChar);
                        for (Object[] socioNomCat : camposSocios) {
                            VistaSocio.muestraCampos(socioNomCat);
                        }
                    } catch (Exception e) {
                        VistaMensaje.mensajeConsola("No se pudieron mostrar los campos");
                        //Para que hay que crear los finally?
                    }
                }
                case 6 -> {
                    try {
                        sc.nextLine();
                        VistaMensaje.mensajeConsola("Introduzca el codigo de la actividad:");
                        var idActividad = sc.next();
                        var monitor = actividaddao.getMonitorResponsable(idActividad);
                        VistaMonitor.muestraMonitor(monitor);
                    } catch (Exception e) {
                        VistaMensaje.mensajeConsola("No se pudieron mostrar los campos");
                        //Para que hay que crear los finally?
                    }
                }
                case 7 -> {
                    sc.nextLine();
                    VistaMensaje.mensajeConsola("Introduzca el codigo de la actividad:");
                    var idActividad = sc.next();
                    try {
                        var sociosActividad = actividaddao.getSociosActividad(idActividad);
                        for (Socio s : sociosActividad) {
                            VistaSocio.muestraSocio(s);
                        }
                    } catch (Exception e) {
                        VistaMensaje.mensajeConsola("No se pudieron mostrar los campos");
                        //Para que hay que crear los finally?
                    }
                }
                case 8 -> {
                    try {
                        var s = new Socio("S011", "Rafa", "49957379K", "1/1/0001", 'A');
                        sociodao.insertarSocio(session, s);
                    } catch (Exception e) {
                        VistaMensaje.mensajeConsola("No se pudo dar de alta al socio");
                    }
                }
                case 9 -> {
                    try {
                        sociodao.eliminarSocio(session, "S011");
                    } catch (Exception e) {
                        VistaMensaje.mensajeConsola("No se pudo dar de baja al socio");
                    }
                }
                case 10 -> {
                    try {
                        sc.nextLine();
                        VistaMensaje.mensajeConsola("Introduzca el numero del socio:");
                        String numSocio = sc.next();
                        VistaMensaje.mensajeConsola("Introduzca la nueva categoria del socio:");
                        String categoria = sc.next();
                        char categoriaChar = categoria.charAt(0);
                        sociodao.actualizarCategoria(session, numSocio, categoriaChar);
                    } catch (Exception e) {
                        VistaMensaje.mensajeConsola("No se pudo modificar la categoria del socio");
                    }
                }
                case 11 -> {
                    try {
                        sc.nextLine();
                        VistaMensaje.mensajeConsola("Introduzca el numero del socio:");
                        String numSocio = sc.next();
                        VistaMensaje.mensajeConsola("Introduzca en codigo de la actividad:");
                        var idActividad = sc.next();
                        sociodao.addActividad(session, numSocio, idActividad);

                    } catch (Exception e) {
                        VistaMensaje.mensajeConsola("No se pudo añadir a la actividad al socio,"
                                + " asegurese de que el socio y la actividad existen");
                    }
                }

                case 12 -> {
                    try {
                        sc.nextLine();
                        VistaMensaje.mensajeConsola("Introduzca el numero del socio:");
                        String numSocio = sc.next();
                        VistaMensaje.mensajeConsola("Introduzca en codigo de la actividad:");
                        var idActividad = sc.next();
                        sociodao.removeActividad(session, numSocio, idActividad);

                    } catch (Exception e) {
                        VistaMensaje.mensajeConsola("No se pudo eliminar de la actividad al socio,"
                                + " asegurese de que el socio y la actividad existen");
                    }
                }
                case 13 -> {
                    try {
                        sc.nextLine();
                        VistaMensaje.mensajeConsola("Introduzca en codigo de la actividad:");
                        var idActividad = sc.next();
                        var socios = actividaddao.getSociosActividad(idActividad);
                        for (Socio socio : socios) {
                            Object[] camposSocio = {socio.getNombre(), socio.getTelefono()};
                            VistaSocio.muestraCampos(camposSocio);
                        }
                    } catch (Exception e) {
                        System.out.println("No se pudieron mostrar los socios");
                    }
                }
                case 14 -> {
                    try {
                        sc.nextLine();
                        VistaMensaje.mensajeConsola("Introduzca en numero del socio:");
                        var numSocio = sc.next();
                        var actividades = sociodao.getActividades(session , numSocio);

                        for (Actividad a : actividades) {
                            Object[] camposActividad = {a.getNombre(), a.getPrecioBaseMes()};
                            VistaSocio.muestraCampos(camposActividad);
                        }
                    } catch (Exception e) {
                        System.out.println("No se pudieron mostrar las actividades" + e.getMessage());
                    }
                }

            }
        } while (opcion != 0);
    }
}
