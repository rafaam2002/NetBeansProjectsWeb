/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IRemoto;

import java.rmi.*;


/**
 *
 * @author rafaa
 */
public interface IPiscina extends Remote {

    public void entraAdulto() throws InterruptedException , RemoteException;

    public boolean entraNinio() throws InterruptedException, RemoteException;

    public void saleAdulto() throws InterruptedException, RemoteException;

    public void saleNinio() throws InterruptedException, RemoteException;

}
