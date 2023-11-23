/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Aplicacion;

import Controlador.ControladorLogin;
import Controlador.ControladorPrincipal;

/**
 *
 * @author rafaa
 */
public class PracticaHibernateDDSI {

    public static void main(String[] args) {
        var cLogin = new ControladorLogin("Oracle");
        var cPrincipal = new ControladorPrincipal(cLogin.getSessionFactory());
        
    }
}
