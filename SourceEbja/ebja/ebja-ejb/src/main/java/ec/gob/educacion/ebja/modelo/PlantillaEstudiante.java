package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the plantilla_estudiante database table.
 * 
 */
@Entity
@Table(name="plantilla_estudiante",schema="ebja")
@NamedQuery(name="PlantillaEstudiante.findAll", query="SELECT p FROM PlantillaEstudiante p")
public class PlantillaEstudiante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PLANTILLA_ESTUDIANTE_IDPLANTILLAESTUDIANTE_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PLANTILLA_ESTUDIANTE_IDPLANTILLAESTUDIANTE_GENERATOR")
	@Column(name="id_plantilla_estudiante")
	private Integer idPlantillaEstudiante;

	@Column(name="curso_id")
	private Integer cursoId;

	private String estado;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;


	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;

	@Column(name="ip_usuario")
	private String ipUsuario;

	
	//bi-directional many-to-one association to Malla
	@ManyToOne
	@JoinColumn(name="id_malla_docente")
	private MallaDocente mallaDocente;
	

	//bi-directional many-to-one association to Nota
	@OneToMany(mappedBy="plantillaEstudiante")
	private List<Notas> notas;
	
	//bi-directional many-to-one association to matricula
	@ManyToOne
	@JoinColumn(name="id")
	private Matricula matricula;

	public PlantillaEstudiante() {
	}

	public Integer getIdPlantillaEstudiante() {
		return this.idPlantillaEstudiante;
	}

	public void setIdPlantillaEstudiante(Integer idPlantillaEstudiante) {
		this.idPlantillaEstudiante = idPlantillaEstudiante;
	}

	public Integer getCursoId() {
		return this.cursoId;
	}

	public void setCursoId(Integer cursoId) {
		this.cursoId = cursoId;
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


	public MallaDocente getMallaDocente() {
		return mallaDocente;
	}

	public void setMallaDocente(MallaDocente mallaDocente) {
		this.mallaDocente = mallaDocente;
	}

	public List<Notas> getNotas() {
		return this.notas;
	}

	public void setNotas(List<Notas> notas) {
		this.notas = notas;
	}

	public Notas addNota(Notas nota) {
		getNotas().add(nota);
		nota.setPlantillaEstudiante(this);

		return nota;
	}

	public Notas removeNota(Notas nota) {
		getNotas().remove(nota);
		nota.setPlantillaEstudiante(null);

		return nota;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	
	
	

}