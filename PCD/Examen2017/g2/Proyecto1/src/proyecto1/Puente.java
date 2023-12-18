/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author rafaa
 */
public class Puente {

    int ninosEsperando = 0;
    int ninosDentro = 0;
    int adultosDentro = 0;
    
    public synchronized void entraNino() throws InterruptedException {
        ninosEsperando++;
        while(adultosDentro > 1 || ninosDentro > 2 || (ninosDentro == 1 && adultosDentro == 1)){
            wait();
        }
        ninosEsperando--;
        ninosDentro++;
    }

    public synchronized void entraAdulto() throws InterruptedException {
       while(ninosEsperando > 0 || adultosDentro > 1 || ninosDentro > 1){
           wait();
       }
       adultosDentro++;
    }

    public synchronized void saleNino() throws InterruptedException {
      ninosDentro--;
      notifyAll();
    }

    public synchronized void saleAdulto() {
      adultosDentro--;
      notifyAll();
    }

}
