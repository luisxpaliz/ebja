package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.modelo.RegistroEstudiante;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public abstract interface RegistroEstudianteFacadeLocal {

	void create(RegistroEstudiante registroEstudiante);

	void edit(RegistroEstudiante registroEstudiante);

	void remove(RegistroEstudiante registroEstudiante);

	RegistroEstudiante find(Object id);

	List<RegistroEstudiante> findAll();

	List<RegistroEstudiante> findRange(int[] range);

	int count();

	public abstract RegistroEstudiante getNombreCedula(String paramString);

	public abstract RegistroEstudiante getApellidosNombres(String paramString);

	public abstract List<RegistroEstudiante> getRegistroEstudiantePorFiltros(String paramString1, String paramString2, Date paramDate, Integer paramString4, String nombreTipoDocumento);
	  
	public abstract boolean existeRegistroEstudiantePorFiltros(String paramString1, String paramString2, Date paramDate, Integer paramString4, String nombreTipoDocumento);

	public abstract RegistroEstudiante buscarInscripcionPendiente(String numeroIdentificacion, String apellidosNombres);

}
