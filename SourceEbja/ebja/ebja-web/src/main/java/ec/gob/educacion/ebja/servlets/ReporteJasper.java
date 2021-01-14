package ec.gob.educacion.ebja.servlets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

@SuppressWarnings("deprecation")
public class ReporteJasper {
	static final String PATH_REPORTES = "/report/";

	private ServletContext aux;
	private String nombreReporte;

	@SuppressWarnings("deprecation")
	public void generarReporte(Map<String, Object> parametros, JRBeanCollectionDataSource ds, String nombreReporte) {
		byte[] archivo;
		try {
			aux = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String realPath = aux.getRealPath("/");

			parametros.put("URL_LOGO", realPath + "/img/logo.png");
			parametros.put("URL_BACKGROUND", realPath + "/img/marca_agua.jpg");

			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(realPath + PATH_REPORTES + nombreReporte + ".jasper");
			JasperPrint jasperPrint;
			jasperPrint = JasperFillManager.fillReport(report, parametros, ds);

			archivo = JasperExportManager.exportReportToPdf(jasperPrint);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			JRExporter<?, ?, ?, ?> exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
			exporter.exportReport();
			archivo = outputStream.toByteArray();

			verReporte(archivo, nombreReporte, "pdf");
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("general", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ""));
		}
	}

	@SuppressWarnings("deprecation")
	public byte[] generarReporteArchivo(Map<String, Object> parametros, JRBeanCollectionDataSource ds, String nombreReporte) {
		byte[] archivo;
		try {
			aux = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String realPath = aux.getRealPath("/");

			parametros.put("SUBREPORT_DIR", realPath + "jasperReportsUsuario/");

			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(realPath + PATH_REPORTES + nombreReporte + ".jasper");

			JasperPrint jasperPrint;
			jasperPrint = JasperFillManager.fillReport(report, parametros, ds);
			archivo = JasperExportManager.exportReportToPdf(jasperPrint);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			JRExporter<?, ?, ?, ?> exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
			exporter.exportReport();
			archivo = outputStream.toByteArray();

			verReporte(archivo, nombreReporte, "pdf");
		} catch (Exception e) {
			archivo = null;
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("general",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede Generar el archivo PDF", ""));
		}
		
		return archivo;
	}

	public void verReporte(byte[] data, String nombreArchivo, String tipo) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		try {
			OutputStream responseStream = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + nombreArchivo + tipo + "\"");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0L);
			response.setContentLength(data.length);
			responseStream.write(data);
			response.flushBuffer();
			responseStream.close();
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage("general",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ""));
		}
		facesContext.responseComplete();
	}

    //Grabar archivo excel en la ruta indicada. 
	public boolean grabarArchivo(InputStream inputStream, String rutaArchivo) {
		try {
			// write the inputStream to a FileOutputStream
	        OutputStream outputStream = new FileOutputStream(new File(rutaArchivo));

			int read = 0;
            byte[] bytes = new byte[1024];
 
            while ((read = inputStream.read(bytes)) != -1) {
            	outputStream.write(bytes, 0, read);
            }
 
            inputStream.close();
            outputStream.flush();
            outputStream.close();
            return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public void generarReporteNavegador(Map<String, Object> parametros, JRBeanCollectionDataSource ds, String nombreReporte) {
		this.nombreReporte = nombreReporte;
		byte[] archivo;
		try {
			aux = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String realPath = aux.getRealPath("/");

			parametros.put("IMAGE_DIR", realPath + "img/");
			//parametros.put("URL_LOGO", realPath + "/img/logo.png");
			if (nombreReporte.equals("inscripcionVirtual")) {
				parametros.put("URL_BACKGROUND", realPath + "/img/inscripcion_virtual.jpg");
			} else {
				parametros.put("URL_BACKGROUND", realPath + "/img/marca_agua.jpg");
			}

			parametros.put("URL_LOGO", realPath + "/img/logo_Mineduc.jpg");
			parametros.put("URL_LOGO1", realPath + "/img/logo_Gobierno.jpg");
			

			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(realPath + PATH_REPORTES + nombreReporte + ".jasper");
			JasperPrint jasperPrint;
			jasperPrint = JasperFillManager.fillReport(report, parametros, ds);

			archivo = JasperExportManager.exportReportToPdf(jasperPrint);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			JRExporter<?, ?, ?, ?> exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
			exporter.exportReport();
			archivo = outputStream.toByteArray();

			verReporteNavegador(archivo);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("general", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ""));
		}
	}

	public void generarReporteExcel(Map<String, Object> parametros, JRBeanCollectionDataSource ds, String nombreReporte) {
		ServletOutputStream servletOutputStream;
		try {
			aux = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String realPath = aux.getRealPath("/");

			parametros.put("URL_LOGO", realPath + "/img/logo_Mineduc.jpg");
			parametros.put("URL_LOGO1", realPath + "/img/logo_Gobierno.jpg");
			
			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(realPath + PATH_REPORTES + nombreReporte + ".jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, ds);
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			httpServletResponse.setContentType("application/xlsx");
			httpServletResponse.addHeader("Content-disposition", "attachment; filename=\"" + nombreReporte + ".xlsx\"");
			servletOutputStream = httpServletResponse.getOutputStream();
			
			JRXlsxExporter excelExporter = new JRXlsxExporter();
			excelExporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
			excelExporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, servletOutputStream);
			excelExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			excelExporter.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.FALSE);
			excelExporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
			excelExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			excelExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			excelExporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.TRUE);
			excelExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
			
			excelExporter.exportReport();
			servletOutputStream.flush();
			servletOutputStream.close();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("general", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ""));
		}
	}

	private void verReporteNavegador(byte[] archivo) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.setContentType("application/pdf");
		response.addHeader("Content-disposition", "attachment; filename=\"" + nombreReporte + ".pdf\"");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0L);
		int read;
		try {
			InputStream fis = new ByteArrayInputStream(archivo);
			OutputStream os;
			byte[] bytes1 = new byte['Ѐ'];
			os = response.getOutputStream();

			while ((read = fis.read(bytes1)) != -1) {
				os.write(bytes1, 0, read);
			}
			os.flush();
			os.close();
			fis.close();
		} catch (Exception localException) {
		}

		FacesContext.getCurrentInstance().responseComplete();
	}
	
	
		@SuppressWarnings("deprecation")
		public void generarReporteConfiguracionOfertas(Map<String, Object> parametros, Connection conn, String nombreReporte) {
			this.nombreReporte = nombreReporte;
			byte[] archivo;
			try {
				aux = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
				String realPath = aux.getRealPath("/");
			
				JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(realPath + PATH_REPORTES + nombreReporte + ".jasper");
				JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, conn);
				archivo = JasperExportManager.exportReportToPdf(jasperPrint);
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				JRExporter<?, ?, ?, ?> exporter = new JRPdfExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
				exporter.exportReport();
				archivo = outputStream.toByteArray();
				verReporteNavegador(archivo);
			} catch (Exception e) {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage("general", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ""));
			}
		}
}
