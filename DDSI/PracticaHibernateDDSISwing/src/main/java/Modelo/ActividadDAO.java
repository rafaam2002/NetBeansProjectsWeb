/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.VistaMensaje;
import Vista.VistaMonitor;
import Vista.VistaSocio;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author rafaa
 */
public class ActividadDAO {

    /**
     * Devuelve el monitor responsable asociado a una actividad específica.
     *
     * @param idActividad El ID único de la actividad que se desea consultar.
     * @param session La sesión de Hibernate a utilizar para la consulta.
     * @return El objeto Monitor que es responsable de la actividad, o null si
     * no se encuentra.
     */
    public Monitor getMonitorResponsable(String idActividad, Session session) {
        Query consulta = session.createNamedQuery("Actividad.findByIdActividad", Actividad.class);
        consulta.setParameter("idActividad", idActividad);
        Actividad a = (Actividad) consulta.getSingleResult();
        return a.getMonitorResponsable();
    }

    /**
     * Devuelve el conjunto de socios asociados a una actividad específica.
     * @param idActividad El ID único de la actividad que se desea consultar.
     * @param session La sesión de Hibernate a utilizar para la consulta.
     * @return Un conjunto (Set) de objetos Socio que están asociados a la
     * actividad, o un conjunto vacío si no se encuentra.
     */
    public Set<Socio> getSociosActividad(String idActividad, Session session) {
        Query consulta = session.createNamedQuery("Actividad.findByIdActividad", Actividad.class)
                .setParameter("idActividad", idActividad);

        Actividad a = (Actividad) consulta.getSingleResult();
        return a.getSocios();
    }

    /**
     * Obtiene la lista de todas las actividades almacenadas en la base de
     * datos.
     * @param session La sesión de Hibernate a utilizar para la consulta.
     * @return Una lista de objetos Actividad que representan todas las
     * actividades almacenadas, o una lista vacía si no hay actividades.
     */
    public List<Actividad> getActividades(Session session) {
        Query consulta = session.createNamedQuery("Actividad.findAll", Actividad.class);
        return consulta.getResultList();
    }
    
    public Actividad getActividad(Session session, String idActividad){
          Query consulta = session.createNamedQuery("Actividad.findByIdActividad", Actividad.class);
          consulta.setParameter("idActividad", idActividad);
        return (Actividad) consulta.getSingleResult();
    }

}
