package ec.gob.educacion.ebja.facade.local;

import java.util.List;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.Acuerdo;
import ec.gob.educacion.ebja.modelo.GrupoFasePrograma;
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
    List<ProgramaGrado> buscarGradoPorFase(String grupoFase);
    List<Grado> buscarGradoNinguno();
    List<Grado> buscarGradoUnificadosPorProgramaOrdinario(long idGrupoFaseNotas);
    List<ProgramaGrado> buscarProgramaGrado(Grado grado, ProgramaEbja programaEbja);
    List<Grado> buscarGradoUnificadosPorPrograma(String programaEbja); 
    Integer programaEbjaTieneAsignadoPackCurso(String nemonico);
    List<ProgramaGrado> buscarPrograma(Integer programaEbja);
    List<ProgramaEbja> buscarPrimerProgramaPorPeriodo(Long idGrupoFase);
    ProgramaGrado buscarProgramaGrado(Integer grado , String programaEbja);
    ProgramaGrado buscarProgramaGradoInicial(String programaEbja);
    List<ProgramaEbja> buscarProgramaEbjaPorGrado(Long idGrupoFase, Integer idGrado);
    ProgramaEbja buscarProgramaEbjaSiguienteInscripcion(Long idGrupoFase, Integer igProgramaEbjaActual);
	List<Grado> buscarGradoUnificados(String programaEbja);
	Grado buscarGradoUltimo(String programaEbja);
	Grado buscarGradoPrimero(String programaEbja);
	Grado buscarGradoSiguienteOferta(String programaEbja,Integer idGrado);
	Grado buscarGradoYaConLaSiguienteOferta(String programaEbja,Integer idGrado);
	List<Grado> buscarGradoProgramaNinguno();
	List<Grado> buscarProgramaNemonico(String programaEbja);
	List<ProgramaGrado> buscarGradoYProgramaPorFaseOrdinario(long idGrupoFaseNotas, Integer grado);
	List<Grado> buscarGradoUnificadosVisible(String programaEbja);
	List<Grado> buscarProgramaNemonicoVisible(String programaEbja);
	
    

	
}
