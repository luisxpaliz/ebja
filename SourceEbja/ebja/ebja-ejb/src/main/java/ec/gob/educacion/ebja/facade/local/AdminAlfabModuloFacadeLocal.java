package ec.gob.educacion.ebja.facade.local;

import java.util.List;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.Acuerdo;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;



@Local
public interface AdminAlfabModuloFacadeLocal {
	
	    void create(ProgramaEbja acuerdo);

	    void edit(ProgramaEbja acuerdo);

	    void remove(ProgramaEbja acuerdo);
	    
	    boolean findCodigo(); 

	    ProgramaEbja find(Object id);
	    
	    List<Object[]> findByCodigo (String codigoAcuerdo);
	    
	    List<ProgramaEbja> buscarProgramaEbjaActivos();
	    
	    List<Object[]> findByNombre (String nombreAcuerdo);

	    List<ProgramaEbja> findAll();
	    
	    List<ProgramaEbja> findAllActive();
	    
}
