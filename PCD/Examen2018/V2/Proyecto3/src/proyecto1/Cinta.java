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
public class Cinta extends Thread {

    private final int id;
    private final Random random;
    private final Semaphore pk;

    public Cinta(int id, Semaphore pk) {
        this.id = id;
        random = new Random();
        this.pk = pk;
    }

    @Override
    public void run() {
        int tiempo;
        int cant;
        System.out.println("Soy La Cinta " + id);
        try {
            while (true) {
                tiempo = random.nextInt(5) + 1;
                cant = random.nextInt(3) + 2;
                System.out.println("Cinta Rellena: " + cant);
                for (int i = 0; i < cant; i++) {
                    pk.release();
                }
                sleep(tiempo * 1000);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Cinta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
