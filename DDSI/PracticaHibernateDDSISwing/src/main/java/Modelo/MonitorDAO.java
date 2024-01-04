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

    /**
     * Obtiene una lista de todos los monitores almacenados en la base de datos.
     *
     * @param session La sesión de Hibernate a utilizar para la consulta.
     * @return Una lista de objetos Monitor que representan todos los monitores
     * almacenados, o una lista vacía si no hay monitores.
     * @throws Exception Si ocurre algún error durante la ejecución de la
     * consulta.
     */
    public ArrayList<Monitor> listaMonitores(Session session) throws Exception {

        Query consulta = session.createQuery("SELECT m FROM Monitor m", Monitor.class);
        return (ArrayList<Monitor>) consulta.list();
    }
    
     /**
     * Recupera una lista de socios cuyos nombres cumplen con una expresión
     * dada ordenados de menor a mayor por codMonitor.
     *
     * @param s La sesión de Hibernate.
     * @param expresionNombre La expresión que se utilizará para filtrar los
     * socios por nombre.
     * @return Un ArrayList de socios cuyos nombres cumplen con la expresión
     * dada.
     * @throws java.lang.Exception
     */
    public ArrayList<Monitor> getMonitoresPorExpresion(Session s, String expresionNombre) throws Exception {

        Query consulta = s.createQuery("FROM Monitor m WHERE m.nombre LIKE :expresionNombre "
                + "ORDER BY m.codMonitor ASC", Monitor.class);
        consulta.setParameter("expresionNombre", expresionNombre + "%");

        return (ArrayList<Monitor>) consulta.getResultList();
    }

    /**
     * Obtiene una lista de todos los monitores almacenados en la base de datos
     * ordenados por número de monitor de menor a mayor.
     *
     * @param session La sesión de Hibernate a utilizar para la consulta.
     * @return Una lista de objetos Monitor ordenados por número de monitor, o
     * una lista vacía si no hay monitores.
     * @throws Exception Si ocurre algún error durante la ejecución de la
     * consulta.
     */
    public ArrayList<Monitor> listMonitoresSortByNumMonitor(Session session) throws Exception {
        Query consulta = session.createQuery("SELECT m FROM Monitor m ORDER BY m.codMonitor ASC");
        return (ArrayList<Monitor>) consulta.getResultList();
    }

    /**
     * Devuelve el último código de monitor almacenado en la base de datos.
     *
     * @param session La sesión de Hibernate a utilizar para la consulta.
     * @return El último código de monitor almacenado en la base de datos, o
     * null si no hay monitores.
     * @throws Exception Si ocurre algún error durante la ejecución de la
     * consulta.
     */
    public String DevolverUltimoCodigo(Session session) throws Exception {
        Query consulta = session.createQuery("Select MAX(m.codMonitor) from Monitor m");
        return (String) consulta.getSingleResult();
    }

    /**
     * Inserta o actualiza un objeto Monitor en la base de datos.
     *
     * @param session La sesión de Hibernate a utilizar para la operación.
     * @param monitor El objeto Monitor que se va a insertar o actualizar en la
     * base de datos.
     * @throws Exception Si ocurre algún error durante la operación de inserción
     * o actualización.
     */
    public void insertarActualizarMonitor(Session session, Monitor monitor) throws Exception {
        session.saveOrUpdate(monitor);
    }

    /**
     * Elimina un monitor de la base de datos mediante su código de monitor.
     * @param session La sesión de Hibernate a utilizar para la operación.
     * @param codMonitor El código único que identifica al monitor que se va a
     * eliminar.
     * @throws Exception Si ocurre algún error durante la operación de
     * eliminación.
     */
    public void eliminarMonitor(Session session, String codMonitor) throws Exception {
        Query consulta = session.createNamedQuery("Monitor.findByCodMonitor", Monitor.class).setParameter("codMonitor", codMonitor);
        session.delete((Monitor) consulta.getSingleResult());
    }
}
