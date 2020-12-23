package ec.gob.educacion.ebja.dto;

public class RegistroEstudianteDTO {

	private String apellidosNombres;
	private String numeroIdentificacion;

	private String apellidosNombresUsuarioTmp;
	private String numeroIdentificacionUsuarioTmp;
	
	private String nomProgramaEbja;
	
	private String nomPais;
	private String nomProvincia;
	private String nomCanton;
	private String codigoPostal;
	private String correoElectronico;
	private String telefono;
	private String callePrincipal;
	private String codRegistroEstudiante;
	private String fechaInicioClases;
	private String motivoNoMatricula;
	private Integer idInscripcion;
	private Integer indice;
	private String codigoSenpladesDistrito;

	public RegistroEstudianteDTO() {
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getNumeroIdentificacionUsuarioTmp() {
		return numeroIdentificacionUsuarioTmp;
	}

	public void setNumeroIdentificacionUsuarioTmp(String numeroIdentificacionUsuarioTmp) {
		this.numeroIdentificacionUsuarioTmp = numeroIdentificacionUsuarioTmp;
	}

	public String getNomProgramaEbja() {
		return nomProgramaEbja;
	}

	public void setNomProgramaEbja(String nomProgramaEbja) {
		this.nomProgramaEbja = nomProgramaEbja;
	}

	public String getNomPais() {
		return nomPais;
	}

	public void setNomPais(String nomPais) {
		this.nomPais = nomPais;
	}

	public String getNomProvincia() {
		return nomProvincia;
	}

	public void setNomProvincia(String nomProvincia) {
		this.nomProvincia = nomProvincia;
	}

	public String getNomCanton() {
		return nomCanton;
	}

	public void setNomCanton(String nomCanton) {
		this.nomCanton = nomCanton;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getCallePrincipal() {
		return callePrincipal;
	}

	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}

	public String getApellidosNombresUsuarioTmp() {
		return apellidosNombresUsuarioTmp;
	}

	public void setApellidosNombresUsuarioTmp(String apellidosNombresUsuarioTmp) {
		this.apellidosNombresUsuarioTmp = apellidosNombresUsuarioTmp;
	}

	public String getApellidosNombres() {
		return apellidosNombres;
	}

	public void setApellidosNombres(String apellidosNombres) {
		this.apellidosNombres = apellidosNombres;
	}

	public String getCodRegistroEstudiante() {
		return codRegistroEstudiante;
	}

	public void setCodRegistroEstudiante(String codRegistroEstudiante) {
		this.codRegistroEstudiante = codRegistroEstudiante;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFechaInicioClases() {
		return fechaInicioClases;
	}

	public void setFechaInicioClases(String fechaInicioClases) {
		this.fechaInicioClases = fechaInicioClases;
	}

	public String getMotivoNoMatricula() {
		return motivoNoMatricula;
	}

	public void setMotivoNoMatricula(String motivoNoMatricula) {
		this.motivoNoMatricula = motivoNoMatricula;
	}

	public Integer getIdInscripcion() {
		return idInscripcion;
	}

	public void setIdInscripcion(Integer idInscripcion) {
		this.idInscripcion = idInscripcion;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public String getCodigoSenpladesDistrito() {
		return codigoSenpladesDistrito;
	}

	public void setCodigoSenpladesDistrito(String codigoSenpladesDistrito) {
		this.codigoSenpladesDistrito = codigoSenpladesDistrito;
	}


}
