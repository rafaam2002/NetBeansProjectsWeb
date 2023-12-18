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
public class Grande implements Runnable {

    private final int id;
    private final Random random;
    private final Monton pk;

    public Grande(int id, Monton pk) {
        this.id = id;
        random = new Random();
        this.pk = pk;
    }

    @Override
    public void run() {
        int tiempo = random.nextInt(3) + 2;
        System.out.println("Inicia Grande " + id);
        try {
            for (int i = 0; i < 7; i++) {
                System.out.println("Grande " + id + " carga");
                pk.cargaMucho();
                sleep(tiempo * 1000);
            }
            System.out.println("Grande " + id + " termina");
        } catch (InterruptedException ex) {
            Logger.getLogger(Grande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
