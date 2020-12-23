/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.educacion.ebja.facade.local;

import java.util.List;
import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.TipoCatalogoEbja;
import ec.gob.educacion.ebja.modelo.CatalogoEbja;

@Local
public interface CatalogoEbjaFacadeLocal {

    void create(CatalogoEbja catalogo);
    void edit(CatalogoEbja catalogo);
    void remove(CatalogoEbja catalogo);

    CatalogoEbja find(Object id);
    List<CatalogoEbja> findAll();
    List<CatalogoEbja> findRange(int[] range);
    int count();
    public List<CatalogoEbja> obtenerTipoAsistencia();
    public CatalogoEbja obtenerTipoAsistenciaNemonico(String nemonico);
    
    List<CatalogoEbja> obtenerCatalogosPorTipoCatalogo(String nemonicoTipoCatalogo);
    List<TipoCatalogoEbja> listaTipoCatalogoEbja();
    CatalogoEbja obtenerCatalogoPorTipoCatalogo(String nemonicoTipoCatalogo, String nemonicoCatalogo);
}
