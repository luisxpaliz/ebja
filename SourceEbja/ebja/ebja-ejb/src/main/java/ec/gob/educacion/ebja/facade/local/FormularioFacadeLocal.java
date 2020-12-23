package ec.gob.educacion.ebja.facade.local;

import java.util.List;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.Formulario;

@Local
public interface FormularioFacadeLocal {

	void create(Formulario formulario);
    void edit(Formulario formulario);
    void remove(Formulario formulario);
    Formulario obtenerFormulario (String valorVariable);
    List<Formulario> findAllActive();
	
}
