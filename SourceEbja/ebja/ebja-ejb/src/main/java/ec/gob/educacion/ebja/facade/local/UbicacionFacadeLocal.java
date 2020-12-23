package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.modelo.Ubicacion;
import java.util.List;
import javax.ejb.Local;

@Local
public abstract interface UbicacionFacadeLocal {

	void create(Ubicacion ubicacion);
	void edit(Ubicacion ubicacion);
	void remove(Ubicacion ubicacion);

	Ubicacion find(Object id);
	List<Ubicacion> findAll();
	List<Ubicacion> findRange(int[] range);
	int count();

	Ubicacion buscarPorIdInscripcion(Integer idInscripcion);
	
}
