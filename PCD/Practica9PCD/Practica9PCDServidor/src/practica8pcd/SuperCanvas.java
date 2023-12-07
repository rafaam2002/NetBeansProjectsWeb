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

    public Cliente(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Cliente cliente = (Cliente) obj;
        return id == cliente.id;
    }
}

/**
 *
 * @author usuario
 */
public class SuperCanvas extends Canvas {

    private final int ancho;
    private final int alto;
    private final ArrayList<Cliente> clientesTarjeta = new ArrayList<Cliente>();
    private final ArrayList<Cliente> clientesEfectivo = new ArrayList<Cliente>();
    private final Image tarjetaImg, efectivoImg, cajeroImg;
    private final ArrayList<Cliente> clientesPagandoTarjeta = new ArrayList<Cliente>();
    private Cliente clientePagandoEfectivo = null;
    private final ArrayList<Cliente> clientesPagandoEfectivo = new ArrayList<Cliente>();

    public SuperCanvas(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.setSize(ancho, alto);
        this.setBackground(new Color(0XDBD5F2));
        tarjetaImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../imagenes/tarjeta.png"));
        efectivoImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../imagenes/abuelaForrada.png"));
        cajeroImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../imagenes/cajeroPersona.png"));
    }

    public synchronized void insertarClienteTarjeta(int id) {
        var c = new Cliente(id);
        if (!clientesTarjeta.contains(c)) {
            clientesTarjeta.add(c);
        }
        repaint();
    }

    public synchronized void insertarClienteEfectivo(int id) {
        var c = new Cliente(id);
        if (!clientesEfectivo.contains(c)) {
            clientesEfectivo.add(c);
        }
        repaint();
    }

    public synchronized void eliminarClienteTarjeta(int id) {
        var c = new Cliente(id);
        if (clientesTarjeta.contains(c)) {
            clientesTarjeta.remove(c);
        }
        repaint();
    }

    public synchronized void eliminarClienteEfectivo(int id) {
        var c = new Cliente(id);
        if (clientesEfectivo.contains(c)) {
            clientesEfectivo.remove(c);
        }
        repaint();
    }

    public synchronized void insertarClientePagandoTarjeta(int id) {
        var c = new Cliente(id);
        if (!clientesPagandoTarjeta.contains(c)) {
            clientesPagandoTarjeta.add(c);
        }
        repaint();
    }

    public synchronized void eliminarClientePagandoTarjeta(int id) {
        var c = new Cliente(id);
        if (clientesPagandoTarjeta.contains(c)) {
            clientesPagandoTarjeta.remove(c);
        }
        repaint();
    }

    public synchronized void insertarClientePagandoEfectivo(int id) {
        var c = new Cliente(id);
        if (!c.equals(clientePagandoEfectivo)) {
            clientePagandoEfectivo = c;
        }
        repaint();
    }

    public synchronized void eliminarClientePagandoEfectivo(int id) {
        clientePagandoEfectivo = null;
        repaint();
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public synchronized void paint(Graphics g) {
        int separacion = 0;
        Font f1 = new Font("Broadway", Font.BOLD, 20);
        Font f2 = new Font("Broadway", Font.BOLD, 10);
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
            separacion += 190;
        }

        //Meter clientes en la fila de tarjeta
        og.setFont(f1);
        separacion = 10;
        for (Cliente c : clientesTarjeta) {
            og.drawImage(tarjetaImg, separacion, 10, 120, 120, this);
            og.drawString("T-" + c.getId(), separacion, 140);
            separacion += 120;

        }

        //Meter clientes en la fila de Efectivo
        separacion = 10;
        for (Cliente c : clientesEfectivo) {
            og.drawImage(efectivoImg, separacion, alto - 160, 120, 120, this);
            og.drawString("E-" + c.getId(), separacion, alto - 130);
            separacion += 120;
        }

        //Clientes pagando tarjeta
        separacion = 30;
        for (Cliente c : clientesPagandoTarjeta) {
            og.drawImage(tarjetaImg, separacion, 210, 120, 120, this);
            og.drawString("T-" + c.getId(), separacion, 200);
            separacion += 190;

        }
        if (clientePagandoEfectivo != null) {
            og.drawImage(efectivoImg, 600, 210, 120, 120, this);
            og.drawString("E-" + clientePagandoEfectivo.getId(), 600, 200);
        }

        //Persona Cajero
        og.drawImage(cajeroImg, 660, 290, 190, 170, this);

        g.drawImage(img, 0, 0, null);

    }

}
