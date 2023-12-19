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
public class Vibradora {

    ReentrantLock mutex = new ReentrantLock();
    Condition colaInox = mutex.newCondition();
    Condition colaHierro = mutex.newCondition();
//    Condition colaPantalones = mutex.newCondition();
    int hierrosDentro = 0;
    int inoxDentro = 0;
    int hierrosEsperando = 0;
    int inoxEsperando = 0;

    public void entraInox() throws InterruptedException {
        mutex.lock();
        try {
            inoxEsperando++;
            while (hierrosDentro > 0 || inoxDentro >= 3) {
                colaInox.await();
            }
            inoxEsperando--;
            inoxDentro++;
        } finally {
            mutex.unlock();
        }

    }

    public void entraHierro() throws InterruptedException {
        mutex.lock();
        try {
            hierrosEsperando++;
            while (inoxDentro > 0 || hierrosDentro >= 2) {
                colaHierro.await();
            }
            hierrosEsperando--;
            hierrosDentro++;
        } finally {
            mutex.unlock();
        }

    }

    public void saleInox() throws InterruptedException {
        mutex.lock();
        try {
            inoxDentro--;
            if (inoxEsperando > 0) {
                colaInox.signal();
            } else {
                colaHierro.signal();
            }
        } finally {
            mutex.unlock();
        }

    }

    public void saleHierro() {
        mutex.lock();
        try {
            hierrosDentro--;
           if(hierrosEsperando > 0 ){
               colaHierro.signal();
           } else {
               colaInox.signal();
           }
        } finally {
            mutex.unlock();
        }
    }

}
