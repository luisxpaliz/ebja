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
@Table(name="notas")
@NamedQuery(name="Nota.findAll", query="SELECT n FROM Nota n")
public class Nota implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="NOTAS_IDNOTAS_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NOTAS_IDNOTAS_GENERATOR")
	@Column(name="id_notas")
	private Integer idNotas;

	private String estado;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="id_jornada")
	private Integer idJornada;

	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;

	@Column(name="ip_usuario")
	private String ipUsuario;

	private String nemonico;

	private BigDecimal nota;

	//bi-directional many-to-one association to Matricula
	@ManyToOne
	@JoinColumn(name="id_matricula")
	private Matricula matricula;

	//bi-directional many-to-one association to PlantillaNota
	@ManyToOne
	@JoinColumn(name="id_plantilla_nota")
	private PlantillaNota plantillaNota;

	public Nota() {
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

	public Integer getIdJornada() {
		return this.idJornada;
	}

	public void setIdJornada(Integer idJornada) {
		this.idJornada = idJornada;
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

	public Matricula getMatricula() {
		return this.matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public PlantillaNota getPlantillaNota() {
		return this.plantillaNota;
	}

	public void setPlantillaNota(PlantillaNota plantillaNota) {
		this.plantillaNota = plantillaNota;
	}

}