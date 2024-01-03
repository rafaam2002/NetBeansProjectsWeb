/*
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Monitor;
import Modelo.Socio;
import Vista.PanelSocios;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rafaa
 */
class UtilTablasSocio {

    DefaultTableModel modeloTablaSocios;
    PanelSocios pSocios;

    public UtilTablasSocio(PanelSocios pSocios) {
        this.pSocios = pSocios;

        //Para trabajar con los valores de la tabla
        this.modeloTablaSocios = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        pSocios.jTableSocios.setModel(modeloTablaSocios);

    }

    public void dibujarTablaSocios() {
        String[] columnasTabla = {"Código", "Nombre", "DNI", "Telefono", "Correo",
            "Fecha Incorporación", "Categoria"};
        modeloTablaSocios.setColumnIdentifiers(columnasTabla);

        //Para no permitir redimensiones con el raton
        pSocios.jTableSocios.getTableHeader().setResizingAllowed(false);
        pSocios.jTableSocios.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        //Para fijar el ancho de las columnas
        pSocios.jTableSocios.getColumnModel().getColumn(0).setPreferredWidth(40);
        pSocios.jTableSocios.getColumnModel().getColumn(1).setPreferredWidth(240);
        pSocios.jTableSocios.getColumnModel().getColumn(2).setPreferredWidth(70);
        pSocios.jTableSocios.getColumnModel().getColumn(3).setPreferredWidth(70);
        pSocios.jTableSocios.getColumnModel().getColumn(4).setPreferredWidth(200);
        pSocios.jTableSocios.getColumnModel().getColumn(5).setPreferredWidth(150);
        pSocios.jTableSocios.getColumnModel().getColumn(6).setPreferredWidth(60);
    }

    public void rellenarTablaSocios(ArrayList<Socio> socios) {
        Object[] fila = new Object[7];

        for (Socio s : socios) {
            fila[0] = s.getNumeroSocio();
            fila[1] = s.getNombre();
            fila[2] = s.getDni();
            fila[3] = s.getTelefono();
            fila[4] = s.getCorreo();
            fila[5] = s.getFechaEntrada();
            fila[6] = s.getCategoria();
            modeloTablaSocios.addRow(fila);
        }
    }

    public void vaciarTablaSocios() {
        while (modeloTablaSocios.getRowCount() > 0) {
            modeloTablaSocios.removeRow(0);
        }
    }

}
