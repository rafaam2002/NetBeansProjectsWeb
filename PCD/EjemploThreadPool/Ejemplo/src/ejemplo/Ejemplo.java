/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class Ejemplo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //ExecutorService thp = Executors.newCachedThreadPool();

        ExecutorService thp = Executors.newFixedThreadPool(2);
        //ExecutorService thp = Executors.newScheduledThreadPool(corePoolSize);
                
        for (int i = 0; i < 5; i++) {
         sinretorno r = new sinretorno((i + 1) * 1000);
         thp.submit(r);
         }

//        ArrayList<Future<Integer>> resultados = new ArrayList();
//        for (int i = 0; i < 5; i++) {
//            conretorno r = new conretorno((i + 1) * 100);
//            System.out.println("Lanzando tarea " + (i + 1) * 100);
//            Future<Integer> f = thp.submit(r);
//            resultados.add(f);
//        }

        thp.shutdown();
        //thp.shutdownNow();
        
//        for (int i = 0; i < resultados.size(); i++) {
//            try {
//                Future f = resultados.get(i);
//                System.out.println("Resultados " + f.get());
//            } catch (InterruptedException | ExecutionException ex) {
//                Logger.getLogger(Ejemplo.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
    }
}

class sinretorno implements Runnable {
    
    int id;
    
    public sinretorno(int id) {
        this.id = id;
    }

    //Se muestra 3 veces con una espera de 1 s en cada una
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                System.out.println("Hilo " + Thread.currentThread().getId() + " ejecuta tarea " + id);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(sinretorno.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


class conretorno implements Callable<Integer> {

    int id;

    public conretorno(int id) {
        this.id = id;
    }

        //Se muestra 3 veces con una espera de 1 s en cada una y devuelve el id + 1000
    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < 3; i++) {
            System.out.println("Hilo " + Thread.currentThread().getId() + " ejecuta tarea " + id);
            Thread.sleep(1000);
        }
        return id + 1000;
    }
}