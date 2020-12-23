package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "detalle_traslado",schema="ebja")
@NamedQuery(name="DetalleTraslado.findAll", query="SELECT t FROM DetalleTraslado t")
public class DetalleTraslado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "detalle_traslado_generador", sequenceName = "ebja.detalle_traslado_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="detalle_traslado_generador")
	private Integer id;

	private String estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;
	
	@Column(name="ip_usuario")
	private String ipUsuario;
	
	@Column(name="suministro_luz")
	private String suministroLuz;
	
	//bi-directional many-to-one association to CursoParalelo
	@ManyToOne
	@JoinColumn(name="id_matricula")
	private Matricula matricula;
	
	//bi-directional many-to-one association to CursoParalelo
	@ManyToOne
	@JoinColumn(name="id_matricula_curso_paralelo")
	private CursoParalelo cursoParalelo;

	//bi-directional many-to-one association to ProgramaInstitucion
	@ManyToOne
	@JoinColumn(name="id_matricula_programa_institucion")
	private ProgramaInstitucion programaInstitucion;
	
	//bi-directional many-to-one association to CatalogoEbja
	@ManyToOne
	@JoinColumn(name="id_matricula_tipo_proceso_origen", referencedColumnName = "id")
	private CatalogoEbja catalogoTipoProceso;

	@Column(name="dpa_parroquia")
	private String dpaParroquia;

	public DetalleTraslado() {
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
	
	public CatalogoEbja getCatalogoTipoProceso() {
		return catalogoTipoProceso;
	}

	public void setCatalogoTipoProceso(CatalogoEbja catalogoTipoProceso) {
		this.catalogoTipoProceso = catalogoTipoProceso;
	}

	public String getSuministroLuz() {
		return suministroLuz;
	}

	public void setSuministroLuz(String suministroLuz) {
		this.suministroLuz = suministroLuz;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public String getDpaParroquia() {
		return dpaParroquia;
	}

	public void setDpaParroquia(String dpaParroquia) {
		this.dpaParroquia = dpaParroquia;
	}

}