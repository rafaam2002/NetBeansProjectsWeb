package Socket;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author rafaa
 */
public class Mensaje {

    private String nEmisor;
    private String nReceptor;
    private String text;
    private String fecha;
   

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Mensaje() {
    }

    public String getnEmisor() {
        return nEmisor;
    }

    public void setnEmisor(String nEmisor) {
        this.nEmisor = nEmisor;
    }

    public String getnReceptor() {
        return nReceptor;
    }

    public void setnReceptor(String nReceptor) {
        this.nReceptor = nReceptor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.nEmisor + this.nReceptor; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
  
}
