package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.modelo.DocenteCurso;
import ec.gob.educacion.ebja.modelo.zeus.Institucion;

import java.util.List;
import javax.ejb.Local;

@Local
public abstract interface DocenteCursoFacadeLocal {

	void create(DocenteCurso docenteCurso);
	void edit(DocenteCurso docenteCurso);
	void remove(DocenteCurso docenteCurso);

	DocenteCurso find(Object id);
	List<DocenteCurso> findAll();
	List<DocenteCurso> findRange(int[] range);
	int count();
	
	List<DocenteCurso> docenteCursoFindByIdProgramaInstitucion(Integer idProgramaInstitucion);
	Long validarExisteDocenteCurso(Integer idProgramaInstitucion, Integer idDocente);
	List<Institucion> buscarInstitucionDocente(Integer idDocente);
}
