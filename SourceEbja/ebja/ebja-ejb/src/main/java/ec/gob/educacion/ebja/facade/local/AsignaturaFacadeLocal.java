package ec.gob.educacion.ebja.facade.local;

import java.util.List;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.Asignatura;


@Local
public interface AsignaturaFacadeLocal {

    void create(Asignatura asignatura);

    void edit(Asignatura asignatura);

    void remove(Asignatura asignatura);

    Asignatura find(Object id);
    
    Asignatura findByNemonico(String Nemonico);
    
    List<Asignatura> findByArea (String area);
    
    List<Asignatura> findByMateria (String materia);

    List<Asignatura> findAll();
    
    List<Asignatura> buscarTodasAsignaturas();
	
    List<Asignatura> buscarTodasAsignaturasActivas();
    
}
