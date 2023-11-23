/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplohilos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaa
 */
public class EjemploHilos {

    public static void main(String[] args) {
        Sumador s = new Sumador(0);
        for (int i = 0; i < 10; i++) {
            s.incrementa();
        }
        HiloThread h1 = new HiloThread(s);//extends
        HiloThread h2 = new HiloThread(s);

        HiloRunnable r1 = new HiloRunnable(s);//implements
        Thread h3 = new Thread(r1);
        //Thread h4 = new Thread(r1); //reutilizo los atributos de r1
        Thread h4 = new Thread(new HiloRunnable(s));

        h1.start();//inicia la ejecucion de forma concurrente
        h2.start();
        h3.start();
        h4.start();
        System.out.println("Nombre del hilo main: " + Thread.currentThread().getName()); //curentThread devuelve el hilo que lo esta ejecutando
        System.out.println("Nombre: " + h1.getName() + "id: " + h1.getId() 
                + "prioridad:" + h1.getPriority() + "estado: " + h1.getState());

        try {
            h1.join();//para que la main espere a que terminen
        } catch (InterruptedException ex) {
            Logger.getLogger(EjemploHilos.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            h2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(EjemploHilos.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            h3.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(EjemploHilos.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            h4.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(EjemploHilos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(s.getContador());
    }
}
