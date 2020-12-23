package ec.gob.educacion.ebja.facade;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.hibernate.Hibernate;
import ec.gob.educacion.ebja.facade.local.CursoParaleloFacadeLocal;
import ec.gob.educacion.ebja.modelo.CursoParalelo;
import ec.gob.educacion.ebja.modelo.zeus.Paralelo;
import ec.gob.educacion.ebja.recursos.Constantes;

@Stateless
public class CursoParaleloFacade extends AbstractFacade<CursoParalelo> implements CursoParaleloFacadeLocal {
	
	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

    public CursoParaleloFacade() {
        super(CursoParalelo.class);
    }

	@Override
	public Long validarExisteCursoParalelo(Integer idProgramaInstitucion, Integer idParalelo) {
		String sql = "";
		Long resultado = (long) 0;
		
		sql = "select count(cp)"
			+ "  from CursoParalelo cp"
			+ " where cp.programaInstitucion.id = :idProgramaInstitucion"
			+ "   and cp.paralelo.id = :idParalelo"
			+ "   and cp.estado = '1' ";
		
		resultado = (Long) em.createQuery(sql).setParameter("idProgramaInstitucion", idProgramaInstitucion)
							 .setParameter("idParalelo", idParalelo).getSingleResult();
		
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CursoParalelo> findByIdProgramaInstitucion(Integer idProgramaInstitucion) {
		String sql = "";
		List<CursoParalelo> listaAux;
		List<CursoParalelo> listaResultado = new ArrayList<>();
		
		sql = "select cp"
				+ " from CursoParalelo cp "
				+ " where cp.programaInstitucion.id = :idProgramaInstitucion "
				+ "   and cp.estado = '1' "
				+ " order by cp.paralelo.descripcion ";
		
		listaAux = em.createQuery(sql)
				.setParameter("idProgramaInstitucion", idProgramaInstitucion)
				.getResultList();
		
		if (!listaAux.isEmpty()) {
			int index = 0;
			for (CursoParalelo cursoParaleloAux : listaAux) {
				cursoParaleloAux = listaAux.get(index);
				Hibernate.initialize(cursoParaleloAux.getParalelo());
				listaResultado.add(cursoParaleloAux);
				index++;
			}
		}
		
		return listaResultado;
	}

	@Override
	public Integer cuentaTotalAforo(Integer idProgramaInstitucion) {
		String sql = "";
		Long resultado = (long) 0;
	
		sql = "select coalesce(sum(cp.aforo),0) "
				+ " from CursoParalelo cp "
				+ " where cp.programaInstitucion.id = :idProgramaInstitucion "
				+ " and cp.estado = '1' ";			
		
		resultado = (Long) em.createQuery(sql).setParameter("idProgramaInstitucion", idProgramaInstitucion).getSingleResult();
				
		return resultado.intValue();
	}

	@Override
	public Integer cuentaTotalBanca(Integer idProgramaInstitucion) {
		String sql = "";
		Long resultado = (long) 0;
		
		sql = "select coalesce(sum(cp.numeroBanca),0) "
				+ " from CursoParalelo cp "
				+ " where cp.programaInstitucion.id = :idProgramaInstitucion "
				+ " and cp.estado = '1' ";
		
		resultado = (Long) em.createQuery(sql).setParameter("idProgramaInstitucion", idProgramaInstitucion).getSingleResult();
				
		return resultado.intValue();
	}

	@Override
	public Integer cuentaTotalMatriculado(Integer idProgramaInstitucion) {
		String sql = "";
		Long resultado = (long) 0;
		
		sql = "select coalesce(sum(cp.numeroMatriculado),0) "
				+ " from CursoParalelo cp "
				+ " where cp.programaInstitucion.id = :idProgramaInstitucion "
				+ " and cp.estado = '1' ";
		
		resultado = (Long) em.createQuery(sql).setParameter("idProgramaInstitucion", idProgramaInstitucion).getSingleResult();
		
		return resultado.intValue();
	}

	@Override
	public List<CursoParalelo> obtenerCursoParaleloPorInstitucion(String amie, Integer idProgramaInstitucion) {
		List<CursoParalelo> listaCursoParalelo = new ArrayList<CursoParalelo>();

		StringBuilder sql = new StringBuilder();
		sql.append("select cp from CursoParalelo cp "
		         + "  join cp.paralelo pa "
		         + "  join cp.programaInstitucion pi "
		         + " where (pa.estado = :estado or pa.estado = :estado1) "
		         + "   and pi.estado = :estado "
		         + "   and cp.estado = :estado "
		         + "   and pi.institucEstablec.idInstitucion.amie = :amie "
        		 + "   and pi.id = :idProgramaInstitucion ");
		
		TypedQuery<CursoParalelo> q = em.createQuery(sql.toString(), CursoParalelo.class)
			.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO)
			.setParameter("estado1", Constantes.ESTADO_REGISTRO_ACTIVO1)
			.setParameter("amie", amie)
			.setParameter("idProgramaInstitucion", idProgramaInstitucion);
		
		listaCursoParalelo = q.getResultList();

		return listaCursoParalelo;
	}

	@Override
	public List<CursoParalelo> obtenerCursoParaleloPorinstitucion(Integer idParalelo, Integer idProgramaInstitucion) {
		List<CursoParalelo> listaCursoParalelo = new ArrayList<CursoParalelo>();
		String sql = "";
		sql = "select cp"
				+ "  from CursoParalelo cp"
				+ " where cp.programaInstitucion.id = :idProgramaInstitucion"
				+ "   and cp.paralelo.id = :idParalelo ";
		listaCursoParalelo = em.createQuery(sql).setParameter("idProgramaInstitucion", idProgramaInstitucion)
		 .setParameter("idParalelo", idParalelo).getResultList();
		return listaCursoParalelo;
	}
	
}
