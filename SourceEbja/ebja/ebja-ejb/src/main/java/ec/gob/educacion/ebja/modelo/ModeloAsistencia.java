package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="modelo_asistencia",schema="ebja")
@NamedQuery(name="ModeloAsistencia.findAll", query="SELECT m FROM ModeloAsistencia m")
public class ModeloAsistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ModeloAsistencia_generador", sequenceName = "ebja.modelo_asistencia_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="ModeloAsistencia_generador")
	private Integer id;

	private String estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="id_usuario_creacion__")
	private Integer idUsuarioCreacion;
	
	@Column(name="ip_usuario")
	private String ipUsuario;

	@Column(name="porcentaje_asistencia_materia")
	private Integer porcentajeAsistenciaMateria;

	@Column(name="porcentaje_asistencia_parcial")
	private Integer porcentajeAsistenciaParcial;
	
	@Column(name="id_tipo_asistencia_catalogo")
	private Integer catalogoTipoAsistencia;

	//bi-directional many-to-one association to ProgramaEbja
	@ManyToOne
	@JoinColumn(name="id_matricula")
	private Matricula matricula;

	public ModeloAsistencia() {
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

	public Integer getPorcentajeAsistenciaMateria() {
		return this.porcentajeAsistenciaMateria;
	}

	public void setPorcentajeAsistenciaMateria(Integer porcentajeAsistenciaMateria) {
		this.porcentajeAsistenciaMateria = porcentajeAsistenciaMateria;
	}

	public Integer getPorcentajeAsistenciaParcial() {
		return this.porcentajeAsistenciaParcial;
	}

	public void setPorcentajeAsistenciaParcial(Integer porcentajeAsistenciaParcial) {
		this.porcentajeAsistenciaParcial = porcentajeAsistenciaParcial;
	}
	
	

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Integer getCatalogoTipoAsistencia() {
		return catalogoTipoAsistencia;
	}

	public void setCatalogoTipoAsistencia(Integer catalogoTipoAsistencia) {
		this.catalogoTipoAsistencia = catalogoTipoAsistencia;
	}

	
}