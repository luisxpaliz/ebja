package ec.gob.educacion.ebja.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ec.gob.educacion.ebja.facade.local.ProgramaEbjaFacadeLocal;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.recursos.Constantes;

@Stateless
public class ProgramaEbjaFacade extends AbstractFacade<ProgramaEbja> implements ProgramaEbjaFacadeLocal {

	private List<ProgramaEbja> listaProgramaEbja;
	private ProgramaEbja programa;
	private boolean existeRegistro =false;

	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	public ProgramaEbjaFacade() {
		super(ProgramaEbja.class);
		listaProgramaEbja = new ArrayList<ProgramaEbja>();
		programa = new ProgramaEbja();
	}

	public ProgramaEbjaFacade(Class<ProgramaEbja> entityClass) {
		super(entityClass);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProgramaEbja> findAllActive() {
		listaProgramaEbja = em.createNamedQuery("ProgramaEbja.findAllActive").getResultList();
		return listaProgramaEbja;
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public ProgramaEbja obtenerProgramaEbja(String valorVariable) {
		programa  = (ProgramaEbja) em.createNamedQuery("ProgramaEbja.findByCodigo").setParameter("nemonico", valorVariable).getSingleResult();
		return programa;
	}
	
	@Override
	public List<ProgramaEbja> obtenerProgramaEbjaGrupoFase(long idGrupoFase) {
		listaProgramaEbja  =  em.createNamedQuery("ProgramaEbja.findByFaseGrupo").setParameter("idFaseGrupo", idGrupoFase).getResultList();
		return listaProgramaEbja;
	}
	
	@Override
	public Integer obtenerOfertaExterna(long idGrupoFase) {
		em.createNamedQuery("ProgramaEbja.findByFaseGrupo").setParameter("idFaseGrupo", idGrupoFase).getResultList();
		return null;
	 }
	
	@Override
	public Integer buscarSiguientePrograma(String programaEbja) {
		String sql = "SELECT p.secuenciaPrograma FROM ProgramaEbja p where p.nemonico =:programaEbja";
		Integer tempProgramaEbja =  (Integer) em.createQuery(sql).setParameter("programaEbja",programaEbja).getSingleResult();
		return tempProgramaEbja;
	}
	
	
	@Override
	public ProgramaEbja buscarPrograma(Integer idProgramaEbja) {
		String sql = "SELECT p FROM ProgramaEbja p where p.id =:idProgramaEbja";
		ProgramaEbja tempProgramaEbja =  (ProgramaEbja) em.createQuery(sql).setParameter("idProgramaEbja",idProgramaEbja).getSingleResult();
		return tempProgramaEbja;
	}
		

	@Override
	public List<ProgramaEbja> obtenerProgramaEbjaPorInstitucion(String amie) {
		List<ProgramaEbja> listaProgramaEbja = new ArrayList<ProgramaEbja>();

		StringBuilder sql = new StringBuilder();
		sql.append("select pe from ProgramaInstitucion pi "
				 + "  join pi.programaEbja pe "
		         + " where (pi.estado = :estado or pi.estado = :estado1) "
		         + "   and pi.institucEstablec.idInstitucion.amie = :amie ");
		
		TypedQuery<ProgramaEbja> q = em.createQuery(sql.toString(), ProgramaEbja.class)
			.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO)
			.setParameter("estado1", Constantes.ESTADO_REGISTRO_ACTIVO1)
			.setParameter("amie", amie);
		
		listaProgramaEbja = q.getResultList();

		return listaProgramaEbja;
	}

	@Override
	public ProgramaEbja obtenerProgramaPorNemonico(String nemonico) {
		// TODO Auto-generated method stub
		String sql = "SELECT p FROM ProgramaEbja p where p.nemonico =:nemonico";
		List <ProgramaEbja> tempProgramaEbja =  em.createQuery(sql).setParameter("nemonico",nemonico).getResultList();
		if(tempProgramaEbja.size()!=0)
		return tempProgramaEbja.get(0);
		else {
		System.out.println("resultado vacio ProgramaPorNemonico");
		return null;
		
	}
	}
	
	
}
