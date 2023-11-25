/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author rafaa
 */
public class MonitorDAO {

    private  Session s;

    
    
    public ArrayList<Monitor> listaMonitores(Session session){
        s = session;
         Query consulta = s.createQuery("SELECT m FROM Monitor m", Monitor.class);
        return (ArrayList<Monitor>) consulta.list();
    }
}
