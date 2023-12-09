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

/**
 *
 * @author rafaa
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Transferencia.findAll", query = "SELECT t FROM Transferencia t"),
    @NamedQuery(name = "Transferencia.findById", query = "SELECT t FROM Transferencia t WHERE t.id = :id"),
    @NamedQuery(name = "Transferencia.findSentTransfersByUserId", query = "SELECT t FROM Transferencia t WHERE t.emiTransferencia.id = :userId"),
    @NamedQuery(name = "Transferencia.findReceivedTransfersByUserId", query = "SELECT t FROM Transferencia t WHERE t.recTransferencia.id = :userId")
})
public class Transferencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }
    
    @ManyToOne
    @JoinColumn(name = "Emisor")
    private Usuario emiTransferencia;
    
    @ManyToOne
    @JoinColumn(name = "Receptor")
    private Usuario recTransferencia;

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
        if (!(object instanceof Transferencia)) {
            return false;
        }
        Transferencia other = (Transferencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Transferencia[ id=" + id + " ]";
    }
    
}
