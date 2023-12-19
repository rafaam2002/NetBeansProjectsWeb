/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.rmi.RemoteException;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaa
 */
public class Ninio extends Thread {

    private final int id;
    private final Random random;
    private final Piscina pk;

    public Ninio(int id, Piscina pk) {
        this.id = id;
        random = new Random();
        this.pk = pk;
    }

    @Override
    public void run() {
//        int tiempo = random.nextInt(3) + 2;
//        System.out.println("Inicia Ninio " + id);
//        try {
////            if (pk.entraNinio()) {
//                pk.entraNinio();
//                sleep(tiempo * 1000);
//                pk.saleNinio();
//                System.out.println("Ninio " + id + " finaliza");
////            } else {
//                System.out.println("Ninio " + id + " se va ya que no habia ningun adulto en la piscina");
////            }
//
//            System.out.println("Ninio " + id + " termina");
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Ninio.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (RemoteException ex) {
//            Logger.getLogger(Ninio.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
