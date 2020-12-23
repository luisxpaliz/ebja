package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.modelo.DetalleTraslado;
import java.util.List;
import javax.ejb.Local;

@Local
public abstract interface DetalleTrasladoFacadeLocal {

	void create(DetalleTraslado detalleTraslado);
	void edit(DetalleTraslado detalleTraslado);
	void remove(DetalleTraslado detalleTraslado);

	DetalleTraslado find(Object id);
	List<DetalleTraslado> findAll();
	List<DetalleTraslado> findRange(int[] range);
	int count();
}
