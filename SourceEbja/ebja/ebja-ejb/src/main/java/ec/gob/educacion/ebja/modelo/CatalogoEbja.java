package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="catalogo_ebja",schema="ebja")
@NamedQueries({ @NamedQuery(name="CatalogoEbja.findAll", query="SELECT c FROM CatalogoEbja c"),
			@NamedQuery(name = "CatalogoEbja.findById", query = "SELECT c FROM CatalogoEbja c WHERE c.id = :id"),
			@NamedQuery(name = "CatalogoEbja.findByNombre", query = "SELECT c FROM CatalogoEbja c WHERE c.nombre = :nombre"),
			@NamedQuery(name = "CatalogoEbja.findByDescripcion", query = "SELECT c FROM CatalogoEbja c WHERE c.descripcion = :descripcion"),
			@NamedQuery(name = "CatalogoEbja.findByNemonico", query = "SELECT c FROM CatalogoEbja c WHERE c.nemonico = :nemonico"),
			@NamedQuery(name = "CatalogoEbja.findByEstado", query = "SELECT c FROM CatalogoEbja c WHERE c.estado = :estado") })
public class CatalogoEbja implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @SequenceGenerator(name = "catalogoEbja_generador", sequenceName = "ebja.catalogo_ebja_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "catalogoEbja_generador")
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

	@Size(max = 800)
	@Column(name = "descripcion")
	private String descripcion;

	@Basic(optional = false)
	@NotNull
	@Column(name = "estado")
	private String estado;

	@Size(max = 25)
	@Column(name = "nemonico")
	private String nemonico;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;
	
	@Column(name="ip_usuario")
	private String ipUsuario;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "nombre")
	private String nombre;

	//bi-directional many-to-one association to TipoCatalogoEbja
	@ManyToOne
	@JoinColumn(name="id_tipo_catalogo_ebja")
	private TipoCatalogoEbja tipoCatalogoEbja;

	public CatalogoEbja() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNemonico() {
		return this.nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
	}
	
	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getIdUsuarioCreacion() {
		return this.idUsuarioCreacion;
	}

	public void setIdUsuarioCreacion(Integer idUsuarioCreacion) {
		this.idUsuarioCreacion = idUsuarioCreacion;
	}
	
	public String getIpUsuario() {
		return this.ipUsuario;
	}

	public void setIpUsuario(String ipUsuario) {
		this.ipUsuario = ipUsuario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoCatalogoEbja getTipoCatalogoEbja() {
		return this.tipoCatalogoEbja;
	}

	public void setTipoCatalogoEbja(TipoCatalogoEbja tipoCatalogoEbja) {
		this.tipoCatalogoEbja = tipoCatalogoEbja;
	}
	
	
}