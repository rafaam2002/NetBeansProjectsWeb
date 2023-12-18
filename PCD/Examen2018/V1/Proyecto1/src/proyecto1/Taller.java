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
public class Taller {

    int operariosLibres = 4;
    int camionesEsperando = 0;
    int adultosDentro = 0;
    ReentrantLock mutex = new ReentrantLock();
    Condition colaCoches = mutex.newCondition();
    Condition colaCamiones = mutex.newCondition();

    public void entraCamion() throws InterruptedException {
        mutex.lock();
        try {
            camionesEsperando++;
            while (operariosLibres < 2) {
                colaCamiones.await();
            }
            camionesEsperando--;
            operariosLibres -= 2;
            System.out.println("Num operarios Libres: " + operariosLibres);
        } finally {
            mutex.unlock();
        }

    }

    public void entraCoche() throws InterruptedException {
        mutex.lock();
        try {
            if (operariosLibres == 0 || camionesEsperando > 0) { //if
                colaCoches.await();
            }
            operariosLibres--;
            System.out.println("Num operarios Libres: " + operariosLibres);
        } finally {
            mutex.unlock();
        }

    }

    public void saleCamion() throws InterruptedException {
        mutex.lock();
        try {
            operariosLibres += 2;
            if (camionesEsperando > 1) {
                colaCamiones.signal();
            } else {
                colaCoches.signal();
            }
        } finally {
            mutex.unlock();
        }

    }

    public void saleCoche() {
        mutex.lock();
        try {
            operariosLibres++;
            if (operariosLibres > 1 && camionesEsperando > 1) {                      //if (operariosLibres > 1 && camionesEsperando > 1) {
                colaCamiones.signal();
            } else {
                colaCoches.signal();
            }
        } finally {
            mutex.unlock();
        }
    }

}
