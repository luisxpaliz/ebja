package ec.gob.educacion.ebja.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.educacion.ebja.facade.local.ModeloEvaluacionFacadeLocal;
import ec.gob.educacion.ebja.modelo.ModeloEvaluacion;

@Stateless
public class ModeloEvaluacionFacade extends AbstractFacade<ModeloEvaluacion> implements ModeloEvaluacionFacadeLocal{

	private List<Object[]> listaObjectResultado;
	private List<ModeloEvaluacion> listaModeloEvaluacion;
	private String sql = "";
	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;
	
	public ModeloEvaluacionFacade() {
		super(ModeloEvaluacion.class);
		listaObjectResultado = new ArrayList<Object[]>();
	}
	
	public ModeloEvaluacionFacade(Class<ModeloEvaluacion> entityClass) {
		super(entityClass);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public List<Object[]> findByCodigo(String codigoModelo) {
		sql = "";
		sql = "SELECT m, c.nombre  FROM ModeloEvaluacion m, CatalogoEbja c  where m.estado = c.nemonico and m.programaEbja.nemonico like concat('%',:nem,'%')";
		return validarBusquedaSimpleOTotal(sql, "nem", codigoModelo);
		
	}
	
	@Override
	public ModeloEvaluacion findByNemModulo(String codigoModelo) {
		
		return (ModeloEvaluacion) em.createQuery("SELECT m FROM ModeloEvaluacion m where m.nemonico =:nem").setParameter("nem", codigoModelo).getSingleResult();
		
	}

	@Override
	public List<Object[]> findByNombre(String nombreModelo) {
		sql = "";
		sql = "SELECT m, c.nombre  FROM ModeloEvaluacion m, CatalogoEbja c where m.estado = c.nemonico and m.programaEbja.nombre like concat('%',:nombre,'%') ";
		return validarBusquedaSimpleOTotal(sql, "nombre", nombreModelo);
	}
	
	
	
	@SuppressWarnings("unchecked")
	private List<Object[]> validarBusquedaSimpleOTotal(String NamedQuery, String variable, String valorVariable) {

		if (valorVariable.isEmpty()) {
			listaObjectResultado = em.createNamedQuery("ModeloEvaluacion.findAll").getResultList();
		} else {
			listaObjectResultado = em.createQuery(NamedQuery).setParameter(variable, valorVariable).getResultList();
		}
		return listaObjectResultado;
	}

	@Override
	public List<ModeloEvaluacion> buscarTodosModelos() {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ModeloEvaluacion> buscarTodosModelosActivos() {
		listaModeloEvaluacion = em.createNamedQuery("ModeloEvaluacion.findAllActiveCombo").getResultList();
		return listaModeloEvaluacion;
	}

}
