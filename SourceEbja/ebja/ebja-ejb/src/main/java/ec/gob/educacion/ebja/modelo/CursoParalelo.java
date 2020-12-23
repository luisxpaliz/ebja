package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;

import ec.gob.educacion.ebja.modelo.zeus.Paralelo;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="curso_paralelo", schema = "ebja")
@NamedQuery(name="CursoParalelo.findAll", query="SELECT c FROM CursoParalelo c")
public class CursoParalelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "curso_paralelo_generador", sequenceName = "ebja.curso_paralelo_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="curso_paralelo_generador")
	private Integer id;

	private Integer aforo;

	private String estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;
	
	@Column(name="ip_usuario")
	private String ipUsuario;

	@Column(name="numero_banca")
	private Integer numeroBanca;

	@Column(name="numero_matriculado")
	private Integer numeroMatriculado;

	//bi-directional many-to-one association to ProgramaInstitucion
	@ManyToOne
	@JoinColumn(name="id_programa_institucion")
	private ProgramaInstitucion programaInstitucion;

	//bi-directional many-to-one association to ProgramaInstitucion
	@ManyToOne
	@JoinColumn(name="id_paralelo")
	private Paralelo paralelo;
		
	//bi-directional many-to-one association to DocenteCurso
	@OneToMany(mappedBy="cursoParalelo")
	private List<DocenteCurso> docenteCursos;

	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="cursoParalelo")
	private List<Matricula> matriculas;

	public CursoParalelo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAforo() {
		return this.aforo;
	}

	public void setAforo(Integer aforo) {
		this.aforo = aforo;
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

	public Integer getNumeroBanca() {
		return this.numeroBanca;
	}

	public void setNumeroBanca(Integer numeroBanca) {
		this.numeroBanca = numeroBanca;
	}

	public Integer getNumeroMatriculado() {
		return this.numeroMatriculado;
	}

	public void setNumeroMatriculado(Integer numeroMatriculado) {
		this.numeroMatriculado = numeroMatriculado;
	}

	public ProgramaInstitucion getProgramaInstitucion() {
		return this.programaInstitucion;
	}

	public void setProgramaInstitucion(ProgramaInstitucion programaInstitucion) {
		this.programaInstitucion = programaInstitucion;
	}

	public Paralelo getParalelo() {
		return paralelo;
	}

	public void setParalelo(Paralelo paralelo) {
		this.paralelo = paralelo;
	}

	public List<DocenteCurso> getDocenteCursos() {
		return this.docenteCursos;
	}

	public void setDocenteCursos(List<DocenteCurso> docenteCursos) {
		this.docenteCursos = docenteCursos;
	}

	public DocenteCurso addDocenteCurso(DocenteCurso docenteCurso) {
		getDocenteCursos().add(docenteCurso);
		docenteCurso.setCursoParalelo(this);

		return docenteCurso;
	}

	public DocenteCurso removeDocenteCurso(DocenteCurso docenteCurso) {
		getDocenteCursos().remove(docenteCurso);
		docenteCurso.setCursoParalelo(null);

		return docenteCurso;
	}

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matricula addMatricula(Matricula matricula) {
		getMatriculas().add(matricula);
		matricula.setCursoParalelo(this);

		return matricula;
	}

	public Matricula removeMatricula(Matricula matricula) {
		getMatriculas().remove(matricula);
		matricula.setCursoParalelo(null);

		return matricula;
	}

}