package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;

import ec.gob.educacion.ebja.modelo.zeus.Grado;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="inscripcion", schema = "ebja")
@NamedQuery(name="Inscripcion.findAll", query="SELECT i FROM Inscripcion i ")
public class Inscripcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "inscripcion_generador", sequenceName = "ebja.inscripcion_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="inscripcion_generador")
	private Integer id;

	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;
	
	@Column(name="ip_usuario")
	private String ipUsuario;

	//bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name="id_grado_aprobado", referencedColumnName = "id")
	private Grado gradoAprobado;

    @Column(name="id_programa_aprobado")
	private long ProgramaAprobado;
	
    @Column(name="id_fase_aprobado")
	private long FaseAprobado;


	//bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name="id_programa_grado", referencedColumnName = "id")
	private ProgramaGrado programaGrado;

	//bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name="id_rezago_catalogo", referencedColumnName = "id")
	private CatalogoEbja catalogoRezago;

	private String nemonico;

	@Column(name="presenta_certificado")
	private String presentaCertificado;

	//bi-directional many-to-one association to RegistroEstudiante
	@OneToMany(mappedBy="inscripcion")
	private List<RegistroEstudiante> registroEstudiantes;
	
	//bi-directional many-to-one association to RegistroEstudiante
	@OneToMany(mappedBy="inscripcion")
	private List<Estudiante> estudiante;

	@Column(name="apellidos_nombres_usuario")
	private String apellidosNombresUsuario;

	@Column(name="numero_identificacion_usuario")
	private String numeroIdentificacionUsuario;
	
	@Transient
	private boolean estadoProceso;

	public Inscripcion() {
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

	public String getNemonico() {
		return this.nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
	}

	public String getPresentaCertificado() {
		return this.presentaCertificado;
	}

	public void setPresentaCertificado(String presentaCertificado) {
		this.presentaCertificado = presentaCertificado;
	}

	public List<RegistroEstudiante> getRegistroEstudiantes() {
		return this.registroEstudiantes;
	}

	public void setRegistroEstudiantes(List<RegistroEstudiante> registroEstudiantes) {
		this.registroEstudiantes = registroEstudiantes;
	}

	public RegistroEstudiante addRegistroEstudiante(RegistroEstudiante registroEstudiante) {
		getRegistroEstudiantes().add(registroEstudiante);
		registroEstudiante.setInscripcion(this);

		return registroEstudiante;
	}

	public RegistroEstudiante removeRegistroEstudiante(RegistroEstudiante registroEstudiante) {
		getRegistroEstudiantes().remove(registroEstudiante);
		registroEstudiante.setInscripcion(null);

		return registroEstudiante;
	}

	public CatalogoEbja getCatalogoRezago() {
		return catalogoRezago;
	}

	public void setCatalogoRezago(CatalogoEbja catalogoRezago) {
		this.catalogoRezago = catalogoRezago;
	}

	public Grado getGradoAprobado() {
		return gradoAprobado;
	}

	public void setGradoAprobado(Grado gradoAprobado) {
		this.gradoAprobado = gradoAprobado;
	}

	
 
	public ProgramaGrado getProgramaGrado() {
		return programaGrado;
	}

	public void setProgramaGrado(ProgramaGrado programaGrado) {
		this.programaGrado = programaGrado;
	}

	public String getApellidosNombresUsuario() {
		return apellidosNombresUsuario;
	}

	public void setApellidosNombresUsuario(String apellidosNombresUsuario) {
		this.apellidosNombresUsuario = apellidosNombresUsuario;
	}

	public String getNumeroIdentificacionUsuario() {
		return numeroIdentificacionUsuario;
	}

	public void setNumeroIdentificacionUsuario(String numeroIdentificacionUsuario) {
		this.numeroIdentificacionUsuario = numeroIdentificacionUsuario;
	}

	public boolean getEstadoProceso() {
		return estadoProceso;
	}

	public void setEstadoProceso(boolean estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	/**
	 * @return the programaAprobado
	 */
	public long getProgramaAprobado() {
		return ProgramaAprobado;
	}

	/**
	 * @param programaAprobado the programaAprobado to set
	 */
	public void setProgramaAprobado(long programaAprobado) {
		ProgramaAprobado = programaAprobado;
	}

	/**
	 * @return the faseAprobado
	 */
	public long getFaseAprobado() {
		return FaseAprobado;
	}

	/**
	 * @param faseAprobado the faseAprobado to set
	 */
	public void setFaseAprobado(long faseAprobado) {
		FaseAprobado = faseAprobado;
	}

	public List<Estudiante> getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(List<Estudiante> estudiante) {
		this.estudiante = estudiante;
	}
	
	

}