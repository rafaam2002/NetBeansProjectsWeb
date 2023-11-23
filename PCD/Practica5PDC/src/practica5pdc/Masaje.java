package practica5pdc;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaa
 */
public class Masaje extends Thread {

    private final Centro centro;
    private final Random rdm = new Random();
    private final CanvasClinica cv;

    public Masaje(Centro centro, String nombre, CanvasClinica cv) {
        this.centro = centro;
        setName(nombre);
        this.cv = cv;
    }

    @Override
    public void run() {
        char donde = 0;
        try {
            cv.pintaEnCola(getName());
            donde = centro.EntraMasaje();
//            System.out.println("El hilo " + getName() + " pudo entrar en Masaje ");

        } catch (InterruptedException ex) {
            System.out.println("El hilo " + getName() + " no pudo entrar en Masaje " + ex.getMessage());
        }
        if( donde == 'm'){
            cv.pintaEntraMasaje(getName());
        }
        else cv.pintaEntraRehabilitacion(getName());
        try {
            sleep(rdm.nextInt(2, 4)*1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Masaje.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            centro.entraVestuario();
            cv.pintaEntraVestuario(getName());
//            System.out.println("El hilo " + getName() + " pudo entrar en Vestuario");
        } catch (InterruptedException ex) {
            System.out.println("El hilo " + getName() + " no pudo entrar en Vestuario " + ex.getMessage());
        }
        if( donde == 'm')
            centro.saleMasaje();
        else centro.SaleRehabilitacion();    
//        System.out.println("El hilo " + getName() + " Sale de Masaje ");
        
        try {
            sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Masaje.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.out.println("El hilo " + getName() + "Sale de Vestuario ");
        centro.saleVestuario();
        cv.pintaSaleVestuario();
    }
}
