/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import java.sql.*;

/**
 *
 * @author rafaa
 * clase que establece una conexion con la DB mediante el objeto Connection
 */
public class Conexion {
    private final Connection conn;
    
    /**
     *
     * @param sgbd
     * @param ip
     * @param service_bd
     * @param usuario
     * @param password
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Conexion(String sgbd, String ip, String service_bd, String usuario,
            String password) throws ClassNotFoundException, SQLException {
        if(sgbd.equals("oracle")){
            conn = DriverManager.getConnection("jdbc:" + sgbd + ":thin:@" + ip +":1521:" + service_bd, usuario, password);
        }else{
            conn = DriverManager.getConnection("jdbc:" + sgbd + "://" + ip +":3306/" + service_bd,usuario, password);
        }
    }

    /**
     *
     * @return conn
     */
    public Connection getConnection (){
        return conn;
    }

    /**
     * cierra conn
     * @throws SQLException
     */
    public void closeConection() throws SQLException{
        if(conn != null){
            conn.close();
        }
    }
    public DatabaseMetaData getMetaDataDB() throws SQLException{
        return conn.getMetaData(); 
    }
}
