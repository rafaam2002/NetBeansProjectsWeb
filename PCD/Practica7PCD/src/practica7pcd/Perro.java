/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica7pcd;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaa
 */
public class Perro extends Thread {

//    private static int generador = 1;
    private final int id;
    private final Comedero comedero;
    private final Random rdm = new Random(1234);
    private final CanvasComedero cv;

    public Perro(Comedero c, CanvasComedero cv, int id) {
        comedero = c;
        this.cv = cv;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            cv.enColaPerros(id, 'P');
            System.out.println("Perro " + id + " esperando");
            comedero.entraPerro();
            cv.finColaPerros(id, 'P');
            System.out.println("Entra perro " + id);
        } catch (InterruptedException ex) {
            System.out.println("Error al entrar perrro");
        }

        try {
            cv.enColaComiendo(id, 'P');
            sleep(rdm.nextInt(6, 8) * 1000);
        } catch (InterruptedException ex) {
            System.out.println("Error al hacer el sleep");
        }
        comedero.salePerro();
        cv.finColaComiendo(id, 'P');
        System.out.println("Sale perro " + id);

    }

}
