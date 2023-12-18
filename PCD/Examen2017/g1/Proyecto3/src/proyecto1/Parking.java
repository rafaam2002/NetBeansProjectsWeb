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
public class Parking {

    int ocupadasBus = 0;
    int ocupadasCoche = 0;
    int busesEsperando = 0;
    ReentrantLock mutex = new ReentrantLock();
    Condition colaCoches = mutex.newCondition();
    Condition colaBuses = mutex.newCondition();

    public void entraBus() throws InterruptedException {
        mutex.lock();
        try {
            busesEsperando++;
            while (ocupadasBus >= 2) {
                colaBuses.await();
            }
            busesEsperando--;
            ocupadasBus++;
        } finally {
            mutex.unlock();
        }

    }

    public boolean entraCoche() throws InterruptedException {
        mutex.lock();
        try {
            while ((ocupadasCoche >= 5 && ocupadasBus >= 2) || (ocupadasBus < 5 && busesEsperando > 0)) {
                colaCoches.await();
            }
            if (ocupadasCoche < 5) {
                ocupadasCoche++;
                return true;
            } else {
                entraBus();
                return false;
            }
        } finally {
            mutex.unlock();
        }

    }

    public void saleBus() throws InterruptedException {
        mutex.lock();
        try {
            ocupadasBus--;
            if(busesEsperando > 0){
                colaBuses.signal();
            }else {
                colaCoches.signal();
            }
        } finally {
            mutex.unlock();
        }

    }

    public void saleCoche() {
        mutex.lock();
        try {
            ocupadasCoche--;
            colaCoches.signal();
        } finally {
            mutex.unlock();
        }

    }

}
