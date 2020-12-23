package ec.gob.educacion.ebja.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gob.educacion.ebja.facade.local.CatalogoFacadeLocal;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.modelo.TipoPrograma;
import ec.gob.educacion.ebja.modelo.zeus.Catalogo;
import ec.gob.educacion.ebja.modelo.zeus.Etnia;
import ec.gob.educacion.ebja.modelo.zeus.Grado;
import ec.gob.educacion.ebja.modelo.Pais;
import ec.gob.educacion.ebja.servicio.CatalogoServicio;

@Stateless
public class CatalogoServicioImpl implements CatalogoServicio {
	@EJB
	private CatalogoFacadeLocal catalogoFacadeLocal;

	@Override
	public List<Catalogo> obtenerCatalogosPorTipoCatalogo(String nemonicoTipoCatalogo) {
		return catalogoFacadeLocal.obtenerCatalogosPorTipoCatalogo(nemonicoTipoCatalogo);
	}

	@Override
	public List<Etnia> listaEtnia() {
		return catalogoFacadeLocal.listaEtnia();
	}

	@Override
	public List<Pais> listaPais() {
		return catalogoFacadeLocal.listaPais();
	}

	@Override
	public List<Pais> listaPaisUbicacion() {
		return catalogoFacadeLocal.listaPaisUbicacion();
	}

	@Override
	public List<Grado> listaGrado() {
		return catalogoFacadeLocal.listaGrado();
	}

	@Override
	public List<ProgramaEbja> listaProgramaEbja(Integer idGrado) {
		return catalogoFacadeLocal.listaProgramaEbja(idGrado);
	}

	@Override
	public List<TipoPrograma> listaTipoPrograma() {
		return catalogoFacadeLocal.listaTipoPrograma();
	}
}
