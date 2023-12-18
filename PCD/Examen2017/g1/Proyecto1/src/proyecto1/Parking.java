/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author rafaa
 */
public class Parking {

    int libresBus = 2;
    int libresCoche = 5;
    int busesEsperando = 0;

    int conCitaEsperando = 0;

    public synchronized void entraBus() throws InterruptedException {
        busesEsperando++;
        while (libresBus == 0) {
            wait();
        }
        busesEsperando--;
        libresBus--;
    }

    public synchronized boolean entraCoche() throws InterruptedException {
        while ((libresCoche == 0 && libresBus == 0) || (libresBus > 0 && busesEsperando > 0)) {
            wait();
        }
        if (libresCoche > 0) {
            libresCoche--;
            return true;
        } else {
            entraBus();
            return false;
        }
    }

    public synchronized void saleBus() throws InterruptedException {
        libresBus++;
        notifyAll();
    }

    public synchronized void saleCoche() {
        libresCoche++;
        notifyAll();
    }

}
