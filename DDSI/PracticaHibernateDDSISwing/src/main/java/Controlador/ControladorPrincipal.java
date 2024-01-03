package Controlador;

import Modelo.Monitor;
import Modelo.MonitorDAO;
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

/**
 *
 * @author rafaa
 */
public class ControladorPrincipal implements ActionListener {

    private final SessionFactory sessionFactory;
    private Session session;
    private final vistaPrincipal vistaP;
    private final PanelMonitores pMonitores;
    private final PanelPrincipal pPrincipal;
    private final PanelSocios pSocios;
    private final UtilTablasMonitor uTablasM;
    private final MonitorDAO monitorDAO;
    private  final VMensaje vMensaje;

    private final ControladorSocios controladorS;
    private final ControladorMonitor controladorM;

//    private final vistaLogin vLoginMenu;
    public ControladorPrincipal(SessionFactory s) {

        //Inixcializar Frame Principal
        vistaP = new vistaPrincipal();
        vistaP.getContentPane().setLayout(new CardLayout());
        vistaP.setLocationRelativeTo(null);
        vistaP.setVisible(true);
        addListeners();

        //Inicializar y agregar al Frame Principal los Paneles
        pPrincipal = new PanelPrincipal();
        pMonitores = new PanelMonitores();
        pSocios = new PanelSocios();
        vistaP.add(pPrincipal);
        vistaP.add(pMonitores);
        vistaP.add(pSocios);

        muestraPanel("pPrincipal");

        //Inicializar las clases que se encargan de las tablas
        uTablasM = new UtilTablasMonitor(pMonitores);

        //logica del Controlador
        sessionFactory = s;
        monitorDAO = new MonitorDAO();

        //inicializo otros controladores
        controladorS = new ControladorSocios(pSocios, sessionFactory);
        controladorM = new ControladorMonitor(pMonitores, sessionFactory);
        
        vMensaje = new VMensaje();
        vMensaje.MensajeInfo(pPrincipal, "Conexion correcta");
        
        

    }

    private void addListeners() {
        vistaP.GestionMonitores.addActionListener(this);
        vistaP.SalirAplicacion.addActionListener(this);
        vistaP.GestionSocios.addActionListener(this);
        vistaP.VolverPrincipal.addActionListener(this);
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
            case "VolverPrincipal" -> {
                muestraPanel("pPrincipal");
                
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
            default -> {
                VistaMensaje.mensajeConsola("El panel indicado no existe");
            }
        }
    }

}
