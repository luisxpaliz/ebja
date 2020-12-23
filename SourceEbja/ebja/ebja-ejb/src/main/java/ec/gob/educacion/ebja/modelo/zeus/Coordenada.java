/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "coordenada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coordenada.findAll", query = "SELECT c FROM Coordenada c")
    , @NamedQuery(name = "Coordenada.findById", query = "SELECT c FROM Coordenada c WHERE c.id = :id")
    , @NamedQuery(name = "Coordenada.findByCoordenadaNorte", query = "SELECT c FROM Coordenada c WHERE c.coordenadaNorte = :coordenadaNorte")
    , @NamedQuery(name = "Coordenada.findByCoordenadaEste", query = "SELECT c FROM Coordenada c WHERE c.coordenadaEste = :coordenadaEste")
    , @NamedQuery(name = "Coordenada.findByFechaCreacion", query = "SELECT c FROM Coordenada c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Coordenada.findByFachaInicio", query = "SELECT c FROM Coordenada c WHERE c.fachaInicio = :fachaInicio")
    , @NamedQuery(name = "Coordenada.findByFechaFin", query = "SELECT c FROM Coordenada c WHERE c.fechaFin = :fechaFin")
    , @NamedQuery(name = "Coordenada.findByCallePrincipal", query = "SELECT c FROM Coordenada c WHERE c.callePrincipal = :callePrincipal")
    , @NamedQuery(name = "Coordenada.findByNumeroLote", query = "SELECT c FROM Coordenada c WHERE c.numeroLote = :numeroLote")
    , @NamedQuery(name = "Coordenada.findByCalleSecundaria", query = "SELECT c FROM Coordenada c WHERE c.calleSecundaria = :calleSecundaria")
    , @NamedQuery(name = "Coordenada.findBySector", query = "SELECT c FROM Coordenada c WHERE c.sector = :sector")
    , @NamedQuery(name = "Coordenada.findByReferencia", query = "SELECT c FROM Coordenada c WHERE c.referencia = :referencia")
    , @NamedQuery(name = "Coordenada.findByEstado", query = "SELECT c FROM Coordenada c WHERE c.estado = :estado")
    , @NamedQuery(name = "Coordenada.findByFechaMigracion", query = "SELECT c FROM Coordenada c WHERE c.fechaMigracion = :fechaMigracion")})
public class Coordenada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "coordenada_norte")
    private Float coordenadaNorte;
    @Column(name = "coordenada_este")
    private Float coordenadaEste;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "facha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fachaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Size(max = 150)
    @Column(name = "calle_principal")
    private String callePrincipal;
    @Size(max = 10)
    @Column(name = "numero_lote")
    private String numeroLote;
    @Size(max = 150)
    @Column(name = "calle_secundaria")
    private String calleSecundaria;
    @Size(max = 80)
    @Column(name = "sector")
    private String sector;
    @Size(max = 100)
    @Column(name = "referencia")
    private String referencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_migracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMigracion;
    @JoinColumn(name = "id_establecimiento", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Establecimiento idEstablecimiento;

    public Coordenada() {
    }

    public Coordenada(Integer id) {
        this.id = id;
    }

    public Coordenada(Integer id, Date fechaCreacion, String estado) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getCoordenadaNorte() {
        return coordenadaNorte;
    }

    public void setCoordenadaNorte(Float coordenadaNorte) {
        this.coordenadaNorte = coordenadaNorte;
    }

    public Float getCoordenadaEste() {
        return coordenadaEste;
    }

    public void setCoordenadaEste(Float coordenadaEste) {
        this.coordenadaEste = coordenadaEste;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFachaInicio() {
        return fachaInicio;
    }

    public void setFachaInicio(Date fachaInicio) {
        this.fachaInicio = fachaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getCallePrincipal() {
        return callePrincipal;
    }

    public void setCallePrincipal(String callePrincipal) {
        this.callePrincipal = callePrincipal;
    }

    public String getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

    public String getCalleSecundaria() {
        return calleSecundaria;
    }

    public void setCalleSecundaria(String calleSecundaria) {
        this.calleSecundaria = calleSecundaria;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
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

    public Establecimiento getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(Establecimiento idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
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
        if (!(object instanceof Coordenada)) {
            return false;
        }
        Coordenada other = (Coordenada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.educacion.ebja.modelo.zeus.Coordenada[ id=" + id + " ]";
    }
    
}
