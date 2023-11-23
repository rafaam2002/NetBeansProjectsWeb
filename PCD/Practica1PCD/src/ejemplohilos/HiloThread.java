/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplohilos;

/**
 *
 * @author rafaa
 */
public class HiloThread extends Thread{
    private Sumador s;
    
    public HiloThread (Sumador s){
        this.s = s;
    }
    
    @Override
    public void run(){
        System.out.println("soy" + getName()+ "su id es " + getId());
        for (int i = 0; i < 10; i++) {
            s.incrementa();
        }
    }
}
