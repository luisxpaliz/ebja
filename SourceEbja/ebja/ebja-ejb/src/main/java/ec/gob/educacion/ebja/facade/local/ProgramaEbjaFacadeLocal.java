package ec.gob.educacion.ebja.facade.local;

import java.util.List;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.GrupoFasePrograma;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;

@Local
public interface ProgramaEbjaFacadeLocal {

	void create(ProgramaEbja reglaNegocio);
    void edit(ProgramaEbja reglaNegocio);
    void remove(ProgramaEbja reglaNegocio);

    ProgramaEbja find(Object id);
    List<ProgramaEbja> findAll();
    List<ProgramaEbja> findAllActive();
    List<ProgramaEbja> findAllActiveExt();
    ProgramaEbja obtenerProgramaEbja(String valorVariable);
    Integer obtenerOfertaExterna(long idGrupoFase);
    List<ProgramaEbja> obtenerProgramaEbjaGrupoFase(long idGrupoFase, long idTipoGrupoFase);
    List<ProgramaEbja> obtenerProgramaEbjaGrupoFaseExtraordinaria(long idGrupoFase);
    List<ProgramaEbja> obtenerProgramaEbjaGrupoFaseExtraordinariaNemonico(String nemonicoGrupoFase);
    List<GrupoFasePrograma> obtenerProgramaEbjaGrupoFase(String nemonicoGrupoFase);
    List<ProgramaEbja> obtenerProgramaEbjaGrupoFaseOrdinaria(long idGrupoFase, long idTipoGrupoFase);
    List<ProgramaEbja> obtenerProgramaEbjaNinguno();
	Integer buscarSiguientePrograma(String programaEbja);
	ProgramaEbja buscarPrograma(Integer idProgramaEbja);
	
    List<ProgramaEbja> obtenerProgramaEbjaPorInstitucion(String amie);
    ProgramaEbja obtenerProgramaPorNemonico(String nemonico);
    
    GrupoFasePrograma buscarFaseProgramaPorProgramaEbja(String programaEbja);
	
}
