package ec.gob.educacion.ebja.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.educacion.ebja.facade.local.ManejoCatalogoFacadeLocal;
import ec.gob.educacion.ebja.modelo.TipoCatalogoEbja;
import ec.gob.educacion.ebja.modelo.CatalogoEbja;

@Stateless
public class ManejoCatalogoFacade extends AbstractFacade<CatalogoEbja> implements ManejoCatalogoFacadeLocal {

	private List<Object[]> listaObjectResultado;

	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;
	private String sql = "";

	public ManejoCatalogoFacade() {
		super(CatalogoEbja.class);
		listaObjectResultado = new ArrayList<Object[]>();
	}

	public ManejoCatalogoFacade(Class<CatalogoEbja> entityClass) {
		super(entityClass);

	}
 
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public List<Object[]> findByCodigo(String codigoCatalogo) {
		sql = " ";
		sql = "SELECT c,ce.nombre FROM CatalogoEbja c, CatalogoEbja ce WHERE c.tipoCatalogoEbja.editable ='1' and c.estado = ce.nemonico and c.nemonico like concat('%',:nem,'%')";
		return validarBusquedaSimpleOTotal(sql, "nem", codigoCatalogo);
	}

	@Override
	public List<Object[]> findByNombre(String nombreCatalogo) {
		sql = " ";
		sql = "SELECT c,ce.nombre FROM CatalogoEbja c, CatalogoEbja ce WHERE c.tipoCatalogoEbja.editable ='1' and c.estado = ce.nemonico and c.nombre like concat('%',:nombre,'%')";
		return validarBusquedaSimpleOTotal(sql, "nombre", nombreCatalogo);
	}

	@SuppressWarnings("unchecked")
	private List<Object[]> validarBusquedaSimpleOTotal(String NamedQuery, String variable, String valorVariable) {

		if (valorVariable.isEmpty()) {
			sql = " ";
			sql = "SELECT c, ce.nombre FROM CatalogoEbja c, CatalogoEbja ce WHERE c.tipoCatalogoEbja.editable ='1' and c.estado = ce.nemonico";
			listaObjectResultado = em.createQuery(sql).getResultList();
		} else {
			listaObjectResultado = em.createQuery(NamedQuery).setParameter(variable, valorVariable)
					.getResultList();
		}
		return listaObjectResultado;
	}

	public TipoCatalogoEbja buscarTipoCatalogoEbja(TipoCatalogoEbja tipoCatalogoEbja) {
		return (TipoCatalogoEbja) em.createNamedQuery("TipoCatalogoEbja.findByCodigo").setParameter("idAcuerdo", String.valueOf(tipoCatalogoEbja.getId())).getSingleResult();
	}
}
