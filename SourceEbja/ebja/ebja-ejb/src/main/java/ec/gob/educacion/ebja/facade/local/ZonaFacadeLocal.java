package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.modelo.zeus.Zona;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author washington.sangacha
 */
@Local
public interface ZonaFacadeLocal {

    void create(Zona zona);
    void edit(Zona zona);
    void remove(Zona zona);

    Zona find(Object id);
    List<Zona> findAll();
    List<Zona> findRange(int[] range);
    List<Zona> findByEstado(String estado);
    int count();

    Zona buscarPorCodigoSenplades(String codigoSenplades);
    
}
