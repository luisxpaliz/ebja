package ec.gob.educacion.ebja.dto;

import java.util.List;

public class SubMenuDTO {

	private String valor;
	private List<ItemMenuDTO> items;
	private String url;
	private String icono;
	
	public SubMenuDTO() {};
	
	public SubMenuDTO(String valor, List<ItemMenuDTO> items) {
		this.valor = valor;
		this.items = items;
	}
	
	public SubMenuDTO(String valor, String url, String icono) {
		this.valor = valor;
		this.url = url;
		this.icono = icono;
	}
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

	public List<ItemMenuDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemMenuDTO> items) {
		this.items = items;
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
}
