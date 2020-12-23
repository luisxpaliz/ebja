package ec.gob.educacion.ebja.dto;


public class DocenteDTO {

	private String nombres;
	private String cedula;
	private Long codigo;
	private String rolDocente;		
	
	public DocenteDTO() {
		//Constructor por defecto
	}
	
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getRolDocente() {
		return rolDocente;
	}
	public void setRolDocente(String rolDocente) {
		this.rolDocente = rolDocente;
	}	
}
