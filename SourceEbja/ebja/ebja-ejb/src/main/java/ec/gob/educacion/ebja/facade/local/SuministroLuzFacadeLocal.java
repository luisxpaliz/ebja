package ec.gob.educacion.ebja.facade.local;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.asignaciones.SuministroLuz;

/**
 * @author jbrito-20180904
 */
@Local
public interface SuministroLuzFacadeLocal {

	/**
	 * Metodo que retorna el suministroLuz de acuerdo al CUE
	 * @param cue
	 * @return
	 */
	SuministroLuz obtenerSuministroXCUE(String cue);

}
