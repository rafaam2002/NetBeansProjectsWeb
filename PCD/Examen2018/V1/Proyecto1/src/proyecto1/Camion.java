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
public class Camion extends Thread {

    private final int id;
    private final Random random;
    private final Taller pk;

    public Camion(int id, Taller pk) {
        this.id = id;
        random = new Random();
        this.pk = pk;
    }

    @Override
    public void run() {
        int tiempo = random.nextInt(3) + 3;
        System.out.println("Soy el Camion " + id);
        try {
            pk.entraCamion();
            System.out.println("Soy el Camion " + id + " y entro");
            sleep(tiempo * 1000);
            pk.saleCamion();
            System.out.println("soy el Camion " + id + " y salgo");
        } catch (InterruptedException ex) {
            Logger.getLogger(Camion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
