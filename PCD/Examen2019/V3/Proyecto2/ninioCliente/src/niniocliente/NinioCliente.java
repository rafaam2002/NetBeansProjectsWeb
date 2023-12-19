/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package niniocliente;

import IRemoto.IPiscina;
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Locale;
import java.rmi.registry.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaa
 */
public class NinioCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here

            Registry Registro = LocateRegistry.getRegistry("localhost", 2015);

            IPiscina objrem = (IPiscina) Naming.lookup("rmi://localhost:2015/piscinaremoto");

            System.out.println("Voy a entrar");

            System.out.println(objrem.entraNinio());
            objrem.saleNinio();
            System.out.println("Salgo");

//            Naming.lookup("")
        } catch (RemoteException | NotBoundException | MalformedURLException | InterruptedException ex) {
            Logger.getLogger(NinioCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
