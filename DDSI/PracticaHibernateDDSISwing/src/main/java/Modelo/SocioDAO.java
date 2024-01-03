/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.Set;

//import org.hibernate.Query;
import org.hibernate.query.Query;
import org.hibernate.Session;

/**
 *
 * @author rafaa
 */
public class SocioDAO {

    /**
     * Obtiene una lista de todos los socios almacenados en la base de datos
     * utilizando HQL.
     *
     * @param s La sesión de Hibernate a utilizar para la consulta.
     * @return Una lista de objetos Socio que representan todos los socios
     * almacenados, o una lista vacía si no hay socios.
     * @throws Exception Si ocurre algún error durante la ejecución de la
     * consulta.
     */
    public ArrayList<Socio> getSociosHQL(Session s) throws Exception {
        Query consulta = s.createQuery("SELECT s FROM Socio s", Socio.class);
        return (ArrayList<Socio>) consulta.list();
    }

    /**
     * Obtiene una lista de todos los socios almacenados en la base de datos
     * utilizando SQL.
     *
     * @param s La sesión de Hibernate a utilizar para la consulta.
     * @return Una lista de objetos Socio que representan todos los socios
     * almacenados, o una lista vacía si no hay socios.
     * @throws Exception Si ocurre algún error durante la ejecución de la
     * consulta.
     */
    public ArrayList<Socio> getSociosSQL(Session s) throws Exception {
        Query consulta = s.createNativeQuery("SELECT * FROM SOCIO s", Socio.class);
        return (ArrayList<Socio>) consulta.list();
    }

    /**
     * Obtiene una lista de todos los socios almacenados en la base de datos
     * utilizando NamedQueries.
     *
     * @param s La sesión de Hibernate a utilizar para la consulta.
     * @return Una lista de objetos Socio que representan todos los socios
     * almacenados, o una lista vacía si no hay socios.
     * @throws Exception Si ocurre algún error durante la ejecución de la
     * consulta.
     */
    public ArrayList<Socio> getSociosNamedQuery(Session s) throws Exception {
        Query consulta = s.createNamedQuery("Socio.findAll", Socio.class);
        return (ArrayList<Socio>) consulta.list();
    }

    /**
     * Devuelve el último número de socio almacenado en la base de datos.
     *
     * @param session La sesión de Hibernate a utilizar para la consulta.
     * @return El último número de socio almacenado en la base de datos, o null
     * si no hay socios.
     * @throws Exception Si ocurre algún error durante la ejecución de la
     * consulta.
     */
    public String DevolverUltimoCodigo(Session session) throws Exception {
        Query consulta = session.createQuery("Select MAX(s.numeroSocio) from Socio s");
        return (String) consulta.getSingleResult();
    }

    /**
     * Obtiene una lista de nombres y teléfonos de todos los socios almacenados
     * en la base de datos.
     *
     * @param s La sesión de Hibernate a utilizar para la consulta.
     * @return Una lista de arrays de objetos que contiene nombres y teléfonos
     * de los socios, o una lista vacía si no hay socios. Cada array de objetos
     * contiene dos elementos: el nombre del socio y su número de teléfono.
     * @throws Exception Si ocurre algún error durante la ejecución de la
     * consulta.
     */
    public ArrayList<Object[]> getNomTelSocios(Session s) throws Exception {
        Query consulta = s.createQuery("SELECT s.nombre,s.telefono FROM Socio s");
        return (ArrayList<Object[]>) consulta.list();
    }

    /**
     * Obtiene una lista de todos los socios almacenados en la base de datos
     * ordenados por número de socio ordenados de mayor a menor.
     *
     * @param s La sesión de Hibernate a utilizar para la consulta.
     * @return Una lista de objetos Socio ordenados por número de socio, o una
     * lista vacía si no hay socios.
     * @throws Exception Si ocurre algún error durante la ejecución de la
     * consulta.
     */
    public ArrayList<Socio> getSociosSortByNumSocio(Session s) throws Exception {
        Query consulta = s.createQuery("SELECT s FROM Socio s ORDER BY s.numeroSocio ASC");
        return (ArrayList<Socio>) consulta.getResultList();

    }

    /**
     * Obtiene una lista de nombres y categorías de socios que coinciden con la
     * categoría especificada.
     *
     * @param s La sesión de Hibernate a utilizar para la consulta.
     * @param c La categoría de socios a buscar.
     * @return Una lista de arrays de objetos que contiene nombres y categorías
     * de los socios que coinciden con la categoría proporcionada, o una lista
     * vacía si no hay socios que coincidan. Cada array de objetos contiene dos
     * elementos: el nombre del socio y su categoría.
     * @throws Exception Si ocurre algún error durante la ejecución de la
     * consulta.
     */
    public ArrayList<Object[]> getSociosCategoria(Session s, char c) throws Exception {
        Query consulta = s.createQuery("SELECT s.nombre,s.categoria FROM Socio s where s.categoria = :categoria");
        consulta.setParameter("categoria", c);
        return (ArrayList<Object[]>) consulta.list();
    }

