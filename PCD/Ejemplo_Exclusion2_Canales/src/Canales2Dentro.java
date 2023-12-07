
import org.jcsp.lang.Channel;
import org.jcsp.lang.One2OneChannel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author pedro
 */
public class Canales2Dentro {

    public static void main(String args[]) throws InterruptedException {

        int N = 5;

        //Definimos los buzones
        One2OneChannel entrar[] = Channel.one2oneArray(N);
        One2OneChannel salir[] = Channel.one2oneArray(N);


        Thread hilos[] = new Thread[N];
        Controlador controler = new Controlador(entrar, salir);
        controler.start();
        for (int i = 0; i < N; i++) {
            hilos[i] = new Hilo(entrar[i], salir[i], i);
            hilos[i].start();
        }
        for (int i = 0; i < N; i++) {
            hilos[i].join();
        }

        System.exit(0);
    }

}
