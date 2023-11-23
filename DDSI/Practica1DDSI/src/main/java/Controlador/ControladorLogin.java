/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.VistaInfoDB;
import Vista.VistaMensajes;
import java.sql.*;

/**
 *
 * @author rafaa
 */
public class ControladorLogin {

    private Conexion conexion;
    //final VistaMensajes vMensaje;
    //final VistaInfoDB vInfoDB;

    /**
     *
     * metodo privado para hacer una prueba de conexion a mariadb
     *
     * @return void
     */
    private void conectarDBMaria() {
        try {
            String server = "mariadb";
            String ip = "172.34.1.241"; //18
            String bd = "DDSI_032";
            String u = "DDSI_032";
            String p = "pececitos";
            conexion = new Conexion(server, ip, bd, u, p);
            VistaMensajes.mensajeConsola("Conexión Correcta");
        } catch (SQLException sqle) {
            VistaMensajes.mensajeConsola("Error de conexión", sqle.getMessage());
        } catch (ClassNotFoundException ex) {
            VistaMensajes.mensajeConsola("Error indeterminado", ex.getMessage());
        }
    }
     private void conectarDBOracle() {
           try {
            String server = "oracle";
            String ip = "172.17.20.39"; 
            String bd = "etsi";
            String u = "DDSI_032";
            String p = "DDSI_032";
            conexion = new Conexion(server, ip, bd, u, p);
            VistaMensajes.mensajeConsola("Conexión Correcta");
        } catch (SQLException sqle) {
            VistaMensajes.mensajeConsola("Error de conexión", sqle.getMessage());
        } catch (ClassNotFoundException ex) {
            VistaMensajes.mensajeConsola("Error indeterminado", ex.getMessage());
        }
     }

    /**
     * inicializa el controlador
     */
    public ControladorLogin() {
        conectarDBOracle();
        //infoMetaData();
        //closeConexion();
    }

    /**
     * cirerra conexion
     */
    public void closeConexion() {
        if (this.conexion != null) {
            try {
                conexion.closeConection();
                VistaMensajes.mensajeConsola("Conexion cerrada con exito");
            } catch (SQLException ex) {
                VistaMensajes.mensajeConsola("Error al cerrar conexion ", ex.getMessage());
            }
        }
    }

    /**
     *
     * @return conexion
     */
    public Conexion getConexion() {
        return conexion;
    }
    public void infoMetaData(){
        try {
            VistaInfoDB.infoMetaData(conexion.getMetaDataDB());
        } catch (SQLException ex) {
            
        }
    }
    
}
