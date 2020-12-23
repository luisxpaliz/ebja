package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.dto.TotalesMatriculaDTO;
import ec.gob.educacion.ebja.modelo.Matricula;
import java.util.List;
import javax.ejb.Local;

@Local
public abstract interface MatriculaFacadeLocal {

	void create(Matricula matricula);
	void edit(Matricula matricula);
	void remove(Matricula matricula);

	Matricula find(Object id);
	List<Matricula> findAll();
	List<Matricula> findRange(int[] range);
	int count();
	
	List<Object[]> obtenerEstudiantesParaMatricula(Integer idProgramaEbja, Integer idZoda);
	TotalesMatriculaDTO procesoMatriculaAutomaticaFuncion(Integer idProgramaEbja, Integer idZona, Integer idUsuarioCreacion, String ipUsuario);
	Integer cantidadEstudiantesParaMatricula(Integer idProgramaEbja, Integer idZona); 
	List<Object[]> obtenerEstudiantesParaMatriculaSU(Integer idProgramaEbja, Integer idZoda);
	List<Object[]> listaMatriculadosPorOfertaYZona(Integer idProgramaEbja, Integer idZoda);
	
	List<Matricula> obtenerEstudiantesAsignarParalelo(Integer idCursoParalelo, Integer idParalelo);
	List<Matricula> obtenerEstudiantesInstitucion(String amie);
	
	Matricula obtenerEstudianteParaTraslado(String numeroIdentificacion, String apellidosNombres);
	Matricula obtenerAsignacion(String numeroIdentificacion, String apellidosNombres);
}
