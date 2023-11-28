/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica8pcd;

/**
 *
 * @author rafaa
 */
public class Supermercado {

    private int numCajasLibres = 4;
    private boolean operarioLibre = true;
    private int efectivoEsperando = 0;

    synchronized public void pagarEfectivo() throws InterruptedException {
        efectivoEsperando++;
        while (numCajasLibres == 0 || !operarioLibre) {
            wait();
        }
        numCajasLibres--;
        operarioLibre = false;

    }

    synchronized public void pagarTarjeta() throws InterruptedException {
        while (numCajasLibres == 0 || (efectivoEsperando > 0 && operarioLibre)) {
            wait();
        }
        numCajasLibres--;
    }

    synchronized public void salir() {
        numCajasLibres++;
        if ('E' == Thread.currentThread().getName().charAt(0)) {
            operarioLibre = true;
        }
        notifyAll();
    }

}
