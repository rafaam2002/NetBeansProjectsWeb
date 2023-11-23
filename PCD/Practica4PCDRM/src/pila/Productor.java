package pila;

import java.util.Random;
import practica3pcd.MiCanvas;

/**
 *
 * @author rafaa
 */
public class Productor extends Thread {

    private final PilaLenta lapila;
    private final MiCanvas cv;
    private final int cantidad;
    private int contExcepciones = 1;
    private int intentos;
    private String msg;

    public Productor(PilaLenta p, MiCanvas cv, int cantidad, String nombre) {
        lapila = p;
        this.cv = cv;
        this.cantidad = cantidad;
        this.setName(nombre);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void producir() {
        intentos = 0;
        Random rdm = new Random();
        for (int i = 0; i < cantidad; i++) {
            int sleepAleatorio = 1 + rdm.nextInt(3);
            try {
                lapila.Apila((int) (rdm.nextDouble() * 100));
                sleep(sleepAleatorio * 1000);
            } catch (Exception ex) {
                msg = "Pila llena " + getName() + " X" + contExcepciones;
                System.out.println("la pila no se ha podido llenar mas " + getName()+ " " + ex.getMessage());
                cv.avisa(this);
                contExcepciones++;
                break; //captura la excepcion fuera
            }
        }
    }

    @Override
    public void run() {
        producir();
    }
}
