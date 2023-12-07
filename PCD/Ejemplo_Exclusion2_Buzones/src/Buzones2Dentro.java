
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.Channel;
import org.jcsp.lang.One2OneChannel;
import org.jcsp.util.Buffer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author pedro
 */
public class Buzones2Dentro {

    public static void main(String args[]) throws InterruptedException {

        int N = 20;

        //Definimos los buzones
        Any2OneChannel entrar = Channel.any2one(new Buffer(N));
        Any2OneChannel salir = Channel.any2one(new Buffer(N));
        One2OneChannel permiso[] = new One2OneChannel[N];

        for (int i = 0; i < N; i++) {
            permiso[i] = Channel.one2one(new Buffer(1));
        }

        Thread hilos[] = new Thread[N];
        Controlador controler = new Controlador(entrar, salir, permiso);
        controler.start();
        for (int i = 0; i < N; i++) {
            hilos[i] = new Hilo(entrar, salir, permiso[i], i);
            hilos[i].start();
        }
        for (int i = 0; i < N; i++) {
            hilos[i].join();
        }

        System.exit(0);
    }

}
