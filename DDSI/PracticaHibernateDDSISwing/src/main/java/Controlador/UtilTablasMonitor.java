/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Monitor;
import Vista.PanelMonitores;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rafaa
 */
public class UtilTablasMonitor {

    DefaultTableModel modeloTablaMonitores;
    PanelMonitores pMonitores;

    public UtilTablasMonitor(PanelMonitores pMonitores) {
        this.pMonitores = pMonitores;

        //Para trabajar con los valores de la tabla
        this.modeloTablaMonitores = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        pMonitores.jTableMonitores.setModel(modeloTablaMonitores);

    }

    public void dibujarTablaMonitores() {
        String[] columnasTabla = {"Código", "Nombre", "DNI", "Telefono", "Correo",
            "Fecha Incorporación", "Nick"};
        modeloTablaMonitores.setColumnIdentifiers(columnasTabla);

        //Para no permitir redimensiones con el raton
        pMonitores.jTableMonitores.getTableHeader().setResizingAllowed(false);
        pMonitores.jTableMonitores.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        //Para fijar el ancho de las columnas
        pMonitores.jTableMonitores.getColumnModel().getColumn(0).setPreferredWidth(40);
        pMonitores.jTableMonitores.getColumnModel().getColumn(1).setPreferredWidth(240);
        pMonitores.jTableMonitores.getColumnModel().getColumn(2).setPreferredWidth(70);
        pMonitores.jTableMonitores.getColumnModel().getColumn(3).setPreferredWidth(70);
        pMonitores.jTableMonitores.getColumnModel().getColumn(4).setPreferredWidth(200);
        pMonitores.jTableMonitores.getColumnModel().getColumn(5).setPreferredWidth(150);
        pMonitores.jTableMonitores.getColumnModel().getColumn(6).setPreferredWidth(60);

    }

    public void rellenarTablaMonitores(ArrayList<Monitor> monitores) {
        Object[] fila = new Object[7];

        for (Monitor m : monitores) {
            fila[0] = m.getCodMonitor();
            fila[1] = m.getNombre();
            fila[2] = m.getDni();
            fila[3] = m.getTelefono();
            fila[4] = m.getCorreo();
            fila[5] = m.getFechaEntrada();
            fila[6] = m.getNick();
            modeloTablaMonitores.addRow(fila);
        }
    }
    
    public void vaciarTablaMonitores(){
        while(modeloTablaMonitores.getRowCount() > 0) {
            modeloTablaMonitores.removeRow(0);
        }
    }
}
