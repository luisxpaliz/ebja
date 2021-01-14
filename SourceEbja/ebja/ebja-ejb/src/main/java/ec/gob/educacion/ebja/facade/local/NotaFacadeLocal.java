package ec.gob.educacion.ebja.facade.local;

import java.util.List;
import javax.ejb.Local;
import ec.gob.educacion.ebja.modelo.Nota;


@Local
public interface NotaFacadeLocal {

	void create(Nota nota);

    void edit(Nota nota);

    void remove(Nota nota);
    
    boolean findCodigo(); 

    Nota find(Object id);
    
    List<Object[]> findByCodigo (String codigoNota);
    List<Nota> buscarNotaActivos();
    List<Object[]> findByNombre (String nombreNota);
    List<Nota> findAll();
    List<Object[]> findAllPivot();
    List<Nota> findAllActive();
}
