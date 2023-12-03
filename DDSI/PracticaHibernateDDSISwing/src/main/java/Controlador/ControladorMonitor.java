/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Monitor;
import Modelo.MonitorDAO;
import Modelo.Socio;
import Vista.JDialogInsertarMonitor;
import Vista.PanelMonitores;
import Vista.VMensaje;
import Vista.VistaMensaje;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author rafaelMesaNombela
 */
public class ControladorMonitor implements ActionListener {

    private final PanelMonitores pMonitores;
    private final UtilTablasMonitor uTablasM;
    private final SessionFactory sessionFactory;
    private Session session;
    private Transaction tr;
    private final MonitorDAO monitorDAO;
    private final VMensaje vMensaje;
    private final JDialogInsertarMonitor dialogoInsertaMonitor;

    public ControladorMonitor(PanelMonitores pMonitores, SessionFactory sessionFactory) {
        vMensaje = new VMensaje();
        this.pMonitores = pMonitores;
        uTablasM = new UtilTablasMonitor(this.pMonitores);
        this.sessionFactory = sessionFactory;
        monitorDAO = new MonitorDAO();

        //Inicializar dialogo insertar monitor
        dialogoInsertaMonitor = new JDialogInsertarMonitor();
        dialogoInsertaMonitor.setLocationRelativeTo(null);
        dialogoInsertaMonitor.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialogoInsertaMonitor.setResizable(false);

        addListeners();
    }

    private void addListeners() {
        pMonitores.jButtonNuevoMonitor.addActionListener(this);
        pMonitores.jButtonBajaMonitor.addActionListener(this);
        pMonitores.jButtonActualizacionMonitor.addActionListener(this);
        dialogoInsertaMonitor.jButtomInsertarMonitor.addActionListener(this);
        dialogoInsertaMonitor.jButtonCancelarMonitor.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "NuevoMonitor" -> {
                session = sessionFactory.openSession();
                tr = session.beginTransaction();
                try {
                    String codMonitor = monitorDAO.DevolverUltimoCodigo(session);
                    dialogoInsertaMonitor.codigoMonitor.setText(codMonitor);
                    dialogoInsertaMonitor.codigoMonitor.setText(codMonitor);
                    dialogoInsertaMonitor.codigoMonitor.setEditable(false);
                    dialogoInsertaMonitor.setVisible(true);
                    tr.commit();
                } catch (Exception ex) {
                    tr.rollback();
                    vMensaje.MensajeInfo(pMonitores, ex.getMessage());
                } finally {
                    if (session != null && session.isOpen()) {
                        session.close();
                    }
                }

            }
            case "InsertarMonitor" -> {
                try {
                    session = sessionFactory.openSession();
                    tr = session.beginTransaction();

                    String nombre = dialogoInsertaMonitor.nombreMonitor.getText();
                    String DNI = dialogoInsertaMonitor.DNIMonitor.getText();
                    String telefono = dialogoInsertaMonitor.telefonoMonitor.getText();
                    String correo = dialogoInsertaMonitor.codigoMonitor.getText();
                    String nick = dialogoInsertaMonitor.nickMonitor.getText();
                    
                    var monitor = new Monitor(correo, nombre, DNI, telefono, correo, correo, nick);
                    
                } catch (Exception ex) {
                    tr.rollback();

                }
            }
            default ->
                Vista.VistaMensaje.mensajeConsola("La accion de ese boton no esta registrada");
        }

    }

    public void init() {
        dibujarTabla();
    }

    private void dibujarTabla() {
        uTablasM.dibujarTablaMonitores();
        session = sessionFactory.openSession();
        tr = session.beginTransaction();
        try {
            ArrayList<Monitor> monitores = pideMonitores();
            uTablasM.vaciarTablaMonitores();
            uTablasM.rellenarTablaMonitores(monitores);
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

    private ArrayList<Monitor> pideMonitores() throws Exception {
        ArrayList<Monitor> monitores = monitorDAO.listaMonitores(session);
        return monitores;
    }

}
