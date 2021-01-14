package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "programa_ebja", schema = "ebja")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "ProgramaEbja.findAll", query = "SELECT c FROM ProgramaEbja c "),
		@NamedQuery(name = "ProgramaEbja.findAllActive", query = "SELECT c FROM ProgramaEbja c where c.estado = '1' order by c.id "),
		@NamedQuery(name = "ProgramaEbja.findAllActiveExt", query = "SELECT c FROM ProgramaEbja c where c.estado = '1' and c.grupoFasePrograma.faseExterna = 0 and c.nemonico not like '%NIN%' order by c.id "),
		@NamedQuery(name = "ProgramaEbja.findById", query = "SELECT c FROM ProgramaEbja c WHERE c.id = :id"),
		@NamedQuery(name = "ProgramaEbja.findByCodigo", query = "SELECT c FROM ProgramaEbja c WHERE c.nemonico = :nemonico"),
		@NamedQuery(name = "ProgramaEbja.findByNombre", query = "SELECT c FROM ProgramaEbja c WHERE c.nombre = :nombre"),
		@NamedQuery(name = "ProgramaEbja.findByIdUsuarioCreacion", query = "SELECT c FROM ProgramaEbja c WHERE c.idUsuarioCreacion = :idUsuarioCreacion"),
		@NamedQuery(name = "ProgramaEbja.findByFechaCreacion", query = "SELECT c FROM ProgramaEbja c WHERE c.fechaCreacion = :fechaCreacion"),
		@NamedQuery(name = "ProgramaEbja.findByFaseGrupo", query = "SELECT c FROM ProgramaEbja c WHERE c.grupoFasePrograma.idGrupoFaseNotas = :idFaseGrupo and c.tipoGrupoFase =:idTipoGrupoFase and c.estado ='1' "),
		@NamedQuery(name = "ProgramaEbja.findByFaseGrupoExtraordinaria", query = "SELECT c FROM ProgramaEbja c WHERE c.grupoFasePrograma.idGrupoFaseNotas = :idFaseGrupo and c.tipoGrupoFase =:idFaseGrupo and c.estado ='1' order by c.secuenciaPrograma "),
		@NamedQuery(name = "ProgramaEbja.findByFaseGrupoExtraordinariaNemonico", query = "SELECT c FROM ProgramaEbja c WHERE c.grupoFasePrograma.nemonico = :nemonico and c.estado ='1' order by c.secuenciaPrograma "),
		@NamedQuery(name = "ProgramaEbja.findByProgramaEbjaFase", query = "SELECT c.grupoFasePrograma FROM ProgramaEbja c WHERE c.nemonico = :nemonico and c.estado ='1'"),
		@NamedQuery(name = "ProgramaEbja.findNinguno", query = "SELECT c FROM ProgramaEbja c WHERE c.nemonico like '%NIN%' and c.estado ='1' "),
		@NamedQuery(name = "ProgramaEbja.findByEstado", query = "SELECT c FROM ProgramaEbja c WHERE c.estado = :estado") })
