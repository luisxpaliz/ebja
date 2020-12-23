package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.modelo.zeus.Parroquia;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ParroquiaFacadeLocal {

    void create(Parroquia parroquia);
    void edit(Parroquia parroquia);
    void remove(Parroquia parroquia);

    Parroquia find(Object id);
    List<Parroquia> findAll();
    List<Parroquia> findRange(int[] range);
    int count();

    List<Parroquia> listaParroquiaPorCanton(String codigoParroquia);
    Parroquia buscarPorCodigoParroquia(String codigoParroquia);
    
}
