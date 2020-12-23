package ec.gob.educacion.ebja.facade.local;

import java.util.List;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.Mensaje;

@Local
public interface MensajeFacadeLocal {

	void create(Mensaje menssaje);
	void edit( Mensaje mensaje);
	void remove(Mensaje mensaje);

	Mensaje find(Object id);
	List<Mensaje> findAll();
	List<Object[]> buscarMensajesNombre(String mensaje);
	List<Object[]> buscarMensajesNemonico(String mensaje);

	Mensaje obtenerTituloPagina(String nemonico);
	
}



