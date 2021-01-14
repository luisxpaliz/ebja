package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the plantilla_nota database table.
 * 
 */
@Entity
@Table(name="plantilla_nota",schema="ebja")
@NamedQuery(name="PlantillaNota.findAll", query="SELECT p FROM PlantillaNota p")
public class PlantillaNota implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PLANTILLA_NOTA_IDPLANTILLANOTA_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PLANTILLA_NOTA_IDPLANTILLANOTA_GENERATOR")
	@Column(name="id_plantilla_nota")
	private Integer idPlantillaNota;

	private String estado;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;

	@Column(name="ip_usuario")
	private String ipUsuario;

	@Column(name="nemonico_plantilla")
	private String nemonicoPlantilla;

	@Column(name="nombre_nota")
	private String nombreNota;

	@Column(name="nombre_plantilla")
	private String nombrePlantilla;

	//bi-directional many-to-one association to ModeloEvaluacion
	@ManyToOne
	@JoinColumn(name="id_modelo_evaluacion")
	private ModeloEvaluacion modeloEvaluacion;
		
	//bi-directional many-to-one association to TipoNota
	@ManyToOne
	@JoinColumn(name="id_tipo_nota")
	private TipoNota tipoNota;

	//bi-directional many-to-one association to Nota
	@OneToMany(mappedBy="plantillaNota")
	private List<Nota> nota;
	
	//bi-directional many-to-one association to TipoNota
	@ManyToOne
	@JoinColumn(name="id_malla")
	private Malla malla;

	public PlantillaNota() {
	}

	public Integer getIdPlantillaNota() {
		return this.idPlantillaNota;
	}

	public void setIdPlantillaNota(Integer idPlantillaNota) {
		this.idPlantillaNota = idPlantillaNota;
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

	public String getNemonicoPlantilla() {
		return this.nemonicoPlantilla;
	}

	public void setNemonicoPlantilla(String nemonicoPlantilla) {
		this.nemonicoPlantilla = nemonicoPlantilla;
	}

	public String getNombreNota() {
		return this.nombreNota;
	}

	public void setNombreNota(String nombreNota) {
		this.nombreNota = nombreNota;
	}

	public String getNombrePlantilla() {
		return this.nombrePlantilla;
	}

	public void setNombrePlantilla(String nombrePlantilla) {
		this.nombrePlantilla = nombrePlantilla;
	}
	
	public ModeloEvaluacion getModeloEvaluacion() {
		return this.modeloEvaluacion;
	}

	public void setModeloEvaluacion(ModeloEvaluacion modeloEvaluacion) {
		this.modeloEvaluacion = modeloEvaluacion;
	}

	public TipoNota getTipoNota() {
		return this.tipoNota;
	}

	public void setTipoNota(TipoNota tipoNota) {
		this.tipoNota = tipoNota;
	}

	public List<Nota> getNota() {
		return this.nota;
	}

	public void setNotas(List<Nota> nota) {
		this.nota = nota;
	}

	public Nota addNota(Nota nota) {
		getNota().add(nota);
		nota.setPlantillaNota(this);

		return nota;
	}

	public Nota removeNota(Nota nota) {
		getNota().remove(nota);
		nota.setPlantillaNota(null);

		return nota;
	}

	public Malla getMalla() {
		return malla;
	}

	public void setMalla(Malla malla) {
		this.malla = malla;
	}
	
	

}