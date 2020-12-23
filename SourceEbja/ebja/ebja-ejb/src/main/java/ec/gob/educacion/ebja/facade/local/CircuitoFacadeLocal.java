package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.modelo.zeus.Circuito;

import java.util.List;
import javax.ejb.Local;

@Local
public interface CircuitoFacadeLocal {

    void create(Circuito circuito);
    void edit(Circuito circuito);
    void remove(Circuito circuito);

    Circuito find(Object id);
    List<Circuito> findAll();
    List<Circuito> findRange(int[] range);
    List<Circuito> findByDistrito(Integer idDistrito, String estado);
    int count();

    List<Circuito> listaCircuitoPorCanton(String codigoCanton);
    Circuito buscarPorCodigoCircuito(String codigoCircuito);
    List<Circuito> buscarPorProvCantParr(Short idProvincia, Short idCanton, Short idParroquia);   
    
}
