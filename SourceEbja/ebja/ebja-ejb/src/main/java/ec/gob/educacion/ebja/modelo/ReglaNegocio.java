package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="regla_negocio",schema="ebja")
@NamedQueries({@NamedQuery(name="ReglaNegocio.findAll", query="SELECT r FROM ReglaNegocio r"),
	           @NamedQuery(name="ReglaNegocio.findAllActive", query="SELECT r FROM ReglaNegocio r where r.estado='1' ")})
public class ReglaNegocio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "regla_generador", sequenceName = "ebja.regla_negocio_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "regla_generador")
	@Basic(optional = false)
	@Column(name = "id")
	
	private Integer id;

	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;
	
	@Column(name="ip_usuario")
	private String ipUsuario;

	@Column(name="fecha_fin")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFin;

	@Column(name="fecha_inicio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;
	


	//bi-directional many-to-one association to Fase
	@ManyToOne
	@JoinColumn(name="id_fase")
	private Fase fase;

	//bi-directional many-to-one association to ProgramaEbja
	@ManyToOne
	@JoinColumn(name="id_programa_ebja")
	private ProgramaEbja programaEbja;
	
	//bi-directional many-to-one association to DetalleReglaNegocio
	@OneToMany(mappedBy="reglaNegocio")
	private List<DetalleReglaNegocio> detalleReglaNegocios;

	public ReglaNegocio() {
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

	public Fase getFase() {
		return this.fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
	}

	public ProgramaEbja getProgramaEbja() {
		return this.programaEbja;
	}

	public void setProgramaEbja(ProgramaEbja programaEbja) {
		this.programaEbja = programaEbja;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

}