/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author rafaa
 */
public class canvasPiscina extends Canvas{

    public canvasPiscina(int ancho, int largo) {
        super.setSize(ancho, largo);
        super.setBackground(Color.WHITE);
    }
    
    @Override
    public synchronized void update(Graphics g){
        paint(g);
    }
    
    /**
     *
     * @param g
     */
    @Override
    public synchronized void paint (Graphics g){
        Image imagen = createImage(getWidth(), getHeight());
        Graphics gbuf = imagen.getGraphics();
        Font f1 = new Font("Arial",Font.BOLD,15);
        gbuf.setFont(f1);
        
        gbuf.setColor(Color.red);
        gbuf.fillRect(0, 0, 200, 200);
        
        g.drawImage(imagen, 0, 0, this);
    }
    
}
