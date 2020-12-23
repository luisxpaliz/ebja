package ec.gob.educacion.ebja.facade;

import ec.gob.educacion.ebja.facade.local.ProvinciaFacadeLocal;
import ec.gob.educacion.ebja.modelo.zeus.Provincia;
import ec.gob.educacion.ebja.recursos.Constantes;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ProvinciaFacade extends AbstractFacade<Provincia> implements ProvinciaFacadeLocal {

    @PersistenceContext(unitName = "zeusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProvinciaFacade() {
        super(Provincia.class);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Provincia> listaProvinciaPorZona(String codigoSenpladesZona)
    {
      try
      {
        StringBuilder sentencia = new StringBuilder();
        sentencia.append(" select distinct p from  Provincia p ");
        sentencia.append("        inner join p.cantonList as c ");
        sentencia.append("        inner join c.parroquiaList as par ");
        sentencia.append("        inner join par.circuitoParroquiaList as cp ");
        sentencia.append("        inner join cp.idCircuito as cir ");
        sentencia.append("        inner join cir.idDistrito as dis ");
        sentencia.append("        inner join dis.idZona as z ");
        sentencia.append(" where z.codigoSenpladesZona = :codigoSenpladesZona ");
        sentencia.append("   and p.estado = :estado ");
        sentencia.append(" order by p.descripcion asc ");

        Query q = em.createQuery(sentencia.toString())
        			.setParameter("codigoSenpladesZona", codigoSenpladesZona)
        			.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO);
        return q.getResultList();
      } catch (Exception e) {
        e.printStackTrace(); }
      	return new ArrayList();
    }
    
	public Provincia buscarPorCodigoProvincia(String codigoProvincia)
    {
      try
      {
        StringBuilder sentencia = new StringBuilder();
        sentencia.append(" select p from Provincia p ");
        sentencia.append(" where p.codigoProvincia = :codigoProvincia ");
        sentencia.append("   and p.estado = :estado ");

        Query q = em.createQuery(sentencia.toString())
        			.setParameter("codigoProvincia", codigoProvincia)
        			.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO);
        return (Provincia) q.getSingleResult();
      } catch (Exception e) {
        e.printStackTrace(); }
      	return new Provincia();
    }

}
