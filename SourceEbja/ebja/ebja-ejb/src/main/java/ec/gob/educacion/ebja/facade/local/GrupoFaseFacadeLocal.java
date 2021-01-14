package ec.gob.educacion.ebja.facade.local;

import java.util.List;
import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.GrupoFasePrograma;
import ec.gob.educacion.ebja.modelo.ProgramaEducativo;

@Local
public interface GrupoFaseFacadeLocal {
		

	void create(GrupoFasePrograma grupoFasePrograma);

    void edit(GrupoFasePrograma grupoFasePrograma);

    void remove(GrupoFasePrograma grupoFasePrograma);
    
	Object[] findByNemonico(String codigoGrupoFasePrograma);

    GrupoFasePrograma find(Object id);
    
    List<Object[]> findByCodigo (String codigoGrupoFasePrograma);
    
    List<Object[]> findByNombre (String nombreGrupoFasePrograma);
    
    List<Object[]> findByProyecto(String nombreGrupoFasePrograma);
    
    List<GrupoFasePrograma> findByProgramaEbja(String nemonicoProgramaEbja);

    List<GrupoFasePrograma> findAll();
    
    List<GrupoFasePrograma> buscarTodosGrupoFasePrograma();
    
    List<GrupoFasePrograma> buscarGrupoFaseProgramaActivos();
    
    List<GrupoFasePrograma> buscarGrupoFaseProgActInternos();
    
    List<GrupoFasePrograma> buscarGrupoFaseProgActInternosXProyecto(String programaEducativo);
    
    Integer buscarFaseExterna(long grupoFaseSeleccionado);
    
    List<GrupoFasePrograma> buscarTodosGrupoFaseProgramaActivos(Long proyectoSeleccionado);
    
    List<GrupoFasePrograma> buscarTodosGrupoFaseProgramaActivosExternos(Long proyectoSeleccionado);
    
    GrupoFasePrograma findByCodigoSoloGrupoFasePrograma(String codigoGrupoFasePrograma);
}