    /**
     * Inserta o actualiza un objeto Socio en la base de datos.
     *
     * @param s La sesión de Hibernate a utilizar para la operación.
     * @param socio El objeto Socio que se va a insertar o actualizar en la base
     * de datos.
     * @throws Exception Si ocurre algún error durante la operación de inserción
     * o actualización.
     */
    public void insertarSocio(Session s, Socio socio) throws Exception {
        s.saveOrUpdate(socio);
    }

    /**
     * Elimina un socio de la base de datos mediante su número de socio.
     *
     * @param s La sesión de Hibernate a utilizar para la operación.
     * @param numSocio El número único que identifica al socio que se va a
     * eliminar.
     * @throws Exception Si ocurre algún error durante la operación de
     * eliminación.
     */
    public void eliminarSocio(Session s, String numSocio) throws Exception {
        Query consulta = s.createNamedQuery("Socio.findByNumeroSocio", Socio.class)
                .setParameter("numeroSocio", numSocio);
        var socio = (Socio) consulta.getSingleResult();
        s.delete(socio);
    }

    /**
     * Actualiza la categoría de un socio en la base de datos mediante su número
     * de socio.
     *
     * @param s La sesión de Hibernate a utilizar para la operación.
     * @param numSocio El número único que identifica al socio cuya categoría se
     * va a actualizar.
     * @param categoria La nueva categoría que se asignará al socio.
     * @throws Exception Si ocurre algún error durante la operación de
     * actualización.
     */
    public void actualizarCategoria(Session s, String numSocio, char categoria) throws Exception {
        Query consulta = s.createNamedQuery("Socio.findByNumeroSocio", Socio.class)
                .setParameter("numeroSocio", numSocio);

        var socio = (Socio) consulta.getSingleResult();

        socio.setCategoria(categoria);

        s.saveOrUpdate(socio);
    }

    /**
     * Asocia un socio a una actividad en la base de datos mediante sus números
     * de socio y de actividad.
     *
     * @param s La sesión de Hibernate a utilizar para la operación.
     * @param numSocio El número único que identifica al socio que se va a
     * asociar a la actividad.
     * @param idActividad El ID único que identifica a la actividad a la cual se
     * va a asociar el socio.
     * @throws Exception Si ocurre algún error durante la operación de
     * asociación.
     */
    public void addActividad(Session s, String numSocio, String idActividad) throws Exception {
        Query consultaSocio = s.createNamedQuery("Socio.findByNumeroSocio", Socio.class)
                .setParameter("numeroSocio", numSocio);
        Query consultaActividad = s.createNamedQuery("Actividad.findByIdActividad", Actividad.class)
                .setParameter("idActividad", idActividad);

        var socio = (Socio) consultaSocio.getSingleResult();
        var actividad = (Actividad) consultaActividad.getSingleResult();
        actividad.altaSocio(socio);
        s.saveOrUpdate(socio);
    }

    /**
     * Desvincula un socio de una actividad en la base de datos mediante sus
     * números de socio y de actividad.
     *
     * @param s La sesión de Hibernate a utilizar para la operación.
     * @param numSocio El número único que identifica al socio que se va a
     * desvincular de la actividad.
     * @param idActividad El ID único que identifica a la actividad de la cual
     * se va a desvincular el socio.
     * @throws Exception Si ocurre algún error durante la operación de
     * desvinculación.
     */
    public void removeActividad(Session s, String numSocio, String idActividad) throws Exception {
        Query consultaSocio = s.createNamedQuery("Socio.findByNumeroSocio", Socio.class)
                .setParameter("numeroSocio", numSocio);
        Query consultaActividad = s.createNamedQuery("Actividad.findByIdActividad", Actividad.class)
                .setParameter("idActividad", idActividad);

        var socio = (Socio) consultaSocio.getSingleResult();
        var actividad = (Actividad) consultaActividad.getSingleResult();
        actividad.bajaSocio(socio);
        s.saveOrUpdate(socio);
    }

    /**
     * Obtiene un conjunto de actividades asociadas a un socio en la base de
     * datos mediante su número de socio.
     *
     * @param s La sesión de Hibernate a utilizar para la consulta.
     * @param numSocio El número único que identifica al socio del cual se
     * obtendrán las actividades asociadas.
     * @return Un conjunto de actividades asociadas al socio identificado por el
     * número proporcionado.
     * @throws Exception Si ocurre algún error durante la ejecución de la
     * consulta.
     */
    public Set<Actividad> getActividades(Session s, String numSocio) throws Exception {
        Query consulta = s.createNamedQuery("Socio.findByNumeroSocio", Socio.class)
                .setParameter("numeroSocio", numSocio);

        var socio = (Socio) consulta.getSingleResult();
        return (Set<Actividad>) socio.getActividades();
    }

    /**
     * Obtiene una lista de todos los socios almacenados en la base de datos
     * ordenados por número de socio.
     * @param session La sesión de Hibernate a utilizar para la consulta.
     * @return Una lista de objetos Socio ordenados por número de socio, o una
     * lista vacía si no hay socios.
     * @throws Exception Si ocurre algún error durante la ejecución de la
     * consulta.
     */
    public ArrayList<Socio> listSociosSortByNumSocio(Session session) throws Exception {
        Query consulta = session.createQuery("SELECT s FROM Socio s ORDER BY s.numeroSocio ASC");
        return (ArrayList<Socio>) consulta.getResultList();
    }
}
