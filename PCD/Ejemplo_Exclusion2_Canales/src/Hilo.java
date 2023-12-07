
import java.util.Random;
import org.jcsp.lang.One2OneChannel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pedro
 */
public class Hilo extends Thread {

    private final int id;
    protected final Random rng = new Random();
    private final One2OneChannel entrar;
    private final One2OneChannel salir;

    public Hilo(One2OneChannel entrar, One2OneChannel salir, int i) {
        this.id = i;
        this.entrar = entrar;
        this.salir = salir;
        rng.setSeed(System.currentTimeMillis() + id);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {

            try {
                System.out.println("Llega el hilo " + id);
                entrar.out().write(id);
                System.out.println("                     ---> Hilo " + id + " tiene permiso ");
                Thread.sleep(1000 * (rng.nextInt(4) + 1));
                System.out.println("            <-- Termina el hilo " + id);
                salir.out().write(id);

            } catch (InterruptedException e) {
                System.out.println("Excepcion " + e.getMessage());
            }
        }
    }
}
