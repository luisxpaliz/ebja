package ec.gob.educacion.ebja.facade;

import ec.gob.educacion.ebja.facade.local.CircuitoFacadeLocal;
import ec.gob.educacion.ebja.modelo.zeus.Circuito;
import ec.gob.educacion.ebja.recursos.Constantes;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;

@Stateless
public class CircuitoFacade extends AbstractFacade<Circuito> implements CircuitoFacadeLocal {

    @PersistenceContext(unitName = "zeusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CircuitoFacade() {
        super(Circuito.class);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Circuito> listaCircuitoPorCanton(String codigoCanton)
    {
      try
      {
        StringBuilder sentencia = new StringBuilder();
        sentencia.append(" select distinct c from Circuito c ");
        sentencia.append("  where c.idCanton.codigoCanton = :codigoCanton ");
        sentencia.append("    and c.estado = :estado ");
        sentencia.append("  order by c.descripcion asc ");

        Query q = em.createQuery(sentencia.toString())
        			.setParameter("codigoCanton", codigoCanton)
        			.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO);
        return q.getResultList();
      } catch (Exception e) {
        e.printStackTrace(); }
      	return new ArrayList();
    }
    
	public Circuito buscarPorCodigoCircuito(String codigoCircuito)
    {
      try
      {
        StringBuilder sentencia = new StringBuilder();
        sentencia.append(" select c from Circuito c ");
        sentencia.append("  where c.codigoSenpladesCircuito = :codigoCircuito ");
        sentencia.append("    and c.estado = :estado ");

        Query q = em.createQuery(sentencia.toString())
        			.setParameter("codigoCircuito", codigoCircuito)
        			.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO);
        return (Circuito) q.getSingleResult();
      } catch (Exception e) {
        e.printStackTrace(); }
      	return new Circuito();
    }

	@SuppressWarnings({ "unchecked" })
	public List<Circuito> buscarPorProvCantParr(Short idProvincia, Short idCanton, Short idParroquia) {
		List<Circuito> listaCircuito = new ArrayList<Circuito>();
		try {
			StringBuilder sentencia = new StringBuilder();
			sentencia.append(" select distinct c ");
			sentencia.append("   from CircuitoParroquia cp join cp.idCircuito c ");
			sentencia.append("                             join c.idDistrito d ");
			sentencia.append("  where cp.idParroquia.id = :idParroquia ");
			sentencia.append("    and cp.idParroquia.idCanton.id = :idCanton ");
			sentencia.append("    and cp.idParroquia.idCanton.idProvincia.id = :idProvincia ");
			sentencia.append("    and c.estado = :estado ");
			sentencia.append("  order by c.descripcion asc");

			Query q = em.createQuery(sentencia.toString())
						.setParameter("idParroquia", idParroquia)
						.setParameter("idCanton", idCanton)
						.setParameter("idProvincia", idProvincia)
						.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO);
			listaCircuito = q.getResultList();
			Hibernate.initialize(listaCircuito);
		} catch (Exception e) {
			e.printStackTrace();
			listaCircuito = null;
		}
		
		return listaCircuito;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Circuito> findByDistrito(Integer idDistrito, String estado) {
		List<Circuito> listaCircuito;
		
		listaCircuito = em.createNamedQuery("Circuito.findByCircuito")
							.setParameter("idDistrito", idDistrito.shortValue())
							.setParameter("estado", estado).getResultList();

		return listaCircuito;
	}

}
