/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Config.HibernateUtilMariaDB;
import Config.HibernateUtilOracle;
import Vista.VistaMensaje;
import Vista.vistaLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.hibernate.SessionFactory;

/**
 *
 * @author rafaa
 */
public class ControladorLogin implements ActionListener {

    private SessionFactory sessionFactory;
    private final vistaLogin vLogin;

    public ControladorLogin() {
        vLogin = new vistaLogin();
        addListeners();

        vLogin.setLocationRelativeTo(null);
        vLogin.setVisible(true);

        vLogin.SelectDB.setSelectedIndex(0);

    }

    private void conectarBD(String DB) throws ExceptionInInitializerError {
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
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //Probar si cuando se produce un error en la conexion sigue funiconando
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "jButtonConectar" -> {
                try {
                    var eleccionDb = (String) vLogin.SelectDB.getSelectedItem();
                    conectarBD(eleccionDb);
                    vLogin.dispose();
                    new ControladorPrincipal(sessionFactory);
                } catch (ExceptionInInitializerError ex) {
                    Throwable cause = ex.getCause();
                    System.out.println("Error en la conexión. Revise el fichero .cfg.xml: " + cause.getMessage());
                }

            }
            case "jButtonSalir" -> {
                vLogin.dispose();
                System.exit(0);
            }
            default ->
                throw new AssertionError();
        }
    }

    private void addListeners() {
        vLogin.jButton1Conectar.addActionListener(this);
        vLogin.jButton2SalirDialogoConexion.addActionListener(this);
    }
}
