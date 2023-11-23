
package UsaPila;

/**
 *
 * @author rafaa
 */
public class Consumidor implements Runnable{
    private final PilaLenta lapila;
    
    public Consumidor(PilaLenta p){
        lapila = p;
    }
    
    public void consumir(){
        for (int i = 0; i < 10; i++) {
            try {
                lapila.Desapila();
            } catch (Exception ex) {
                System.out.println("la pila no se puede vaciar mas " + ex.getMessage());
            }
        }
    }

    @Override
    public void run() {
        consumir();
    }
}
