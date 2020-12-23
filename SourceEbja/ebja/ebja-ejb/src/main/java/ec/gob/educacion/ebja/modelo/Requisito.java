package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the requisito database table.
 * 
 */
@Entity
@NamedQuery(name="Requisito.findAll", query="SELECT r FROM Requisito r")
public class Requisito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REQUISITO_IDREQUISITO_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REQUISITO_IDREQUISITO_GENERATOR")
	@Column(name="id_requisito")
	private Integer idRequisito;

	private String descripcion;

	private Boolean estado;

	public Requisito() {
	}

	public Integer getIdRequisito() {
		return this.idRequisito;
	}

	public void setIdRequisito(Integer idRequisito) {
		this.idRequisito = idRequisito;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

}