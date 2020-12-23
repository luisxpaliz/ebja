package ec.gob.educacion.ebja.recursos;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Condiciones {

	public String getStringFromPage(String dirPage) {
		String cadena;
		String text="";
		try {
			InputStream in = getClass().getResourceAsStream(dirPage); 
			BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
			while((cadena = buffer.readLine())!=null) {
				text += cadena;
			}
			buffer.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return text;
	}
}
