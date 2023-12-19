/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.One2OneChannel;

/**
 *
 * @author rafaa
 */
public class Adulto implements Runnable {

    private final int id;
    private final Random random;
//    private final Piscina pk;
    Any2OneChannel entraAdulto;
    Any2OneChannel saleAdulto;
    One2OneChannel permiso;

    public Adulto(int id, Any2OneChannel entraAdulto,
            Any2OneChannel saleAdulto, One2OneChannel permiso) {
        this.id = id;
        random = new Random();
        this.entraAdulto = entraAdulto;
        this.saleAdulto = saleAdulto;
        this.permiso = permiso;
    }

    @Override
    public void run() {
        int tiempo = random.nextInt(4) + 3;
        System.out.println("Inicia Adulto " + id);
        try {
            entraAdulto.out().write(id);
            permiso.in().read();
            sleep(tiempo * 1000);
            saleAdulto.out().write(id);
            System.out.println("Adulto " + id + " finaliza");

        } catch (InterruptedException ex) {
            Logger.getLogger(Adulto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
