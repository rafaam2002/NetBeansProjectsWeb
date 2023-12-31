/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package practica7pcd;

import static java.lang.Thread.sleep;
import java.util.Random;

/**
 *
 * @author rafaa
 */
public class GeneradorFAPP extends java.awt.Frame {

    /**
     * Creates new form GeneradorFAPP
     */
    public GeneradorFAPP() {
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
    public static void main(String args[]) throws InterruptedException {
        var gen = new GeneradorFAPP();
        gen.setSize(900, 700);
        var cv = new CanvasComedero(900, 700);
        gen.add(cv);
        gen.setVisible(true);

        Thread[] animales = new Thread[20];
        Random rdm = new Random(4321);
        Comedero c = new Comedero();

        for (int i = 0; i < 20; i++) {
            if (rdm.nextInt(100) < 50) {
                animales[i] = new Perro(c,cv,i+1);   
            } else {
                animales[i] = new Thread(new Gato(c,cv,i+1));
            }
            animales[i].start();
            try {
                sleep(rdm.nextInt(1, 3) * 1000);
            } catch (InterruptedException ex) {
                System.out.println("No se pudo hacer el sleep");
            }
        }
        for (int i = 0; i < 20; i++) {
            try {
                animales[i].join();
            } catch (InterruptedException ex) {
                System.out.println("No se pudo hacer el join");
            }
        }
        System.exit(0);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
