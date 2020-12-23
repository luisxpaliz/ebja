package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.modelo.zeus.Provincia;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ProvinciaFacadeLocal {

    void create(Provincia provincia);
    void edit(Provincia provincia);
    void remove(Provincia provincia);

    Provincia find(Object id);
    List<Provincia> findAll();
    List<Provincia> findRange(int[] range);
    int count();

    List<Provincia> listaProvinciaPorZona(String codigoSempladesZona);
    Provincia buscarPorCodigoProvincia(String codigoProvincia);
    
}
