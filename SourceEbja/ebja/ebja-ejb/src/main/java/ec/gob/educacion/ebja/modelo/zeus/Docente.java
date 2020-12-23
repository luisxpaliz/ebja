package ec.gob.educacion.ebja.modelo.zeus;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@NamedQuery(name="Docente.findAll", query="SELECT d FROM Docente d")
public class Docente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DOCENTE_ID_GENERATOR", sequenceName="DOCENTE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOCENTE_ID_GENERATOR")
	private Integer id;

	@Column(name="anio_experiencia")
	private Integer anioExperiencia;

	@Column(name="correo_institucional")
	private String correoInstitucional;

	private String estado;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_ingreso")
	private Timestamp fechaIngreso;

	@Column(name="fecha_migracion")
	private Timestamp fechaMigracion;

	@Column(name="fecha_salida")
	private Timestamp fechaSalida;

	@Column(name="id_estado_docente")
	private Integer idEstadoDocente;

	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona")
	private Persona persona;

	public Docente() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnioExperiencia() {
		return this.anioExperiencia;
	}

	public void setAnioExperiencia(Integer anioExperiencia) {
		this.anioExperiencia = anioExperiencia;
	}

	public String getCorreoInstitucional() {
		return this.correoInstitucional;
	}

	public void setCorreoInstitucional(String correoInstitucional) {
		this.correoInstitucional = correoInstitucional;
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

	public Timestamp getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Timestamp getFechaMigracion() {
		return this.fechaMigracion;
	}

	public void setFechaMigracion(Timestamp fechaMigracion) {
		this.fechaMigracion = fechaMigracion;
	}

	public Timestamp getFechaSalida() {
		return this.fechaSalida;
	}

	public void setFechaSalida(Timestamp fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Integer getIdEstadoDocente() {
		return this.idEstadoDocente;
	}

	public void setIdEstadoDocente(Integer idEstadoDocente) {
		this.idEstadoDocente = idEstadoDocente;
	}

	public Integer getIdUsuarioCreacion() {
		return this.idUsuarioCreacion;
	}

	public void setIdUsuarioCreacion(Integer idUsuarioCreacion) {
		this.idUsuarioCreacion = idUsuarioCreacion;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}