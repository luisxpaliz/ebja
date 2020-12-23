package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name="dato_familiar", schema = "ebja")
@NamedQueries({
    @NamedQuery(name = "DatoFamiliar.findAll", query = "SELECT d FROM DatoFamiliar d")
    , @NamedQuery(name = "DatoFamiliar.findById", query = "SELECT d FROM DatoFamiliar d WHERE d.id = :id")
    , @NamedQuery(name = "DatoFamiliar.findByIdUsuarioCreacion", query = "SELECT d FROM DatoFamiliar d WHERE d.idUsuarioCreacion = :idUsuarioCreacion")
    , @NamedQuery(name = "DatoFamiliar.findByFechaCreacion", query = "SELECT d FROM DatoFamiliar d WHERE d.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "DatoFamiliar.findByEstado", query = "SELECT d FROM DatoFamiliar d WHERE d.estado = :estado")})
public class DatoFamiliar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "datoFamiliar_generador", sequenceName = "ebja.dato_familiar_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="datoFamiliar_generador")
	private Integer id;

	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;
	
	@Column(name="ip_usuario")
	private String ipUsuario;

	//bi-directional many-to-one association to RegistroEstudiante
	@ManyToOne
	@JoinColumn(name="id_dato_familiar_catalogo", referencedColumnName = "id")
	private CatalogoEbja catalogoDatoFamiliar;

	//bi-directional many-to-one association to RegistroEstudiante
	@ManyToOne
	@JoinColumn(name="id_registro_estudiante")
	private RegistroEstudiante registroEstudiante;

	public DatoFamiliar() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public RegistroEstudiante getRegistroEstudiante() {
		return this.registroEstudiante;
	}

	public void setRegistroEstudiante(RegistroEstudiante registroEstudiante) {
		this.registroEstudiante = registroEstudiante;
	}

	public CatalogoEbja getCatalogoDatoFamiliar() {
		return catalogoDatoFamiliar;
	}

	public void setCatalogoDatoFamiliar(CatalogoEbja catalogoDatoFamiliar) {
		this.catalogoDatoFamiliar = catalogoDatoFamiliar;
	}

}