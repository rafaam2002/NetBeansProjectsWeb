/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clienteefectivo;

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
public class ClienteEfectivo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try {
            
            Registry Registro = LocateRegistry.getRegistry("localhost", 2015);

            ISuperMercado objrem = (ISuperMercado) Naming.lookup("rmi://localhost:2015/superremoto");

            irAlSuper(objrem);

        } catch (NotBoundException | MalformedURLException | RemoteException | InterruptedException ex) {
            Logger.getLogger(ClienteEfectivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      private static void irAlSuper(ISuperMercado objrem) throws RemoteException, InterruptedException {
          var rdm = new Random();
          int sleepTime = rdm.nextInt(2000) + 3000;
          int id = objrem.getIdCliente();

          objrem.pagarEfectivo(id);
          
          sleep(sleepTime);
          
          objrem.salir(id,'E');
    }
    
}
