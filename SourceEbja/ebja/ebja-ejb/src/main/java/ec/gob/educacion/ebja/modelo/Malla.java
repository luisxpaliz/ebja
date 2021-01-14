package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;

import ec.gob.educacion.ebja.modelo.zeus.Grado;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "malla",schema="ebja")
@NamedQueries({
@NamedQuery(name="Malla.findAllActive", query="SELECT m FROM Malla m where m.estado='1' "), 
@NamedQuery(name="Malla.findByDescripcion", query="SELECT m FROM Malla m")})
public class Malla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "malla_generador", sequenceName = "ebja.malla_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="malla_generador")
	private Integer id;

	private String descripcion;

	private String estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;
	
	@Column(name="ip_usuario")
	private String ipUsuario;

	@Column(name="horas_clase")
	private Integer horasClase;
	
	private String nemonico;

	//bi-directional many-to-one association to Asignatura
	@ManyToOne
	@JoinColumn(name="id_asignatura")
	private Asignatura asignatura;

	//bi-directional many-to-one association to ProgramaEbja
	@ManyToOne
	@JoinColumn(name="id_programa_grado")
	private ProgramaGrado programaGrado;

	
	//bi-directional many-to-one association to PlantillaNota
	@OneToMany(mappedBy="malla")
	private List<PlantillaNota> plantillaNota;
	
	//bi-directional many-to-one association to PlantillaNota
	@OneToMany(mappedBy="malla")
	private List<DocenteCurso> docenteCurso;

	public Malla() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Integer getHorasClase() {
		return this.horasClase;
	}

	public void setHorasClase(Integer horasClase) {
		this.horasClase = horasClase;
	}

	public Asignatura getAsignatura() {
		return this.asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	

	public ProgramaGrado getProgramaGrado() {
		return programaGrado;
	}

	public void setProgramaGrado(ProgramaGrado programaGrado) {
		this.programaGrado = programaGrado;
	}

	public String getNemonico() {
		return nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
	}

	public List<PlantillaNota> getPlantillaNota() {
		return plantillaNota;
	}

	public void setPlantillaNota(List<PlantillaNota> plantillaNota) {
		this.plantillaNota = plantillaNota;
	}

	public List<DocenteCurso> getDocenteCurso() {
		return docenteCurso;
	}

	public void setDocenteCurso(List<DocenteCurso> docenteCurso) {
		this.docenteCurso = docenteCurso;
	}
	
	

}