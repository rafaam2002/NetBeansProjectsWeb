/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pila;

import static java.lang.Thread.sleep;
import practica3pcd.MiCanvas;

/**
 * Pila con los metodos apilar y desapilar realentizados para que quede mas
 * clara la concurrencia ya que dos hilos que por ejemplo quieran apilar primero
 * lee uno la variable cima Ej = 3, y mientras se produce el sleep el otro hilo
 * lee tambien cima = 3, entonces sobreescriben el dato que querian
 * introducir.Esto sin el sleep tambien pasaria pero el sleep acentua el suceso
 *
 * @author usuario
 */
public class PilaLenta implements IPila {

    private int cima;      //estas variables no se pueden ser optimizadas por el compilador
    private int capacidad; //ya que las van a leer concurrentemente varios hilos
    private int numelementos;
    private final Object[] datos; //array de datos
    private final MiCanvas cv;

    /**
     *
     * @param capacidad
     * @param cv
     * @param anchoCanvas
     * @param altoCanvas
     */
    public PilaLenta(int capacidad, MiCanvas cv, int anchoCanvas, int altoCanvas) {
        cima = 0;
        this.capacidad = capacidad;
        numelementos = 0;
        datos = new Object[capacidad];
        this.cv = cv;
    }

    /**
     * Metodo creado por mi que vacia lapila para saber como se quedo
     */
    public void muestraPila() {
        System.out.println("capacidad: " + capacidad);
        System.out.println("numero de elementos: " + numelementos);
        System.out.println("valor de cima: " + cima);

        for (int i = cima - 1; i >= 0; i--) {
            System.out.println(datos[i]);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int getNum() {
        return numelementos;
    }

    public Object[] getDatos() {
        return datos;
    }

    /**
     *
     * @param elemento
     * @throws Exception
     */
    @Override
    public synchronized void Apila(Object elemento) throws Exception {
        int intentos = 0;
        while (pilallena() && intentos < 3) {
            System.out.println("El " + (intentos + 1) + " intento del hilo  " + Thread.currentThread().getName() + " apilando");
            wait();
            intentos++;
        }
        if (!pilallena() && intentos < 3) {
            //sleep(300);
            datos[cima] = elemento;  //La clase Object no tiene constructor de copia y no es inmutable, esto no esta bien entonces
            cima++;
            notifyAll();
            numelementos++;

            //sleep(100);
            cv.representa(this);
        } else {
            //sleep(100);
            throw new Exception("La pila esta llena ");
        }

    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public synchronized Object Desapila() throws Exception {
        int intentos = 0;
        while (pilavacia() && intentos < 3) {
            System.out.println("El " + (intentos + 1) + " intento del hilo  " + Thread.currentThread().getName() + " desapilando");
            wait();
            intentos++;
        }
        if (!pilavacia() && intentos < 3) {
            cima--;
            numelementos--;
            notifyAll();
            //sleep(300);
            cv.representa(this);
            return datos[cima];
        } else {
            //sleep(200);
            throw new Exception("La pila esta vacia");
        }
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public Object Primero() throws Exception {
        if (!pilavacia()) {
            return datos[cima - 1];
        } else {
            throw new Exception("La pila esta vacia");
        }
    }

    private boolean pilavacia() {
        return (cima == 0);
    }

    private boolean pilallena() {
        return (cima >= capacidad);
    }

}
