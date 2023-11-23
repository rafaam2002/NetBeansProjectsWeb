/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.ArrayList;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author rafaa
 */
public class SocioDAO {

    private Transaction tr;

    public ArrayList<Socio> getSociosHQL(Session s) {
        tr = s.beginTransaction();

        Query consulta = s.createQuery("SELECT s FROM Socio s", Socio.class);
        tr.commit(); //el commit aqui??
        return (ArrayList<Socio>) consulta.list();


    }

    public ArrayList<Socio> getSociosSQL(Session s) {
        tr = s.beginTransaction();
        Query consulta = s.createNativeQuery("SELECT * FROM SOCIO s", Socio.class);
        tr.commit();
        return (ArrayList<Socio>) consulta.list();
    }

    public ArrayList<Socio> getSociosNamedQuery(Session s) {
        tr = s.beginTransaction();
        Query consulta = s.createNamedQuery("Socio.findAll", Socio.class);
        tr.commit();
        return (ArrayList<Socio>) consulta.list();
    }

    public ArrayList<Object[]> getNomTelSocios(Session s) {
        tr = s.beginTransaction();

        Query consulta = s.createQuery("SELECT s.nombre,s.telefono FROM Socio s");
        tr.commit();
        return (ArrayList<Object[]>) consulta.list();

      
    }

    public ArrayList<Object[]> getSociosCategoria(Session s, char c) {
        tr = s.beginTransaction();
        Query consulta = s.createQuery("SELECT s.nombre,s.categoria FROM Socio s where s.categoria = :categoria");
        consulta.setParameter("categoria", c);
        tr.commit();

        return (ArrayList<Object[]>) consulta.list();

    }

    public void insertarSocio(Session s, Socio socio) {
        tr = s.beginTransaction();
        Query consulta = s.createNamedQuery("Actividad.findByIdActividad",Actividad.class)
                .setParameter("idActividad", "AC01");
        
        
        var actividadNuevoSocio = (Actividad) consulta.getSingleResult();
        actividadNuevoSocio.altaSocio(socio);
        s.saveOrUpdate(actividadNuevoSocio);
        s.saveOrUpdate(socio);
        
        tr.commit();
    }
    
    public void eliminarSocio(Session s, String numSocio){
        tr = s.beginTransaction();
        Query consulta = s.createNamedQuery("Socio.findByNumeroSocio",Socio.class)
                .setParameter("numeroSocio", numSocio);
        var socio = (Socio) consulta.getSingleResult();
        for (Actividad actividad : socio.getActividades()) {
            actividad.bajaSocio(socio);
            s.saveOrUpdate(actividad);
        }
        s.delete(socio);
        tr.commit();
    }
    
    public void actualizarCategoria(Session s,String numSocio, char categoria){
        tr= s.beginTransaction();
          Query consulta = s.createNamedQuery("Socio.findByNumeroSocio",Socio.class)
                .setParameter("numeroSocio", numSocio);
          
          var socio = (Socio) consulta.getSingleResult();
          
          socio.setCategoria(categoria);
          
          s.saveOrUpdate(socio);
          tr.commit();
    }

    public void addActividad(Session s, String numSocio, String idActividad) {
         tr= s.beginTransaction();
          Query consultaSocio = s.createNamedQuery("Socio.findByNumeroSocio",Socio.class)
                .setParameter("numeroSocio", numSocio);
          Query consultaActividad = s.createNamedQuery("Actividad.findByIdActividad",Actividad.class)
                .setParameter("idActividad",idActividad);
          
          var socio =(Socio) consultaSocio.getSingleResult();
          var actividad = (Actividad) consultaActividad.getSingleResult();
          actividad.altaSocio(socio);
          s.saveOrUpdate(socio);
          s.saveOrUpdate(actividad);
          tr.commit();
    }

    public void removeActividad(Session s, String numSocio, String idActividad) {
         tr= s.beginTransaction();
          Query consultaSocio = s.createNamedQuery("Socio.findByNumeroSocio",Socio.class)
                .setParameter("numeroSocio", numSocio);
          Query consultaActividad = s.createNamedQuery("Actividad.findByIdActividad",Actividad.class)
                .setParameter("idActividad",idActividad);
          
          var socio =(Socio) consultaSocio.getSingleResult();
          var actividad = (Actividad) consultaActividad.getSingleResult();
          actividad.bajaSocio(socio);
          s.saveOrUpdate(socio);
          s.saveOrUpdate(actividad);
          tr.commit();
    }

    public Set<Actividad> getActividades(Session s, String numSocio) {
         tr= s.beginTransaction();
          Query consulta = s.createNamedQuery("Socio.findByNumeroSocio",Socio.class)
                .setParameter("numeroSocio", numSocio);
           tr.commit();
           var socio = (Socio)consulta.getSingleResult();
          return (Set<Actividad>)socio.getActividades();
    }
}
