/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica4;

import practica4.CanvasPila;
import static java.lang.Thread.sleep;

/**
 *
 * @author usuario
 */
public class Pila implements IPila {

    private int numelementos;
    private int cima;
    private int capacidad;
    private volatile Object datos[];
    private CanvasPila canvas;

    public Pila(int capacidad, CanvasPila canvas) {
        datos = new Object[capacidad];
        this.capacidad = capacidad;
        this.canvas = canvas;
        cima = 0;
        numelementos = -1;
    }

    @Override
    public int GetNum() {
        return numelementos;
    }

    @Override
    public synchronized void Apila(Object elemento) throws Exception {
        int contador = 0;
        
        while (pilaLlena() && contador < 3) {

            System.out.println("El " + (contador + 1) + " intento del hilo " + Thread.currentThread().getName());
            wait();
            contador++;
        }
        if (!pilaLlena() && contador < 3) {
            numelementos++;
            datos[numelementos] = elemento;
            cima++;
            notifyAll();
            canvas.representa(datos, cima, numelementos);
            System.out.println("Soy el hilo " + Thread.currentThread().getName() + " y he insertado el elemento " + elemento + " correcto.");
            
        } else {
            throw new Exception(Thread.currentThread().getName()+": la pila esta llena, Me voy.");

        }
    }

    @Override
    public synchronized Object Desapila() throws Exception {
        int contador = 0;
        while (pilaVacia() && contador < 3) {
            System.out.println("El " + (contador + 1) + " intento del hilo " + Thread.currentThread().getName());
            wait();
            contador++;
        }
        if (!pilaVacia() && contador < 3) {
            Object aux = datos[numelementos];
            numelementos--;
            cima--;
            canvas.representa(datos, cima, numelementos);
            notify();
            return aux;

        } else {
            throw new Exception(Thread.currentThread().getName()+": la pila esta vacia,Me voy.");
        }
    }

    @Override
    public Object Primero() throws Exception {
        if (numelementos != 0) {
            return datos[cima];
        } else {
            throw new Exception("la pila esta vacia.");
        }
    }

    public boolean pilaVacia() {
        if (numelementos == -1) {
            canvas.avisa("PILA VACIA");
            return true;
        } else {
            return false;
        }
    }

    public boolean pilaLlena() {
        if (cima == capacidad) {
            canvas.avisa("PILA LLENA");
            return true;
        } else {
            return false;
        }
    }
}
