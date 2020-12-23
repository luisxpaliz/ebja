package ec.gob.educacion.ebja.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.gob.educacion.ebja.facade.local.MensajeFacadeLocal;
import ec.gob.educacion.ebja.modelo.Mensaje;

@Stateless
public class MensajeFacade extends AbstractFacade<Mensaje> implements MensajeFacadeLocal {

	private List<Object[]> listaObjectResultado;
	private String sql;
	
	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;
	
	public MensajeFacade() {
		super(Mensaje.class);
		listaObjectResultado = new ArrayList<Object[]>();
	}
	
	public MensajeFacade(Class<Mensaje> entityClass) {
		super(entityClass);
		
	}

	public List<Object[]> buscarMensajesNombre(String mensaje) {
		
		if (mensaje.isEmpty()) {
			listaObjectResultado = em.createQuery("SELECT m, c.nombre FROM Mensaje m,CatalogoEbja c where m.estado = c.nemonico").getResultList();
		} else {
			listaObjectResultado = em.createQuery("SELECT m, c.nombre FROM Mensaje m,CatalogoEbja c  where  m.estado = c.nemonico and m.cabecera like concat('%',:nom,'%')")
					.setParameter("nom", mensaje).getResultList();
		}	
		
		return listaObjectResultado;
	}
	
    public List<Object[]> buscarMensajesNemonico(String mensaje) {
		
		if (mensaje.isEmpty()) {
			listaObjectResultado = em.createQuery("SELECT m, c.nombre FROM Mensaje m,CatalogoEbja c where m.estado = c.nemonico").getResultList();
		} else {
			listaObjectResultado = em.createQuery("SELECT m, c.nombre FROM Mensaje m,CatalogoEbja c  where  m.estado = c.nemonico and m.nemonico like concat('%',:nem,'%')")
					.setParameter("nem", mensaje).getResultList();
		}	
		
		return listaObjectResultado;
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public Mensaje obtenerTituloPagina(String nemonico) {
		try {
			sql = "SELECT m FROM Mensaje m, Formulario f "
					+ " where m.formulario.id = f.id "
					+ "   and f.nemonico = :nemonico ";
				Query queryAux = em.createQuery(sql)
								   .setParameter("nemonico", nemonico);
				return (Mensaje) queryAux.getSingleResult();
		} catch (Exception e) {
			e.getStackTrace();
			return null;
		}
	}
}
