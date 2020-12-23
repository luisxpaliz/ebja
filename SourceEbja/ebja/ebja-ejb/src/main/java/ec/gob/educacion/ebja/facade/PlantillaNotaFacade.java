package ec.gob.educacion.ebja.facade;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.gob.educacion.ebja.facade.local.PlantillaNotaFacadeLocal;
import ec.gob.educacion.ebja.modelo.PlantillaNota;

public class PlantillaNotaFacade extends AbstractFacade<PlantillaNota> implements PlantillaNotaFacadeLocal {

	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public PlantillaNotaFacade(Class<PlantillaNota> entityClass) {
		super(entityClass);
	}
	
	public PlantillaNotaFacade() {
		super(PlantillaNota.class);
	}

	
	@Override
	public PlantillaNota find(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> findByCodigo(String codigoPlantillaNota) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> findByNombre(String nombrePlantillaNota) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlantillaNota> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlantillaNota> buscarTodosPlantillaNota() {
		
		return null;
	}

	@Override
	public List<PlantillaNota> buscarTodosPlantillaNotaActivos() {
		
		return null;
	}

	@Override
	public PlantillaNota findByCodigoSoloPlantillaNota(String codigoPlantillaNota) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
