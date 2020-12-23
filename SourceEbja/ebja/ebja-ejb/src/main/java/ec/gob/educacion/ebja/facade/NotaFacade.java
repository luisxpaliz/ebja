package ec.gob.educacion.ebja.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.educacion.ebja.facade.local.NotaFacadeLocal;
import ec.gob.educacion.ebja.modelo.Notas;

@Stateless
public class NotaFacade extends AbstractFacade<Notas>implements NotaFacadeLocal {

	private List<Object[]> listaObjectResultado;
	private String sql = "";
	
	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public NotaFacade(Class<Notas> entityClass) {
		super(entityClass);
	}
	
	public NotaFacade() {
		super(Notas.class);
	}

	@Override
	public boolean findCodigo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Notas find(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> findByCodigo(String codigoNota) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notas> buscarNotaActivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> findByNombre(String nombreNota) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Object[]> findAllPivot() {
		
		listaObjectResultado = ejecutarCursorPivot("");		
		System.out.println("");
		return listaObjectResultado;
	}

	@Override
	public List<Notas> findAllActive() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private List<Object[]> validarBusquedaSimpleOTotal(String NamedQuery, String variable, String valorVariable) {

		if (valorVariable.isEmpty()) {
			sql = "";
			sql = "select a, c.nombre from Acuerdo a, CatalogoEbja c where a.estado = c.nemonico ";
			listaObjectResultado = em.createQuery(sql).getResultList();
		} else {
			listaObjectResultado = em.createQuery(NamedQuery).setParameter(variable, valorVariable).getResultList();
		}
		return listaObjectResultado;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized List<Object[]> ejecutarCursorPivot(String query){
		 
		 em.createNativeQuery("BEGIN;").executeUpdate();
		 em.createNativeQuery("SELECT ebja.dynamic_pivot('SELECT id_plantilla_estudiante,id_plantilla_nota,nota FROM ebja.notas where tipo_nota_abc IS NULL ORDER BY 1','SELECT DISTINCT id_plantilla_nota FROM ebja.notas where tipo_nota_abc IS NULL ORDER BY 1 ','pivot');").getSingleResult();
		 listaObjectResultado =  em.createNativeQuery("FETCH ALL IN pivot;").getResultList();
		 em.createNativeQuery("CLOSE pivot;").executeUpdate();
		 em.createNativeQuery("COMMIT;").executeUpdate();
		
		return listaObjectResultado;
	}

}
