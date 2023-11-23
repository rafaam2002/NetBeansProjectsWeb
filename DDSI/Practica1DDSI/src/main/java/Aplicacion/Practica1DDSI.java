package Aplicacion;
import Controlador.*;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author rafaa
 */


public class Practica1DDSI {

    public static void main(String[] args) {
        ControladorLogin controladorLogin = new ControladorLogin();
        ControladorPrincipal controladorPrincipal = new ControladorPrincipal(controladorLogin.getConexion());
        
    }
}
