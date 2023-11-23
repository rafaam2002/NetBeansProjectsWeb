package UsaPila;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Le he metido el volatile a cima y a numelementos de Pilalenta. Pero el
 * synchronized no se donde ponerlo exactamente, en producir y consumir? o en
 * apilar y desapilar (para mi tiene mas sentido la segunda) pero de ninguna de
 * las dos formas funciona. Y no entiend como deberia funcionar ya que si se
 * lanzan hilos que producen e hilos que consumen a la vez, si se ejecuta en
 * primer lugar uno que consume la pila esta vacia y da excepcion, que creo que
 * es lo que me pasa (aunque es raro que me pase siempre)
 *
 * @author rafaa
 */
public class UsaPila {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PilaLenta pila = new PilaLenta(20);

        Productor p1 = new Productor(pila); //estamos usando la misma pila para todos
        Productor p2 = new Productor(pila);
        Consumidor consumidor1 = new Consumidor(pila);
        Thread c1 = new Thread(consumidor1);
        Thread c2 = new Thread(consumidor1);

        p1.start();
        p2.start();
        c1.start();
        c2.start();

        try {
            p1.join();
        } catch (InterruptedException ex) {
            System.out.println("El hilo p1 no puedo hacer join" + ex.getMessage());
        }
        try {
            p2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(UsaPila.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            c1.join();
        } catch (InterruptedException ex) {
            System.out.println("El hilo c1 no puedo hacer join" + ex.getMessage());

        }
        try {
            c2.join();
        } catch (InterruptedException ex) {
            System.out.println("El hilo c2 no puedo hacer join" + ex.getMessage());
        }

        /*
        p2.start();
        c1.start();
        c2.start();
        

        try {
            p1.join();
        } catch (InterruptedException ex) {
            System.out.println("No se ha podido ejecutar el hilo p1" + ex.getMessage());
        }
        try {
            p2.join();
        } catch (InterruptedException ex) {
            System.out.println("No se ha podido ejecutar el hilo p2" + ex.getMessage());
        }
        try {
            c1.join();
        } catch (InterruptedException ex) {
            System.out.println("No se ha podido ejecutar el hilo c1" + ex.getMessage());
        }
        try {
            c2.join();
        } catch (InterruptedException ex) {
            System.out.println("No se ha podido ejecutar el hilo c2" + ex.getMessage());            
        }
         */
        pila.muestraPila();

        //Probar P1.start P1.join C1.start C2.start c1.join c2.join:
        // si la pila sale vacia esque esta bien, no te fijes en los mensajes de pila vacia
        // que logicamente tiene que dar el doble, pero lo importante esque no se quede ningun elemento
        //  en la pila ya que sin el syncrhorized en algun momento los dos hilos leeran la misma cima
    }
}
