package ec.gob.educacion.ebja.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ec.gob.educacion.ebja.facade.local.ParaleloFacadeLocal;
import ec.gob.educacion.ebja.modelo.zeus.Paralelo;
import ec.gob.educacion.ebja.recursos.Constantes;

@Stateless
public class ParaleloFacade extends AbstractFacade<Paralelo> implements ParaleloFacadeLocal {

    @PersistenceContext(unitName = "zeusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParaleloFacade() {
        super(Paralelo.class);
    }
    
    @SuppressWarnings("unchecked")
	@Override
	public List<Paralelo> findAllActive() {
    	String sql = "";
    	List<Paralelo> listaParalelo = new ArrayList<>();
    	
    	sql = "select p"
				+ " from Paralelo p"
				+ " where p.estado = '1'"
				+ " order by p.descripcion";
    	
    	listaParalelo = em.createQuery(sql).getResultList();
		return listaParalelo;
	}

	@Override
	public List<Paralelo> obtenerParaleloPorInstitucion(String amie, Integer idProgramaEbja) {
		List<Paralelo> listaParalelo = new ArrayList<>();

		StringBuilder sql = new StringBuilder();
		sql.append("select pa from CursoParalelo cp "
		         + "  join cp.paralelo pa "
		         + "  join cp.programaInstitucion pi "
		         + " where (pi.estado = :estado or pi.estado = :estado1) "
		         + "   and pi.institucEstablec.idInstitucion.amie = :amie "
        		 + "   and pi.programaEbja.id = :idProgramaEbja ");
		
		TypedQuery<Paralelo> q = em.createQuery(sql.toString(), Paralelo.class)
			.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO)
			.setParameter("estado1", Constantes.ESTADO_REGISTRO_ACTIVO1)
			.setParameter("amie", amie)
			.setParameter("idProgramaEbja", idProgramaEbja);
		
		listaParalelo = q.getResultList();

		return listaParalelo;
	}
}
