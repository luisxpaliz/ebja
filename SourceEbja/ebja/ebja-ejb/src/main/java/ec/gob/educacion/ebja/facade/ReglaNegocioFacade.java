package ec.gob.educacion.ebja.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ec.gob.educacion.ebja.facade.local.ReglaNegocioFacadeLocal;
import ec.gob.educacion.ebja.modelo.ReglaNegocio;

@Stateless
public class ReglaNegocioFacade extends AbstractFacade<ReglaNegocio> implements ReglaNegocioFacadeLocal{

	private List<Object[]> listaObjectResultado;
	
	private List<ReglaNegocio> listaReglaNegocio;
	private String sql = "";
	
	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;
	
	public ReglaNegocioFacade() {
		super(ReglaNegocio.class);
	}
	
	public ReglaNegocioFacade(Class<ReglaNegocio> entityClass) {
		super(entityClass);	
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findByProgramaEbja (String nemonico){
		sql = "";
		sql = "select rn, c.nombre from ReglaNegocio rn, CatalogoEbja c where  rn.estado = c.nemonico and rn.programaEbja.nemonico like concat('%',:nem,'%')";
		listaObjectResultado = em.createQuery(sql).setParameter("nem", nemonico).getResultList();
		return listaObjectResultado;
	}
	
	@SuppressWarnings("unchecked")
	public List<ReglaNegocio> findByProgramaEbjaFaseValidacion (String nemonicoPrograma,String nemonicoFase){
		
		sql = "";
		sql = " SELECT r FROM ReglaNegocio r where r.programaEbja.nemonico =:pnem and r.fase.nemonico =:fnem ";
		listaReglaNegocio = em.createQuery(sql).setParameter("pnem", nemonicoPrograma).setParameter("fnem", nemonicoFase).getResultList();
		return listaReglaNegocio;
		
	}

	public ReglaNegocio buscarPorProgramaEbjaFase(Integer idProgramaEbja, String nemonico) {
		ReglaNegocio reglaNegocio = new ReglaNegocio();
		try {
			StringBuilder sentencia = new StringBuilder();
			sentencia.append(" select rn from ReglaNegocio rn ");
			sentencia.append("  where rn.programaEbja.id = :idProgramaEbja ");
			sentencia.append("    and rn.fase.nemonico = :nemonico ");
			sentencia.append("    and rn.estado = :estado ");

			Query q = em.createQuery(sentencia.toString())
						.setParameter("idProgramaEbja", idProgramaEbja)
						.setParameter("nemonico", nemonico)
						.setParameter("estado", "1");
			reglaNegocio =  (ReglaNegocio) q.getSingleResult();
		} catch (Exception e) {
			//e.printStackTrace();
			reglaNegocio = null;
		}
		
		return reglaNegocio;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ReglaNegocio> findAllActive(){
		return em.createNamedQuery("ReglaNegocio.findAllActive").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReglaNegocio> findByProgramaEbjaValido(String nemonicoPrograma) {
		sql = "";
		sql = " SELECT r FROM ReglaNegocio r where r.programaEbja.nemonico =:pnem ";
		listaReglaNegocio = em.createQuery(sql).setParameter("pnem", nemonicoPrograma).getResultList();
		return listaReglaNegocio;
	}
	
	
	@Override
	public boolean buscarDependenciaPorCodigoReglaNegocio(String id) {
		String sql ="SELECT r FROM ReglaNegocio r where r.estado='1' and r.programaEbja.nemonico =:cod";
		return em.createQuery(sql).setParameter("cod", id).getResultList().isEmpty();
	}
	
	
}
