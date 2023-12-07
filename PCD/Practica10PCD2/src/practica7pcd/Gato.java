/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica7pcd;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.One2OneChannel;

/**
 *
 * @author rafaa
 */
public class Gato implements Runnable {

//    private static int generador = 1;
    private final int id;
    private final Random rdm = new Random(123456);
    private final CanvasComedero cv;
    private final Any2OneChannel entraGato;
    private final Any2OneChannel saleGato;
    private final One2OneChannel permiso;

    public Gato(Any2OneChannel entraGato, Any2OneChannel saleGato, One2OneChannel permiso, CanvasComedero cv, int id) {
        this.entraGato = entraGato;
        this.saleGato = saleGato;
        this.permiso = permiso;
        this.cv = cv;
        this.id = id;
    }

    @Override
    public void run() {

        cv.enColaGatos(id, 'G');
        System.out.println("Gato " + id + " esperando");
        entraGato.out().write(id);
        int lee = (int) permiso.in().read();
        cv.finColaGatos(id, 'G');
        System.out.println("Entra gato " + id);
        try {
            cv.enColaComiendo(id, 'G');
            sleep(rdm.nextInt(6, 8) * 1000);
        } catch (InterruptedException ex) {
            System.out.println("Eror al hacer sleep");
        }
        saleGato.out().write(id);
        cv.finColaComiendo(id, 'G');
        System.out.println("Sale gato " + id);
    }

}
