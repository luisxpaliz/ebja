package ec.gob.educacion.ebja.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the programa_educativo database table.
 * 
 */
@Entity
@Table(name="programa_educativo",schema="ebja")
@NamedQueries({
@NamedQuery(name="ProgramaEducativo.findAllActive", query="SELECT p FROM ProgramaEducativo p where p.estado='1'"),
@NamedQuery(name="ProgramaEducativo.findAllPCEICPL", query="SELECT p FROM ProgramaEducativo p where p.estado='1' and p.nemonico in ('PCEI','CPL')")})
public class ProgramaEducativo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "programa_educativo", sequenceName = "ebja.programa_educativo_id_programa_educativo_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "programa_educativo")
	@Column(name="id_programa_educativo")
	private Integer idProgramaEducativo;

	private String estado;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="id_usuario_creacion")
	private Integer idUsuarioCreacion;

	@Column(name="ip_usuario")
	private String ipUsuario;

	private String nemonico;

	@Column(name="nombre_programa")
	private String nombrePrograma;
	
	@OneToMany(mappedBy="programaEducativo")
	private List<GrupoFasePrograma> grupoFaseProgramas;

	public ProgramaEducativo() {
	}

	public Integer getIdProgramaEducativo() {
		return this.idProgramaEducativo;
	}

	public void setIdProgramaEducativo(Integer idProgramaEducativo) {
		this.idProgramaEducativo = idProgramaEducativo;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
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

	public String getNombrePrograma() {
		return this.nombrePrograma;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	public List<GrupoFasePrograma> getGrupoFaseProgramas() {
		return grupoFaseProgramas;
	}

	public void setGrupoFaseProgramas(List<GrupoFasePrograma> grupoFaseProgramas) {
		this.grupoFaseProgramas = grupoFaseProgramas;
	}
	
	
	
}