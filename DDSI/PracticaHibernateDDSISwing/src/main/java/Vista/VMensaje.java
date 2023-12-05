/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.awt.Component;
import java.util.Locale;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author rafaelMesaNombela
 */
public class VMensaje {

    public void mensajeConsola(String texto) {

        JOptionPane.showMessageDialog(null, texto);
    }

    public void MensajeInfo(Component C, String texto) {

        //Se cre un objeto porque si no no se posicionar el mensaje en el centro, se me crea arriba a la derecha
        JOptionPane optionPane = new JOptionPane(texto, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog(C, "Información");

        dialog.setLocationRelativeTo(null); // También puedes usar C en lugar de null
        dialog.setVisible(true);

//        JOptionPane.showMessageDialog(C, texto, "Información",
//                JOptionPane.INFORMATION_MESSAGE);

    }
}
