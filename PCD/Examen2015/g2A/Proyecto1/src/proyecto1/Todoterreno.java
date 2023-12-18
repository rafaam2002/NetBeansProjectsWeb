/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaa
 */
public class Todoterreno extends Thread {

    private final int id;
    private final Random random;
    private final Lavadero lv;

    public Todoterreno(int id, Lavadero lv) {
        this.id = id;
        random = new Random();
        this.lv = lv;
    }

    @Override
    public void run() {
        int tiempo = random.nextInt(4) + 2;
        System.out.println("Soy el todoterreno " + id );
        try {
            lv.entraTT(id);
            sleep(tiempo * 1000);
            lv.saleTT(id);
//            almacen.compra(numKilos);
        } catch (InterruptedException ex) {
            Logger.getLogger(Todoterreno.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("soy el todoterreno " + id + " y he  terminado");
    }
}
