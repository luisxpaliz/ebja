package ec.gob.educacion.ebja.modelo.zeus;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Sostenimiento
 *
 */

@Entity
@Table(name = "sostenimiento")
public class Sostenimiento implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
	private Integer id;
	private String descripcion;
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	private String estado;
	@Column(name="fecha_migracion")
	private Date fechaMigracion;
	private static final long serialVersionUID = 1L;

	public Sostenimiento() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}   
	
	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaMigracion() {
		return fechaMigracion;
	}
	public void setFechaMigracion(Date fechaMigracion) {
		this.fechaMigracion = fechaMigracion;
	}   
	
   
}
