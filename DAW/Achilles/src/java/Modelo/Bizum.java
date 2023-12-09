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
    @NamedQuery(name = "Bizum.findAll", query = "SELECT b FROM Bizum b"),
    @NamedQuery(name = "Bizum.findById", query = "SELECT b FROM Bizum b WHERE b.id = :id"),
    @NamedQuery(name = "Bizum.findSentBizumByUserId", query = "SELECT b FROM Bizum b WHERE b.emiBizum.id = :userId"),
    @NamedQuery(name = "Bizum.findReceivedBizumByUserId", query = "SELECT b FROM Bizum b WHERE b.recBizum.id = :userId")
})
public class Bizum implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "Emisor")
    private Usuario emiBizum;
    
    @ManyToOne
    @JoinColumn(name = "Receptor")
    private Usuario recBizum;
    

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
        if (!(object instanceof Bizum)) {
            return false;
        }
        Bizum other = (Bizum) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Bizum[ id=" + id + " ]";
    }
    
}
