
import org.jcsp.lang.Alternative;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pedro
 */
public class Controlador extends Thread {

    One2OneChannel[] entrar, salir;

    Controlador(One2OneChannel[] entrar, One2OneChannel[] salir) {
        this.entrar = entrar;
        this.salir = salir;

    }

    @Override
    public void run() {
        int dentro = 0;

        int id;
        final Guard[] guardas_or = new Guard[entrar.length + salir.length];

        for (int i = 0; i < entrar.length; i++) {
            guardas_or[i] = entrar[i].in();
        }
        for (int i = 0; i < salir.length; i++) {
            guardas_or[entrar.length + i] = salir[i].in();
        }

        final boolean[] preCondition = new boolean[entrar.length + salir.length];

        final Alternative selector = new Alternative(guardas_or);

        while (true) {
            for (int i = 0; i < entrar.length; i++) {
                preCondition[i] = dentro < 2;
            }
            for (int i = 0; i < salir.length; i++) {
                preCondition[entrar.length + i] = true;
            }
            int index = selector.select(preCondition);
            switch (index) {
                case 0, 1, 2, 3, 4 -> {
                    //System.out.println("Entra switch "+index);
                    id = (int) entrar[index].in().read();
                    dentro++;
                }
                case 5, 6, 7, 8, 9 -> {
                    //System.out.println("Sale switch "+index);
                    id = (int) salir[index - entrar.length].in().read();
                    dentro--;
                }
                default ->
                    System.out.println("Error");
            }

        }

    }
}
