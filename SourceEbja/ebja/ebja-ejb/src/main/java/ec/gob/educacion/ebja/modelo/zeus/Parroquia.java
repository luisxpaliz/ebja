package ec.gob.educacion.ebja.modelo.zeus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
@Table(name = "parroquia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parroquia.findAll", query = "SELECT p FROM Parroquia p")
    , @NamedQuery(name = "Parroquia.findById", query = "SELECT p FROM Parroquia p WHERE p.id = :id")
    , @NamedQuery(name = "Parroquia.findByCodigoParroquia", query = "SELECT p FROM Parroquia p WHERE p.codigoParroquia = :codigoParroquia")
    , @NamedQuery(name = "Parroquia.findByDescripcion", query = "SELECT p FROM Parroquia p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Parroquia.findByZona", query = "SELECT p FROM Parroquia p WHERE p.zona = :zona")
    , @NamedQuery(name = "Parroquia.findByFechaCreacion", query = "SELECT p FROM Parroquia p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Parroquia.findByEstado", query = "SELECT p FROM Parroquia p WHERE p.estado = :estado")
    , @NamedQuery(name = "Parroquia.findByFechaMigracion", query = "SELECT p FROM Parroquia p WHERE p.fechaMigracion = :fechaMigracion")})
public class Parroquia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "codigo_parroquia")
    private String codigoParroquia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "zona")
    private Character zona;
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idParroquia", fetch = FetchType.EAGER)
    private Set<CircuitoParroquia> circuitoParroquiaList;
    
    @JoinColumn(name = "id_canton", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Canton idCanton;

    public Parroquia() {
    }

    public Parroquia(Short id) {
        this.id = id;
    }

    public Parroquia(Short id, String codigoParroquia, String descripcion, Date fechaCreacion, String estado) {
        this.id = id;
        this.codigoParroquia = codigoParroquia;
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

    public String getCodigoParroquia() {
        return codigoParroquia;
    }

    public void setCodigoParroquia(String codigoParroquia) {
        this.codigoParroquia = codigoParroquia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Character getZona() {
        return zona;
    }

    public void setZona(Character zona) {
        this.zona = zona;
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
    public Set<CircuitoParroquia> getCircuitoParroquiaList() {
        return circuitoParroquiaList;
    }

    public void setCircuitoParroquiaList(Set<CircuitoParroquia> circuitoParroquiaList) {
        this.circuitoParroquiaList = circuitoParroquiaList;
    }

    public Canton getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(Canton idCanton) {
        this.idCanton = idCanton;
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
        if (!(object instanceof Parroquia)) {
            return false;
        }
        Parroquia other = (Parroquia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.educacion.ebja.modelo.zeus.Parroquia[ id=" + id + " ]";
    }
    
}
