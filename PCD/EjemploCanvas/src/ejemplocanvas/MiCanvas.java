/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplocanvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author rafaa
 */
public class MiCanvas extends Canvas {

    private final int valores[] = {0, 0};
    private String mensaje;

    public MiCanvas(int ancho, int alto) {
        this.setSize(ancho, alto);
        this.setBackground(Color.CYAN);
        mensaje = "";
    }

    public void actualiza(int[] contadores) {
        valores[0] = contadores[0];
        valores[1] = contadores[1];
        repaint(); //
    }
    public void ponCadena(String cadena){
        mensaje = cadena;
        repaint();
    }
    
    @Override
    public void update (Graphics g){ //llamar al metodo update desde mi programa no es una practica recomendada, se recomienda repaint (luego el sistema llamara a update entre otras cosas que hace el repaint)
        paint(g); //para que no borre nada de la pantalla pero nos surge el problema de que
                 //sobreescribimos los numeros (para solucionarlo dibujamos una imagen completa cada vez)
    }
    

    @Override
    public void paint(Graphics g) {//se ejecuta solo una vez a menos que ejecutemos repaint

        Font f1 = new Font("Broadway", Font.BOLD, 30);
        Font f2 = new Font("Corbel", Font.BOLD | Font.ITALIC, 30);
        
        Image img = createImage(getWidth(),getHeight()); //coge las medidas del canvas
        Graphics og = img.getGraphics();
        
        og.setColor(Color.red);
        og.fillOval(50, 70, 30, 30);
        og.setFont(f1);

        og.drawString("Contador 1 =" + valores[0], 100, 100);
        og.setColor(Color.blue);
        og.fillRect(50, 200, 30, 30);
        og.setFont(f2);
        og.drawString("Contador 2 = " + valores[1], 100, 200);
        og.drawString(mensaje, 460, 50);
        g.drawImage(img, 0, 0, null);
    }
    
}
