/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        Thread[] hilos = new Thread[10];
        Taller almacen = new Taller();
        ExecutorService thp = Executors.newFixedThreadPool(3);
        ArrayList<Future<Integer>> response = new ArrayList();

        int nCamiones = 0;
        for (int i = 0; i < 10; i++) {
            if (random.nextInt((100) + 1) <= 50) {
                hilos[nCamiones] = new Camion(i, almacen);
                hilos[nCamiones].start();
                nCamiones++;
            } else {
                thp.submit(new Coche(i,almacen));
            }
            sleep((random.nextInt(3) + 1) * 1000);
        }
        System.out.println("Termino de crear hilos");
        sleep(2000);

        for (int i = 0; i < nCamiones; i++) {
            hilos[i].join();
        }
        System.out.println("Terminan todos los camiones");
        thp.shutdown();
        
        int cantTotal = 0;
        for (Future<Integer> future : response) {
            try {
                cantTotal += future.get();
            } catch (ExecutionException ex) {
                Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("El programa ha finalizado y la cantidad totl de teimpo de coches es: " + cantTotal);

    }
}
