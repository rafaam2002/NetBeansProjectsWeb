/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica7pcd;

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
public class CanvasComedero extends Canvas {

    class Animal {

        private int id;
        private char tipo;

        public Animal(int id, char tipo) {
            this.id = id;
            this.tipo = tipo;
        }

        /**
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * @return the tipo
         */
        public char getTipo() {
            return tipo;
        }

        /**
         * @param tipo the tipo to set
         */
        public void setTipo(char tipo) {
            this.tipo = tipo;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Animal c = (Animal) obj;
            if (c.getId() == this.getId()) {
                return true;
            } else {
                return false;
            }
        }
    }

    private ArrayList<Animal> colaPerros = new ArrayList();
    private ArrayList<Animal> colaGatos = new ArrayList();
    private ArrayList<Animal> colaComiendo = new ArrayList();

    private Image perroImg, gatoImg, comidaImg;

    public CanvasComedero(int ancho, int alto) throws InterruptedException {
        super.setSize(ancho, alto);
        super.setBackground(Color.white);

        perroImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../Image/scoobyDoo.png"));
        gatoImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../Image/doraemon.png"));
        comidaImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../Image/dorayakiSinFondo.png"));

    }

    private void inserta(ArrayList<Animal> lista, int id, char tipo) {
        Animal c = new Animal(id, tipo);
        int pos = lista.indexOf(c);
        if (pos == -1) {
            lista.add(c);
        } else {
            lista.set(pos, c);
        }
        repaint();
    }

    public synchronized void enColaPerros(int id, char tipo) {
        inserta(colaPerros, id, tipo);
        repaint();
    }

    public synchronized void finColaPerros(int id, char tipo) {
        Animal a = new Animal(id, tipo);
        colaPerros.remove(a);
        repaint();
    }

    public synchronized void enColaGatos(int id, char tipo) {
        inserta(colaGatos, id, tipo);
        repaint();
    }

    public synchronized void finColaGatos(int id, char tipo) {
        Animal a = new Animal(id, tipo);
        colaGatos.remove(a);
        repaint();
    }

    public synchronized void enColaComiendo(int id, char tipo) {
        inserta(colaComiendo, id, tipo);
        repaint();
    }

    public synchronized void finColaComiendo(int id, char tipo) {
        Animal a = new Animal(id, tipo);
        colaComiendo.remove(a);
        repaint();
    }

    @Override
    public void update(Graphics g) {
        paint(g);

    }

    @Override
    public void paint(Graphics g) {
        int[] posColaPerros = {460, 150, 440, 130};
        int[] posColaGatos = {460, 320, 440, 130};
        int[] posColaComiendo = {0, 150, 300, 300};

        int anchoicon = 40, altoicon = 80, espacio = 65;

        Image imagen = createImage(getWidth(), getHeight());
        Font f1 = new Font("Arial", Font.BOLD, 15);
        Graphics gbuf = imagen.getGraphics();
        gbuf.setFont(f1);
        setBackground(Color.lightGray);

        //cola perros
        gbuf.setColor(Color.white);
        gbuf.fillRect(posColaPerros[0], posColaPerros[1], posColaPerros[2], posColaPerros[3]);
        for (int i = 0; i < colaPerros.size(); i++) {
            if (i < colaPerros.size()) {
                gbuf.setColor(Color.blue);
                gbuf.drawImage(perroImg, posColaPerros[0] + espacio * i, posColaPerros[1] + 14, anchoicon, altoicon, null);
                gbuf.drawString(colaPerros.get(i).getId() + "," + colaPerros.get(i).getTipo(), posColaPerros[0] + espacio * i, posColaPerros[1] + 14);
            }
        }

        //cola gatos
        gbuf.setColor(Color.white);
        gbuf.fillRect(posColaGatos[0], posColaGatos[1], posColaGatos[2], posColaGatos[3]);
        for (int i = 0; i < colaGatos.size(); i++) {
            if (i < colaGatos.size()) {
                gbuf.setColor(Color.red);
                gbuf.drawImage(gatoImg, posColaGatos[0] + espacio * i, posColaGatos[1] + 14, anchoicon, altoicon, null);
                gbuf.drawString(colaGatos.get(i).getId() + "," + colaGatos.get(i).getTipo(), posColaGatos[0] + espacio * i, posColaGatos[1] + 14);
            }

        }

        //comiendo
        gbuf.setColor(Color.white);
        gbuf.fillRect(posColaComiendo[0], posColaComiendo[1], posColaComiendo[2], posColaComiendo[3]);

        for (int i = 0; i < colaComiendo.size(); i++) {
            if (i < colaComiendo.size()) {
                if (colaComiendo.get(i).getTipo() == 'G') {
                    gbuf.setColor(Color.red);
                    gbuf.drawImage(gatoImg, posColaComiendo[0] + espacio * i, posColaComiendo[1] + 14, anchoicon, altoicon, null);
                    gbuf.drawString(colaComiendo.get(i).getId() + "," + colaComiendo.get(i).getTipo(), posColaComiendo[0] + espacio * i, posColaComiendo[1] + 14);
                } else {
                    gbuf.setColor(Color.blue);
                    gbuf.drawImage(perroImg, posColaComiendo[0] + espacio * i, posColaComiendo[1] + 14, anchoicon, altoicon, null);
                    gbuf.drawString(colaComiendo.get(i).getId() + "," + colaComiendo.get(i).getTipo(), posColaComiendo[0] + espacio * i, posColaComiendo[1] + 14);
                }
            }
        }

        gbuf.drawImage(comidaImg, posColaComiendo[0], posColaComiendo[1], null);
        g.drawImage(imagen, 0, 0, this);

    }
}
