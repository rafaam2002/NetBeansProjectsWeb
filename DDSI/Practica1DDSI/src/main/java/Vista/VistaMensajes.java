/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

/**
 *
 * @author rafaa
 */
public class VistaMensajes { //yo pondria los metodos de esta clase estaticos, ya que no tiene ningun parametro
    
    
    public static void mensajeConsola(String mensajePersonal, String mensajeExc){
        System.out.println("***************************************");
        System.out.println(mensajePersonal + "\n" + mensajeExc);
        System.out.println("***************************************");
    }
    public static void mensajeConsola(String mensaje){
        System.out.println("***************************************");
        System.out.println(mensaje);
        System.out.println("***************************************");
    }
}
