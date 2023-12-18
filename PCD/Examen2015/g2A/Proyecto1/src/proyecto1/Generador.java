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
        final Lavadero lv = new Lavadero();
        ExecutorService thp = Executors.newFixedThreadPool(2);
        ArrayList<Future<Boolean>> response = new ArrayList();
        int numTT = 0;

        for (int i = 0; i < 10; i++) {
            if (random.nextInt((100) + 1) <= 30) {
                hilos[numTT] = new Todoterreno(i, lv);
                hilos[numTT].start();
                numTT++;
            } else {
                response.add(thp.submit(new Coche(i, lv)));
//                hilos[i] = new Thread(new Coche(i,lv));
            }
            sleep((random.nextInt(3) + 1) * 1000);
        }
        System.out.println("Termino de crear hilos");
        sleep(2000);

        for (int i = 0; i < numTT; i++) {
            hilos[i].join();
        }
        thp.shutdown();
        int lvCoche = 0;
        int lvTT = 0;
        for (Future<Boolean> future : response) {
            if (future.get()) {
                lvCoche++;
            } else {
                lvTT++;
            }
        }
        System.out.println("los coches entraron: " + lvCoche + " veces en lv de coches");
        System.out.println("los coches entraron: " + lvTT + " veces en lv de todoterrenos");
        System.out.println("El programa ha finalizado");

    }
}
