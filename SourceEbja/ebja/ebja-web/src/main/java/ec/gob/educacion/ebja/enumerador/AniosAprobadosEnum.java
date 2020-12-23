package ec.gob.educacion.ebja.enumerador;

import java.util.Arrays;
import java.util.List;

public enum AniosAprobadosEnum
{
  DECIMO_EGB(10, "10 EGB", Arrays.asList(new String[] { "Bachillerato" })), 
  PRIMERO_BGU(11, "1 BGU", Arrays.asList(new String[] { "Bachillerato" })), 
  SEGUNDO_BGU(12, "2 BGU", Arrays.asList(new String[] { "Bachillerato" }));
  
  private int codigo;
  private String anioAprobado;
  private List<String> ofertaEducativa;
  
  private AniosAprobadosEnum(int codigo, String anioAprobado, List<String> ofertaEducativa) {
    this.codigo = codigo;
    this.anioAprobado = anioAprobado;
    this.ofertaEducativa = ofertaEducativa;
  }
  
  public int getCodigo() {
    return codigo;
  }
  
  public String getAnioAprobado() {
    return anioAprobado;
  }
  
  public void setAnioAprobado(String anioAprobado) {
    this.anioAprobado = anioAprobado;
  }
  
  public List<String> getOfertaEducativa() {
    return ofertaEducativa;
  }
  
  public void setOfertaEducativa(List<String> ofertaEducativa) {
    this.ofertaEducativa = ofertaEducativa;
  }
}
