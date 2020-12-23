package ec.gob.educacion.ebja.recursos;

import ec.gob.educacion.ebja.modelo.Inscripcion;
import ec.gob.educacion.ebja.modelo.RegistroEstudiante;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
	public MailUtil() {
	}

	public static void enviarMail(String usuarioCorreo, String clave,
			String ipCorreo, String puerto, String usuarioRecipiente,
			String mensaje) {
		Properties properties = System.getProperties();

		properties.setProperty("mail.smtp.host", ipCorreo);

		Session session = Session.getDefaultInstance(properties);
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(usuarioCorreo));

			Address[] add = null;
			String[] usuariosRecipiente = usuarioRecipiente.split(";");

			if (usuariosRecipiente.length > 1) {
				add = new Address[usuariosRecipiente.length];
				for (int i = 0; i < usuariosRecipiente.length; i++) {
					add[i] = new InternetAddress(usuariosRecipiente[i]);
				}
			} else {
				add = new Address[] { new InternetAddress(usuarioRecipiente) };
			}
			message.addRecipients(Message.RecipientType.TO, add);

			message.setSubject("Registro, inscripción oferta educativa extraordinaria (virtual)!");

			message.setContent(mensaje, "text/html");

			Transport.send(message);
			System.out.println("Mensaje enviado con éxito....");
		} catch (MessagingException mex) {
			System.out.println("Mensaje no enviado...");
		}
	}

	public static String construirMensajeCorreo(RegistroEstudiante registroEstudiante, String fechaInicioClases) {
		StringBuilder sb = new StringBuilder();
		sb.append("<h3>Registro de Inscripci&#243;n a las Ofertas Educativas Extraordinarias (Virtual) por parte de los ciudadanos.</h3>");
		sb.append("<h3>Estimado se ha enviado un correo electronico relacionado con el registro de la inscripci&#243;n a la oferta educativa extraordinaria (virtual).</h3>");
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

		sb.append("<h3>El/la aspirante "
				+ registroEstudiante.getApellidosNombres()
				+ ", portador/a del documento No. "
				+ registroEstudiante.getNumeroIdentificacion() + ", fue inscrito en el "
				+ formateador.format(registroEstudiante.getFechaCreacion())
				+ "</h3>");
		sb.append("<h3> </h3>");
		sb.append("<h3> </h3>");
		sb.append("<h3> </h3>");
		sb.append("<h3> </h3>");
		sb.append("<h3>OPERADOR </h3>");

		sb.append("<h3> </h3>");
		sb.append("<h3>El inicio de clases de "
				+ registroEstudiante.getApellidosNombres() + " es el d&#237;a: "
				+ fechaInicioClases + "</h3>");

		sb.append("<h3> </h3>");
		sb.append("<h3>Nota de descargo: El Ministerio de Educaci&#243;n, realizar&#225; la asignaci&#243;n de estudiantes a instituciones educativas correspondientes.</h3>");

		sb.append("<h3> </h3>");
		sb.append("<h3>El/la registrante declara tener poder/autorizaci&#243;n suficiente para realizar el presente registro y establecer que la informaci&#243;n que este documento contiene es veraz, por lo que asumir&#225; la responsabilidad y obligaciones que por efecto de este proceso se generen.</h3>");

		sb.append("<h3> </h3>");
		sb.append("<h3>En caso de que el estudiante no disponga de documentos habilitantes: promociones o certificado de terminaci&#243;n de EGB superior, o  sea promovido del 10mo de EGB mediante prueba de ubicaci&#243;n, en el t&#233;rmino de 30 d&#237;as, contados a partir del inicio del per&#237;odo acad&#233;mico, la inscripci&#243;n quedar&#225; autom&#225;ticamente anulada.</h3>");

		return sb.toString();
	}

	public static void main(String... arg) {
		enviarMail("Eduacion\\gestion.cupos", "Mineduc2014", "10.200.6.104", "25", "jorger.ponce@educacion.gob.ec", "");
	}
	
	public static boolean EnviarCorreoValidacion(String dominioOrigen,String host,String usuario,String clave,String asunto,RegistroEstudiante registroEstudiante) {
		try {
			
		// Propiedades de la conexi�n
					Properties props = new Properties();
					
					props.setProperty("mail.smtp.host", host);
					props.setProperty("mail.smtp.starttls.enable", "true");
					props.setProperty("mail.smtp.port", "25");
					props.setProperty("mail.smtp.user", usuario);
					props.setProperty("mail.smtp.auth", "false");
					
					// Preparamos la sesion
					Session session = Session.getDefaultInstance(props);

					// Construimos el mensaje
					MimeMessage message = new MimeMessage(session);
					// la persona que tiene que verificar
					message.setFrom(new InternetAddress(usuario));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress( registroEstudiante.getCorreoElectronico()));
					//message.addHeader("Disposition-Notification-To", "ebja.temp"); enviar  confirmacion de lectura
					message.setSubject("Correo de validación al registro de inscripción Modalidad Educativa a Distancia-Virtual");
					message.setText(MensajeCorreo(registroEstudiante,dominioOrigen), "ISO-8859-1", "html");

					Transport.send(message);
					
					System.out.println("correo enviado correctamente");
					return true;
					
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				
		}
	
	
	public static String getCadenaAlfanumAleatoria(int longitud) {
		String cadenaAleatoria = "";
		long milis = new java.util.GregorianCalendar().getTimeInMillis();
		Random r = new Random(milis);
		int i = 0;
		while (i < longitud) {
			char c = (char) r.nextInt(255);
			
			if ((c >= '0' && c <= 9) || (c >= 'A' && c <= 'Z')) {
				cadenaAleatoria += c;
				i++;
			}
		}
		return cadenaAleatoria;
	}
	
	public static String MensajeCorreo(RegistroEstudiante registroEstudiante,String dominioOrigen) {
		StringBuilder sb = new StringBuilder();
		sb.append("<h3>Estimado estudiante:</h3>");
		sb.append("<h3>El presente correo est&#225 relacionado con el registro de su inscripci&#243;n\r\n" + 
				"a la Modalidad Educativa a Distancia &#45; Virtual.</h3>");
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

		
		sb.append("<h3>El/la aspirante "
				+ registroEstudiante.getApellidosNombres()
				+ ", portador/a del documento No. "
				+ registroEstudiante.getNumeroIdentificacion() + ", fue inscrito en la modalidad de Educaci&#243;n a Distancia &#45; Virtual"
				+ ", el d&#237;a "+ formateador.format(registroEstudiante.getFechaCreacion())+".</h3>");
		
		sb.append("<h3> </h3>");
		//sb.append("<h3>Gracias por inscribirse, haga click en el siguiente enlace para validar su correo y descargar las generalidades de la oferta" + 
		//		"<a href='"+dominioOrigen+"ins-virtual-web/faces/paginas/externo/validacion.xhtml?usuario=" + registroEstudiante.getNumeroIdentificacion() +"&fas="+registroEstudiante.getInscripcion().getProgramaGrado().getProgramaEbja().getNemonico()+ "&token=" + registroEstudiante.getToken()+"'>Enlace</a>  </h3>");

		return sb.toString();
	}
	
}
