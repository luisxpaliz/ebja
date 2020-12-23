package ec.gob.educacion.ebja.modelo.zeus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "tipo_institucion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoInstitucion.findAll", query = "SELECT t FROM TipoInstitucion t")
    , @NamedQuery(name = "TipoInstitucion.findById", query = "SELECT t FROM TipoInstitucion t WHERE t.id = :id")
    , @NamedQuery(name = "TipoInstitucion.findByDescripcion", query = "SELECT t FROM TipoInstitucion t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TipoInstitucion.findByFechaCreacion", query = "SELECT t FROM TipoInstitucion t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TipoInstitucion.findByEstado", query = "SELECT t FROM TipoInstitucion t WHERE t.estado = :estado")
    , @NamedQuery(name = "TipoInstitucion.findByFechaMigracion", query = "SELECT t FROM TipoInstitucion t WHERE t.fechaMigracion = :fechaMigracion")})
public class TipoInstitucion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
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
    @OneToMany(mappedBy = "idTipoInstitucion", fetch = FetchType.LAZY)
    private List<Institucion> institucionList;

    public TipoInstitucion() {
    }

    public TipoInstitucion(Short id) {
        this.id = id;
    }

    public TipoInstitucion(Short id, String descripcion, Date fechaCreacion, String estado) {
        this.id = id;
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
    public List<Institucion> getInstitucionList() {
        return institucionList;
    }

    public void setInstitucionList(List<Institucion> institucionList) {
        this.institucionList = institucionList;
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
        if (!(object instanceof TipoInstitucion)) {
            return false;
        }
        TipoInstitucion other = (TipoInstitucion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.educacion.ebja.modelo.zeus.TipoInstitucion[ id=" + id + " ]";
    }
    
}
