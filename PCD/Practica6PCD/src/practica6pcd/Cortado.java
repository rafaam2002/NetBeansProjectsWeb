/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica6pcd;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto1.CanvasCongreso;

/**
 *
 * @author rafaa
 */
public class Cortado extends Thread {

    private final Semaphore salaLeche;
    private final Semaphore salaCafe;
    private final Semaphore leche;
    private final Semaphore cafe;
    private final Semaphore papelera;
    private final Random rdm;
    private final CanvasCongreso cv;

    public Cortado(Semaphore salaCafe, Semaphore salaLeche, Semaphore leche, Semaphore cafe, Semaphore papelera, String name, CanvasCongreso cv) {
        this.salaCafe = salaCafe;
        this.salaLeche = salaLeche;
        this.cafe = cafe;
        this.leche = leche;
        this.papelera = papelera;
        setName(name);
        rdm = new Random(1234);
        this.cv = cv;
    }

    @Override
    public void run() {
        try {
            cv.encolaleche((int) getId(), 'C', 1, 2);
            salaLeche.acquire();
            cv.fincolaleche((int) getId(), 'C', 1, 2);
            cv.ensalaleche((int) getId(), 'C', 1, 2);
            System.out.println(getName() + " ha entrado en la sala de leche");
            leche.acquire();
            cv.ensalaleche((int) getId(), 'C', 0, 2);
            System.out.println(getName() + " se ha servido leche");
            salaLeche.release();
            cv.finsalaleche((int) getId(), 'C', 0, 2);
            System.out.println(getName() + " ha aban donadola sala de leche");
            cv.encolacafe((int) getId(), 'C', 0, 2);
            salaCafe.acquire();
            cv.fincolacafe((int) getId(), 'C', 0, 2);
            cv.ensalacafe((int) getId(), 'C', 0, 2);
            System.out.println(getName() + " ha entrado en la sala de cafe");
            cafe.acquire();
            cv.ensalacafe((int) getId(), 'C', 0, 1);
            System.out.println(getName() + " se ha servido uno de cafe");
            cafe.acquire();
            cv.ensalacafe((int) getId(), 'C', 0, 0);
            System.out.println(getName() + " se ha servido 2 de cafe");
            salaCafe.release();
            cv.finsalacafe((int) getId(), 'C', 0, 0);
            System.out.println(getName() + " ha abandonado la sala de cafe");
            try {
                cv.ensalon((int) getId(), 'C', 0, 0);
                sleep(1000 * rdm.nextInt(1, 4));
            } catch (InterruptedException ex) {
                System.out.println("Error en el primer sleep +" + ex.getMessage());
            }
            cv.finsalon((int) getId(), 'C', 0, 0);
            cv.encolapapelera((int) getId(), 'C', 0, 0);
            papelera.acquire();
            cv.fincolapapelera((int) getId(), 'C', 0, 0);
            cv.enpapelera((int) getId(), 'C', 0, 0);
//            System.out.println(getName() + " ha ido a la papelera");
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Error en el segundo sleep +" + ex.getMessage());
            }
            papelera.release();
            cv.finpapelera((int) getId(), 'C', 0, 0);
            System.out.println(getName() + " ha abandonado la papelera");

        } catch (InterruptedException ex) {
            System.out.println("Error en alguna operacion del semaforo +" + ex.getMessage());
        }
    }

}
