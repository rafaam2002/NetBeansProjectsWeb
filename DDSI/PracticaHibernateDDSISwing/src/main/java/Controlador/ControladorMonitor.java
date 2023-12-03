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
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
                    } else {
                        codNuevoMonitor += "0";
                    }

                    codNuevoMonitor += numUltimoMonitor;

                    dialogoInsertaMonitor.codigoMonitor.setText(codNuevoMonitor);
                    dialogoInsertaMonitor.codigoMonitor.setEditable(false);
                    dialogoInsertaMonitor.setVisible(true);
                } catch (Exception ex) {
                    vMensaje.MensajeInfo(pMonitores, ex.getMessage());
                } finally {
                    if (session != null && session.isOpen()) {
                        session.close();
                    }
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
                    String correo = dialogoInsertaMonitor.codigoMonitor.getText();
                    String nick = dialogoInsertaMonitor.nickMonitor.getText();
                    String fecha = "";

                    if (dialogoInsertaMonitor.FechaNacMonitor.getDate() != null) {
                        fecha = Establecerfecha(dialogoInsertaMonitor.FechaNacMonitor.getDate().toString());
                    }
                    //campos opigatorios
                    DNI = DNI.toUpperCase(); 
                    System.out.println(DNI);
                    if (!nombre.isEmpty() && DNIValido(DNI) && fechaValida(fecha)) {
                        Monitor m = new Monitor(codigo, nombre, DNI, fecha); //creamos el monitor con los campos obligatorios
                        if (telefonoValido(telefono)) {
                            m.setTelefono(telefono);
                        }
                        if (!correo.isEmpty()) {
                            m.setCorreo(correo);
                        }
                        if (!nick.isEmpty()) {
                            m.setNick(nick);
                        }
                        monitorDAO.insertarActualizarMonitor(session, m);
                    } else {
                        vMensaje.MensajeInfo(pMonitores, "Debe rellenar todos los campos obligatorios correctamente");
                    }
                    tr.commit();
                } catch (Exception ex) {
                    tr.rollback();
                    vMensaje.MensajeInfo(pMonitores, ex.getMessage());

                } finally {
                    if (session != null && session.isOpen()) {
                        session.close();
                    }
                    dialogoInsertaMonitor.dispose();
                    dibujarTabla();
                    vaciarDatos();
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
            ArrayList<Monitor> monitores = pideMonitores();
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

    private ArrayList<Monitor> pideMonitores() throws Exception {
        ArrayList<Monitor> monitores = monitorDAO.listaMonitores(session);
        return monitores;
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
//        letra = Character.toUpperCase(letra);
//        DNI = DNI.substring(0, DNI.length() - 2);
//        DNI += Character.toString(letra);

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
        dialogoInsertaMonitor.FechaNacMonitor.setDate(null);
        dialogoInsertaMonitor.correoMonitor.setText("");
        dialogoInsertaMonitor.nickMonitor.setText("");
        dialogoInsertaMonitor.telefonoMonitor.setText("");
    }

}
