package ec.gob.educacion.ebja.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.educacion.ebja.facade.local.MallaDocenteFacadeLocal;
import ec.gob.educacion.ebja.modelo.Asignatura;
import ec.gob.educacion.ebja.modelo.MallaDocente;

@Stateless
public class MallaDocenteFacade extends AbstractFacade<MallaDocente> implements MallaDocenteFacadeLocal {

	private String sql;
	private List<Asignatura> listaResultado;
	
	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;
	
	public MallaDocenteFacade() {
		super(MallaDocente.class);
	}
	
	public MallaDocenteFacade(Class<MallaDocente> entityClass) {
		super(entityClass);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	@Override
	public List<Asignatura> buscarAsignatura(Integer paraleloId, Integer programaInstId) {
		
		sql = "SELECT m.malla.asignatura FROM MallaDocente m where m.docenteCurso.programaInstitucion.id = :programaInstId and m.docenteCurso.cursoParalelo.id = :paraleloId";
		listaResultado = em.createQuery(sql).setParameter("programaInstId", programaInstId).setParameter("paraleloId", paraleloId).getResultList();
		return listaResultado;
	}

}
