package ec.gob.educacion.ebja.recursos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.ExternalContext;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author washington.sangacha
 */
public class ClienteServicioWeb {
    
    public static String servicioWebGet(String urlServicioWeb) {
        StringBuilder jsonRespuesta = new StringBuilder();
        TrustManager[] trustAllCerts = new TrustManager[]{
        new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType) {
            }
        }};
        try {
            URL url = new URL(urlServicioWeb);
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                jsonRespuesta.append(output);
            }
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ClienteServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(ClienteServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonRespuesta.toString();
    }

    public static String servicioWebHttpPost(String urlServicioWeb, String json) {
        StringBuilder jsonRespuesta = new StringBuilder();
        try {
            URL url = new URL(urlServicioWeb);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            bw.write(json);
            bw.flush();
            bw.close();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"))){
                String output;
                while ((output = br.readLine()) != null) {
                    jsonRespuesta.append(output);
                }
            }catch(IOException e){
                Logger.getLogger(ClienteServicioWeb.class.getName()).log(Level.SEVERE, null, e);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonRespuesta.toString();
    }
    
    
    
    public static String servicioWebPost(String urlServicioWeb, String json) {
        StringBuilder jsonRespuesta = new StringBuilder();
        TrustManager[] trustAllCerts = new TrustManager[]{
        new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType) {
            }
        }};
        
        try {
            URL url = new URL(urlServicioWeb);
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setDoOutput(true); 
            
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            bw.write(json);
            bw.flush();
            bw.close();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(),"UTF-8"))){
                String output;
                while ((output = br.readLine()) != null) {
                    jsonRespuesta.append(output);
                }
            }catch(IOException e){
                Logger.getLogger(ClienteServicioWeb.class.getName()).log(Level.SEVERE, null, e);
            }
            
            conn.disconnect();            

        } catch (MalformedURLException e) {
            Logger.getLogger(ClienteServicioWeb.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(ClienteServicioWeb.class.getName()).log(Level.SEVERE, null, e);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ClienteServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(ClienteServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonRespuesta.toString();
    }
    
    public static String getURLWithContextPath(ExternalContext external) {
		HttpServletRequest request = (HttpServletRequest)external.getRequest();
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
	}
    
    public static String getURLWebServiceAutenticacion(ExternalContext external) {
		HttpServletRequest request = (HttpServletRequest)external.getRequest();
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+Constantes.URL_SERVICIO_WEB_VALIDAR_USUARIO;
	}
    
    public static String getURLWebServiceRecursos(ExternalContext external) {
		HttpServletRequest request = (HttpServletRequest)external.getRequest();
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+Constantes.URL_SERVICIO_WEB_RECURSOS_USUARIO;
	}
    
    public static String getURLWebService(ExternalContext external, String pathServicioWeb) {
		HttpServletRequest request = (HttpServletRequest)external.getRequest();
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+pathServicioWeb;
	}
}
