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
public class Efectivo implements Callable<Integer> {

    private final Supermercado supermercado;
    private final Random rdm = new Random();
    private final String name;
    private final SuperCanvas cv;
    private final int id;                   

    public Efectivo(Supermercado supermercado, String name, SuperCanvas cv) {
        this.supermercado = supermercado;
        this.name = name;
        this.cv = cv;
        this.id = Integer.parseInt(name.substring(name.length() - 1, name.length()));
    }

    @Override
    public Integer call() {
        int sleepTime = rdm.nextInt(2000) + 3000;

        try {
            System.out.println("El Hilo " + name + " intenta entrar en caja");
            cv.insertarClienteEfectivo(id);
            supermercado.pagarEfectivo();
        } catch (InterruptedException ex) {
            Logger.getLogger(Efectivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println("El Hilo " + name + " en caja");
            cv.eliminarClienteEfectivo(id);
            cv.insertarClientePagandoEfectivo(id);
            sleep(1000);  //            sleep(); 
        } catch (InterruptedException ex) {
            Logger.getLogger(Tarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        supermercado.salir('E');
        System.out.println("El Hilo " + name + " sale de la caja");
        cv.eliminarClientePagandoEfectivo(id);
        return sleepTime / 1000;
    }

}
