/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
/**
 * la voy a hacer con metodos static por el momento(me parece que tiene mas
 * sentido)
 *
 * @author rafaa
 */
public class VistaInfoDB {

    static public void infoMetaData(DatabaseMetaData dbmd) throws SQLException {
        System.out.println("**************************");
        System.out.println(dbmd.getURL());           //PORQUE DEVUELVEN CLASES ABSTACTAS??? ABSTRACT STRING?? no es que devuelvan clases abstractas
        System.out.println("**************************");//es que el metodo de la clase es abstracto, lo que quiere decir que todas la subclases lo tiene
        System.out.println(dbmd.getDriverName());//que implementar
        System.out.println("**************************");
        System.out.println(dbmd.getDriverVersion()); 
        System.out.println("**************************");
        System.out.println(dbmd.getUserName());
        System.out.println("**************************");
        System.out.println(dbmd.getSQLKeywords());   //no se si esto esta bien
    }

}
