package ec.gob.educacion.ebja.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.educacion.ebja.facade.local.ProgramaEducativoFacadeLocal;
import ec.gob.educacion.ebja.modelo.ProgramaEducativo;

@Stateless
public class ProgramaEducativoFacade extends AbstractFacade<ProgramaEducativo> implements ProgramaEducativoFacadeLocal {

	private ProgramaEducativo programaEducativo;
	
	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public ProgramaEducativoFacade() {
		super(ProgramaEducativo.class);
	}
	
	public ProgramaEducativoFacade(Class<ProgramaEducativo> entityClass) {
		super(entityClass);
	}

	@Override
	public ProgramaEducativo find(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> findByCodigo(String codigoProgramaEducativo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> findByNombre(String nombreProgramaEducativo) {
		
		return null;
	}

	@Override
	public List<ProgramaEducativo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProgramaEducativo> buscarTodosProgramaEducativo() {
		return null;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProgramaEducativo> buscarTodosProgramaEducativoActivos() {
		return  em.createNamedQuery("ProgramaEducativo.findAllActive").getResultList();
	}

	@Override
	public ProgramaEducativo findByCodigoSoloProgramaEducativo(String codigoProgramaEducativo) {
		programaEducativo = (ProgramaEducativo) em.createQuery("SELECT p FROM ProgramaEducativo p WHERE p.nemonico =:nem").setParameter("nem", codigoProgramaEducativo ).getSingleResult();
		return programaEducativo;
	}

	

}
