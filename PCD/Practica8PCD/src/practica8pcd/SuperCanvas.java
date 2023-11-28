/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica8pcd;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JTree;

/**
 *
 * @author usuario
 */
public class SuperCanvas extends Canvas {

    private final int ancho;
    private final int alto ;

    public SuperCanvas(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.setSize(ancho, alto);
        this.setBackground(Color.WHITE);
    }

    @Override
    public void paint(Graphics g) {
        Image img = createImage(getWidth(), getHeight()); //coge las medidas del canvas
        Graphics og = img.getGraphics();
        og.drawRect(0, 0, ancho, alto);
        g.drawImage(img, 0, 0, null);

    }
}
