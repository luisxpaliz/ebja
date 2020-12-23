package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;

import ec.gob.educacion.ebja.modelo.zeus.InstitucEstablec;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="programa_institucion", schema = "ebja")
@NamedQueries({
    @NamedQuery(name = "ProgramaInstitucion.findAll", query = "Select p from Paralelo p")
    , @NamedQuery(name = "ProgramaInstitucion.findById", query = "SELECT pi FROM ProgramaInstitucion pi WHERE pi.id = :id")
    , @NamedQuery(name = "ProgramaInstitucion.findByIdProgramaEbja", query = "SELECT pi FROM ProgramaInstitucion pi WHERE pi.programaGrado.programaEbja.id = :idProgramaEbja and pi.estado = '1'")})
public class ProgramaInstitucion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "programa_institucion_generador", sequenceName = "ebja.programa_institucion_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="programa_institucion_generador")
	private Integer id;

	@Column(name="cupo_disponible")
	private Integer cupoDisponible;

	private String estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;
	
	@Column(name="ip_usuario")
	private String ipUsuario;
	
	@Column(name="total_aforo")
	private Integer totalAforo;
	
	@Column(name="total_banca")
	private Integer totalBanca;
	
	//bi-directional many-to-one association to CursoParalelo
	@OneToMany(mappedBy="programaInstitucion")
	private List<CursoParalelo> cursoParalelos;

	//bi-directional many-to-one association to DocenteCurso
	@OneToMany(mappedBy="programaInstitucion")
	private List<DocenteCurso> docenteCursos;

	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="programaInstitucion")
	private List<Matricula> matriculas;

	//bi-directional many-to-one association to ProgramaEbja
	@ManyToOne
	@JoinColumn(name="id_programa_grado")
	private ProgramaGrado programaGrado;

	//bi-directional many-to-one association to ProgramaEbja
	@ManyToOne
	@JoinColumn(name="id_instituc_establec")
	private InstitucEstablec institucEstablec;
	
	@Transient
	private boolean estadoProceso;
	
	public ProgramaInstitucion() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCupoDisponible() {
		return this.cupoDisponible;
	}

	public void setCupoDisponible(Integer cupoDisponible) {
		this.cupoDisponible = cupoDisponible;
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
	
	public Integer getTotalAforo() {
		return this.totalAforo;
	}

	public void setTotalAforo(Integer totalAforo) {
		this.totalAforo = totalAforo;
	}

	public Integer getTotalBanca() {
		return this.totalBanca;
	}

	public void setTotalBanca(Integer totalBanca) {
		this.totalBanca = totalBanca;
	}

	public List<CursoParalelo> getCursoParalelos() {
		return this.cursoParalelos;
	}

	public void setCursoParalelos(List<CursoParalelo> cursoParalelos) {
		this.cursoParalelos = cursoParalelos;
	}

	public CursoParalelo addCursoParalelo(CursoParalelo cursoParalelo) {
		getCursoParalelos().add(cursoParalelo);
		cursoParalelo.setProgramaInstitucion(this);

		return cursoParalelo;
	}

	public CursoParalelo removeCursoParalelo(CursoParalelo cursoParalelo) {
		getCursoParalelos().remove(cursoParalelo);
		cursoParalelo.setProgramaInstitucion(null);

		return cursoParalelo;
	}

	public List<DocenteCurso> getDocenteCursos() {
		return this.docenteCursos;
	}

	public void setDocenteCursos(List<DocenteCurso> docenteCursos) {
		this.docenteCursos = docenteCursos;
	}

	public DocenteCurso addDocenteCurso(DocenteCurso docenteCurso) {
		getDocenteCursos().add(docenteCurso);
		docenteCurso.setProgramaInstitucion(this);

		return docenteCurso;
	}

	public DocenteCurso removeDocenteCurso(DocenteCurso docenteCurso) {
		getDocenteCursos().remove(docenteCurso);
		docenteCurso.setProgramaInstitucion(null);

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
		matricula.setProgramaInstitucion(this);

		return matricula;
	}

	public Matricula removeMatricula(Matricula matricula) {
		getMatriculas().remove(matricula);
		matricula.setProgramaInstitucion(null);

		return matricula;
	}

	public ProgramaGrado getProgramaGrado() {
		return programaGrado;
	}

	public void setProgramaGrado(ProgramaGrado programaGrado) {
		this.programaGrado = programaGrado;
	}

	public InstitucEstablec getInstitucEstablec() {
		return this.institucEstablec;
	}

	public void setInstitucEstablec(InstitucEstablec institucEstablec) {
		this.institucEstablec = institucEstablec;
	}

	public boolean getEstadoProceso() {
		return estadoProceso;
	}

	public void setEstadoProceso(boolean estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
}