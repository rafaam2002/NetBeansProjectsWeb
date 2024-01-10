/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ActividadDAO;
import Modelo.MonitorDAO;
import Vista.JDialogInsertarMonitor;
import Vista.PanelActividades;
import Vista.PanelMonitores;
import Vista.VMensaje;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.hibernate.SessionFactory;

/**
 *
 * @author rafaa
 */
public class ControladorActividades implements ActionListener{

    private final VMensaje vMensaje;
    private final PanelActividades pActividades;
    private final UtilTablasActividad uTablasA;
    private final SessionFactory sessionFactory;
    private final ActividadDAO actividadDAO;
    
    
    public ControladorActividades(PanelActividades pActividades, SessionFactory sessionFactory) {
        vMensaje = new VMensaje();
        this.pActividades = pActividades;
        uTablasA = new UtilTablasActividad(this.pActividades);
        this.sessionFactory = sessionFactory;
        actividadDAO = new ActividadDAO();

        //Inicializar dialogo insertar Actividad
//        dialogoInsertaMonitor = new JDialogInsertarMonitor();
//        dialogoInsertaMonitor.setLocationRelativeTo(null);
//        dialogoInsertaMonitor.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
//        dialogoInsertaMonitor.setResizable(false);

        addListeners();
    }
    
    public void init(){
        pActividades.jButtonNuevaActividad.addActionListener(this);
    }

    private void addListeners() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
