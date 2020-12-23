package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="tipo_catalogo_ebja",schema="ebja")
@XmlRootElement
@NamedQueries({ @NamedQuery(name="TipoCatalogoEbja.findAll", query="SELECT t FROM TipoCatalogoEbja t"),
	@NamedQuery(name="TipoCatalogoEbja.findAllEditable", query="SELECT t FROM TipoCatalogoEbja t where t.editable ='1'"),
	@NamedQuery(name="TipoCatalogoEbja.findByNemonico", query="SELECT t FROM TipoCatalogoEbja t where t.nemonico =:nemonico")})
public class TipoCatalogoEbja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "tipo_catalogo_generador", sequenceName = "ebja.tipo_catalogo_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="tipo_catalogo_generador")
	private Integer id;

	private String descripcion;

	private String editable;

	private String estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;
	
	@Column(name="ip_usuario")
	private String ipUsuario;

	private String nemonico;

	private String nombre;

	//bi-directional many-to-one association to CatalogoEbja
	@OneToMany(mappedBy="tipoCatalogoEbja")
	private List<CatalogoEbja> catalogoEbja;


	public TipoCatalogoEbja() {
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

	public String getEditable() {
		return this.editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public String getNemonico() {
		return this.nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<CatalogoEbja> getCatalogoEbja() {
		return this.catalogoEbja;
	}

	public void setCatalogoEbja(List<CatalogoEbja> catalogoEbja) {
		this.catalogoEbja = catalogoEbja;
	}

	public CatalogoEbja addCatalogoEbja(CatalogoEbja catalogoEbja) {
		getCatalogoEbja().add(catalogoEbja);
		catalogoEbja.setTipoCatalogoEbja(this);

		return catalogoEbja;
	}

	public CatalogoEbja removeCatalogoEbja(CatalogoEbja catalogoEbja) {
		getCatalogoEbja().remove(catalogoEbja);
		catalogoEbja.setTipoCatalogoEbja(null);

		return catalogoEbja;
	}

}