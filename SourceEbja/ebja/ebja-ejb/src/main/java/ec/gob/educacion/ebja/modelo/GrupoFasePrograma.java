package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "grupo_fase_programa",schema="ebja")
@NamedQueries({
@NamedQuery(name="GrupoFasePrograma.findByCodigo", query="SELECT c FROM Acuerdo c WHERE c.nemonico = :nemonico"),
@NamedQuery(name="GrupoFasePrograma.findAllActive", query="SELECT a FROM GrupoFasePrograma a where a.estado = '1'")})
public class GrupoFasePrograma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "grupo_fase_acuerdo_generador", sequenceName = "ebja.grupo_fase_programa_id_grupo_fase_programa_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="grupo_fase_acuerdo_generador")
	@Column(name="id_grupo_fase_programa")
	private long idGrupoFaseNotas;

	private String estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;
	
	@Column(name="ip_usuario")
	private String ipUsuario;

	private String nemonico;

	private String nombre;
	
	@Column(name="fase_externa")
	private Integer faseExterna;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_inicio")
	private Date fechaInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_fin")
	private Date fechaFin;
	
	@ManyToOne
	@JoinColumn(name="id_programa_educativo")
	private ProgramaEducativo programaEducativo;
	
	@OneToMany(mappedBy="grupoFasePrograma")
	private List<ProgramaEbja> programasEbja;
	
	public GrupoFasePrograma() {
	}

	public long getIdGrupoFaseNotas() {
		return idGrupoFaseNotas;
	}

	public void setIdGrupoFaseNotas(long idGrupoFaseNotas) {
		this.idGrupoFaseNotas = idGrupoFaseNotas;
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

	public String getNemonico() {
		return this.nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public ProgramaEducativo getProgramaEducativo() {
		return programaEducativo;
	}

	public void setProgramaEducativo(ProgramaEducativo programaEducativo) {
		this.programaEducativo = programaEducativo;
	}

	public List<ProgramaEbja> getProgramasEbja() {
		return programasEbja;
	}

	public void setProgramasEbja(List<ProgramaEbja> programasEbja) {
		this.programasEbja = programasEbja;
	}

	public Integer getFaseExterna() {
		return faseExterna;
	}

	public void setFaseExterna(Integer faseExterna) {
		this.faseExterna = faseExterna;
	}
	
	
	

}
