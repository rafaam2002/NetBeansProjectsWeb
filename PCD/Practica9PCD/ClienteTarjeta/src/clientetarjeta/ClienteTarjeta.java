/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clientetarjeta;

import IRemoto.ISuperMercado;
import static java.lang.Thread.sleep;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaa
 */
public class ClienteTarjeta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
            Registry Registro = LocateRegistry.getRegistry("localhost", 2015);

            ISuperMercado objrem = (ISuperMercado) Naming.lookup("rmi://localhost:2015/superremoto");

            irAlSuper(objrem);
           
            
//            int cont = objrem.incrementa("Pedro", 5);
//            System.out.println("He dejado el contador en " + cont);

        } catch (NotBoundException | MalformedURLException | RemoteException | InterruptedException ex) {
            Logger.getLogger(ClienteTarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void irAlSuper(ISuperMercado objrem) throws RemoteException, InterruptedException {
          var rdm = new Random();
          int sleepTime = rdm.nextInt(2000) + 3000;
          int id = objrem.getIdCliente();

          objrem.pagarTarjeta(id);
          
          sleep(sleepTime);
          
          objrem.salir(id,'T');
//          
//        try {
//            System.out.println("El Hilo 1" + " intenta entrar en la caja");
//            cv.insertarClienteTarjeta(id);
//            supermercado.pagarTarjeta();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Tarjeta.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            System.out.println("El Hilo " + name + " en caja");
//            cv.eliminarClienteTarjeta(id);
//            cv.insertarClientePagandoTarjeta(id);
//            sleep(sleepTime);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Tarjeta.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        supermercado.salir('T');
//        System.out.println("El Hilo " + name + " sale de la caja");
//        cv.eliminarClientePagandoTarjeta(id);
//        return sleepTime / 1000;
    }

}
