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
@Table(name = "tipo_catalogo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCatalogo.findAll", query = "SELECT t FROM TipoCatalogo t")
    , @NamedQuery(name = "TipoCatalogo.findById", query = "SELECT t FROM TipoCatalogo t WHERE t.id = :id")
    , @NamedQuery(name = "TipoCatalogo.findByNombre", query = "SELECT t FROM TipoCatalogo t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoCatalogo.findByDescripcion", query = "SELECT t FROM TipoCatalogo t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TipoCatalogo.findByNemonico", query = "SELECT t FROM TipoCatalogo t WHERE t.nemonico = :nemonico")
    , @NamedQuery(name = "TipoCatalogo.findByIdUsuarioCreacion", query = "SELECT t FROM TipoCatalogo t WHERE t.idUsuarioCreacion = :idUsuarioCreacion")
    , @NamedQuery(name = "TipoCatalogo.findByFechaCreacion", query = "SELECT t FROM TipoCatalogo t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TipoCatalogo.findByEstado", query = "SELECT t FROM TipoCatalogo t WHERE t.estado = :estado")
    , @NamedQuery(name = "TipoCatalogo.findByFechaMigracion", query = "SELECT t FROM TipoCatalogo t WHERE t.fechaMigracion = :fechaMigracion")})
public class TipoCatalogo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 25)
    @Column(name = "nemonico")
    private String nemonico;
    @Column(name = "id_usuario_creacion")
    private Integer idUsuarioCreacion;
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCatalogo", fetch = FetchType.LAZY)
    private List<Catalogo> catalogoList;

    public TipoCatalogo() {
    }

    public TipoCatalogo(Integer id) {
        this.id = id;
    }

    public TipoCatalogo(Integer id, String nombre, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNemonico() {
        return nemonico;
    }

    public void setNemonico(String nemonico) {
        this.nemonico = nemonico;
    }

    public Integer getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public void setIdUsuarioCreacion(Integer idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
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
    public List<Catalogo> getCatalogoList() {
        return catalogoList;
    }

    public void setCatalogoList(List<Catalogo> catalogoList) {
        this.catalogoList = catalogoList;
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
        if (!(object instanceof TipoCatalogo)) {
            return false;
        }
        TipoCatalogo other = (TipoCatalogo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.educacion.ebja.ebja.zeus.TipoCatalogo[ id=" + id + " ]";
    }
    
}
