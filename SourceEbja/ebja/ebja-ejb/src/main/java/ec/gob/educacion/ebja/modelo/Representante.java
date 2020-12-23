package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "representante",schema="ebja")
@NamedQuery(name="Representante.findAll", query="SELECT r FROM Representante r")
public class Representante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "representante_generador", sequenceName = "ebja.representante_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="representante_generador")
	private Integer id;

	@Column(name="apellido_nombre")
	private String apellidoNombre;

	private String telefono_celular;

	private String telefono_convencional;

	@Column(name="correo_electronico")
	private String correoElectronico;

	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	@Column(name="id_estado_civil_catalogo")
	private Integer idEstadoCivilCatalogo;

	@Column(name="id_genero_catalogo")
	private Integer idGeneroCatalogo;

	@Column(name="id_parentesco_catalogo")
	private Integer idParentescoCatalogo;

	@Column(name="id_tipo_identificacion_catalogo")
	private Integer idTipoIdentificacionCatalogo;

	@Column(name="numero_identificacion")
	private String numeroIdentificacion;

	@Column(name="representante_legal")
	private String representanteLegal;

	//bi-directional many-to-one association to RegistroEstudiante
	@ManyToOne
	@JoinColumn(name="id_registro_estudiante")
	private RegistroEstudiante registroEstudiante;

	public Representante() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellidoNombre() {
		return this.apellidoNombre;
	}

	public void setApellidoNombre(String apellidoNombre) {
		this.apellidoNombre = apellidoNombre;
	}

	public String getTelefono_celular() {
		return telefono_celular;
	}

	public void setTelefono_celular(String telefono_celular) {
		this.telefono_celular = telefono_celular;
	}

	public String getTelefono_convencional() {
		return telefono_convencional;
	}

	public void setTelefono_convencional(String telefono_convencional) {
		this.telefono_convencional = telefono_convencional;
	}

	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
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
	
	

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getIdEstadoCivilCatalogo() {
		return this.idEstadoCivilCatalogo;
	}

	public void setIdEstadoCivilCatalogo(Integer idEstadoCivilCatalogo) {
		this.idEstadoCivilCatalogo = idEstadoCivilCatalogo;
	}

	public Integer getIdGeneroCatalogo() {
		return this.idGeneroCatalogo;
	}

	public void setIdGeneroCatalogo(Integer idGeneroCatalogo) {
		this.idGeneroCatalogo = idGeneroCatalogo;
	}

	public Integer getIdParentescoCatalogo() {
		return this.idParentescoCatalogo;
	}

	public void setIdParentescoCatalogo(Integer idParentescoCatalogo) {
		this.idParentescoCatalogo = idParentescoCatalogo;
	}

	public Integer getIdTipoIdentificacionCatalogo() {
		return this.idTipoIdentificacionCatalogo;
	}

	public void setIdTipoIdentificacionCatalogo(Integer idTipoIdentificacionCatalogo) {
		this.idTipoIdentificacionCatalogo = idTipoIdentificacionCatalogo;
	}

	public String getNumeroIdentificacion() {
		return this.numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getRepresentanteLegal() {
		return this.representanteLegal;
	}

	public void setRepresentanteLegal(String representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	public RegistroEstudiante getRegistroEstudiante() {
		return this.registroEstudiante;
	}

	public void setRegistroEstudiante(RegistroEstudiante registroEstudiante) {
		this.registroEstudiante = registroEstudiante;
	}

}