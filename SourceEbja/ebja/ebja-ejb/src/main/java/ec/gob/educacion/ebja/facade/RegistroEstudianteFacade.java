package ec.gob.educacion.ebja.facade;

import ec.gob.educacion.ebja.facade.local.RegistroEstudianteFacadeLocal;
import ec.gob.educacion.ebja.modelo.RegistroEstudiante;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class RegistroEstudianteFacade extends AbstractFacade<RegistroEstudiante>
		implements RegistroEstudianteFacadeLocal {

	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

    public RegistroEstudianteFacade() {
        super(RegistroEstudiante.class);
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RegistroEstudiante getNombreCedula(String nomCedula) {
		List<RegistroEstudiante> listaOfertasEducativas = new ArrayList();
		try {
			String script = "Select a From RegistroEstudiante a Where a.numeroIdentificacion =:nomCedula";
			StringBuilder sentencia = new StringBuilder().append(script);
			Query query = em.createQuery(sentencia.toString());
			query.setParameter("nomCedula", nomCedula);

			listaOfertasEducativas = query.getResultList();
			if (listaOfertasEducativas.size() > 0) {
				return (RegistroEstudiante) listaOfertasEducativas.get(0);
			}
			return null;
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RegistroEstudiante getApellidosNombres(String apellidosNombres) {
		List<RegistroEstudiante> listaOfertasEducativas = new ArrayList();
		try {
			String script = "Select a From RegistroEstudiante a Where a.apellidosNombres =:apellidosNombres";
			StringBuilder sentencia = new StringBuilder().append(script);
			Query query = em.createQuery(sentencia.toString());
			query.setParameter("apellidosNombres", apellidosNombres);

			listaOfertasEducativas = query.getResultList();
			if (listaOfertasEducativas.size() > 0) {
				return (RegistroEstudiante) listaOfertasEducativas.get(0);
			}
			return null;
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return null;
	}

	public RegistroEstudiante buscarInscripcionPendiente(String numeroIdentificacion, String apellidosNombres) {
		RegistroEstudiante registroEstudiante = new RegistroEstudiante();
		try {
			String script = "select re From RegistroEstudiante re join re.inscripcion i join i.programaGrado pg join pg.programaEbja pe where re.estado='1' and pe.estado='1' ";
			
			if (numeroIdentificacion.equals("")) {
				script += " and re.apellidosNombres = :apellidosNombres ";
			} else {
				script += " and re.numeroIdentificacion = :numeroIdentificacion ";
			}
			
			StringBuilder sentencia = new StringBuilder().append(script);
			Query query = em.createQuery(sentencia.toString());
			
			if (numeroIdentificacion.equals("")) {
				query.setParameter("apellidosNombres", apellidosNombres);
			} else {
				query.setParameter("numeroIdentificacion", numeroIdentificacion);
			}
			List<RegistroEstudiante> lista =  query.getResultList();
			
			
			if(lista.isEmpty()) {
			registroEstudiante = null;
			}else {
				registroEstudiante = lista.get(0);
			}
			
			
		} catch (NoResultException e) {
			e.printStackTrace();
			registroEstudiante = null;
		}
		
		return registroEstudiante;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<RegistroEstudiante> getRegistroEstudiantePorFiltros(String numeroIdentificacion,
			String apellidosNombres, Date fechaNacimiento, Integer idTipoIdentificacion, String nombreTipoDocumento) {
		try {
			String script = "select re From RegistroEstudiante re join re.inscripcion i join i.programaGrado pg join pg.programaEbja pe where re.estado='1' and pe.estado='1'  "
						+   "   and re.catalogoTipoIdentificacion.id = :idTipoIdentificacion and ";
			Query query = null;
			switch (nombreTipoDocumento) {
			case "C":
				script = script + " re.numeroIdentificacion = :numeroIdentificacion ";
				query = em.createQuery(script);
				query.setParameter("idTipoIdentificacion", idTipoIdentificacion);
				query.setParameter("numeroIdentificacion", numeroIdentificacion);
				break;
			case "P":
			case "R":
				script = script + " re.numeroIdentificacion = :numeroIdentificacion ";
				query = em.createQuery(script);
				query.setParameter("idTipoIdentificacion", idTipoIdentificacion);
				query.setParameter("numeroIdentificacion", numeroIdentificacion);
				break;
			case "N":
				script = script + " re.apellidosNombres =:apellidosNombres ";
				query = em.createQuery(script);
				query.setParameter("idTipoIdentificacion", idTipoIdentificacion);
				query.setParameter("apellidosNombres", apellidosNombres);
			}

			return query.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return new ArrayList();
	}
	
	public boolean existeRegistroEstudiantePorFiltros(String numeroIdentificacion, String apellidosNombres, Date fechaNacimiento, Integer itTipoIdentificacion, String nombreTipoDocumento)
	  {
	    return !getRegistroEstudiantePorFiltros(numeroIdentificacion, apellidosNombres, fechaNacimiento, itTipoIdentificacion, nombreTipoDocumento).isEmpty();
	  }	
}
