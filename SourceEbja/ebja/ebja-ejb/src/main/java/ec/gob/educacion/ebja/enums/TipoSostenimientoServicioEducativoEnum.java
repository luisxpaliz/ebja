package ec.gob.educacion.ebja.enums;

import java.util.Arrays;
import java.util.List;


public enum TipoSostenimientoServicioEducativoEnum {
	SOSTENIMIENTO_PCEI(3,"PCEI", "PCEI",Arrays.asList("FISCAL", "FISCOMISIONAL", "MUNICIPAL")),
	SOSTENIMIENTO_EBJA(1,"EBJA", "EBJASEMIPRESENCIAL",Arrays.asList("FISCAL")),
	SOSTENIMIENTO_CPL(2,"CPL","CPL", Arrays.asList("FISCOMISIONAL", "PARTICULAR")),
			;

	private long idServicioEducativo;
	private String servicioEducativo;
	private String nemonicoServicioEducativo;
	private List<String> sostenimientos;
	
	private TipoSostenimientoServicioEducativoEnum(long idServicioEducativo,String servicioEducativo, String nemonicoServicioEducativo,List<String> sostenimientos){
		this.idServicioEducativo = idServicioEducativo;
		this.servicioEducativo=servicioEducativo;
		this.nemonicoServicioEducativo = nemonicoServicioEducativo;
		this.sostenimientos=sostenimientos;
	}

	public String getServicioEducativo() {
		return servicioEducativo;
	}

	public void setServicioEducativo(String servicioEducativo) {
		this.servicioEducativo = servicioEducativo;
	}

	public List<String> getSostenimientos() {
		return sostenimientos;
	}

	public void setSostenimientos(List<String> sostenimientos) {
		this.sostenimientos = sostenimientos;
	}

	public String getNemonicoServicioEducativo() {
		return nemonicoServicioEducativo;
	}

	public void setNemonicoServicioEducativo(String nemonicoServicioEducativo) {
		this.nemonicoServicioEducativo = nemonicoServicioEducativo;
	}

	public long getIdServicioEducativo() {
		return idServicioEducativo;
	}

	public void setIdServicioEducativo(long idServicioEducativo) {
		this.idServicioEducativo = idServicioEducativo;
	}
	
}