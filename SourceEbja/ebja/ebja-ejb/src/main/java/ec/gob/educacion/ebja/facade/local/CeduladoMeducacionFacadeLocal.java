package ec.gob.educacion.ebja.facade.local;

import ec.gob.educacion.ebja.modelo.asignaciones.CeduladoMeducacion;
import ec.gob.educacion.ebja.modelo.asignaciones.Nacionalidad;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

@Local
public abstract interface CeduladoMeducacionFacadeLocal
{
  public abstract List<CeduladoMeducacion> findPersonaByCedula(String paramString);
  public abstract String findPersona(String paramString);
  public abstract CeduladoMeducacion findCeduladoMeducacion(String paramString);
  
  Nacionalidad buscarNacionalidadPorCodigo(BigDecimal codNacionalidad);
}
