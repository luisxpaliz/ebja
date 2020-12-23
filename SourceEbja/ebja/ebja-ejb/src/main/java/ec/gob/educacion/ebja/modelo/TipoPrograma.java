package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

@Entity
@Table(name="tipo_programa", schema = "ebja")
@NamedQueries({
@NamedQuery(name="TipoPrograma.findByNemonico", query="SELECT t FROM TipoPrograma t where t.nemonico =:nemonico"),
@NamedQuery(name="TipoPrograma.findAll", query="SELECT t FROM TipoPrograma t")})
public class TipoPrograma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "tipo_programa", sequenceName = "ebja.tipo_programa_ebja_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "tipo_programa")
	@Basic(optional = false)
	private Integer id;

	private String estado;

	private String nemonico;

	private String nombre;
	
	//bi-directional many-to-one association to ProgramaEbja
	@OneToMany(mappedBy="tipoPrograma")
	private List<ProgramaEbja> programaEbjas;

	public TipoPrograma() {
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
	
	public List<ProgramaEbja> getProgramaEbjas() {
		return this.programaEbjas;
	}

	public void setProgramaEbjas(List<ProgramaEbja> programaEbjas) {
		this.programaEbjas = programaEbjas;
	}

	public ProgramaEbja addProgramaEbja(ProgramaEbja programaEbja) {
		getProgramaEbjas().add(programaEbja);
		programaEbja.setTipoPrograma(this);

		return programaEbja;
	}

	public ProgramaEbja removeProgramaEbja(ProgramaEbja programaEbja) {
		getProgramaEbjas().remove(programaEbja);
		programaEbja.setTipoPrograma(null);

		return programaEbja;
	}
}