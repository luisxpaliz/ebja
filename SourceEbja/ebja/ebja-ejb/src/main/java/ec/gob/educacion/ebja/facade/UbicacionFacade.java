package ec.gob.educacion.ebja.facade;

import ec.gob.educacion.ebja.facade.local.UbicacionFacadeLocal;
import ec.gob.educacion.ebja.modelo.Ubicacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;

@Stateless
public class UbicacionFacade extends AbstractFacade<Ubicacion> implements UbicacionFacadeLocal {

	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

    public UbicacionFacade() {
        super(Ubicacion.class);
    }
	
	@Override
	public Ubicacion buscarPorIdInscripcion(Integer idInscripcion) {
		Ubicacion ubicacion = new Ubicacion();
		try {
			String sql = "SELECT u FROM Ubicacion u "
				       + " where u.estado='1' "
				       + "   and u.inscripcion.id=:idInscripcion ";
			ubicacion = (Ubicacion) em.createQuery(sql).setParameter("idInscripcion", idInscripcion).getSingleResult();
			Hibernate.initialize(ubicacion.getParroquia().getIdCanton().getIdProvincia());
			Hibernate.initialize(ubicacion.getCircuito().getIdDistrito().getIdZona());
		} catch (Exception e) {
			e.getStackTrace();
			ubicacion = null;
		}
		
		return ubicacion;
	}

}
