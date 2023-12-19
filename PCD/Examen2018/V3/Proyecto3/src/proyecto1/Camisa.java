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
public class Camisa extends Thread {

    private final int id;
    private final Random random;
    private final Semaphore semCorte;
    private final Semaphore semCoser;

    public Camisa(int id, Semaphore sCorte, Semaphore sCoser) {
        this.id = id;
        random = new Random();
        this.semCorte = sCorte;
        this.semCoser = sCoser;
    }

    @Override
    public void run() {
        int tiempo = random.nextInt(3) + 1;
        System.out.println("Inicia Camisa " + id);
        try {
            semCorte.acquire();
            sleep(2000);
            System.out.println("Camisa" + id + "Intenta coser");
            semCoser.acquire();
            semCorte.release();
            sleep(2000);
            semCoser.release();

            System.out.println("Camisa" + id + " sale Corte");
        } catch (InterruptedException ex) {
            Logger.getLogger(Camisa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
