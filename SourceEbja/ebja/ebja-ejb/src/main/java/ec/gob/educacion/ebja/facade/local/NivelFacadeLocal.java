package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.modelo.zeus.Nivel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author washington.sangacha
 */
@Local
public interface NivelFacadeLocal {

    void create(Nivel nivel);

    void edit(Nivel nivel);

    void remove(Nivel nivel);

    Nivel find(Object id);

    List<Nivel> findAll();

    List<Nivel> findRange(int[] range);

    int count();
    
    Nivel findByNivel(Nivel nivel);
}
