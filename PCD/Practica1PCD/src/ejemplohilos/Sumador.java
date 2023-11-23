/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplohilos;

/**
 *
 * @author rafaa
 */
public class Sumador {
    private volatile int contador; //volatile no se optimiza
    
    public Sumador (int v){
        contador = v;
    }
    
    public synchronized void incrementa(){ //para que compartan memoria no usen las caches de nivel bajo
        contador++;
    }
    public int getContador(){
        return contador;
    }
}
