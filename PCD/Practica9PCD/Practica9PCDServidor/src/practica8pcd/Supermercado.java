/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica8pcd;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import IRemoto.ISuperMercado;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author rafaa
 */
public class Supermercado extends UnicastRemoteObject implements ISuperMercado {

    private int numCajasLibres = 4;
    private boolean operarioLibre = true;
    private int efectivoEsperando = 0;
    private final Lock mutex = new ReentrantLock();
    private final Condition colaTarjeta = mutex.newCondition();
    private final Condition colaEfectivo = mutex.newCondition();
    private int idClientes = 1;
    private final SuperCanvas cv;

    public Supermercado(SuperCanvas cv) throws RemoteException {
        this.cv = cv;
    }

    @Override
    public int getIdCliente() throws RemoteException {
        return idClientes++;
    }

    @Override
    public void pagarEfectivo(int quien) throws InterruptedException, RemoteException {
        mutex.lock();
        try {
            efectivoEsperando++;
            cv.insertarClienteEfectivo(quien);
            while (numCajasLibres == 0 || !operarioLibre) {
                colaEfectivo.await();
            }
            cv.eliminarClienteEfectivo(quien);
            efectivoEsperando--;
            numCajasLibres--;
            operarioLibre = false;
            cv.insertarClientePagandoEfectivo(quien);
        } finally {
            mutex.unlock();
        }

    }

    @Override
    public void pagarTarjeta(int quien) throws InterruptedException, RemoteException {
        mutex.lock();

        try {
            cv.insertarClienteTarjeta(quien);
            while (numCajasLibres == 0 || (efectivoEsperando > 0 && operarioLibre)) {
                colaTarjeta.await();
            }
            numCajasLibres--;
            cv.eliminarClienteTarjeta(quien);
            cv.insertarClientePagandoTarjeta(quien);
        } finally {
            mutex.unlock();
        }

    }

    @Override
    public void salir(int quien, char tipo) throws RemoteException {
        mutex.lock();
        try {
            System.out.println("Efectivos esperando: " + efectivoEsperando);
            numCajasLibres++;
            if (tipo == 'E') {
                cv.eliminarClientePagandoEfectivo(quien);
                operarioLibre = true;
                if (efectivoEsperando > 0) {
                    colaEfectivo.signal();
                } else {
                    colaTarjeta.signal();
                }
            }
            else{
                cv.eliminarClientePagandoTarjeta(quien);
                if(operarioLibre = true && efectivoEsperando > 0){
                    colaEfectivo.signal();
                }
                else{
                    colaTarjeta.signal();
                }            
            }
        } finally {
            mutex.unlock();
        }
    }
}
