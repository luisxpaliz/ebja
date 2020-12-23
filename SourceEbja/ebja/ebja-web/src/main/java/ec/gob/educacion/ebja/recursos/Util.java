package ec.gob.educacion.ebja.recursos;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import ec.gob.educacion.ebja.recursos.Constantes;

public class Util {

    public static boolean esCedulaValida(String cedula) {
    	 int NUMERO_PROVINCIAS_ECUADOR = 24;
        //verifica que tenga 10 dígitos y que contenga solo valores numéricos
        if (!((cedula.length() == 10) && cedula.matches("^[0-9]{10}$"))) {
            return false;
        }

        //verifica que los dos primeros dígitos correspondan a un valor entre 1 y NUMERO_DE_PROVINCIAS
        int prov = Integer.parseInt(cedula.substring(0, 2));

        if (!((prov > 0) && (prov <= NUMERO_PROVINCIAS_ECUADOR))) {
            return false;
        }

        //verifica que el último dígito de la cédula sea válido
        int[] d = new int[10];

        //Asignamos el string a un array
        for (int i = 0; i < d.length; i++) {
            d[i] = Integer.parseInt(cedula.charAt(i) + "");
        }

        int imp = 0;
        int par = 0;

        //sumamos los duplos de posición impar
        for (int i = 0; i < d.length; i += 2) {
            d[i] = ((d[i] * 2) > 9) ? ((d[i] * 2) - 9) : (d[i] * 2);
            imp += d[i];
        }

        //sumamos los digitos de posición par
        for (int i = 1; i < (d.length - 1); i += 2) {
            par += d[i];
        }

        //Sumamos los dos resultados
        int suma = imp + par;
        
        //Restamos de la decena superior
        int d10 = Integer.parseInt(String.valueOf(suma + 10).substring(0, 1) +
                "0") - suma;
        
        //Si es diez el décimo dígito es cero        
        d10 = (d10 == 10) ? 0 : d10;

        //si el décimo dígito calculado es igual al digitado la cédula es correcta
        return d10 == d[9];
    } 

	public static BigDecimal sumarValores(BigDecimal... valores) {
		BigDecimal resultado = BigDecimal.ZERO.setScale(2);
		for (BigDecimal valor : valores) {
			if (valor == null || valor.compareTo(BigDecimal.ZERO) == 0) {
				resultado = resultado.add(BigDecimal.ZERO);
			} else {
				resultado = resultado.add(valor).setScale(2, RoundingMode.HALF_EVEN);
			}
		}
		return resultado;
	}
	
	public static BigDecimal sumarValores(Double... valores) {
		BigDecimal resultado = BigDecimal.ZERO.setScale(2);
		for (Double valor : valores) {
			if (valor == null || valor.doubleValue() == 0) {
				resultado = resultado.add(BigDecimal.ZERO);
			} else {
				resultado = resultado.add(BigDecimal.valueOf(valor)).setScale(2, RoundingMode.HALF_EVEN);
			}
		}
		return resultado;
	}
	
	public static void descargarArchivoPDF(byte[] data){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext
				.getExternalContext().getResponse();
		try {			
			OutputStream responseStream = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ "Declaración Juramentada" + ".pdf\"");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentLength(data.length);
			responseStream.write(data);
			response.flushBuffer();
			responseStream.close();
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage("general",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"No se puede Generar el archivo PDF", ""));
		}
		facesContext.responseComplete();		
	}
	
	public static String obtenerEstadoInversion(Integer estadoInversion) {
		String estadoInversionStr = "";
		if (estadoInversion != null) {
			switch (estadoInversion) {
			case 0:
				estadoInversionStr = "NO ENVIADO";
				break;
			case 1:
				estadoInversionStr = "ENVIADO";
				break;
			case 2:
				estadoInversionStr = "SI CUMPLE";
				break;
			case 3:
				estadoInversionStr = "NO CUMPLE";
				break;
			default:
				break;
			}
		}
		return estadoInversionStr;
	}
	
	public static String fechaEnLetras(Date fecha) {
		String fechaFinal = "";
		if (fecha != null) {
			SimpleDateFormat formateadorDia = new SimpleDateFormat("dd");
			String diaValidar = formateadorDia.format(fecha);
			if (diaValidar.equals("1")) {
				SimpleDateFormat formateador = new SimpleDateFormat(
						"'al' dd 'día del mes de' MMMMM 'de' yyyy", new Locale("es", "ES"));
				fechaFinal = formateador.format(fecha);
			} else {
				SimpleDateFormat formateador = new SimpleDateFormat(
						"'a los' dd 'días del mes de' MMMMM 'de' yyyy", new Locale("es", "ES"));
				fechaFinal = formateador.format(fecha);
			}

		}
		return fechaFinal;
	}
	
	public static String fechaEnLetrasComprobante(Date fecha){
		String fechaFinal = "";
		if (fecha != null) {
			SimpleDateFormat formateador = new SimpleDateFormat(
					"dd 'de' MMMMM 'de' yyyy'.'", new Locale("es", "ES"));
			fechaFinal = formateador.format(fecha);
		}
		return fechaFinal;
	}
	
	public static Object[] obtenerNumeroResolucion(String codigoDistrito, Integer secuencialResolucion) {
		String numeroResolucion = "";
		if (secuencialResolucion == null) { secuencialResolucion = 1; }
		int secuencialResolucionLongitud = secuencialResolucion.toString().trim().length();
		if (secuencialResolucionLongitud < 7) {
			numeroResolucion = Constantes.NUMERO_SECUENCIAL.substring(0, Constantes.NUMERO_SECUENCIAL.length() - secuencialResolucionLongitud);
		}
		if (secuencialResolucionLongitud > 7) {
			numeroResolucion = "N/V";
			secuencialResolucion = -1;
		}
		numeroResolucion = numeroResolucion + secuencialResolucion.toString().trim() + codigoDistrito;
		return new Object[] {numeroResolucion, secuencialResolucion};
	}

	//Redondear valor Double a 2 decimales.
    public static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado= (resultado - parteEntera) * Math.pow(10, numeroDecimales);
        resultado = Math.round(resultado);
        resultado = (resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }
    
    public static Throwable getRootException(Throwable exception){
		 Throwable rootException=exception;
		 while(rootException.getCause()!=null){
		  rootException = rootException.getCause();
		 }
		 return rootException;
   }
	
}
