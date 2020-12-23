package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;

import ec.gob.educacion.ebja.modelo.zeus.Docente;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="docente_curso", schema = "ebja")
@NamedQuery(name="DocenteCurso.findAll", query="SELECT d FROM DocenteCurso d")
public class DocenteCurso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "docenteCurso_generador", sequenceName = "ebja.docente_curso_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="docenteCurso_generador")
	private Integer id;

	private String estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;
	
	@Column(name="ip_usuario")
	private String ipUsuario;

	//bi-directional many-to-one association to Docente
	@ManyToOne
	@JoinColumn(name="id_docente")
	private Docente docente;
	
	//bi-directional many-to-one association to CursoParalelo
	@ManyToOne
	@JoinColumn(name="id_curso_paralelo")
	private CursoParalelo cursoParalelo;

	//bi-directional many-to-one association to ProgramaInstitucion
	@ManyToOne
	@JoinColumn(name="id_programa_institucion")
	private ProgramaInstitucion programaInstitucion;

	//bi-directional many-to-one association to MallaDocente
	@OneToMany(mappedBy="docenteCurso")
	private List<MallaDocente> mallaDocentes;

	public DocenteCurso() {
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

	public Docente getDocente() {
		return this.docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}
	
	public CursoParalelo getCursoParalelo() {
		return this.cursoParalelo;
	}

	public void setCursoParalelo(CursoParalelo cursoParalelo) {
		this.cursoParalelo = cursoParalelo;
	}

	public ProgramaInstitucion getProgramaInstitucion() {
		return this.programaInstitucion;
	}

	public void setProgramaInstitucion(ProgramaInstitucion programaInstitucion) {
		this.programaInstitucion = programaInstitucion;
	}

	public List<MallaDocente> getMallaDocentes() {
		return this.mallaDocentes;
	}

	public void setMallaDocentes(List<MallaDocente> mallaDocentes) {
		this.mallaDocentes = mallaDocentes;
	}

	public MallaDocente addMallaDocente(MallaDocente mallaDocente) {
		getMallaDocentes().add(mallaDocente);
		mallaDocente.setDocenteCurso(this);

		return mallaDocente;
	}

	public MallaDocente removeMallaDocente(MallaDocente mallaDocente) {
		getMallaDocentes().remove(mallaDocente);
		mallaDocente.setDocenteCurso(null);

		return mallaDocente;
	}

}