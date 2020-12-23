package ec.gob.educacion.ebja.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;

import ec.gob.educacion.ebja.facade.local.CatalogoFacadeLocal;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.modelo.TipoPrograma;
import ec.gob.educacion.ebja.modelo.zeus.Area;
import ec.gob.educacion.ebja.modelo.zeus.Catalogo;
import ec.gob.educacion.ebja.modelo.zeus.Etnia;
import ec.gob.educacion.ebja.modelo.zeus.Grado;
import ec.gob.educacion.ebja.modelo.zeus.Materia;
import ec.gob.educacion.ebja.recursos.Constantes;
import ec.gob.educacion.ebja.modelo.Pais;

@Stateless
public class CatalogoFacade extends AbstractFacade<Catalogo> implements CatalogoFacadeLocal {

	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CatalogoFacade() {
		super(Catalogo.class);
	}

	@Override
	public List<Catalogo> obtenerCatalogosPorTipoCatalogo(String nemonicoTipoCatalogo) {
		StringBuilder sql = new StringBuilder();
		sql.append("select c from Catalogo c ");
		sql.append(" where (c.estado = :estado or c.estado = :estado1) ");
		sql.append("   and  c.idTipoCatalogo.nemonico = :nemonicoTipoCatalogo ");
		sql.append("   and (c.idTipoCatalogo.estado = :estado or c.idTipoCatalogo.estado = :estado1) ");
		sql.append(" order by c.id ");
		
		TypedQuery<Catalogo> q = em.createQuery(sql.toString(), Catalogo.class)
			.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO)
			.setParameter("estado1", Constantes.ESTADO_REGISTRO_ACTIVO1)
			.setParameter("nemonicoTipoCatalogo", nemonicoTipoCatalogo);
		
		return q.getResultList();
	}

	@Override
	public List<Etnia> listaEtnia() {
		StringBuilder sql = new StringBuilder();
		sql.append("select e from Etnia e ");
		sql.append(" where (e.estado = :estado or e.estado = :estado1) ");
		sql.append(" order by e.nombre ");
		
		TypedQuery<Etnia> q = em.createQuery(sql.toString(), Etnia.class)
			.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO)
			.setParameter("estado1", Constantes.ESTADO_REGISTRO_ACTIVO1);
		
		return q.getResultList();
	}

	@Override
	public List<Pais> listaPais() {
		StringBuilder sql = new StringBuilder();
		sql.append("select p from Pais p ");
		sql.append(" where (p.estado = :estado or p.estado = :estado1) ");
		sql.append("   and p.nombre not like '%OTROS%' ");
		sql.append(" order by p.nombre ");
		
		TypedQuery<Pais> q = em.createQuery(sql.toString(), Pais.class)
			.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO)
			.setParameter("estado1", Constantes.ESTADO_REGISTRO_ACTIVO1);
		
		return q.getResultList();
	}

	@Override
	public List<Pais> listaPaisUbicacion() {
		StringBuilder sql = new StringBuilder();
		sql.append("select p from Pais p ");
		sql.append(" where (p.estado = :estado or p.estado = :estado1) ");
		sql.append("   and (p.id = 108 or p.id = 115 or p.id = 302 or p.id = 345) ");
		sql.append(" order by p.nombre ");
		
		TypedQuery<Pais> q = em.createQuery(sql.toString(), Pais.class)
			.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO)
			.setParameter("estado1", Constantes.ESTADO_REGISTRO_ACTIVO1);
		
		return q.getResultList();
	}

	@Override
	public List<Grado> listaGrado() {
		StringBuilder sql = new StringBuilder();
		sql.append("select g from Grado g ");
		sql.append(" where (g.estado = :estado or g.estado = :estado1) ");
		sql.append("   and (g.id > 3 and g.id < 17) or g.id = 50 ");
		sql.append(" order by g.id ");
		
		TypedQuery<Grado> q = em.createQuery(sql.toString(), Grado.class)
			.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO)
			.setParameter("estado1", Constantes.ESTADO_REGISTRO_ACTIVO4);
		
		return q.getResultList();
	}

	@Override
	public List<ProgramaEbja> listaProgramaEbja(Integer idGrado) {
		List<ProgramaEbja> listaProgramaEbja = new ArrayList<ProgramaEbja>();
		List<ProgramaEbja> listaProgramaEbjaAux = new ArrayList<ProgramaEbja>();

		// Si es pack, mostrar los grados y ver que oferta es la siguiente y mostrar los grados
		
		
		// Si no es pack, se debe mostrar los grados siguentes dentro de la siguiente o misma oferta.
		
		
		StringBuilder sql = new StringBuilder();
		sql.append("select pe from  ");
		sql.append(" where (pg.estado = :estado or pg.estado = :estado1) ");
		sql.append("   and pg.grado.id = :idGrado ");
		
//		StringBuilder sql = new StringBuilder();
//		sql.append("select pe from ProgramaGrado pg join pg.programaEbja pe join pe.modalidad m ");
//		sql.append(" where (pg.estado = :estado or pg.estado = :estado1) ");
//		sql.append("   and pg.grado.id = :idGrado ");
		
		TypedQuery<ProgramaEbja> q = em.createQuery(sql.toString(), ProgramaEbja.class)
			.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO)
			.setParameter("estado1", Constantes.ESTADO_REGISTRO_ACTIVO1)
			.setParameter("idGrado", idGrado);
		
		listaProgramaEbjaAux = q.getResultList();
		if (!listaProgramaEbjaAux.isEmpty()) {
			int index = 0;
			for (ProgramaEbja programaEbjaAux : listaProgramaEbjaAux) {
				programaEbjaAux = listaProgramaEbjaAux.get(index);
				Hibernate.initialize(programaEbjaAux.getTipoPrograma());
				Hibernate.initialize(programaEbjaAux.getModalidad());
				listaProgramaEbja.add(programaEbjaAux);
				index = index + 1;
			}
		}
		
		
		return listaProgramaEbja;
	}

	@Override
	public List<TipoPrograma> listaTipoPrograma() {
		StringBuilder sql = new StringBuilder();
		sql.append("select tp from TipoPrograma tp ");
		
		TypedQuery<TipoPrograma> q = em.createQuery(sql.toString(), TipoPrograma.class);
		
		return q.getResultList();
	}

	@Override
	public List<Area> listaArea() {
		StringBuilder sql = new StringBuilder();
		sql.append("select a from Area a where a.estado = '1'");
		
		TypedQuery<Area> q = em.createQuery(sql.toString(), Area.class);
		
		return q.getResultList();
	}

	@Override
	public List<Materia> listaMateria() {
		StringBuilder sql = new StringBuilder();
		sql.append("select m from Materia m where m.estado = '1'");
		
		TypedQuery<Materia> q = em.createQuery(sql.toString(), Materia.class);
		
		return q.getResultList();
	}

	@Override
	public Area ObtenerArea(String idArea) {
		return  (Area) em.createNamedQuery("Area.findById").setParameter("id",Integer.parseInt(idArea)).getSingleResult();
	}

	@Override
	public Materia ObtenerMateria(String idMateria) {
		return  (Materia) em.createNamedQuery("Materia.findById").setParameter("id", Integer.parseInt(idMateria)).getSingleResult();
	}

}
