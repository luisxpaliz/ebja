package ec.gob.educacion.ebja.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ROW")
public class Persona
{
  @XStreamAlias("CEDULA")
  private String cedula;
  @XStreamAlias("NOMBRE")
  private String nombre;
  @XStreamAlias("GENERO")
  private String genero;
  @XStreamAlias("CONDICION_CEDULADO")
  private String condicionCedulado;
  @XStreamAlias("FECHA_NACIMIENTO")
  private String fechaNacimiento;
  @XStreamAlias("CODIGO_LUGAR_NACIMIENTO")
  private String codigoLugarNacimiento;
  @XStreamAlias("LUGAR_NACIMIENTO")
  private String lugarNacimiento;
  @XStreamAlias("NACIONALIDAD")
  private String nacionalidad;
  @XStreamAlias("ESTADO_CIVIL")
  private String estadoCivil;
  @XStreamAlias("CONYUGE")
  private String conyuge;
  @XStreamAlias("INSTRUCCION")
  private String instruccion;
  @XStreamAlias("PROFESION")
  private String profesion;
  @XStreamAlias("FECHA_CEDULACION")
  private String fechaCedulacion;
  @XStreamAlias("FECHA_MATRIMONIO")
  private String fechaMatrimonio;
  @XStreamAlias("LUGAR_MATRIMONIO")
  private String lugarMatrimonio;
  @XStreamAlias("CODIGO_GENERO")
  private String codigoGenero;
  @XStreamAlias("CODIGO_CONDICION_CEDULADO")
  private String codigoCondicionCedulado;
  @XStreamAlias("CODIGO_NACIONALIDAD")
  private String codigoNacionalidad;
  @XStreamAlias("CODIGO_ESTADO_CIVIL")
  private String codigoEstadoCivil;
  @XStreamAlias("CODIGO_INSTRUCCION")
  private String codigoInstruccion;
  @XStreamAlias("CODIGO_PROFESION")
  private String codigoProfesion;
  @XStreamAlias("CODIGO_LUGAR_MATRIMONIO")
  private String codigoLugarMatrimonio;
  @XStreamAlias("FECHA_DEFUNCION")
  private String fechaDefuncion;
  @XStreamAlias("LUGAR_DOMICILIO")
  private String lugarDomicilio;
  @XStreamAlias("CODIGO_LUGAR_DOMICILIO")
  private String codigoLugarDomicilio;
  @XStreamAlias("CALLES_DOMICILIO")
  private String callesDomicilio;
  @XStreamAlias("NUMERO_CASA")
  private String numeroCasa;
  @XStreamAlias("NOMBRE_PADRE")
  private String nombrePadre;
  @XStreamAlias("NOMBRE_MADRE")
  private String nombreMadre;
  
  public Persona() {}
  
  public String getCedula()
  {
    return cedula;
  }
  
  public void setCedula(String cedula) {
    this.cedula = cedula;
  }
  
  public String getNombre() {
    return nombre;
  }
  
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  public String getGenero() {
    return genero;
  }
  
  public void setGenero(String genero) {
    this.genero = genero;
  }
  
  public String getCondicionCedulado() {
    return condicionCedulado;
  }
  
  public void setCondicionCedulado(String condicionCedulado) {
    this.condicionCedulado = condicionCedulado;
  }
  
  public String getFechaNacimiento() {
    return fechaNacimiento;
  }
  
  public void setFechaNacimiento(String fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }
  
  public String getCodigoLugarNacimiento() {
    return codigoLugarNacimiento;
  }
  
  public void setCodigoLugarNacimiento(String codigoLugarNacimiento) {
    this.codigoLugarNacimiento = codigoLugarNacimiento;
  }
  
  public String getLugarNacimiento() {
    return lugarNacimiento;
  }
  
  public void setLugarNacimiento(String lugarNacimiento) {
    this.lugarNacimiento = lugarNacimiento;
  }
  
  public String getNacionalidad() {
    return nacionalidad;
  }
  
  public void setNacionalidad(String nacionalidad) {
    this.nacionalidad = nacionalidad;
  }
  
  public String getEstadoCivil() {
    return estadoCivil;
  }
  
  public void setEstadoCivil(String estadoCivil) {
    this.estadoCivil = estadoCivil;
  }
  
