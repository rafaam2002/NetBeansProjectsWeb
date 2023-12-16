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
public class Proveedor implements Runnable {

    private final int id;
    private final Random random;
    private final Almacen almacen;

    public Proveedor(int id, Almacen almacen) {
        this.id = id;
        random = new Random();
        this.almacen = almacen;
    }

    @Override
    public void run() {
        int numKilos = random.nextInt(3) + 3;
        System.out.println("Soy el pro " + id + " e intento depositar " + numKilos + " fruta");
        try {
            sleep(2000);
            //Deposita la fruta
            almacen.deposita(0);
        } catch (InterruptedException ex) {
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.out.println("soy el pro " + id + " y he conseguido depositar");
    }

}
