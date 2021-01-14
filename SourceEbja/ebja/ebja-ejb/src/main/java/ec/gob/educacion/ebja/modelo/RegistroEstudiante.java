package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;

import ec.gob.educacion.ebja.modelo.zeus.Catalogo;
import ec.gob.educacion.ebja.modelo.zeus.Etnia;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="registro_estudiante", schema = "ebja")
@NamedQuery(name="RegistroEstudiante.findAll", query="SELECT r FROM RegistroEstudiante r")
public class RegistroEstudiante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "registroEstudiante_generador", sequenceName = "ebja.registro_estudiante_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="registroEstudiante_generador")
	private Integer id;

	@Column(name="apellidos_nombres")
	private String apellidosNombres;

	private String autorepresentado;

	@Column(name="correo_electronico")
	private String correoElectronico;
	
	@Column(name="memorando")
	private String memorando;

	private Integer edad;

	private String estado;
	
	@Column(name="estado_asignacion")
	private String estadoAsignacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;
	
	@Column(name="ip_usuario")
	private String ipUsuario;
	
	@Column(name="ins_observacion")
	private String insObservacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	//bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name="id_situacion_laboral_catalogo", referencedColumnName = "id")
	private CatalogoEbja catalogoSituacionLaboral;

	//bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name="id_tipo_identificacion_catalogo", referencedColumnName = "id")
	private Catalogo catalogoTipoIdentificacion;

	@Column(name="nacionalidad_ecuatoriana")
	private String nacionalidadEcuatoriana;

	@Column(name="numero_identificacion")
	private String numeroIdentificacion;

	@Column(name="recibe_bono")
	private String recibeBono;

	private String telefono;

	@Column(name="telefono_convencional")
	private String telefonoConvencional;

	@Column(name="tiene_discapacidad")
	private String tieneDiscapacidad;

	@Column(name="tiene_hijo")
	private String tieneHijo;
	
	@Column(name="archivo_presentado")
	private String archivoPresentado;
	
	@Column(name="motivo_no_matricula")
	private String motivoNoMatricula;

	//bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name="id_actividad_economica_catalogo", referencedColumnName = "id")
	private CatalogoEbja catalogoActividadEconomica;

	//bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name="id_estado_civil_catalogo", referencedColumnName = "id")
	private Catalogo catalogoEstadoCivil;

	//bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name="id_genero_catalogo", referencedColumnName = "id")
	private Catalogo catalogoGenero;

	//bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name="id_nacionalidad_catalogo", nullable=true)
	private Catalogo catalogoNacionalidad;
	
	//bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name="id_documento_catalogo", referencedColumnName = "id")
	private CatalogoEbja catalogoDocumento;
	
	//bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name="id_motivo_catalogo", referencedColumnName = "id")
	private CatalogoEbja catalogoMotivo;

	//bi-directional many-to-one association to Inscripcion
	@ManyToOne
	@JoinColumn(name="id_inscripcion")
	private Inscripcion inscripcion;

	//bi-directional many-to-one association to Etnia
	@ManyToOne
	@JoinColumn(name="id_etnia")
	private Etnia etnia;

	//bi-directional many-to-one association to Pai
	@ManyToOne
	@JoinColumn(name="id_pais", nullable=true)
	private Pais pais;
	
	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="registroEstudiante")
	private List<Estudiante> estudiante;

	public RegistroEstudiante() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellidosNombres() {
		return this.apellidosNombres;
	}

	public void setApellidosNombres(String apellidosNombres) {
		this.apellidosNombres = apellidosNombres;
	}

	public String getAutorepresentado() {
		return this.autorepresentado;
	}

	public void setAutorepresentado(String autorepresentado) {
		this.autorepresentado = autorepresentado;
	}

	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Integer getEdad() {
		return this.edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getEstadoAsignacion() {
		return this.estadoAsignacion;
	}

	public void setEstadoAsignacion(String estadoAsignacion) {
		this.estadoAsignacion = estadoAsignacion;
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

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidadEcuatoriana() {
		return this.nacionalidadEcuatoriana;
	}

	public void setNacionalidadEcuatoriana(String nacionalidadEcuatoriana) {
		this.nacionalidadEcuatoriana = nacionalidadEcuatoriana;
	}

	public String getNumeroIdentificacion() {
		return this.numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getRecibeBono() {
		return this.recibeBono;
	}

	public void setRecibeBono(String recibeBono) {
		this.recibeBono = recibeBono;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTieneDiscapacidad() {
		return this.tieneDiscapacidad;
	}

	public void setTieneDiscapacidad(String tieneDiscapacidad) {
		this.tieneDiscapacidad = tieneDiscapacidad;
	}

	public String getTieneHijo() {
		return this.tieneHijo;
	}

	public void setTieneHijo(String tieneHijo) {
		this.tieneHijo = tieneHijo;
	}
	
	public String getArchivoPresentado() {
		return this.archivoPresentado;
	}

	public void setArchivoPresentado(String archivoPresentado) {
		this.archivoPresentado = archivoPresentado;
	}
	
	public Inscripcion getInscripcion() {
		return this.inscripcion;
	}

	public void setInscripcion(Inscripcion inscripcion) {
		this.inscripcion = inscripcion;
	}

	public Etnia getEtnia() {
		return this.etnia;
	}

	public void setEtnia(Etnia etnia) {
		this.etnia = etnia;
	}

	public CatalogoEbja getCatalogoActividadEconomica() {
		return catalogoActividadEconomica;
	}

	public void setCatalogoActividadEconomica(CatalogoEbja catalogoActividadEconomica) {
		this.catalogoActividadEconomica = catalogoActividadEconomica;
	}

	public Catalogo getCatalogoEstadoCivil() {
		return catalogoEstadoCivil;
	}

	public void setCatalogoEstadoCivil(Catalogo catalogoEstadoCivil) {
		this.catalogoEstadoCivil = catalogoEstadoCivil;
	}

	public Catalogo getCatalogoGenero() {
		return catalogoGenero;
	}

	public void setCatalogoGenero(Catalogo catalogoGenero) {
		this.catalogoGenero = catalogoGenero;
	}

	public Catalogo getCatalogoNacionalidad() {
		return catalogoNacionalidad;
	}

	public void setCatalogoNacionalidad(Catalogo catalogoNacionalidad) {
		this.catalogoNacionalidad = catalogoNacionalidad;
	}
	
	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Catalogo getCatalogoTipoIdentificacion() {
		return catalogoTipoIdentificacion;
	}

	public void setCatalogoTipoIdentificacion(Catalogo catalogoTipoIdentificacion) {
		this.catalogoTipoIdentificacion = catalogoTipoIdentificacion;
	}

	public CatalogoEbja getCatalogoSituacionLaboral() {
		return catalogoSituacionLaboral;
	}

	public void setCatalogoSituacionLaboral(CatalogoEbja catalogoSituacionLaboral) {
		this.catalogoSituacionLaboral = catalogoSituacionLaboral;
	}

	public String getTelefonoConvencional() {
		return telefonoConvencional;
	}

	public void setTelefonoConvencional(String telefonoConvencional) {
		this.telefonoConvencional = telefonoConvencional;
	}

	public CatalogoEbja getCatalogoDocumento() {
		return catalogoDocumento;
	}

	public void setCatalogoDocumento(CatalogoEbja catalogoDocumento) {
		this.catalogoDocumento = catalogoDocumento;
	}

	public CatalogoEbja getCatalogoMotivo() {
		return catalogoMotivo;
	}

	public void setCatalogoMotivo(CatalogoEbja catalogoMotivo) {
		this.catalogoMotivo = catalogoMotivo;
	}

	public String getMotivoNoMatricula() {
		return motivoNoMatricula;
	}

	public void setMotivoNoMatricula(String motivoNoMatricula) {
		this.motivoNoMatricula = motivoNoMatricula;
	}

	public List<Estudiante> getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(List<Estudiante> estudiante) {
		this.estudiante = estudiante;
	}

	public String getMemorando() {
		return memorando;
	}

	public void setMemorando(String memorando) {
		this.memorando = memorando;
	}
	
	
	
}