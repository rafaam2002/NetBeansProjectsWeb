/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IRemoto;



import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author rafaa
 */
public interface ISuperMercado extends Remote {

    public int getIdCliente() throws RemoteException;

    public void pagarEfectivo(int quien) throws InterruptedException, RemoteException;

    public void pagarTarjeta(int quien) throws InterruptedException, RemoteException;

    public void salir(int quien, char tipo) throws RemoteException;

}
