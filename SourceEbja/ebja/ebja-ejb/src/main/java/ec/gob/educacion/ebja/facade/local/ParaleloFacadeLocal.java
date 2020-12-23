package ec.gob.educacion.ebja.facade.local;

import java.util.List;

import javax.ejb.Local;
import ec.gob.educacion.ebja.modelo.zeus.Paralelo;

@Local
public interface ParaleloFacadeLocal {

    void create(Paralelo paralelo);
    void edit(Paralelo paralelo);
    void remove(Paralelo paralelo);

    Paralelo find(Object id);

    List<Paralelo> findAll();
    List<Paralelo> findRange(int[] range);
    List<Paralelo> findAllActive();
    List<Paralelo> obtenerParaleloPorInstitucion(String amie, Integer idProgramaEbja);

    int count();
    
}
