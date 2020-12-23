package ec.gob.educacion.ebja.facade;

import ec.gob.educacion.ebja.facade.local.NivelFacadeLocal;
import ec.gob.educacion.ebja.modelo.zeus.Nivel;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class NivelFacade extends AbstractFacade<Nivel> implements NivelFacadeLocal {

    @PersistenceContext(unitName = "zeusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NivelFacade() {
        super(Nivel.class);
    }
    
    public Nivel findByNivel(Nivel nivel) {
    	return (Nivel) em.createQuery("select n from Nivel n where n = :nivel ").setParameter("nivel", nivel).getSingleResult();
    }
    
}
