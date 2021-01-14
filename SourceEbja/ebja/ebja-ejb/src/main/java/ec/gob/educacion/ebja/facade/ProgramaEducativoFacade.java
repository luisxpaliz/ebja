package ec.gob.educacion.ebja.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.educacion.ebja.facade.local.ProgramaEducativoFacadeLocal;
import ec.gob.educacion.ebja.modelo.ProgramaEducativo;

@Stateless
public class ProgramaEducativoFacade extends AbstractFacade<ProgramaEducativo> implements ProgramaEducativoFacadeLocal {

	private List<Object[]> listaObjectResultado;
	private ProgramaEducativo programaEducativo;
	private String sql = "";
	
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
	public List<Object[]> findByCodigo(String codigoProgramaEducativo) {
		sql = "";
		sql = "select a, c.nombre from ProgramaEducativo a, CatalogoEbja c where a.estado = c.nemonico and a.nemonico like concat('%',:nem,'%')";
		return validarBusquedaSimpleOTotal(sql, "nem", codigoProgramaEducativo);
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProgramaEducativo> buscarTodosProgramaEducativoCPLPCEI() {
		return  em.createNamedQuery("ProgramaEducativo.findAllPCEICPL").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProgramaEducativo> buscarProgramaCPL() {
		return em.createQuery("SELECT p FROM ProgramaEducativo p WHERE p.nemonico = 'CPL'").getResultList();
	}

	@Override
	public ProgramaEducativo findByCodigoSoloProgramaEducativo(String codigoProgramaEducativo) {
		programaEducativo = (ProgramaEducativo) em.createQuery("SELECT p FROM ProgramaEducativo p WHERE p.nemonico =:nem").setParameter("nem", codigoProgramaEducativo ).getSingleResult();
		return programaEducativo;
	}
	
	@SuppressWarnings("unchecked")
	private List<Object[]> validarBusquedaSimpleOTotal(String NamedQuery, String variable, String valorVariable) {

		if (valorVariable.isEmpty()) {
			sql = "";
			sql = "select a, c.nombre from ProgramaEducativo  a, CatalogoEbja c where a.estado = c.nemonico ";
			listaObjectResultado = em.createQuery(sql).getResultList();
		} else {
			listaObjectResultado = em.createQuery(NamedQuery).setParameter(variable, valorVariable).getResultList();
		}
		return listaObjectResultado;
	}

	

}
