package ec.gob.educacion.ebja.facade;

import ec.gob.educacion.ebja.facade.local.EstudianteFacadeLocal;
import ec.gob.educacion.ebja.modelo.Estudiante;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EstudianteFacade extends AbstractFacade<Estudiante> implements EstudianteFacadeLocal {

	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

    public EstudianteFacade() {
        super(Estudiante.class);
    }
    
    public Integer secuencialEstudiante() {
		Integer secuencialEstudiante = null;
		try {
			secuencialEstudiante = (Integer) em.createQuery("select max(e.id) + 1 from Estudiante e ").getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (secuencialEstudiante == null) {
			secuencialEstudiante = 1;
		}
		
		return secuencialEstudiante;
    }
}
