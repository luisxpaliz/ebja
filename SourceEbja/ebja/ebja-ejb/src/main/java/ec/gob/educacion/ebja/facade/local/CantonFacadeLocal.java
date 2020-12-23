package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.modelo.zeus.Canton;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CantonFacadeLocal {

    void create(Canton canton);
    void edit(Canton canton);
    void remove(Canton canton);

    Canton find(Object id);
    List<Canton> findAll();
    List<Canton> findRange(int[] range);
    int count();

    List<Canton> listaCantonPorProvincia(String codigoProvincia);
    Canton buscarPorCodigoCanton(String codigoCanton);
    
}
