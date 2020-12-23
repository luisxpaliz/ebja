package ec.gob.educacion.ebja.facade.local;
import java.util.List;

import javax.ejb.Local;
import ec.gob.educacion.ebja.modelo.PlantillaEstudiante;

@Local
public interface PlantillaEstudianteFacadeLocal {

	void create(PlantillaEstudiante plantillaEstudiante);

    void edit(PlantillaEstudiante plantillaEstudiante);

    void remove(PlantillaEstudiante plantillaEstudiante);

    PlantillaEstudiante find(Object id);
    
    List<Object[]> findByCodigo (String codigoPlantillaEstudiante);
    
    List<Object[]> findByNombre (String nombrePlantillaEstudiante);

    List<PlantillaEstudiante> findAll();
    
    List<PlantillaEstudiante> buscarTodosPlantillaEstudiante();
    
    List<PlantillaEstudiante> buscarTodosPlantillaEstudianteActivos();
    
    PlantillaEstudiante findByCodigoSoloPlantillaEstudiante(String codigoPlantillaEstudiante);	
	
}
