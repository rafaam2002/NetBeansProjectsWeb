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
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Random random = new Random();
        Thread[] hilos = new Thread[10];
        Vibradora almacen = new Vibradora();
        ExecutorService thp = Executors.newFixedThreadPool(4);
        ArrayList<Future<Integer>> response = new ArrayList();
//      
        int numInox = 0;
        for (int i = 0; i < 10; i++) {
            if (random.nextInt((100) + 1) <= 50) {
                hilos[numInox] = new Inoxidable(i, almacen);
                hilos[numInox].start();
                numInox++;
            } else {
              response.add(thp.submit(new Hierro(i,almacen)));
            }

            sleep((random.nextInt(3) + 1) * 1000);
        }
        System.out.println("Termino de crear hilos");
        sleep(2000);

        for (int i = 0; numInox < 10; i++) {
            hilos[i].join();
        }
        thp.shutdown();
        
        int tiempoHierros = 0;
        for (Future<Integer> future : response) {
            tiempoHierros += future.get(); 
        }
        

        System.out.println("El programa ha finalizado y la cantidad totl de teimpo de hierros es: " + tiempoHierros);

    }
}
