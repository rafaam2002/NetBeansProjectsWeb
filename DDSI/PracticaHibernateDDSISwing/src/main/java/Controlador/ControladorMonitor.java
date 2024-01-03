/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Monitor;
import Modelo.MonitorDAO;
import Modelo.Socio;
import Vista.JDialogInsertarMonitor;
import Vista.PanelMonitores;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author rafaelMesaNombela
 */
public class ControladorMonitor implements ActionListener {

    private final PanelMonitores pMonitores;
    private final UtilTablasMonitor uTablasM;
    private final SessionFactory sessionFactory;
    private Session session;
    private Transaction tr;
    private final MonitorDAO monitorDAO;
    private final VMensaje vMensaje;
    private final JDialogInsertarMonitor dialogoInsertaMonitor;

    public ControladorMonitor(PanelMonitores pMonitores, SessionFactory sessionFactory) {
        vMensaje = new VMensaje();
        this.pMonitores = pMonitores;
        uTablasM = new UtilTablasMonitor(this.pMonitores);
        this.sessionFactory = sessionFactory;
        monitorDAO = new MonitorDAO();

        //Inicializar dialogo insertar monitor
        dialogoInsertaMonitor = new JDialogInsertarMonitor();
        dialogoInsertaMonitor.setLocationRelativeTo(null);
        dialogoInsertaMonitor.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialogoInsertaMonitor.setResizable(false);

        addListeners();
    }

