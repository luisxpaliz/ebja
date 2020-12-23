package ec.gob.educacion.ebja.facade.local;

import java.util.List;
import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.GrupoFasePrograma;

@Local
public interface GrupoFaseFacadeLocal {
		

	void create(GrupoFasePrograma grupoFasePrograma);

    void edit(GrupoFasePrograma grupoFasePrograma);

    void remove(GrupoFasePrograma grupoFasePrograma);

    GrupoFasePrograma find(Object id);
    
    List<Object[]> findByCodigo (String codigoGrupoFasePrograma);
    
    List<Object[]> findByNombre (String nombreGrupoFasePrograma);

    List<GrupoFasePrograma> findAll();
    
    List<GrupoFasePrograma> buscarTodosGrupoFasePrograma();
    
    Integer buscarFaseExterna(long grupoFaseSeleccionado);
    
    List<GrupoFasePrograma> buscarTodosGrupoFaseProgramaActivos(Long proyectoSeleccionado);
    List<GrupoFasePrograma> buscarTodosGrupoFaseProgramaActivosExternos(Long proyectoSeleccionado);
    
    GrupoFasePrograma findByCodigoSoloGrupoFasePrograma(String codigoGrupoFasePrograma);
}
