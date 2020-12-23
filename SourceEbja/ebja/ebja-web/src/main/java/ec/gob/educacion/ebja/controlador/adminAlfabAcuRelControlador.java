package ec.gob.educacion.ebja.controlador;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import ec.gob.educacion.ebja.facade.local.AdminAlfabAcuRelFacadeLocal;
import ec.gob.educacion.ebja.facade.local.AdminAlfabModuloFacadeLocal;
import ec.gob.educacion.ebja.modelo.Acuerdo;
import ec.gob.educacion.ebja.modelo.ProgramaEbja;
import ec.gob.educacion.ebja.recursos.Constantes;
import ec.gob.educacion.ebja.recursos.Util;
import ec.gob.educacion.ebja.controlador.BaseControlador;

@ManagedBean
@ViewScoped
public class adminAlfabAcuRelControlador extends BaseControlador implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Object[]> listaAcuerdos;
	private Acuerdo acuerdo;
	private Acuerdo acuerdoBorrado;
	private Acuerdo acuerdoEliminado;
	private String archivoPdf;
	private String nombre;
	private String nemonico;
	private String busquedaContenido = "";
	private String pathGuardar = "";
	private int crearOeditar = 2;
	private int busquedaTipo = 1;

	
	@EJB
	private AdminAlfabModuloFacadeLocal modulos; 
	@Inject
	private SesionControlador sesionControlador;
	@EJB
	private AdminAlfabAcuRelFacadeLocal acuerdos;

	public adminAlfabAcuRelControlador() {
		listaAcuerdos = new ArrayList<Object[]>();
	}

	public void buscarAcuerdo() {
		switch (busquedaTipo) {
		case 1: {
			listaAcuerdos = acuerdos.findByCodigo(busquedaContenido);
			setBusquedaContenido("");
			break;
		}
		case 2: {
			listaAcuerdos = acuerdos.findByNombre(busquedaContenido);
			setBusquedaContenido("");
			break;
		}
		}
	}

	public void ejecutarCrearOEditarRegistro() {

		if (!validarCamposVacios()) {
			if (crearOeditar == 1)
				crearRegistroAcuerdo();
			else
				editarAcuerdo();
			buscarAcuerdo();
		} else {
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_CAMPOS_VACIOS, ""));
		}
	}

	public void borrarAcuerdo() {
		try {
			borrarAcuerdoLogico();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_INACTIVO_ERROR, ""));
		}
	}

	public void activarAcuerdo() {
		try {
			if (acuerdo.getEstado().contentEquals("1")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_ACTIVO, ""));
			} else {

				acuerdo.setEstado("1");
				acuerdos.edit(acuerdo);
				buscarAcuerdo();
				FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
						new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_ACTIVO_EXITOSAMENTE, ""));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_ACTIVO_ERROR, ""));
		}
	}


	private void cargaDatosRegistro() {
		acuerdo.setNombre(nombre);
		acuerdo.setNemonico(nemonico);
		acuerdo.setIdUsuarioCreacion(sesionControlador.getUsuarioSesion().getId().intValue()); 
		acuerdo.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		acuerdo.setArchivoPdf("S/R");
		acuerdo.setEstado("1"); // Activo
		acuerdo.setIpUsuario(sesionControlador.getIpAdressLocal());
	}

	public void setearCrearAcuerdo() {
		crearOeditar = 1; // bandera de crear acuerdo
		setNombre("");
		setNemonico("");
	}

	public void setearEditarAcuerdo() {
		crearOeditar = 2; // bandera de editar acuerdo
	}

	private boolean validarCamposVacios() {
		return (nombre.isEmpty() || nemonico.isEmpty()) ? true : false;
	}

	public void crearRegistroAcuerdo() {
		try {
			acuerdo = new Acuerdo();
			cargaDatosRegistro();
			acuerdos.create(acuerdo);
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_GUARDADO_EXITOSO, ""));
		} catch (Exception ex) {
			ex.printStackTrace();
			if (Util.getRootException(ex).getLocalizedMessage()
					.contains("llave duplicada viola restricción de unicidad")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_CODIGO_DUPLICADO, ""));
			} else {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_GUARDAR_REGISTRO, ""));
			}
		}
	}

	public void acuerdoSeleccionadoEditar(Object[] object) {
		setearEditarAcuerdo();
		this.acuerdo = (Acuerdo) (object[0]);
		setNemonico(acuerdo.getNemonico());
		setNombre(acuerdo.getNombre());
	}

	public void acuerdoSeleccionadoActivar(Object[] object) {
		this.acuerdo = (Acuerdo) (object[0]);
	}

	public void acuerdoSeleccionadoBorrar(Object[] object) {
		this.acuerdoBorrado = (Acuerdo) (object[0]);
	}
	
	public void acuerdoSeleccionadoEliminar(Object[] object) {
		this.acuerdoEliminado = (Acuerdo) (object[0]);
	}

	public void editarAcuerdo() {
		try {
			acuerdo.setNombre(nombre);
			acuerdo.setNemonico(nemonico);
			acuerdos.edit(acuerdo);
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_EDITO_EXITOSAMENTE, ""));

		} catch (Exception ex) {
			ex.printStackTrace();
			if (Util.getRootException(ex).getLocalizedMessage()
					.contains("llave duplicada viola restricción de unicidad")) {
				FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
						new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERROR_BBDD_CODIGO_DUPLICADO_EDITAR, ""));
			}else {
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.REGISTRO_BBDD_EDITO_ERROR, ""));
		    }
		}
	}

	public void borrarAcuerdoLogico() {
		if (acuerdoBorrado.getEstado().contentEquals("0")) {
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.REGISTRO_BBDD_INACTIVO, ""));
		} else if (buscarDependenciaPorCodigoProgramaEbja(acuerdoBorrado)) {
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo", new FacesMessage(
					FacesMessage.SEVERITY_FATAL,
					"No se puede inactivar el registro, porque existen datos relacionados en Ofertas Educativas", ""));
		} else {
			acuerdoBorrado.setEstado("0");
			acuerdos.edit(acuerdoBorrado);
			buscarAcuerdo();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_INACTIVO_EXITOSAMENTE, ""));
		}
	}
	
	public void eliminarAcuerdoLogico() {
		
		if (buscarDependenciaPorCodigoProgramaEbja(acuerdoEliminado)) {
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo", new FacesMessage(
					FacesMessage.SEVERITY_FATAL,
					"No se puede eliminar el registro, porque existen datos relacionados en Ofertas Educativas", ""));
		} else {
			acuerdoEliminado.setEstado("3");
			acuerdos.edit(acuerdoEliminado);
			buscarAcuerdo();
			FacesContext.getCurrentInstance().addMessage("frmForm:messageAcuerdo",
					new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.REGISTRO_BBDD_ELIMINO_EXITOSAMENTE, ""));
		}
	}
	
	public boolean buscarDependenciaPorCodigoProgramaEbja(Acuerdo tmpAcuerdo) {
	
		boolean tmpcoincidenciaAcuerdo = false;
		for(ProgramaEbja tmpProgEbja: modulos.buscarProgramaEbjaActivos()) {
			for (Acuerdo tmpAcuerdos : tmpProgEbja.getAcuerdos()) {
		       if (tmpAcuerdos.getNemonico().contentEquals(tmpAcuerdo.getNemonico())) {
		    	   tmpcoincidenciaAcuerdo = true;
		    	   break;
		       }
			}
			if(tmpcoincidenciaAcuerdo == true) {
		    	   break;
		    }
		}
		return tmpcoincidenciaAcuerdo;
	}

	public void borrarAcuerdoFisico() {
		acuerdos.remove(acuerdo);
		buscarAcuerdo();
	}

	/*------------------------------------Getters and Setters---------------------------------------*/
	/*----------------------------------------------------------------------------------------------*/

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

	public List<Object[]> getListaAcuerdos() {
		return listaAcuerdos;
	}

	public void setListaAcuerdos(List<Object[]> listaAcuerdos) {
		this.listaAcuerdos = listaAcuerdos;
	}

	public String getArchivoPdf() {
		return archivoPdf;
	}

	public void setArchivoPdf(String archivoPdf) {
		this.archivoPdf = archivoPdf;
	}

	public Acuerdo getAcuerdo() {
		return acuerdo;
	}

	public void setAcuerdo(Acuerdo acuerdo) {
		this.acuerdo = acuerdo;
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

	public String getPathGuardar() {
		return pathGuardar;
	}

	public void setPathGuardar(String pathGuardar) {
		this.pathGuardar = pathGuardar;
	}
}
