package ec.gob.educacion.ebja.controlador;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ec.gob.educacion.ebja.modelo.seguridades.UsuarioSeg;
import ec.gob.educacion.ebja.recursos.ClienteServicioWeb;
import ec.gob.educacion.ebja.recursos.Constantes;
import ec.gob.educacion.ebja.controlador.BaseControlador;

@Named
@ViewScoped
public class LoginControlador extends BaseControlador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(LoginControlador.class);
	private int numeroIntentos = 3;
	private Set<String> listaDuplicados;

	@Inject
	private SesionControlador sesionControlador;

	private String usuario;
	private String password;

	public LoginControlador() {

	}

	@PostConstruct
	public void init() {
		if (sesionControlador.getUsuarioSesion() != null) {
			redireccionarPagina("/faces/paginas/principal.xhtml");
		}

	}

	public String login() {
		try {

			String urlDireccionamiento = "";

			if (sesionControlador.getListaBloqueados().isEmpty()) {
				UsuarioSeg usuario = new UsuarioSeg();
				usuario.setResu(this.usuario);
				usuario.setSsap(this.password);
				usuario.setPrefijoApp(Constantes.PREFIJO_APLICACION_EBJA_WEB);
				Gson gson = new Gson();
				Type type = new TypeToken<UsuarioSeg>() {
				}.getType();
				String jsonEnvio = gson.toJson(usuario, type);
				String jsonRespuesta = ClienteServicioWeb.servicioWebPost(ClienteServicioWeb
						.getURLWebServiceAutenticacion(FacesContext.getCurrentInstance().getExternalContext()),
						jsonEnvio);
				usuario = gson.fromJson(jsonRespuesta, type);
				
				if (usuario != null && usuario.getAccesoConcedido() != null && usuario.getAccesoConcedido()) {
					sesionControlador.getListaIntentos().replace(this.usuario, new Integer(3));
					sesionControlador.setUsuarioSesion(usuario);
					sesionControlador.setRolesAplicacion(usuario.getListaRolesAplicacion());
					// VALIDACION DEL USUARIO
					urlDireccionamiento = "/paginas/principal.xhtml?faces-redirect=true";

				} else {

					if (sesionControlador.getListaIntentos().get(this.usuario) == null) {
						sesionControlador.getListaIntentos().put(this.usuario, new Integer(3));
					}
					numeroIntentos = sesionControlador.getListaIntentos().get(this.usuario).intValue();
					sesionControlador.getListaIngresos().add(this.usuario);
					numeroIntentos -= 1;
					listaDuplicados = new HashSet<String>(sesionControlador.getListaIngresos());
					int unaVez = 0;
					
					if (!listaDuplicados.isEmpty()) {
						for (String duplicado : listaDuplicados) {
							if (Collections.frequency(sesionControlador.getListaIngresos(), duplicado) > 2
									&& duplicado.contentEquals(this.usuario)) {
								sesionControlador.getListaBloqueados().add(duplicado);
								agregarMensajeError("frmLogin:btnLogin",
										"EL USUARIO " + this.usuario
												+ " HA SUPERADO LOS 3 INTENTOS Y SE ENCUENTRA BLOQUEADO POR 1 MINUTO.",
										"Error");
								int valorIngresos = sesionControlador.getListaIngresos().size() - 1;
								for (int i = valorIngresos; i >= 0; i--) {
									if (sesionControlador.getListaIngresos().get(i).contains(duplicado)) {
										sesionControlador.getListaIngresos().remove(i);
									}
								}
								
								sesionControlador.getListaIntentos().remove(duplicado);
								sesionControlador.setValorDuplicado(duplicado);
								sesionControlador.iniciarHiloBloqueo();
								
							} else {
								if (unaVez == 0) {
									agregarMensajeError("frmLogin:btnLogin",
											usuario.getObservacion() + " " + " SOLO DISPONE DE "
													+ sesionControlador.getListaIntentos().get(this.usuario).intValue()
													+ " INTENTOS  ",
											"Error");
									unaVez = 1;
								}
							}
						}
					}
					sesionControlador.getListaIntentos().replace(this.usuario, new Integer(numeroIntentos));

				}
			} else {
				int unaVezTres = 0;
				int bloqueadoFlag = 0;

				
				for (String bloqueado : sesionControlador.getListaBloqueados()) {
					

					if (bloqueado.contentEquals(this.usuario)) {
						bloqueadoFlag = 1;
					}

				}
				if (bloqueadoFlag == 1) {

					if (unaVezTres == 0) {
						agregarMensajeError("frmLogin:btnLogin", " EL USUARIO  " + this.usuario
								+ " HA SUPERADO LOS 3 INTENTOS Y SE ENCUENTRA BLOQUEADO POR 1 MINUTO", "Error");
						unaVezTres = 1;
					}
				} else {

					UsuarioSeg usuario = new UsuarioSeg();
					usuario.setResu(this.usuario);
					usuario.setSsap(this.password);
					usuario.setPrefijoApp(Constantes.PREFIJO_APLICACION_EBJA_WEB);
					Gson gson = new Gson();
					Type type = new TypeToken<UsuarioSeg>() {
					}.getType();
					String jsonEnvio = gson.toJson(usuario, type);
					String jsonRespuesta = ClienteServicioWeb.servicioWebPost(ClienteServicioWeb
							.getURLWebServiceAutenticacion(FacesContext.getCurrentInstance().getExternalContext()),
							jsonEnvio);
					usuario = gson.fromJson(jsonRespuesta, type);

					
					if (usuario != null && usuario.getAccesoConcedido() != null && usuario.getAccesoConcedido()) {
						sesionControlador.getListaIntentos().replace(this.usuario, new Integer(3));
						sesionControlador.setUsuarioSesion(usuario);
						sesionControlador.setRolesAplicacion(usuario.getListaRolesAplicacion());
						// VALIDACION DEL USUARIO
						urlDireccionamiento = "/paginas/principal.xhtml?faces-redirect=true";

					} else {

						if (sesionControlador.getListaIntentos().get(this.usuario) == null) {
							sesionControlador.getListaIntentos().put(this.usuario, new Integer(3));
						}
						numeroIntentos = sesionControlador.getListaIntentos().get(this.usuario).intValue();
						sesionControlador.getListaIngresos().add(this.usuario);
						numeroIntentos -= 1;
						listaDuplicados = new HashSet<String>(sesionControlador.getListaIngresos());
						int unaVezDos = 0;
						if (!listaDuplicados.isEmpty()) {
							for (String duplicado : listaDuplicados) {
								
								if (Collections.frequency(sesionControlador.getListaIngresos(), duplicado) > 2
										&& duplicado.contentEquals(this.usuario)) {
									sesionControlador.getListaBloqueados().add(duplicado);
									agregarMensajeError("frmLogin:btnLogin", "EL USUARIO " + this.usuario
											+ " HA SUPERADO LOS 3 INTENTOS Y SE ENCUENTRA BLOQUEADO POR 1 MINUTO.",
											"Error");
									int valorIngresos = sesionControlador.getListaIngresos().size() - 1;
									for (int i = valorIngresos; i >= 0; i--) {
										if (sesionControlador.getListaIngresos().get(i).contains(duplicado)) {
											sesionControlador.getListaIngresos().remove(i);
										}
									}
									
									sesionControlador.getListaIntentos().remove(duplicado);
									sesionControlador.setValorDuplicado(duplicado);
									sesionControlador.iniciarHiloBloqueo();
									
								} else {
									if (unaVezDos == 0) {
										agregarMensajeError("frmLogin:btnLogin",
												usuario.getObservacion()
														+ " " + " SOLO DISPONE DE " + sesionControlador
																.getListaIntentos().get(this.usuario).intValue()
														+ " INTENTOS  ",
												"Error");
										unaVezDos = 1;
									}
								}
							}
						}
						sesionControlador.getListaIntentos().replace(this.usuario, new Integer(numeroIntentos));
					}
				}
			}

			return urlDireccionamiento;

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Ocurrio un error en login " + e.getMessage(), e);
			agregarMensajeError("frmLogin:btnLogin",
					"Error al iniciar sesión. Por favor comuníquese con el administrador del sistema.", "Error");
		}
		return "";
	}

	public void borrarCampos() {
		setUsuario("");
		setPassword("");
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
