package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.modelo.Inscripcion;
import java.util.List;
import javax.ejb.Local;

@Local
public abstract interface InscripcionFacadeLocal {

	void create(Inscripcion inscripcion);
	void edit(Inscripcion inscripcion);
	void remove(Inscripcion inscripcion);

	Inscripcion find(Object id);
	List<Inscripcion> findAll();
	List<Inscripcion> findRange(int[] range);
	int count();
	
	Integer secuencialInscripcion(); 
	boolean buscarInscripcionPorOferta(String nemonico);
	
	Object[] obtenerInscripcionPorId(Integer id);

}
