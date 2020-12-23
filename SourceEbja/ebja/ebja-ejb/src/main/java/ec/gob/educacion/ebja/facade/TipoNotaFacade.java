package ec.gob.educacion.ebja.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.educacion.ebja.facade.local.TipoNotaFacadeLocal;
import ec.gob.educacion.ebja.modelo.TipoNota;

@Stateless
public class TipoNotaFacade extends AbstractFacade<TipoNota> implements TipoNotaFacadeLocal{

	private List<Object[]> listaObjectResultado;
	private String sql = "";
	
	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public TipoNotaFacade(Class<TipoNota> entityClass) {
		super(entityClass);
	}
	
	public TipoNotaFacade() {
		super(TipoNota.class);
		listaObjectResultado = new ArrayList<Object[]>();
	}

	@Override
	public List<Object[]> findByCodigo(String codigoTipoNota) {
		sql = "";
		sql = "select t, c.nombre from TipoNota t, CatalogoEbja c where t.estado = c.nemonico and t.nemonico like concat('%',:nem,'%')";
		return validarBusquedaSimpleOTotal(sql, "nem", codigoTipoNota);
	}

	@Override
	public List<Object[]> findByNombre(String nombreTipoNota) {
		sql = "";
		sql = "select t, c.nombre from TipoNota t, CatalogoEbja c where t.estado = c.nemonico and t.nombre like concat('%',:nem,'%')";
		return validarBusquedaSimpleOTotal(sql, "nem", nombreTipoNota);
	}

	@Override
	public List<TipoNota> buscarTodosTipoNota() {
		return null;
	}

	@Override
	public List<TipoNota> buscarTodosTipoNotaActivos() {
		return (List<TipoNota>) em.createNamedQuery("TipoNota.findAllActive").getResultList();
	}

	@Override
	public TipoNota findByCodigoSoloTipoNota(String codigoTipoNota) {
		sql = "";
		sql = "select t from TipoNota t where t.nemonico = :nem)";
		return (TipoNota) em.createQuery(sql).setParameter("nem", codigoTipoNota).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	private List<Object[]> validarBusquedaSimpleOTotal(String NamedQuery, String variable, String valorVariable) {

		if (valorVariable.isEmpty()) {
			sql = "";
			sql = "select t, c.nombre from TipoNota t, CatalogoEbja c where t.estado = c.nemonico ";
			listaObjectResultado = em.createQuery(sql).getResultList();
		} else {
			listaObjectResultado = em.createQuery(NamedQuery).setParameter(variable, valorVariable).getResultList();
		}
		return listaObjectResultado;
	}

}
