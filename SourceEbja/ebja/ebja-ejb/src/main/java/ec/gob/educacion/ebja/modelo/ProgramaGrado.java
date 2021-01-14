package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import ec.gob.educacion.ebja.modelo.zeus.Grado;

@Entity
@Table(name="programa_grado", schema = "ebja")
@NamedQuery(name="ProgramaGrado.findAll", query="SELECT p FROM ProgramaGrado p where p.programaEbja.nemonico =:programaEbja ")
public class ProgramaGrado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "programaGrado_generador", sequenceName = "ebja.programa_grado_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="programaGrado_generador")
	private Integer id;
	
	@Column(name = "estado")
	private String estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;
	
	@Column(name="ip_usuario")
	private String ipUsuario;
	
	@Column(name="visible")
	private Integer visible;
	
	@Column(name="id_pack")
	private Integer Idpack;
	
	@Column(name="grado_inicial")
	private Integer gradoInicial;
	
	@Column(name="secuencia_grado")
	private Integer secuenciaGrado;
	
	@ManyToOne
	@JoinColumn(name="id_grado", referencedColumnName = "id")
	private Grado grado;

	//bi-directional many-to-one association to ProgramaEbja
	@ManyToOne
	@JoinColumn(name="id_programa_ebja")
	private ProgramaEbja programaEbja;
	
	//bi-directional many-to-one association to ProgramaEbja
    @ManyToOne
	@JoinColumn(name="id_mensaje_reporte")
	private MensajeReporte mensajeReporte;
	
	//bi-directional many-to-one association to Malla
	@OneToMany(mappedBy="programaGrado")
	private List<Malla> mallas;
	
	//bi-directional many-to-one association to ProgramaInstitucion
	@OneToMany(mappedBy="programaGrado")
	private List<Inscripcion> Inscripciones;
	
	@Column(name="oferta_siguiente")
	private Integer ofertaSiguiente;
	
	@Column(name="visible_combo_grado_probado")
	private Integer visbleGradoAprobado;

	public ProgramaGrado() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProgramaEbja getProgramaEbja() {
		return this.programaEbja;
	}

	public void setProgramaEbja(ProgramaEbja programaEbja) {
		this.programaEbja = programaEbja;
	}

	public String getEstado() {
		return estado;
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

	public List<Inscripcion> getInscripciones() {
		return Inscripciones;
	}

	public void setInscripciones(List<Inscripcion> inscripciones) {
		Inscripciones = inscripciones;
	}

	public Integer getSecuenciaGrado() {
		return secuenciaGrado;
	}

	public void setSecuenciaGrado(Integer secuenciaGrado) {
		this.secuenciaGrado = secuenciaGrado;
	}

	public Integer getIdpack() {
		return Idpack;
	}

	public void setIdpack(Integer idpack) {
		Idpack = idpack;
	}


	public Integer getGradoInicial() {
		return gradoInicial;
	}

	public void setGradoInicial(Integer gradoInicial) {
		this.gradoInicial = gradoInicial;
	}

	public Grado getGrado() {
		return grado;
	}

	public void setGrado(Grado grado) {
		this.grado = grado;
	}

	public Integer getVisible() {
		return visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}

	public Integer getOfertaSiguiente() {
		return ofertaSiguiente;
	}

	public void setOfertaSiguiente(Integer ofertaSiguiente) {
		this.ofertaSiguiente = ofertaSiguiente;
	}

	public MensajeReporte getMensajeReporte() {
		return mensajeReporte;
	}

	public void setMensajeReporte(MensajeReporte mensajeReporte) {
		this.mensajeReporte = mensajeReporte;
	}

	public Integer getVisbleGradoAprobado() {
		return visbleGradoAprobado;
	}

	public void setVisbleGradoAprobado(Integer visbleGradoAprobado) {
		this.visbleGradoAprobado = visbleGradoAprobado;
	}


}