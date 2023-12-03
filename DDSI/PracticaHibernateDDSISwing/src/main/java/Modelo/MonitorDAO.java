/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import org.hibernate.query.Query;
import org.hibernate.Session;

/**
 *
 * @author rafaa
 */
public class MonitorDAO {

    private  Session s;

    
    
    public ArrayList<Monitor> listaMonitores(Session session) throws Exception{
        s = session;
         Query consulta = s.createQuery("SELECT m FROM Monitor m", Monitor.class);
        return (ArrayList<Monitor>) consulta.list();
    }
    
     public String DevolverUltimoCodigo(Session session) throws Exception{
        Query consulta = session.createQuery("Select MAX(m.codMonitor) from Monitor m");
        return (String)consulta.getSingleResult();
    }
     
    public void insertarActualizarMonitor (Session session, Monitor monitor) throws Exception {
        session.saveOrUpdate(monitor);
    }
}
