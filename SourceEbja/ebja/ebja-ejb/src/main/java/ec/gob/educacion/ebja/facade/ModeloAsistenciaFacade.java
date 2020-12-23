package ec.gob.educacion.ebja.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ec.gob.educacion.ebja.facade.local.ModeloAsistenciaFacadeLocal;
import ec.gob.educacion.ebja.modelo.ModeloAsistencia;

@Stateless
public class ModeloAsistenciaFacade extends AbstractFacade<ModeloAsistencia> implements ModeloAsistenciaFacadeLocal {


	private List<Object[]> listaObjectResultado;

	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public ModeloAsistenciaFacade() {
		super(ModeloAsistencia.class);
		listaObjectResultado = new ArrayList<Object[]>();
	}
	
	public ModeloAsistenciaFacade(Class<ModeloAsistencia> entityClass) {
		super(entityClass);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> buscarTodosModelosAsistencia(String nemonico) {
		
		String sql =" ";
		sql = "select m, c.nombre, d.nombre from ModeloAsistencia m, CatalogoEbja c, CatalogoEbja d  where m.catalogoTipoAsistencia = d.id and m.estado =c.nemonico and m.programaEbja.nemonico =:nem ";
		listaObjectResultado = em.createQuery(sql).setParameter("nem", nemonico).getResultList();
		
		return listaObjectResultado;
	}

}
