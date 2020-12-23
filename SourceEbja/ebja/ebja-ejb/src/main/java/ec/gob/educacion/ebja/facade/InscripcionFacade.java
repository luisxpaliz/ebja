package ec.gob.educacion.ebja.facade;

import ec.gob.educacion.ebja.facade.local.InscripcionFacadeLocal;
import ec.gob.educacion.ebja.modelo.Inscripcion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class InscripcionFacade extends AbstractFacade<Inscripcion> implements InscripcionFacadeLocal {

	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

    public InscripcionFacade() {
        super(Inscripcion.class);
    }
    
    public Integer secuencialInscripcion() {
		Integer secuencialInscripcion = null;
		try {
			secuencialInscripcion = (Integer) em.createQuery("select max(i.id) + 1 from Inscripcion i ").getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (secuencialInscripcion == null) {
			secuencialInscripcion = 1;
		}
		
		return secuencialInscripcion;
    }
    
    public boolean buscarInscripcionPorOferta(String nemonico) {
    	return em.createQuery("SELECT i FROM Inscripcion i where i.programaEbja.nemonico = :nem ").setParameter("nem", nemonico).getResultList().isEmpty();
    	
    }

	public Object[] obtenerInscripcionPorId(Integer id) {
    	Object[] aspiranteInscripcion = new Object[3];
    	
		try {
			String sql = 
					  " select ins, ubi, res "
					+ "   from Inscripcion ins, "
					+ "        Ubicacion ubi, "
					+ "        RegistroEstudiante res "
					+ "  where ins.estado = '1' "
					+ "    and ubi.estado = '1' "
					+ "    and res.estado = '1' "
					+ "    and res.estadoAsignacion = '0' "
					+ "    and ins.id = ubi.inscripcion.id "
					+ "    and ins.id = res.inscripcion.id "
					+ "    and ins.id = :id ";
			Query query = em.createQuery(sql).setParameter("id", id);
			aspiranteInscripcion = (Object[]) query.getSingleResult();
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return aspiranteInscripcion;
    }

}
