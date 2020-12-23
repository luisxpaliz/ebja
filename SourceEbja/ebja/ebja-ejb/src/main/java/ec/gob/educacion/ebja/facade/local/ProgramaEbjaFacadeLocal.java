package ec.gob.educacion.ebja.facade.local;

import java.util.List;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.ProgramaEbja;

@Local
public interface ProgramaEbjaFacadeLocal {

	void create(ProgramaEbja reglaNegocio);
    void edit(ProgramaEbja reglaNegocio);
    void remove(ProgramaEbja reglaNegocio);

    ProgramaEbja find(Object id);
    List<ProgramaEbja> findAll();
    List<ProgramaEbja> findAllActive();
    ProgramaEbja obtenerProgramaEbja(String valorVariable);
    List<ProgramaEbja> obtenerProgramaEbjaGrupoFase(long idGrupoFase);
    Integer obtenerOfertaExterna(long idGrupoFase);
	Integer buscarSiguientePrograma(String programaEbja);
	ProgramaEbja buscarPrograma(Integer idProgramaEbja);
	
    List<ProgramaEbja> obtenerProgramaEbjaPorInstitucion(String amie);
    ProgramaEbja obtenerProgramaPorNemonico(String nemonico);
	
}
