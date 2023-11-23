/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Config.HibernateUtilMariaDB;
import Config.HibernateUtilOracle;
import Vista.VistaMensaje;
import org.hibernate.SessionFactory;

/**
 *
 * @author rafaa
 */
public class ControladorLogin {

    private SessionFactory sessionFactory;


    public ControladorLogin(String DB) {
        conectarBD(DB);
    }

    private void conectarBD(String DB) {
        try {
            switch (DB) {
                case "Oracle" -> {
                    sessionFactory = HibernateUtilOracle.getSessionFactory();
                    VistaMensaje.mensajeConsola("Conexión Correcta con Hibernate");
                }
                case "MariaDB" -> {
                    sessionFactory = HibernateUtilMariaDB.getSessionFactory();
                    VistaMensaje.mensajeConsola("Conexión Correcta con Hibernate");
                }
                default ->
                    VistaMensaje.mensajeConsola("Introduce un nombre correcto");
            }
        } catch (ExceptionInInitializerError e) {
            Throwable cause = e.getCause();
            System.out.println("Error en la conexión. Revise el fichero .cfg.xml: " + cause.getMessage());
        }
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
