package ec.gob.educacion.ebja.modelo.asignaciones;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "NACIONALIDAD")
@NamedQuery(name = "Nacionalidad.findAll", query = "SELECT n FROM Nacionalidad n")
public class Nacionalidad implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "COD_NACIONALIDAD")
	private BigDecimal codNacionalidad;

	@Column(name = "DES_NACIONALIDAD")
	private String desNacionalidad;

	public Nacionalidad() {
	}

	public BigDecimal getCodNacionalidad() {
		return codNacionalidad;
	}

	public void setCodNacionalidad(BigDecimal codNacionalidad) {
		this.codNacionalidad = codNacionalidad;
	}

	public String getDesNacionalidad() {
		return desNacionalidad;
	}

	public void setDesNacionalidad(String desNacionalidad) {
		this.desNacionalidad = desNacionalidad;
	}
}
