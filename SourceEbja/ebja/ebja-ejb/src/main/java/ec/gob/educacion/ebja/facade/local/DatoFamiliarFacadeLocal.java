package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.modelo.DatoFamiliar;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DatoFamiliarFacadeLocal {

    void create(DatoFamiliar datoFamiliar);
    void edit(DatoFamiliar datoFamiliar);
    void remove(DatoFamiliar datoFamiliar);

    DatoFamiliar find(Object id);
    List<DatoFamiliar> findAll();
    List<DatoFamiliar> findRange(int[] range);
    int count();
    
    List<DatoFamiliar> listaDatoFamiliar(Integer idRegistroEstudiante);
    DatoFamiliar buscarDatoFamiliar(Integer idRegistroEstudiante, Integer idCatalogoDatoFamiliar);
    boolean eliminarDatoFamiliar(Integer idRegistroEstudiante);
}
