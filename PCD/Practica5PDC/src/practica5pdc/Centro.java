package practica5pdc;

/**
 *
 * @author rafaa
 */
public class Centro {

    private volatile boolean MLibre = true;
    private volatile boolean RLibre = true;
    private boolean VLibre = true;
    private int REsperando = 0;

  

    public synchronized char EntraMasaje() throws InterruptedException {

        while (!MLibre) {
            if (REsperando == 0 && RLibre) {
                EntraRehabilitacion();
                return 'r';
            } else {
                System.out.println(Thread.currentThread().getName() + " Se espera para Masajista");
                wait();
            }
        }
        MLibre = false;
        System.out.println(Thread.currentThread().getName() + " Entra en Masaje");
        return 'm';
//        if (!MLibre && REsperando == 0 && RLibre) {
//            EntraRehabilitacion();
//            return 'r';
//        } else {
//            while (!MLibre) {
//                System.out.println(Thread.currentThread().getName() + " Se espera para Masajista");
//                wait();
//            }
//            MLibre = false;
//            System.out.println(Thread.currentThread().getName() + " Entra en Masaje");
//            return 'm';
//        }

    }

    public synchronized void EntraRehabilitacion() throws InterruptedException {
        REsperando++;
        while (!RLibre) {
            System.out.println(Thread.currentThread().getName() + " Se espera para Rehabilitacion");
            wait();
        }
        RLibre = false;
        System.out.println(Thread.currentThread().getName() + " Entra en Rehabilitacion");
    }

    public synchronized void saleMasaje() {
        MLibre = true;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " Sale de Masaje");
    }

    public synchronized void SaleRehabilitacion() {
        if (REsperando > 0) {
            REsperando--;
        }
        RLibre = true;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " Sale de Rehabilitacion");
    }

    public synchronized void entraVestuario() throws InterruptedException { // en cuanto consiga entrar e ne lvestuario dejara la sala libre
        while (!VLibre) {
            System.out.println(Thread.currentThread().getName() + " Se espera para Vestuario");
            wait();
        }
        VLibre = false;
        System.out.println(Thread.currentThread().getName() + " Entra en Vestuario");
    }

    public synchronized void saleVestuario() { //termina
        VLibre = true;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " Sale de Vestuario");
        
    }

//    public synchronized void Termina() {
//        this.notifyAll();
//        //creo que funicona con notify tambien
//    }
}
