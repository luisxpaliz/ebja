package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "estudiante",schema="ebja")
@NamedQuery(name="Estudiante.findAll", query="SELECT e FROM Estudiante e")
public class Estudiante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "estudiante_generador", sequenceName = "ebja.estudiante_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="estudiante_generador")
	private Integer id;

	private String estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;
	
	@Column(name="ip_usuario")
	private String ipUsuario;

	@Column(name="codigo_estudiante")
	private String codigoEstudiante;

	//bi-directional many-to-one association to Inscripcion
	@ManyToOne
	@JoinColumn(name="id_inscripcion")
	private Inscripcion inscripcion;

	//bi-directional many-to-one association to RegistroEstudiante
	@ManyToOne
	@JoinColumn(name="id_registro_estudiante")
	private RegistroEstudiante registroEstudiante;

	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="estudiante")
	private List<Matricula> matriculas;
	
	@OneToMany(mappedBy="estudiante")
	private List<Agrupacion> agrupacion;

	public Estudiante() {
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

	public Inscripcion getInscripcion() {
		return this.inscripcion;
	}

	public void setInscripcion(Inscripcion inscripcion) {
		this.inscripcion = inscripcion;
	}

	public RegistroEstudiante getRegistroEstudiante() {
		return this.registroEstudiante;
	}

	public void setRegistroEstudiante(RegistroEstudiante registroEstudiante) {
		this.registroEstudiante = registroEstudiante;
	}
	
	public String getCodigoEstudiante() {
		return codigoEstudiante;
	}

	public void setCodigoEstudiante(String codigoEstudiante) {
		this.codigoEstudiante = codigoEstudiante;
	}

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matricula addMatricula(Matricula matricula) {
		getMatriculas().add(matricula);
		matricula.setEstudiante(this);

		return matricula;
	}

	public Matricula removeMatricula(Matricula matricula) {
		getMatriculas().remove(matricula);
		matricula.setEstudiante(null);

		return matricula;
	}

	public List<Agrupacion> getAgrupacion() {
		return agrupacion;
	}

	public void setAgrupacion(List<Agrupacion> agrupacion) {
		this.agrupacion = agrupacion;
	}
	
	
}