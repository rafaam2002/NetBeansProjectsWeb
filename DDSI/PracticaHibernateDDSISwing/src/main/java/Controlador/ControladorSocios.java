/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Socio;
import Modelo.SocioDAO;
import Vista.JDialogInsertarMonitor;
import Vista.JDialogInsertarSocio;
import Vista.PanelSocios;
import Vista.VMensaje;
import Vista.VistaMensaje;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author rafaa
 */
public class ControladorSocios implements ActionListener {

    private final PanelSocios pSocios;
    private final UtilTablasSocio uTablasS;
    private final SessionFactory sessionFactory;
    private Session session;
    private Transaction tr;
    private final SocioDAO socioDAO;
    private final VMensaje vMensaje;
    private final JDialogInsertarSocio dialogoInsertarSocio;

    public ControladorSocios(PanelSocios pSocios, SessionFactory sessionFactory) {
        vMensaje = new VMensaje();
        this.pSocios = pSocios;
        uTablasS = new UtilTablasSocio(this.pSocios);

        //Inicializar dialogo insertar monitor
        dialogoInsertarSocio = new JDialogInsertarSocio();
        dialogoInsertarSocio.setLocationRelativeTo(null);
        dialogoInsertarSocio.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialogoInsertarSocio.setResizable(false);

        this.sessionFactory = sessionFactory;
        socioDAO = new SocioDAO();

        addListeners();

    }

    public void init() {
        dibujarTabla();
    }

