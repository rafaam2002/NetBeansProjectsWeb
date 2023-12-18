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
public class Adulto extends Thread {

    private final int id;
    private final Random random;
    private final Puente pk;

    public Adulto(int id, Puente pk) {
        this.id = id;
        random = new Random();
        this.pk = pk;
    }

    @Override
    public void run() {
        int tiempo = random.nextInt(3) + 3;
        System.out.println("Soy el Adulto " + id);
        try {
            pk.entraAdulto();
            System.out.println("Soy el Adulto " + id + " y entro");
            sleep(tiempo * 1000);
            pk.saleAdulto();
//            almacen.compra(numKilos);
            System.out.println("soy el Adulto " + id + " y salgo");
        } catch (InterruptedException ex) {
            Logger.getLogger(Adulto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
