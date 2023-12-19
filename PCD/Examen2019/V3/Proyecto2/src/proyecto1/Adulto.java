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
public class Adulto implements Runnable {

    private final int id;
    private final Random random;
    private final Piscina pk;

    public Adulto(int id, Piscina pk) {
        this.id = id;
        random = new Random();
        this.pk = pk;
    }

    @Override
    public void run() {
//        int tiempo = random.nextInt(4) + 3;
//        System.out.println("Inicia Adulto " + id);
//        try {
//            pk.entraAdulto();
//            sleep(tiempo * 1000);
//            pk.saleAdulto();
//            System.out.println("Adulto " + id + " finaliza");
//
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Adulto.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
