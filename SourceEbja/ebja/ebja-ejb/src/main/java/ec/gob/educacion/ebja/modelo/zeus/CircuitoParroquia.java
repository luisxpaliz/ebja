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
@Table(name = "circuito_parroquia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CircuitoParroquia.findAll", query = "SELECT c FROM CircuitoParroquia c")
    , @NamedQuery(name = "CircuitoParroquia.findById", query = "SELECT c FROM CircuitoParroquia c WHERE c.id = :id")
    , @NamedQuery(name = "CircuitoParroquia.findByFechaCreacion", query = "SELECT c FROM CircuitoParroquia c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CircuitoParroquia.findByEstado", query = "SELECT c FROM CircuitoParroquia c WHERE c.estado = :estado")
    , @NamedQuery(name = "CircuitoParroquia.findByParroquia", query = "SELECT c FROM CircuitoParroquia c WHERE c.parroquia = :parroquia")
    , @NamedQuery(name = "CircuitoParroquia.findByCircuito", query = "SELECT c FROM CircuitoParroquia c WHERE c.circuito = :circuito")
    , @NamedQuery(name = "CircuitoParroquia.findByFechaMigracion", query = "SELECT c FROM CircuitoParroquia c WHERE c.fechaMigracion = :fechaMigracion")})
public class CircuitoParroquia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private String estado;
    @Size(max = 6)
    @Column(name = "parroquia")
    private String parroquia;
    @Size(max = 30)
    @Column(name = "circuito")
    private String circuito;
    @Column(name = "fecha_migracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMigracion;
    @JoinColumn(name = "id_circuito", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Circuito idCircuito;
    @JoinColumn(name = "id_parroquia", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Parroquia idParroquia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCircuitoParroquia", fetch = FetchType.LAZY)
    private List<InstitucEstablec> institucEstablecList;

    public CircuitoParroquia() {
    }

    public CircuitoParroquia(Short id) {
        this.id = id;
    }

    public CircuitoParroquia(Short id, Date fechaCreacion, String estado) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
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

    public String getParroquia() {
        return parroquia;
    }

    public void setParroquia(String parroquia) {
        this.parroquia = parroquia;
    }

    public String getCircuito() {
        return circuito;
    }

    public void setCircuito(String circuito) {
        this.circuito = circuito;
    }

    public Date getFechaMigracion() {
        return fechaMigracion;
    }

    public void setFechaMigracion(Date fechaMigracion) {
        this.fechaMigracion = fechaMigracion;
    }

    public Circuito getIdCircuito() {
        return idCircuito;
    }

    public void setIdCircuito(Circuito idCircuito) {
        this.idCircuito = idCircuito;
    }

    public Parroquia getIdParroquia() {
        return idParroquia;
    }

    public void setIdParroquia(Parroquia idParroquia) {
        this.idParroquia = idParroquia;
    }

    @XmlTransient
    public List<InstitucEstablec> getInstitucEstablecList() {
        return institucEstablecList;
    }

    public void setInstitucEstablecList(List<InstitucEstablec> institucEstablecList) {
        this.institucEstablecList = institucEstablecList;
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
        if (!(object instanceof CircuitoParroquia)) {
            return false;
        }
        CircuitoParroquia other = (CircuitoParroquia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.educacion.ebja.modelo.zeus.CircuitoParroquia[ id=" + id + " ]";
    }
    
}
