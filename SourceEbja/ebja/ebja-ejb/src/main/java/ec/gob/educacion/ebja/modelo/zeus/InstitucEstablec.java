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
@Table(name = "instituc_establec")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InstitucEstablec.findAll", query = "SELECT i FROM InstitucEstablec i")
    , @NamedQuery(name = "InstitucEstablec.findById", query = "SELECT i FROM InstitucEstablec i WHERE i.id = :id")
    , @NamedQuery(name = "InstitucEstablec.findByFechaInicio", query = "SELECT i FROM InstitucEstablec i WHERE i.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "InstitucEstablec.findByFechaFin", query = "SELECT i FROM InstitucEstablec i WHERE i.fechaFin = :fechaFin")
    , @NamedQuery(name = "InstitucEstablec.findByEstadoVigencia", query = "SELECT i FROM InstitucEstablec i WHERE i.estadoVigencia = :estadoVigencia")
    , @NamedQuery(name = "InstitucEstablec.findByEstado", query = "SELECT i FROM InstitucEstablec i WHERE i.estado = :estado")
    , @NamedQuery(name = "InstitucEstablec.findByObservacion", query = "SELECT i FROM InstitucEstablec i WHERE i.observacion = :observacion")
    , @NamedQuery(name = "InstitucEstablec.findByFechaMigracion", query = "SELECT i FROM InstitucEstablec i WHERE i.fechaMigracion = :fechaMigracion")})
public class InstitucEstablec implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_vigencia")
    private String estadoVigencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_uso")
    private String estadoUso;
    @Size(max = 255)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "fecha_migracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMigracion;
    @JoinColumn(name = "id_circuito_parroquia", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CircuitoParroquia idCircuitoParroquia;
    @JoinColumn(name = "id_establecimiento", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Establecimiento idEstablecimiento;
    @JoinColumn(name = "id_institucion", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Institucion idInstitucion;

    public InstitucEstablec() {
    }

    public InstitucEstablec(Integer id) {
        this.id = id;
    }

    public InstitucEstablec(Integer id, String estadoVigencia, String estado) {
        this.id = id;
        this.estadoVigencia = estadoVigencia;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    
    public String getEstadoUso() {
        return estadoUso;
    }

    public void setEstadoUso(String estadoUso) {
        this.estadoUso = estadoUso;
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

    public CircuitoParroquia getIdCircuitoParroquia() {
        return idCircuitoParroquia;
    }

    public void setIdCircuitoParroquia(CircuitoParroquia idCircuitoParroquia) {
        this.idCircuitoParroquia = idCircuitoParroquia;
    }

    public Establecimiento getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(Establecimiento idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    public Institucion getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(Institucion idInstitucion) {
        this.idInstitucion = idInstitucion;
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
        if (!(object instanceof InstitucEstablec)) {
            return false;
        }
        InstitucEstablec other = (InstitucEstablec) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.educacion.ebja.modelo.zeus.InstitucEstablec[ id=" + id + " ]";
    }   
}
