/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplocanvas;

/**
 *
 * @author rafaa
 */
public class Recurso {
    private final MiCanvas cv;
    private final int contadores[] = {0, 0};
    
    public Recurso (MiCanvas cv){
        this.cv = cv;
    }
    public Recurso(){
        cv = null;
    }

    public synchronized void incrementa(int cual) {
        contadores[cual]++;
        cv.actualiza(contadores);

    }

    public int[] getContadores() {
        return contadores;
    }
}
