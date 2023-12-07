
import java.util.Random;
import org.jcsp.lang.Any2OneChannel;
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
    private final Any2OneChannel entrar;
    private final Any2OneChannel salir;
    private final One2OneChannel permiso;

    public Hilo(Any2OneChannel entrar, Any2OneChannel salir, One2OneChannel permiso, int i) {
        this.id = i;
        this.entrar = entrar;
        this.salir = salir;
        this.permiso = permiso;
        rng.setSeed(System.currentTimeMillis() + id);
    }

    @Override
    public void run() {
        try {
            System.out.println("Llega el hilo " + id);
            entrar.out().write(id);
            int lee = (int) permiso.in().read();
            System.out.println("Lector " + id + " tiene permiso " + lee);
            Thread.sleep(1000 * (rng.nextInt(4) + 1));
            System.out.println("Termina el hilo " + id);
            salir.out().write(id);

        } catch (InterruptedException e) {
            System.out.println("Excepcion " + e.getMessage());
        }
    }
}
