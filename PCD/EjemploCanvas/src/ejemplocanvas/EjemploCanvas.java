/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemplocanvas;

/**
 *
 * @author rafaa
 */
public class EjemploCanvas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Recurso r = new Recurso();

        for (int i = 0; i < 10; i++) {
            r.incrementa(1);
        }
        Sumador h1 = new Sumador(0, r);
        Sumador h2 = new Sumador(1, r);
        Sumador h3 = new Sumador(1, r);

        h1.start();
        h2.start();
        h3.start();

        h1.join();
        h2.join();
        h3.join();

        int resultado[] = r.getContadores();
        System.out.println("El contador 1 vale " + resultado[0]);
        System.out.println("El contador 2 vale " + resultado[1]);

    }

}
