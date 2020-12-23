package ec.gob.educacion.ebja.modelo.zeus;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NamedQueries({
	@NamedQuery(name="Materia.findAll", query="SELECT m FROM Materia m"),
	@NamedQuery(name="Materia.findById", query="SELECT m FROM Materia m where m.id = :id")})
public class Materia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String estado;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_migracion")
	private Timestamp fechaMigracion;

	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;

	private String nemonico;

	private String nombre;

	//bi-directional many-to-one association to AreaConocimiento
	@ManyToOne
	@JoinColumn(name="id_area_conocimiento")
	private AreaConocimiento areaConocimiento;

	public Materia() {
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

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Timestamp getFechaMigracion() {
		return this.fechaMigracion;
	}

	public void setFechaMigracion(Timestamp fechaMigracion) {
		this.fechaMigracion = fechaMigracion;
	}

	public Integer getIdUsuarioCreacion() {
		return this.idUsuarioCreacion;
	}

	public void setIdUsuarioCreacion(Integer idUsuarioCreacion) {
		this.idUsuarioCreacion = idUsuarioCreacion;
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

	public AreaConocimiento getAreaConocimiento() {
		return this.areaConocimiento;
	}

	public void setAreaConocimiento(AreaConocimiento areaConocimiento) {
		this.areaConocimiento = areaConocimiento;
	}

}