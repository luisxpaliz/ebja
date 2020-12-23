package ec.gob.educacion.ebja.modelo.zeus;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PERSONA_ID_GENERATOR", sequenceName="PERSONA_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERSONA_ID_GENERATOR")
	private Integer id;

	@Column(name="calle_principal")
	private String callePrincipal;

	@Column(name="calle_secundaria")
	private String calleSecundaria;

	@Column(name="codigo_unico_electrico")
	private String codigoUnicoElectrico;

	@Column(name="correo_electronico")
	private String correoElectronico;

	private String direccion;

	@Column(name="es_ecuatoriano")
	private String esEcuatoriano;

	private String estado;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_migracion")
	private Timestamp fechaMigracion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	private String genero;

	@Column(name="id_estado_civil_catalogo")
	private Integer idEstadoCivilCatalogo;

	@Column(name="id_etnia")
	private Integer idEtnia;

	@Column(name="id_parroquia_domicilio")
	private Integer idParroquiaDomicilio;

	@Column(name="id_parroquia_nacimiento")
	private Integer idParroquiaNacimiento;

	@Column(name="id_tipo_identificacion_catalogo")
	private Integer idTipoIdentificacionCatalogo;

	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;

	@Column(name="nombres_apellidos")
	private String nombresApellidos;

	@Column(name="numero_identificacion")
	private String numeroIdentificacion;

	private String referencia;

	private String sector;

	private String sexo;

	@Column(name="telefono_celular")
	private String telefonoCelular;

	@Column(name="telefono_convencional")
	private String telefonoConvencional;

	//bi-directional many-to-one association to Docente
	@OneToMany(mappedBy="persona")
	private List<Docente> docentes;

	public Persona() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCallePrincipal() {
		return this.callePrincipal;
	}

	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}

	public String getCalleSecundaria() {
		return this.calleSecundaria;
	}

	public void setCalleSecundaria(String calleSecundaria) {
		this.calleSecundaria = calleSecundaria;
	}

	public String getCodigoUnicoElectrico() {
		return this.codigoUnicoElectrico;
	}

	public void setCodigoUnicoElectrico(String codigoUnicoElectrico) {
		this.codigoUnicoElectrico = codigoUnicoElectrico;
	}

	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEsEcuatoriano() {
		return this.esEcuatoriano;
	}

	public void setEsEcuatoriano(String esEcuatoriano) {
		this.esEcuatoriano = esEcuatoriano;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Timestamp getFechaMigracion() {
		return this.fechaMigracion;
	}

	public void setFechaMigracion(Timestamp fechaMigracion) {
		this.fechaMigracion = fechaMigracion;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getIdEstadoCivilCatalogo() {
		return this.idEstadoCivilCatalogo;
	}

	public void setIdEstadoCivilCatalogo(Integer idEstadoCivilCatalogo) {
		this.idEstadoCivilCatalogo = idEstadoCivilCatalogo;
	}

	public Integer getIdEtnia() {
		return this.idEtnia;
	}

	public void setIdEtnia(Integer idEtnia) {
		this.idEtnia = idEtnia;
	}

	public Integer getIdParroquiaDomicilio() {
		return this.idParroquiaDomicilio;
	}

	public void setIdParroquiaDomicilio(Integer idParroquiaDomicilio) {
		this.idParroquiaDomicilio = idParroquiaDomicilio;
	}

	public Integer getIdParroquiaNacimiento() {
		return this.idParroquiaNacimiento;
	}

	public void setIdParroquiaNacimiento(Integer idParroquiaNacimiento) {
		this.idParroquiaNacimiento = idParroquiaNacimiento;
	}

	public Integer getIdTipoIdentificacionCatalogo() {
		return this.idTipoIdentificacionCatalogo;
	}

	public void setIdTipoIdentificacionCatalogo(Integer idTipoIdentificacionCatalogo) {
		this.idTipoIdentificacionCatalogo = idTipoIdentificacionCatalogo;
	}

	public Integer getIdUsuarioCreacion() {
		return this.idUsuarioCreacion;
	}

	public void setIdUsuarioCreacion(Integer idUsuarioCreacion) {
		this.idUsuarioCreacion = idUsuarioCreacion;
	}

	public String getNombresApellidos() {
		return this.nombresApellidos;
	}

	public void setNombresApellidos(String nombresApellidos) {
		this.nombresApellidos = nombresApellidos;
	}

	public String getNumeroIdentificacion() {
		return this.numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getSector() {
		return this.sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefonoCelular() {
		return this.telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public String getTelefonoConvencional() {
		return this.telefonoConvencional;
	}

	public void setTelefonoConvencional(String telefonoConvencional) {
		this.telefonoConvencional = telefonoConvencional;
	}

	public List<Docente> getDocentes() {
		return this.docentes;
	}

	public void setDocentes(List<Docente> docentes) {
		this.docentes = docentes;
	}

	public Docente addDocente(Docente docente) {
		getDocentes().add(docente);
		docente.setPersona(this);

		return docente;
	}

	public Docente removeDocente(Docente docente) {
		getDocentes().remove(docente);
		docente.setPersona(null);

		return docente;
	}

}