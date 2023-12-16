/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author rafaa
 */
public class Almacen {

    private final Lock mutex = new ReentrantLock();
    private final Condition colaProveedores = mutex.newCondition();
    private final Condition colaClientes = mutex.newCondition();
    int CANT_MAX = 10;
    int cantActual = 2;

    public void deposita(int cant) throws InterruptedException {
        mutex.lock();
        try {
            while (cant + cantActual > CANT_MAX) {
                colaProveedores.await();
            }
            cantActual += cant;
            System.out.println("Hay: " + cantActual);
            colaClientes.signalAll();

        } finally {
            mutex.unlock();
        }
    }

    public boolean compra(int cant) throws InterruptedException {
        mutex.lock();

        try {
            int intentos = 2;
            while (cantActual < cant && intentos > 0) {
                intentos--;
                System.out.println("Num intentos: " + intentos);
                colaClientes.await();
            }
            if (intentos == 0) {
//                colaProveedores.signal();
                return false;
            } else {
                cantActual -= cant;
                System.out.println("Hay: " + cantActual);
                colaProveedores.signalAll();
                return true;
            }
        } finally {
            mutex.unlock();
        }
    }
}
