/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica4;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Consumidor implements Runnable {

    private Pila lapila;

    public Consumidor(Pila p) {
        lapila = p;
    }

    @Override
    public void run() {
        Random r = new Random(System.nanoTime());
        for (int i = 0; i < 15; i++) {
            int ale = 1 + r.nextInt(3);
            try {
                System.out.println("Soy el Consumidor " + Thread.currentThread().getName() + " y espero " + ale + " segundos.");
                sleep(ale * 1000);
                
                Object aux = lapila.Desapila();
                System.out.println("Soy el Consumidor " + Thread.currentThread().getName() + " y elemento desapilado " + aux + " correctamente.");
                
               
            } catch (Exception ex) {
                System.out.println("soy el Consumidor: " + Thread.currentThread().getName() + " " + ex.getMessage());
                break;
            }
        }
    }
}
