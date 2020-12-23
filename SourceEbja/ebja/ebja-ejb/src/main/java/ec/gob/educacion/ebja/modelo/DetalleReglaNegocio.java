package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name="detalle_regla_negocio",schema="ebja")
@NamedQuery(name="DetalleReglaNegocio.findAll", query="SELECT d FROM DetalleReglaNegocio d")
public class DetalleReglaNegocio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "detalle_regla_generador", sequenceName = "ebja.detalle_regla_negocio_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "detalle_regla_generador")
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;
	
	@Column(name="ip_usuario")
	private String ipUsuario;

	@Column(name="valor_regla")
	private String valorRegla;
	
	//bi-directional many-to-one association to RegistroEstudiante
	@ManyToOne
	@JoinColumn(name="id_regla_catalogo", referencedColumnName = "id")
	private CatalogoEbja catalogoRegla;

	//bi-directional many-to-one association to ReglaNegocio
	@ManyToOne
	@JoinColumn(name="id_regla_negocio")
	private ReglaNegocio reglaNegocio;

	public DetalleReglaNegocio() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getValorRegla() {
		return this.valorRegla;
	}

	public void setValorRegla(String valorRegla) {
		this.valorRegla = valorRegla;
	}
	
	public CatalogoEbja getCatalogoRegla() {
		return catalogoRegla;
	}

	public void setCatalogoRegla(CatalogoEbja catalogoRegla) {
		this.catalogoRegla = catalogoRegla;
	}

	public ReglaNegocio getReglaNegocio() {
		return this.reglaNegocio;
	}

	public void setReglaNegocio(ReglaNegocio reglaNegocio) {
		this.reglaNegocio = reglaNegocio;
	}

}