package ec.gob.educacion.ebja.facade.local;

import java.util.List;
import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.TipoNota;


@Local
public interface TipoNotaFacadeLocal {

	void create(TipoNota tipoNota);

    void edit(TipoNota tipoNota);

    void remove(TipoNota tipoNota);

    TipoNota find(Object id);
    
    List<Object[]> findByCodigo (String codigoTipoNota);
    
    List<Object[]> findByNombre (String nombreTipoNota);

    List<TipoNota> findAll();
    
    List<TipoNota> buscarTodosTipoNota();
    
    List<TipoNota> buscarTodosTipoNotaActivos();
    
    TipoNota findByCodigoSoloTipoNota(String codigoTipoNota);
	
	
}
