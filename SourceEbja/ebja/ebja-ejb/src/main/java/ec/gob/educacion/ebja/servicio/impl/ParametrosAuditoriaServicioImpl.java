package ec.gob.educacion.ebja.servicio.impl;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import ec.gob.educacion.ebja.dto.ParametrosAuditoriaDTO;
import ec.gob.educacion.ebja.servicio.ParametrosAuditoriaServicio;


@Stateless
public class ParametrosAuditoriaServicioImpl implements ParametrosAuditoriaServicio {
	
	private ParametrosAuditoriaDTO parametrosAuditoria;
	public static ParametrosAuditoriaDTO parametrosAuditoria2;
	
	
	public ParametrosAuditoriaServicioImpl () {
		
	}
	
	@PostConstruct
	public void init() {
		parametrosAuditoria = new ParametrosAuditoriaDTO();
		parametrosAuditoria2 =  new ParametrosAuditoriaDTO();
	}
	
	
	@Override
	public void actualizarParametrosAuditoria(Long idUs, String identificacionUs, String ipAdress, String hostName) {
		parametrosAuditoria.setCodigoUs(idUs);
		parametrosAuditoria.setIdentificacionUs(identificacionUs);
		parametrosAuditoria.setIpAdressCliente(ipAdress);
		parametrosAuditoria.setHostName(hostName);
	}

	@Override
	public void actualizarParametroAuditoria(ParametrosAuditoriaDTO parametros) {
		parametrosAuditoria = parametros;
		parametrosAuditoria2 = parametros;
		
	}

	public ParametrosAuditoriaDTO getParametrosAuditoria() {
		return parametrosAuditoria;
	}

	public void setParametrosAuditoria(ParametrosAuditoriaDTO parametrosAuditoria) {
		
		this.parametrosAuditoria = parametrosAuditoria;
	}
	
	
	
	
}
