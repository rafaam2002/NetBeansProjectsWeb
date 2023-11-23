/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplohilos;

/**
 *
 * @author rafaa
 */
public class HiloRunnable implements Runnable {

    private Sumador s;

    public HiloRunnable(Sumador s) {
        this.s = s;
    }

    @Override
    public void run() {
        Thread yo = Thread.currentThread();
        System.out.println("soy: " + Thread.currentThread().getName() + "id:" + yo.getId());
        for (int i = 0; i < 10; i++) {
            s.incrementa();
        }
    }
}
