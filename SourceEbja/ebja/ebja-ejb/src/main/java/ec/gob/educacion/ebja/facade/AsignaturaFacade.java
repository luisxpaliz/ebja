package ec.gob.educacion.ebja.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.educacion.ebja.facade.local.AsignaturaFacadeLocal;
import ec.gob.educacion.ebja.modelo.Asignatura;

@Stateless
public class AsignaturaFacade extends AbstractFacade<Asignatura> implements AsignaturaFacadeLocal {

	private List<Asignatura> listaResultado;
	private String sql = "";

	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public AsignaturaFacade() {
		super(Asignatura.class);
		listaResultado = new ArrayList<>();

	}

	public AsignaturaFacade(Class<Asignatura> entityClass) {
		super(entityClass);
	}

	@Override
	public List<Asignatura> findByArea(String area) {
		sql = "";
		area = area.replaceAll("\\s", "%");
		sql = "select a from Asignatura a where upper(a.area.nombre) like concat('%',upper(:area),'%')";
		return validarBusquedaSimpleOTotal(sql, "area", area);
	}
	
	@Override
	public Asignatura findByNemonico(String nemonico) {
		sql = "";
		sql = "SELECT a FROM Asignatura a where a.nemonico =:nemo";
		return (Asignatura) em.createQuery(sql).setParameter("nemo", nemonico).getSingleResult();
	}

	@Override
	public List<Asignatura> findByMateria(String materia) {
		sql = "";
		materia = materia.replaceAll("\\s", "%");
		sql = "select a from Asignatura a where upper(a.materia.nombre) like concat('%',upper(:materia),'%')";
		return validarBusquedaSimpleOTotal(sql, "materia", materia);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Asignatura> buscarTodasAsignaturas() {
		return (List<Asignatura>) em.createNamedQuery("Asignatura.findAll").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Asignatura> buscarTodasAsignaturasActivas() {
		return (List<Asignatura>) em.createNamedQuery("Asignatura.findAllActive").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	private List<Asignatura> validarBusquedaSimpleOTotal(String NamedQuery, String variable, String valorVariable) {

		if (valorVariable.isEmpty()) {
			listaResultado = buscarTodasAsignaturas();
		} else {
			listaResultado = em.createQuery(NamedQuery).setParameter(variable, valorVariable).getResultList();
		}
		return listaResultado;
	}
}