public class ProgramaEbja implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "modulo_generador", sequenceName = "ebja.programa_ebja_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "modulo_generador")
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Column(name = "edad_minima")
	private Integer edadMinima;

	@Basic(optional = false)
	@NotNull
	@Column(name = "estado")
	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	@Column(name = "id_usuario_creacion")
	private Integer idUsuarioCreacion;

	@Column(name = "ip_usuario")
	private String ipUsuario;

	@Column(name = "visible")
	private Integer visible;

	@Basic(optional = false)
	@NotNull
	@Column(name = "fecha_fin")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFin;

	@Basic(optional = false)
	@NotNull
	@Column(name = "fecha_inicio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;

	@Basic(optional = false)
	@NotNull
	@Column(name = "fecha_inicio_clases")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicioClases;

	private String nemonico;

	private String nombre;

	@Column(name = "secuencia_programa")
	private Integer secuenciaPrograma;

	@Column(name = "es_pack")
	private Integer esPack;

	@Column(name = "rezago_minimo")
	private Integer rezagoMinimo;

	@Column(name = "secuencia_inscripcion")
	private Integer SecInscripcion;

	@Column(name = "tipo_grupo_fase")
	private long tipoGrupoFase;

	private String cobertura;

	// bi-directional many-to-one association to Mensaje
	@OneToMany(mappedBy = "programaEbja",fetch = FetchType.EAGER)
	private Set<Mensaje> mensajes;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "programa_acuerdo", schema = "ebja", joinColumns = {
			@JoinColumn(name = "id_programa_ebja", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_acuerdo", nullable = false, updatable = false) })
	private Set<Acuerdo> acuerdos;

	// bi-directional many-to-one association to Modalidad
	@ManyToOne
	@JoinColumn(name = "id_modalidad")
	private Modalidad modalidad;

	// bi-directional many-to-one association to TipoPrograma
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipo_programa")
	private TipoPrograma tipoPrograma;

	// bi-directional many-to-one association to ProgramaGrado
	@OneToMany(mappedBy = "programaEbja", fetch = FetchType.EAGER, cascade = CascadeType.MERGE )
	private List<ProgramaGrado> programaGrados;

	// bi-directional many-to-one association to ReglaNegocio
	@OneToMany(mappedBy = "programaEbja",fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	private Set<ReglaNegocio> reglaNegocios;

	@ManyToOne
	@JoinColumn(name = "id_grupo_fase_programa")
	private GrupoFasePrograma grupoFasePrograma;

	@Transient
	private String nombreProgramaModalidad;
	

	public ProgramaEbja() {
	}

	public ProgramaEbja(Integer id) {
		this.id = id;
	}

	public ProgramaEbja(Integer id, Integer edadMinima, Date fechaFin, Date fechaInicio, String nemonico, String nombre,
			Integer rezagoMinimo, int idUsuarioCreacion, Date fechaCreacion, String estado, Modalidad modalidad) {
		this.id = id;
		this.edadMinima = edadMinima;
		this.fechaFin = fechaFin;
		this.fechaInicio = fechaInicio;
		this.nemonico = nemonico;
		this.nombre = nombre;
		this.rezagoMinimo = rezagoMinimo;
		this.idUsuarioCreacion = idUsuarioCreacion;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
		this.modalidad = modalidad;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEdadMinima() {
		return edadMinima;
	}

	public void setEdadMinima(Integer edadMinima) {
		this.edadMinima = edadMinima;
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

	public String getNemonico() {
		return nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getRezagoMinimo() {
		return rezagoMinimo;
	}

	public void setRezagoMinimo(Integer rezagoMinimo) {
		this.rezagoMinimo = rezagoMinimo;
	}

	public TipoPrograma getTipoPrograma() {
		return tipoPrograma;
	}

	public void setTipoPrograma(TipoPrograma tipoPrograma) {
		this.tipoPrograma = tipoPrograma;
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

	public String getCobertura() {
		return cobertura;
	}

	public void setCobertura(String cobertura) {
		this.cobertura = cobertura;
	}

	public Modalidad getModalidad() {
		return modalidad;
	}

	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
	}

	public String getNombreProgramaModalidad() {
		nombreProgramaModalidad = modalidad == null ? "" : nombre + " - " + modalidad.getNombre();
		return nombreProgramaModalidad;
	}

	public void setNombreProgramaModalidad(String nombreProgramaModalidad) {
		this.nombreProgramaModalidad = nombreProgramaModalidad;
	}

	public Set<Mensaje> getMensajes() {
		return this.mensajes;
	}

	public void setMensajes(Set<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public Mensaje addMensaje(Mensaje mensaje) {
		getMensajes().add(mensaje);
		mensaje.setProgramaEbja(this);

		return mensaje;
	}

	public Mensaje removeMensaje(Mensaje mensaje) {
		getMensajes().remove(mensaje);
		mensaje.setProgramaEbja(null);

		return mensaje;
	}

	public Date getFechaInicioClases() {
		return fechaInicioClases;
	}

	public void setFechaInicioClases(Date fechaInicioClases) {
		this.fechaInicioClases = fechaInicioClases;
	}

	public List<ProgramaGrado> getProgramaGrados() {
		return this.programaGrados;
	}

	public void setProgramaGrados(List<ProgramaGrado> programaGrados) {
		this.programaGrados = programaGrados;
	}

	public ProgramaGrado addProgramaGrado(ProgramaGrado programaGrado) {
		getProgramaGrados().add(programaGrado);
		programaGrado.setProgramaEbja(this);

		return programaGrado;
	}

	public ProgramaGrado removeProgramaGrado(ProgramaGrado programaGrado) {
		getProgramaGrados().remove(programaGrado);
		programaGrado.setProgramaEbja(null);

		return programaGrado;
	}

	public void setAcuerdos(Set<Acuerdo> acuerdos) {
		this.acuerdos = acuerdos;
	}

	public List<Acuerdo> getAcuerdos() {
		return acuerdos.stream().collect(Collectors.toList());
	}

	public GrupoFasePrograma getGrupoFasePrograma() {
		return grupoFasePrograma;
	}

	public void setGrupoFasePrograma(GrupoFasePrograma grupoFasePrograma) {
		this.grupoFasePrograma = grupoFasePrograma;
	}

	public Set<ReglaNegocio> getReglaNegocios() {
		return this.reglaNegocios;
	}

	public void setReglaNegocios(Set<ReglaNegocio> reglaNegocios) {
		this.reglaNegocios = reglaNegocios;
	}

	public ReglaNegocio addReglaNegocio(ReglaNegocio reglaNegocio) {
		getReglaNegocios().add(reglaNegocio);
		reglaNegocio.setProgramaEbja(this);

		return reglaNegocio;
	}

	public ReglaNegocio removeReglaNegocio(ReglaNegocio reglaNegocio) {
		getReglaNegocios().remove(reglaNegocio);
		reglaNegocio.setProgramaEbja(null);

		return reglaNegocio;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ProgramaEbja)) {
			return false;
		}
		ProgramaEbja other = (ProgramaEbja) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	public Object clone() throws CloneNotSupportedException {
		// Assign the shallow copy to new reference variable t
		ProgramaEbja p = (ProgramaEbja) super.clone();
		return p;
	}

	@Override
	public String toString() {
		return "ec.gob.educacion.ebja.modelo.ProgramaEbja[ id=" + id + " ]";
	}

	public Integer getSecuenciaPrograma() {
		return secuenciaPrograma;
	}

	public void setSecuenciaPrograma(Integer secuenciaPrograma) {
		this.secuenciaPrograma = secuenciaPrograma;
	}

	public Integer getEsPack() {
		return esPack;
	}

	public void setEsPack(Integer esPack) {
		this.esPack = esPack;
	}

	public Integer getSecInscripcion() {
		return SecInscripcion;
	}

	public void setSecInscripcion(Integer secInscripcion) {
		SecInscripcion = secInscripcion;
	}

	public Integer getVisible() {
		return visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}

	public long getTipoGrupoFase() {
		return tipoGrupoFase;
	}

	public void setTipoGrupoFase(long tipoGrupoFase) {
		this.tipoGrupoFase = tipoGrupoFase;
	}

}