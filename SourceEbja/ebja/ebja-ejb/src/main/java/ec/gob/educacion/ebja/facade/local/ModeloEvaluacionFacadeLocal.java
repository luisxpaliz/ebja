package ec.gob.educacion.ebja.facade.local;

import java.util.List;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.ModeloEvaluacion;

@Local
public interface ModeloEvaluacionFacadeLocal {

	void create(ModeloEvaluacion modelo);

	void edit(ModeloEvaluacion modelo);

	void remove(ModeloEvaluacion modelo);

	ModeloEvaluacion find(Object id);

	List<Object[]> findByCodigo(String codigoModeloEvaluacion);

	List<Object[]> findByNombre(String nombreModeloEvaluacion);

	List<ModeloEvaluacion> findAll();

	List<ModeloEvaluacion> buscarTodosModelos();

	List<ModeloEvaluacion> buscarTodosModelosActivos();
	
	ModeloEvaluacion findByNemModulo(String codigoModeloEvaluacion);

}
