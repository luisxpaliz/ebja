package ec.gob.educacion.ebja.facade;

import ec.gob.educacion.ebja.facade.local.CantonFacadeLocal;
import ec.gob.educacion.ebja.modelo.zeus.Canton;
import ec.gob.educacion.ebja.recursos.Constantes;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CantonFacade extends AbstractFacade<Canton> implements CantonFacadeLocal {

    @PersistenceContext(unitName = "zeusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CantonFacade() {
        super(Canton.class);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Canton> listaCantonPorProvincia(String codigoProvincia)
    {
      try
      {
        StringBuilder sentencia = new StringBuilder();
        sentencia.append(" select distinct c from Canton c ");
        sentencia.append(" where c.idProvincia.codigoProvincia = :codigoProvincia ");
        sentencia.append(" order by c.descripcion asc ");

        Query q = em.createQuery(sentencia.toString())
        			.setParameter("codigoProvincia", codigoProvincia);
        return q.getResultList();
      } catch (Exception e) {
        e.printStackTrace(); }
      	return new ArrayList();
    }
    
	public Canton buscarPorCodigoCanton(String codigoCanton)
    {
      try
      {
        StringBuilder sentencia = new StringBuilder();
        sentencia.append(" select c from Canton c ");
        sentencia.append("  where c.codigoCanton = :codigoCanton ");
        sentencia.append("    and c.estado = :estado ");

        Query q = em.createQuery(sentencia.toString())
        			.setParameter("codigoCanton", codigoCanton)
        			.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO);
        return (Canton) q.getSingleResult();
      } catch (Exception e) {
        e.printStackTrace(); }
      	return new Canton();
    }

}
