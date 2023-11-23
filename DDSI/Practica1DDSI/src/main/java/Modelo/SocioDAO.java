package Modelo;

import Controlador.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * mirar altaSocio
 *
 * @author rafaa
 */
public class SocioDAO {

    private final Conexion conexion;
    private PreparedStatement ps; //uso el ps para cada metodo no? cuando ps apunte a otro ya borrara el borrador de basura de java el objeto antiguo

    public SocioDAO(Conexion c) {
        conexion = c;
    }

    public boolean existeSocio(String numeroSocio) throws SQLException {
        ps = conexion.getConnection().prepareStatement("SELECT * FROM SOCIO WHERE numsocio == ?");
        ps.setString(1, numeroSocio);
        return ps.executeQuery().next(); //devuelve true si hay algun valor 
    }

    public ResultSet listaSocios() throws SQLException { //porque resultset es una interfaz? creia que las interfaces no se podian usar como objetos
        ps = conexion.getConnection().prepareStatement("SELECT numeroSocio, nombre FROM SOCIO");
        return ps.executeQuery();
    }

    /**
     *
     * @return ResultSet con el ultimo socio
     * @throws SQLException
     */
//    public int ultimoSocio() throws SQLException {
//        ps = conexion.getConnection().prepareStatement("SELECT NUMEROSOCIO FROM SOCIO");
//        ResultSet r = ps.executeQuery();
//        r.next();
//        System.out.println(r.getString(1));
//        System.out.println(r.getString(1).substring(1));
//        return 0;
//    }

    public void altaSocio(Socio socio) throws SQLException {
        ps = conexion.getConnection().prepareStatement("INSERT INTO SOCIO "
                + "(numeroSocio, nombre, dni, fechaNacimiento, telefono, correo, fechaEntrada, categoria)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"); //no habria que poner las ? entre ' '???

        ps.setString(1, socio.getNumeroSocio());
        ps.setString(2, socio.getNombre());
        ps.setString(3, socio.getDni());
        ps.setString(4, socio.getFechaNacimiento());
        ps.setString(5, socio.getTelefono());
        ps.setString(6, socio.getCorreo());
        ps.setString(7, socio.getFechaEntrada());
        ps.setString(8, socio.getCategoria());

        ps.executeUpdate();
        ps.close();
    }

   
    public void bajaSocio(String numSocio) throws SQLException {
//        System.out.println("numSocio:" + numSocio);
        ps = conexion.getConnection().prepareStatement("DELETE FROM SOCIO WHERE numeroSocio = ?");
        ps.setString(1, numSocio);

        ps.executeUpdate();
        ps.close();
    }

    public void modificarSocio(String numSocio, String campo, String valor) throws SQLException {
        ps = conexion.getConnection().prepareStatement("UPDATE SOCIO SET ? = ? WHERE numeroSocio = ?");
        ps.setString(1, campo);
        ps.setString(2, valor);
        ps.setString(3, numSocio);

    }
}
