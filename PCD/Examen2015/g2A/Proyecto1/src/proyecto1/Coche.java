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
    private final Lavadero lv;

    public Coche(int id, Lavadero lv) {
        this.id = id;
        random = new Random();
        this.lv = lv;
    }

    public void run() {

    }

    @Override
    public Object call() throws Exception {
        int tiempo = random.nextInt(4) + 2;
        boolean esCoche = true;
        System.out.println("Soy el coche " + id);
        try {
            esCoche = lv.entraCoche(id);
            if (!esCoche) {
                System.out.println("En entrado como TT");
            } else {
                System.out.println("Entrando como Coche");
            }
            //Deposita la fruta
            sleep(tiempo * 1000);
            if (esCoche) {
                lv.saleCoche(id);
            } else {
                lv.saleTT(id);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("soy el coche " + id + " y he terminado");
        return esCoche;
    }

}
