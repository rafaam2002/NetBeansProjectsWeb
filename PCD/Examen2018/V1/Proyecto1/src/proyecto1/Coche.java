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
public class Coche implements Callable {

    private final int id;
    private final Random random;
    private final Taller pk;

    public Coche(int id, Taller pk) {
        this.id = id;
        random = new Random();
        this.pk = pk;
    }

    @Override
    public Object call() throws Exception {
        int tiempo = random.nextInt(3) + 2;
        System.out.println("Soy el Coche " + id);
        try {
            pk.entraCoche();
            System.out.println("Soy el Coche " + id + " y entro");
            sleep(tiempo * 1000);
            pk.saleCoche();
            System.out.println("soy el Coche " + id + " y me voy");

        } catch (InterruptedException ex) {
            Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiempo;
    }

}
