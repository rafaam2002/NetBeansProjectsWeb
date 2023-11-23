/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica7pcd;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author rafaa
 */
public class Comedero {

    private final Lock mutex = new ReentrantLock();
    private final Condition colaperros = mutex.newCondition();
    private final Condition colagatos = mutex.newCondition();
    private final int CANT_MAX = 4;
    private int nperros = 0;
    private int ngatos = 0;
    private int gatosEsperando = 0;
    private int perrosEsperando = 0;

    public void entraPerro() throws InterruptedException {
        mutex.lock();
        try {
            perrosEsperando++;
            while (nperros + ngatos == CANT_MAX || ngatos == 3 || (nperros == 2 && ngatos == 1)) {
                
                colaperros.await();
            }
            perrosEsperando--;
            nperros++;
        } finally {
            mutex.unlock();
        }

    }

    public void entraGato() throws InterruptedException {
        mutex.lock();
        try { 
            gatosEsperando++;
            while (nperros + ngatos == CANT_MAX || nperros == 3 || (ngatos == 2 && nperros == 1)) {
               
                colagatos.await();
            }
            gatosEsperando--;
            ngatos++;
        } finally {
            mutex.unlock();
        }
    }

    public void salePerro() {
        mutex.lock();

        try {
            nperros--;
            if (perrosEsperando < 0) {
                colaperros.signal();
            } else {
                colagatos.signal();
            }
        } finally {
            mutex.unlock();
        }
    }

    public void saleGato() {
        mutex.lock();
        try {
            ngatos--;
            if (gatosEsperando < 0) {
                colagatos.signal();
            } else {
                colaperros.signal();
            }
        } finally {
            mutex.unlock();

        }
    }
}
