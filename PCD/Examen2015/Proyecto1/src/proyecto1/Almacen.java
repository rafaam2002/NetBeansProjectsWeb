/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author rafaa
 */
public class Almacen {

    int CANT_MAX = 10;
    int cantActual = 2;

    public synchronized void deposita(int cant) throws InterruptedException {
        while (cant + cantActual > CANT_MAX) {
            wait();
        }
        cantActual += cant;
        System.out.println("Hay: " + cantActual);
        notifyAll();
    }

    public synchronized void compra(int cant) throws InterruptedException {
        while (cantActual < cant) {
            wait();
        }
        cantActual -= cant;
        System.out.println("Hay: " + cantActual);
        notifyAll();
    }

}
