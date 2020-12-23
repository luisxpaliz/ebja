package ec.gob.educacion.ebja.facade.local;

import java.util.List;
import javax.ejb.Local;
import ec.gob.educacion.ebja.modelo.PlantillaNota;

@Local
public interface PlantillaNotaFacadeLocal  {

	void create(PlantillaNota plantillaNota);

    void edit(PlantillaNota plantillaNota);

    void remove(PlantillaNota plantillaNota);

    PlantillaNota find(Object id);
    
    List<Object[]> findByCodigo (String codigoPlantillaNota);
    
    List<Object[]> findByNombre (String nombrePlantillaNota);

    List<PlantillaNota> findAll();
    
    List<PlantillaNota> buscarTodosPlantillaNota();
    
    List<PlantillaNota> buscarTodosPlantillaNotaActivos();
    
    PlantillaNota findByCodigoSoloPlantillaNota(String codigoPlantillaNota);
	
}
