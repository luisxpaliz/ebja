package ec.gob.educacion.ebja.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.educacion.ebja.facade.local.ModalidadFacadeLocal;
import ec.gob.educacion.ebja.modelo.Modalidad;

@Stateless
public class ModalidadFacade extends AbstractFacade<Modalidad> implements ModalidadFacadeLocal {

	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;
	
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public ModalidadFacade() {
		super(Modalidad.class);
	}
	
	public ModalidadFacade(Class<Modalidad> entityClass) {
		super(entityClass);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Modalidad> findByCodigo(String codigoModulo) {
		
		return (List<Modalidad>)em.createNamedQuery("Modalidad.findByCodigo").setParameter("nem",codigoModulo ).getResultList();
	}

	@Override
	public List<Object[]> findByNombre(String nombreModalidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Modalidad> buscarTodasModalidades() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Modalidad> buscarTodasModalidadesActivas() {
		return (List<Modalidad>)em.createNamedQuery("Modalidad.findAllActive").getResultList(); 
	}

	

}
