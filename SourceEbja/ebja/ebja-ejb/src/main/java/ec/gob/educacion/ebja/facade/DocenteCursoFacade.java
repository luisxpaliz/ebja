package ec.gob.educacion.ebja.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.educacion.ebja.facade.local.DocenteCursoFacadeLocal;
import ec.gob.educacion.ebja.modelo.DocenteCurso;
import ec.gob.educacion.ebja.modelo.zeus.Institucion;

@Stateless
public class DocenteCursoFacade extends AbstractFacade<DocenteCurso> implements DocenteCursoFacadeLocal {
	
	private List<Institucion> instituciones;
	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

    public DocenteCursoFacade() {
        super(DocenteCurso.class);
    }

	@Override
	public Long validarExisteDocenteCurso(Integer idProgramaInstitucion, Integer idDocente) {
		String sql = "";
		Long resultado = (long) 0;
		
		sql = "select count(dc)"
				+ " from DocenteCurso dc"
				+ " where dc.programaInstitucion.id = :idProgramaInstitucion"
				+ " and dc.docente.id = :idDocente";
		
		resultado = (Long) em.createQuery(sql).setParameter("idProgramaInstitucion", idProgramaInstitucion).setParameter("idDocente", idDocente).getSingleResult();
		
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DocenteCurso> docenteCursoFindByIdProgramaInstitucion(Integer idProgramaInstitucion) {
		String sql = "";
		List<DocenteCurso> listaResultado;
		
		sql = "select dc"
				+ " from DocenteCurso dc"
				+ " where dc.programaInstitucion.id = :idProgramaInstitucion"
				+ " order by dc.docente.persona.nombresApellidos";
		
		listaResultado = em.createQuery(sql)
				.setParameter("idProgramaInstitucion", idProgramaInstitucion)
				.getResultList();
		
		return listaResultado;
	}

	@Override
	public List<Institucion> buscarInstitucionDocente(Integer idDocente) {
		instituciones =  em.createNamedQuery("Institucion.findByDocente").setParameter("id", new Integer("13913954")).getResultList();
		return instituciones;
	}	
	
	
	
	
}
