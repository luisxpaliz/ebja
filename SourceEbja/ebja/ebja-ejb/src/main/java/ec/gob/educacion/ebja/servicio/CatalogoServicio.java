package ec.gob.educacion.ebja.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.modelo.TipoPrograma;
import ec.gob.educacion.ebja.modelo.zeus.Catalogo;
import ec.gob.educacion.ebja.modelo.zeus.Etnia;
import ec.gob.educacion.ebja.modelo.zeus.Grado;
import ec.gob.educacion.ebja.modelo.Pais;

@Local
public interface CatalogoServicio {
	
	List<Catalogo> obtenerCatalogosPorTipoCatalogo(String nemonicoTipoCatalogo);
	List<Etnia> listaEtnia();
	List<Pais> listaPais();
	List<Pais> listaPaisUbicacion();
	List<Grado> listaGrado();
	
	List<ProgramaEbja> listaProgramaEbja(Integer idGrado);
	List<TipoPrograma> listaTipoPrograma();
}
