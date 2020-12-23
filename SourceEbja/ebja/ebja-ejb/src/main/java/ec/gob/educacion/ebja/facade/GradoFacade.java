package ec.gob.educacion.ebja.facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ec.gob.educacion.ebja.facade.local.GradoFacadeLocal;
import ec.gob.educacion.ebja.modelo.zeus.Grado;
import ec.gob.educacion.ebja.modelo.zeus.Nivel;
import ec.gob.educacion.ebja.recursos.Constantes;

@Stateless
public class GradoFacade extends AbstractFacade<Grado> implements GradoFacadeLocal {
	
	private static final Logger LOGGER = Logger.getLogger(GradoFacade.class.getName());

    @PersistenceContext(unitName = "zeusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GradoFacade() {
        super(Grado.class);
    }
    
    @SuppressWarnings("unchecked")
	public List<Grado> findAllActive(){
    	return (List<Grado>)em.createNamedQuery("Grado.findAllActive").getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Grado> obtenerGradosPorAmieInstitucionEstado(String amie, List<Character> estadosGrados, Short codigoRegimenAnioLectivo, int mostrarGrado){
    	List<Grado> grados = new ArrayList<Grado>();
    	try {
    		StringBuilder sentencia = new StringBuilder();
    		sentencia.append("select distinct n from Nivel n join fetch n.gradoList g join g.cursoList o ");
			sentencia.append("where o.idInstitucionEstablecimiento.idInstitucion.amie = :amie ");
			sentencia.append("and o.estado in (:estadosGrados) ");
			sentencia.append("and g.estado in (:estadosGrados) ");
			sentencia.append("and g.mostrarGrado = :mostrarGrado ");
			sentencia.append("and o.idRegAnioLectivo.id = :codigoRegimenAnioLectivo ");
			sentencia.append("order by g.id asc ");
			List<Nivel> niveles = em.createQuery(sentencia.toString())
						.setParameter("amie", amie)
						.setParameter("estadosGrados", estadosGrados)
						.setParameter("codigoRegimenAnioLectivo", codigoRegimenAnioLectivo)
						.setParameter("mostrarGrado", mostrarGrado)
						.getResultList();
			
			for (Nivel nivel : niveles) {
				for (Grado grado : nivel.getGradoList()) {
					grados.add(grado);
				}
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			grados = new ArrayList<Grado>();
		}
    	
    	return grados;
    }
    
    @SuppressWarnings("unchecked")
	public List<Grado> obtenerGradosPorAmieInstitucionEstadoConMallaCargada(String amie, List<Character> estadosGrados, Short codigoRegimenAnioLectivo, int mostrarGrado){
    	List<Grado> grados = new ArrayList<Grado>();
    	try {
    		StringBuilder sentencia = new StringBuilder();
    		sentencia.append("select distinct g from Curso o join o.idGrado g ");
			sentencia.append("where o.idInstitucionEstablecimiento.idInstitucion.amie = :amie ");
			sentencia.append("and o.estado in (:estadosGrados) ");
			sentencia.append("and g.estado in (:estadosGrados) ");
			sentencia.append("and g.mostrarGrado = :mostrarGrado ");
			sentencia.append("and o.idRegAnioLectivo.id = :codigoRegimenAnioLectivo ");
			sentencia.append("and o in (select e.idCurso from Esquema e where e.idCurso = o and e.idRegisAnioLectivo = :codigoRegimenAnioLectivo and e.estado = :estado) ");
			sentencia.append("order by g.id asc ");
			grados = em.createQuery(sentencia.toString())
						.setParameter("amie", amie)
						.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO)
						.setParameter("estadosGrados", estadosGrados)
						.setParameter("codigoRegimenAnioLectivo", codigoRegimenAnioLectivo)
						.setParameter("mostrarGrado", mostrarGrado)
						.getResultList();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			grados = new ArrayList<Grado>();
		}
    	
    	return grados;
    }
    
    @SuppressWarnings("unchecked")
	public Set<Grado> obtenerTodosGradosParalelosInstitucionPorRegimenAnioLectivo(Integer codigoInstitucion, List<Character> estados, Short codigoRegAniLec){
    	Set<Grado> grados = new HashSet<>();
    	try {
    		StringBuilder sentencia = new StringBuilder();
    		sentencia.append("select g from Grado g join fetch g.cursoList c join fetch c.paraleloCensoList p ");
			sentencia.append("where  ");
			sentencia.append("c.idInstitucionEstablecimiento.idInstitucion.id = :codigoInstitucion ");
			sentencia.append("and c.estado in (:estados) ");
			sentencia.append("and c.idRegAnioLectivo.id = :codigoRegAniLec ");
			sentencia.append("and p.estado in (:estados) ");
			sentencia.append(" order by g.id asc ");
			grados.addAll(em.createQuery(sentencia.toString())
						.setParameter("codigoInstitucion", codigoInstitucion)
						.setParameter("codigoRegAniLec", codigoRegAniLec)
						.setParameter("estados", estados)
						.getResultList());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			grados = new HashSet<>();
		}
    	return grados;
    }
    
    @SuppressWarnings("unchecked")
	public List<Grado> obtenerTodosGradosParalelosInstitucionPorRegimenAnioLectivoYNiveles(Integer codigoInstitucion, List<Character> estados, Short codigoRegAniLec, List<Nivel> niveles, Short codigoJornada, Short codigoModalidad, Short codigoTipoEducacion, Short codigoEspecialidad){
    	List<Grado> grados = new ArrayList<>();
    	try {
    		StringBuilder sentencia = new StringBuilder();
    		sentencia.append("select distinct g from Grado g join fetch g.cursoList c join fetch c.paraleloCensoList p ");
			sentencia.append("where  ");
			sentencia.append("c.idInstitucionEstablecimiento.idInstitucion.id = :codigoInstitucion ");
			sentencia.append("and c.estado in (:estados) ");
			sentencia.append("and c.idRegAnioLectivo.id = :codigoRegAniLec ");
			sentencia.append("and c.idJornada.id = :codigoJornada ");
			sentencia.append("and c.idModalidad.id = :codigoModalidad ");
			sentencia.append("and c.idTipoEducacion.id = :codigoTipoEducacion ");
			sentencia.append("and p.estado in (:estados) ");
			sentencia.append("and g.idNivel in (:niveles) ");
			
			Query query = em.createQuery(sentencia.toString() + " and c.idEspecialidad is null order by g.id asc ")
					.setParameter("codigoInstitucion", codigoInstitucion)
					.setParameter("codigoRegAniLec", codigoRegAniLec)
					.setParameter("codigoJornada", codigoJornada)
					.setParameter("codigoModalidad", codigoModalidad)
					.setParameter("codigoTipoEducacion", codigoTipoEducacion)
					.setParameter("estados", estados)
					.setParameter("niveles", niveles);
			grados.addAll(query.getResultList());
			
			if(codigoEspecialidad != null) {
				sentencia.append("and c.idEspecialidad.id = :codigoEspecialidad  ");
				sentencia.append(" order by g.id asc ");
				Query queryEspecialidad = em.createQuery(sentencia.toString())
						.setParameter("codigoInstitucion", codigoInstitucion)
						.setParameter("codigoRegAniLec", codigoRegAniLec)
						.setParameter("codigoJornada", codigoJornada)
						.setParameter("codigoModalidad", codigoModalidad)
						.setParameter("codigoTipoEducacion", codigoTipoEducacion)
						.setParameter("estados", estados)
						.setParameter("niveles", niveles)
						.setParameter("codigoEspecialidad", codigoEspecialidad);
				grados.addAll(queryEspecialidad.getResultList());
			}
			Collections.sort(grados, (Grado m1, Grado m2) -> m1.getId().compareTo(m2.getId()));
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			grados = new ArrayList<>();
		}
    	
    	return grados;
    }
    
    @SuppressWarnings("unchecked")
	public Set<Grado> obtenerGradosCursosParalelosPorEstadoRegimenAnioLectivoEstado(Integer codigoInstitucion, List<Character> estados, Short codigoRegAniLec, List<Short> codigosNivelesTutor, int mostrarGrado){
    	Set<Grado> grados = new HashSet<>();
    	try {
			StringBuilder sentencia = new StringBuilder();
    		sentencia.append("select g from Grado g join g.cursoList c  ");
			sentencia.append("where  ");
			sentencia.append("c.idInstitucionEstablecimiento.idInstitucion.id = :codigoInstitucion ");
			sentencia.append("and c.estado in (:estados) ");
			sentencia.append("and c.idRegAnioLectivo.id = :codigoRegAniLec ");
			sentencia.append("and g.idNivel.id in (:codigosNivelesTutor) ");
			sentencia.append("and g.mostrarGrado = :mostrarGrado ");
			sentencia.append(" order by g.id asc ");
			
			grados.addAll(em.createQuery(sentencia.toString())
						.setParameter("codigoInstitucion", codigoInstitucion)
						.setParameter("codigoRegAniLec", codigoRegAniLec)
						.setParameter("estados", estados)
						.setParameter("codigosNivelesTutor", codigosNivelesTutor)
						.setParameter("mostrarGrado", mostrarGrado)
						.getResultList());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			grados = new HashSet<>();
		}
    	return grados;
    }

	@Override
	public List<Grado> obtenerGradosPorNemonicoMateriaEstadoRegimenAnioLectivoInstitucion(String nemonicoMateria,
			Integer codigoInstitucion, Short codigoRegAniLec, List<Character> estadosGrados, int mostrarGrado) {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct g from Grado g join g.cursoList c join g.mallaList m join m.escenarioList es ");
		sql.append("where c.idInstitucionEstablecimiento.idInstitucion.id = :codigoInstitucion ");
		sql.append("and c.idRegAnioLectivo.id = :codigoRegAniLec ");
		sql.append("and es.idAsignatura.idMateria.nemonico = :nemonicoMateria ");
		sql.append("and g.estado in (:estadosGrados) and c.estado in (:estadosGrados) and m.estado = :estado and es.estado = :estado and g.mostrarGrado = :mostrarGrado ");
		TypedQuery<Grado> q = em.createQuery(sql.toString(),Grado.class)
				.setParameter("codigoInstitucion", codigoInstitucion)
				.setParameter("codigoRegAniLec", codigoRegAniLec)
				.setParameter("nemonicoMateria", nemonicoMateria)
				.setParameter("estadosGrados", estadosGrados)
				.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO)
				.setParameter("mostrarGrado", mostrarGrado);
		return q.getResultList();		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Grado> obtenerGradoEbja(){
		String sentencia;
		//sentencia = "select g from Grado g where g.nemonico IN ('1RO DE EGB','2DO DE EGB', '3RO DE EGB', '4TO DE EGB', '5TO DE EGB', '6TO DE EGB', '7MO DE EGB', '8VO DE EGB','9NO DE EGB', '10MO DE EGB')  ";
		
		sentencia = "select g1 from Grado g1 where g1.id=50 or g1.id between 4 and 16   ";
		
		return em.createQuery(sentencia).getResultList();
	}

	@Override
	public Grado obtenerGrado(String nemonico) {
		String sentencia;
		sentencia = "select g from Grado g where g.nemonico = :nem and g.id < 17 ";
		return (Grado) em.createQuery(sentencia).setParameter("nem", nemonico).getSingleResult();
	}
}