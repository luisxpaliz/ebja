package ec.gob.educacion.ebja.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ec.gob.educacion.ebja.facade.local.CatalogoEbjaFacadeLocal;
import ec.gob.educacion.ebja.modelo.TipoCatalogoEbja;
import ec.gob.educacion.ebja.modelo.CatalogoEbja;
import ec.gob.educacion.ebja.recursos.Constantes;

@Stateless
public class CatalogoEbjaFacade extends AbstractFacade<CatalogoEbja> implements CatalogoEbjaFacadeLocal {

	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CatalogoEbjaFacade() {
		super(CatalogoEbja.class);
	}
	
	@Override
	public List<CatalogoEbja> obtenerTipoAsistencia() {
		StringBuilder sql = new StringBuilder();
		sql.append("select c from CatalogoEbja c where c.nemonico in ('UD','MD')");
		TypedQuery<CatalogoEbja> q = em.createQuery(sql.toString(), CatalogoEbja.class);
		
		return q.getResultList();
	}
	
	@Override
	public CatalogoEbja obtenerTipoAsistenciaNemonico(String nemonico) {
		StringBuilder sql = new StringBuilder();
		sql.append("select c from CatalogoEbja c where c.nemonico =:nem");
		TypedQuery<CatalogoEbja> q = em.createQuery(sql.toString(), CatalogoEbja.class).setParameter("nem", nemonico);
		return q.getSingleResult();
	}

	@Override
	public List<CatalogoEbja> obtenerCatalogosPorTipoCatalogo(String nemonicoTipoCatalogo) {
		StringBuilder sql = new StringBuilder();
		sql.append("select c from CatalogoEbja c ");
		sql.append(" where (c.estado = :estado or c.estado = :estado1) ");
		sql.append("   and  c.tipoCatalogoEbja.nemonico = :nemonicoTipoCatalogo ");
		sql.append("   and (c.tipoCatalogoEbja.estado = :estado or c.tipoCatalogoEbja.estado = :estado1) ");
		sql.append(" order by c.id ");
		
		TypedQuery<CatalogoEbja> q = em.createQuery(sql.toString(), CatalogoEbja.class)
			.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO)
			.setParameter("estado1", Constantes.ESTADO_REGISTRO_ACTIVO1)
			.setParameter("nemonicoTipoCatalogo", nemonicoTipoCatalogo);
		
		return q.getResultList();
	}

	@Override
	public List<TipoCatalogoEbja> listaTipoCatalogoEbja() {
		StringBuilder sql = new StringBuilder();
		sql.append("select tce from TipoCatalogoEbja tce ");
		//sql.append("where (pg.estado = :estado or pg.estado = :estado1) ");
		//sql.append("  and pg.grado.id = :idGrado ");
		
		TypedQuery<TipoCatalogoEbja> q = em.createQuery(sql.toString(), TipoCatalogoEbja.class);
		//	.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO)
		//	.setParameter("estado1", Constantes.ESTADO_REGISTRO_ACTIVO1)
		//	.setParameter("idGrado", idGrado);
		
		return q.getResultList();
	}

	@Override
	public CatalogoEbja obtenerCatalogoPorTipoCatalogo(String nemonicoTipoCatalogo, String nemonicoCatalogo) {
		StringBuilder sql = new StringBuilder();
		sql.append("select c from CatalogoEbja c ");
		sql.append(" where (c.estado = :estado or c.estado = :estado1) ");
		sql.append("   and  c.tipoCatalogoEbja.nemonico = :nemonicoTipoCatalogo ");
		sql.append("   and  c.nemonico = :nemonicoCatalogo ");
		sql.append("   and (c.tipoCatalogoEbja.estado = :estado or c.tipoCatalogoEbja.estado = :estado1) ");
		sql.append(" order by c.id ");
		
		TypedQuery<CatalogoEbja> q = em.createQuery(sql.toString(), CatalogoEbja.class)
			.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO)
			.setParameter("estado1", Constantes.ESTADO_REGISTRO_ACTIVO1)
			.setParameter("nemonicoTipoCatalogo", nemonicoTipoCatalogo)
			.setParameter("nemonicoCatalogo", nemonicoCatalogo);
		
		return q.getSingleResult();
	}

}
