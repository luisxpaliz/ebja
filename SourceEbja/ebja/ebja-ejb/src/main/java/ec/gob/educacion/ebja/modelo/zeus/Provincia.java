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
@Table(name = "provincia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provincia.findAll", query = "SELECT p FROM Provincia p")
    , @NamedQuery(name = "Provincia.findById", query = "SELECT p FROM Provincia p WHERE p.id = :id")
    , @NamedQuery(name = "Provincia.findByCodigoProvincia", query = "SELECT p FROM Provincia p WHERE p.codigoProvincia = :codigoProvincia")
    , @NamedQuery(name = "Provincia.findByDescripcion", query = "SELECT p FROM Provincia p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Provincia.findByCapitalProvincia", query = "SELECT p FROM Provincia p WHERE p.capitalProvincia = :capitalProvincia")
    , @NamedQuery(name = "Provincia.findByFechaCreacion", query = "SELECT p FROM Provincia p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Provincia.findByCodigoArea", query = "SELECT p FROM Provincia p WHERE p.codigoArea = :codigoArea")
    , @NamedQuery(name = "Provincia.findByEstado", query = "SELECT p FROM Provincia p WHERE p.estado = :estado")
    , @NamedQuery(name = "Provincia.findByFechaMigracion", query = "SELECT p FROM Provincia p WHERE p.fechaMigracion = :fechaMigracion")})
public class Provincia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codigo_provincia")
    private String codigoProvincia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 50)
    @Column(name = "capital_provincia")
    private String capitalProvincia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 2)
    @Column(name = "codigo_area")
    private String codigoArea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_migracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMigracion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProvincia", fetch = FetchType.LAZY)
    private List<Comunidad> comunidadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProvincia", fetch = FetchType.LAZY)
    private List<Canton> cantonList;

    public Provincia() {
    }

    public Provincia(Short id) {
        this.id = id;
    }

    public Provincia(Short id, String codigoProvincia, String descripcion, Date fechaCreacion, String estado) {
        this.id = id;
        this.codigoProvincia = codigoProvincia;
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

    public String getCodigoProvincia() {
        return codigoProvincia;
    }

    public void setCodigoProvincia(String codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCapitalProvincia() {
        return capitalProvincia;
    }

    public void setCapitalProvincia(String capitalProvincia) {
        this.capitalProvincia = capitalProvincia;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
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
    public List<Comunidad> getComunidadList() {
        return comunidadList;
    }

    public void setComunidadList(List<Comunidad> comunidadList) {
        this.comunidadList = comunidadList;
    }

    @XmlTransient
    public List<Canton> getCantonList() {
        return cantonList;
    }

    public void setCantonList(List<Canton> cantonList) {
        this.cantonList = cantonList;
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
        if (!(object instanceof Provincia)) {
            return false;
        }
        Provincia other = (Provincia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.educacion.ebja.modelo.zeus.Provincia[ id=" + id + " ]";
    }
    
}
