package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "discapacidad", schema = "ebja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Discapacidad.findAll", query = "SELECT c FROM Discapacidad c")
    , @NamedQuery(name = "Discapacidad.findById", query = "SELECT c FROM Discapacidad c WHERE c.id = :id")
    , @NamedQuery(name = "Discapacidad.findByIdUsuarioCreacion", query = "SELECT c FROM Discapacidad c WHERE c.idUsuarioCreacion = :idUsuarioCreacion")
    , @NamedQuery(name = "Discapacidad.findByFechaCreacion", query = "SELECT c FROM Discapacidad c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Discapacidad.findByEstado", query = "SELECT c FROM Discapacidad c WHERE c.estado = :estado")})
public class Discapacidad implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
	@SequenceGenerator(name = "discapacidad_generador", sequenceName = "ebja.discapacidad_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="discapacidad_generador")
    private Integer id;

	//bi-directional many-to-one association to RegistroEstudiante
	@ManyToOne
	@JoinColumn(name="id_registro_estudiante")
	private RegistroEstudiante registroEstudiante;
	
    @Size(max = 20)
    @Column(name = "carnet_conadis")
    private String carnetConadis;
	
    @JoinColumn(name = "id_tipo_discapacidad_catalogo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CatalogoEbja catalogoDiscapacidad;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje")
    private BigDecimal porcentaje;
    
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;
	
	@Column(name="ip_usuario")
	private String ipUsuario;

    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private String estado;

    public Discapacidad() {
    }

    public Discapacidad(Integer id) {
        this.id = id;
    }

    public Discapacidad(Integer id, BigDecimal porcentaje, int idUsuarioCreacion, Date fechaCreacion, String estado) {
        this.id = id;
        this.porcentaje = porcentaje;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public RegistroEstudiante getRegistroEstudiante() {
		return registroEstudiante;
	}

	public void setRegistroEstudiante(RegistroEstudiante registroEstudiante) {
		this.registroEstudiante = registroEstudiante;
	}

	public String getCarnetConadis() {
		return carnetConadis;
	}

	public void setCarnetConadis(String carnetConadis) {
		this.carnetConadis = carnetConadis;
	}

	public CatalogoEbja getCatalogoDiscapacidad() {
		return catalogoDiscapacidad;
	}

	public void setCatalogoDiscapacidad(CatalogoEbja catalogoDiscapacidad) {
		this.catalogoDiscapacidad = catalogoDiscapacidad;
	}

	public BigDecimal getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getIdUsuarioCreacion() {
		return this.idUsuarioCreacion;
	}

	public void setIdUsuarioCreacion(Integer idUsuarioCreacion) {
		this.idUsuarioCreacion = idUsuarioCreacion;
	}
	
	public String getIpUsuario() {
		return this.ipUsuario;
	}

	public void setIpUsuario(String ipUsuario) {
		this.ipUsuario = ipUsuario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
        if (!(object instanceof Discapacidad)) {
            return false;
        }
        Discapacidad other = (Discapacidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.educacion.ebja.modelo.Discapacidad[ id=" + id + " ]";
    }
}
