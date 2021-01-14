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
@Table(name = "institucion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Institucion.findAll", query = "SELECT i FROM Institucion i")
    , @NamedQuery(name = "Institucion.findById", query = "SELECT i FROM Institucion i WHERE i.id = :id")
    , @NamedQuery(name = "Institucion.findByAmie", query = "SELECT i FROM Institucion i WHERE i.amie = :amie")
    , @NamedQuery(name = "Institucion.findByDescripcion", query = "SELECT i FROM Institucion i WHERE i.descripcion = :descripcion")
    , @NamedQuery(name = "Institucion.findByCodigoPostal", query = "SELECT i FROM Institucion i WHERE i.codigoPostal = :codigoPostal")
    , @NamedQuery(name = "Institucion.findByFechaCreacion", query = "SELECT i FROM Institucion i WHERE i.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Institucion.findByEstado", query = "SELECT i FROM Institucion i WHERE i.estado = :estado")
    , @NamedQuery(name = "Institucion.findByActualizado", query = "SELECT i FROM Institucion i WHERE i.actualizado = :actualizado")
    , @NamedQuery(name = "Institucion.findByObservacion", query = "SELECT i FROM Institucion i WHERE i.observacion = :observacion")
    , @NamedQuery(name = "Institucion.findByRectorDirectorLider", query = "SELECT i FROM Institucion i WHERE i.rectorDirectorLider = :rectorDirectorLider")
    , @NamedQuery(name = "Institucion.findByFechaMigracion", query = "SELECT i FROM Institucion i WHERE i.fechaMigracion = :fechaMigracion")
    , @NamedQuery(name = "Institucion.findByAmieAndEstado", query = "SELECT i FROM Institucion i WHERE i.amie = :amie and i.estado = :estado")
    , @NamedQuery(name = "Institucion.findByDocente", query="select i from Institucion i where i.id IN (select a.idInstitucion.id from InstitucEstablec a where a.id IN ((select c.programaInstitucion.id from DocenteCurso c where c.docente.id IN ((select d.id from Docente d where d.persona.id = :id and d.estado='1')))))")})
public class Institucion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "amie")
    private String amie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 50)
    @Column(name = "codigo_postal")
    private String codigoPostal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private String estado;
    @Column(name = "actualizado")
    private String actualizado;
    @Size(max = 255)
    @Column(name = "observacion")
    private String observacion;
    @Size(max = 150)
    @Column(name = "rector_director_lider")
    private String rectorDirectorLider;
    @Column(name = "fecha_migracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMigracion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInstitucion", fetch = FetchType.LAZY)
    private List<InstitucEstablec> institucEstablecList;
    @JoinColumn(name = "id_comunidad", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Comunidad idComunidad;
    @JoinColumn(name = "id_tipo_institucion", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoInstitucion idTipoInstitucion;
    @Column(name = "tipologia")
    private Short tipologia;    
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_horario")
    private Catalogo tipoHorario;
    @Column(name = "id_sostenimiento")
    private Integer idSostenimiento;

    public Institucion() {
    	//Constructor vacio
    }

    public Institucion(Integer id) {
        this.id = id;
    }

    public Institucion(Integer id, String amie, String descripcion, Date fechaCreacion, String estado) {
        this.id = id;
        this.amie = amie;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAmie() {
        return amie;
    }

    public void setAmie(String amie) {
        this.amie = amie;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
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

    public String getActualizado() {
        return actualizado;
    }

    public void setActualizado(String actualizado) {
        this.actualizado = actualizado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getRectorDirectorLider() {
        return rectorDirectorLider;
    }

    public void setRectorDirectorLider(String rectorDirectorLider) {
        this.rectorDirectorLider = rectorDirectorLider;
    }

    public Date getFechaMigracion() {
        return fechaMigracion;
    }

    public void setFechaMigracion(Date fechaMigracion) {
        this.fechaMigracion = fechaMigracion;
    }

    @XmlTransient
    public List<InstitucEstablec> getInstitucEstablecList() {
        return institucEstablecList;
    }

    public void setInstitucEstablecList(List<InstitucEstablec> institucEstablecList) {
        this.institucEstablecList = institucEstablecList;
    }

    public Comunidad getIdComunidad() {
        return idComunidad;
    }

    public void setIdComunidad(Comunidad idComunidad) {
        this.idComunidad = idComunidad;
    }

    public TipoInstitucion getIdTipoInstitucion() {
        return idTipoInstitucion;
    }

    public void setIdTipoInstitucion(TipoInstitucion idTipoInstitucion) {
        this.idTipoInstitucion = idTipoInstitucion;
    }
    
    public Short getTipologia() {
		return tipologia;
	}

	public void setTipologia(Short tipologia) {
		this.tipologia = tipologia;
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
        if (!(object instanceof Institucion)) {
            return false;
        }
        Institucion other = (Institucion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "ec.gob.educacion.ebja.modelo.zeus.Institucion[ id=" + id + " ]";
    }

	public Catalogo getTipoHorario() {
		return tipoHorario;
	}

	public void setTipoHorario(Catalogo tipoHorario) {
		this.tipoHorario = tipoHorario;
	}

	public Integer getIdSostenimiento() {
		return idSostenimiento;
	}

	public void setIdSostenimiento(Integer idSostenimiento) {
		this.idSostenimiento = idSostenimiento;
	}

}
