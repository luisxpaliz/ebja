package ec.gob.educacion.ebja.facade.local;

import java.util.List;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.Acuerdo;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.modelo.ProgramaGrado;
import ec.gob.educacion.ebja.modelo.zeus.Grado;

@Local
public interface ProgramaGradoFacadeLocal {

	void create(ProgramaGrado programaGrado);

    void edit(ProgramaGrado programaGrado);

    void remove(ProgramaGrado programaGrado);

    ProgramaGrado find(Object id);
    
    boolean buscarDependenciaPorCodigoGrado(String id);
    
    List<Object[]> buscarTodosGrados();
    List<Object[]> buscarTodosGradosPorPrograma(String programa);
    List<ProgramaGrado> buscarProgramaGrado(Grado grado, ProgramaEbja programaEbja);
    List<Grado> buscarGradoUnificadosPorPrograma(String programaEbja); 
    Integer programaEbjaTieneAsignadoPackCurso(String nemonico);
    List<ProgramaGrado> buscarPrograma(String programaEbja);
    ProgramaGrado buscarProgramaGrado(Integer grado , String programaEbja);
    List<ProgramaEbja> buscarProgramaEbjaPorGrado(Long idGrupoFase, Integer idGrado);
    ProgramaEbja buscarProgramaEbjaSiguienteInscripcion(Long idGrupoFase, Integer igProgramaEbjaActual);
	List<Grado> buscarGradoUnificados(String programaEbja);
	Grado buscarGradoUltimo(String programaEbja);
	Grado buscarGradoPrimero(String programaEbja);
	Grado buscarGradoSiguienteOferta(String programaEbja,Integer idGrado);
    

	
}
