package ec.gob.educacion.ebja.modelo.seguridades;

public class Sede {

	private Long codigo;
	private String nombre;
	private String descripcion;
	private String nemonico;
	private String estado;

	public Sede() {
	}

	public Sede(Long codigo, String nombre, String estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.estado = estado;
	}

	public Sede(Long codigo, Sede sede, String nombre, String descripcion,
			String nemonico, String estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nemonico = nemonico;
		this.estado = estado;
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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

	public String getNemonico() {
		return this.nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
