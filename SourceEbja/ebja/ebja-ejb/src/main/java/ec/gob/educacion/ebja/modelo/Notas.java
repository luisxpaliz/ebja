package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the notas database table.
 * 
 */
@Entity
@Table(name="notas",schema="ebja")
@NamedQuery(name="Notas.findAll", query="SELECT n FROM Notas n where n.idNotas <7")
public class Notas implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "notas_generador", sequenceName = "ebja.notas_id_notas_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="notas_generador")
	@Column(name="id_notas")
	private Integer idNotas;

	private String estado;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;

	@Column(name="ip_usuario")
	private String ipUsuario;

	private String nemonico;

	private BigDecimal nota;

	//bi-directional many-to-one association to ModeloEvaluacion
	@ManyToOne
	@JoinColumn(name="id_modelo_evaluacion")
	private ModeloEvaluacion modeloEvaluacion;

	//bi-directional many-to-one association to PlantillaEstudiante
	@ManyToOne
	@JoinColumn(name="id_plantilla_estudiante")
	private PlantillaEstudiante plantillaEstudiante;

	//bi-directional many-to-one association to PlantillaNota
	@ManyToOne
	@JoinColumn(name="id_plantilla_nota")
	private PlantillaNota plantillaNota;

	public Notas() {
	}

	public Integer getIdNotas() {
		return this.idNotas;
	}

	public void setIdNotas(Integer idNotas) {
		this.idNotas = idNotas;
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

	public String getNemonico() {
		return this.nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
	}

	public BigDecimal getNota() {
		return this.nota;
	}

	public void setNota(BigDecimal nota) {
		this.nota = nota;
	}

	public ModeloEvaluacion getModeloEvaluacion() {
		return this.modeloEvaluacion;
	}

	public void setModeloEvaluacion(ModeloEvaluacion modeloEvaluacion) {
		this.modeloEvaluacion = modeloEvaluacion;
	}

	public PlantillaEstudiante getPlantillaEstudiante() {
		return this.plantillaEstudiante;
	}

	public void setPlantillaEstudiante(PlantillaEstudiante plantillaEstudiante) {
		this.plantillaEstudiante = plantillaEstudiante;
	}

	public PlantillaNota getPlantillaNota() {
		return this.plantillaNota;
	}

	public void setPlantillaNota(PlantillaNota plantillaNota) {
		this.plantillaNota = plantillaNota;
	}

}