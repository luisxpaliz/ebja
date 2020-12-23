package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tipo_nota database table.
 * 
 */
@Entity
@Table(name="tipo_nota",schema="ebja")
@NamedQueries({
@NamedQuery(name="TipoNota.findAll", query="SELECT t FROM TipoNota t"),
@NamedQuery(name="TipoNota.findAllActive", query="SELECT t FROM TipoNota t where t.estado = '1'")})
public class TipoNota implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPO_NOTA_IDTIPONOTA_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_NOTA_IDTIPONOTA_GENERATOR")
	@Column(name="id_tipo_nota")
	private Integer idTipoNota;

	private String estado;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="id_grado")
	private Integer idGrado;

	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;

	@Column(name="ip_usuario")
	private String ipUsuario;

	private String nemonico;

	private String nombre;

	//bi-directional many-to-one association to PlantillaNota
	@OneToMany(mappedBy="tipoNota")
	private List<PlantillaNota> plantillaNotas;

	//bi-directional many-to-one association to TipoNota
	@ManyToOne
	@JoinColumn(name="id_tipo_nota_padre")
	private TipoNota tipoNota;

	//bi-directional many-to-one association to TipoNota
	@OneToMany(mappedBy="tipoNota")
	private List<TipoNota> tipoNotas;

	public TipoNota() {
	}

	public Integer getIdTipoNota() {
		return this.idTipoNota;
	}

	public void setIdTipoNota(Integer idTipoNota) {
		this.idTipoNota = idTipoNota;
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

	public Integer getIdGrado() {
		return this.idGrado;
	}

	public void setIdGrado(Integer idGrado) {
		this.idGrado = idGrado;
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

	public List<PlantillaNota> getPlantillaNotas() {
		return this.plantillaNotas;
	}

	public void setPlantillaNotas(List<PlantillaNota> plantillaNotas) {
		this.plantillaNotas = plantillaNotas;
	}

	public PlantillaNota addPlantillaNota(PlantillaNota plantillaNota) {
		getPlantillaNotas().add(plantillaNota);
		plantillaNota.setTipoNota(this);

		return plantillaNota;
	}

	public PlantillaNota removePlantillaNota(PlantillaNota plantillaNota) {
		getPlantillaNotas().remove(plantillaNota);
		plantillaNota.setTipoNota(null);

		return plantillaNota;
	}

	public TipoNota getTipoNota() {
		return this.tipoNota;
	}

	public void setTipoNota(TipoNota tipoNota) {
		this.tipoNota = tipoNota;
	}

	public List<TipoNota> getTipoNotas() {
		return this.tipoNotas;
	}

	public void setTipoNotas(List<TipoNota> tipoNotas) {
		this.tipoNotas = tipoNotas;
	}

	public TipoNota addTipoNota(TipoNota tipoNota) {
		getTipoNotas().add(tipoNota);
		tipoNota.setTipoNota(this);

		return tipoNota;
	}

	public TipoNota removeTipoNota(TipoNota tipoNota) {
		getTipoNotas().remove(tipoNota);
		tipoNota.setTipoNota(null);

		return tipoNota;
	}

}