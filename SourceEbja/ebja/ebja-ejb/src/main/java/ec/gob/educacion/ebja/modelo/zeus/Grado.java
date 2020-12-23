package ec.gob.educacion.ebja.modelo.zeus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import ec.gob.educacion.ebja.modelo.Malla;

@Entity
@Table(name = "grado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grado.findAll", query = "SELECT g FROM Grado g") 
    , @NamedQuery(name = "Grado.findAllActive", query = "SELECT g FROM Grado g where g.estado ='1'") 
    , @NamedQuery(name = "Grado.findById", query = "SELECT g FROM Grado g WHERE g.id = :id")
    , @NamedQuery(name = "Grado.findByDescripcion", query = "SELECT g FROM Grado g WHERE g.descripcion = :descripcion")
    , @NamedQuery(name = "Grado.findByNemonico", query = "SELECT g FROM Grado g WHERE g.nemonico = :nemonico")
    , @NamedQuery(name = "Grado.findByFechaCreacion", query = "SELECT g FROM Grado g WHERE g.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Grado.findByEstado", query = "SELECT g FROM Grado g WHERE g.estado = :estado")
    , @NamedQuery(name = "Grado.findByDescripcionNemonico", query = "SELECT g FROM Grado g WHERE g.descripcionNemonico = :descripcionNemonico")
    , @NamedQuery(name = "Grado.findBySiguienteGrado", query = "SELECT g FROM Grado g WHERE g.siguienteGrado = :siguienteGrado")
    , @NamedQuery(name = "Grado.findByFechaMigracion", query = "SELECT g FROM Grado g WHERE g.fechaMigracion = :fechaMigracion")})
public class Grado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nemonico")
    private String nemonico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private String estado;
    @Size(max = 100)
    @Column(name = "descripcion_nemonico")
    private String descripcionNemonico;
    @Size(max = 100)
    @Column(name = "siguiente_grado")
    private String siguienteGrado;
    @Column(name = "fecha_migracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMigracion;
    @JoinColumn(name = "id_nivel", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Nivel idNivel;
/*    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrado", fetch = FetchType.LAZY)
    private Set<Curso> cursoList;
*/
    
    @Transient
    private String nivelLetras;
    @Transient
    private boolean presentaMalla;
    
    @Column(name = "mostrar_grado")
    private int mostrarGrado;
    
    public Grado() {
    }

    public Grado(Integer id) {
        this.id = id;
    }

    public Grado(Integer id, String descripcion, String nemonico, Date fechaCreacion, String estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.nemonico = nemonico;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getDescripcionNemonico() {
        return descripcionNemonico;
    }

    public void setDescripcionNemonico(String descripcionNemonico) {
        this.descripcionNemonico = descripcionNemonico;
    }

    public String getSiguienteGrado() {
        return siguienteGrado;
    }

    public void setSiguienteGrado(String siguienteGrado) {
        this.siguienteGrado = siguienteGrado;
    }

    public Date getFechaMigracion() {
        return fechaMigracion;
    }

    public void setFechaMigracion(Date fechaMigracion) {
        this.fechaMigracion = fechaMigracion;
    }

    public Nivel getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Nivel idNivel) {
        this.idNivel = idNivel;
    }
/*
    @XmlTransient
    public Set<Curso> getCursoList() {
        return cursoList;
    }

    public void setCursoList(Set<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    @XmlTransient
    public List<Malla> getMallaList() {
        return mallaList;
    }

    public void setMallaList(List<Malla> mallaList) {
        this.mallaList = mallaList;
    }
*/
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grado)) {
            return false;
        }
        Grado other = (Grado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.educacion.ebja.modelo.zeus.Grado[ id=" + id + " ]";
    }

	public String getNivelLetras() {
		/*nivelLetras = "";
		try {
			if(this.getIdNivel().getNemonico().equals(NivelEnum.CIENCIAS.getNemonico()) || this.getIdNivel().getNemonico().equals(NivelEnum.TECNICO.getNemonico())){
				nivelLetras = " - "+this.getIdNivel().getDescripcion();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}*/
		return nivelLetras;
	}
	
	public void setNivelLetras(String nivelLetras) {
		this.nivelLetras = nivelLetras;
	}

	public boolean isPresentaMalla() {
		return presentaMalla;
	}

	public void setPresentaMalla(boolean presentaMalla) {
		this.presentaMalla = presentaMalla;
	}

    public void setNemonico(String nemonico) {
        this.nemonico = nemonico;
    }
    
    public String getNemonico() {
        return nemonico;
    }

	public int getMostrarGrado() {
		return mostrarGrado;
	}

	public void setMostrarGrado(int mostrarGrado) {
		this.mostrarGrado = mostrarGrado;
	}
}
