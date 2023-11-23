package Controlador;

import Modelo.Socio;
import Modelo.SocioDAO;
import Vista.VistaMensajes;
import Vista.VistaMenuOpciones;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * me creo un nuevo SocioDAO cada case del menu? supongo que no mirar SocioDAO:
 * interfaz RESULTSET?? uso del ps para cada metodo?
 *
 * @author rafaa
 */
public class ControladorPrincipal {

    private Conexion conexion;
    private ResultSet result;
    private SocioDAO sDAO;

    public ControladorPrincipal(Conexion conexion) {
        this.conexion = conexion;
        sDAO = new SocioDAO(this.conexion);
        menuOpciones();
    }

    private void menuOpciones() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            VistaMenuOpciones.mostrarMenuOpciones();
            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                opcion = -1;  //para que muestre que esta opcion no es valida
                scanner.nextLine(); //limpia el buffer
            }

            switch (opcion) {
                case 1: {
                    try {
                        result = sDAO.listaSocios();

                        int i = 1;
                        while (result.next()) { //eso tambien genera la misma excepcion, ¿ponerlo en otro bloque o en el mismo?
                            VistaMensajes.mensajeConsola(i + ": " + result.getString(1) + " " + result.getString(2)); //col 1:numero socio col 2: nombre

                            i++;
                        }
                    } catch (SQLException ex) {
                        VistaMensajes.mensajeConsola("No se pudo optener la lista de socios", ex.getMessage());
                    }

                }
                break;

                case 2: {

                    int numUltimoSocio = -1;
                    int numSocio;
                    try {
                        result = sDAO.listaSocios();
                        while (result.next()) {
                            numSocio = Integer.parseInt(result.getString(1).substring(1));
                            if (numSocio > numUltimoSocio) {
                                numUltimoSocio = numSocio;
                            }
                        }
                    } catch (SQLException ex) {
                        VistaMensajes.mensajeConsola("No se encontro al ultimo socio", ex.getMessage());
                    }

                    try {
                        String cadenaNumSocio = "S";
                        var cal = Calendar.getInstance();
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        String fechaActual = formato.format(cal.getTime());
                        System.out.println(Integer.toString(numUltimoSocio).length());
                        if( 3 > Integer.toString(numUltimoSocio).length()){
                            cadenaNumSocio += "0";
                        }
                        cadenaNumSocio += Integer.toString(numUltimoSocio + 1);
                        sDAO.altaSocio(new Socio(cadenaNumSocio, "Rafa", "4995738A", "30/11/2005", "636127815", "rafaa@gmail.com", fechaActual, "A")); //falta darle valores al socio (no se si inventarmelo o mirar si te dan un ejemplo)
                        VistaMensajes.mensajeConsola("El socio se dio de alta con exito");
                    } catch (SQLException ex) {
                        VistaMensajes.mensajeConsola("No se pudo dar de alta al nuevo socio ", ex.getMessage());
                    }
                }
                break;

                case 3: {
                    try {
                        sDAO.bajaSocio("S011");
//                        sDAO.bajaSocio(new Socio("10", "Rafa", "4995738A", "30/11/2005", "636127815", "rafaa@gmail.com", "23/10/2023", "A")); //falta darle valores al socio (no se si inventarmelo o mirar si te dan un ejemplo)
                        VistaMensajes.mensajeConsola("El socio se dio de baja con exito");
                    } catch (SQLException ex) {
                        VistaMensajes.mensajeConsola("No se pudo dar de baja al socio", ex.getMessage());
                    }
                }
                break;

                case 4:
                    String numSocio = "none";
                    String campo = "none";
                    String valor = "none";

                     {
                        try {
                            sDAO.modificarSocio(numSocio, campo, valor);
                        } catch (SQLException ex) {
                            VistaMensajes.mensajeConsola("No se pudo modificar el socio", ex.getMessage());
                        }
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingresa un número válido.");
            }
        } while (opcion != 5);

    }
}
