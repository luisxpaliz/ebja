package ec.gob.educacion.ebja.facade.local;

import java.util.List;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.TipoCatalogoEbja;

@Local
public interface TipoCatalogoEbjaFacadeLocal {

	void create(TipoCatalogoEbja tipoCatalogoEbja);
    void edit(TipoCatalogoEbja tipoCatalogoEbja);
    void remove(TipoCatalogoEbja tipoCatalogoEbja);

    TipoCatalogoEbja find(Object id);
    List<TipoCatalogoEbja> findAll();
    List<TipoCatalogoEbja> tipoCatalogoActivos();
    
    TipoCatalogoEbja ObtenerTipoCatalogoEbja(String valorVariable);
	
}
