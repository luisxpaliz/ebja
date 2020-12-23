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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "nivel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nivel.findAll", query = "SELECT n FROM Nivel n")
    , @NamedQuery(name = "Nivel.findById", query = "SELECT n FROM Nivel n WHERE n.id = :id")
    , @NamedQuery(name = "Nivel.findByDescripcion", query = "SELECT n FROM Nivel n WHERE n.descripcion = :descripcion")
    , @NamedQuery(name = "Nivel.findByNemonico", query = "SELECT n FROM Nivel n WHERE n.nemonico = :nemonico")
    , @NamedQuery(name = "Nivel.findByFechaCreacion", query = "SELECT n FROM Nivel n WHERE n.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Nivel.findByEstado", query = "SELECT n FROM Nivel n WHERE n.estado = :estado")
    , @NamedQuery(name = "Nivel.findByMostrarSubnivel", query = "SELECT n FROM Nivel n WHERE n.mostrarSubnivel = :mostrarSubnivel")
    , @NamedQuery(name = "Nivel.findByFechaMigracion", query = "SELECT n FROM Nivel n WHERE n.fechaMigracion = :fechaMigracion")})
public class Nivel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nemonico")
    private String nemonico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private String estado;
    @Column(name = "mostrar_subnivel")
    private Character mostrarSubnivel;
    @Column(name = "fecha_migracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMigracion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNivel", fetch = FetchType.LAZY)
    private Set<Grado> gradoList;
/*    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNivel", fetch = FetchType.LAZY)
    private List<Especialidad> especialidadList;
*/
    @OneToMany(mappedBy = "idNivel", fetch = FetchType.LAZY)
    private List<Nivel> nivelList;
    
    @JoinColumn(name = "id_nivel", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Nivel idNivel;
    
    @JoinColumn(name = "id_tipo_nivel", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoNivel idTipoNivel;
    
    @Transient
    private String nivelCompuestoConTipoNivel;

    public Nivel() {
    }

    public Nivel(Short id) {
        this.id = id;
    }

    public Nivel(Short id, String descripcion, String nemonico, Date fechaCreacion, String estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.nemonico = nemonico;
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

    public String getNemonico() {
        return nemonico;
    }

    public void setNemonico(String nemonico) {
        this.nemonico = nemonico;
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

    public Character getMostrarSubnivel() {
        return mostrarSubnivel;
    }

    public void setMostrarSubnivel(Character mostrarSubnivel) {
        this.mostrarSubnivel = mostrarSubnivel;
    }

    public Date getFechaMigracion() {
        return fechaMigracion;
    }

    public void setFechaMigracion(Date fechaMigracion) {
        this.fechaMigracion = fechaMigracion;
    }

    @XmlTransient
    public Set<Grado> getGradoList() {
        return gradoList;
    }

    public void setGradoList(Set<Grado> gradoList) {
        this.gradoList = gradoList;
    }
/*
    @XmlTransient
    public List<Especialidad> getEspecialidadList() {
        return especialidadList;
    }

    public void setEspecialidadList(List<Especialidad> especialidadList) {
        this.especialidadList = especialidadList;
    }
*/
    @XmlTransient
    public List<Nivel> getNivelList() {
        return nivelList;
    }

    public void setNivelList(List<Nivel> nivelList) {
        this.nivelList = nivelList;
    }

    public Nivel getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Nivel idNivel) {
        this.idNivel = idNivel;
    }

    public TipoNivel getIdTipoNivel() {
        return idTipoNivel;
    }

    public void setIdTipoNivel(TipoNivel idTipoNivel) {
        this.idTipoNivel = idTipoNivel;
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
        if (!(object instanceof Nivel)) {
            return false;
        }
        Nivel other = (Nivel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.educacion.ebja.modelo.zeus.Nivel[ id=" + id + " ]";
    }

	public String getNivelCompuestoConTipoNivel() {
		return nivelCompuestoConTipoNivel;
	}

	public void setNivelCompuestoConTipoNivel(String nivelCompuestoConTipoNivel) {
		this.nivelCompuestoConTipoNivel = nivelCompuestoConTipoNivel;
	}
    
}
