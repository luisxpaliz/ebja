package ec.gob.educacion.ebja.facade.local;

import javax.ejb.Local;
import ec.gob.educacion.ebja.modelo.asignaciones.InsParametro;

@Local
public abstract interface InsParametroFacadeLocal
{
  public abstract InsParametro buscarPorCodigo(String paramString);
  public abstract InsParametro[] obtenerParametrosMail();

}
