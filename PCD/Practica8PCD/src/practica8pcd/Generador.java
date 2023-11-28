/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica8pcd;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Generador {


    public static void main(String[] args) {
        // TODO code application logic here
        var rdm = new Random();
       
        var clientes = new Thread[20];
        var supermercado = new Supermercado();
        for (int i = 0; i < 20; i++) {
            if (rdm.nextInt(100) < 50){
               clientes[i] = new Thread(new Tarjeta(supermercado, "tarjeta-" + i));
            }else{
                clientes[i] = new Thread (new Efectivo(supermercado,"efectivo-" + i));
            }
            clientes[i].start();
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for (int i = 0; i < 20; i++) {
            try {
                clientes[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
