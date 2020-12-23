package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;	
	@Column(name = "id_persona")
	private Integer idPersona;	
	private String usuario;	
	private String contrasenia;
	@Column(name = "numero_conexiones")
	private Short numeroConexiones;
	@Column(name = "primera_vez")
	private Boolean primeraVez;
	@Column(name = "fecha_ultima_conexion")
	private Date fechaUltimaConexion;
	@Column(name = "id_usuario_creacion")
	private Integer idUsuarioCreacion;
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;	
	private Character estado;
	
	@Column(name = "fecha_peticion")
	private Date fechaPeticionUsuario;
	@Column(name = "estado_peticion")
	private Short estadoPeticion;
	@Column(name = "token_solicitud")
	private String tokenSolicitud;
	@Column(name = "tipo_usuario_solicitado")
	private String tipoUsuarioSolicitado;
	
	public Usuario() {
		//Constructor vacio
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Short getNumeroConexiones() {
		return numeroConexiones;
	}

	public void setNumeroConexiones(Short numeroConexiones) {
		this.numeroConexiones = numeroConexiones;
	}

	public Boolean getPrimeraVez() {
		return primeraVez;
	}

	public void setPrimeraVez(Boolean primeraVez) {
		this.primeraVez = primeraVez;
	}

	public Date getFechaUltimaConexion() {
		return fechaUltimaConexion;
	}

	public void setFechaUltimaConexion(Date fechaUltimaConexion) {
		this.fechaUltimaConexion = fechaUltimaConexion;
	}

	public Integer getIdUsuarioCreacion() {
		return idUsuarioCreacion;
	}

	public void setIdUsuarioCreacion(Integer idUsuarioCreacion) {
		this.idUsuarioCreacion = idUsuarioCreacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Character getEstado() {
		return estado;
	}

	public void setEstado(Character estado) {
		this.estado = estado;
	}

	public Date getFechaPeticionUsuario() {
		return fechaPeticionUsuario;
	}

	public void setFechaPeticionUsuario(Date fechaPeticionUsuario) {
		this.fechaPeticionUsuario = fechaPeticionUsuario;
	}

	public Short getEstadoPeticion() {
		return estadoPeticion;
	}

	public void setEstadoPeticion(Short estadoPeticion) {
		this.estadoPeticion = estadoPeticion;
	}

	public String getTokenSolicitud() {
		return tokenSolicitud;
	}

	public void setTokenSolicitud(String tokenSolicitud) {
		this.tokenSolicitud = tokenSolicitud;
	}

	public String getTipoUsuarioSolicitado() {
		return tipoUsuarioSolicitado;
	}

	public void setTipoUsuarioSolicitado(String tipoUsuarioSolicitado) {
		this.tipoUsuarioSolicitado = tipoUsuarioSolicitado;
	}

}
