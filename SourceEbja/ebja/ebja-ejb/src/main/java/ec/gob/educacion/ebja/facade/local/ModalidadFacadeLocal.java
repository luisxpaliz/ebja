package ec.gob.educacion.ebja.facade.local;

import java.util.List;
import javax.ejb.Local;
import ec.gob.educacion.ebja.modelo.Modalidad;


@Local
public interface ModalidadFacadeLocal {

	void create(Modalidad modalidad);

    void edit(Modalidad modalidad);

    void remove(Modalidad modalidad);

    Modalidad find(Object id);
    
    List<Modalidad> findByCodigo (String codigoModalidad);
    

    
    List<Object[]> findByNombre (String nombreModalidad);

    List<Modalidad> findAll();
    
    List<Modalidad> buscarTodasModalidades();
    
    List<Modalidad> buscarTodasModalidadesActivas();
	
}
