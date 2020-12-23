package ec.gob.educacion.ebja.facade.local;

import java.util.List;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.Fase;


@Local
public interface FaseFacadeLocal {

	void create(Fase fase);
    void edit(Fase fase);
    void remove(Fase fase);

    Fase find(Object id);
    List<Fase> findAll();
    List<Fase> findAllActive();
	Fase obtenerFase(String valorVariable);
	
}
