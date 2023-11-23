/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica4;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author wadad
 */
public class CanvasPila extends Canvas {

    private int numelementos;
    private int cima;
    private int capacidad;
    private volatile Object datos[];
    private String mensaje;

    public CanvasPila(int capacidad) {
        this.capacidad = capacidad;
        this.cima = 0;
        this.numelementos = 0;
        this.datos = null;
        this.mensaje = null;
        this.setSize(600, 700);

        this.setBackground(Color.cyan);
    }

    @Override
    public void paint(Graphics g) {
        Font f = new Font("Arial", Font.BOLD, 30);
        Image imgb = createImage(this.getWidth(), this.getHeight());
        Graphics og = imgb.getGraphics();

        og.setFont(f);
        og.setColor(Color.black);
        og.drawString("PILA", 265, 40);
        og.setColor(Color.white);
        og.fillRect(200, 50, 200, 555);
        og.setColor(Color.BLACK);
        og.drawRect(200, 50, 200, 555);
        for (int i = 0; i < capacidad - 1; i++) {
            og.setColor(Color.black);
            og.drawLine(200, 100 + (i * 55), 400, 100 + (i * 55));
        }
        
        
        
        for (int i = 0; i < cima; i++) {

            og.setColor(Color.black);
            og.drawString("Datos= " + datos[i], 220, 585 - (i * 55));
        }
        if (mensaje != null) {
            og.setColor(Color.red);
            og.drawString(mensaje, 210, 650);
        }
        g.drawImage(imgb, 0, 0, null);

    }

    public void avisa(String mensaje) {
        this.mensaje = mensaje;
        repaint();
    }

    public void representa(Object[] buf, int cima, int numele) {
        this.numelementos = numele;
        this.cima = cima;
        this.datos = buf;
        mensaje = null;
        repaint();
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
}
