package ec.gob.educacion.ebja.facade.local;
import java.util.List;
import javax.ejb.Local;
import ec.gob.educacion.ebja.modelo.TipoPrograma;


@Local
public interface TipoProgramaFacadeLocal {

	void create(TipoPrograma tipoPrograma);
    void edit(TipoPrograma tipoPrograma);
    void remove(TipoPrograma tipoPrograma);

    TipoPrograma find(Object id);
    List<TipoPrograma> findAll();
    
    TipoPrograma ObtenerPrograma(String valorVariable);
	
}
