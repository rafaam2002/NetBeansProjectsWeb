/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package practica8pcd;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class GeneradorAPP extends java.awt.Frame {

    private static SuperCanvas cv = new SuperCanvas(800, 600);

    /**
     * Creates new form GeneradorAPP
     */
    public GeneradorAPP() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        var generador = new GeneradorAPP();
        generador.setSize(800, 600);
        generador.setTitle("Practica 8");
        generador.setLocation(100, 50);

        generador.add(cv);
        generador.setVisible(true);

        var rdm = new Random();

        var thpEfectivo = Executors.newFixedThreadPool(10);
        var thpTarjeta = Executors.newFixedThreadPool(10);
        var esperaTarjeta = new ArrayList<Future<Integer>>();
        var esperaEfectivo = new ArrayList<Future<Integer>>();
        int tiempoEsperaTarjeta = 0;
        int tiempoEsperaEfectivo = 0;

        var supermercado = new Supermercado();
        for (int i = 0; i < 50; i++) {
            if (rdm.nextInt(100) < 0) {
                esperaTarjeta.add(thpTarjeta.submit(new Tarjeta(supermercado, "Tarjeta-" + i, cv)));
            } else {
                esperaEfectivo.add(thpEfectivo.submit(new Efectivo(supermercado, "Efectivo-" + i, cv)));
            }
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(GeneradorAPP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        thpEfectivo.shutdown();
        System.out.println("El main hace el primer shutdown");
        thpTarjeta.shutdown();
        System.out.println("El main hace el primer shutdown");

        for (Future<Integer> future : esperaTarjeta) {
            try {
                tiempoEsperaTarjeta += future.get();
            } catch (InterruptedException ex) {
                Logger.getLogger(GeneradorAPP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(GeneradorAPP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (Future<Integer> future : esperaEfectivo) {
            try {
                tiempoEsperaEfectivo += future.get();
            } catch (InterruptedException ex) {
                Logger.getLogger(GeneradorAPP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(GeneradorAPP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GeneradorAPP.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Tiempo usado por efectivo: " + tiempoEsperaEfectivo + " sec");
        System.out.println("Tiempo usado por tarjeta: " + tiempoEsperaTarjeta + " sec");

        try {
            sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GeneradorAPP.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}