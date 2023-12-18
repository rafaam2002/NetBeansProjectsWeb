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
public class Pequenyia extends Thread {

    private final int id;
    private final Random random;
    private final Semaphore pk;

    public Pequenyia(int id, Semaphore pk) {
        this.id = id;
        random = new Random();
        this.pk = pk;
    }

    @Override
    public void run() {
        int tiempo = random.nextInt(3) + 1;
        System.out.println("Inicia Pequenia " + id);
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("Pequnia " + id + " carga");
                pk.acquire();
                sleep(tiempo * 1000);
            }
            System.out.println("Pequenia " + id + " termina");
        } catch (InterruptedException ex) {
            Logger.getLogger(Pequenyia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
