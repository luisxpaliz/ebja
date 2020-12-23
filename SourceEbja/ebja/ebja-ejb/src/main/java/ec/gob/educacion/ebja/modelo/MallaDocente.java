package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="malla_docente",schema="ebja")
@NamedQuery(name="MallaDocente.findAll", query="SELECT m FROM MallaDocente m ")
public class MallaDocente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "mallaDocente_generador", sequenceName = "ebja.malla_docente_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="mallaDocente_generador")
	private Integer id;

	private String estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;
	
	@Column(name="ip_usuario")
	private String ipUsuario;

	//bi-directional many-to-one association to DocenteCurso
	@ManyToOne
	@JoinColumn(name="id_docente_curso")
	private DocenteCurso docenteCurso;
	
	//bi-directional many-to-one association to MallaDocente
	@OneToMany(mappedBy="mallaDocente")
	private List<PlantillaEstudiante> plantillaEstudiantes;
	
	//bi-directional many-to-one association to Malla
	@ManyToOne
	@JoinColumn(name="id_malla")
	private Malla malla;
	
	//bi-directional many-to-one association to PlantillaEstudiante
	@OneToMany(mappedBy="mallaDocente")
	private List<PlantillaEstudiante> plantillaEstudiante;

	public MallaDocente() {
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

	public DocenteCurso getDocenteCurso() {
		return this.docenteCurso;
	}

	public void setDocenteCurso(DocenteCurso docenteCurso) {
		this.docenteCurso = docenteCurso;
	}

	public Malla getMalla() {
		return this.malla;
	}

	public void setMalla(Malla malla) {
		this.malla = malla;
	}

}