package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pais", schema = "ebja")
public class Pais implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "pais_generador", sequenceName = "ebja.pais_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="pais_generador")
	private Short id;
	
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "nombre")
	private String nombre;
	
	@Basic(optional = false)
	@NotNull
	@Column(name = "estado")
	private String estado;
	
	//bi-directional many-to-one association to RegistroEstudiante
	@OneToMany(mappedBy="pais")
	private List<RegistroEstudiante> registroEstudiantes;

	//bi-directional many-to-one association to Ubicacion
	@OneToMany(mappedBy="pais")
	private List<Ubicacion> ubicaciones;
	
	public Pais() {
	}

	public Pais(Short id) {
		this.id = id;
	}

	public Pais(Short id, String nombre, String estado) {
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
	}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public List<RegistroEstudiante> getRegistroEstudiantes() {
		return this.registroEstudiantes;
	}

	public void setRegistroEstudiantes(List<RegistroEstudiante> registroEstudiantes) {
		this.registroEstudiantes = registroEstudiantes;
	}

	public RegistroEstudiante addRegistroEstudiante(RegistroEstudiante registroEstudiante) {
		getRegistroEstudiantes().add(registroEstudiante);
		registroEstudiante.setPais(this);

		return registroEstudiante;
	}

	public RegistroEstudiante removeRegistroEstudiante(RegistroEstudiante registroEstudiante) {
		getRegistroEstudiantes().remove(registroEstudiante);
		registroEstudiante.setPais(null);

		return registroEstudiante;
	}

	public List<Ubicacion> getUbicaciones() {
		return this.ubicaciones;
	}

	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	public Ubicacion addUbicacion(Ubicacion ubicacion) {
		getUbicaciones().add(ubicacion);
		ubicacion.setPais(this);

		return ubicacion;
	}

	public Ubicacion removeUbicacion(Ubicacion ubicacion) {
		getUbicaciones().remove(ubicacion);
		ubicacion.setPais(null);

		return ubicacion;
	}
	
	
//	@Override
//	public int hashCode() {
//		int hash = 0;
//		hash += (id != null ? id.hashCode() : 0);
//		return hash;
//	}
//
//	@Override
//	public boolean equals(Object object) {
//		if (!(object instanceof Pais)) {
//			return false;
//		}
//		Pais other = (Pais) object;
//		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//			return false;
//		}
//		return true;
//	}
//
//	@Override
//	public String toString() {
//		return "ec.gob.educacion.ebja.modelo.Pais[ id=" + id + " ]";
//	}

}
