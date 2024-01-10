/*
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Monitor;
import Modelo.Socio;
import Vista.JDialogActualizacionCategorias;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rafaa
 */
class UtilTablasActualizacionCategorias {

    DefaultTableModel modeloTablaActualizacionCategorias;
    JDialogActualizacionCategorias dialogoCategorias;

    public UtilTablasActualizacionCategorias(JDialogActualizacionCategorias dialogoCategorias) {
        this.dialogoCategorias = dialogoCategorias;

        //Para trabajar con los valores de la tabla
        this.modeloTablaActualizacionCategorias = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dialogoCategorias.jTableCategorias.setModel(modeloTablaActualizacionCategorias);

    }

    public void dibujarTablaActualizacionCategorias() {
        String[] columnasTabla = { "Nombre", "DNI", "Categoria"};
        modeloTablaActualizacionCategorias.setColumnIdentifiers(columnasTabla);

        //Para no permitir redimensiones con el raton
        dialogoCategorias.jTableCategorias.getTableHeader().setResizingAllowed(false);
        dialogoCategorias.jTableCategorias.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        //Para fijar el ancho de las columnas
        dialogoCategorias.jTableCategorias.getColumnModel().getColumn(0).setPreferredWidth(240);
        dialogoCategorias.jTableCategorias.getColumnModel().getColumn(1).setPreferredWidth(200);
        dialogoCategorias.jTableCategorias.getColumnModel().getColumn(2).setPreferredWidth(120);
    }

    public void rellenarTablaActualizacionCategorias(List<Socio> socios) {
        Object[] fila = new Object[7];

        for (Socio s : socios) {
            fila[0] = s.getNombre();
            fila[1] = s.getDni();
            fila[2] = s.getCategoria();
            modeloTablaActualizacionCategorias.addRow(fila);
        }
    }

    public void vaciarTablaActualizacionCategorias() {
        while (modeloTablaActualizacionCategorias.getRowCount() > 0) {
            modeloTablaActualizacionCategorias.removeRow(0);
        }
    }

}
