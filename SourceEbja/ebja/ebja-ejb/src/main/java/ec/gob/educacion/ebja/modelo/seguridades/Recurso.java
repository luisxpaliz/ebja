package ec.gob.educacion.ebja.modelo.seguridades;

import java.util.List;

public class Recurso {

	private Long codigo;
	private Long padre;
	private Long aplicacion;
	private String nombre;
	private String descripcion;
	private String url;
	private Integer nivel;
	private String estado;
	private List<Recurso> recursosHijos;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Long getPadre() {
		return padre;
	}
	public void setPadre(Long padre) {
		this.padre = padre;
	}
	public Long getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(Long aplicacion) {
		this.aplicacion = aplicacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public List<Recurso> getRecursosHijos() {
		return recursosHijos;
	}
	public void setRecursosHijos(List<Recurso> recursosHijos) {
		this.recursosHijos = recursosHijos;
	}
	
}
