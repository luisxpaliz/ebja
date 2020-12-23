package ec.gob.educacion.ebja.facade.local;

import java.util.List;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.TipoCatalogoEbja;
import ec.gob.educacion.ebja.modelo.CatalogoEbja;

@Local
public interface ManejoCatalogoFacadeLocal {
	
	   void create(CatalogoEbja catalogo);

	    void edit(CatalogoEbja catalogo);

	    void remove(CatalogoEbja catalogo);

	    CatalogoEbja find(Object id);
	    
	    List<Object[]> findByCodigo (String codigoCatalogo);
	    
	    List<Object[]> findByNombre (String nombreCatalogo);

	    List<CatalogoEbja> findAll();
	    
	    TipoCatalogoEbja buscarTipoCatalogoEbja(TipoCatalogoEbja tipoCatalogoEbja);   
}
