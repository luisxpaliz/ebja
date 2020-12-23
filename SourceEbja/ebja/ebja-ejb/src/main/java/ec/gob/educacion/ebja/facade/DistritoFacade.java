package ec.gob.educacion.ebja.facade;

import ec.gob.educacion.ebja.facade.local.DistritoFacadeLocal;
import ec.gob.educacion.ebja.modelo.zeus.Distrito;
import ec.gob.educacion.ebja.recursos.Constantes;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Hibernate;

@Stateless
public class DistritoFacade extends AbstractFacade<Distrito> implements DistritoFacadeLocal {
	
	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public DistritoFacade() {
		super(Distrito.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Distrito> listaDistritoPorCanton(String codigoCanton) {
		try {
			StringBuilder sentencia = new StringBuilder();
			sentencia.append(" select distinct d from Distrito d ");
			sentencia.append("  where d.idCanton.codigoCanton = :codigoCanton ");
			sentencia.append("    and d.estado = :estado ");
			sentencia.append("  order by d.descripcion asc ");

			Query q = em.createQuery(sentencia.toString()).setParameter("codigoCanton", codigoCanton)
					.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO);
			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList();
	}

	public Distrito buscarPorCodigoDistrito(String codigoDistrito) {
		try {
			StringBuilder sentencia = new StringBuilder();
			sentencia.append(" select d from Distrito d ");
			sentencia.append("  where d.codigoSenpladesDistrito = :codigoDistrito ");
			sentencia.append("    and d.estado = :estado ");

			Query q = em.createQuery(sentencia.toString()).setParameter("codigoDistrito", codigoDistrito)
					.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO);
			return (Distrito) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Distrito();
	}

	@SuppressWarnings({ "unchecked" })
	public Distrito buscarPorProvCantParrCirc(Short idProvincia, Short idCanton, Short idParroquia, Integer idCircuito) {
		List<Distrito> listaDistrito = new ArrayList<Distrito>();
		Distrito distrito = new Distrito();
		try {
			StringBuilder sentencia = new StringBuilder();
			sentencia.append(" select distinct d ");
			sentencia.append("   from CircuitoParroquia cp join cp.idCircuito c ");
			sentencia.append("                             join c.idDistrito d ");
			sentencia.append("  where cp.idParroquia.id = :idParroquia ");
			sentencia.append("    and cp.idParroquia.idCanton.id = :idCanton ");
			sentencia.append("    and cp.idParroquia.idCanton.idProvincia.id = :idProvincia ");
			sentencia.append("    and c.id = :idCircuito ");
			sentencia.append("    and d.estado = :estado ");
			sentencia.append("  order by d.descripcion asc ");

			Query q = em.createQuery(sentencia.toString())
						.setParameter("idParroquia", idParroquia)
						.setParameter("idCanton", idCanton)
						.setParameter("idProvincia", idProvincia)
						.setParameter("idCircuito", idCircuito)
						.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO);
			listaDistrito = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			listaDistrito = null;
		}
		
		if (!listaDistrito.isEmpty()) {
			distrito = listaDistrito.get(0);
			Hibernate.initialize(distrito.getIdZona());
		}
		
		
		return distrito;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Distrito> findByZona(Integer idZona, String estado) {
		List<Distrito> listaDistrito;
		
		listaDistrito = em.createNamedQuery("Distrito.findByZona")
							.setParameter("idZona", idZona.shortValue())
							.setParameter("estado", estado).getResultList();

		return listaDistrito;
	}
}
