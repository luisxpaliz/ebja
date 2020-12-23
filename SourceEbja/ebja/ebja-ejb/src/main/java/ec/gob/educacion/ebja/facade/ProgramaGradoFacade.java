package ec.gob.educacion.ebja.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ec.gob.educacion.ebja.facade.local.ProgramaGradoFacadeLocal;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.modelo.ProgramaGrado;
import ec.gob.educacion.ebja.modelo.zeus.Grado;

@Stateless
public class ProgramaGradoFacade extends AbstractFacade<ProgramaGrado> implements ProgramaGradoFacadeLocal {

	private List<Object[]> listaObjectResultado;
	private List<ProgramaGrado> listaProgramaGrado;
	private List<Grado> grado;
	private List<Grado> listaGradosUnificados;
	private String sql = "";
	private List<ProgramaEbja> tempResultado;
	private ProgramaEbja ProgramaEbjaTemp;
	
	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;
	
	
	public ProgramaGradoFacade() {
		super(ProgramaGrado.class);
		
	}
	
	public ProgramaGradoFacade(Class<ProgramaGrado> entityClass) {
		super(entityClass);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> buscarTodosGrados() {  
		
	    listaObjectResultado = em.createQuery("SELECT p,c.nombre FROM ProgramaGrado p,CatalogoEbja c where p.estado = c.nemonico and p.estado = '1'").getResultList();
		return listaObjectResultado;
	}
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> buscarTodosGradosPorPrograma(String nemonico) {
		    sql = "";
			sql = "SELECT p,c.nombre FROM ProgramaGrado p,CatalogoEbja c where p.estado = c.nemonico and p.estado = '1' and p.programaEbja.nemonico like concat('%',:nem,'%')";
			listaObjectResultado = em.createQuery(sql).setParameter("nem", nemonico).getResultList();
			return listaObjectResultado;
	}
	
	@Override
	public boolean buscarDependenciaPorCodigoGrado(String id) {
		String sql ="SELECT p FROM ProgramaGrado p where p.estado = '1' and p.programaEbja.nemonico = :cod";
		return em.createQuery(sql).setParameter("cod", id).getResultList().isEmpty();
		
	}

	@Override
	public List<ProgramaGrado> buscarProgramaGrado(Grado grado, ProgramaEbja programaEbja) {
		sql = "SELECT p FROM ProgramaGrado p where p.grado.id = :grado and p.programaEbja.id =:programaEbja and p.estado = '1'";
		listaProgramaGrado = em.createQuery(sql).setParameter("grado",grado.getId()).setParameter("programaEbja",programaEbja.getId()).getResultList();
		return listaProgramaGrado;	
	}
/**/
	@Override
	public List<Grado> buscarGradoUnificadosPorPrograma(String programaEbja) {
		sql = "SELECT p.gradoUnificado.grado FROM ProgramaGrado p where p.programaEbja.nemonico =:programaEbja and p.estado = '1'";
		listaGradosUnificados =  em.createQuery(sql).setParameter("programaEbja",programaEbja).getResultList();
		return listaGradosUnificados;
	}
	
	@Override
	public List<ProgramaGrado> buscarPrograma(String programaEbja) {
		sql = "SELECT p FROM ProgramaGrado p where p.programaEbja.id =:programaEbja and p.estado = '1'";
		listaProgramaGrado = em.createQuery(sql).setParameter("programaEbja",programaEbja).getResultList();
		return listaProgramaGrado;
		
	}
	
	@Override
	public List<Grado> buscarGradoUnificados(String programaEbja) {
		sql = "SELECT p.grado FROM ProgramaGrado p where p.programaEbja.nemonico =:programaEbja and p.estado = '1'  ";
		listaGradosUnificados =  em.createQuery(sql).setParameter("programaEbja",programaEbja).getResultList();
		return listaGradosUnificados;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ProgramaGrado buscarProgramaGrado(Integer grado , String programaEbja) {
		
		sql = "SELECT p FROM ProgramaGrado p where p.programaEbja.nemonico =:programaEbja and p.grado.id =:grado and p.estado = '1'  ";
		listaProgramaGrado = em.createQuery(sql).setParameter("programaEbja",programaEbja).setParameter("grado",grado).getResultList();
		return (listaProgramaGrado.isEmpty())?null:listaProgramaGrado.get(0); 
	}
	
	
	
	@Override
	public Grado buscarGradoPrimero(String programaEbja) {
		sql = "SELECT p.grado FROM ProgramaGrado p where p.programaEbja.nemonico =:programaEbja and p.gradoInicial = 1 and p.estado = '1' and p.visible = '1' ";
		Grado tempGrado =  (Grado) em.createQuery(sql).setParameter("programaEbja",programaEbja).getSingleResult();
		return tempGrado;
	}
	
	
	@Override
	public Grado buscarGradoUltimo(String programaEbja) {
		sql = "SELECT p.grado FROM ProgramaGrado p where p.programaEbja.nemonico =:programaEbja and p.gradoInicial = 3 and p.estado = '1'";
		Grado tempGrado =  (Grado) em.createQuery(sql).setParameter("programaEbja",programaEbja).getSingleResult();
		return tempGrado;
	}

	@Override
	public Grado buscarGradoSiguienteOferta(String programaEbja,Integer idGrado) {
		sql = " Select g from Grado g where g.id in (SELECT p.secuenciaGrado FROM ProgramaGrado p where p.programaEbja.nemonico =:programaEbja and p.grado.id = :idGrado and p.estado = '1'  )";
		Grado tempGrado =  (Grado) em.createQuery(sql).setParameter("programaEbja",programaEbja).setParameter("idGrado",idGrado).getSingleResult();
		return tempGrado;
	}
	
	public List<ProgramaEbja> buscarProgramaEbjaPorGrado(Long idGrupoFase, Integer idGrado) {
		
		sql = "SELECT p.programaEbja FROM ProgramaGrado p where p.programaEbja.grupoFasePrograma.idGrupoFaseNotas = :idGrupoFase and p.grado.id =:idGrado and p.estado = '1'  ";
		List<ProgramaEbja> listPrograma = em.createQuery(sql).setParameter("idGrupoFase",idGrupoFase).setParameter("idGrado",idGrado).getResultList();
		return listPrograma;
	}
	
	 public ProgramaEbja buscarProgramaEbjaSiguienteInscripcion(Long idGrupoFase, Integer idProgramaEbjaActual) {
		sql = "SELECT pe FROM ProgramaEbja pe where pe.id in (SELECT p.programaEbja.SecInscripcion FROM ProgramaGrado p where  p.estado = '1'   and p.programaEbja.grupoFasePrograma.idGrupoFaseNotas = :idGrupoFase and p.programaEbja.id =:idProgramaEbjaActual)";
		List<ProgramaEbja> listPrograma = em.createQuery(sql).setParameter("idGrupoFase",idGrupoFase).setParameter("idProgramaEbjaActual",idProgramaEbjaActual).getResultList();
		ProgramaEbjaTemp = (listPrograma.isEmpty()==false)?listPrograma.get(0):null;
		/*sale resultados con programa */
		return ProgramaEbjaTemp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
     public Integer programaEbjaTieneAsignadoPackCurso(String nemonico) {
    	 
		tempResultado =  em.createQuery("SELECT p FROM ProgramaEbja p where p.estado = '1'   and p.nemonico = :nemonico").setParameter("nemonico", nemonico).getResultList();
		// 0 no tiene pack, 1 si tiene pack
		return tempResultado.get(0).getEsPack();
	}


}
