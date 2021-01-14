package ec.gob.educacion.ebja.facade;



import ec.gob.educacion.ebja.dto.Persona;
import ec.gob.educacion.ebja.facade.local.CeduladoMeducacionFacadeLocal;
import ec.gob.educacion.ebja.modelo.asignaciones.CeduladoMeducacion;
import ec.gob.educacion.ebja.modelo.asignaciones.Nacionalidad;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;

@SuppressWarnings("deprecation")
@Stateless
public class CeduladoMeducacionFacade extends AbstractFacade<CeduladoMeducacion>
		implements CeduladoMeducacionFacadeLocal {

	@PersistenceContext(unitName = "asignacionesPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public CeduladoMeducacionFacade() {
		super(CeduladoMeducacion.class);
	}

	@SuppressWarnings("unchecked")
	public List<CeduladoMeducacion> findPersonaByCedula(String cedula) {
		String sql = "select cm from CeduladoMeducacion cm where cm.cedula = :cedula";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("cedula", new BigDecimal(cedula));

		return query.getResultList();
	}

	public CeduladoMeducacion findCeduladoMeducacion(String cedula) {
		try {
			String sql = "select cm from CeduladoMeducacion cm where cm.cedula = :cedula";
			Query query = getEntityManager().createQuery(sql);
			query.setParameter("cedula", new BigDecimal(cedula));
			return (CeduladoMeducacion) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public String findPersona(String cedula) {
		String sql = "select cm.cedula, cm.nombres, cm.cod_Sexo, cm.cod_Condicion_Cedulado,        sx.des_Sexo, cm.COD_LUGAR_NACIMIENTO, cc.des_Condicion_Cedulado, cm.fecha_Nacimiento,       lc.des_Localidad, na.des_Nacionalidad, ec.des_Estado_Civil, cm.nombre_Conyuge,        ins.des_Instruccion,pr.des_Profesion,cm.fecha_Expedicion_Ced,       cm.fecha_Matrimonio, cm.cod_Nacionalidad, cm.cod_Estado_Civil, cm.cod_Instruccion,        cm.cod_Profesion, cm.cod_Lugar_Matrimonio, cm.fecha_Fallecimiento,\t     cm.cod_Domicilio, cm.nombre_Calle, cm.numero_Casa, cm.nombre_Padre, cm.nombre_Madre,        ld.des_Localidad AS localid   from MV_CEDULADO_MEDUCACION cm\t       LEFT JOIN SEXO sx ON cm.COD_SEXO=sx.COD_SEXO        LEFT JOIN CONDICION_CEDULADO cc ON cm.COD_CONDICION_CEDULADO =cc.COD_CONDICION_CEDULADO       LEFT JOIN LOCALIDAD lc ON cm.COD_LUGAR_NACIMIENTO=lc.COD_LOCALIDAD        LEFT JOIN NACIONALIDAD na ON cm.COD_NACIONALIDAD=na.COD_NACIONALIDAD        LEFT JOIN ESTADO_CIVIL ec ON cm.COD_ESTADO_CIVIL=ec.COD_ESTADO_CIVIL        LEFT JOIN INSTRUCCION ins ON cm.COD_INSTRUCCION=ins.COD_INSTRUCCION        LEFT JOIN PROFESION pr ON cm.COD_PROFESION=pr.COD_PROFESION        LEFT JOIN LOCALIDAD ld ON cm.COD_DOMICILIO=ld.COD_LOCALIDAD  where cm.cedula = :cedula   and cm.COD_CONDICION_CEDULADO not in (8,7,20) ";

		Query query = getEntityManager().createNativeQuery(sql);
		query.setParameter("cedula", new BigDecimal(cedula));
		List<Object[]> listaPersonas = query.getResultList();
		return tranformarXML(transformarPersona(listaPersonas));
	}

	public Nacionalidad buscarNacionalidadPorCodigo(BigDecimal codNacionalidad) {
		try {
			String sql = "select n from Nacionalidad n where n.codNacionalidad = :codNacionalidad ";
			Query query = getEntityManager().createQuery(sql);
			query.setParameter("codNacionalidad", codNacionalidad);
			return (Nacionalidad) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public Persona transformarPersona(List<Object[]> listaPersonas) {
		Persona persona = new Persona();
		for (Object[] objects : listaPersonas) {
			persona.setCedula(String.valueOf(objects[0]));
			persona.setNombre(String.valueOf(objects[1]));
			persona.setCodigoGenero(String.valueOf(objects[2]));
			persona.setCodigoCondicionCedulado(String.valueOf(objects[3]));
			persona.setGenero(String.valueOf(objects[4]));
			persona.setCodigoLugarNacimiento(String.valueOf(objects[5]));
			persona.setCondicionCedulado(String.valueOf(objects[6]));
			persona.setFechaNacimiento(String.valueOf(objects[7]));
			persona.setFechaNacimiento(persona.getFechaNacimiento().replace("-", "/"));
			String anio = persona.getFechaNacimiento().substring(0, 4);
			String dia = persona.getFechaNacimiento().substring(8, 10);
			String mes = persona.getFechaNacimiento().substring(5, 7);
			persona.setFechaNacimiento(dia + "/" + mes + "/" + anio);

			persona.setLugarNacimiento(String.valueOf(objects[8]));
			persona.setNacionalidad(String.valueOf(objects[9]));
			persona.setEstadoCivil(String.valueOf(objects[10]));
			persona.setConyuge(String.valueOf(objects[11]));
			persona.setInstruccion(String.valueOf(objects[12]));
			persona.setProfesion(String.valueOf(objects[13]));
			persona.setFechaCedulacion(String.valueOf(objects[14]));

			persona.setFechaMatrimonio(String.valueOf(objects[15]));
			persona.setCodigoNacionalidad(String.valueOf(objects[16]));
			persona.setCodigoEstadoCivil(String.valueOf(objects[17]));
			persona.setCodigoInstruccion(String.valueOf(objects[18]));
			persona.setCodigoProfesion(String.valueOf(objects[19]));
			persona.setCodigoLugarMatrimonio(String.valueOf(objects[20]));
			persona.setFechaDefuncion(String.valueOf(objects[21]));

			persona.setCodigoLugarDomicilio(String.valueOf(objects[22]));
			persona.setCallesDomicilio(String.valueOf(objects[23]));
			persona.setNumeroCasa(String.valueOf(objects[24]));
			persona.setNombrePadre(String.valueOf(objects[25]));
			persona.setNombreMadre(String.valueOf(objects[26]));
			persona.setLugarDomicilio(String.valueOf(objects[27]));
			persona.setLugarMatrimonio("null");
		}
		return persona;
	}

	public String tranformarXML(Persona persona) {
		XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
		XStream stream = new XStream(new DomDriver("UTF-8", replacer));
		stream.autodetectAnnotations(true);
		stream.alias("ROW", Persona.class);
		stream.processAnnotations(Persona.class);
		String xml = stream.toXML(persona);

		return xml;
	}
}
