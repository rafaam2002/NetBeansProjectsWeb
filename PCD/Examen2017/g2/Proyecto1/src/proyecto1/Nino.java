/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaa
 */
public class Nino implements Runnable {

    private final int id;
    private final Random random;
    private final Puente pk;

    public Nino(int id, Puente pk) {
        this.id = id;
        random = new Random();
        this.pk = pk;
    }

    @Override
    public void run() {
        int tiempo = random.nextInt(3) + 2;
        boolean esCoche;
        System.out.println("Soy el Nino " + id);
        try {
            pk.entraNino();
            System.out.println("Soy el Nino " + id + " y entro");
            //Deposita la fruta
            sleep(tiempo * 1000);
            pk.saleNino();
            System.out.println("soy el Nino " + id + " y me voy");

        } catch (InterruptedException ex) {
            Logger.getLogger(Nino.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
