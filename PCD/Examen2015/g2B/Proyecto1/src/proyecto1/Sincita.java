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
public class Sincita implements Runnable {

    private final int id;
    private final Random random;
    private final Sala almacen;

    public Sincita(int id, Sala almacen) {
        this.id = id;
        random = new Random();
        this.almacen = almacen;
    }

    @Override
    public void run() {
        int tiempo = random.nextInt(3) + 2;
        System.out.println("Soy el Sincita " + id );
        try {
            //Deposita la fruta
            sleep(tiempo * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sincita.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("soy el Sincita " + id + " y he terminado");
    }

}
