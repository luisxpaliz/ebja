package ec.gob.educacion.ebja.facade;

import ec.gob.educacion.ebja.facade.local.PaisFacadeLocal;
import ec.gob.educacion.ebja.modelo.Pais;
import ec.gob.educacion.ebja.recursos.Constantes;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PaisFacade extends AbstractFacade<Pais> implements PaisFacadeLocal {

    @PersistenceContext(unitName = "zeusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaisFacade() {
        super(Pais.class);
    }
    
	public Pais buscarPorCodigoPais(String codigoPais)
    {
      try
      {
        StringBuilder sentencia = new StringBuilder();
        sentencia.append(" select p from Pais p ");
        sentencia.append(" where p.codigoPais = :codigoPais ");
        sentencia.append("   and p.estado = :estado ");

        Query q = em.createQuery(sentencia.toString())
        			.setParameter("codigoPais", codigoPais)
        			.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO);
        return (Pais) q.getSingleResult();
      } catch (Exception e) {
        e.printStackTrace(); }
      	return new Pais();
    }

}
