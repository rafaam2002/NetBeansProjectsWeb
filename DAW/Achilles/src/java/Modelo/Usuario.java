/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author rafaa
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findContactsById", query = "SELECT u.contactos FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findSentMessagesById", query = "SELECT u.mEnviados FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findReceivedMessagesById", query = "SELECT u.mRecividos FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findSentBizumById", query = "SELECT u.bEnviados FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findReceivedBizumById", query = "SELECT u.bRecividos FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findSentTransfersById", query = "SELECT u.tEnviadas FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findNickById", query = "SELECT u.nick FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByNick", query = "SELECT u FROM Usuario u WHERE u.nick = :nick"),
    @NamedQuery(name = "Usuario.findByNumTel", query = "SELECT u FROM Usuario u WHERE u.numTel = :numTel"),
    @NamedQuery(name = "Usuario.findByNumCuenta", query = "SELECT u FROM Usuario u WHERE u.numCuenta = :numCuenta"),
})

public class Usuario implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nick;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false)
    private String numTel;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private long numCuenta;
    private Double dineroDouble;
    @Column(nullable = false)
    private boolean bizumActive;

    @ManyToMany
    @JoinTable(name = "Contacto", joinColumns = {
        @JoinColumn(name = "Usuario1")}, inverseJoinColumns = {
        @JoinColumn(name = "Usuario2")})
    private List<Usuario> contactos;

    @OneToMany(mappedBy = "emiMensaje")
    private List<Mensaje> mEnviados;

    @OneToMany(mappedBy = "recMensaje")
    private List<Mensaje> mRecividos;

    @OneToMany(mappedBy = "emiBizum")
    private List<Bizum> bEnviados;

    @OneToMany(mappedBy = "recBizum")
    private List<Bizum> bRecividos;

    @OneToMany(mappedBy = "emiTransferencia")
    private List<Transferencia> tEnviadas;

    @OneToMany(mappedBy = "recTransferencia")
    private List<Transferencia> tRecividas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Usuario[ id=" + getId() + " ]";
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the nick
     */
    public String getNick() {
        return nick;
    }

    /**
     * @param nick the nick to set
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the numTel
     */
    public String getNumTel() {
        return numTel;
    }

    /**
     * @param numTel the numTel to set
     */
    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the numCuenta
     */
    public long getNumCuenta() {
        return numCuenta;
    }

    /**
     * @param numCuenta the numCuenta to set
     */
    public void setNumCuenta(long numCuenta) {
        this.numCuenta = numCuenta;
    }

    /**
     * @return the dineroDouble
     */
    public Double getDineroDouble() {
        return dineroDouble;
    }

    /**
     * @param dineroDouble the dineroDouble to set
     */
    public void setDineroDouble(Double dineroDouble) {
        this.dineroDouble = dineroDouble;
    }

    /**
     * @return the bizumActive
     */
    public boolean isBizumActive() {
        return bizumActive;
    }

    /**
     * @param bizumActive the bizumActive to set
     */
    public void setBizumActive(boolean bizumActive) {
        this.bizumActive = bizumActive;
    }

    /**
     * @return the contactos
     */
    public List<Usuario> getContactos() {
        return contactos;
    }

    /**
     * @param contactos the contactos to set
     */
    public void setContactos(List<Usuario> contactos) {
        this.contactos = contactos;
    }

    /**
     * @return the mEnviados
     */
    public List<Mensaje> getmEnviados() {
        return mEnviados;
    }

    /**
     * @param mEnviados the mEnviados to set
     */
    public void setmEnviados(List<Mensaje> mEnviados) {
        this.mEnviados = mEnviados;
    }

    /**
     * @return the mRecividos
     */
    public List<Mensaje> getmRecividos() {
        return mRecividos;
    }

    /**
     * @param mRecividos the mRecividos to set
     */
    public void setmRecividos(List<Mensaje> mRecividos) {
        this.mRecividos = mRecividos;
    }

    /**
     * @return the bEnviados
     */
    public List<Bizum> getbEnviados() {
        return bEnviados;
    }

    /**
     * @param bEnviados the bEnviados to set
     */
    public void setbEnviados(List<Bizum> bEnviados) {
        this.bEnviados = bEnviados;
    }

    /**
     * @return the bRecividos
     */
    public List<Bizum> getbRecividos() {
        return bRecividos;
    }

    /**
     * @param bRecividos the bRecividos to set
     */
    public void setbRecividos(List<Bizum> bRecividos) {
        this.bRecividos = bRecividos;
    }

    /**
     * @return the tEnviadas
     */
    public List<Transferencia> gettEnviadas() {
        return tEnviadas;
    }

    /**
     * @param tEnviadas the tEnviadas to set
     */
    public void settEnviadas(List<Transferencia> tEnviadas) {
        this.tEnviadas = tEnviadas;
    }

    /**
     * @return the tRecividas
     */
    public List<Transferencia> gettRecividas() {
        return tRecividas;
    }

    /**
     * @param tRecividas the tRecividas to set
     */
    public void settRecividas(List<Transferencia> tRecividas) {
        this.tRecividas = tRecividas;
    }

}
