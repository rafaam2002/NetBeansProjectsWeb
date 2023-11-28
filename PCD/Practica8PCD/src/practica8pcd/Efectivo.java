/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica8pcd;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaa
 */
public class Efectivo implements Runnable {

    private final Supermercado supermercado;
    private final Random rdm = new Random();
    private final String name;

    public Efectivo(Supermercado supermercado, String name) {
        this.supermercado = supermercado;
        this.name = name;
    }

    @Override
    public void run() {
        int sleepTime = rdm.nextInt(2000) + 3000;

        try {
            System.out.println("El Hilo " + name + " intenta entrar en caja");
            supermercado.pagarEfectivo();
        } catch (InterruptedException ex) {
            Logger.getLogger(Efectivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println("El Hilo " + name + " en caja");
            sleep(sleepTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        supermercado.salir();
        System.out.println("El Hilo " + name + " sale de la caja");
    }

}
