package practica5pdc;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaa
 */
public class Rehabilita implements Runnable {

    private final Centro centro;
    private final Random rdm = new Random();
    private final String nombre;
    private final CanvasClinica cv;

    public Rehabilita(Centro centro, String nombre, CanvasClinica cv) {
        this.centro = centro;
        this.nombre = nombre;
        this.cv = cv;
    }
    

    @Override
    public void run() {
        Thread.currentThread().setName(nombre);
        try {
            cv.pintaEnCola(nombre);
            centro.EntraRehabilitacion();
//            System.out.println("El hilo " + Thread.currentThread().getName() + " pudo entrar en Rehabilitacion ");
        } catch (InterruptedException ex) {
            System.out.println("El hilo " + Thread.currentThread().getName() + " Rehabilita no pudo entrar en Rehabilitacion " + ex.getMessage());
        }
        try {
            cv.pintaEntraRehabilitacion(nombre);
            sleep(rdm.nextInt(2, 4)*1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Rehabilita.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            centro.entraVestuario();
            cv.pintaEntraVestuario(nombre);
//            System.out.println("El hilo " + Thread.currentThread().getName() + " pudo entrar en Vestuario ");
        } catch (InterruptedException ex) {
            System.out.println("El hilo " + Thread.currentThread().getName() + " Rehabilita no pudo entrar en Vestuario " + ex.getMessage());
        }
//        System.out.println("El hilo " + Thread.currentThread().getName() + " sale de Rehabilitacion ");
        centro.SaleRehabilitacion();
        try {
            sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Masaje.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.out.println("El hilo " + Thread.currentThread().getName() + " sale de Vestuario ");
        centro.saleVestuario();
        cv.pintaSaleVestuario();
    }

}
