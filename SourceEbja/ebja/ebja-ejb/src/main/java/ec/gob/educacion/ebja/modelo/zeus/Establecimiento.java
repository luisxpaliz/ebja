package ec.gob.educacion.ebja.modelo.zeus;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "establecimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Establecimiento.findAll", query = "SELECT e FROM Establecimiento e")
    , @NamedQuery(name = "Establecimiento.findById", query = "SELECT e FROM Establecimiento e WHERE e.id = :id")
    , @NamedQuery(name = "Establecimiento.findByCodigoCuadrante", query = "SELECT e FROM Establecimiento e WHERE e.codigoCuadrante = :codigoCuadrante")
    , @NamedQuery(name = "Establecimiento.findBySuperficieCalculada", query = "SELECT e FROM Establecimiento e WHERE e.superficieCalculada = :superficieCalculada")
    , @NamedQuery(name = "Establecimiento.findBySuperficieEscritura", query = "SELECT e FROM Establecimiento e WHERE e.superficieEscritura = :superficieEscritura")
    , @NamedQuery(name = "Establecimiento.findByFechaInicio", query = "SELECT e FROM Establecimiento e WHERE e.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Establecimiento.findByFechaFin", query = "SELECT e FROM Establecimiento e WHERE e.fechaFin = :fechaFin")
    , @NamedQuery(name = "Establecimiento.findByEstadoVigencia", query = "SELECT e FROM Establecimiento e WHERE e.estadoVigencia = :estadoVigencia")
    , @NamedQuery(name = "Establecimiento.findByEstado", query = "SELECT e FROM Establecimiento e WHERE e.estado = :estado")
    , @NamedQuery(name = "Establecimiento.findByObservacion", query = "SELECT e FROM Establecimiento e WHERE e.observacion = :observacion")
    , @NamedQuery(name = "Establecimiento.findByFechaMigracion", query = "SELECT e FROM Establecimiento e WHERE e.fechaMigracion = :fechaMigracion")})
public class Establecimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "codigo_cuadrante")
    private String codigoCuadrante;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "superficie_calculada")
    private BigDecimal superficieCalculada;
    @Column(name = "superficie_escritura")
    private BigDecimal superficieEscritura;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "estado_vigencia")
    private String estadoVigencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private String estado;
    @Size(max = 255)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "fecha_migracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMigracion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstablecimiento", fetch = FetchType.LAZY)
    private List<Coordenada> coordenadaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstablecimiento", fetch = FetchType.LAZY)
    private List<InstitucEstablec> institucEstablecList;

    public Establecimiento() {
    }

    public Establecimiento(Integer id) {
        this.id = id;
    }

    public Establecimiento(Integer id, String codigoCuadrante, String estado) {
        this.id = id;
        this.codigoCuadrante = codigoCuadrante;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoCuadrante() {
        return codigoCuadrante;
    }

    public void setCodigoCuadrante(String codigoCuadrante) {
        this.codigoCuadrante = codigoCuadrante;
    }

    public BigDecimal getSuperficieCalculada() {
        return superficieCalculada;
    }

    public void setSuperficieCalculada(BigDecimal superficieCalculada) {
        this.superficieCalculada = superficieCalculada;
    }

    public BigDecimal getSuperficieEscritura() {
        return superficieEscritura;
    }

    public void setSuperficieEscritura(BigDecimal superficieEscritura) {
        this.superficieEscritura = superficieEscritura;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstadoVigencia() {
        return estadoVigencia;
    }

    public void setEstadoVigencia(String estadoVigencia) {
        this.estadoVigencia = estadoVigencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaMigracion() {
        return fechaMigracion;
    }

    public void setFechaMigracion(Date fechaMigracion) {
        this.fechaMigracion = fechaMigracion;
    }

    @XmlTransient
    public List<Coordenada> getCoordenadaList() {
        return coordenadaList;
    }

    public void setCoordenadaList(List<Coordenada> coordenadaList) {
        this.coordenadaList = coordenadaList;
    }

    @XmlTransient
    public List<InstitucEstablec> getInstitucEstablecList() {
        return institucEstablecList;
    }

    public void setInstitucEstablecList(List<InstitucEstablec> institucEstablecList) {
        this.institucEstablecList = institucEstablecList;
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
        if (!(object instanceof Establecimiento)) {
            return false;
        }
        Establecimiento other = (Establecimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.educacion.ebja.modelo.zeus.Establecimiento[ id=" + id + " ]";
    }
    
}
