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
public class Tarjeta implements Runnable {

    private final Supermercado supermercado;
    private final Random rdm = new Random();

    public Tarjeta(Supermercado supermercado) {
        this.supermercado = supermercado;
    }

    @Override
    public void run() {
         int sleepTime = rdm.nextInt(2000) + 3000;
        try {
            supermercado.pagarTarjeta();
        } catch (InterruptedException ex) {
            Logger.getLogger(Tarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            sleep(sleepTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        supermercado.salir();
    }

}
