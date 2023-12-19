/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.jcsp.lang.Alternative;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannel;

/**
 *
 * @author rafaa
 */
public class Piscina extends Thread {

    int plazas = 5;
    int adultosDentro = 0;
    int adultosEsperando = 0;
    final Any2OneChannel entraNinio;
    final Any2OneChannel entraAdulto;
    final Any2OneChannel saleNinio;
    final Any2OneChannel saleAdulto;
    final One2OneChannel[] permiso;
//    ReentrantLock mutex = new ReentrantLock();
//    Condition colaChicas = mutex.newCondition();
//    Condition colaGrandes = mutex.newCondition();

    public Piscina(Any2OneChannel entraNinio, Any2OneChannel entraAdulto,
            Any2OneChannel saleNinio, Any2OneChannel saleAdulto, One2OneChannel[] permiso) {
        this.entraNinio = entraNinio;
        this.entraAdulto = entraAdulto;
        this.saleNinio = saleNinio;
        this.saleAdulto = saleAdulto;
        this.permiso = permiso;
    }

    @Override
    public void run() {
        int id;
        Guard[] guardas_or = new Guard[4];
        guardas_or[0] = entraNinio.in();
        guardas_or[1] = entraAdulto.in();
        guardas_or[2] = saleNinio.in();
        guardas_or[3] = saleAdulto.in();

        final boolean[] preconditions = new boolean[4];

        Alternative selector = new Alternative(guardas_or);

        while (true) {

            preconditions[0] = !(adultosEsperando > 0 && plazas < 2);
            preconditions[1] = !(plazas < 1);
            preconditions[2] = true;
            preconditions[3] = true;

            int index = (int) selector.select(preconditions);

            switch (index) {
                case 0 -> {
                    id = (int) entraNinio.in().read();
                    permiso[id].out().write(id);
                    plazas -= 2;
                }
                case 1 -> {
                    id = (int) entraAdulto.in().read();
                    permiso[id].out().write(id);
                    plazas -= 1;
                    adultosDentro++;
                }
                case 2 -> {
                    saleNinio.in().read();
                    plazas += 2;
                }
                case 3 -> {
                    saleAdulto.in().read();
                    plazas +=1;
                    adultosDentro--;
                }
            }

        }

    }

//    public synchronized void entraAdulto() throws InterruptedException {
//        adultosEsperando++;
//        while (plazas < 1) {
//            wait();
//        }
//        adultosDentro++;
//        adultosEsperando--;
//        plazas--;
//    }
//
//    public synchronized boolean entraNinio() throws InterruptedException {
//        if (adultosDentro == 0) {
//            return false;
//        } else {
//            while (adultosEsperando > 0 || plazas < 2) {
//                wait();
//                if (adultosDentro == 0) {
//                    return false;
//                }
//            }
//            plazas -= 2;
//            return true;
//        }
//    }
//
//    public synchronized void saleAdulto() throws InterruptedException {
//        plazas++;
//        adultosDentro--;
//        notifyAll();
//
//    }
//
//    public synchronized void saleNinio() throws InterruptedException {
//        plazas+= 2;
//        notifyAll();
//
//    }
}
