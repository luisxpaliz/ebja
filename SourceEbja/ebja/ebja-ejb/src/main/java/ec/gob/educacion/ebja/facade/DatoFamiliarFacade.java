package ec.gob.educacion.ebja.facade;

import ec.gob.educacion.ebja.facade.local.DatoFamiliarFacadeLocal;
import ec.gob.educacion.ebja.modelo.DatoFamiliar;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

@Stateless
public class DatoFamiliarFacade extends AbstractFacade<DatoFamiliar> implements DatoFamiliarFacadeLocal {

	private static final Logger LOGGER = Logger.getLogger(UsuarioFacade.class.getName());
	
	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

    public DatoFamiliarFacade() {
        super(DatoFamiliar.class);
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DatoFamiliar> listaDatoFamiliar(Integer idRegistroEstudiante) {
		List<DatoFamiliar> listaDatoFamiliar = new ArrayList();
		try {
			String script = "Select df From DatoFamiliar df "
					      + " Where df.estado = '1' "
					      + "   and df.registroEstudiante.id = :idRegistroEstudiante ";
			StringBuilder sentencia = new StringBuilder().append(script);
			Query query = em.createQuery(sentencia.toString());
			query.setParameter("idRegistroEstudiante", idRegistroEstudiante);

			listaDatoFamiliar = query.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			listaDatoFamiliar = null;
		}
		return listaDatoFamiliar;
	}

	public DatoFamiliar buscarDatoFamiliar(Integer idRegistroEstudiante, Integer idCatalogoDatoFamiliar) {
		DatoFamiliar datoFamiliar = new DatoFamiliar();
		try {
			String script = "Select df From DatoFamiliar df "
					      + " Where df.estado = '1' "
					      + "   and df.registroEstudiante.id = :idRegistroEstudiante "
		                  + "   and df.catalogoDatoFamiliar.id = :idCatalogoDatoFamiliar ";
			StringBuilder sentencia = new StringBuilder().append(script);
			Query query = em.createQuery(sentencia.toString());
			query.setParameter("idRegistroEstudiante", idRegistroEstudiante);
			query.setParameter("idCatalogoDatoFamiliar", idCatalogoDatoFamiliar);

			datoFamiliar = (DatoFamiliar) query.getSingleResult();
		} catch (NoResultException e) {
			LOGGER.info("No se encontraron datos familiares del usuario ");
			//e.printStackTrace();
			datoFamiliar = null;
		}
		
		return datoFamiliar;
	}

	public boolean eliminarDatoFamiliar(Integer idRegistroEstudiante) {
		boolean procesoEliminar;
		try {
			String script = "delete from DatoFamiliar df "
					      + " Where df.registroEstudiante.id = :idRegistroEstudiante ";
			StringBuilder sentencia = new StringBuilder().append(script);
			Query query = em.createQuery(sentencia.toString());
			query.setParameter("idRegistroEstudiante", idRegistroEstudiante);

			query.executeUpdate();
			procesoEliminar = true;
		} catch (NoResultException e) {
			e.printStackTrace();
			procesoEliminar = false;
		}
		
		return procesoEliminar;
	}

}
