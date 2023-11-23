/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica6pcd;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaa
 */
public class Camarero extends Thread {

    private final Semaphore cafe;
    private final Semaphore leche;

    public Camarero(Semaphore cafe, Semaphore leche) {
        this.cafe = cafe;
        this.leche = leche;
    }

    @Override
    public void run() {
        try {
            while (true) {
                rellenar();
                System.out.println("Camarero rellena maquinas");
                sleep(6000);
            }
        } catch (Exception e) {
            System.out.println("Camarero termina");
        }
    }

    private void rellenar() {
        cafe.release(5);
        leche.release(5);
    }

}
