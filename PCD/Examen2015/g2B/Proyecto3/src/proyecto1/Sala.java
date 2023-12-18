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
public class Sala {

    boolean libre = true;
    int conCitaEsperando = 0;
//    int sinCitaEsperando = 0;
    int vecesEsperandoSin = 0;
    int sinCitaEsperando = 0;
    int veces = 0;
    ReentrantLock mutex = new ReentrantLock();
    Condition colaConCinta = mutex.newCondition();
    Condition colaSinCita = mutex.newCondition();

    public synchronized void concitaIn() throws InterruptedException {
        mutex.lock();
        try {
            conCitaEsperando++;
            while (!libre) {
                colaConCinta.await();
            }
            libre = false;
            conCitaEsperando--;
            if(sinCitaEsperando > 0) veces++; 
        } finally {
            mutex.unlock();
        }

    }

    public synchronized void concitaOut() throws InterruptedException {
        mutex.lock();
        try {
            libre = true;
            if((sinCitaEsperando > 0 && veces >=2) || conCitaEsperando == 0){
                colaSinCita.signal();
            } else {
                colaConCinta.signal();
            }
        } finally {
            mutex.unlock();
        }

    }

    public synchronized void sincitaIn() throws InterruptedException {
        mutex.lock();
        try {
            while (!libre || conCitaEsperando > 0) {
                colaSinCita.await();
            }
            libre = false;
        } finally {
            mutex.unlock();
        }

    }

    public synchronized void sincitaOut() {
        mutex.lock();
        try {
            if (conCitaEsperando > 0) {
                colaConCinta.signal();
            } else {
                colaSinCita.signal();
            }

        } finally {
            mutex.unlock();
        }

    }

}
