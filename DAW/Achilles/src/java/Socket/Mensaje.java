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
    private String identificador;

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

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
        return this.nEmisor + this.nReceptor +this.text + this.fecha; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mensaje other = (Mensaje) obj;
        if (!Objects.equals(this.nEmisor, other.nEmisor)) {
            return false;
        }
        if (!Objects.equals(this.nReceptor, other.nReceptor)) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        return Objects.equals(this.fecha, other.fecha);
    }

   
    
    

}
