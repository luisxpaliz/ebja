package ec.gob.educacion.ebja.dto;

import java.io.Serializable;

public class ParametrosAuditoriaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String ipAdressCliente;
	private String hostName;
	private Long codigoUs;
	private String identificacionUs;		
	
	public ParametrosAuditoriaDTO() {
		//Constructor por defecto
	}
	
	public String getIpAdressCliente() {
		return ipAdressCliente;
	}
	public void setIpAdressCliente(String ipAdressCliente) {
		this.ipAdressCliente = ipAdressCliente;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public Long getCodigoUs() {
		return codigoUs;
	}
	public void setCodigoUs(Long codigoUs) {
		this.codigoUs = codigoUs;
	}
	public String getIdentificacionUs() {
		return identificacionUs;
	}
	public void setIdentificacionUs(String identificacionUs) {
		this.identificacionUs = identificacionUs;
	}	
}
