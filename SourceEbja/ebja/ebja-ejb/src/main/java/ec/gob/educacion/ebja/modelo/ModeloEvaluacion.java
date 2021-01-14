package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the modelo_evaluacion database table.
 * 
 */
@Entity
@Table(name="modelo_evaluacion",schema="ebja")
@NamedQuery(name="ModeloEvaluacion.findAll", query="SELECT m FROM ModeloEvaluacion m")
public class ModeloEvaluacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MODELO_EVALUACION_IDMODELOEVALUACION_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MODELO_EVALUACION_IDMODELOEVALUACION_GENERATOR")
	@Column(name="id_modelo_evaluacion")
	private Integer idModeloEvaluacion;

	private String estado;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_fin")
	private Timestamp fechaFin;

	@Column(name="fecha_inicio")
	private Timestamp fechaInicio;

	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;

	@Column(name="ip_usuario")
	private String ipUsuario;

	@Column(name="numero_decimal_calificacion")
	private Integer numeroDecimalCalificacion;

	@Column(name="porcentaje_examen")
	private Integer porcentajeExamen;

	//bi-directional many-to-one association to PlantillaNota
	@OneToMany(mappedBy="modeloEvaluacion")
	private List<PlantillaNota> plantillaNotas;

	public ModeloEvaluacion() {
	}

	public Integer getIdModeloEvaluacion() {
		return this.idModeloEvaluacion;
	}

	public void setIdModeloEvaluacion(Integer idModeloEvaluacion) {
		this.idModeloEvaluacion = idModeloEvaluacion;
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

	public Timestamp getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Timestamp getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
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

	public Integer getNumeroDecimalCalificacion() {
		return this.numeroDecimalCalificacion;
	}

	public void setNumeroDecimalCalificacion(Integer numeroDecimalCalificacion) {
		this.numeroDecimalCalificacion = numeroDecimalCalificacion;
	}

	public Integer getPorcentajeExamen() {
		return this.porcentajeExamen;
	}

	public void setPorcentajeExamen(Integer porcentajeExamen) {
		this.porcentajeExamen = porcentajeExamen;
	}
	public List<PlantillaNota> getPlantillaNotas() {
		return this.plantillaNotas;
	}

	public void setPlantillaNotas(List<PlantillaNota> plantillaNotas) {
		this.plantillaNotas = plantillaNotas;
	}

	public PlantillaNota addPlantillaNota(PlantillaNota plantillaNota) {
		getPlantillaNotas().add(plantillaNota);
		plantillaNota.setModeloEvaluacion(this);

		return plantillaNota;
	}

	public PlantillaNota removePlantillaNota(PlantillaNota plantillaNota) {
		getPlantillaNotas().remove(plantillaNota);
		plantillaNota.setModeloEvaluacion(null);

		return plantillaNota;
	}

}