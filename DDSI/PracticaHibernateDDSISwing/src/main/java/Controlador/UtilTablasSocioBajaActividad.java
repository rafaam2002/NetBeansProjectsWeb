/*
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Actividad;
import Vista.JDialogSocioBajaActividad;
import Vista.JDialogSocioBajaActividad;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rafaa
 */
class UtilTablasSocioBajaActividad {

    DefaultTableModel modeloTablaSocioBaja;
    JDialogSocioBajaActividad jDSocioBaja;

    public UtilTablasSocioBajaActividad(JDialogSocioBajaActividad jDialog) {
        this.jDSocioBaja = jDialog;

        //Para trabajar con los valores de la tabla
        this.modeloTablaSocioBaja = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jDSocioBaja.jTableSocioBaja.setModel(modeloTablaSocioBaja);

    }

    public void dibujarTablaSocioBaja() {
        String[] columnasTabla = {"Código", "Nombre", "Descripcion", "PrecioBaseMes", "MonitorResponsable"};
        modeloTablaSocioBaja.setColumnIdentifiers(columnasTabla);

        //Para no permitir redimensiones con el raton
        jDSocioBaja.jTableSocioBaja.getTableHeader().setResizingAllowed(false);
        jDSocioBaja.jTableSocioBaja.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        //Para fijar el ancho de las columnas
        jDSocioBaja.jTableSocioBaja.getColumnModel().getColumn(0).setPreferredWidth(100);
        jDSocioBaja.jTableSocioBaja.getColumnModel().getColumn(1).setPreferredWidth(240);
        jDSocioBaja.jTableSocioBaja.getColumnModel().getColumn(2).setPreferredWidth(240);
        jDSocioBaja.jTableSocioBaja.getColumnModel().getColumn(3).setPreferredWidth(100);
        jDSocioBaja.jTableSocioBaja.getColumnModel().getColumn(4).setPreferredWidth(100);
    }

    public void rellenarTablaSocioBaja(ArrayList<Actividad> actividades) {
        Object[] fila = new Object[7];

        for (Actividad a : actividades) {
            fila[0] = a.getIdActividad();
            fila[1] = a.getNombre();
            fila[2] = a.getDescripcion();
            fila[3] = a.getPrecioBaseMes();
            fila[4] = a.getMonitorResponsable().getCodMonitor();
            modeloTablaSocioBaja.addRow(fila);
        }
    }

    public void vaciarTablaSocioBaja() {
        while (modeloTablaSocioBaja.getRowCount() > 0) {
            modeloTablaSocioBaja.removeRow(0);
        }
    }

}
