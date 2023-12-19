/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaa
 */
public class Pantalon implements Runnable {

    private final int id;
    private final Random random;
    private final Semaphore semCorte;
    private final Semaphore semCoser;

    public Pantalon(int id, Semaphore sCorte, Semaphore sCoser) {
        this.id = id;
        random = new Random();
        this.semCorte = sCorte;
        this.semCoser = sCoser;
    }

    @Override
    public void run() {
//        int tiempo = random.nextInt(3) + 2;
        System.out.println("Inicia Pantalon " + id);
        try {
            semCorte.acquire();
            sleep(2000);
            System.out.println("Camisa" + id + "Intenta coser");
            semCoser.acquire();
            semCorte.release();
            sleep(2000);
            semCoser.release();
            System.out.println("Pantalon" + id + " termina");
        } catch (InterruptedException ex) {
            Logger.getLogger(Pantalon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
