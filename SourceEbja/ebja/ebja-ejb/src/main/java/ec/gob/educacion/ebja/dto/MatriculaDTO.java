package ec.gob.educacion.ebja.dto;

import java.io.Serializable;
import java.util.Date;

public class MatriculaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idInscripcion;
	private Integer idRegistroEstudiante;
	private Integer idGgradoAprobado;
	private Integer idProgramaEbja;
	private String codigoPostal;
	private Integer idCircuito;
	private Integer idParroquia;
	private String direccion;
	private String numeroIdentificacion;
	private String apellidosNombres;
	private Date fechaNacimiento;
	private String descripcionParalelo;
	private Integer indice;
	private Integer idMatricula;

	public MatriculaDTO() {
	}

	public Integer getIdInscripcion() {
		return idInscripcion;
	}

	public void setIdInscripcion(Integer idInscripcion) {
		this.idInscripcion = idInscripcion;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getApellidosNombres() {
		return apellidosNombres;
	}

	public void setApellidosNombres(String apellidosNombres) {
		this.apellidosNombres = apellidosNombres;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getIdRegistroEstudiante() {
		return idRegistroEstudiante;
	}

	public void setIdRegistroEstudiante(Integer idRegistroEstudiante) {
		this.idRegistroEstudiante = idRegistroEstudiante;
	}

	public Integer getIdGgradoAprobado() {
		return idGgradoAprobado;
	}

	public void setIdGgradoAprobado(Integer idGgradoAprobado) {
		this.idGgradoAprobado = idGgradoAprobado;
	}

	public Integer getIdProgramaEbja() {
		return idProgramaEbja;
	}

	public void setIdProgramaEbja(Integer idProgramaEbja) {
		this.idProgramaEbja = idProgramaEbja;
	}

	public Integer getIdCircuito() {
		return idCircuito;
	}

	public void setIdCircuito(Integer idCircuito) {
		this.idCircuito = idCircuito;
	}

	public Integer getIdParroquia() {
		return idParroquia;
	}

	public void setIdParroquia(Integer idParroquia) {
		this.idParroquia = idParroquia;
	}

	public String getDescripcionParalelo() {
		return descripcionParalelo;
	}

	public void setDescripcionParalelo(String descripcionParalelo) {
		this.descripcionParalelo = descripcionParalelo;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public Integer getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(Integer idMatricula) {
		this.idMatricula = idMatricula;
	}

}