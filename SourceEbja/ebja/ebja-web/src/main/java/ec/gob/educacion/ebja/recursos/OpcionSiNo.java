package ec.gob.educacion.ebja.recursos;

public class OpcionSiNo {
	
	private String Nombre;
	private String Nemonico;
	
	public OpcionSiNo(String Nom, String Nem) {	
		this.Nombre = Nom;
		this.Nemonico = Nem;
	}
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getNemonico() {
		return Nemonico;
	}
	public void setNemonico(String nemonico) {
		Nemonico = nemonico;
	}

}
