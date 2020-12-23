package ec.gob.educacion.ebja.modelo.asignaciones;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="MV_CEDULADO_MEDUCACION")
@NamedQuery(name="CeduladoMeducacion.findAll", query="SELECT c FROM CeduladoMeducacion c")
public class CeduladoMeducacion implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  private BigDecimal cedula;
  @Column(name="CEDULA_MADRE")
  private BigDecimal cedulaMadre;
  @Column(name="CEDULA_PADRE")
  private BigDecimal cedulaPadre;
  @Column(name="COD_CONDICION_CEDULADO")
  private BigDecimal codCondicionCedulado;
  @Column(name="COD_DOMICILIO")
  private BigDecimal codDomicilio;
  @Column(name="COD_ESTADO_CIVIL")
  private BigDecimal codEstadoCivil;
  @Column(name="COD_INSTRUCCION")
  private BigDecimal codInstruccion;
  @Column(name="COD_LUGAR_MATRIMONIO")
  private BigDecimal codLugarMatrimonio;
  @Column(name="COD_LUGAR_NACIMIENTO")
  private BigDecimal codLugarNacimiento;
  @Column(name="COD_NACIONALIDAD")
  private BigDecimal codNacionalidad;
  @Column(name="COD_PROFESION")
  private String codProfesion;
  @Column(name="COD_SEXO")
  private Long codSexo;
  @Temporal(TemporalType.DATE)
  @Column(name="FECHA_EXPEDICION_CED")
  private Date fechaExpedicionCed;
  @Temporal(TemporalType.DATE)
  @Column(name="FECHA_FALLECIMIENTO")
  private Date fechaFallecimiento;
  @Temporal(TemporalType.DATE)
  @Column(name="FECHA_MATRIMONIO")
  private Date fechaMatrimonio;
  @Temporal(TemporalType.DATE)
  @Column(name="FECHA_NACIMIENTO")
  private Date fechaNacimiento;
  @Column(name="NOMBRE_CALLE")
  private String nombreCalle;
  @Column(name="NOMBRE_CONYUGE")
  private String nombreConyuge;
  @Column(name="NOMBRE_MADRE")
  private String nombreMadre;
  @Column(name="NOMBRE_PADRE")
  private String nombrePadre;
  private String nombres;
  @Column(name="NUMERO_CASA")
  private String numeroCasa;
  
  public CeduladoMeducacion() {}
  
  public BigDecimal getCedula()
  {
    return cedula;
  }
  
  public void setCedula(BigDecimal cedula) {
    this.cedula = cedula;
  }
  
  public BigDecimal getCedulaMadre() {
    return cedulaMadre;
  }
  
  public void setCedulaMadre(BigDecimal cedulaMadre) {
    this.cedulaMadre = cedulaMadre;
  }
  
  public BigDecimal getCedulaPadre() {
    return cedulaPadre;
  }
  
  public void setCedulaPadre(BigDecimal cedulaPadre) {
    this.cedulaPadre = cedulaPadre;
  }
  
  public BigDecimal getCodCondicionCedulado() {
    return codCondicionCedulado;
  }
  
  public void setCodCondicionCedulado(BigDecimal codCondicionCedulado) {
    this.codCondicionCedulado = codCondicionCedulado;
  }
  
  public BigDecimal getCodDomicilio() {
    return codDomicilio;
  }
  
  public void setCodDomicilio(BigDecimal codDomicilio) {
    this.codDomicilio = codDomicilio;
  }
  
  public BigDecimal getCodEstadoCivil() {
    return codEstadoCivil;
  }
  
  public void setCodEstadoCivil(BigDecimal codEstadoCivil) {
    this.codEstadoCivil = codEstadoCivil;
  }
  
  public BigDecimal getCodInstruccion() {
    return codInstruccion;
  }
  
  public void setCodInstruccion(BigDecimal codInstruccion) {
    this.codInstruccion = codInstruccion;
  }
  
  public BigDecimal getCodLugarMatrimonio() {
    return codLugarMatrimonio;
  }
  
  public void setCodLugarMatrimonio(BigDecimal codLugarMatrimonio) {
    this.codLugarMatrimonio = codLugarMatrimonio;
  }
  
  public BigDecimal getCodLugarNacimiento() {
    return codLugarNacimiento;
  }
  
  public void setCodLugarNacimiento(BigDecimal codLugarNacimiento) {
    this.codLugarNacimiento = codLugarNacimiento;
  }
  
  public BigDecimal getCodNacionalidad() {
    return codNacionalidad;
  }
  
  public void setCodNacionalidad(BigDecimal codNacionalidad) {
    this.codNacionalidad = codNacionalidad;
  }
  
  public String getCodProfesion() {
    return codProfesion;
  }
  
  public void setCodProfesion(String codProfesion) {
    this.codProfesion = codProfesion;
  }
  
  public Long getCodSexo() {
    return codSexo;
  }
  
  public void setCodSexo(Long codSexo) {
    this.codSexo = codSexo;
  }
  
  public Date getFechaExpedicionCed() {
    return fechaExpedicionCed;
  }
  
  public void setFechaExpedicionCed(Date fechaExpedicionCed) {
    this.fechaExpedicionCed = fechaExpedicionCed;
  }
  
  public Date getFechaFallecimiento() {
    return fechaFallecimiento;
  }
  
  public void setFechaFallecimiento(Date fechaFallecimiento) {
    this.fechaFallecimiento = fechaFallecimiento;
  }
  
  public Date getFechaMatrimonio() {
    return fechaMatrimonio;
  }
  
  public void setFechaMatrimonio(Date fechaMatrimonio) {
    this.fechaMatrimonio = fechaMatrimonio;
  }
  
  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }
  
  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }
  
  public String getNombreCalle() {
    return nombreCalle;
  }
  
  public void setNombreCalle(String nombreCalle) {
    this.nombreCalle = nombreCalle;
  }
  
  public String getNombreConyuge() {
    return nombreConyuge;
  }
  
  public void setNombreConyuge(String nombreConyuge) {
    this.nombreConyuge = nombreConyuge;
  }
  
  public String getNombreMadre() {
    return nombreMadre;
  }
  
  public void setNombreMadre(String nombreMadre) {
    this.nombreMadre = nombreMadre;
  }
  
  public String getNombrePadre() {
    return nombrePadre;
  }
  
  public void setNombrePadre(String nombrePadre) {
    this.nombrePadre = nombrePadre;
  }
  
  public String getNombres() {
    return nombres;
  }
  
  public void setNombres(String nombres) {
    this.nombres = nombres;
  }
  
  public String getNumeroCasa() {
    return numeroCasa;
  }
  
  public void setNumeroCasa(String numeroCasa) {
    this.numeroCasa = numeroCasa;
  }
}
