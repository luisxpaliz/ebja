package ec.gob.educacion.ebja.facade.local;

import java.util.List;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.zeus.InstitucEstablec;

@Local
public interface InstitucEstablecFacadeLocal {

	InstitucEstablec find(Object id);
	List<InstitucEstablec> findAll();
	List<InstitucEstablec> findRange(int[] range);
	int count();
	
	List<InstitucEstablec> institucionFindByAmie (String codigoAmie);    
    List<InstitucEstablec> institucionFindByCircuito (Integer idCircuito);
}
