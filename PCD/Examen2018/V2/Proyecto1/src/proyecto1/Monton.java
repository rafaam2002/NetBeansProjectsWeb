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
public class Monton {

    int tm = 0;
    int grandeEsperando = 0;
    int chicasEsperando = 0;
    ReentrantLock mutex = new ReentrantLock();
    Condition colaChicas = mutex.newCondition();
    Condition colaGrandes = mutex.newCondition();

    public void cargaMucho() throws InterruptedException {
        mutex.lock();
        try {
            grandeEsperando++;
            while (tm < 2) {
                colaGrandes.await();
            }
            grandeEsperando--;
            System.out.println("cant: " + tm);
            tm -= 2;
            colaChicas.signal();
        } finally {
            mutex.unlock();
        }

    }

    public void cargaPoco() throws InterruptedException {
        mutex.lock();
        try {
            chicasEsperando++;
            while (tm < 1 || (tm >= 2 && grandeEsperando > 1)) {
                colaChicas.await();
            }
            System.out.println("cant: " + tm);
            tm -= 1;
            chicasEsperando--;
        } finally {
            mutex.unlock();
        }
    }

    public void rellena(int cant) throws InterruptedException {
        mutex.lock();
        try {
            tm += cant;
            int cantSupuesta = tm;
            while(cantSupuesta > 1 && grandeEsperando > 0){
                colaGrandes.signal();
                cantSupuesta-=2;
            }
            while(cantSupuesta > 1 && chicasEsperando > 0){
                colaChicas.signal();
                cantSupuesta--;
            }
            
        } finally {
            mutex.unlock();
        }

    }

}
