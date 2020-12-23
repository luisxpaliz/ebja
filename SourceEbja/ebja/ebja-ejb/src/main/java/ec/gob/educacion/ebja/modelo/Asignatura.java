package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;

import ec.gob.educacion.ebja.modelo.zeus.Area;
import ec.gob.educacion.ebja.modelo.zeus.Materia;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "asignatura",schema="ebja")

@NamedQueries({
	@NamedQuery(name="Asignatura.findAll", query="SELECT a FROM Asignatura a"),
	@NamedQuery(name="Asignatura.findAllActive", query="SELECT a FROM Asignatura a where a.estado='1' "),
	@NamedQuery(name="Asignatura.findById", query="SELECT a FROM Asignatura a where a.id = :id")})
public class Asignatura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "asignatura_generador", sequenceName = "ebja.asignatura_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="asignatura_generador")
	private Integer id;

	private String estado;
	
	private String nemonico;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;
	
	@Column(name="ip_usuario")
	private String ipUsuario;
	
	//bi-directional many-to-one association to ProgramaInstitucion
	@ManyToOne
	@JoinColumn(name="id_area")
	private Area area;
	
	//bi-directional many-to-one association to Materia
	@ManyToOne
	@JoinColumn(name="id_materia")
	private Materia materia;
	
	//bi-directional many-to-one association to Malla
	@OneToMany(mappedBy="asignatura")
	private List<Malla> mallas;

	public Asignatura() {
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

	public String getNemonico() {
		return nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
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
	
	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Materia getMateria() {
		return this.materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
	public List<Malla> getMallas() {
		return this.mallas;
	}

	public void setMallas(List<Malla> mallas) {
		this.mallas = mallas;
	}

	public Malla addMalla(Malla malla) {
		getMallas().add(malla);
		malla.setAsignatura(this);

		return malla;
	}

	public Malla removeMalla(Malla malla) {
		getMallas().remove(malla);
		malla.setAsignatura(null);

		return malla;
	}

}