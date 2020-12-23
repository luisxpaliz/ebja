/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.educacion.ebja.modelo.zeus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "distrito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Distrito.findAll", query = "SELECT d FROM Distrito d")
    , @NamedQuery(name = "Distrito.findById", query = "SELECT d FROM Distrito d WHERE d.id = :id")
    , @NamedQuery(name = "Distrito.findByZona", query = "SELECT d FROM Distrito d WHERE d.idZona.id = :idZona and d.estado = :estado")
    , @NamedQuery(name = "Distrito.findByCodigoSenpladesDistrito", query = "SELECT d FROM Distrito d WHERE d.codigoSenpladesDistrito = :codigoSenpladesDistrito")
    , @NamedQuery(name = "Distrito.findByNombreDistrito", query = "SELECT d FROM Distrito d WHERE d.nombreDistrito = :nombreDistrito")
    , @NamedQuery(name = "Distrito.findByDescripcion", query = "SELECT d FROM Distrito d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "Distrito.findByFechaCreacion", query = "SELECT d FROM Distrito d WHERE d.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Distrito.findByEstado", query = "SELECT d FROM Distrito d WHERE d.estado = :estado")
    , @NamedQuery(name = "Distrito.findByFechaMigracion", query = "SELECT d FROM Distrito d WHERE d.fechaMigracion = :fechaMigracion")})
public class Distrito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "codigo_senplades_distrito")
    private String codigoSenpladesDistrito;
    @Size(max = 25)
    @Column(name = "nombre_distrito")
    private String nombreDistrito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_migracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMigracion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDistrito", fetch = FetchType.LAZY)
    private List<Circuito> circuitoList;
    @JoinColumn(name = "id_zona", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Zona idZona;

    public Distrito() {
    }

    public Distrito(Short id) {
        this.id = id;
    }

    public Distrito(Short id, String codigoSenpladesDistrito, String descripcion, Date fechaCreacion, String estado) {
        this.id = id;
        this.codigoSenpladesDistrito = codigoSenpladesDistrito;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getCodigoSenpladesDistrito() {
        return codigoSenpladesDistrito;
    }

    public void setCodigoSenpladesDistrito(String codigoSenpladesDistrito) {
        this.codigoSenpladesDistrito = codigoSenpladesDistrito;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaMigracion() {
        return fechaMigracion;
    }

    public void setFechaMigracion(Date fechaMigracion) {
        this.fechaMigracion = fechaMigracion;
    }

    @XmlTransient
    public List<Circuito> getCircuitoList() {
        return circuitoList;
    }

    public void setCircuitoList(List<Circuito> circuitoList) {
        this.circuitoList = circuitoList;
    }

    public Zona getIdZona() {
        return idZona;
    }

    public void setIdZona(Zona idZona) {
        this.idZona = idZona;
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
        if (!(object instanceof Distrito)) {
            return false;
        }
        Distrito other = (Distrito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.educacion.ebja.modelo.zeus.Distrito[ id=" + id + " ]";
    }
    
}
