/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaa
 */
public class Concita extends Thread {

    private final int id;
    private final Random random;
    private final Sala almacen;

    public Concita(int id, Sala almacen) {
        this.id = id;
        random = new Random();
        this.almacen = almacen;
    }

    @Override
    public void run() {
        int tiempo = random.nextInt(3) + 2;
        System.out.println("Soy el Concita " + id );
        try {
            sleep(tiempo * 1000);
//            almacen.compra(numKilos);
        } catch (InterruptedException ex) {
            Logger.getLogger(Concita.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("soy el Concita " + id + " y he  terminado");
    }
}
