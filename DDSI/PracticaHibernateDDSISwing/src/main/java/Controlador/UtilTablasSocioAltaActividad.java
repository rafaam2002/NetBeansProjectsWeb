/*
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Actividad;
import Modelo.Socio;
import Vista.JDialogSocioAltaActividad;
import Vista.PanelSocios;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rafaa
 */
class UtilTablasSocioAltaActividad {

    DefaultTableModel modeloTablaSocioAlta;
    JDialogSocioAltaActividad jDSocioAlta;

    public UtilTablasSocioAltaActividad(JDialogSocioAltaActividad jDialog) {
        this.jDSocioAlta = jDialog;

        //Para trabajar con los valores de la tabla
        this.modeloTablaSocioAlta = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jDSocioAlta.jTableSocioAlta.setModel(modeloTablaSocioAlta);

    }

    public void dibujarTablaSocioAlta() {
        String[] columnasTabla = {"Código", "Nombre", "Descripcion", "Precio", "Monitor"};
        modeloTablaSocioAlta.setColumnIdentifiers(columnasTabla);

        //Para no permitir redimensiones con el raton
        jDSocioAlta.jTableSocioAlta.getTableHeader().setResizingAllowed(false);
        jDSocioAlta.jTableSocioAlta.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        //Para fijar el ancho de las columnas
        jDSocioAlta.jTableSocioAlta.getColumnModel().getColumn(0).setPreferredWidth(100);
        jDSocioAlta.jTableSocioAlta.getColumnModel().getColumn(1).setPreferredWidth(220);
        jDSocioAlta.jTableSocioAlta.getColumnModel().getColumn(2).setPreferredWidth(300);
        jDSocioAlta.jTableSocioAlta.getColumnModel().getColumn(3).setPreferredWidth(100);
        jDSocioAlta.jTableSocioAlta.getColumnModel().getColumn(4).setPreferredWidth(100);
    }

    public void rellenarTablaSocioAlta(ArrayList<Actividad> actividades) {
        Object[] fila = new Object[7];

        for (Actividad a : actividades) {
            fila[0] = a.getIdActividad();
            fila[1] = a.getNombre();
            fila[2] = a.getDescripcion();
            fila[3] = a.getPrecioBaseMes();
            fila[4] = a.getMonitorResponsable().getCodMonitor();
            modeloTablaSocioAlta.addRow(fila);
        }
    }

    public void vaciarTablaSocioAlta() {
        while (modeloTablaSocioAlta.getRowCount() > 0) {
            modeloTablaSocioAlta.removeRow(0);
        }
    }

}
