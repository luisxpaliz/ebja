package ec.gob.educacion.ebja.recursos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
	private String email;

	public EmailValidator(String email) {
		super();
		this.email = email;
	}

	public boolean isEmailValid() {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern pattern = Pattern.compile(ePattern);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}