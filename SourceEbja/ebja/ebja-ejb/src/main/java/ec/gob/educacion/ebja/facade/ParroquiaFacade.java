package ec.gob.educacion.ebja.facade;

import ec.gob.educacion.ebja.facade.local.ParroquiaFacadeLocal;
import ec.gob.educacion.ebja.modelo.zeus.Parroquia;
import ec.gob.educacion.ebja.recursos.Constantes;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ParroquiaFacade extends AbstractFacade<Parroquia> implements ParroquiaFacadeLocal {

    @PersistenceContext(unitName = "zeusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParroquiaFacade() {
        super(Parroquia.class);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Parroquia> listaParroquiaPorCanton(String codigoCanton)
    {
      try
      {
        StringBuilder sentencia = new StringBuilder();
        sentencia.append(" select distinct p from Parroquia p ");
        sentencia.append(" where p.idCanton.codigoCanton = :codigoCanton ");
        sentencia.append("   and p.estado = :estado ");
        sentencia.append(" order by p.descripcion asc ");

        Query q = em.createQuery(sentencia.toString())
        			.setParameter("codigoCanton", codigoCanton)
        			.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO);
        return q.getResultList();
      } catch (Exception e) {
        e.printStackTrace(); }
      	return new ArrayList();
    }
    
	public Parroquia buscarPorCodigoParroquia(String codigoParroquia)
    {
      try
      {
        StringBuilder sentencia = new StringBuilder();
        sentencia.append(" select p from Parroquia p ");
        sentencia.append("  where p.codigoParroquia = :codigoParroquia ");
        //sentencia.append("    and p.estado = :estado ");

        Query q = em.createQuery(sentencia.toString())
        			.setParameter("codigoParroquia", codigoParroquia);
        //			.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO);
        return (Parroquia) q.getSingleResult();
      } catch (Exception e) {
        e.printStackTrace(); }
      	return new Parroquia();
    }

}
