/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica4;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Productor extends Thread {

    private Pila lapila;

    public Productor(Pila p) {
        lapila = p;
    }

    @Override
    public void run() {

        Random r = new Random(System.nanoTime());
        
        for (int i = 0; i < 15; i++) {
            int ale = 1 + r.nextInt(3);
            int x = 1+r.nextInt(100);
            try {
                 System.out.println("Soy el Productor " + Thread.currentThread().getName() + " y espero " + ale + " segundos.");
                 sleep(ale * 1000);
                  lapila.Apila(x);
             
                 
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                break;
                
            }
            
            
        }
    }

}
