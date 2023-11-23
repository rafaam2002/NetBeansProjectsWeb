package pila;

import static java.lang.Thread.sleep;
import java.util.Random;
import practica3pcd.MiCanvas;

/**
 *
 * @author rafaa
 */
public class Consumidor implements Runnable {

    private final PilaLenta lapila;
    private final MiCanvas cv;
    private final int cantidad;
    private int contExcepciones = 1;
    private final String nombre;
    private String msg;

    public Consumidor(PilaLenta p, MiCanvas cv, int cantidad, String nombre) {
        lapila = p;
        this.cv = cv;
        this.cantidad = cantidad;
        this.nombre = nombre;
    }

    public void consumir() {
        Random rdm = new Random();
        for (int i = 0; i < cantidad; i++) {
            int sleepAleatorio = 1 + rdm.nextInt(3);
            try {
                lapila.Desapila();
                sleep(sleepAleatorio * 1000);
            } catch (Exception ex) {
                Thread currentThread = Thread.currentThread();
                currentThread.setName(nombre);
                msg = "Pila vacia " + currentThread.getName() + " X" + contExcepciones;
                System.out.println("la pila no se puede vaciar mas " + currentThread.getName()+ " " + ex.getMessage());
                cv.avisa(this);
                contExcepciones++;
                break;
            }
        }
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public void run() {
        consumir();
    }
}
