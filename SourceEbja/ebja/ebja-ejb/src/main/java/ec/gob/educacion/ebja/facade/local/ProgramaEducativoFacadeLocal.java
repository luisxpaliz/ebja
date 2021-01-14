package ec.gob.educacion.ebja.facade.local;

import java.util.List;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.ProgramaEducativo;

@Local
public interface ProgramaEducativoFacadeLocal {

	 void create(ProgramaEducativo programaEducativo);

	    void edit(ProgramaEducativo programaEducativo);

	    void remove(ProgramaEducativo programaEducativo);

	    ProgramaEducativo find(Object id);
	    
	    List<Object[]> findByCodigo (String codigoProgramaEducativo);
	    
	    List<Object[]> findByNombre (String nombreProgramaEducativo);

	    List<ProgramaEducativo> findAll();
	    
	    List<ProgramaEducativo> buscarTodosProgramaEducativo();
	    
	    List<ProgramaEducativo> buscarTodosProgramaEducativoActivos();
	    
	    List<ProgramaEducativo> buscarProgramaCPL();
	    
	    ProgramaEducativo findByCodigoSoloProgramaEducativo(String codigoProgramaEducativo);
	    
	    List<ProgramaEducativo> buscarTodosProgramaEducativoCPLPCEI();
	
}
