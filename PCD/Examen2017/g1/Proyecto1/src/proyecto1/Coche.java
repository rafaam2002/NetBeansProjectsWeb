/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaa
 */
public class Coche implements Runnable {

    private final int id;
    private final Random random;
    private final Parking pk;

    public Coche(int id, Parking pk) {
        this.id = id;
        random = new Random();
        this.pk = pk;
    }

    @Override
    public void run() {
        int tiempo = random.nextInt(3) + 2;
        boolean esCoche;
        System.out.println("Soy el coche " + id);
        try {
            esCoche = pk.entraCoche();
            System.out.println("Soy el coche " + id + " y aparco");
            //Deposita la fruta
            sleep(tiempo * 1000);
            if (esCoche) {
                pk.saleCoche();
            } else {
                System.out.println("soy el coche " + id + " y entro en bus");
                pk.saleBus();
            }
            System.out.println("soy el coche " + id + " y me voy");

        } catch (InterruptedException ex) {
            Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
