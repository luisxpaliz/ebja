package ec.gob.educacion.ebja.dto;

import java.io.Serializable;

public class TotalesMatriculaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer numInscrito;
	private Integer numEstudiante;
	private Integer numMatricula;
	private Integer numNoMatricula;

	public TotalesMatriculaDTO() {
	}

	public Integer getNumInscrito() {
		return numInscrito;
	}

	public void setNumInscrito(Integer numInscrito) {
		this.numInscrito = numInscrito;
	}

	public Integer getNumEstudiante() {
		return numEstudiante;
	}

	public void setNumEstudiante(Integer numEstudiante) {
		this.numEstudiante = numEstudiante;
	}

	public Integer getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(Integer numMatricula) {
		this.numMatricula = numMatricula;
	}

	public Integer getNumNoMatricula() {
		return numNoMatricula;
	}

	public void setNumNoMatricula(Integer numNoMatricula) {
		this.numNoMatricula = numNoMatricula;
	}
}