package ec.gob.educacion.ebja.modelo.seguridades;

public class Aplicacion {

	private Long codigo;
	private String prefijo;
	private String nombre;
	private String descripcion;
	private String tipo;
	private String url;
	private String estado;
	
	public Aplicacion() {
	}

	public Aplicacion(Long codigo, String prefijo, String nombre, String tipo,
			String estado) {
		this.codigo = codigo;
		this.prefijo = prefijo;
		this.nombre = nombre;
		this.tipo = tipo;
		this.estado = estado;
	}

	public Aplicacion(Long codigo, String prefijo, String nombre,
			String descripcion, String tipo, String url, String estado) {
		this.codigo = codigo;
		this.prefijo = prefijo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.url = url;
		this.estado = estado;
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getPrefijo() {
		return this.prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
