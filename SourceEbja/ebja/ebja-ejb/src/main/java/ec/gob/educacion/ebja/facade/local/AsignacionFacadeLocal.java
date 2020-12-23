package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.modelo.Inscripcion;
import ec.gob.educacion.ebja.modelo.RegistroEstudiante;
import ec.gob.educacion.ebja.modelo.Ubicacion;
import javax.ejb.Local;

@Local
public abstract interface AsignacionFacadeLocal {
	boolean procesarAsignacion(Inscripcion inscripcion, RegistroEstudiante registroEstudiante, Ubicacion ubicacion);
}
