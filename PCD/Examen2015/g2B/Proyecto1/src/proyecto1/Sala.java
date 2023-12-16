/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author rafaa
 */
public class Sala {

    boolean libre = true;
    int conCitaEsperando = 0;

    public synchronized void concitaIn(int cant) throws InterruptedException {
        conCitaEsperando++;
        while (!libre) {
            wait();
        }
        libre = false;
        conCitaEsperando--;
    }

    public synchronized void concitaOut(int cant) throws InterruptedException {
        libre = true;
        notifyAll();
    }

    public synchronized void sincitaIn(int cant) throws InterruptedException {
        while (!libre || conCitaEsperando > 0) {
            wait();
        }
        libre = false;
    }

    public synchronized void sincitaOut(int cant) {
        libre = true;
        notifyAll();
    }

}
