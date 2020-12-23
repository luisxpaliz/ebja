package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.modelo.zeus.Distrito;

import java.util.List;
import javax.ejb.Local;

@Local
public interface DistritoFacadeLocal {

    void create(Distrito distrito);
    void edit(Distrito distrito);
    void remove(Distrito distrito);

    Distrito find(Object id);
    List<Distrito> findAll();
    List<Distrito> findRange(int[] range);
    List<Distrito> findByZona(Integer idZona, String estado);
    int count();

    List<Distrito> listaDistritoPorCanton(String codigoCanton);
    
    Distrito buscarPorCodigoDistrito(String codigoDistrito);
    Distrito buscarPorProvCantParrCirc(Short idProvincia, Short idCanton, Short idParroquia, Integer idCircuito);   
}
