/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica8pcd;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaa
 */
public class Efectivo implements Runnable{
    private final Supermercado supermercado;

    public Efectivo(Supermercado supermercado) {
        this.supermercado = supermercado;
    }
    
    

    @Override
    public void run() {
        try {
            supermercado.pagarEfectivo();
        } catch (InterruptedException ex) {
            Logger.getLogger(Efectivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
