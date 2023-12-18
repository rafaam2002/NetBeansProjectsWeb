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
public class Autobus extends Thread {

    private final int id;
    private final Random random;
    private final Parking pk;

    public Autobus(int id, Parking pk) {
        this.id = id;
        random = new Random();
        this.pk = pk;
    }

    @Override
    public void run() {
        int tiempo = random.nextInt(3) + 3;
        System.out.println("Soy el Bus " + id);
        try {
            pk.entraBus();
            System.out.println("Soy el Bus " + id + " y aparco");
            sleep(tiempo * 1000);
            pk.saleBus();
//            almacen.compra(numKilos);
            System.out.println("soy el Bus " + id + " y me voy");
        } catch (InterruptedException ex) {
            Logger.getLogger(Autobus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
