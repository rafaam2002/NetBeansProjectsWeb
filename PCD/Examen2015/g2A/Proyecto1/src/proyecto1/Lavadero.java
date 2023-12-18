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
public class Lavadero {

    boolean libreCoche = true;
    boolean libreTT = true;
    int ttEsperando = 0;

    ReentrantLock mutex = new ReentrantLock();
    Condition colaCoches = mutex.newCondition();
    Condition colaTT = mutex.newCondition();

    public boolean entraCoche(int id) throws InterruptedException {
        mutex.lock();
        try {
            while (!libreCoche && !libreTT && ttEsperando > 0) {
                colaCoches.await();
            }
            if (libreCoche) {
                libreCoche = false;
                return true;
            } else {
                entraTT(id);
                return false;
            }
        } finally {
            mutex.unlock();
        }
    }

    public void entraTT(int id) throws InterruptedException {
        mutex.lock();
        try {
            ttEsperando++;
            while (!libreTT) {
                colaTT.await();
            }
            ttEsperando--;
            libreTT = false;
        } finally {
            mutex.unlock();
        }

    }

    public void saleCoche(int id) {
        mutex.lock();
        try {
            libreCoche = true;
            colaCoches.signal();
        } finally {
            mutex.unlock();
        }

    }

    public void saleTT(int id) throws InterruptedException {
        mutex.lock();
        try {
            libreTT = true;
            //si es un tt
            if (ttEsperando == 0) {
                colaCoches.signal();
            } else {
                colaTT.signal();
            }
        } finally {
            mutex.unlock();
        }

    }

}
