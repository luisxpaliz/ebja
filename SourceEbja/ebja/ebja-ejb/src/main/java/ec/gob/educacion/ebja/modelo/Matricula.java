package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "matricula",schema="ebja")
@NamedQuery(name="Matricula.findAll", query="SELECT m FROM Matricula m")
public class Matricula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "matricula_generador", sequenceName = "ebja.matricula_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="matricula_generador")
	private Integer id;

	private String estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_proceso")
	private Date fechaProceso;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;
	
	@Column(name="ip_usuario")
	private String ipUsuario;
	
	private String promover;
	
	//bi-directional many-to-one association to CursoParalelo
	@ManyToOne
	@JoinColumn(name="id_curso_paralelo")
	private CursoParalelo cursoParalelo;

	//bi-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name="id_estudiante")
	private Estudiante estudiante;

	//bi-directional many-to-one association to ProgramaInstitucion
	@ManyToOne
	@JoinColumn(name="id_programa_institucion")
	private ProgramaInstitucion programaInstitucion;
	
	//bi-directional many-to-one association to CatalogoEbja
	@ManyToOne
	@JoinColumn(name="id_tipo_proceso_catalogo", referencedColumnName = "id")
	private CatalogoEbja catalogoTipoProceso;
	
	// bi-directional many-to-one association to ModeloAsistencia
	@OneToMany(mappedBy = "matricula")
	private List<ModeloAsistencia> modeloAsistencias;
	
	//bi-directional many-to-one association to ParaleloGrado
		@ManyToOne
		@JoinColumn(name="id_paralelo_grado")
		private ParaleloGrado paraleloGrado;

	
	@Transient
	private boolean asignarParalelo;

	public Matricula() {
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
	
	public Date getFechaProceso() {
		return this.fechaProceso;
	}

	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
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
	
	public String getPromover() {
		return this.promover;
	}

	public void setPromover(String promover) {
		this.promover = promover;
	}
	
	public CursoParalelo getCursoParalelo() {
		return this.cursoParalelo;
	}

	public void setCursoParalelo(CursoParalelo cursoParalelo) {
		this.cursoParalelo = cursoParalelo;
	}

	public Estudiante getEstudiante() {
		return this.estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public ProgramaInstitucion getProgramaInstitucion() {
		return this.programaInstitucion;
	}

	public void setProgramaInstitucion(ProgramaInstitucion programaInstitucion) {
		this.programaInstitucion = programaInstitucion;
	}
	
	public CatalogoEbja getCatalogoTipoProceso() {
		return catalogoTipoProceso;
	}

	public void setCatalogoTipoProceso(CatalogoEbja catalogoTipoProceso) {
		this.catalogoTipoProceso = catalogoTipoProceso;
	}

	public boolean getAsignarParalelo() {
		asignarParalelo = cursoParalelo == null ? false : true;
		return asignarParalelo;
	}

	public void setAsignarParalelo(boolean asignarParalelo) {
		this.asignarParalelo = asignarParalelo;
	}
	
	public ParaleloGrado getParaleloGrado() {
		return this.paraleloGrado;
	}

	public void setParaleloGrado(ParaleloGrado paraleloGrado) {
		this.paraleloGrado = paraleloGrado;
	}


}