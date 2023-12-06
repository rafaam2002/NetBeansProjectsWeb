/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.Set;
import org.hibernate.HibernateException;

//import org.hibernate.Query;
import org.hibernate.query.Query;
import org.hibernate.Session;

/**
 *
 * @author rafaa
 */
public class SocioDAO {


    public ArrayList<Socio> getSociosHQL(Session s) throws Exception{
        Query consulta = s.createQuery("SELECT s FROM Socio s", Socio.class);
        return (ArrayList<Socio>) consulta.list();
    }

    public ArrayList<Socio> getSociosSQL(Session s) throws Exception{
        Query consulta = s.createNativeQuery("SELECT * FROM SOCIO s", Socio.class);
        return (ArrayList<Socio>) consulta.list();
    }

    public ArrayList<Socio> getSociosNamedQuery(Session s) throws Exception{
        Query consulta = s.createNamedQuery("Socio.findAll", Socio.class);
        return (ArrayList<Socio>) consulta.list();
    }

    public ArrayList<Object[]> getNomTelSocios(Session s) throws Exception{

        Query consulta = s.createQuery("SELECT s.nombre,s.telefono FROM Socio s");
        return (ArrayList<Object[]>) consulta.list();

    }
    
    public ArrayList<Socio> getSociosSortByNumSocio (Session s) throws Exception {
        Query consulta = s.createQuery("SELECT s FROM Socio s ORDER BY s.numeroSocio ASC");
        return (ArrayList<Socio>) consulta.getResultList();
                
    }

    public ArrayList<Object[]> getSociosCategoria(Session s, char c) throws Exception{
        Query consulta = s.createQuery("SELECT s.nombre,s.categoria FROM Socio s where s.categoria = :categoria");
        consulta.setParameter("categoria", c);
        return (ArrayList<Object[]>) consulta.list();
    }

    public void insertarSocio(Session s, Socio socio) throws Exception {
        s.saveOrUpdate(socio);
    }

    public void eliminarSocio(Session s, String numSocio) throws Exception {
        Query consulta = s.createNamedQuery("Socio.findByNumeroSocio", Socio.class)
                .setParameter("numeroSocio", numSocio);
        var socio = (Socio) consulta.getSingleResult();
        s.delete(socio);
    }

    public void actualizarCategoria(Session s, String numSocio, char categoria) throws Exception{
        Query consulta = s.createNamedQuery("Socio.findByNumeroSocio", Socio.class)
                .setParameter("numeroSocio", numSocio);

        var socio = (Socio) consulta.getSingleResult();

        socio.setCategoria(categoria);

        s.saveOrUpdate(socio);
    }

    public void addActividad(Session s, String numSocio, String idActividad) throws Exception{
        Query consultaSocio = s.createNamedQuery("Socio.findByNumeroSocio", Socio.class)
                .setParameter("numeroSocio", numSocio);
        Query consultaActividad = s.createNamedQuery("Actividad.findByIdActividad", Actividad.class)
                .setParameter("idActividad", idActividad);

        var socio = (Socio) consultaSocio.getSingleResult();
        var actividad = (Actividad) consultaActividad.getSingleResult();
        actividad.altaSocio(socio);
        s.saveOrUpdate(socio);
    }

    public void removeActividad(Session s, String numSocio, String idActividad) throws Exception{
        Query consultaSocio = s.createNamedQuery("Socio.findByNumeroSocio", Socio.class)
                .setParameter("numeroSocio", numSocio);
        Query consultaActividad = s.createNamedQuery("Actividad.findByIdActividad", Actividad.class)
                .setParameter("idActividad", idActividad);

        var socio = (Socio) consultaSocio.getSingleResult();
        var actividad = (Actividad) consultaActividad.getSingleResult();
        actividad.bajaSocio(socio);
        s.saveOrUpdate(socio);
    }

    public Set<Actividad> getActividades(Session s, String numSocio) throws Exception{
        Query consulta = s.createNamedQuery("Socio.findByNumeroSocio", Socio.class)
                .setParameter("numeroSocio", numSocio);

        var socio = (Socio) consulta.getSingleResult();
        return (Set<Actividad>) socio.getActividades();
    }
}
