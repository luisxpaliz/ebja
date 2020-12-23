package ec.gob.educacion.ebja.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.educacion.ebja.facade.local.AdminAlfabModuloFacadeLocal;
import ec.gob.educacion.ebja.modelo.Acuerdo;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;

@Stateless
public class AdminAlfabModuloFacade extends AbstractFacade<ProgramaEbja> implements AdminAlfabModuloFacadeLocal {

	private List<Object[]> listaObjectResultado;
	private List<ProgramaEbja> listaProgramaEbjaResultado;


	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;
	private String sql = "";

	public AdminAlfabModuloFacade() {
		super(ProgramaEbja.class);
		listaObjectResultado = new ArrayList<Object[]>();
		listaProgramaEbjaResultado = new ArrayList();
	}

	public AdminAlfabModuloFacade(Class<ProgramaEbja> entityClass) {
		super(entityClass);

	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public List<Object[]> findByCodigo(String codigoAcuerdo) {
		sql = " ";
		//sql = "select pa, c.nombre from ProgramaAcuerdo pa, CatalogoEbja c where pa.programaEbja.nemonico like concat('%',:nem,'%') and pa.programaEbja.estado = c.nemonico ";
		sql = "select pe, c.nombre from ProgramaEbja pe, CatalogoEbja c where pe.nemonico like concat('%',:nem,'%') and pe.estado = c.nemonico and pe.estado in ('0','1')";
		return validarBusquedaSimpleOTotal(sql, "nem", codigoAcuerdo);
	}
	
	@Override
	public List<ProgramaEbja> buscarProgramaEbjaActivos(){
		
		sql = " ";
		sql="SELECT c FROM ProgramaEbja c where c.estado ='1'";
		listaProgramaEbjaResultado = em.createQuery(sql).getResultList();
		return listaProgramaEbjaResultado;
	}

	@Override
	public List<Object[]> findByNombre(String nombreAcuerdo) {
		sql = " ";
		sql = "select pa, c.nombre from ProgramaAcuerdo pa, CatalogoEbja c where pa.programaEbja.nombre like concat('%',:nombre,'%') and pa.programaEbja.estado = c.nemonico and pa.estado in ('0','1') ";
		return validarBusquedaSimpleOTotal(sql, "nombre", nombreAcuerdo);
	}

	@SuppressWarnings("unchecked")
	private List<Object[]> validarBusquedaSimpleOTotal(String NamedQuery, String variable, String valorVariable) {

		if (valorVariable.isEmpty()) {
			sql = " ";
			//sql = "select pa, c.nombre from ProgramaAcuerdo pa, CatalogoEbja c where pa.programaEbja.estado = c.nemonico";
			sql="select pe, c.nombre from ProgramaEbja pe, CatalogoEbja c where pe.estado = c.nemonico and pe.estado in ('0','1')";
			listaObjectResultado = em.createQuery(sql).getResultList();
		} else {
			listaObjectResultado = em.createQuery(NamedQuery).setParameter(variable, valorVariable)
					.getResultList();
		}
		return listaObjectResultado;
	}

	

	@Override
	public boolean findCodigo() {
		sql = " ";
		sql = "select pa, c.nombre from ProgramaAcuerdo pa where pa.estado in ('0','1')";
		listaObjectResultado = em.createQuery(sql).getResultList();
		return false;
	}
	
	
	@Override
	public List<ProgramaEbja> findAllActive() {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	
}
