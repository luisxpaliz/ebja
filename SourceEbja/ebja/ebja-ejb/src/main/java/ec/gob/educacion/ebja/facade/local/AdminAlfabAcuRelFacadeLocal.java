package ec.gob.educacion.ebja.facade.local;

import java.util.List;

import javax.ejb.Local;

import ec.gob.educacion.ebja.modelo.Acuerdo;
import ec.gob.educacion.ebja.modelo.AcuerdoJTA;


@Local
public interface AdminAlfabAcuRelFacadeLocal {

    void create(Acuerdo acuerdo);

    void edit(Acuerdo acuerdo);

    void remove(Acuerdo acuerdo);

    Acuerdo find(Object id);
    
    List<Object[]> findByCodigo (String codigoAcuerdo);
    
    List<Object[]> findByNombre (String nombreAcuerdo);

    List<Acuerdo> findAll();
    
    List<Acuerdo> buscarTodosAcuerdos();
    
    List<Acuerdo> buscarTodosAcuerdosActivos();
    
    List<AcuerdoJTA> buscarTodosAcuerdosActivosJTA();
    
    List<Acuerdo> buscarTodosAcuerdosActivosNat();
    
	Acuerdo findByCodigoSoloAcuerdo(String codigoAcuerdo);

	
}
