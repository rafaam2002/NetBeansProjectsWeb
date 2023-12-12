/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica7pcd;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.Any2OneChannel;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannel;

/**
 *
 * @author rafaa
 */
public class Comedero extends Thread {

    Any2OneChannel entraPerro, salePerro, entraGato, saleGato;
    One2OneChannel[] permiso;

    public Comedero(Any2OneChannel entraPerro, Any2OneChannel entraGato, Any2OneChannel salePerro,
            Any2OneChannel saleGato, One2OneChannel[] permiso) {

        this.entraPerro = entraPerro;
        this.entraGato = entraGato;
        this.salePerro = salePerro;
        this.saleGato = saleGato;
        this.permiso = permiso;
    }

    @Override
    public void run() {

        int numPerros = 0;
        int numGatos = 0;
        int id;

        //Guard son los canales que esta escuchando
        final Guard[] guardas_or = new Guard[4];
        guardas_or[0] = entraPerro.in();
        guardas_or[1] = entraGato.in();
        guardas_or[2] = salePerro.in();
        guardas_or[3] = saleGato.in();

        final boolean[] preCondition = new boolean[guardas_or.length];

        final Alternative selector = new Alternative(guardas_or);

        while (true) {
            
//            preCondition[0] = true;
//            preCondition[1] = true;
                                                
            preCondition[0] = numGatos + numPerros < 4 && ((numGatos <= 2 && numPerros <= 1) || numGatos == 0);
            preCondition[1] = numPerros + numGatos < 4 && ((numPerros <= 2 && numGatos <= 1) || numPerros == 0);
            preCondition[2] = true;
            preCondition[3] = true;
            System.out.println("Entraleer " + preCondition[0] + "  Entraescriir " + preCondition[1] + " " + (numPerros+numGatos));
            int index = selector.select(preCondition);
            switch (index) {
                case 0 -> {
                    //System.out.println("Entra en leer");
                    id = (int) entraPerro.in().read();
                    numPerros++;
                    System.err.println("Numero de perros: " + numPerros);
                    permiso[id].out().write(id);
                }

                case 1 -> {
                    //System.out.println("Entra en leer");
                    id = (int) entraGato.in().read();
                    numGatos++;
                    System.err.println("numGatos: " + numGatos );
                    permiso[id].out().write(id);
                }

                case 2 -> {
                    //System.out.println("Sale");
                    id = (int) salePerro.in().read();
                    numPerros--;
                }

                case 3 -> {
                    id = (int) saleGato.in().read();
                    numGatos--;
                }

                default ->
                    System.out.println("Error");

            }

        }

    }

}
