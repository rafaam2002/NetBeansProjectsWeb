/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.One2OneChannel;

/**
 *
 * @author rafaa
 */
public class Ninio extends Thread {

    private final int id;
    private final Random random;
//    private final Piscina pk;
    final Any2OneChannel entraNinio;
    final Any2OneChannel saleNinio;
    final One2OneChannel permiso;

    public Ninio(int id, Any2OneChannel entraNinio,
            Any2OneChannel saleNinio, One2OneChannel permiso) {
        this.id = id;
        random = new Random();
        this.entraNinio = entraNinio;
        this.saleNinio = saleNinio;
        this.permiso = permiso;
    }

    @Override
    public void run() {
        int tiempo = random.nextInt(3) + 2;
        System.out.println("Inicia Ninio " + id);
        try {
            entraNinio.out().write(id);
            permiso.in().read();
            sleep(tiempo * 1000);
            saleNinio.out().write(id);
            System.out.println("Ninio " + id + " finaliza");
//            System.out.println("Ninio " + id + " se va ya que no habia ningun adulto en la piscina");
//            System.out.println("Ninio " + id + " termina");
        } catch (InterruptedException ex) {
            Logger.getLogger(Ninio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
