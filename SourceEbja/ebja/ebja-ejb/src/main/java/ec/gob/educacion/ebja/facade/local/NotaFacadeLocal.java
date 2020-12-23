package ec.gob.educacion.ebja.facade.local;

import java.util.List;
import javax.ejb.Local;
import ec.gob.educacion.ebja.modelo.Notas;


@Local
public interface NotaFacadeLocal {

	void create(Notas nota);

    void edit(Notas nota);

    void remove(Notas nota);
    
    boolean findCodigo(); 

    Notas find(Object id);
    
    List<Object[]> findByCodigo (String codigoNota);
    List<Notas> buscarNotaActivos();
    List<Object[]> findByNombre (String nombreNota);
    List<Notas> findAll();
    List<Object[]> findAllPivot();
    List<Notas> findAllActive();
}
