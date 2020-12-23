package ec.gob.educacion.ebja.recursos;

public class CedulaValidator {
	private static final int[] coeficientes = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };

	private String cedula;

	public CedulaValidator(String cedula) {
		super();
		this.cedula = cedula;
	}

	public boolean validate() {
		try {
			return cedula != null && longitudCorrecta()
					&& ultimoDigitoEsValido();
		} catch (Exception e) {
			return false;
		}
	}

	private boolean ultimoDigitoEsValido() {
		return calcularUltimoDigito().equals(extraerUltimoDigito());
	}

	private String extraerUltimoDigito() {
		return cedula.substring(9, 10);
	}

	private String calcularUltimoDigito() {
		int sum = 0;
		int aux;
		for (int i = 0; i < coeficientes.length; i++) {
			int digito = Integer.parseInt(cedula.substring(i, i + 1));
			aux = digito * coeficientes[i];
			if (aux > 9) {
				aux -= 9;
			}
			sum += aux;
		}
		int mod = sum % 10;
		mod = (mod == 0) ? 10 : mod;
		final int res = (10 - mod);
		return String.valueOf(res);
	}

	private boolean longitudCorrecta() {
		return cedula.length() == 10;
	}
}