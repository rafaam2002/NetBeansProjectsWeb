/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Aplicacion;

import Controlador.ControladorLogin;
import Controlador.ControladorPrincipal;
import com.formdev.flatlaf.FlatDarculaLaf;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author rafaa
 */
public class PracticaHibernateDDSI {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception ex) {
            System.err.println(  "Mensaje de error ");
        }
            var cLogin = new ControladorLogin();
//        var cPrincipal = new ControladorPrincipal(cLogin.getSessionFactory());

        }
    }
