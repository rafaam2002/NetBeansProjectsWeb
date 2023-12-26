
package Objetos;

/**
 *
 * @author rafaa
 */
public class Mensaje {

    private String nEmisor;
    private String nReceptor;
    private String mensaje;

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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