    private void addListeners() {
        pSocios.jButtonNuevoSocio.addActionListener(this);
        pSocios.jButtonBajaSocio.addActionListener(this);
        pSocios.jButtonActualizarSocio.addActionListener(this);
        dialogoInsertarSocio.jButtonOkSocio.addActionListener(this);
        dialogoInsertarSocio.jButtonCancelarSocio.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "NuevoSocio" -> {
                session = sessionFactory.openSession();
                try {
                    String numSocio = socioDAO.DevolverUltimoCodigo(session);
                    var intUltimoSocio = Integer.parseInt(numSocio.split("S")[1]);
                    intUltimoSocio++;
                    String numNuevoSocio = "S";
                    if (intUltimoSocio < 10) {
                        numNuevoSocio += "00";
                    } else if (intUltimoSocio < 100) {
                        numNuevoSocio += "0";
                    }

                    numNuevoSocio += intUltimoSocio;

                    dialogoInsertarSocio.codigoSocio.setText(numNuevoSocio);
                    dialogoInsertarSocio.codigoSocio.setEditable(false);
                    dialogoInsertarSocio.setVisible(true);

                } catch (Exception ex) {
                    vMensaje.MensajeInfo(pSocios, ex.getMessage());

                    VistaMensaje.mensajeConsola("Error en la insercion de un nuevo socio " + ex.getMessage());
                }
            }

            case "OkSocio" -> {
                try {
                    session = sessionFactory.openSession();
                    tr = session.beginTransaction();

                    String numeroSocio = dialogoInsertarSocio.codigoSocio.getText();
                    String nombre = dialogoInsertarSocio.nombreSocio.getText();
                    String DNI = dialogoInsertarSocio.DNISocio.getText();
                    String telefono = dialogoInsertarSocio.telefonoSocio.getText();
                    String correo = dialogoInsertarSocio.correoSocio.getText();
                    String categoriaString = dialogoInsertarSocio.categoriaSocio.getText();
                    String fecha = "";

                    if (dialogoInsertarSocio.FechaInicioSocio.getDate() != null) {
                        fecha = Establecerfecha(dialogoInsertarSocio.FechaInicioSocio.getDate().toString());

                    }
                    //campos opigatorios
                    DNI = DNI.toUpperCase();
                    char categoria = categoriaString.charAt(0);
                    if (!nombre.isEmpty() && DNIValido(DNI) && fechaValida(fecha)) {
                        Socio s = new Socio(numeroSocio, nombre, DNI, fecha, categoria); //creamos el monitor con los campos obligatorios
                        if (telefonoValido(telefono)) {
                            s.setTelefono(telefono);
                        } else {
                            VistaMensaje.mensajeConsola("El telefono no es valido");

                        }
                        if (!correo.isEmpty()) {
                            s.setCorreo(correo);

                        } else {
                            VistaMensaje.mensajeConsola("El correo no esta relleno");

                        }

                        socioDAO.insertarSocio(session, s);
                    } else {
                        vMensaje.MensajeInfo(pSocios, "Debe rellenar todos los campos obligatorios correctamente");
                    }
                    tr.commit();
                } catch (Exception ex) {
                    tr.rollback();
                    vMensaje.MensajeInfo(pSocios, ex.getMessage());

                } finally {
                    if (session != null && session.isOpen()) {
                        session.close();
                    }
                    dialogoInsertarSocio.dispose();
                    dibujarTabla();
                    vaciarDatos();
                }

            }

            case "BajaSocio" -> {
                int filaSocio = pSocios.jTableSocios.getSelectedRow();
                if (filaSocio != -1) {
                    int confirm = BajaDialog(pSocios);
                    if (confirm == JOptionPane.YES_OPTION) {
                        try {
                            session = sessionFactory.openSession();
                            tr = session.beginTransaction();

                            List<Socio> socios = socioDAO.getSociosSortByNumSocio(session);
                            socioDAO.eliminarSocio(session, socios.get(filaSocio).getNumeroSocio());
                            tr.commit();
                            VistaMensaje.mensajeConsola("El socio se ha eliminado con exito");
//                            vMensaje.MensajeInfo(pSocios, "Socio dado de baja con exito");
                        } catch (Exception ex) {
                            tr.rollback();
                            VistaMensaje.mensajeConsola("Error en el delete de un socio " + ex.getMessage());
                            vMensaje.MensajeInfo(pSocios, "Error al eliminar socio " + ex.getMessage());
                        } finally {
                            if (session != null && session.isOpen()) {
                                session.close();
                            }

                            dibujarTabla();
                        }
                    }
                }
            }
            case "ActualizarSocio" -> {
                int filaSocio = pSocios.jTableSocios.getSelectedRow();
                if (filaSocio != -1) {
                    try {
                        session = sessionFactory.openSession();
                        tr = session.beginTransaction();

                        List<Socio> socios = socioDAO.listSociosSortByNumSocio(session);
                        Socio socio = socios.get(filaSocio);

                        dialogoInsertarSocio.codigoSocio.setText(socio.getNumeroSocio());
                        dialogoInsertarSocio.nombreSocio.setText(socio.getNombre());
                        dialogoInsertarSocio.DNISocio.setText(socio.getDni());
                        dialogoInsertarSocio.telefonoSocio.setText(socio.getTelefono());
                        dialogoInsertarSocio.correoSocio.setText(socio.getCorreo());
                        dialogoInsertarSocio.FechaInicioSocio.setDate(stringToDate(socio.getFechaEntrada()));
                        dialogoInsertarSocio.categoriaSocio.setText(socio.getCategoria().toString());
                        dialogoInsertarSocio.codigoSocio.setEditable(false);
                        dialogoInsertarSocio.setVisible(true);

//                        tr.commit();
//                        VistaMensaje.mensajeConsola("El socio se ha modificado con exito");
//                            vMensaje.MensajeInfo(pSocios, "Socio dado de baja con exito");
                    } catch (Exception ex) {
                        VistaMensaje.mensajeConsola("Error no se puede mostrar panel para"
                                + " modificar socio " + ex.getMessage());
                        vMensaje.MensajeInfo(pSocios, "Error al modificar monitor " + ex.getMessage());
                    }
                } else {
                    vMensaje.MensajeInfo(pSocios, "Para Actualizar un monitor debes seleccionarlo primero");
                }

            }

            default ->
                VistaMensaje.mensajeConsola("El boton pulsado no tiene una opcion definida");
        }
    }

    private void dibujarTabla() {
        uTablasS.dibujarTablaSocios();
        session = sessionFactory.openSession();

        tr = session.beginTransaction();

        try {
            ArrayList<Socio> socios = socioDAO.getSociosSortByNumSocio(session);
            uTablasS.vaciarTablaSocios();
            uTablasS.rellenarTablaSocios(socios);
            tr.commit();
        } catch (Exception ex) {
            tr.rollback();
            VistaMensaje.mensajeConsola("Error " + ex.getMessage());
        } finally {
            if (session != null & session.isOpen()) {
                session.close();
            }
        }
    }

    public int BajaDialog(Component C) {
        int opcion = JOptionPane.showConfirmDialog(C, "Deseas eliminar dicho Socio ?",
                "Atención", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        return opcion;
    }

    private String Establecerfecha(String s) {
        String[] aux = s.split(" ");

        String e = aux[2] + "/"; //Dia
        switch (aux[1]) {
            case "Jan" ->
                e += "01";
            case "Feb" ->
                e += "02";
            case "Mar" ->
                e += "03";
            case "Apr" ->
                e += "04";
            case "May" ->
                e += "05";
            case "Jun" ->
                e += "06";
            case "Jul" ->
                e += "07";
            case "Aug" ->
                e += "08";
            case "Sep" ->
                e += "09";
            case "Oct" ->
                e += "10";
            case "Nov" ->
                e += "11";
            case "Dec" ->
                e += "12";

        }
        e += "/" + aux[5];
        return e;
    }

    //comprueba que el dni tiene 9 caracteres y que el ultimo es una letra
    private boolean DNIValido(String DNI) {
        char letra = DNI.charAt(DNI.length() - 1);
        return DNI.length() == 9 && Character.isLetter(letra);
    }

    private boolean fechaValida(String fechaString) {

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        try {
            // Convertir el String a un objeto Date
            Date fecha = formatoFecha.parse(fechaString);
            Date fechaActual = new Date();

            // Comparar si la fecha es anterior o igual a la fecha actual
            return !fecha.after(fechaActual);
        } catch (ParseException ex) {
            System.out.println("Error al convertir la fecha: " + ex.getMessage());
            return false;
        }
    }

    private boolean telefonoValido(String telefono) {
        if (!telefono.isEmpty()) {
            return telefono.length() == 9;
        } else {
            return false;
        }
    }

    private Date stringToDate(String fechaString) throws ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.parse(fechaString);
    }

    private void vaciarDatos() {
        dialogoInsertarSocio.nombreSocio.setText("");
        dialogoInsertarSocio.DNISocio.setText("");
        dialogoInsertarSocio.FechaInicioSocio.setDate(null);
        dialogoInsertarSocio.correoSocio.setText("");
        dialogoInsertarSocio.telefonoSocio.setText("");
        dialogoInsertarSocio.categoriaSocio.setText("");
    }

}
