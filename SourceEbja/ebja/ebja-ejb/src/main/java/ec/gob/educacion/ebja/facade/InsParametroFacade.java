package ec.gob.educacion.ebja.facade;

import ec.gob.educacion.ebja.facade.local.InsParametroFacadeLocal;
import ec.gob.educacion.ebja.modelo.asignaciones.InsParametro;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

@Stateless
public class InsParametroFacade extends AbstractFacade<InsParametro> implements InsParametroFacadeLocal {

	@PersistenceContext(unitName = "asignacionesPU")
	private EntityManager em;
	
	@EJB
	private InsParametroFacadeLocal insParametroFacadeLocal;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public InsParametroFacade() {
		super(InsParametro.class);
	}

	public InsParametro buscarPorCodigo(String txtNemonico) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<InsParametro> criteria = cb.createQuery(InsParametro.class);
		Root<InsParametro> insParametro = criteria.from(InsParametro.class);
		criteria.select(insParametro).where(cb.equal(insParametro.get("txtNemonico"), txtNemonico))
				.orderBy(new Order[] { cb.asc(insParametro.get("txtNemonico")) });
		return (InsParametro) em.createQuery(criteria).getSingleResult();
	}

	public InsParametro[] obtenerParametrosMail() {
		InsParametro[] lista = new InsParametro[6];
		lista[0] = insParametroFacadeLocal.buscarPorCodigo("US_CO");
		lista[1] = insParametroFacadeLocal.buscarPorCodigo("C_U_C");
		lista[2] = insParametroFacadeLocal.buscarPorCodigo("SE_CO");
		lista[3] = insParametroFacadeLocal.buscarPorCodigo("PU_CO");
		lista[4] = insParametroFacadeLocal.buscarPorCodigo("US_RE");
		lista[5] = insParametroFacadeLocal.buscarPorCodigo("FE_IC");
		return lista;
	}
}
