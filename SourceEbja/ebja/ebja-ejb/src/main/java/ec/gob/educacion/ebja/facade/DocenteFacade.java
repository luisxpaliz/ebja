package ec.gob.educacion.ebja.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;

import ec.gob.educacion.ebja.facade.local.DocenteFacadeLocal;
import ec.gob.educacion.ebja.modelo.zeus.Docente;

@Stateless
public class DocenteFacade extends AbstractFacade<Docente> implements DocenteFacadeLocal {

	private List<Docente> listaResultado;
	
    @PersistenceContext(unitName = "zeusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocenteFacade() {
        super(Docente.class);
    }
    
    @SuppressWarnings("unchecked")
	@Override
	public List<Docente> findAllActive() {
    	String sql = "";
    	List<Docente> listaDocente = new ArrayList<>();
    	
    	sql = "select d"
				+ " from Docente d"
				+ " where d.estado = '1'"
				+ " order by d.persona.nombresApellidos";
    	
    	listaDocente = em.createQuery(sql).getResultList();
		return listaDocente;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Docente> docenteFindByIdentificacion(String identificacion) {
		String sql = "";
		List<Docente> listaAux = new ArrayList<>();
		listaResultado = new ArrayList<>();
		
		sql = "select d"
				+ " from Docente d"
				+ " where upper(d.persona.numeroIdentificacion) = upper(:identificacion)"
				+ " and d.estado = '1'"
				+ " and d.idEstadoDocente = 1";
		
		listaAux = em.createQuery(sql).setParameter("identificacion", identificacion).getResultList();
		
		if (!listaAux.isEmpty()) {
			int index = 0;
			for (Docente docenteAux : listaAux) {
				docenteAux = listaAux.get(index);
				Hibernate.initialize(docenteAux.getPersona());
				listaResultado.add(docenteAux);
				index++;
			}
		}
		
		return listaResultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Docente> docenteFindByNombre(String nombreApellido) {
		String sql = "";
		List<Docente> listaAux = new ArrayList<>();
		listaResultado = new ArrayList<>();
		nombreApellido = nombreApellido.replaceAll("\\s", "%");
		
		sql = "select d"
				+ " from Docente d"
				+ " where upper(d.persona.nombresApellidos) like concat('%',upper(:nombreApellido),'%')"
				+ " and d.estado = '1'"
				+ " and d.idEstadoDocente = 1";
		
		listaAux = em.createQuery(sql).setParameter("nombreApellido", nombreApellido).getResultList();
		
		if (!listaAux.isEmpty()) {
			int index = 0;
			for (Docente docenteAux : listaAux) {
				docenteAux = listaAux.get(index);
				Hibernate.initialize(docenteAux.getPersona());
				listaResultado.add(docenteAux);
				index++;
			}
		}
		
		return listaResultado;
	}
    
}
