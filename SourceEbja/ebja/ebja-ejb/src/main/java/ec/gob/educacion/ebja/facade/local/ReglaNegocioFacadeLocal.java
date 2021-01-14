package ec.gob.educacion.ebja.facade.local;

import java.util.List;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.ReglaNegocio;

@Local
public interface ReglaNegocioFacadeLocal {
	
	void create(ReglaNegocio reglaNegocio);
    void edit(ReglaNegocio reglaNegocio);
    void remove(ReglaNegocio reglaNegocio);

    ReglaNegocio find(Object id);
    List<ReglaNegocio> findAll();
    List<ReglaNegocio> findAllActive();
    List<Object[]> findByProgramaEbja(String nemonico);
    List<Object[]> findByProgramaEbjaUnique(String nemonico);
    
    boolean buscarDependenciaPorCodigoReglaNegocio(String id);
	  
    ReglaNegocio buscarPorProgramaEbjaFase(Integer idProgramaEbja, String nemonico);
    List<ReglaNegocio> findByProgramaEbjaValido (String nemonicoPrograma);
    
    List<ReglaNegocio> findByProgramaEbjaFaseValidacion (String nemonicoPrograma,String nemonicoFase);
	
}
