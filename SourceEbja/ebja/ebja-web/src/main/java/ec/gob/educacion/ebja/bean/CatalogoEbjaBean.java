package ec.gob.educacion.ebja.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.UploadedFile;

import ec.gob.educacion.ebja.controlador.BaseControlador;
import ec.gob.educacion.ebja.controlador.SesionControlador;
import ec.gob.educacion.ebja.facade.local.ManejoCatalogoFacadeLocal;
import ec.gob.educacion.ebja.facade.local.TipoCatalogoEbjaFacadeLocal;
import ec.gob.educacion.ebja.modelo.CatalogoEbja;
import ec.gob.educacion.ebja.modelo.TipoCatalogoEbja;
import ec.gob.educacion.ebja.recursos.Constantes;
import ec.gob.educacion.ebja.recursos.Util;

@ManagedBean
@ViewScoped
public class CatalogoEbjaBean extends BaseControlador implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Object[]> listaCatalogos;
	private CatalogoEbja catalogo;
	private String archivoPdf;
	private String nombre;
	private String nemonico;
	private String busquedaContenido = "";
	private String pathGuardar = "";
	private UploadedFile archivoParaGuardar;
	private int crearOeditar = 2;
	private int busquedaTipo = 1;
	private List<TipoCatalogoEbja> listaTipoCatalogo;
	private String tipoCatalogoSeleccionado ="";
	

	@EJB
	private ManejoCatalogoFacadeLocal catalogos;
	
	@EJB
	private TipoCatalogoEbjaFacadeLocal tipoCatalogo;
	
	@Inject
	private SesionControlador sesionControlador;

	public CatalogoEbjaBean() {
			listaCatalogos = new ArrayList<Object[]>();
			listaTipoCatalogo = new ArrayList<>();
		}
	
	@PostConstruct
	public void init() {
		cargarTipoCatalogo();
	}

	public void buscarCatalogoEbja() {

		switch (busquedaTipo) {
		case 1: {
			listaCatalogos = catalogos.findByCodigo(busquedaContenido);
			setBusquedaContenido("");
			break;
		}
		case 2: {
			listaCatalogos = catalogos.findByNombre(busquedaContenido);
			setBusquedaContenido("");
			break;
		}
		}
	}

	public void ejecutarCrearOEditarRegistro() {

		if (!validarCamposVacios()) {
			if (crearOeditar == 1)
				crearRegistroCatalogoEbja();
			else
				editarCatalogoEbja();
			    buscarCatalogoEbja();
		} else {
			
			FacesContext.getCurrentInstance().addMessage("frmForm:messageCatalogoEbja",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_CAMPOS_VACIOS, ""));
		}
	}

	public void borrarCatalogoEbja() {
		try {
			borrarCatalogoEbjaLogico();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageCatalogoEbja",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_INACTIVO_ERROR, ""));
		}
	}
	
	
	public void eliminarCatalogoEbja() {
		try {
			borrarCatalogoEbjaFisico();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageCatalogoEbja",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_BORRAR_ERROR, ""));
		}
	}

	public void activarCatalogoEbja() {
		try {
			
			if (catalogo.getEstado().contentEquals("1")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageCatalogoEbja",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_ACTIVO, ""));
			} else {
				
				catalogo.setEstado("1");
				catalogos.edit(catalogo);
				buscarCatalogoEbja();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageCatalogoEbja",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_ACTIVO_EXITOSAMENTE, ""));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageCatalogoEbja",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_ACTIVO_ERROR, ""));
		}
	}

	/*------------------------------------Funciones de Apoyo----------------------------------------*/

	
	private void cargarTipoCatalogo(){
		listaTipoCatalogo = tipoCatalogo.tipoCatalogoActivos();
	}
	private void cargaDatosRegistro() {
        
		
		catalogo.setTipoCatalogoEbja(tipoCatalogo.ObtenerTipoCatalogoEbja(tipoCatalogoSeleccionado));
		catalogo.setNombre(nombre);
		catalogo.setFechaCreacion(new Date());
		catalogo.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue());
		catalogo.setNemonico(nemonico);
		catalogo.setIpUsuario(sesionControlador.getIpAdressLocal());
		catalogo.setEstado("1"); // Activo
	}

	public void setearCrearCatalogoEbja() {
		crearOeditar = 1; // bandera de crear CatalogoEbja
		setNombre("");
		setNemonico("");
	}

	public void setearEditarCatalogoEbja() {
		crearOeditar = 2; // bandera de editar CatalogoEbja
	}

	private boolean validarCamposVacios() {
		return (nombre.isEmpty() || nemonico.isEmpty()) ? true : false;
	}

	public void crearRegistroCatalogoEbja() {
		try {
			catalogo = new CatalogoEbja();
			cargaDatosRegistro();
			catalogos.create(catalogo);
			FacesContext.getCurrentInstance().addMessage("frmForm:messageCatalogoEbja",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_GUARDADO_EXITOSO, ""));
		} catch (Exception ex) {
			ex.printStackTrace();
			if (Util.getRootException(ex).getLocalizedMessage()
					.contains("llave duplicada viola restricción de unicidad")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageCatalogoEbja",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_CODIGO_DUPLICADO, ""));
			} else {
			FacesContext.getCurrentInstance().addMessage("frmForm:messageCatalogoEbja",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_GUARDAR_REGISTRO, ""));
		}
		}
	}

	public void catalogoSeleccionadoEditar(Object[] object) {
		setearEditarCatalogoEbja();
		this.catalogo = (CatalogoEbja) (object[0]);
		setTipoCatalogoSeleccionado(catalogo.getTipoCatalogoEbja().getNemonico());
		setNemonico(catalogo.getNemonico());
		setNombre(catalogo.getNombre());

	}

	public void catalogoSeleccionadoActivar(Object[] object) {
		this.catalogo = (CatalogoEbja) (object[0]);
	}

	public void catalogoSeleccionadoBorrar(Object[] object) {
		this.catalogo = (CatalogoEbja) (object[0]);
	}

	public void editarCatalogoEbja() {

		try {
			catalogo.setTipoCatalogoEbja(tipoCatalogo.ObtenerTipoCatalogoEbja(tipoCatalogoSeleccionado));
			catalogo.setNombre(nombre);
			catalogo.setNemonico(nemonico);
			catalogos.edit(catalogo);
			FacesContext.getCurrentInstance().addMessage("frmForm:messageCatalogoEbja",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_EDITO_EXITOSAMENTE, ""));

		} catch (Exception ex) {
			ex.printStackTrace();
			if (Util.getRootException(ex).getLocalizedMessage()
					.contains("llave duplicada viola restricción de unicidad")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageCatalogoEbja",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_CODIGO_DUPLICADO_EDITAR, ""));
			}else {
			FacesContext.getCurrentInstance().addMessage("frmForm:messageCatalogoEbja",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_EDITO_ERROR, ""));
		}
		}

	}

	public void borrarCatalogoEbjaLogico() {
		
	
			
			if (this.catalogo.getEstado().contentEquals("0")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageCatalogoEbja",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_INACTIVO, ""));
			}else {
				catalogo.setEstado("0");
				catalogos.edit(catalogo);
				buscarCatalogoEbja();
				FacesContext.getCurrentInstance().addMessage("frmForm:messageCatalogoEbja",
						new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_INACTIVO_EXITOSAMENTE, ""));
			}
			
		
		
	}

	public void borrarCatalogoEbjaFisico() {
		
			catalogos.remove(catalogo);
			buscarCatalogoEbja();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageCatalogoEbja",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_BORRAR_EXITOSAMENTE, ""));
		
	}

	/*------------------------------------Getters and Setters---------------------------------------*/

	public int getBusquedaTipo() {
		return busquedaTipo;
	}

	public int getCrearOeditar() {
		return crearOeditar;
	}

	public void setCrearOeditar(int crearOeditar) {
		this.crearOeditar = crearOeditar;
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

	public List<Object[]> getlistaCatalogos() {
		return listaCatalogos;
	}

	public void setlistaCatalogos(List<Object[]> listaCatalogos) {
		this.listaCatalogos = listaCatalogos;
	}

	public String getArchivoPdf() {
		return archivoPdf;
	}

	public void setArchivoPdf(String archivoPdf) {
		this.archivoPdf = archivoPdf;
	}

	public CatalogoEbja getCatalogoEbja() {
		return catalogo;
	}

	public void setCatalogoEbja(CatalogoEbja catalogo) {
		this.catalogo = catalogo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNemonico() {
		return nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
	}

	public UploadedFile getArchivoParaGuardar() {
		return archivoParaGuardar;
	}

	public void setArchivoParaGuardar(UploadedFile archivoParaGuardar) {
		this.archivoParaGuardar = archivoParaGuardar;
	}

	public String getPathGuardar() {
		return pathGuardar;
	}

	public void setPathGuardar(String pathGuardar) {
		this.pathGuardar = pathGuardar;
	}

	public List<TipoCatalogoEbja> getListaTipoCatalogo() {
		return listaTipoCatalogo;
	}

	public void setListaTipoCatalogo(List<TipoCatalogoEbja> listaTipoCatalogo) {
		this.listaTipoCatalogo = listaTipoCatalogo;
	}

	public String getTipoCatalogoSeleccionado() {
		return tipoCatalogoSeleccionado;
	}

	public void setTipoCatalogoSeleccionado(String tipoCatalogoSeleccionado) {
		this.tipoCatalogoSeleccionado = tipoCatalogoSeleccionado;
	}
	
}