  public String getConyuge() {
    return conyuge;
  }
  
  public void setConyuge(String conyuge) {
    this.conyuge = conyuge;
  }
  
  public String getInstruccion() {
    return instruccion;
  }
  
  public void setInstruccion(String instruccion) {
    this.instruccion = instruccion;
  }
  
  public String getProfesion() {
    return profesion;
  }
  
  public void setProfesion(String profesion) {
    this.profesion = profesion;
  }
  
  public String getFechaCedulacion() {
    return fechaCedulacion;
  }
  
  public void setFechaCedulacion(String fechaCedulacion) {
    this.fechaCedulacion = fechaCedulacion;
  }
  
  public String getFechaMatrimonio() {
    return fechaMatrimonio;
  }
  
  public void setFechaMatrimonio(String fechaMatrimonio) {
    this.fechaMatrimonio = fechaMatrimonio;
  }
  
  public String getLugarMatrimonio() {
    return lugarMatrimonio;
  }
  
  public void setLugarMatrimonio(String lugarMatrimonio) {
    this.lugarMatrimonio = lugarMatrimonio;
  }
  
  public String getCodigoGenero() {
    return codigoGenero;
  }
  
  public void setCodigoGenero(String codigoGenero) {
    this.codigoGenero = codigoGenero;
  }
  
  public String getCodigoCondicionCedulado() {
    return codigoCondicionCedulado;
  }
  
  public void setCodigoCondicionCedulado(String codigoCondicionCedulado) {
    this.codigoCondicionCedulado = codigoCondicionCedulado;
  }
  
  public String getCodigoNacionalidad() {
    return codigoNacionalidad;
  }
  
  public void setCodigoNacionalidad(String codigoNacionalidad) {
    this.codigoNacionalidad = codigoNacionalidad;
  }
  
  public String getCodigoEstadoCivil() {
    return codigoEstadoCivil;
  }
  
  public void setCodigoEstadoCivil(String codigoEstadoCivil) {
    this.codigoEstadoCivil = codigoEstadoCivil;
  }
  
  public String getCodigoInstruccion() {
    return codigoInstruccion;
  }
  
  public void setCodigoInstruccion(String codigoInstruccion) {
    this.codigoInstruccion = codigoInstruccion;
  }
  
  public String getCodigoProfesion() {
    return codigoProfesion;
  }
  
  public void setCodigoProfesion(String codigoProfesion) {
    this.codigoProfesion = codigoProfesion;
  }
  
  public String getCodigoLugarMatrimonio() {
    return codigoLugarMatrimonio;
  }
  
  public void setCodigoLugarMatrimonio(String codigoLugarMatrimonio) {
    this.codigoLugarMatrimonio = codigoLugarMatrimonio;
  }
  
  public String getFechaDefuncion() {
    return fechaDefuncion;
  }
  
  public void setFechaDefuncion(String fechaDefuncion) {
    this.fechaDefuncion = fechaDefuncion;
  }
  
  public String getLugarDomicilio() {
    return lugarDomicilio;
  }
  
  public void setLugarDomicilio(String lugarDomicilio) {
    this.lugarDomicilio = lugarDomicilio;
  }
  
  public String getCodigoLugarDomicilio() {
    return codigoLugarDomicilio;
  }
  
  public void setCodigoLugarDomicilio(String codigoLugarDomicilio) {
    this.codigoLugarDomicilio = codigoLugarDomicilio;
  }
  
  public String getCallesDomicilio() {
    return callesDomicilio;
  }
  
  public void setCallesDomicilio(String callesDomicilio) {
    this.callesDomicilio = callesDomicilio;
  }
  
  public String getNumeroCasa() {
    return numeroCasa;
  }
  
  public void setNumeroCasa(String numeroCasa) {
    this.numeroCasa = numeroCasa;
  }
  
  public String getNombrePadre() {
    return nombrePadre;
  }
  
  public void setNombrePadre(String nombrePadre) {
    this.nombrePadre = nombrePadre;
  }
  
  public String getNombreMadre() {
    return nombreMadre;
  }
  
  public void setNombreMadre(String nombreMadre) {
    this.nombreMadre = nombreMadre;
  }
}
