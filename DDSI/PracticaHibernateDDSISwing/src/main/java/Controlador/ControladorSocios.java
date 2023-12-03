/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Socio;
import Modelo.SocioDAO;
import Vista.JDialogInsertarMonitor;
import Vista.PanelSocios;
import Vista.VMensaje;
import Vista.VistaMensaje;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
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
    private final VMensaje vMensaje;

    public ControladorSocios(PanelSocios pSocios, SessionFactory sessionFactory) {
        vMensaje = new VMensaje();
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
                    var s = new Socio("S014", "Rafa", "49900379K", "1/1/0001", 'A');
                    socioDAO.insertarSocio(session, s);
                    tr.commit();
                    VistaMensaje.mensajeConsola("El socio se ha insertado con exito");
                } catch (Exception ex) {
                    tr.rollback();

                    VistaMensaje.mensajeConsola("Error en la insercion de un nuevo socio " + ex.getMessage());
                } finally {
                    if (session != null && session.isOpen()) {
                        session.close();
                    }

                    dibujarTabla();
                }
            }
            case "BajaSocio" -> {
                int filaSocio = pSocios.jTableSocios.getSelectedRow();
                if (filaSocio != -1) {
                    int confirm = BajaDialog(pSocios);
                    if (confirm == JOptionPane.YES_OPTION) {
                        try {
                            session = sessionFactory.openSession();
                            tr = session.beginTransaction();

                            List<Socio> socios = socioDAO.getSociosNamedQuery(session);
                            socioDAO.eliminarSocio(session, socios.get(filaSocio).getNumeroSocio());
                            tr.commit();
                            VistaMensaje.mensajeConsola("El socio se ha eliminado con exito");
//                            vMensaje.MensajeInfo(pSocios, "Socio dado de baja con exito");
                        } catch (Exception ex) {
                            tr.rollback();
                            VistaMensaje.mensajeConsola("Error en el delete de un socio " + ex.getMessage());
                            vMensaje.MensajeInfo(pSocios, "Error al eliminar socio " + ex.getMessage());
                        } finally {
                            if (session != null && session.isOpen()) {
                                session.close();
                            }

                            dibujarTabla();
                        }
                    }
                }
            }

            default ->
                VistaMensaje.mensajeConsola("El boton pulsado no tiene una opcion definida");
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

    public int BajaDialog(Component C) {
        int opcion = JOptionPane.showConfirmDialog(C, "Deseas eliminar dicho Socio ?",
                "Atención", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        return opcion;
    }

}
