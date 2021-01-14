package ec.gob.educacion.ebja.modelo.zeus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "circuito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Circuito.findAll", query = "SELECT c FROM Circuito c")
    , @NamedQuery(name = "Circuito.findById", query = "SELECT c FROM Circuito c WHERE c.id = :id")
    , @NamedQuery(name = "Circuito.findByCircuito", query = "SELECT c FROM Circuito c WHERE c.idDistrito.id = :idDistrito and c.estado = :estado")
    , @NamedQuery(name = "Circuito.findByCodigoSenpladesCircuito", query = "SELECT c FROM Circuito c WHERE c.codigoSenpladesCircuito = :codigoSenpladesCircuito")
    , @NamedQuery(name = "Circuito.findByDescripcion", query = "SELECT c FROM Circuito c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "Circuito.findByFechaCreacion", query = "SELECT c FROM Circuito c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Circuito.findByEstado", query = "SELECT c FROM Circuito c WHERE c.estado = :estado")
    , @NamedQuery(name = "Circuito.findByZona", query = "SELECT c FROM Circuito c WHERE c.zona = :zona")
    , @NamedQuery(name = "Circuito.findByFechaMigracion", query = "SELECT c FROM Circuito c WHERE c.fechaMigracion = :fechaMigracion")})
public class Circuito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "codigo_senplades_circuito")
    private String codigoSenpladesCircuito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private String estado;
    
    @Column(name = "zona")
    private Character zona;
    
    @Column(name = "fecha_migracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMigracion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCircuito", fetch = FetchType.EAGER)
    private List<CircuitoParroquia> circuitoParroquiaList;
    
    @JoinColumn(name = "id_distrito", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Distrito idDistrito;

    public Circuito() {
    }

    public Circuito(Integer id) {
        this.id = id;
    }

    public Circuito(Integer id, String codigoSenpladesCircuito, String descripcion, Date fechaCreacion, String estado) {
        this.id = id;
        this.codigoSenpladesCircuito = codigoSenpladesCircuito;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoSenpladesCircuito() {
        return codigoSenpladesCircuito;
    }

    public void setCodigoSenpladesCircuito(String codigoSenpladesCircuito) {
        this.codigoSenpladesCircuito = codigoSenpladesCircuito;
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

    public Character getZona() {
        return zona;
    }

    public void setZona(Character zona) {
        this.zona = zona;
    }

    public Date getFechaMigracion() {
        return fechaMigracion;
    }

    public void setFechaMigracion(Date fechaMigracion) {
        this.fechaMigracion = fechaMigracion;
    }

    @XmlTransient
    public List<CircuitoParroquia> getCircuitoParroquiaList() {
        return circuitoParroquiaList;
    }

    public void setCircuitoParroquiaList(List<CircuitoParroquia> circuitoParroquiaList) {
        this.circuitoParroquiaList = circuitoParroquiaList;
    }

    public Distrito getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(Distrito idDistrito) {
        this.idDistrito = idDistrito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Circuito)) {
            return false;
        }
        Circuito other = (Circuito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.educacion.ebja.modelo.zeus.Circuito[ id=" + id + " ]";
    }
    
}
