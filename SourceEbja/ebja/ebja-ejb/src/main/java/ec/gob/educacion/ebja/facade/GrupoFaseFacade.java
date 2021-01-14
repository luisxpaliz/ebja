package ec.gob.educacion.ebja.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.educacion.ebja.facade.local.GrupoFaseFacadeLocal;
import ec.gob.educacion.ebja.modelo.Acuerdo;
import ec.gob.educacion.ebja.modelo.GrupoFasePrograma;
import ec.gob.educacion.ebja.modelo.ProgramaEducativo;

@Stateless
public class GrupoFaseFacade extends AbstractFacade<GrupoFasePrograma> implements GrupoFaseFacadeLocal {

	private List<Object[]> listaObjectResultado;
	private List<GrupoFasePrograma> listaGrupoFaseResultado;
	private GrupoFasePrograma grupoFase;
	private String sql = "";

	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;
	
	public GrupoFaseFacade(Class<GrupoFasePrograma> entityClass) {
		super(entityClass);
	}
	
	public GrupoFaseFacade() {
		super(GrupoFasePrograma.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}	

	@Override
	public List<Object[]> findByCodigo(String codigoGrupoFasePrograma) {
		sql = "";
		sql = "select a, c.nombre from GrupoFasePrograma a, CatalogoEbja c where a.estado = c.nemonico and a.nemonico like concat('%',:nem,'%')";
		return validarBusquedaSimpleOTotal(sql, "nem", codigoGrupoFasePrograma);
	}
	
	
	@Override
	public Object[] findByNemonico(String codigoGrupoFasePrograma) {
		sql = "";
		sql = "select a, c.nombre from GrupoFasePrograma a, CatalogoEbja c where a.estado = c.nemonico and a.nemonico = :nem";
		return (Object[]) em.createQuery(sql).setParameter("nem", codigoGrupoFasePrograma).getSingleResult();
	}


	@Override
	public List<Object[]> findByNombre(String nombreGrupoFasePrograma) {
		sql = "";
		sql = "select a, c.nombre from GrupoFasePrograma a, CatalogoEbja c where a.estado = c.nemonico and a.nombre like concat('%',:nombre,'%') ";
		return validarBusquedaSimpleOTotal(sql, "nombre", nombreGrupoFasePrograma);
	}
	
	@Override
	public List<GrupoFasePrograma> findByProgramaEbja(String nemonicoProgramaEbja) {
		sql = "";
		sql = "select a.grupoFasePrograma FROM ProgramaEbja a WHERE a.nemonico like :nemonicoProgramaEbja";
	    listaGrupoFaseResultado = em.createQuery(sql).setParameter(nemonicoProgramaEbja, nemonicoProgramaEbja).getResultList();
		return listaGrupoFaseResultado;
	}
	
	@Override
	public List<Object[]> findByProyecto(String nemonicoProyecto) {
		sql = "";
		sql = "select a, c.nombre from GrupoFasePrograma a, CatalogoEbja c where a.estado = c.nemonico and a.programaEducativo.nemonico =:nemonicoProyecto ";
		listaObjectResultado = em.createQuery(sql).setParameter("nemonicoProyecto", nemonicoProyecto).getResultList();
		return listaObjectResultado;
	}

	@Override
	public List<GrupoFasePrograma> buscarTodosGrupoFasePrograma() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Integer buscarFaseExterna(long grupoFaseSeleccionado) {
		
		sql = "";
		sql = "SELECT a FROM GrupoFasePrograma a where a.idGrupoFaseNotas =:grupoFaseSeleccionado and a.estado = '1'";
		grupoFase = (GrupoFasePrograma) em.createQuery(sql).setParameter("grupoFaseSeleccionado", grupoFaseSeleccionado).getSingleResult(); 
		return grupoFase.getFaseExterna();
		
	}

	@Override
	public List<GrupoFasePrograma> buscarTodosGrupoFaseProgramaActivos(Long proyectoSeleccionado) {
		sql = "";
		sql = "SELECT a FROM GrupoFasePrograma a where a.programaEducativo.idProgramaEducativo =:proyectoSeleccionado and a.faseExterna = 0 and a.estado = '1' order by a.secuenciaFase ";
		listaGrupoFaseResultado = em.createQuery(sql).setParameter("proyectoSeleccionado", Integer.valueOf(proyectoSeleccionado.intValue())).getResultList(); 
		return listaGrupoFaseResultado;
	}
	
	@Override
	public  List<GrupoFasePrograma> buscarTodosGrupoFaseProgramaActivosExternos(Long proyectoSeleccionado){
		sql = "";
		sql = "SELECT a FROM GrupoFasePrograma a where a.programaEducativo.idProgramaEducativo =:proyectoSeleccionado and a.faseExterna = 1 and a.estado = '1'";
		listaGrupoFaseResultado = em.createQuery(sql).setParameter("proyectoSeleccionado", Integer.valueOf(proyectoSeleccionado.intValue())).getResultList(); 
		return listaGrupoFaseResultado;
	}

	@Override
	public GrupoFasePrograma findByCodigoSoloGrupoFasePrograma(String codigoGrupoFasePrograma) {
		sql = "";
		sql = "SELECT a FROM GrupoFasePrograma a where a.nemonico =:grupoFaseSeleccionado and a.estado = '1'";
		grupoFase = (GrupoFasePrograma) em.createQuery(sql).setParameter("grupoFaseSeleccionado", codigoGrupoFasePrograma).getResultList().get(0); 
		return grupoFase;
	}
	

	

	@Override
	public List<GrupoFasePrograma> buscarGrupoFaseProgramaActivos() {
		sql = " ";
		sql="SELECT c FROM GrupoFasePrograma c where c.estado ='1'";
		listaGrupoFaseResultado = em.createQuery(sql).getResultList();
		return listaGrupoFaseResultado;
	}
	
	@Override
	public List<GrupoFasePrograma> buscarGrupoFaseProgActInternosXProyecto(String programaEducativo) {
		sql = " ";
		sql="SELECT c FROM GrupoFasePrograma c where c.estado ='1' and c.faseExterna = 0 and c.programaEducativo.nemonico=:programaEducativo";
		listaGrupoFaseResultado = em.createQuery(sql).setParameter("programaEducativo", programaEducativo).getResultList();
		return listaGrupoFaseResultado;
	}
	
	@Override
	public List<GrupoFasePrograma> buscarGrupoFaseProgActInternos() {
		sql = " ";
		sql="SELECT c FROM GrupoFasePrograma c where c.estado ='1' and c.faseExterna = 0 ";
		listaGrupoFaseResultado = em.createQuery(sql).getResultList();
		return listaGrupoFaseResultado;
	}
	
	@SuppressWarnings("unchecked")
	private List<Object[]> validarBusquedaSimpleOTotal(String NamedQuery, String variable, String valorVariable) {

		if (valorVariable.isEmpty()) {
			sql = "";
			sql = "select a, c.nombre from GrupoFasePrograma a, CatalogoEbja c where a.estado = c.nemonico ";
			listaObjectResultado = em.createQuery(sql).getResultList();
		} else {
			listaObjectResultado = em.createQuery(NamedQuery).setParameter(variable, valorVariable).getResultList();
		}
		return listaObjectResultado;
	}

	

}
