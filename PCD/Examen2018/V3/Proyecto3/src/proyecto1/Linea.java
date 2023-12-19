/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author rafaa
 */
public class Linea {

    ReentrantLock mutex = new ReentrantLock();
    Condition colaCorte = mutex.newCondition();
    Condition colaCamisas = mutex.newCondition();
    Condition colaPantalones = mutex.newCondition();
    int libreCorte = 2;
    boolean libreCoser = true;
    int pantalonesEsperando = 0;

    public void entraCorte() throws InterruptedException {
        mutex.lock();
        try {
            while (libreCorte == 0) {
                colaCorte.await();
            }
            libreCorte--;
        } finally {
            mutex.unlock();
        }

    }

    public void coserCamisa() throws InterruptedException {
        mutex.lock();
        try {
            while (!libreCoser || pantalonesEsperando > 0) {
                colaCamisas.await();
            }
            libreCorte++;
            libreCoser = false;
            colaCorte.signal();
        } finally {
            mutex.unlock();
        }
    }

    public void coserPantalon() throws InterruptedException {
        mutex.lock();
        try {
            pantalonesEsperando++;
            while (!libreCoser) {
                colaPantalones.await();
            }
            pantalonesEsperando--;
            libreCorte++;
            libreCoser = false;
            colaCorte.signal();
        } finally {
            mutex.unlock();
        }

    }

    public void saleCoser() {
        mutex.lock();
        try {
            libreCoser = true;
            if(pantalonesEsperando > 0){
                colaPantalones.signal();
            } else {
                colaCamisas.signal();
            }
        } finally {
            mutex.unlock();
        }

    }

}
