/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica8pcd;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JTree;

class Cliente {

    private final int id;
    private final char tipo;

    public Cliente(int id, char tipo) {
        this.id = id;
        this.tipo = tipo;
    }
}

/**
 *
 * @author usuario
 */
public class SuperCanvas extends Canvas {

    private final int ancho;
    private final int alto;
    private final ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private final Image tarjetaImg, efectivoImg;

    public SuperCanvas(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.setSize(ancho, alto);
        this.setBackground(new Color(0XDBD5F2));
        tarjetaImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../imagenes/abuelaForrada.jpeg"));
        efectivoImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../imagenes/abuelaForrada.jpeg"));
    }

    @Override
    public void paint(Graphics g) {
        int separacion = 0;
        Font f1 = new Font("Broadway", Font.BOLD, 20);
        Image img = createImage(getWidth(), getHeight()); //coge las medidas del canvas
        Graphics og = img.getGraphics();
        og.setFont(f1);

        //Colas superior e inferior
        og.setColor(new Color(0xF2EB88));
        for (int i = 0; i < 2; i++) {
            og.fillRect(0, separacion, ancho, 140);
            og.setColor(new Color(0xEAF205));
            separacion = alto - 160;
        }

        //String superior de las cajas
        separacion = 75;
        og.setColor(new Color(0x333333));
        for (int i = 1; i < 5; i++) {
            og.drawString("Caja " + i, separacion, 190);
            separacion += 190;
        }

        //Cajas
        separacion = 20;
        og.setColor(new Color(0x333333));
        for (int i = 1; i < 5; i++) {
            og.fillRect(separacion, 200, 180, alto - 410);
            og.drawImage(efectivoImg, 0, 0, 120, 120, this);
            separacion += 190;
        }

        //Meter clientes en la fila de tarjeta
        for (Cliente c : clientes) {

        }

        g.drawImage(img, 0, 0, null);

    }

}
