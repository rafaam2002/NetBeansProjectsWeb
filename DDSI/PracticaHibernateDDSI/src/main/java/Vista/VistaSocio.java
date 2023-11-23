/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Modelo.Socio;

/**
 *
 * @author rafaa
 */
public class VistaSocio {

    public static void muestraMenu() {
        System.out.println("Menú de opciones:");
        System.out.println("1. Información de los socios (HQL)");
        System.out.println("2. Información de los socios (SQL Nativo)");
        System.out.println("3. Información de los socios (Consulta nombrada)");
        System.out.println("4. Nombre y teléfono de los socios");
        System.out.println("5. Nombre y categoría de los socios");
        System.out.println("6. Responsable de una actividad");
        System.out.println("7. Socios de una actividad");
        System.out.println("8. Alta de un socio");
        System.out.println("9. Baja de un socio");
        System.out.println("10. Actualización de la categoría de un socio");
        System.out.println("11. Inscripción de un socio en una actividad");
        System.out.println("12. Baja de un socio de una actividad");
        System.out.println("13. Listado de socios inscritos en una actividad");
        System.out.println("14. Listado de actividades de un socio");
        System.out.println("0. Salir");
        System.out.print("Ingrese el número de la opción deseada: ");
    }

    public static void muestraSocio(Socio socio) {
        System.out.println(socio.getNumeroSocio() + "\t" + socio.getNombre() + "\t" + socio.getDni()
                + "\t" + socio.getCorreo() + "\t" + socio.getFechaEntrada()
                + "\t" + socio.getFechaNacimiento()
                + "\t" + socio.getCategoria() + "\t" + socio.getTelefono());
    }

    public static void muestraCampos(Object[] o) {
        for (Object campo : o) {
            System.out.print(campo + "\t");
        }
        System.out.println("");
    }

}
