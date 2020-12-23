package ec.gob.educacion.ebja.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ec.gob.educacion.ebja.facade.local.PlantillaEstudianteFacadeLocal;
import ec.gob.educacion.ebja.modelo.PlantillaEstudiante;

@Stateless
public class PlantillaEstudianteFacade extends AbstractFacade<PlantillaEstudiante> implements PlantillaEstudianteFacadeLocal {

	private List<Object[]> listaObjectResultado;
	private String sql = "";

	@PersistenceContext(unitName = "zeusPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public PlantillaEstudianteFacade() {
		super(PlantillaEstudiante.class);
		listaObjectResultado = new ArrayList<Object[]>();

	}

	public PlantillaEstudianteFacade(Class<PlantillaEstudiante> entityClass) {
		super(entityClass);
	}
	
	@Override
	public void create(PlantillaEstudiante plantillaEstudiante) {
		// TODO Auto-generated method stub

	}

	@Override
	public void edit(PlantillaEstudiante plantillaEstudiante) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(PlantillaEstudiante plantillaEstudiante) {
		// TODO Auto-generated method stub

	}

	@Override
	public PlantillaEstudiante find(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> findByCodigo(String codigoPlantillaEstudiante) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> findByNombre(String nombrePlantillaEstudiante) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlantillaEstudiante> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlantillaEstudiante> buscarTodosPlantillaEstudiante() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlantillaEstudiante> buscarTodosPlantillaEstudianteActivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlantillaEstudiante findByCodigoSoloPlantillaEstudiante(String codigoPlantillaEstudiante) {
		// TODO Auto-generated method stub
		return null;
	}

}
