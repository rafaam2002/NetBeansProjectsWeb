/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica8pcd;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaa
 */
public class Tarjeta implements Callable<Integer> {

    private final Supermercado supermercado;
    private final Random rdm = new Random();
    private final String name;
    private final SuperCanvas cv;
    private final int id;

    public Tarjeta(Supermercado supermercado, String name, SuperCanvas cv) {
        this.supermercado = supermercado;
        this.name = name;
        this.cv = cv;
        this.id = Integer.parseInt(name.substring(name.length() - 1, name.length()));
    }

    @Override
    public Integer call() {
        int sleepTime = rdm.nextInt(2000) + 3000;
        try {
            System.out.println("El Hilo " + name + " intenta entrar en la caja");
            cv.insertarClienteTarjeta(id);
            supermercado.pagarTarjeta();
        } catch (InterruptedException ex) {
            Logger.getLogger(Tarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println("El Hilo " + name + " en caja");
            cv.eliminarClienteTarjeta(id);
            cv.insertarClientePagandoTarjeta(id);
            sleep(sleepTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        supermercado.salir('T');
        System.out.println("El Hilo " + name + " sale de la caja");
        cv.eliminarClientePagandoTarjeta(id);
        return sleepTime / 1000;
    }

}
