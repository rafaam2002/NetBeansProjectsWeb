/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author rafaa
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Mensaje.findAll", query = "SELECT m FROM Mensaje m"),
    @NamedQuery(name = "Mensaje.findById", query = "SELECT m FROM Mensaje m WHERE m.id = :id"),
    @NamedQuery(name = "Mensaje.findSentMessagesByUserId", query = "SELECT m FROM Mensaje m WHERE m.emiMensaje.id = :userId"),
    @NamedQuery(name = "Mensaje.findReceivedMessagesByUserId", query = "SELECT m FROM Mensaje m WHERE m.recMensaje.id = :userId")
})
public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String mensaje;
    
    @ManyToOne
    @JoinColumn(name = "Emisor")
    private Usuario emiMensaje;
    
    @ManyToOne
    @JoinColumn (name = "Receptor")
    private Usuario recMensaje;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensaje)) {
            return false;
        }
        Mensaje other = (Mensaje) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Mensaje[ id=" + id + " ]";
    }
    
}
