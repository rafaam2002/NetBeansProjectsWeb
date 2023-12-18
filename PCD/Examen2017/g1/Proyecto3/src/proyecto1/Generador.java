/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

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
        Parking almacen = new Parking();
        ExecutorService thp = Executors.newFixedThreadPool(3);
        ArrayList<Future<Integer>> response = new ArrayList();

        int numBuses = 0;
        for (int i = 0; i < 10; i++) {
            if (random.nextInt((100) + 1) <= 30) {
                hilos[numBuses] = new Autobus(i, almacen);
                hilos[numBuses].start();
                numBuses++;
            } else {
                response.add(thp.submit(new Coche(i, almacen)));
            }
            sleep((random.nextInt(2) + 1) * 1000);
        }
        System.out.println("Termino de crear hilos");
        sleep(2000);

        thp.shutdown();
        for (int i = 0; i < numBuses; i++) {
            hilos[i].join();
        }

        int tiempoCoches = 0;
        for (Future<Integer> future : response) {
            tiempoCoches += future.get();
        }
        System.out.println("El programa ha finalizado y los coches han tardado: " + tiempoCoches);

    }
}
