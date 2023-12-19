/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.registry.*;

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
        Piscina piscina;
        try {
            piscina = new Piscina();

            Registry registro = LocateRegistry.createRegistry(2015);
            registro.rebind("piscinaremoto", piscina);
            System.out.println("Server esta Funcionando");

            System.in.read();

            System.out.println("El programa ha finalizado y la cantidad totl de teimpo de coches es: ");
            System.exit(0);
//            for (int i = 0; i < 10; i++) {
//                if (random.nextInt((100) + 1) <= 40) {
//                    hilos[i] = new Ninio(i, almacen);
//                } else {
//                    hilos[i] = new Thread(new Adulto(i, almacen));
//                }
//                hilos[i].start();
//
//                sleep((random.nextInt(2) + 1) * 1000);
//            }
//            System.out.println("Termino de crear hilos");
//            sleep(2000);
//
//            for (int i = 0; i < 10; i++) {
//                hilos[i].join();
//            }
//
        } catch (RemoteException ex) {
            Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
