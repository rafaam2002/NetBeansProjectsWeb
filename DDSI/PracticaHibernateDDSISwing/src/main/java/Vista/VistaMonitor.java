/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Modelo.Monitor;

/**
 *
 * @author rafaa
 */
public class VistaMonitor {
    
    public static void muestraMonitor(Monitor m){
        System.out.println(m.getCodMonitor() + "\t"+ m.getNombre() + "\t" + m.getCorreo() + "\t" + m.getDni()+
                "\t" + m.getFechaEntrada() + "\t" + m.getNick() + "\t" + m.getActividadesResponsable()
                + "\t"  +m.getTelefono());
    }
}
