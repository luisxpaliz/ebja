package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "modalidad",schema="ebja")
@NamedQueries({
@NamedQuery(name="Modalidad.findAll", query="SELECT m FROM Modalidad m"),
@NamedQuery(name="Modalidad.findByCodigo", query="SELECT m FROM Modalidad m where m.nemonico = :nem"),
@NamedQuery(name="Modalidad.findAllActive", query="SELECT m FROM Modalidad m where m.estado = '1'")})
public class Modalidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "modalidad", sequenceName = "ebja.modalidad_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "modalidad")
	@Basic(optional = false)
	private Integer id;

	private String estado;

	private String nemonico;

	private String nombre;

	//bi-directional many-to-one association to ProgramaEbja
	@OneToMany(mappedBy="modalidad")
	private List<ProgramaEbja> programaEbjas;

	public Modalidad() {
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
		programaEbja.setModalidad(this);

		return programaEbja;
	}

	public ProgramaEbja removeProgramaEbja(ProgramaEbja programaEbja) {
		getProgramaEbjas().remove(programaEbja);
		programaEbja.setModalidad(null);

		return programaEbja;
	}

}