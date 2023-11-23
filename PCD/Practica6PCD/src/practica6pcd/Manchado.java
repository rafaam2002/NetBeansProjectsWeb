/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica6pcd;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto1.CanvasCongreso;

/**
 *
 * @author rafaa
 */
public class Manchado implements Runnable {

    private Semaphore salaLeche;
    private Semaphore salaCafe;
    private Semaphore leche;
    private Semaphore cafe;
    private Semaphore papelera;
    private final Random rdm;
    private final String name;
    private final CanvasCongreso cv;

    public Manchado(Semaphore salaCafe, Semaphore salaLeche, Semaphore leche, Semaphore cafe, Semaphore papelera, String name, CanvasCongreso cv) {
        this.salaCafe = salaCafe;
        this.salaLeche = salaLeche;
        this.cafe = cafe;
        this.leche = leche;
        this.papelera = papelera;
        this.name = name;
        rdm = new Random(123);
        this.cv = cv;
    }

    @Override
    public void run() {
        try {
            Thread.currentThread().setName(name);
            int id = (int) Thread.currentThread().getId();
            cv.encolacafe((int) id, 'M', 2, 1);
            salaCafe.acquire();
            cv.fincolacafe(id, 'M', 2, 1);
            cv.ensalacafe(id, 'M', 2, 1);
            System.out.println(Thread.currentThread().getName() + " ha entrado en la sala de cafe");
            cafe.acquire();
            cv.ensalacafe(id, 'M', 2, 0);
            System.out.println(Thread.currentThread().getName() + " se ha servido cafe");
            salaCafe.release();
            cv.finsalacafe(id, 'M', 2, 0);
            System.out.println(Thread.currentThread().getName() + " ha abandonado en la sala de cafe");
            cv.encolaleche(id, 'M', 2, 0);
            salaLeche.acquire();  //primero adquiero la siguiente sala para no quedarme fuera de las dos
            cv.fincolaleche(id, 'M', 2, 0);
            cv.ensalaleche(id, 'M', 2, 0);
            System.out.println(Thread.currentThread().getName() + " ha entrado en la sala de leche");
            leche.acquire();
            cv.ensalaleche(id, 'M', 1, 0);
            System.out.println(Thread.currentThread().getName() + " se ha servido uno de leche");
            leche.acquire();
            cv.ensalaleche(id, 'M', 0, 0);
            System.out.println(Thread.currentThread().getName() + " se ha servido dos de leche");
            salaLeche.release();
            cv.finsalaleche(id, 'M', 0, 0);
            System.out.println(Thread.currentThread().getName() + " ha abandonado la sala de leche");
            try {
                cv.ensalon(id, 'M', 0, 0);
                sleep(1000 * rdm.nextInt(1, 4));
            } catch (InterruptedException ex) {
                System.out.println("Error en el primer sleep +" + ex.getMessage());
            }
            cv.finsalon(id, 'M', 0, 0);
            cv.encolapapelera(id, 'M', 0, 0);
            papelera.acquire();
            cv.fincolapapelera(id, 'M', 0, 0);
            cv.enpapelera(id, 'M', 0, 0);
            System.out.println(Thread.currentThread().getName() + " ha ido a la papelera");
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Error en el segundo sleep +" + ex.getMessage());
            }
            papelera.release();
            cv.finpapelera(id, 'M', 0, 0);
            System.out.println(Thread.currentThread().getName() + " ha abandonado la papelara");
        } catch (InterruptedException ex) {
            System.out.println("Error en alguna operacion del semaforo +" + ex.getMessage());
        }
    }

}
