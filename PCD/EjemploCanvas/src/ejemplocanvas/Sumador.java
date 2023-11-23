/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplocanvas;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaa
 */
public class Sumador extends Thread {

    private int cual;
    private Recurso r;

    public Sumador(int cual, Recurso r) {
        this.r = r;
        this.cual = cual;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            try {
                r.incrementa(cual);
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Sumador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
