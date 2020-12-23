package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.modelo.ProgramaInstitucion;
import ec.gob.educacion.ebja.modelo.zeus.Paralelo;

import java.util.List;
import javax.ejb.Local;

@Local
public abstract interface ProgramaInstitucionFacadeLocal {

	void create(ProgramaInstitucion programaInstitucion);
	void edit(ProgramaInstitucion programaInstitucion);
	void remove(ProgramaInstitucion programaInstitucion);

	ProgramaInstitucion find(Object id);
	List<ProgramaInstitucion> findAll();
	List<ProgramaInstitucion> findRange(int[] range);
	List<ProgramaInstitucion> findByIdProgramaEbja(Integer idProgramaEbja);
	List<ProgramaInstitucion> findByIdProgramaEbjaAmie(Integer idProgramaEbja, String codigoAmie);
	List<ProgramaInstitucion> findByIdProgramaEbjaCircuito(Integer idProgramaEbja, Integer idCircuito);
	List<ProgramaInstitucion> findByIdProgramaEbjaParroquia(Integer idProgramaEbja, Short idParroquia);
	int count();
	
	List<ProgramaInstitucion> listaProgramaInstitucionPorProgramaEbja(Integer idProgramaEbja, Integer idZona);
	Long validarExisteProgramaInstitucion(Integer idProgramaEbja, Integer idInstitucEstablec);

	List<ProgramaInstitucion> obtenerProgramaInstitucionPorInstitucion(String amie);
	ProgramaInstitucion obtenerProgramaInstitucionPorId(Integer idProgramaInstitucion);
	
	List<ProgramaEbja> buscarProgramaEbjaInstitucion(String amie);
	List<Paralelo> buscarParaleloInstitucion (String amie, String nemonico);
	List<ProgramaInstitucion> buscarProgramaInstitucion(String amie, String nemonico);

}
