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
    , @NamedQuery(name = "ProgramaInstitucion.findById", query = "SELECT pi FROM ProgramaInstitucion pi WHERE pi.idGrupoFasePrograma = :idFase")})
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
	
	@Column(name="id_grupo_fase_programa")
	private Integer idGrupoFasePrograma;

	//bi-directional many-to-one association to ProgramaEbja
	@ManyToOne
	@JoinColumn(name="id_instituc_establec")
	private InstitucEstablec institucEstablec;
	
	@Column(name="id_sostenimiento")
	private Integer idSostenimiento;
	
	//bi-directional many-to-one association to CursoParalelo
	@OneToMany(mappedBy="programaInstitucion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CursoParalelo> cursoParalelos;
	
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

	public InstitucEstablec getInstitucEstablec() {
		return this.institucEstablec;
	}

	public void setInstitucEstablec(InstitucEstablec institucEstablec) {
		this.institucEstablec = institucEstablec;
	}

	public Integer getIdSostenimiento() {
		return idSostenimiento;
	}

	public void setIdSostenimiento(Integer idSostenimiento) {
		this.idSostenimiento = idSostenimiento;
	}

	public List<CursoParalelo> getCursoParalelos() {
		return this.cursoParalelos;
	}

	public void setCursoParalelos(List<CursoParalelo> cursoParalelos) {
		this.cursoParalelos = cursoParalelos;
	}

	public Integer getIdGrupoFasePrograma() {
		return idGrupoFasePrograma;
	}

	public void setIdGrupoFasePrograma(Integer idGrupoFasePrograma) {
		this.idGrupoFasePrograma = idGrupoFasePrograma;
	}
	
	
}