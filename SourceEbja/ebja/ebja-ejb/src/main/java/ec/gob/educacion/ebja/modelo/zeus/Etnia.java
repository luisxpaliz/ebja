package ec.gob.educacion.ebja.modelo.zeus;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "etnia")
@NamedQueries({ @NamedQuery(name = "Etnia.findAll", query = "SELECT e FROM Etnia e"),
		@NamedQuery(name = "Etnia.findById", query = "SELECT e FROM Etnia e WHERE e.id = :id"),
		@NamedQuery(name = "Etnia.findByNombre", query = "SELECT e FROM Etnia e WHERE e.nombre = :nombre"),
		@NamedQuery(name = "Etnia.findByNemonico", query = "SELECT e FROM Etnia e WHERE e.nemonico = :nemonico"),
		@NamedQuery(name = "Etnia.findByIdUsuarioCreacion", query = "SELECT e FROM Etnia e WHERE e.idUsuarioCreacion = :idUsuarioCreacion"),
		@NamedQuery(name = "Etnia.findByFechaCreacion", query = "SELECT e FROM Etnia e WHERE e.fechaCreacion = :fechaCreacion"),
		@NamedQuery(name = "Etnia.findByEstado", query = "SELECT e FROM Etnia e WHERE e.estado = :estado"),
		@NamedQuery(name = "Etnia.findByFechaMigracion", query = "SELECT e FROM Etnia e WHERE e.fechaMigracion = :fechaMigracion") })
public class Etnia implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Short id;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "nombre")
	private String nombre;
	@Size(max = 25)
	@Column(name = "nemonico")
	private String nemonico;
	@Column(name = "id_usuario_creacion")
	private Integer idUsuarioCreacion;
	@Basic(optional = false)
	@NotNull
	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	@Basic(optional = false)
	@NotNull
	@Column(name = "estado")
	private String estado;
	@Column(name = "fecha_migracion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaMigracion;
	// @OneToMany(mappedBy = "idEtnia", fetch = FetchType.LAZY)
	// private List<Persona> personaList;

	public Etnia() {
	}

	public Etnia(Short id) {
		this.id = id;
	}

	public Etnia(Short id, String nombre, Date fechaCreacion, String estado) {
		this.id = id;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
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

	public String getNemonico() {
		return nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
	}

	public Integer getIdUsuarioCreacion() {
		return idUsuarioCreacion;
	}

	public void setIdUsuarioCreacion(Integer idUsuarioCreacion) {
		this.idUsuarioCreacion = idUsuarioCreacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaMigracion() {
		return fechaMigracion;
	}

	public void setFechaMigracion(Date fechaMigracion) {
		this.fechaMigracion = fechaMigracion;
	}

	/*
	 * @XmlTransient public List<Persona> getPersonaList() { return personaList; }
	 * 
	 * public void setPersonaList(List<Persona> personaList) { this.personaList =
	 * personaList; }
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Etnia)) {
			return false;
		}
		Etnia other = (Etnia) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ec.gob.educacion.ebja.modelo.zeus.Etnia[ id=" + id + " ]";
	}

}
