package ec.gob.educacion.ebja.modelo.zeus;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "catalogo")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Catalogo.findAll", query = "SELECT c FROM Catalogo c"),
		@NamedQuery(name = "Catalogo.findById", query = "SELECT c FROM Catalogo c WHERE c.id = :id"),
		@NamedQuery(name = "Catalogo.findByNombre", query = "SELECT c FROM Catalogo c WHERE c.nombre = :nombre"),
		@NamedQuery(name = "Catalogo.findByDescripcion", query = "SELECT c FROM Catalogo c WHERE c.descripcion = :descripcion"),
		@NamedQuery(name = "Catalogo.findByNemonico", query = "SELECT c FROM Catalogo c WHERE c.nemonico = :nemonico"),
		@NamedQuery(name = "Catalogo.findByIdUsuarioCreacion", query = "SELECT c FROM Catalogo c WHERE c.idUsuarioCreacion = :idUsuarioCreacion"),
		@NamedQuery(name = "Catalogo.findByFechaCreacion", query = "SELECT c FROM Catalogo c WHERE c.fechaCreacion = :fechaCreacion"),
		@NamedQuery(name = "Catalogo.findByEstado", query = "SELECT c FROM Catalogo c WHERE c.estado = :estado"),
		@NamedQuery(name = "Catalogo.findByFechaMigracion", query = "SELECT c FROM Catalogo c WHERE c.fechaMigracion = :fechaMigracion") })
public class Catalogo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_catalogo", referencedColumnName = "id")
    private TipoCatalogo idTipoCatalogo;
    
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "nombre")
	private String nombre;
	
	@Size(max = 800)
	@Column(name = "descripcion")
	private String descripcion;
	
	@Size(max = 25)
	@Column(name = "nemonico")
	private String nemonico;
	
	@Column(name = "id_usuario_creacion")
	private Integer idUsuarioCreacion;
	
	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	
	@Basic(optional = false)
	@NotNull
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "fecha_migracion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaMigracion;

	public Catalogo() {
	}

	public Catalogo(Integer id) {
		this.id = id;
	}

	public Catalogo(Integer id, String nombre, String estado) {
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNemonico() {
		return nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaMigracion() {
		return fechaMigracion;
	}

	public void setFechaMigracion(Date fechaMigracion) {
		this.fechaMigracion = fechaMigracion;
	}

	public TipoCatalogo getIdTipoCatalogo() {
		return idTipoCatalogo;
	}

	public void setIdTipoCatalogo(TipoCatalogo idTipoCatalogo) {
		this.idTipoCatalogo = idTipoCatalogo;
	}
}
