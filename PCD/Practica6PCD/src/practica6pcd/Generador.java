/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica6pcd;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaa
 */
public class Generador {

    public static void main(String[] args) {
//        Random rdm = new Random();
//        Semaphore salaCafe = new Semaphore(3);
//        Semaphore salaLeche = new Semaphore(3);
//        Semaphore cafe = new Semaphore(10);
//        Semaphore leche = new Semaphore(10);
//        Semaphore papelera = new Semaphore(1);
//
//        Camarero camarero = new Camarero(cafe, leche);
//        Thread[] asistentes = new Thread[30];
//        
//        camarero.start();
//        //creacion de hilos
//        for (int i = 0; i < 30; i++) {
//            if (rdm.nextInt(100) < 50) {
//                asistentes[i] = new Cortado(salaCafe, salaLeche, cafe, leche, papelera, "Cortado-" + i);
//            } else {
//                asistentes[i] = new Thread(new Manchado(salaCafe, salaLeche, cafe, leche, papelera, "Manchado-" + i));
//            }
//            asistentes[i].start();
//            try {
//                sleep(500);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        //espera de hilos
//        for (int i = 0; i < 30; i++) {
//            try {
//                asistentes[i].join();
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        //finalizo camarero
//        System.out.println("Mato a camarero");
//        camarero.interrupt(); // le lanza una interrupcion de tipo interrupt a camarero, ahi hacemos un brake para salir del bucle
//        try {
//            camarero.join();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }
}
