package ec.gob.educacion.ebja.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.educacion.ebja.facade.local.GrupoFaseFacadeLocal;
import ec.gob.educacion.ebja.modelo.Acuerdo;
import ec.gob.educacion.ebja.modelo.GrupoFasePrograma;

@Stateless
public class GrupoFaseFacade extends AbstractFacade<GrupoFasePrograma> implements GrupoFaseFacadeLocal {

	private List<GrupoFasePrograma> listaObjectResultado;
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
	public GrupoFasePrograma find(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> findByCodigo(String codigoGrupoFasePrograma) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> findByNombre(String nombreGrupoFasePrograma) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GrupoFasePrograma> findAll() {
		// TODO Auto-generated method stub
		return null;
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
		sql = "SELECT a FROM GrupoFasePrograma a where a.programaEducativo.idProgramaEducativo =:proyectoSeleccionado and a.faseExterna = 0 and a.estado = '1'";
		listaObjectResultado = em.createQuery(sql).setParameter("proyectoSeleccionado", Integer.valueOf(proyectoSeleccionado.intValue())).getResultList(); 
		return listaObjectResultado;
	}
	
	@Override
	public  List<GrupoFasePrograma> buscarTodosGrupoFaseProgramaActivosExternos(Long proyectoSeleccionado){
		sql = "";
		sql = "SELECT a FROM GrupoFasePrograma a where a.programaEducativo.idProgramaEducativo =:proyectoSeleccionado and a.faseExterna = 1 and a.estado = '1'";
		listaObjectResultado = em.createQuery(sql).setParameter("proyectoSeleccionado", Integer.valueOf(proyectoSeleccionado.intValue())).getResultList(); 
		return listaObjectResultado;
	}

	@Override
	public GrupoFasePrograma findByCodigoSoloGrupoFasePrograma(String codigoGrupoFasePrograma) {

		return null;
	}

}
