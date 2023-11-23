package practica3pcd;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import pila.Consumidor;
import pila.PilaLenta;
import pila.Productor;

/**
 * preguntar por la calse avisa
 *
 * @author rafaa
 */
public class MiCanvas extends Canvas {

    private PilaLenta pila;
    private final ArrayList hilosConMensaje;
    private final int capacidadPila;
    private int posYCima;  //posicion de cima de la pila
    final int alturaCanvas = 700;
    final int anchuraCanvas = 800;
    int alturaTapa;
    int alturaBase;

    public MiCanvas(int ancho, int alto, int capacidadPila) {
        this.hilosConMensaje = new ArrayList();
        this.setSize(ancho, alto);
        this.setBackground(Color.WHITE);
        this.capacidadPila = capacidadPila;
        alturaTapa = (alturaCanvas / 2) - 20 * (capacidadPila / 2);//alturaCanvas/2 -> la mitad del canvas; 20*capacidadPila/2 ->primer ovalo sube la mitad de la capacidad de la pila

        if (capacidadPila % 2 == 0) { //si es impar hay que sumarle otra casilla
            alturaBase = (alturaCanvas / 2) + 20 * (capacidadPila / 2); //segundo ovalo que baja con respecto al eje y la otra mitad de la capacidad de la pila para que quepan todos los elementos
        } else {
            alturaBase = (alturaCanvas / 2) + 20 * (capacidadPila / 2) + 20;
        }
       // posYCima = alturaBase - 2;

    }

    @Override
    public void update(Graphics g) { //llamar al metodo update desde mi programa no es una practica recomendada, se recomienda repaint (luego el sistema llamara a update entre otras cosas que hace el repaint)
        paint(g); //para que no borre nada de la pantalla pero nos surge el problema de que
        //sobreescribimos los numeros (para solucionarlo dibujamos una imagen completa cada vez)
    }

    /**
     * agrega un hilo con mensaje a hilosConMensaje y llama a repaint() si el
     * hilo existe solo llama a repaint()
     *
     * @param o
     */
    public void avisa(final Object o) {
        if (!hilosConMensaje.contains(o)) {
            hilosConMensaje.add(o);
        }
        repaint();
    }

    public void representa(final PilaLenta pila) {
        this.pila = pila;
        repaint();
    }

    @Override
    public void paint(Graphics g) {//se ejecuta solo una vez a menos que ejecutemos repaint

        Font f1 = new Font("Wide Latin", Font.PLAIN, 18);
        Image img = createImage(getWidth(), getHeight()); //coge las medidas del canvas
        Graphics og = img.getGraphics();

        og.setFont(f1);
        og.setColor(Color.red);

        posYCima = alturaBase - 2;

        // og.drawOval(anchuraCanvas / 2, alturaCanvas / 2, 100, 10);
        og.fillOval(anchuraCanvas / 2 - 30, alturaTapa - 4, 60, 8);
        og.fillOval(anchuraCanvas / 2 - 30, alturaBase - 4, 60, 8);
        og.drawLine(anchuraCanvas / 2 - 30, alturaTapa, anchuraCanvas / 2 - 30, alturaBase);
        og.drawLine(anchuraCanvas / 2 + 30, alturaTapa, anchuraCanvas / 2 + 30, alturaBase);

        for (int i = 1; i < capacidadPila; i++) {
            og.drawLine(anchuraCanvas / 2 - 30, alturaTapa + (i * 20), anchuraCanvas / 2 + 30, alturaTapa + (i * 20));
        }
        og.setColor(Color.black);
        int alturaMsgP = 0;
        int alturaMsgC = 0;

        for (Iterator it = hilosConMensaje.iterator(); it.hasNext();) {
            switch (it.next()) {
                case Productor p -> {
                    og.drawString(p.getMsg(), anchuraCanvas - 275, alturaCanvas - 580 + alturaMsgP);
                    alturaMsgP += 20;
                }
                case Consumidor c -> {
                    og.drawString(c.getMsg(), 20, alturaCanvas - 580 + alturaMsgC);
                    alturaMsgC += 20;
                }
                default -> {
                }
            }
        }
        og.setColor(Color.ORANGE);
        if(pila != null)
            for (int i = 0; i < pila.getNum(); i++) {
                og.drawString(pila.getDatos()[i].toString(), anchuraCanvas / 2 - 20, posYCima);
                posYCima -= 20;
            }
        

//        if (numElementos != 0) { //esto hara que cuando se elimine el ultimo elemento de la pila solo se pinte la estructura de la pila
//            for (int i = 0; i < numElementos; i++) {
//                og.drawString(datosPintar[i].toString(), anchuraCanvas / 2 - 20, posYCima);
//                posYCima -= 20;
//            }
//        }
        g.drawImage(img, 0, 0, null);
    }

}
