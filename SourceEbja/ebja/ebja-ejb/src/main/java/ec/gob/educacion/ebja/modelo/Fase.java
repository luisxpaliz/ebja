package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "fase",schema="ebja")
@NamedQueries({
	@NamedQuery(name="Fase.findAll", query="SELECT f FROM Fase f"),
	@NamedQuery(name="Fase.findByNemonico", query="SELECT f FROM Fase f where f.nemonico =:nemonico"),
	@NamedQuery(name="Fase.findAllActivo", query="SELECT f FROM Fase f where f.estado ='1' ")})
public class Fase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "fase_generador", sequenceName = "ebja.fase_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "fase_generador")
	@Basic(optional = false)
	private Integer id;

	private String estado;

	private String nemonico;

	private String nombre;

	private String observacion;

	private Integer orden;

	//bi-directional many-to-one association to Fase
	@ManyToOne
	@JoinColumn(name="id_fase_padre")
	private Fase fase;

	//bi-directional many-to-one association to Fase
	@OneToMany(mappedBy="fase")
	private List<Fase> fases;
	
	//bi-directional many-to-one association to Formulario
	@OneToMany(mappedBy="fase")
	private List<Formulario> formularios;

	//bi-directional many-to-one association to ReglaNegocio
	@OneToMany(mappedBy="fase")
	private List<ReglaNegocio> reglaNegocios;

	public Fase() {
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

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Integer getOrden() {
		return this.orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public Fase getFase() {
		return this.fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
	}

	public List<Fase> getFases() {
		return this.fases;
	}

	public void setFases(List<Fase> fases) {
		this.fases = fases;
	}

	public Fase addFas(Fase fase) {
		getFases().add(fase);
		fase.setFase(this);

		return fase;
	}

	public Fase removeFas(Fase fase) {
		getFases().remove(fase);
		fase.setFase(null);

		return fase;
	}
	
	public List<Formulario> getFormularios() {
		return this.formularios;
	}

	public void setFormularios(List<Formulario> formularios) {
		this.formularios = formularios;
	}

	public Formulario addFormulario(Formulario formulario) {
		getFormularios().add(formulario);
		formulario.setFase(this);

		return formulario;
	}

	public Formulario removeFormulario(Formulario formulario) {
		getFormularios().remove(formulario);
		formulario.setFase(null);

		return formulario;
	}

	public List<ReglaNegocio> getReglaNegocios() {
		return this.reglaNegocios;
	}

	public void setReglaNegocios(List<ReglaNegocio> reglaNegocios) {
		this.reglaNegocios = reglaNegocios;
	}

	public ReglaNegocio addReglaNegocio(ReglaNegocio reglaNegocio) {
		getReglaNegocios().add(reglaNegocio);
		reglaNegocio.setFase(this);

		return reglaNegocio;
	}

	public ReglaNegocio removeReglaNegocio(ReglaNegocio reglaNegocio) {
		getReglaNegocios().remove(reglaNegocio);
		reglaNegocio.setFase(null);

		return reglaNegocio;
	}

}