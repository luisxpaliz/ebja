package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.modelo.Pais;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PaisFacadeLocal {

    void create(Pais pais);
    void edit(Pais pais);
    void remove(Pais pais);

    Pais find(Object id);
    List<Pais> findAll();
    List<Pais> findRange(int[] range);
    int count();

    Pais buscarPorCodigoPais(String codigoPais);
    
}
