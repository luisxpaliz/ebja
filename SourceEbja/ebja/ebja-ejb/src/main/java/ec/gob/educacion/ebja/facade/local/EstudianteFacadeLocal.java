package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.modelo.Estudiante;
import java.util.List;
import javax.ejb.Local;

@Local
public abstract interface EstudianteFacadeLocal {

	void create(Estudiante estudiante);
	void edit(Estudiante estudiante);
	void remove(Estudiante estudiante);

	Estudiante find(Object id);
	List<Estudiante> findAll();
	List<Estudiante> findRange(int[] range);
	int count();
	
	Integer secuencialEstudiante();
}
