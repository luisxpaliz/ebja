package ec.gob.educacion.ebja.modelo.seguridades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UsuarioSeg {

	private Long codigo;
	private String identificacion;
	private String nombre;
	private String nombres;
	private String apellidos;
	private String direccion;
	private String telefono;
	private String celular;
	private String correoElectronico;
	private String cambioClave;
	private String actualizacionDatos;
	private String tokenCambioClave;
	private Date fechaSolicitudClave;
	private Sede sede;
	private String estado;
	private String resu;
    private String ssap;
    private List<RolAplicacion> listaRolesAplicacion = new ArrayList<RolAplicacion>();
    private String observacion;
    private String prefijoApp;
    private Boolean accesoConcedido;
    private String cedula;
    private String claveGenerada;
    private Long id;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getCambioClave() {
		return cambioClave;
	}
	public void setCambioClave(String cambioClave) {
		this.cambioClave = cambioClave;
	}
	public String getActualizacionDatos() {
		return actualizacionDatos;
	}
	public void setActualizacionDatos(String actualizacionDatos) {
		this.actualizacionDatos = actualizacionDatos;
	}
	public String getTokenCambioClave() {
		return tokenCambioClave;
	}
	public void setTokenCambioClave(String tokenCambioClave) {
		this.tokenCambioClave = tokenCambioClave;
	}
	public Date getFechaSolicitudClave() {
		return fechaSolicitudClave;
	}
	public void setFechaSolicitudClave(Date fechaSolicitudClave) {
		this.fechaSolicitudClave = fechaSolicitudClave;
	}
	public Sede getSede() {
		return sede;
	}
	public void setSede(Sede sede) {
		this.sede = sede;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public List<RolAplicacion> getListaRolesAplicacion() {
		return listaRolesAplicacion;
	}
	public void setListaRolesAplicacion(List<RolAplicacion> listaRolesAplicacion) {
		this.listaRolesAplicacion = listaRolesAplicacion;
	}
	public String getResu() {
		return resu;
	}
	public void setResu(String resu) {
		this.resu = resu;
	}
	public String getSsap() {
		return ssap;
	}
	public void setSsap(String ssap) {
		this.ssap = ssap;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getPrefijoApp() {
		return prefijoApp;
	}
	public void setPrefijoApp(String prefijoApp) {
		this.prefijoApp = prefijoApp;
	}
	public Boolean getAccesoConcedido() {
		return accesoConcedido;
	}
	public void setAccesoConcedido(Boolean accesoConcedido) {
		this.accesoConcedido = accesoConcedido;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getClaveGenerada() {
		return claveGenerada;
	}
	public void setClaveGenerada(String claveGenerada) {
		this.claveGenerada = claveGenerada;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
		
}
