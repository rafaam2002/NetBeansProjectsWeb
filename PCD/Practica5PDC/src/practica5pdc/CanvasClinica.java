/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica5pdc;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

/**
 *
 * @author rafaa
 */
public class CanvasClinica extends Canvas {

    private final Image rehabilitaImg;
    private final Image cliMasaje;
    private final Image cliRehabilita;
    private final Image masajistaImg;
    private final Image vestuarioImg;
    private final ArrayList<String> colaMasaje;
    private final ArrayList<String> colaRehabilita;
    private int i;
//    private boolean MLibre = true;
    private String enMasaje = null;
//    private boolean RLibre = true;
    private String enRehabilitacion = null;
    private String enVestuario = null;

    public CanvasClinica(int ancho, int alto) {
        this.colaMasaje = new ArrayList<>();
        this.colaRehabilita = new ArrayList<>();

        setSize(ancho, alto);
        super.setBackground(Color.white);
        rehabilitaImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/fisioSmall.png"));
        masajistaImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/masajistaSmall.png"));
        cliRehabilita = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/gollumSmall.png"));
        cliMasaje = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/legolasSmall.jpg"));
        vestuarioImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/vestuarioSmall.png"));
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        Image image = createImage(getWidth(), getHeight());
        Font f1 = new Font("Arial", Font.BOLD, 17);

        Graphics gbuf = image.getGraphics();
        gbuf.setFont(f1);

//        Colas
        gbuf.setColor(Color.pink);
        gbuf.fillRect(0, 0, 800, 80);
        gbuf.fillRect(0, 520, 800, 80);

//        Pasillo Masaje
        gbuf.fillRect(0, 80, 80, 80);
        gbuf.fillRect(0, 140, 200, 80);

//        Pasillo Rehabilitacion
        gbuf.fillRect(0, 380, 80, 160);
        gbuf.fillRect(0, 380, 200, 80);

//        Pasillo conector
        gbuf.fillRect(0, 0, 80, 500);

//        SalaMasaje
        gbuf.setColor(Color.lightGray);
        gbuf.fillRect(150, 100, 250, 170);
        gbuf.drawImage(masajistaImg, 300, 100, null);

//        Sala Rehabilitacion
        gbuf.fillRect(150, 330, 250, 170);
        gbuf.drawImage(rehabilitaImg, 300, 330, null);

//        Vestuario
        gbuf.setColor(Color.gray);
        gbuf.fillRect(400, 215, 250, 170);
        gbuf.drawImage(vestuarioImg, 570, 215, null);

//        Pasillo Vestuario
        gbuf.setColor(Color.pink);
        gbuf.fillRect(650, 255, 150, 80);

//        CliMasajes cola
        gbuf.setColor(Color.black);

        i = 0;
        for (String string : colaMasaje) {
            gbuf.drawImage(cliMasaje, i += 55, 0, null);
            gbuf.drawString(string, i += 40, 70);
        }

//        CliRehabilita cola
        i = 0;
        for (String string : colaRehabilita) {
            gbuf.drawImage(cliRehabilita, i += 55, 520, null);
            gbuf.drawString(string, i += 55, 540);
        }

//        CliMasaje
        if (enMasaje != null) {
            gbuf.drawImage(cliMasaje, 170, 120, null);
            gbuf.drawString(enMasaje, 180, 200);
        }

//        CliRehabilita
        if (enRehabilitacion != null) {
            if (enRehabilitacion.substring(0, 1).equals("M")) {
                gbuf.drawImage(cliMasaje, 170, 350, null);

            } else {
                gbuf.drawImage(cliRehabilita, 170, 350, null);
            }
            gbuf.drawString(enRehabilitacion, 180, 420);
        }

//      CliVestuario
        if (enVestuario != null) {
            if (enVestuario.substring(0, 1).equals("M")) {
                gbuf.drawImage(cliMasaje, 420, 235, this);
            } else {
                gbuf.drawImage(cliRehabilita, 420, 235, this);
            }
            gbuf.drawString(enVestuario, 430, 305);
        }

        g.drawImage(image, 0, 0, this);

    }

    synchronized void pintaEnCola(String name) {
        if ("M".equals(name.substring(0, 1))) {
            if (!colaMasaje.contains(name)) {
                colaMasaje.add(name);
            }
        } else if (!colaRehabilita.contains(name)) {
            colaRehabilita.add(name);
        }
        repaint();
    }

    synchronized void pintaEntraMasaje(String name) {
        if (colaMasaje.indexOf(name) != -1) {
            colaMasaje.remove(name);
            enMasaje = name;
        }
        repaint();
    }

    synchronized void pintaEntraRehabilitacion(String name) {
        if ("M".equals(name.substring(0, 1))) {
            if (colaMasaje.indexOf(name) != -1) {
                colaMasaje.remove(name);
            }
            colaMasaje.remove(name);
        } else {
            if (colaRehabilita.indexOf(name) != -1) {
                colaRehabilita.remove(name);
            }
        }
        enRehabilitacion = name;
        repaint();
    }
    

    synchronized void pintaEntraVestuario(String name) {
        if (enMasaje != null && enMasaje.equals(name)) {
            enMasaje = null;
        } else {
            enRehabilitacion = null;
        }
        enVestuario = name;
        repaint();

    }

    synchronized void pintaSaleVestuario() {
        enVestuario = null;
        repaint();
    }

//    synchronized void pintaEntraSala(String name) {
//        if ("M".equals(name.substring(0, 1))) {
//            if (colaMasaje.indexOf(name) != -1) {
//                colaMasaje.remove(name);
//            }
//            enMasaje = name;
//
//        } else {
//            if (colaRehabilita.indexOf(name) != -1) {
//                colaRehabilita.remove(name);
//            }
//            enRehabilitacion = name;
//        }
//    }
}
