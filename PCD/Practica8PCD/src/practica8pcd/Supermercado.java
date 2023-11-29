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
//        System.out.println(numCajasLibres + " " + operarioLibre);
        operarioLibre = false;

    }

    synchronized public void pagarTarjeta() throws InterruptedException {
        while (numCajasLibres == 0 || (efectivoEsperando > 0 && operarioLibre)) {
            wait();
        }
        numCajasLibres--;
//        System.out.println(numCajasLibres + " " + operarioLibre);
    }

    synchronized public void salir( char tipo ) {
        numCajasLibres++;
        System.out.println(numCajasLibres + " " + operarioLibre);
        System.out.println(Thread.currentThread().getName().charAt(0));
        System.out.println(Thread.currentThread().getName());
        if (tipo == 'E') {
            efectivoEsperando--;
            operarioLibre = true;
        }
        notifyAll();
    }

}
