
package UsaPila;

import java.util.Random;
/**
 *
 * @author rafaa
 */
public class Productor extends Thread {
    private final PilaLenta lapila;
    
    public Productor(PilaLenta p){
        lapila = p;
    }
    
    public void producir() {
        Random rdm = new Random();
        for (int i = 0; i < 10; i++) {
            try {
                lapila.Apila((int)(rdm.nextDouble()*100));
            } catch (Exception ex) {
                System.out.println("la pila no se ha podido rellenar" + ex.getMessage());
            }
        }
    }
    @Override
    public void run(){
        producir();
    }
}
