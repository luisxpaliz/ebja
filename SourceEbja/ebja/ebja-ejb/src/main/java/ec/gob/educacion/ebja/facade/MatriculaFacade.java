package ec.gob.educacion.ebja.facade;

import ec.gob.educacion.ebja.dto.TotalesMatriculaDTO;
import ec.gob.educacion.ebja.facade.local.MatriculaFacadeLocal;
import ec.gob.educacion.ebja.modelo.Matricula;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Hibernate;

@Stateless
public class MatriculaFacade extends AbstractFacade<Matricula> implements MatriculaFacadeLocal {
	@Resource(mappedName = "java:jboss/datasources/zeusDS")
	private DataSource dataSource;

	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

    public MatriculaFacade() {
        super(Matricula.class);
    }
    
    @SuppressWarnings("unchecked")
	public List<Object[]> obtenerEstudiantesParaMatricula(Integer idProgramaEbja, Integer idZona) {
    	short idZona1 = idZona.shortValue();
		List<Object[]> listaEstudiantesParaMatricula = new ArrayList<Object[]>();
    	
		try {
			String sql = 
					  " select distinct ins, ubi, res "
					+ "   from Inscripcion ins, "
					+ "        Ubicacion ubi, "
					+ "        RegistroEstudiante res, "
					+ "        Circuito cir, "
					+ "        Distrito dis, "
					+ "        ProgramaInstitucion proins, "
					+ "        InstitucEstablec insest, "
					+ "        CircuitoParroquia cirpar "
					+ "  where ins.estado = '1' "
					+ "    and res.estadoAsignacion = '0' "
					+ "    and ins.id = ubi.inscripcion.id "
					+ "    and ins.id = res.inscripcion.id "
					+ "    and cir.id = ubi.circuito.id "
					+ "    and dis.id = cir.idDistrito.id "
					+ "    and ins.programaEbja.id = proins.programaEbja.id "
					+ "    and proins.institucEstablec.id = insest.id "
					+ "    and insest.idCircuitoParroquia.id = cirpar.id "
					+ "    and cirpar.idCircuito.id = ubi.circuito.id "
					//+ "    and cirpar.idParroquia.id = ubi.parroquia.id "
					+ "    and ubi.parroquia <> null "
					+ "    and ubi.circuito <> null "
					+ "    and ins.programaEbja.id = :idProgramaEbja "
					+ "    and dis.idZona.id = :idZona "
					+ "  order by res.apellidosNombres ";
			Query query = em.createQuery(sql)
							.setParameter("idProgramaEbja", idProgramaEbja)
							.setParameter("idZona", idZona1);
			listaEstudiantesParaMatricula = query.getResultList();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return listaEstudiantesParaMatricula;
    }
    
	public Integer cantidadEstudiantesParaMatricula(Integer idProgramaEbja, Integer idZona) {
    	Long cantidadEstudiantesParaMatricula = null;
    	short idZona1 = idZona.shortValue();
		try {
			String sql = 
					  " select count(distinct ins.id) as catidad "
					+ "   from Inscripcion ins, "
					+ "        Ubicacion ubi, "
					+ "        RegistroEstudiante res, "
					+ "        Circuito cir, "
					+ "        Distrito dis, "
					+ "        ProgramaInstitucion proins, "
					+ "        InstitucEstablec insest, "
					+ "        CircuitoParroquia cirpar "
					+ "  where ins.estado = '1' "
					+ "    and res.estadoAsignacion = '0' "
					+ "    and ins.id = ubi.inscripcion.id "
					+ "    and ins.id = res.inscripcion.id "
					+ "    and cir.id = ubi.circuito.id "
					+ "    and dis.id = cir.idDistrito.id "
					+ "    and ins.programaEbja.id = proins.programaEbja.id "
					+ "    and proins.institucEstablec.id = insest.id "
					+ "    and insest.idCircuitoParroquia.id = cirpar.id "
					+ "    and cirpar.idCircuito.id = ubi.circuito.id "
					//+ "    and cirpar.idParroquia.id = ubi.parroquia.id "
					+ "    and ubi.parroquia <> null "
					+ "    and ubi.circuito <> null "
					+ "    and ins.programaEbja.id = :idProgramaEbja "
					+ "    and dis.idZona.id = :idZona ";
			Query query = em.createQuery(sql)
							.setParameter("idProgramaEbja", idProgramaEbja)
							.setParameter("idZona", idZona1);
			cantidadEstudiantesParaMatricula = (Long) query.getSingleResult();
			return Integer.parseInt(cantidadEstudiantesParaMatricula.toString());
		} catch (Exception exc) {
			exc.printStackTrace();
			return 0;
		}
		
    }
	
	@SuppressWarnings("unused")
	public TotalesMatriculaDTO procesoMatriculaAutomaticaFuncion(Integer idProgramaEbja, Integer idZona, Integer idUsuarioCreacion, String ipUsuario) {
        try {
            Connection connection = dataSource.getConnection();
            // Procedure call
            CallableStatement cstmt = connection.prepareCall("{call ebja.fu_asignacion_automatica(?, ?, ?, ?, ?, ?, ?, ?)}");

            cstmt.setInt(1, idProgramaEbja);
            cstmt.setInt(2, idZona);
            cstmt.setInt(3, idUsuarioCreacion);
            cstmt.setString(4, ipUsuario);
            cstmt.registerOutParameter(5, Types.INTEGER);
            cstmt.registerOutParameter(6, Types.INTEGER);
            cstmt.registerOutParameter(7, Types.INTEGER);
            cstmt.registerOutParameter(8, Types.INTEGER);

            boolean b = cstmt.execute();
            
            TotalesMatriculaDTO totalesMatriculaDTO = new TotalesMatriculaDTO();
            totalesMatriculaDTO.setNumInscrito(cstmt.getInt(5));
            totalesMatriculaDTO.setNumEstudiante(cstmt.getInt(6));
            totalesMatriculaDTO.setNumMatricula(cstmt.getInt(7));
            totalesMatriculaDTO.setNumNoMatricula(cstmt.getInt(8));
            
            cstmt.close();

            return totalesMatriculaDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    @SuppressWarnings("unchecked")
	public List<Object[]> obtenerEstudiantesParaMatriculaSU(Integer idProgramaEbja, Integer idZona) {
    	short idZona1 = idZona.shortValue();
		List<Object[]> listaEstudiantesParaMatricula = new ArrayList<Object[]>();
		List<Object[]> listaEstudiantesParaMatriculaAux = new ArrayList<Object[]>();
    	
		try {
			//Lista de aspirantes con DA.
			String sql = 
					  " select distinct ins, ubi, res "
					+ "   from Inscripcion ins, "
					+ "        Ubicacion ubi, "
					+ "        RegistroEstudiante res, "
					+ "        Circuito cir, "
					+ "        Distrito dis, "
					+ "		   Zona zon, "
					+ "        ProgramaInstitucion proins, "
					+ "        InstitucEstablec insest, "
					+ "        CircuitoParroquia cirpar "
					+ "  where ins.estado = '1' "
					+ "    and res.estadoAsignacion = '0' "
					+ "    and res.insObservacion is null "
					+ "    and ins.id = ubi.inscripcion.id "
					+ "    and ins.id = res.inscripcion.id "
					+ "    and cir.id = ubi.circuito.id "
					+ "    and dis.id = cir.idDistrito.id "
					+ "    and ins.programaEbja.id = proins.programaEbja.id "
					+ "    and proins.institucEstablec.id = insest.id "
					+ "    and insest.idCircuitoParroquia.id = cirpar.id "
					+ "    and cirpar.idCircuito.id = ubi.circuito.id "
					//+ "    and cirpar.idParroquia.id = ubi.parroquia.id "
					+ "    and ubi.parroquia <> null "
					+ "    and ubi.circuito <> null "
					+ "    and ins.programaEbja.id = :idProgramaEbja "
					+ "    and dis.idZona.id = :idZona "
					+ "  order by res.apellidosNombres ";
			Query query = em.createQuery(sql)
							.setParameter("idProgramaEbja", idProgramaEbja)
							.setParameter("idZona", idZona1);
			listaEstudiantesParaMatricula = query.getResultList();
			
			//Lista de aspirantes sin DA.
			String sqlAux = 
					  " select distinct ins, ubi, res "
					+ "   from Inscripcion ins, "
					+ "        Ubicacion ubi, "
					+ "        RegistroEstudiante res "
					+ "  where ins.estado = '1' "
					+ "    and res.estadoAsignacion = '0' "
					+ "    and ins.id = ubi.inscripcion.id "
					+ "    and ins.id = res.inscripcion.id "
					+ "    and ins.programaEbja.id = :idProgramaEbja "
					+ "    and ubi.parroquia is null "
					+ "    and ubi.circuito is null ";
			Query queryAux = em.createQuery(sqlAux)
							   .setParameter("idProgramaEbja", idProgramaEbja);
			listaEstudiantesParaMatriculaAux = queryAux.getResultList();

			if (!listaEstudiantesParaMatriculaAux.isEmpty()) {
				for (Object[] object : listaEstudiantesParaMatriculaAux) {
					listaEstudiantesParaMatricula.add(object);
				}
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return listaEstudiantesParaMatricula;
    }
    
    @SuppressWarnings("unchecked")
	public List<Object[]> listaMatriculadosPorOfertaYZona(Integer idProgramaEbja, Integer idZona) {
    	short idZona1 = idZona.shortValue();
		List<Object[]> listaEstudiantesParaMatricula = new ArrayList<Object[]>();
		List<Object[]> listaEstudiantesParaMatriculaAux = new ArrayList<Object[]>();
    	
		try {
			//Lista de aspirantes con DA.
			String sql = 
					  " select distinct ins, ubi, res "
					+ "   from Inscripcion ins, "
					+ "        Ubicacion ubi, "
					+ "        RegistroEstudiante res, "
					+ "        Circuito cir, "
					+ "        Distrito dis, "
					+ "		   Zona zon, "
					+ "        ProgramaInstitucion proins, "
					+ "        InstitucEstablec insest, "
					+ "        CircuitoParroquia cirpar "
					+ "  where ins.estado = '1' "
					+ "    and res.estadoAsignacion = '1' "
					+ "    and res.insObservacion is null "
					+ "    and ins.id = ubi.inscripcion.id "
					+ "    and ins.id = res.inscripcion.id "
					+ "    and cir.id = ubi.circuito.id "
					+ "    and dis.id = cir.idDistrito.id "
					+ "    and ins.programaEbja.id = proins.programaEbja.id "
					+ "    and proins.institucEstablec.id = insest.id "
					+ "    and insest.idCircuitoParroquia.id = cirpar.id "
					+ "    and cirpar.idCircuito.id = ubi.circuito.id "
					//+ "    and cirpar.idParroquia.id = ubi.parroquia.id "
					+ "    and ubi.parroquia <> null "
					+ "    and ubi.circuito <> null "
					+ "    and ins.programaEbja.id = :idProgramaEbja "
					+ "    and dis.idZona.id = :idZona "
					+ "  order by res.apellidosNombres ";
			Query query = em.createQuery(sql)
							.setParameter("idProgramaEbja", idProgramaEbja)
							.setParameter("idZona", idZona1);
			listaEstudiantesParaMatricula = query.getResultList();
			
			//Lista de aspirantes sin DA.
			String sqlAux = 
					  " select distinct ins, ubi, res "
					+ "   from Inscripcion ins, "
					+ "        Ubicacion ubi, "
					+ "        RegistroEstudiante res "
					+ "  where ins.estado = '1' "
					+ "    and res.estadoAsignacion = '1' "
					+ "    and ins.id = ubi.inscripcion.id "
					+ "    and ins.id = res.inscripcion.id "
					+ "    and ins.programaEbja.id = :idProgramaEbja "
					+ "    and ubi.parroquia is null "
					+ "    and ubi.circuito is null ";
			Query queryAux = em.createQuery(sqlAux)
							   .setParameter("idProgramaEbja", idProgramaEbja);
			listaEstudiantesParaMatriculaAux = queryAux.getResultList();

			if (!listaEstudiantesParaMatriculaAux.isEmpty()) {
				for (Object[] object : listaEstudiantesParaMatriculaAux) {
					listaEstudiantesParaMatricula.add(object);
				}
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return listaEstudiantesParaMatricula;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> obtenerEstudiantesAsignarParalelo(Integer idCursoParalelo, Integer idParalelo) {
		List<Matricula> listaMatricula = new ArrayList<>();

		String sql = 
				  " select mat "
				+ "   from ProgramaInstitucion pi, "
				+ "        CursoParalelo cp, "
				+ "        Paralelo par, "
				+ "        Matricula mat, "
				+ "        Estudiante est, "
				+ "		   RegistroEstudiante re "
				//+ "  where mat.estado = '1' "
				+ "  where re.estado = '1' "
				+ "    and cp.estado = '1' "
				+ "    and re.insObservacion is null "
				+ "    and cp.programaInstitucion.id = pi.id "
				+ "    and cp.paralelo.id = par.id "
				+ "    and mat.programaInstitucion.id = pi.id "
				+ "    and mat.estudiante.id = est.id "
				+ "    and est.registroEstudiante.id = re.id "
				+ "    and (mat.cursoParalelo.id = :idCursoParalelo or mat.cursoParalelo.id is null) "
				+ "    and par.id = :idParalelo "
				+ "  order by re.apellidosNombres";
		Query query = em.createQuery(sql)
						.setParameter("idCursoParalelo", idCursoParalelo)
						.setParameter("idParalelo", idParalelo);
		listaMatricula = query.getResultList();

		return listaMatricula;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Matricula> obtenerEstudiantesInstitucion(String amie) {
		List<Matricula> listaMatricula = new ArrayList<>();

		String sql = 
				  " select distinct mat "
				+ "   from ProgramaInstitucion pi, "
				+ "        CursoParalelo cp, "
				+ "        Paralelo par, "
				+ "        Matricula mat, "
				+ "        Estudiante est, "
				+ "		   RegistroEstudiante re "
				+ "  where mat.estado = '1' "
				+ "    and cp.programaInstitucion.id = pi.id "
				+ "    and cp.paralelo.id = par.id "
				+ "    and mat.programaInstitucion.id = pi.id "
				+ "    and mat.estudiante.id = est.id "
				+ "    and est.registroEstudiante.id = re.id "
				+ "    and pi.institucEstablec.idInstitucion.amie = :amie "
				+ "  order by mat.cursoParalelo.id ";
		Query query = em.createQuery(sql)
						.setParameter("amie", amie);
		listaMatricula = query.getResultList();

		return listaMatricula;
	}

	public Matricula obtenerEstudianteParaTraslado(String numeroIdentificacion, String apellidosNombres) {
		Matricula matricula = new Matricula();
		try {
			String sql = 
					  " select distinct mat "
					+ "   from ProgramaInstitucion pi, "
					+ "        CursoParalelo cp, "
					+ "        Paralelo par, "
					+ "        Matricula mat, "
					+ "        Estudiante est, "
					+ "		   RegistroEstudiante re "
					+ "  where mat.estado = '1' "
//					+ "    and mat.cursoParalelo <> null "
					+ "    and cp.programaInstitucion.id = pi.id "
					+ "    and cp.paralelo.id = par.id "
					+ "    and mat.programaInstitucion.id = pi.id "
					+ "    and mat.estudiante.id = est.id "
					+ "    and est.registroEstudiante.id = re.id "
					+ "    and "
					+ "      ( re.numeroIdentificacion = :numeroIdentificacion "
					+ "     or "
					+ "        re.apellidosNombres = :apellidosNombres ) "
					+ "  order by mat.cursoParalelo.id ";
			Query query = em.createQuery(sql)
							.setParameter("numeroIdentificacion", numeroIdentificacion)
							.setParameter("apellidosNombres", apellidosNombres);
			matricula = (Matricula) query.getSingleResult();
			
			//Reinicializar objetos, para poder utilizarlos.
			Hibernate.initialize(matricula.getEstudiante().getInscripcion().getProgramaGrado().getProgramaEbja());
			Hibernate.initialize(matricula.getEstudiante().getRegistroEstudiante());
			Hibernate.initialize(matricula.getEstudiante().getRegistroEstudiante().getCatalogoDocumento());
			Hibernate.initialize(matricula.getCursoParalelo());
			Hibernate.initialize(matricula.getProgramaInstitucion().getInstitucEstablec().getIdInstitucion());
		} catch (Exception e) {
			matricula = null;
		}

		return matricula;
	}

	public Matricula obtenerAsignacion(String numeroIdentificacion, String apellidosNombres) {
		Matricula matricula = new Matricula();
		try {
			String sql = 
					  " select distinct mat "
					+ "   from ProgramaInstitucion pi, "
					+ "        Matricula mat, "
					+ "        Estudiante est, "
					+ "		   RegistroEstudiante re "
					+ "  where mat.estado = '1' "
					+ "    and mat.programaInstitucion.id = pi.id "
					+ "    and mat.estudiante.id = est.id "
					+ "    and est.registroEstudiante.id = re.id "
					+ "    and "
					+ "      ( re.numeroIdentificacion = :numeroIdentificacion "
					+ "     or "
					+ "        re.apellidosNombres = :apellidosNombres ) "
					+ "  order by mat.cursoParalelo.id ";
			Query query = em.createQuery(sql)
							.setParameter("numeroIdentificacion", numeroIdentificacion)
							.setParameter("apellidosNombres", apellidosNombres);
			matricula = (Matricula) query.getSingleResult();
			
			//Reinicializar objetos, para poder utilizarlos.
			Hibernate.initialize(matricula.getEstudiante().getInscripcion().getProgramaGrado().getProgramaEbja());
			Hibernate.initialize(matricula.getEstudiante().getRegistroEstudiante());
			Hibernate.initialize(matricula.getEstudiante().getRegistroEstudiante().getCatalogoDocumento());
			Hibernate.initialize(matricula.getProgramaInstitucion().getInstitucEstablec().getIdInstitucion());
		} catch (Exception e) {
			matricula = null;
		}

		return matricula;
	}
}
