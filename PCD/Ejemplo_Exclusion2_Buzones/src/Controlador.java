
import org.jcsp.lang.Alternative;
import org.jcsp.lang.Any2OneChannel;
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

    Any2OneChannel entrar, salir;
    One2OneChannel[] permiso;

    Controlador(Any2OneChannel entrar, Any2OneChannel salir, One2OneChannel[] permiso) {
        this.entrar = entrar;
        this.salir = salir;
        this.permiso = permiso;

    }

    @Override
    public void run() {

        int dentro = 0;
        int id;
        final Guard[] guardas_or = new Guard[2];
        guardas_or[0] = entrar.in();
        guardas_or[1] = salir.in();

        final boolean[] preCondition = new boolean[guardas_or.length];

        final Alternative selector = new Alternative(guardas_or);

        while (true) {

            preCondition[0] = dentro < 2;
            preCondition[1] = true;
            //System.out.println("Entraleer " + preCondition[0] + "  Entraescriir " + preCondition[1]);
            int index = selector.select(preCondition);
            switch (index) {
                case 0:
                    //System.out.println("Entra en leer");
                    id = (int) entrar.in().read();
                    dentro++;
                    permiso[id].out().write(id);
                    break;
                case 1:
                    //System.out.println("Sale");
                    id = (int) salir.in().read();
                    dentro--;
                    break;
                default:
                    System.out.println("Error");

            }

        }

    }
}
