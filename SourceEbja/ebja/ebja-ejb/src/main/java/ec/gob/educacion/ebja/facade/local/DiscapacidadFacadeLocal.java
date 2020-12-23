package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.modelo.Discapacidad;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DiscapacidadFacadeLocal {

    void create(Discapacidad discapacidad);
    void edit(Discapacidad discapacidad);
    void remove(Discapacidad discapacidad);

    Discapacidad find(Object id);
    List<Discapacidad> findAll();
    List<Discapacidad> findRange(int[] range);
    int count();

    Discapacidad buscarDiscapacidad(Integer idRegistroEstudiante, Integer idCatalogoDiscapacidad);
    boolean eliminarDiscapacidad(Integer idRegistroEstudiante);
    
}
