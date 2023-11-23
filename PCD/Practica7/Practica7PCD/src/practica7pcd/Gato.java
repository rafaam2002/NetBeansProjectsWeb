/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica7pcd;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaa
 */
public class Gato implements Runnable {

//    private static int generador = 1;
    private final int id;
    private final Comedero comedero;
    private final Random rdm = new Random(123456);
    private final CanvasComedero cv;

    public Gato(Comedero c, CanvasComedero cv, int id) {
        comedero = c;
        this.cv = cv;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            cv.enColaGatos(id, 'G');
            System.out.println("Gato " + id + " esperando");
            comedero.entraGato();
            cv.finColaGatos(id, 'G');
            System.out.println("Entra gato " + id);
        } catch (InterruptedException ex) {
            System.out.println("Error al entrar el gato");
        }
        try {
            cv.enColaComiendo(id, 'G');
            sleep(rdm.nextInt(6, 8) * 1000);
        } catch (InterruptedException ex) {
            System.out.println("Eror al hacer sleep");
        }
        comedero.saleGato();
        cv.finColaComiendo(id, 'G');
        System.out.println("Sale gato " + id);
    }

}
