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
public class Hierro implements Callable {

    private final int id;
    private final Random random;
    private final Vibradora pk;

    public Hierro(int id, Vibradora pk) {
        this.id = id;
        random = new Random();
        this.pk = pk;
    }

   

    @Override
    public Object call() throws Exception {
        int tiempo = random.nextInt(4) + 2;
        System.out.println("Inicia Hierro " + id + "idhilo: " + Thread.currentThread().getId());
        try {

            pk.entraHierro();
            sleep(tiempo * 1000);
            pk.saleHierro();
            System.out.println("Hierro " + id + " termina");
        } catch (InterruptedException ex) {
            Logger.getLogger(Hierro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiempo;
    }

}
