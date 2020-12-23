package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.modelo.CursoParalelo;
import ec.gob.educacion.ebja.modelo.zeus.Paralelo;

import java.util.List;
import javax.ejb.Local;

@Local
public abstract interface CursoParaleloFacadeLocal {

	void create(CursoParalelo cursoParalelo);
	void edit(CursoParalelo cursoParalelo);
	void remove(CursoParalelo cursoParalelo);

	CursoParalelo find(Object id);
	List<CursoParalelo> findAll();
	List<CursoParalelo> findRange(int[] range);
	int count();
	
	List<CursoParalelo> findByIdProgramaInstitucion(Integer idProgramaInstitucion);
	Long validarExisteCursoParalelo(Integer idProgramaInstitucion, Integer idParalelo);
	Integer cuentaTotalAforo(Integer idProgramaInstitucion);
	Integer cuentaTotalBanca(Integer idProgramaInstitucion);
	Integer cuentaTotalMatriculado(Integer idProgramaInstitucion);
	
	List<CursoParalelo> obtenerCursoParaleloPorInstitucion(String amie, Integer idProgramaInstitucion);
	List<CursoParalelo> obtenerCursoParaleloPorinstitucion(Integer idParalelo, Integer idProgramaInstitucion);
	
}
