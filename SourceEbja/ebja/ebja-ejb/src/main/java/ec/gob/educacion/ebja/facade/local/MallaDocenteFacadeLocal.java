package ec.gob.educacion.ebja.facade.local;

import java.util.List;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.Asignatura;
import ec.gob.educacion.ebja.modelo.MallaDocente;

@Local
public interface MallaDocenteFacadeLocal {

	 void create(MallaDocente mallaDocente);

	 void edit(MallaDocente mallaDocente);

	 void remove(MallaDocente mallaDocente);

	 MallaDocente find(Object id);
	    
	 List<Asignatura> buscarAsignatura(Integer programaInstId,Integer paraleloId );
	  
}
