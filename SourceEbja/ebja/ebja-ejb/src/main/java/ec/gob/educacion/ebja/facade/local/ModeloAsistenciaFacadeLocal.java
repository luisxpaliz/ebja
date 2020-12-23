package ec.gob.educacion.ebja.facade.local;

import java.util.List;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.ModeloAsistencia;

@Local
public interface ModeloAsistenciaFacadeLocal {

	void create(ModeloAsistencia acuerdo);

    void edit(ModeloAsistencia acuerdo);

    void remove(ModeloAsistencia acuerdo);

    ModeloAsistencia find(Object id);
    
    List<Object[]> buscarTodosModelosAsistencia(String nemonico);
	
}
