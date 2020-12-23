package ec.gob.educacion.ebja.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.educacion.ebja.facade.local.MallaFacadeLocal;
import ec.gob.educacion.ebja.modelo.Malla;

@Stateless
public class MallaFacade extends AbstractFacade<Malla> implements MallaFacadeLocal {

	
	private String sql;
	
	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;
	
	private List<Object[]> listaObjectResultado;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public MallaFacade(Class<Malla> entityClass) {
		super(entityClass);
	}
	
	public MallaFacade() {
		super(Malla.class);
		listaObjectResultado = new ArrayList<Object[]>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findByDescripcion(String descripcionMalla) {
		
	    sql = "SELECT m,c.nombre FROM Malla m,CatalogoEbja c where m.descripcion like concat('%',:descrip,'%') and m.estado = c.nemonico";
		return validarBusquedaDescripcion(sql,"descrip",descripcionMalla);
	}	

	@Override
	public List<Malla> buscarTodasMallasActivas() {
		return null;
	}

	@Override
	public List<Object[]> findByCodigo(String codigoMalla) {
		 sql = "SELECT m,c.nombre FROM Malla m,CatalogoEbja c where m.nemonico =:nemo and m.estado = c.nemonico";
		 return validarBusquedaCodigo(sql,"nemo",codigoMalla);
	}
	
	@SuppressWarnings("unchecked")
	private List<Object[]> validarBusquedaDescripcion(String NamedQuery, String variable, String valorVariable) {

		if (valorVariable.isEmpty()) {
			sql = " ";
			sql = "SELECT m, c.nombre FROM Malla m, CatalogoEbja c where m.estado = c.nemonico ";
			listaObjectResultado = em.createQuery(sql).getResultList();
		} else {
			listaObjectResultado = em.createQuery(NamedQuery).setParameter(variable, valorVariable)
					.getResultList();
		}
		return listaObjectResultado;
	}
	
	@SuppressWarnings("unchecked")
	private List<Object[]> validarBusquedaCodigo(String NamedQuery, String variable, String valorVariable) {

		if (valorVariable.isEmpty()) {
			sql = " ";
			sql = "SELECT m, c.nombre FROM Malla m, CatalogoEbja c where m.estado = c.nemonico ";
			listaObjectResultado = em.createQuery(sql).getResultList();
		} else {
			listaObjectResultado = em.createQuery(NamedQuery).setParameter(variable, valorVariable)
					.getResultList();
		}
		return listaObjectResultado;
	}
	

}
