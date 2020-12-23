package ec.gob.educacion.ebja.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ec.gob.educacion.ebja.controlador.BaseControlador;

import ec.gob.educacion.ebja.facade.local.CeduladoMeducacionFacadeLocal;
import ec.gob.educacion.ebja.modelo.zeus.Catalogo;
import ec.gob.educacion.ebja.recursos.Constantes;
import ec.gob.educacion.ebja.servicio.CatalogoServicio;

@ManagedBean
@ViewScoped
public class EstudianteBean extends BaseControlador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private CatalogoServicio catalogoServicio;
	@EJB
	private CeduladoMeducacionFacadeLocal ceduladoMeducacionFacadeLocal;

	private int busquedaTipo = 1;
	private String busquedaContenido = "";
	private boolean readonlyNumeroIdentificacion = false;
	private boolean readonlyApellidosNombres = false;
	private List<Catalogo> listaTipoDocumento;

	@PostConstruct
	public void init() {
		cargarCatalogos();
		
	}
	
	private boolean editNumeroIdentificacion = false;
	private boolean habPanelA = false;
	private boolean habPanelB = false;
	private boolean habPanelC = false;
	private boolean habPanelD = false;
	
	
	public void habilitarConCedula() {	
		habilitarPanelesABCD();
	}
	
	public void buscarEstudiante() {
		
	}
	
	public void habilitarPanelesABCD() {
		setHabPanelA(true);
		setHabPanelB(true);
		setHabPanelC(true);
		setHabPanelD(true);
	}
	
	public void cargarCatalogos() {
		// Obtener lista tipo documento.
		
		
		 setEditNumeroIdentificacion(true);
				listaTipoDocumento = catalogoServicio.obtenerCatalogosPorTipoCatalogo(Constantes.NEMONICO_TIPO_DOCUMENTO);
	}
	
	/*------------------------------------Funciones de Apoyo----------------------------------------*/
	
	
	
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


	public boolean isReadonlyNumeroIdentificacion() {
		return readonlyNumeroIdentificacion;
	}


	public void setReadonlyNumeroIdentificacion(boolean readonlyNumeroIdentificacion) {
		this.readonlyNumeroIdentificacion = readonlyNumeroIdentificacion;
	}


	public List<Catalogo> getListaTipoDocumento() {
		return listaTipoDocumento;
	}


	public void setListaTipoDocumento(List<Catalogo> listaTipoDocumento) {
		this.listaTipoDocumento = listaTipoDocumento;
	}


	public boolean isReadonlyApellidosNombres() {
		return readonlyApellidosNombres;
	}


	public void setReadonlyApellidosNombres(boolean readonlyApellidosNombres) {
		this.readonlyApellidosNombres = readonlyApellidosNombres;
	}

	public boolean isEditNumeroIdentificacion() {
		return editNumeroIdentificacion;
	}

	public void setEditNumeroIdentificacion(boolean editNumeroIdentificacion) {
		this.editNumeroIdentificacion = editNumeroIdentificacion;
	}

	public boolean isHabPanelA() {
		return habPanelA;
	}

	public void setHabPanelA(boolean habPanelA) {
		this.habPanelA = habPanelA;
	}

	public boolean isHabPanelB() {
		return habPanelB;
	}

	public void setHabPanelB(boolean habPanelB) {
		this.habPanelB = habPanelB;
	}

	public boolean isHabPanelC() {
		return habPanelC;
	}

	public void setHabPanelC(boolean habPanelC) {
		this.habPanelC = habPanelC;
	}

	public boolean isHabPanelD() {
		return habPanelD;
	}

	public void setHabPanelD(boolean habPanelD) {
		this.habPanelD = habPanelD;
	} 
	
	
	
	
}
