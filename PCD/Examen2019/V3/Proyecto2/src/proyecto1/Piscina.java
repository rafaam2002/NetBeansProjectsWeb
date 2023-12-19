/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import IRemoto.IPiscina;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;


/**
 *
 * @author rafaa
 */
public class Piscina extends UnicastRemoteObject implements IPiscina {

    int plazas = 5;
    int adultosDentro = 0;
    int adultosEsperando = 0;
    ReentrantLock mutex = new ReentrantLock();
    Condition colaNinios = mutex.newCondition();
    Condition colaAdultos = mutex.newCondition();
    
    public Piscina ( ) throws  RemoteException{
        
    }

    @Override
    public void entraAdulto() throws InterruptedException {
        mutex.lock();
        try {
            adultosEsperando++;
            while (plazas < 1) {
                colaAdultos.await();
            }
            adultosDentro++;
            adultosEsperando--;
            plazas--;
        } finally {
            mutex.unlock();
        }

    }

    @Override
    public boolean entraNinio() throws InterruptedException, RemoteException {
        mutex.lock();
        try {
            if (adultosDentro == 0) {
//                return false;
            } else {
                while (adultosEsperando > 0 || plazas < 2) {
                    colaNinios.await();
                    if (adultosDentro == 0) {
//                        return false;
                    }
                }
                plazas -= 2;
//                return true;
            }
        } finally {
            mutex.unlock();
        }
        return false;
    }

    @Override
    public void saleAdulto() throws InterruptedException {
        mutex.lock();
        try {
            plazas++;
            adultosDentro--;
            if (adultosEsperando > 0) {
                colaAdultos.signal();
            } else if (plazas >= 2) {
                colaNinios.signal();
            }
        } finally {
            mutex.unlock();
        }

    }

    @Override
    public void saleNinio() throws InterruptedException {
        mutex.lock();
        try {
            plazas += 2;
            if (adultosDentro > 0) {
                colaAdultos.signal();
                colaAdultos.signal();
            } else {
                colaNinios.signal();
            }
        } finally {
            mutex.unlock();
        }

    }

}
