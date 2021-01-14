package ec.gob.educacion.ebja.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.educacion.ebja.facade.local.AdminAlfabAcuRelFacadeLocal;
import ec.gob.educacion.ebja.modelo.Acuerdo;
import ec.gob.educacion.ebja.modelo.AcuerdoJTA;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;

@Stateless
public class AdminAlfabAcuRelFacade extends AbstractFacade<Acuerdo> implements AdminAlfabAcuRelFacadeLocal {

	private List<Object[]> listaObjectResultado;
	private String sql = "";

	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public AdminAlfabAcuRelFacade() {
		super(Acuerdo.class);
		listaObjectResultado = new ArrayList<Object[]>();

	}

	public AdminAlfabAcuRelFacade(Class<Acuerdo> entityClass) {
		super(entityClass);
	}

	@Override
	public List<Object[]> findByCodigo(String codigoAcuerdo) {

		sql = "";
		sql = "select a, c.nombre from Acuerdo a, CatalogoEbja c where a.estado = c.nemonico and a.nemonico like concat('%',:nem,'%')";
		return validarBusquedaSimpleOTotal(sql, "nem", codigoAcuerdo);
	}
	
	@Override
	public Acuerdo findByCodigoSoloAcuerdo(String codigoAcuerdo) {

		sql = "";
		sql = "select a from Acuerdo a where a.nemonico = :nem)";
		return (Acuerdo) em.createQuery(sql).setParameter("nem", codigoAcuerdo).getSingleResult();
	}

	@Override
	public List<Object[]> findByNombre(String nombreAcuerdo) {
		sql = "";
		sql = "select a, c.nombre from Acuerdo a, CatalogoEbja c where a.estado = c.nemonico and a.nombre like concat('%',:nombre,'%') ";
		return validarBusquedaSimpleOTotal(sql, "nombre", nombreAcuerdo);
	}

	@SuppressWarnings("unchecked")
	public List<Acuerdo> buscarTodosAcuerdos() {
		return (List<Acuerdo>) em.createNamedQuery("Acuerdo.findAll").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Acuerdo> buscarTodosAcuerdosActivos() {
		return (List<Acuerdo>) em.createNamedQuery("Acuerdo.findActiveAll").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<AcuerdoJTA> buscarTodosAcuerdosActivosJTA() {
		return (List<AcuerdoJTA>) em.createNamedQuery("AcuerdoJTA.findActiveAll").getResultList();
	}
	
	/*Se necesita para relizar una consulta rapida en una relación EAGER del mapeo Hibernate*/
	@Override
	public List<Acuerdo> buscarTodosAcuerdosActivosNat(){
		sql = " ";
		sql="select a.id,a.nombre,a.nemonico,a.archivo_pdf,a.id_usuario_creacion,a.fecha_creacion,a.ip_usuario,a.estado from ebja.acuerdo a where a.estado='1' ";
		return (List<Acuerdo>) em.createNativeQuery(sql,Acuerdo.class).getResultList();
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
}
