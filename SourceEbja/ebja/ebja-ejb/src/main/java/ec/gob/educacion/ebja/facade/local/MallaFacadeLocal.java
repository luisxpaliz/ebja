package ec.gob.educacion.ebja.facade.local;

import java.util.List;

import javax.ejb.Local;
import ec.gob.educacion.ebja.modelo.Malla;

@Local
public interface MallaFacadeLocal {

	    void create(Malla malla);

	    void edit(Malla malla);

	    void remove(Malla malla);

	    Malla find(Object id);
	    
	    List<Object[]> findByDescripcion (String descripcionMalla);
	    
	    List<Object[]> findByCodigo (String codigoMalla);
	    
	    List<Malla> buscarTodasMallasActivas();
	
}
