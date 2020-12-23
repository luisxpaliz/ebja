package ec.gob.educacion.ebja.facade;

import ec.gob.educacion.ebja.facade.local.AsignacionFacadeLocal;
import ec.gob.educacion.ebja.modelo.Inscripcion;
import ec.gob.educacion.ebja.modelo.RegistroEstudiante;
import ec.gob.educacion.ebja.modelo.Ubicacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Stateless
public class AsignacionFacade implements AsignacionFacadeLocal {
	// Creating resources
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("standalone");
	EntityManager em = emf.createEntityManager();
	
    public boolean procesarAsignacion(Inscripcion inscripcion, RegistroEstudiante registroEstudiante, Ubicacion ubicacion) {
		//EntityTransaction et = em.getTransaction();
		try {
			em.getTransaction().begin();
				em.persist(inscripcion);
				registroEstudiante.setInscripcion(inscripcion);
				em.persist(registroEstudiante);
				ubicacion.setInscripcion(inscripcion);
				em.persist(ubicacion);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		// Closing resources
		em.close();
		emf.close();		
		return false;
    }
}
