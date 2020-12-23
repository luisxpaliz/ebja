package ec.gob.educacion.ebja.enumerador;

public enum TipoCatalogoEnum {

	ACTIVIDADES_ADMINISTRATIVAS("CAA");
	
	private String nemonico;

	private TipoCatalogoEnum(String nemonico) {
		this.nemonico = nemonico;
	}

	public String getNemonico() {
		return nemonico;
	}
}
