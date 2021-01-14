/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.educacion.ebja.facade.local;

import java.util.List;
import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.modelo.TipoPrograma;
import ec.gob.educacion.ebja.modelo.zeus.Area;
import ec.gob.educacion.ebja.modelo.zeus.Catalogo;
import ec.gob.educacion.ebja.modelo.zeus.Etnia;
import ec.gob.educacion.ebja.modelo.zeus.Grado;
import ec.gob.educacion.ebja.modelo.zeus.Materia;
import ec.gob.educacion.ebja.modelo.Pais;

@Local
public interface CatalogoFacadeLocal {

    void create(Catalogo catalogo);
    void edit(Catalogo catalogo);
    void remove(Catalogo catalogo);

    Catalogo find(Object id);
    
    List<Catalogo> findAll();
    List<Catalogo> findRange(int[] range);
    int count();
    
    List<Catalogo> obtenerCatalogosPorTipoCatalogo(String nemonicoTipoCatalogo);
    List<Etnia> listaEtnia();
    List<Pais> listaPais();
    List<Pais> listaPaisUbicacion();
    List<Grado> listaGrado();
    List<ProgramaEbja> listaProgramaEbja(Integer idGrado);
    List<TipoPrograma> listaTipoPrograma();
    List<Area> listaArea();
    List<Materia> listaMateria();
    List<Pais> listaPaisUbicacionEcuador();
    
    Area ObtenerArea(String idArea);
    Materia ObtenerMateria(String idMateria);
}
