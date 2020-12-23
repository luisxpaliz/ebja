package ec.gob.educacion.ebja.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

import ec.gob.educacion.ebja.facade.local.CatalogoFacadeLocal;
import ec.gob.educacion.ebja.modelo.zeus.Catalogo;


@ManagedBean
@ViewScoped
public class CatalogoControlador extends BaseControlador implements Serializable {

	private static final long serialVersionUID = 1L;	
	private String busquedaContenido = "";
	private List<Catalogo> listaCatalogos;
	private Part ArchivoPdf;
	
	private int crearOeditar=2;
	private int busquedaTipo = 1;
	
	@EJB
	private CatalogoFacadeLocal catalogos;
	
	public CatalogoControlador() {
		listaCatalogos = new ArrayList<Catalogo>();
	}

	public void buscarCatalogo(ActionEvent actionEvent) {

		/*switch (busquedaTipo) {

			case 1: {
				 listaCatalogos = catalogos.findByNemonico(busquedaContenido);
				 break;
			}
			case 2: {
				listaCatalogos =  catalogos.findByNombre(busquedaContenido);
				break;
			}
//			case 3: {
//				listaCatalogos =  catalogos.findByTipoCatalogo(busquedaContenido);
//				break;
//			}
		}*/
	}

	public boolean crearRegistroCatalogo() {
		return true;
	}

	public boolean editarCatalogo() {
		return true;
	}

	public boolean borrarCatalogo() {
		return true;
	}
	
	public void catalogoSeleccionadoEditar(Object[] object) {
		/*setearEditarCatalogo();
		this.catalogo = (Catalogo) (object[0]);
		setNemonico(catalogo.getNemonico());
		setNombre(acuerdo.getNombre());*/
		
	}
	
	public boolean catalogoSeleccionadoBorrar(Object[] object) {
		/*this.acuerdo = (Acuerdo) (object[0]);
		return true;*/
		return true;
	}
	
	public void setearEditarCatalogo() {
		crearOeditar=2; // bandera de editar acuerdo
	}

	/*------------------------------------Getters and Setters---------------------------------------*/

	public int getBusquedaTipo() {
		return busquedaTipo;
	}

	public void setBusquedaTipo(int busquedaTipo) {
		this.busquedaTipo = busquedaTipo;
	}

	public String getBusquedaContenido() {
		return busquedaContenido;
	}

	public void setBusquedaContenido(String busquedaContenido) {
		this.busquedaContenido = busquedaContenido;
	}

	public List<Catalogo> getlistaCatalogos() {
		return listaCatalogos;
	}

	public void setlistaCatalogos(List<Catalogo> listaCatalogos) {
		this.listaCatalogos = listaCatalogos;
	}

	public Part getArchivoPdf() {
		return ArchivoPdf;
	}

	public void setArchivoPdf(Part archivoPdf) {
		ArchivoPdf = archivoPdf;
	}	

}