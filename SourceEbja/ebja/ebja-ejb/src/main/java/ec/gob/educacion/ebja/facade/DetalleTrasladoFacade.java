package ec.gob.educacion.ebja.facade;

import ec.gob.educacion.ebja.facade.local.DetalleTrasladoFacadeLocal;
import ec.gob.educacion.ebja.modelo.DetalleTraslado;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Stateless
public class DetalleTrasladoFacade extends AbstractFacade<DetalleTraslado> implements DetalleTrasladoFacadeLocal {
	@Resource(mappedName = "java:jboss/datasources/zeusDS")
	private DataSource dataSource;

	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

    public DetalleTrasladoFacade() {
        super(DetalleTraslado.class);
    }
}
