/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica7pcd;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.One2OneChannel;

/**
 *
 * @author rafaa
 */
public class Perro extends Thread {

//    private static int generador = 1;
    private final int id;
    private final Random rdm = new Random(1234);
    private final CanvasComedero cv;
    private final Any2OneChannel entraPerro;
    private final Any2OneChannel salePerro;
    private final One2OneChannel permiso;

    public Perro(Any2OneChannel entraPerro, Any2OneChannel salePerro, One2OneChannel permiso, CanvasComedero cv, int id) {
        this.entraPerro = entraPerro;
        this.salePerro = salePerro;
        this.permiso = permiso;

        this.cv = cv;
        this.id = id;
    }

    @Override
    public void run() {
        cv.enColaPerros(id, 'P');
//        System.out.println("Perro " + id + " esperando");
        entraPerro.out().write(id);
        int lee = (int) permiso.in().read();
        cv.finColaPerros(id, 'P');
//        System.out.println("Entra perro " + id + " "+ lee);

        try {
            cv.enColaComiendo(id, 'P');
            sleep(rdm.nextInt(6, 8) * 1000);
        } catch (InterruptedException ex) {
//            System.out.println("Error al hacer el sleep");
        }
        salePerro.out().write(id);
        cv.finColaComiendo(id, 'P');
//        System.out.println("Sale perro " + id);

    }

}
