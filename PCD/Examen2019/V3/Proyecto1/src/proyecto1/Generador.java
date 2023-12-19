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
import org.jcsp.lang.*;
import org.jcsp.util.Buffer;

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

        Any2OneChannel entraNinio = Channel.any2one(new Buffer(20));
        Any2OneChannel entraAdulto = Channel.any2one(new Buffer(20));
        Any2OneChannel saleNinio = Channel.any2one(new Buffer(20));
        Any2OneChannel saleAdulto = Channel.any2one(new Buffer(20));
        One2OneChannel[] permiso = Channel.one2oneArray(10, new Buffer(20));

        Piscina piscina = new Piscina(entraNinio,entraAdulto,saleNinio,saleAdulto,permiso);
        piscina.start();

        for (int i = 0; i < 10; i++) {
            if (random.nextInt((100) + 1) <= 40) {
                hilos[i] = new Ninio(i, entraNinio,saleNinio,permiso[i]);
            } else {
                hilos[i] = new Thread(new Adulto(i,entraAdulto,saleAdulto,permiso[i]));
            }
            hilos[i].start();

            sleep((random.nextInt(2) + 1) * 1000);
        }
        System.out.println("Termino de crear hilos");
        sleep(2000);

        for (int i = 0; i < 10; i++) {
            hilos[i].join();
        }
        piscina.interrupt();

        System.out.println("Terminan todos los camiones");

        System.out.println("El programa ha finalizado y la cantidad totl de teimpo de coches es: ");

    }
}
