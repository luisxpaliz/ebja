package ec.gob.educacion.ebja.facade;

import ec.gob.educacion.ebja.facade.local.ProgramaInstitucionFacadeLocal;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.modelo.ProgramaInstitucion;
import ec.gob.educacion.ebja.modelo.zeus.Paralelo;
import ec.gob.educacion.ebja.recursos.Constantes;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Hibernate;

@Stateless
public class ProgramaInstitucionFacade extends AbstractFacade<ProgramaInstitucion> implements ProgramaInstitucionFacadeLocal {

	private List<ProgramaInstitucion> listaResultado;
	private List<ProgramaEbja> listaPrograma;
	private List<Paralelo> listaParalelo;
	private List<ProgramaInstitucion> listaProgramaInstitucion;
	
	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

    public ProgramaInstitucionFacade() {
        super(ProgramaInstitucion.class);
    }

	@SuppressWarnings({ "unchecked" })
	public List<ProgramaInstitucion> listaProgramaInstitucionPorProgramaEbja(Integer idProgramaEbja, Integer idZona) {
    	short idZona1 = idZona.shortValue();
		List<ProgramaInstitucion> listaProgramaInstitucion = new ArrayList<ProgramaInstitucion>();
		List<ProgramaInstitucion> listaProgramaInstitucionAux = new ArrayList<ProgramaInstitucion>();
		
		try {
			StringBuilder sentencia = new StringBuilder();
			sentencia.append(" select distinct pi ");
			sentencia.append("   from ProgramaInstitucion pi, ");
			sentencia.append("        InstitucEstablec insest, ");
			sentencia.append("        CircuitoParroquia cirpar, ");
			sentencia.append("        Circuito cir, ");
			sentencia.append("        Distrito dis ");
			sentencia.append(" where pi.estado = '1' ");
			sentencia.append("   and pi.institucEstablec.id = insest.id ");
			sentencia.append("   and insest.idCircuitoParroquia.id = cirpar.id ");
			sentencia.append("   and cirpar.idCircuito.id = cir.id ");
			sentencia.append("   and cir.idDistrito.id = dis.id ");
			sentencia.append("   and pi.programaEbja.id = :idProgramaEbja ");
			sentencia.append("   and dis.idZona.id = :idZona ");
			sentencia.append(" order by pi.id asc");

			Query q = em.createQuery(sentencia.toString())
						.setParameter("idProgramaEbja", idProgramaEbja)
						.setParameter("idZona", idZona1);
			listaProgramaInstitucionAux = q.getResultList();
			
			if (!listaProgramaInstitucionAux.isEmpty()) {
				int index = 0;
				for (ProgramaInstitucion programaInstitucionAux : listaProgramaInstitucionAux) {
					programaInstitucionAux = listaProgramaInstitucionAux.get(index);
					Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdCircuitoParroquia().getIdCircuito());
					Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdCircuitoParroquia().getIdCircuito().getIdDistrito());
					Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdCircuitoParroquia().getIdCircuito().getIdDistrito().getIdZona());
					Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdCircuitoParroquia().getIdParroquia());
					Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdInstitucion());
					listaProgramaInstitucion.add(programaInstitucionAux);
					index = index + 1;
				}
			}
			
			return listaProgramaInstitucion;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaProgramaInstitucion;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProgramaInstitucion> findByIdProgramaEbja(Integer idProgramaEbja) {
		List<ProgramaInstitucion> listaProgramaInstitucion = new ArrayList<ProgramaInstitucion>();
		List<ProgramaInstitucion> listaProgramaInstitucionAux = new ArrayList<ProgramaInstitucion>();
		
		listaProgramaInstitucionAux = em.createNamedQuery("ProgramaInstitucion.findByIdProgramaEbja")
							.setParameter("idProgramaEbja", idProgramaEbja).getResultList();

		if (!listaProgramaInstitucionAux.isEmpty()) {
			int index = 0;
			for (ProgramaInstitucion programaInstitucionAux : listaProgramaInstitucionAux) {
				programaInstitucionAux = listaProgramaInstitucionAux.get(index);
				Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdCircuitoParroquia().getIdCircuito().getIdDistrito().getIdZona());
				Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdCircuitoParroquia().getIdParroquia().getIdCanton().getIdProvincia());
				Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdInstitucion());
				Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdEstablecimiento());
				listaProgramaInstitucion.add(programaInstitucionAux);
				index = index + 1;
			}
		}
		
		return listaProgramaInstitucion;
	}

	@Override
	public Long validarExisteProgramaInstitucion(Integer idProgramaEbja, Integer idInstitucEstablec) {
		String sql = "";
		Long resultado = (long) 0;
		
		sql = "select count(pi)"
				+ " from ProgramaInstitucion pi"
				+ " where pi.programaEbja.id = :idProgramaEbja"
				+ " and pi.institucEstablec.id = :idInstitucEstablec"
				+ " and pi.estado = '1'";
		
		resultado = (Long) em.createQuery(sql).setParameter("idProgramaEbja", idProgramaEbja).setParameter("idInstitucEstablec", idInstitucEstablec).getSingleResult();
		
		return resultado;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProgramaInstitucion> findByIdProgramaEbjaAmie(Integer idProgramaEbja, String codigoAmie) {
		String sql = "";
		List<ProgramaInstitucion> listaAux = new ArrayList<>();
		listaResultado = new ArrayList<>();
		
		sql = "select pi"
				+ " from ProgramaInstitucion pi"
				+ " where upper(pi.institucEstablec.idInstitucion.amie) like concat('%',upper(:amie),'%')"
				+ " and pi.programaEbja.id = :idProgramaEbja"
				+ " and pi.estado = '1'";
		
		listaAux = em.createQuery(sql)
				.setParameter("amie", codigoAmie)
				.setParameter("idProgramaEbja", idProgramaEbja)
				.getResultList();
		
		if (!listaAux.isEmpty()) {
			int index = 0;
			for (ProgramaInstitucion programaInstitucionAux : listaAux) {
				programaInstitucionAux = listaAux.get(index);
				Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdCircuitoParroquia().getIdCircuito().getIdDistrito().getIdZona());
				Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdCircuitoParroquia().getIdParroquia().getIdCanton().getIdProvincia());
				Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdInstitucion());
				Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdEstablecimiento());
				listaResultado.add(programaInstitucionAux);
				index++;
			}
		}
		
		return listaResultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProgramaInstitucion> findByIdProgramaEbjaCircuito(Integer idProgramaEbja, Integer idCircuito) {
		String sql = "";
		List<ProgramaInstitucion> listaAux = new ArrayList<>();
		listaResultado = new ArrayList<>();
		
		sql = "select pi "
			+ "  from ProgramaInstitucion pi "
			+ " where pi.estado = '1' "
			+ "   and pi.programaEbja.id = :idProgramaEbja "
			+ "   and pi.institucEstablec.idCircuitoParroquia.idCircuito.id = :idCircuito ";
		
		listaAux = em.createQuery(sql)
					 .setParameter("idCircuito", idCircuito)
					 .setParameter("idProgramaEbja", idProgramaEbja)
					 .getResultList();
		
		if (!listaAux.isEmpty()) {
			int index = 0;
			for (ProgramaInstitucion programaInstitucionAux : listaAux) {
				programaInstitucionAux = listaAux.get(index);
				Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdCircuitoParroquia().getIdCircuito().getIdDistrito().getIdZona());
				Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdCircuitoParroquia().getIdParroquia().getIdCanton().getIdProvincia());
				Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdInstitucion());
				Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdEstablecimiento());
				listaResultado.add(programaInstitucionAux);
				index++;
			}
		}
		
		return listaResultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProgramaInstitucion> findByIdProgramaEbjaParroquia(Integer idProgramaEbja, Short idParroquia) {
		String sql = "";
		List<ProgramaInstitucion> listaAux = new ArrayList<>();
		listaResultado = new ArrayList<>();
		
		sql = "select pi "
			+ "  from ProgramaInstitucion pi "
			+ " where pi.estado = '1' "
			+ "   and pi.programaEbja.id = :idProgramaEbja "
			+ "   and pi.institucEstablec.idCircuitoParroquia.idParroquia.id = :idParroquia ";
		
		listaAux = em.createQuery(sql)
					 .setParameter("idProgramaEbja", idProgramaEbja)
					 .setParameter("idParroquia", idParroquia)
					 .getResultList();
		
		if (!listaAux.isEmpty()) {
			int index = 0;
			for (ProgramaInstitucion programaInstitucionAux : listaAux) {
				programaInstitucionAux = listaAux.get(index);
				Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdCircuitoParroquia().getIdCircuito().getIdDistrito().getIdZona());
				Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdCircuitoParroquia().getIdParroquia().getIdCanton().getIdProvincia());
				Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdInstitucion());
				Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdEstablecimiento());
				listaResultado.add(programaInstitucionAux);
				index++;
			}
		}
		
		return listaResultado;
	}

	@Override
	public List<ProgramaInstitucion> obtenerProgramaInstitucionPorInstitucion(String amie) {
		List<ProgramaInstitucion> listaProgramaInstitucion = new ArrayList<>();
		List<ProgramaInstitucion> listaAux = new ArrayList<>();

		StringBuilder sql = new StringBuilder();
		sql.append("select pi from ProgramaInstitucion pi "
		         + " where (pi.estado = :estado or pi.estado = :estado1) "
		         + "   and pi.institucEstablec.idInstitucion.amie = :amie ");
		
		TypedQuery<ProgramaInstitucion> q = em.createQuery(sql.toString(), ProgramaInstitucion.class)
			.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO)
			.setParameter("estado1", Constantes.ESTADO_REGISTRO_ACTIVO1)
			.setParameter("amie", amie);
		
		listaAux = q.getResultList();
		
		if (!listaAux.isEmpty()) {
			int index = 0;
			for (ProgramaInstitucion programaInstitucionAux : listaAux) {
				programaInstitucionAux = listaAux.get(index);
				Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdCircuitoParroquia().getIdCircuito().getIdDistrito().getIdZona());
				Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdCircuitoParroquia().getIdParroquia().getIdCanton().getIdProvincia());
				Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdInstitucion());
				Hibernate.initialize(programaInstitucionAux.getInstitucEstablec().getIdEstablecimiento());
				listaProgramaInstitucion.add(programaInstitucionAux);
				index++;
			}
		}

		return listaProgramaInstitucion;
	}

	@Override
	public ProgramaInstitucion obtenerProgramaInstitucionPorId(Integer idProgramaInstitucion) {
		String sql = "";
		ProgramaInstitucion programaInstitucion = new ProgramaInstitucion();
		
		sql = "select pi "
			+ "  from ProgramaInstitucion pi "
			+ " where pi.estado = '1' "
			+ "   and pi.id = :idProgramaInstitucion ";
		
		programaInstitucion = (ProgramaInstitucion) em.createQuery(sql)
													.setParameter("idProgramaInstitucion", idProgramaInstitucion)
													.getSingleResult();
		Hibernate.initialize(programaInstitucion.getInstitucEstablec().getIdCircuitoParroquia().getIdCircuito().getIdDistrito().getIdZona());
		Hibernate.initialize(programaInstitucion.getInstitucEstablec().getIdCircuitoParroquia().getIdParroquia().getIdCanton().getIdProvincia());
		Hibernate.initialize(programaInstitucion.getInstitucEstablec().getIdInstitucion());
		Hibernate.initialize(programaInstitucion.getInstitucEstablec().getIdEstablecimiento());
		
		return programaInstitucion;
	}

	@Override
	public List<ProgramaEbja> buscarProgramaEbjaInstitucion(String amie) {
		String sql = "SELECT pi.programaEbja FROM ProgramaInstitucion pi where pi.institucEstablec.idInstitucion.amie = :amie";
		listaPrograma = em.createQuery(sql).setParameter("amie", amie).getResultList();
		return listaPrograma;
	}
	
	@Override
	public List<Paralelo> buscarParaleloInstitucion (String amie, String nemonico) {
		String sql = "Select p from Paralelo p where p.id in ( Select cp.paralelo.id from CursoParalelo cp where cp.programaInstitucion.id in (SELECT pi.id FROM ProgramaInstitucion pi where pi.institucEstablec.idInstitucion.amie = :amie and pi.programaEbja.nemonico = :nemonico)))";
		listaParalelo =  em.createQuery(sql).setParameter("amie", amie).setParameter("nemonico", nemonico).getResultList();
		return listaParalelo; 
	}
	
	@Override
	public List<ProgramaInstitucion> buscarProgramaInstitucion(String amie, String nemonico) {
		String sql = "SELECT pi FROM ProgramaInstitucion pi where pi.institucEstablec.idInstitucion.amie = :amie and pi.programaEbja.nemonico = :nemonico";
		listaProgramaInstitucion = em.createQuery(sql).setParameter("amie", amie).setParameter("nemonico", nemonico).getResultList();
		return listaProgramaInstitucion;
	}
	
	
	
	
	

}
