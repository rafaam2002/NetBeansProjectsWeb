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
public class Inoxidable extends Thread {

    private final int id;
    private final Random random;
    private final Vibradora pk;

    public Inoxidable(int id, Vibradora pk) {
        this.id = id;
        random = new Random();
        this.pk = pk;
    }

    @Override
    public void run() {
        int tiempo = random.nextInt(3) + 3;
        System.out.println("Inox " + id + " inicia");
        try {
            pk.entraInox();
            sleep(tiempo * 1000);
            pk.saleInox();
            System.out.println("inox" + id + " Sale");
        } catch (InterruptedException ex) {
            Logger.getLogger(Inoxidable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
