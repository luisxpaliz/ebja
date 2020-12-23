package ec.gob.educacion.ebja.modelo.asignaciones;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="INS_PARAMETRO")
public class InsParametro
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private long codigo;
  private String descripcion;
  private String txtNemonico;
  private Double valor;
  
  public InsParametro() {}
  
  @Id
  @Column(name="CODIGO")
  public long getCodigo()
  {
    return codigo;
  }
  
  public void setCodigo(long codigo) { this.codigo = codigo; }
  
  @Column(name="DESCRIPCION")
  public String getDescripcion()
  {
    return descripcion;
  }
  
  public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
  
  @Column(name="TXT_NEMONICO")
  public String getTxtNemonico()
  {
    return txtNemonico;
  }
  
  public void setTxtNemonico(String txtNemonico) { this.txtNemonico = txtNemonico; }
  
  @Column(name="VALOR")
  public Double getValor()
  {
    return valor;
  }
  
  public void setValor(Double valor) { this.valor = valor; }
}
