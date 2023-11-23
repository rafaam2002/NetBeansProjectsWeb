/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.VistaMensaje;
import Vista.VistaMonitor;
import Vista.VistaSocio;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author rafaa
 */
public class ActividadDAO {

    private final Session session;
    private Transaction tr;

    public ActividadDAO(Session session) {
        this.session = session;
    }

    public Monitor getMonitorResponsable(String idActividad) {
        Query consulta = session.createNamedQuery("Actividad.findByIdActividad", Actividad.class);
        consulta.setParameter("idActividad", idActividad);
        Actividad a = (Actividad) consulta.getSingleResult();
        return a.getMonitorResponsable();   
    }

    public Set<Socio> getSociosActividad(String idActividad) {
        Query consulta = session.createNamedQuery("Actividad.findByIdActividad",Actividad.class)
            .setParameter("idActividad",idActividad);
        
        Actividad a = (Actividad) consulta.getSingleResult();
        return a.getSocios();
    }

}
