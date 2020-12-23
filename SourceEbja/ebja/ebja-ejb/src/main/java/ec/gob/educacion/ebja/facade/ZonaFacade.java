/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.educacion.ebja.facade;

import ec.gob.educacion.ebja.facade.local.ZonaFacadeLocal;
import ec.gob.educacion.ebja.modelo.zeus.Zona;
import ec.gob.educacion.ebja.recursos.Constantes;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ZonaFacade extends AbstractFacade<Zona> implements ZonaFacadeLocal {

    @PersistenceContext(unitName = "zeusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ZonaFacade() {
        super(Zona.class);
    }

	public Zona buscarPorCodigoSenplades(String codigoSenplades) {
		try {
			StringBuilder sentencia = new StringBuilder();
			sentencia.append(" select z from Zona z ");
			sentencia.append(" where z.codigoSenpladesZona = :codigoSenplades ");
			sentencia.append("   and z.estado = :estado ");

			Query q = em.createQuery(sentencia.toString()).setParameter("codigoSenplades", codigoSenplades)
					.setParameter("estado", Constantes.ESTADO_REGISTRO_ACTIVO);
			return (Zona) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Zona();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Zona> findByEstado(String estado) {
		List<Zona> listaZona;
		
		listaZona = em.createNamedQuery("Zona.findByEstado")
							.setParameter("estado", estado).getResultList();

		return listaZona;
	}
    
}
