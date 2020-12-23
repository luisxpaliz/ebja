package ec.gob.educacion.ebja.servicio;

import javax.ejb.Local;

import ec.gob.educacion.ebja.dto.ParametrosAuditoriaDTO;

@Local
public interface ParametrosAuditoriaServicio {
	
	public void actualizarParametrosAuditoria(Long idUs, String identificacionUs, String ipAdress, String hostName);
	public void actualizarParametroAuditoria( ParametrosAuditoriaDTO parametros);
	public ParametrosAuditoriaDTO getParametrosAuditoria();
	
}
