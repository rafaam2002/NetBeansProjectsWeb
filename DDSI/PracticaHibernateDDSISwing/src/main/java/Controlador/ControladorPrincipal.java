package Controlador;

import Modelo.Actividad;
import Modelo.MonitorDAO;
import Modelo.Socio;
import Modelo.SocioDAO;
import Vista.JDialogCuotaSocios;
import Vista.PanelActividades;
import Vista.VistaMensaje;
import Vista.PanelMonitores;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import Vista.vistaPrincipal;
import Vista.PanelPrincipal;
import Vista.PanelSocios;
import Vista.VMensaje;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaa
 */
public class ControladorPrincipal implements ActionListener {

    private final SessionFactory sessionFactory;
    private Session session;
    private Transaction tr;
    private final SocioDAO socioDAO;
    private final vistaPrincipal vistaP;
    private final PanelMonitores pMonitores;
    private final PanelPrincipal pPrincipal;
    private final PanelActividades pActividades;
    private final PanelSocios pSocios;
    private final VMensaje vMensaje;
    private final ControladorSocios controladorS;
    private final ControladorMonitor controladorM;
    private final ControladorActividades controladorA;
    private final JDialogCuotaSocios dialogoCuotaSocios;

//    private final vistaLogin vLoginMenu;
    public ControladorPrincipal(SessionFactory s) {

        //logica del Controlador
        sessionFactory = s;

        //Inixcializar Frame Principal
        vistaP = new vistaPrincipal();
        vistaP.getContentPane().setLayout(new CardLayout());
        vistaP.setLocationRelativeTo(null);
        vistaP.setVisible(true);

        //Inicializar y agregar al Frame Principal los Paneles
        pPrincipal = new PanelPrincipal();
        pMonitores = new PanelMonitores();
        pSocios = new PanelSocios();
        pActividades = new PanelActividades();
        vistaP.add(pPrincipal);
        vistaP.add(pMonitores);
        vistaP.add(pSocios);
        vistaP.add(pActividades);

        muestraPanel("pPrincipal");

        //inicializo otros controladores
        controladorS = new ControladorSocios(pSocios, sessionFactory);
        controladorM = new ControladorMonitor(pMonitores, sessionFactory);
        controladorA = new ControladorActividades(pActividades, sessionFactory);

        vMensaje = new VMensaje();
        vMensaje.MensajeInfo(pPrincipal, "Conexion correcta");

        //inicializo DAOs
        socioDAO = new SocioDAO();

        //inicializo ventanas de dialogo
        dialogoCuotaSocios = new JDialogCuotaSocios();

        //Los listeners siempre lo ultimo
        addListeners();
    }

    private void addListeners() {
        vistaP.GestionMonitores.addActionListener(this);
        vistaP.SalirAplicacion.addActionListener(this);
        vistaP.GestionSocios.addActionListener(this);
        vistaP.VolverPrincipal.addActionListener(this);
        vistaP.GestionActividades.addActionListener(this);
        vistaP.CuotaSocios.addActionListener(this);
        dialogoCuotaSocios.jButtonVerCuotaSocio.addActionListener(this);
        dialogoCuotaSocios.jButtonCerrarCuotaSocio.addActionListener(this);
        dialogoCuotaSocios.jButtonVerCuotaSocio.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "SalirAplicacion" -> {
                vistaP.dispose();
                System.exit(0);
            }
            case "GestionMonitores" -> {

                muestraPanel("pMonitores");
                controladorM.init();
            }
            case "GestionSocios" -> {
                muestraPanel("pSocios");
                controladorS.init();
            }
            case "GestionActividades" -> {
                muestraPanel("pActividades");
                controladorA.init();
            }
            case "VolverPrincipal" -> {
                muestraPanel("pPrincipal");

            }
            //Preparacion del dialogo cuotaSocios
            case "CuotaSocios" -> {
                dialogoCuotaSocios.codigoSocio.setText("");
                dialogoCuotaSocios.jLabelCuotaMensualSocio.setText("");
                dialogoCuotaSocios.jLabelNumActividadesSocio.setText("");
                dialogoCuotaSocios.jLabelLabelCuotaMensualSocio.setText("");
                dialogoCuotaSocios.jLabelLabelNumActividadesSocio.setText("");
                dialogoCuotaSocios.setLocationRelativeTo(vistaP);
                dialogoCuotaSocios.setVisible(true);
            }
            //cierre de dialogo cuotaSocios
            case "CerrarCuotaSocios" -> {
                dialogoCuotaSocios.dispose();
            }
            //Boton verCuota del dialog cuotaSocios
            case "VerCuota" -> {
                String numSocio = dialogoCuotaSocios.codigoSocio.getText();
                try {
                    session = sessionFactory.openSession();
                    tr = session.beginTransaction();
                    Socio socio = socioDAO.getSocioByNumSocio(session, numSocio);
                    tr.commit();

                    double cuotaMensualBase = 0;

                    for (Actividad actividad : socio.getActividades()) {
                        double cuotaActividad = actividad.getPrecioBaseMes();
                        cuotaMensualBase += cuotaActividad;
                    }
                    double cuotaMensualConDescuento = 0;
                    switch (socio.getCategoria()) {
                        case 'B' -> {
                            cuotaMensualConDescuento = cuotaMensualBase * 0.9;
                        }
                        case 'C' -> {
                            cuotaMensualConDescuento = cuotaMensualBase * 0.8;
                        }
                        case 'D' -> {
                            cuotaMensualConDescuento = cuotaMensualBase * 0.7;
                        }
                        case 'E' -> {
                            cuotaMensualConDescuento = cuotaMensualBase * 0.6;
                        }
                        default ->
                            cuotaMensualConDescuento = cuotaMensualBase;
                    }
                    dialogoCuotaSocios.jLabelLabelNumActividadesSocio.setText("Número de Actividades");
                    dialogoCuotaSocios.jLabelLabelCuotaMensualSocio.setText("Cuota Mensual");
                    dialogoCuotaSocios.jLabelCuotaMensualSocio.setText(Double.toString(cuotaMensualConDescuento));
                    dialogoCuotaSocios.jLabelNumActividadesSocio.setText(Integer.toString(socio.getActividades().size()));
                    
                } catch (Exception ex) {
                    tr.rollback();
                    vMensaje.MensajeInfo(pSocios, ex.getMessage());
                } finally {
                    if (session != null && session.isOpen()) {
                        session.close();
                    }

                }

            }
            default -> {
                VistaMensaje.mensajeConsola("No se ha reconocido el evento, revisa los nombres de las variables de las vista");
            }

        }
    }

    private void muestraPanel(String panel) {

        pPrincipal.setVisible(false);
        pMonitores.setVisible(false);
        pSocios.setVisible(false);
        pActividades.setVisible(false);

        switch (panel) {
            case "pMonitores" -> {
                pMonitores.setVisible(true);
            }
            case "pSocios" -> {
                pSocios.setVisible(true);
            }
            case "pPrincipal" -> {
                pPrincipal.setVisible(true);
            }
            case "pActividades" -> {
                pActividades.setVisible(true);
            }
            default -> {
                VistaMensaje.mensajeConsola("El panel indicado no existe");
            }
        }
    }

}
