/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author rafaa
 */
public class Generador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        Thread[] hilos = new Thread[40];
        Almacen almacen = new Almacen();

        for (int i = 0; i < 40; i++) {
            if (random.nextInt((100) + 1) <= 50) {
                hilos[i] = new Cliente(i,almacen);
            } else {
                hilos[i] = new Thread(new Proveedor(i,almacen));
            }
            hilos[i].start();
            sleep((random.nextInt(2) ) * 1000);
        }
        System.out.println("Termino de crear hilos");
        sleep(2000);
        
        for (int i = 0; i < 20; i++) {
            hilos[i].interrupt();
        }
        System.out.println("El programa ha finalizado");
        
    }
}
