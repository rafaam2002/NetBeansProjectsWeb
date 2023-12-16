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
public class Cliente extends Thread {

    private final int id;
    private final Random random;
    private final Almacen almacen;

    public Cliente(int id, Almacen almacen) {
        this.id = id;
        random = new Random();
        this.almacen = almacen;
    }

    @Override
    public void run() {
        int numKilos = random.nextInt(3) + 3;
        System.out.println("Soy el cli " + id + " y voy a comprar " + numKilos + " fruta");
        try {
            sleep(2000);
            if (almacen.compra(numKilos)) {
//                System.out.println("soy el cli " + id + " y he conseguido comprar");
            } else {
                System.out.println("soy el cli " + id + " y he agotado mis intentos");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
