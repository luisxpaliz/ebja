package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "acuerdo",schema="ebja")
@NamedQueries({
@NamedQuery(name="Acuerdo.findByCodigo", query="SELECT c FROM Acuerdo c WHERE c.nemonico = :nemonico"),
@NamedQuery(name="Acuerdo.findAll", query="SELECT a FROM Acuerdo a where a.estado = '1' and a.estado = '0'"),
@NamedQuery(name="Acuerdo.findActiveAll", query="SELECT a FROM Acuerdo a where a.estado = '1'"),
@NamedQuery(name="Acuerdo.findByNombre", query="SELECT c FROM Acuerdo c WHERE c.nombre = :nombre")})
public class Acuerdo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "acuerdo_generador", sequenceName = "ebja.acuerdo_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="acuerdo_generador")
	private long id;

	@Column(name="archivo_pdf")
	private String archivoPdf;

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
	
//	//bi-directional many-to-one association to ProgramaAcuerdo
//	@OneToMany(mappedBy="acuerdo")
//	private List<ProgramaAcuerdo> programaAcuerdos;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "acuerdos")
	private Set<ProgramaEbja> programaEbja; 
	
	public Acuerdo() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getArchivoPdf() {
		return this.archivoPdf;
	}

	public void setArchivoPdf(String archivoPdf) {
		this.archivoPdf = archivoPdf;
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
	
//	public List<ProgramaAcuerdo> getProgramaAcuerdos() {
//		return this.programaAcuerdos;
//	}
//
//	public void setProgramaAcuerdos(List<ProgramaAcuerdo> programaAcuerdos) {
//		this.programaAcuerdos = programaAcuerdos;
//	}

//	public ProgramaAcuerdo addProgramaAcuerdo(ProgramaAcuerdo programaAcuerdo) {
//		getProgramaAcuerdos().add(programaAcuerdo);
//		programaAcuerdo.setAcuerdo(this);
//
//		return programaAcuerdo;
//	}
//
//	public ProgramaAcuerdo removeProgramaAcuerdo(ProgramaAcuerdo programaAcuerdo) {
//		getProgramaAcuerdos().remove(programaAcuerdo);
//		programaAcuerdo.setAcuerdo(null);
//
//		return programaAcuerdo;
//	}

}