    private void addListeners() {
        pMonitores.jButtonNuevoMonitor.addActionListener(this);
        pMonitores.jButtonBajaMonitor.addActionListener(this);
        pMonitores.jButtonActualizacionMonitor.addActionListener(this);
        dialogoInsertaMonitor.jButtomInsertarMonitor.addActionListener(this);
        dialogoInsertaMonitor.jButtonCancelarMonitor.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "NuevoMonitor" -> {
                session = sessionFactory.openSession();
                try {
                    String codMonitor = monitorDAO.DevolverUltimoCodigo(session);
                    var numUltimoMonitor = Integer.parseInt(codMonitor.split("M")[1]);
                    numUltimoMonitor++;
                    String codNuevoMonitor = "M";
                    if (numUltimoMonitor < 10) {
                        codNuevoMonitor += "00";
                    } else if (numUltimoMonitor < 100) {
                        codNuevoMonitor += "0";
                    }

                    codNuevoMonitor += numUltimoMonitor;

                    dialogoInsertaMonitor.codigoMonitor.setText(codNuevoMonitor);
                    dialogoInsertaMonitor.codigoMonitor.setEditable(false);
                    dialogoInsertaMonitor.setVisible(true);
                } catch (Exception ex) {
                    vMensaje.MensajeInfo(pMonitores, ex.getMessage());
                }

            }
            case "InsertarMonitor" -> {
                try {
                    session = sessionFactory.openSession();
                    tr = session.beginTransaction();

                    String codigo = dialogoInsertaMonitor.codigoMonitor.getText();
                    String nombre = dialogoInsertaMonitor.nombreMonitor.getText();
                    String DNI = dialogoInsertaMonitor.DNIMonitor.getText();
                    String telefono = dialogoInsertaMonitor.telefonoMonitor.getText();
                    String correo = dialogoInsertaMonitor.correoMonitor.getText();
                    String nick = dialogoInsertaMonitor.nickMonitor.getText();
                    String fecha = "";

                    if (dialogoInsertaMonitor.FechaInicioMonitor.getDate() != null) {
                        fecha = Establecerfecha(dialogoInsertaMonitor.FechaInicioMonitor.getDate().toString());

                    }
                    //campos opigatorios
                    DNI = DNI.toUpperCase();
                    if (!nombre.isEmpty() && DNIValido(DNI) && fechaValida(fecha)) {
                        Monitor m = new Monitor(codigo, nombre, DNI, fecha); //creamos el monitor con los campos obligatorios
                        if (telefonoValido(telefono)) {
                            m.setTelefono(telefono);
                        } else {
                            VistaMensaje.mensajeConsola("El telefono no es valido");
                            vMensaje.MensajeInfo(pMonitores, "El telefono no tiene los dígitos necesarios");
                        }
                        if (correoValido(correo)) {
                            m.setCorreo(correo);
                        } else {
                            VistaMensaje.mensajeConsola("El correo no esta relleno");
                            vMensaje.MensajeInfo(pMonitores, "El correo no cumple un patón válido");
                        }
                        if (!nick.isEmpty()) {
                            m.setNick(nick);
                        } else {
                            VistaMensaje.mensajeConsola("El nick no es valido");
                        }
                        monitorDAO.insertarActualizarMonitor(session, m);
                        tr.commit();
                        dialogoInsertaMonitor.dispose();
                        dibujarTabla();
                        vaciarDatos();
                    } else {
                        vMensaje.MensajeInfo(pMonitores, "Debe rellenar todos los campos obligatorios correctamente");
                    }

                } catch (Exception ex) {
                    tr.rollback();
                    vMensaje.MensajeInfo(pMonitores, ex.getMessage());

                } finally {
                    if (session != null && session.isOpen()) {
                        session.close();
                    }

                }

            }

            case "BajaMonitor" -> {
                int filaMonitor = pMonitores.jTableMonitores.getSelectedRow();
                if (filaMonitor != -1) {
                    int confirm = BajaDialog(pMonitores);
                    if (confirm == JOptionPane.YES_OPTION) {
                        try {
                            session = sessionFactory.openSession();
                            tr = session.beginTransaction();

                            List<Monitor> monitores = monitorDAO.listMonitoresSortByNumMonitor(session);
                            monitorDAO.eliminarMonitor(session, monitores.get(filaMonitor).getCodMonitor());
                            tr.commit();
                            VistaMensaje.mensajeConsola("El monitor se ha eliminado con exito");
//                            vMensaje.MensajeInfo(pSocios, "Socio dado de baja con exito");
                        } catch (Exception ex) {
                            tr.rollback();
                            VistaMensaje.mensajeConsola("Error en el delete de un monitor " + ex.getMessage());
                            vMensaje.MensajeInfo(pMonitores, "Error al eliminar monitor " + ex.getMessage());
                        } finally {
                            if (session != null && session.isOpen()) {
                                session.close();
                            }
                            dibujarTabla();
                        }
                    }
                } else {
                    vMensaje.MensajeInfo(pMonitores, "Debe seleccionar un monitor");
                }
            }

            case "ActualizarMonitor" -> {
                int filaMonitor = pMonitores.jTableMonitores.getSelectedRow();
                if (filaMonitor != -1) {
                    try {
                        session = sessionFactory.openSession();
                        tr = session.beginTransaction();

                        List<Monitor> monitores = monitorDAO.listMonitoresSortByNumMonitor(session);
                        Monitor monitor = monitores.get(filaMonitor);

                        dialogoInsertaMonitor.codigoMonitor.setText(monitor.getCodMonitor());
                        dialogoInsertaMonitor.nombreMonitor.setText(monitor.getNombre());
                        dialogoInsertaMonitor.DNIMonitor.setText(monitor.getDni());
                        dialogoInsertaMonitor.telefonoMonitor.setText(monitor.getTelefono());
                        dialogoInsertaMonitor.correoMonitor.setText(monitor.getCorreo());
                        dialogoInsertaMonitor.nickMonitor.setText(monitor.getNick());
                        dialogoInsertaMonitor.FechaInicioMonitor.setDate(stringToDate(monitor.getFechaEntrada()));
                        dialogoInsertaMonitor.codigoMonitor.setEditable(false);
                        dialogoInsertaMonitor.setVisible(true);

                    } catch (Exception ex) {

                        VistaMensaje.mensajeConsola("Error al mostrar desplegable " + ex.getMessage());
                        vMensaje.MensajeError(pMonitores, "Error al mostrar desplegable " + ex.getMessage());
                    }
                } else {
                    vMensaje.MensajeInfo(pMonitores, "Para Actualizar un monitor debes seleccionarlo primero");
                }

            }

            default ->
                Vista.VistaMensaje.mensajeConsola("La accion de ese boton no esta registrada");
        }

    }

    public void init() {
        dibujarTabla();
    }

    private void dibujarTabla() {
        uTablasM.dibujarTablaMonitores();
        session = sessionFactory.openSession();
        tr = session.beginTransaction();
        try {
            ArrayList<Monitor> monitores = monitorDAO.listMonitoresSortByNumMonitor(session);
            uTablasM.vaciarTablaMonitores();
            uTablasM.rellenarTablaMonitores(monitores);
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

    //comprueba que el dni tiene 9 caracteres y que el ultimo es una letra
    private boolean DNIValido(String DNI) {
        char letra = DNI.charAt(DNI.length() - 1);
        return DNI.length() == 9 && Character.isLetter(letra);
    }

    private boolean telefonoValido(String telefono) {
        if (!telefono.isEmpty()) {
            return telefono.length() == 9;
        } else {
            return false;
        }
    }

    private void vaciarDatos() {
        dialogoInsertaMonitor.nombreMonitor.setText("");
        dialogoInsertaMonitor.DNIMonitor.setText("");
        dialogoInsertaMonitor.FechaInicioMonitor.setDate(null);
        dialogoInsertaMonitor.correoMonitor.setText("");
        dialogoInsertaMonitor.nickMonitor.setText("");
        dialogoInsertaMonitor.telefonoMonitor.setText("");
    }

    public int BajaDialog(Component C) {
        int opcion = JOptionPane.showConfirmDialog(C, "Deseas eliminar dicho Monitor ?",
                "Atención", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        return opcion;
    }

    private Date stringToDate(String fechaString) throws ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.parse(fechaString);
    }

    /**
     * Valida la forma de una dirección de correo
     *
     * @param email cadena de texto con el email a validar
     * @return
     */
    private static boolean correoValido(String correo) {
        String patronCorreo = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(patronCorreo);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

}
