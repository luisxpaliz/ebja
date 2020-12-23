package ec.gob.educacion.ebja.dto;

public class ItemMenuDTO implements Comparable<ItemMenuDTO> {

	private String valor;
	private String url;
	private String icono;
	private int orden;
	
	public ItemMenuDTO() {};
	
	public ItemMenuDTO(String valor, String url, String icono,int orden) {
		this.valor = valor;
		this.url = url;
		this.icono = icono;
		this.orden =orden;
	}
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}

	@Override
	public int compareTo(ItemMenuDTO o) {
		
		return 0;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	
	
}
