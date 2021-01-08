package ec.gob.educacion.ebja.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;

import ec.gob.educacion.ebja.facade.local.InstitucEstablecFacadeLocal;
import ec.gob.educacion.ebja.modelo.zeus.InstitucEstablec;

@Stateless
public class InstitucEstablecFacade extends AbstractFacade<InstitucEstablec> implements InstitucEstablecFacadeLocal {
	private List<InstitucEstablec> listaResultado;
	
	@PersistenceContext(unitName = "zeusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
	
	public InstitucEstablecFacade() {
		super(InstitucEstablec.class);
		listaResultado = new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InstitucEstablec> institucionFindByAmie(String codigoAmie) {
		String sql = "";
		List<InstitucEstablec> listaAux = new ArrayList<>();
		listaResultado = new ArrayList<>();
		
		sql = "select ins"
				+ " from InstitucEstablec ins"
				+ " where upper(ins.idInstitucion.amie) like concat('%',upper(:amie),'%')"
				+ " and ins.estado = '1'"
				+ " and ins.estadoVigencia = '1'";
		
		listaAux = em.createQuery(sql).setParameter("amie", codigoAmie).getResultList();
		
		if (!listaAux.isEmpty()) {
			int index = 0;
			for (InstitucEstablec institucEstablecAux : listaAux) {
				institucEstablecAux = listaAux.get(index);
				Hibernate.initialize(institucEstablecAux.getIdCircuitoParroquia().getIdCircuito().getIdDistrito().getIdZona());
				Hibernate.initialize(institucEstablecAux.getIdCircuitoParroquia().getIdParroquia().getIdCanton().getIdProvincia());
				Hibernate.initialize(institucEstablecAux.getIdInstitucion());
				Hibernate.initialize(institucEstablecAux.getIdEstablecimiento());
				listaResultado.add(institucEstablecAux);
				index++;
			}
		}
		
		return listaResultado;		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InstitucEstablec> institucionFindByCircuito(Integer idCircuito) {
		String sql = "";
		List<InstitucEstablec> listaAux = new ArrayList<>();
		listaResultado = new ArrayList<>();
		
		sql = "select ins"
				+ " from InstitucEstablec ins"
				+ " where ins.idCircuitoParroquia.idCircuito.id = :idCircuito"
				+ " and ins.estado = '1'"
				+ " and ins.estadoVigencia = '1'";
		
		listaAux = em.createQuery(sql).setParameter("idCircuito", idCircuito).getResultList();
		
		if (!listaAux.isEmpty()) {
			int index = 0;
			for (InstitucEstablec institucEstablecAux : listaAux) {
				institucEstablecAux = listaAux.get(index);
				Hibernate.initialize(institucEstablecAux.getIdCircuitoParroquia().getIdCircuito().getIdDistrito().getIdZona());
				Hibernate.initialize(institucEstablecAux.getIdCircuitoParroquia().getIdParroquia().getIdCanton().getIdProvincia());
				Hibernate.initialize(institucEstablecAux.getIdInstitucion());
				Hibernate.initialize(institucEstablecAux.getIdEstablecimiento());
				listaResultado.add(institucEstablecAux);
				index++;
			}
		}
		
		return listaResultado;
	}
	
	@SuppressWarnings("unchecked")
	public List<InstitucEstablec> institucionFindByParroquia (short idParroquia, String sostenimiento){
		String sql = "";
		List<InstitucEstablec> listaAux = new ArrayList<>();
		listaResultado = new ArrayList<>();
		
		sql = "select ins"
				+ " from InstitucEstablec ins"
				+ " where ins.idCircuitoParroquia.idParroquia.id = :idParroquia"
				+ " and ins.idInstitucion.sostenimiento.descripcion = :sostenimiento"
				+ " and ins.estado = '1'"
				+ " and ins.estadoVigencia = '1' order by ins.idInstitucion.amie asc ";
		
		listaAux = em.createQuery(sql).setParameter("idParroquia", idParroquia).setParameter("sostenimiento", sostenimiento).getResultList();
		
		if (!listaAux.isEmpty()) {
			int index = 0;
			for (InstitucEstablec institucEstablecAux : listaAux) {
				institucEstablecAux = listaAux.get(index);
				Hibernate.initialize(institucEstablecAux.getIdCircuitoParroquia().getIdCircuito().getIdDistrito().getIdZona());
				Hibernate.initialize(institucEstablecAux.getIdCircuitoParroquia().getIdParroquia().getIdCanton().getIdProvincia());
				Hibernate.initialize(institucEstablecAux.getIdInstitucion());
				Hibernate.initialize(institucEstablecAux.getIdInstitucion().getSostenimiento());
				Hibernate.initialize(institucEstablecAux.getIdEstablecimiento());
				listaResultado.add(institucEstablecAux);
				index++;
			}
		}
		
		return listaResultado;
	}

}
