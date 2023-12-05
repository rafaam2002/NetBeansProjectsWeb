/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica8pcd;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author rafaa
 */
public class Supermercado {

    private int numCajasLibres = 4;
    private boolean operarioLibre = true;
    private int efectivoEsperando = 0;
    private final Lock mutex = new ReentrantLock();
    private final Condition colaTarjeta = mutex.newCondition();
    private final Condition colaEfectivo = mutex.newCondition();

    public void pagarEfectivo() throws InterruptedException {
        mutex.lock();
        try {
            efectivoEsperando++;
            if (numCajasLibres == 0 || !operarioLibre) {
                colaEfectivo.await();
            }
            efectivoEsperando--;
            numCajasLibres--;
            operarioLibre = false;
        } finally {
            mutex.unlock();
        }

    }

    public void pagarTarjeta() throws InterruptedException {
        mutex.lock();

        try {
            if (numCajasLibres == 0 || (efectivoEsperando > 0 && operarioLibre)) {
                colaTarjeta.await();
            }
            numCajasLibres--;
        } finally {
            mutex.unlock();
        }

    }

    public void salir(char tipo) {
        mutex.lock();
        try {
            System.out.println("Efectivos esperando: " + efectivoEsperando);
            numCajasLibres++;

            if (tipo == 'E') {
                operarioLibre = true;
            }
            if (efectivoEsperando > 0) {
                colaEfectivo.signal();
            } else {
                colaTarjeta.signal();
            }
        } finally {
            mutex.unlock();
        }
    }
}
