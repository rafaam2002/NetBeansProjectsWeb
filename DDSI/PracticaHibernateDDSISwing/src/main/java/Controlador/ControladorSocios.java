/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Socio;
import Modelo.SocioDAO;
import Vista.PanelSocios;
import Vista.VistaMensaje;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author rafaa
 */
public class ControladorSocios implements ActionListener {

    private final PanelSocios pSocios;
    private final UtilTablasSocio uTablasS;
    private final SessionFactory sessionFactory;
    private Session session;
    private Transaction tr;
    private final SocioDAO socioDAO;

    public ControladorSocios(PanelSocios pSocios, SessionFactory sessionFactory) {
        this.pSocios = pSocios;
        uTablasS = new UtilTablasSocio(this.pSocios);
        addListeners();
        this.sessionFactory = sessionFactory;
        socioDAO = new SocioDAO();
    }

    public void init() {
        dibujarTabla();
    }

    private void addListeners() {
        pSocios.jButtonNuevoSocio.addActionListener(this);
        pSocios.jButtonBajaSocio.addActionListener(this);
        pSocios.jButtonActualizarSocio.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "NuevoSocio" -> {
                try {
                    session = sessionFactory.openSession();
                    tr = session.beginTransaction();
                    var s = new Socio("S002", "Rafa", "49957379K", "1/1/0001", 'A');
                    socioDAO.insertarSocio(session, s);
                    VistaMensaje.mensajeConsola("El socio se ha insertado con exito");
                    
                } catch (HibernateException ex) {
                    tr.rollback();
                    VistaMensaje.mensajeConsola("Error en la petición de socios " + ex.getMessage());
                } finally {
                    if (session != null && session.isOpen()) {
                        session.close();
                    }
                    dibujarTabla();
                }
            }
            case "EliminarSocio" -> {
                
            }

            default ->
                throw new AssertionError();
        }
    }

    private void dibujarTabla() {
        uTablasS.dibujarTablaSocios();
        session = sessionFactory.openSession();

        tr = session.beginTransaction();

        try {
            ArrayList<Socio> socios = pideSocios();
            uTablasS.vaciarTablaSocios();
            uTablasS.rellenarTablaSocios(socios);
            tr.commit();
        } catch (Exception ex) {
            tr.rollback();
            VistaMensaje.mensajeConsola("Error " + ex.getMessage());
        } finally {
            if (session != null & session.isOpen()) {
                session.close();
            }
        }
    }

    private ArrayList<Socio> pideSocios() throws Exception {
        ArrayList<Socio> socios = socioDAO.getSociosNamedQuery(session);
        return socios;
    }
}
