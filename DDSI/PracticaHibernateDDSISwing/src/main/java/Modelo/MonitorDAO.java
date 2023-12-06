/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;

/**
 *
 * @author rafaa
 */
public class MonitorDAO {

    public ArrayList<Monitor> listaMonitores(Session session) throws Exception {

        Query consulta = session.createQuery("SELECT m FROM Monitor m", Monitor.class);
        return (ArrayList<Monitor>) consulta.list();
    }

    public ArrayList<Monitor> listMonitoresSortByNumMonitor(Session session) throws Exception {
        Query consulta = session.createQuery("SELECT m FROM Monitor m ORDER BY m.codMonitor ASC");
        return (ArrayList<Monitor>)consulta.getResultList();
    }

    public String DevolverUltimoCodigo(Session session) throws Exception {
        Query consulta = session.createQuery("Select MAX(m.codMonitor) from Monitor m");
        return (String) consulta.getSingleResult();
    }

    public void insertarActualizarMonitor(Session session, Monitor monitor) throws Exception {
        session.saveOrUpdate(monitor);
    }

    public void eliminarMonitor(Session session, String codMonitor) throws Exception {
        Query consulta = session.createNamedQuery("Monitor.findByCodMonitor", Monitor.class).setParameter("codMonitor", codMonitor);
        session.delete((Monitor) consulta.getSingleResult());
    }
}
