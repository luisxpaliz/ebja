package ec.gob.educacion.ebja.recursos;

public class Constantes {
	
	public static final String ESTADO_REGISTRO_ACTIVO = "1";
	public static final String ESTADO_REGISTRO_ACTIVO1 = "A";
	public static final Character ESTADO_REGISTRO_INACTIVO = '0';
	public static final String URL_SERVICIO_WEB_VALIDAR_USUARIO = "/serviciosEducacion-web/resources/ValidacionUsuarioLdapSeguridadesJdc/ValidarUsuarioGeneralObtenerRoles";
	public static final String URL_SERVICIO_WEB_RECURSOS_USUARIO = "/serviciosEducacion-web/resources/ValidacionUsuarioLdapSeguridadesJdc/ObtenerRecursosDeRol";
	public static final String ERROR_BBDD_CODIGO_DUPLICADO = "Error al guardar el registro por Código Duplicado";
	public static final String ERROR_BBDD_CODIGO_DUPLICADO_EDITAR = "Error al editar el registro por Código Duplicado";
	public static final String ERROR_BBDD_REGISTRO_DUPLICADO = "Error al guardar el registro por Duplicidad";
	public static final String ERROR_BBDD_GUARDAR_REGISTRO = "Hubo un error al guardar el registro";
	public static final String REGISTRO_BBDD_GUARDADO_EXITOSO= "El registro se guardó exitosamente";
	public static final String REGISTRO_BBDD_ACTIVO ="Actualmente el registro se encuentra activo";
	public static final String REGISTRO_BBDD_ACTIVO_EXITOSAMENTE ="El registro de activó exitosamente";
	public static final String REGISTRO_BBDD_ACTIVO_ERROR ="Hubo un error al Activar el registro";
	public static final String REGISTRO_BBDD_INACTIVO ="Actualmente el registro se encuentra inactivo";
	public static final String REGISTRO_BBDD_INACTIVO_EXITOSAMENTE ="El registro de inactivó exitosamente";
	public static final String REGISTRO_BBDD_ELIMINO_EXITOSAMENTE ="El registro se eliminó exitosamente";
	public static final String REGISTRO_BBDD_BORRAR_EXITOSAMENTE ="El registro se eliminó de manera permanente";
	public static final String REGISTRO_BBDD_INACTIVO_ERROR ="Hubo un error al Inactivar el registro";
	public static final String REGISTRO_BBDD_ELIMINO_ERROR ="Hubo un error al Eliminar el registro";
	public static final String REGISTRO_BBDD_BORRAR_ERROR ="Hubo un error al borrar el registro permanentemente";
	public static final String REGISTRO_BBDD_EDITO_EXITOSAMENTE="El registro de Editó exitosamente";
	public static final String REGISTRO_BBDD_EDITO_ERROR="Hubo un error al editar el registro";
	public static final String REGISTRO_BBDD_CAMPOS_VACIOS="Existen campos vacios en el registro";
	public static final String REGISTRO_BBDD_REGISTROYAEXISTENTE="Ya se encuentra registrada la información";
	
	//LOCAl
	//public static final String SERVIDOR_AD = "ldap://10.2.30.71:389";
	//DESARROLLO
	//public static final String SERVIDOR_AD = "ldap://181.113.66.51:389";
	//PREPRODUCCION
	//public static final String SERVIDOR_AD = "ldap://10.200.6.103:389";
	//PRODUCCION
	public static final String SERVIDOR_AD = "ldap://10.0.2.10:389";

	//Nemonico de Catalogo.
	public static final String NEMONICO_AREA_CONOCIMIENTO_MATERIAS_DISCRECION = "MATDISAREA";
	public static final String NEMONICO_AREA_CONOCIMIENTO_MATERIAS_OPTATIVAS = "MATOPTAREA";
	public static final String NEMONICO_MATERIA_DISCRECION_MALLA = "MATDISMALLA";
	public static final String NEMONICO_MATERIA_OPTATIVA_MALLA = "MATOPTMALLA";
	public static final String NEMONICO_AREA_ASIGNATURA_OPTATIVA = "ASGOPT";
	public static final String NEMONICO_AREA_HORAS_ADICIONALES = "HORADI";
	public static final String NEMONICO_TIPO_DOCUMENTO = "TIPIDE";
	public static final String NEMONICO_GENERO = "SEX";
	public static final String NEMONICO_ESTADO_CIVIL = "ESCI";
	public static final String NEMONICO_NACIONALIDAD_INDIGENA = "NACI";
	public static final String NEMONICO_ACTIVIDAD_ECONOMICA = "ACTECO";
	public static final String NEMONICO_SITUACION_LABORAL = "SITLAB";
	public static final String NEMONICO_DATOS_FAMILIARES = "DATFAM";
	public static final String NEMONICO_DOCUMENTO_PRESENTADO = "DOCPRE";
	public static final String NEMONICO_DOCUMENTO_MOTIVO = "MOTDOC";
	public static final String NEMONICO_DISCAPACIDAD_GENERAL = "DISGEN";
	public static final String NEMONICO_REZAGO_EDUCATIVO = "ANIREZ";
	public static final String NEMONICO_REZAGO_EDUCATIVO_PCEI = "ANIREZAGPCEI";

	public static final String UNCHECKED = "unchecked";
	public static final String REGISTRO_ACTIVO = "A";
	public static final String REGISTRO_INACTIVO = "I";
	public static final String REGISTRO_CANCELADO = "C";
	public static final String REGISTRO_NUEVO = "NU";
	public static final String REGISTRO_CASOS_ESPECIALES = "CE";
	public static final String REPRESENTANTELEGAL_REFERENCIALCONVENCIONAL_POSI = "S";
	public static final String REPRESENTANTELEGAL_REFERENCIALCONVENCIONAL_NEG = "N";
	public static final String SELECCION_POSITIVA = "SI";
	public static final String SELECCION_NEGATIVA = "NO";
	public static final String TIPO_PROCESO_INICIAL_DE_INSCRIPCION = "TPUV";
	public static final String TIPO_PROCESO_INSCRIPCION = "TPI ";
	public static final String TIPO_PROCESO_HERMANOS = "TPH ";
	public static final String ESTADOS_CIVILES = "ESCI";
	public static final String TIPOS_GENERO = "SEX ";
	public static final String TIPOS_NIVEL_INSTITUCION = "ACAD";
	public static final String TIPOS_PARENTESCOS = "PARE";
	public static final String TIPOS_CASOS_ESPECIALES = "CAE ";
	public static final String TIPO_PROCESO_INICIAL_DE_INSCRIPCION_UN_ESTUDIANTE = "Solamente un aspirante";
	public static final String TIPO_PROCESO_HERMANOS_OPCION_3 = "Varios aspirantes hermanos";
	public static final String TIPO_PROCESO_HERMANOS_OPCION_2 = "El aspirante solicita ingresar con su hermano/a en el sistema educativo fiscal";
	public static final String TIPO_PROCESO_HERMANOS_OPCION_1 = "El aspirante NO tiene un hermano/a en el sistema educativo fiscal";
	public static final String NEMONICO_CASO_ESPECIAL_MIGRACION = "CAEM";
	public static final String NEMONICO_CASO_ESPECIAL_REINSERCION = "CAER";
	public static final String NEMONICO_CASO_ESPECIAL_EDUCACION_ESPECIAL = "CAEE";
	public static final String NEMONICO_CASO_ESPECIAL_VULNERABILIDAD = "CAEV";
	public static final String TIPO_PROCESO_CASO_ESPECIAL_OPCION_7 = "El estudiante NO presenta documentos legalizados o apostillados que certifiquen los estudios realizados previamente.";
	public static final Integer CODIGO_PARENTESCO_PADRE = 19;
	public static final Integer CODIGO_PARENTESCO_MADRE = 20;
	public static final Integer TIPO_PROCESO_EDUCACION_INICIAL = 1;
	public static final Integer TIPO_PROCESO_PRIMERO_BASICA = 2;
	public static final Integer TIPO_PROCESO_TRASLADO_A_FISCAL = 3;
	public static final Integer TIPO_PROCESO_MIGRACION = 4;
	public static final Integer TIPO_PROCESO_REINSERCION = 5;
	public static final Integer TIPO_PROCESO_ED_ESPECIAL = 6;
	public static final Integer TIPO_PROCESO_VULNERABILIDAD = 7;
	public static final Integer TIPO_PROCESO_CASO_ESPECIAL_MIGRACION_FINALIZADO = 40;
	public static final Integer TIPO_PROCESO_CASO_ESPECIAL_MIGRACION_EXAMEN = 39;
	public static final Integer TIPO_PROCESO_CASO_ESPECIAL_REINSERCION_EXAMEN = 59;
	
	//Fechas máximas de Inscripción para los niveles Primero de Básica y Educación Inicial
	public static final String FECHA_MÁXIMA_INSCRIPCION_EDUCACION_INICIAL = "05-05-2014";
	public static final String FECHA_MÁXIMA_INSCRIPCION_PRIMERO_BASICA = "01-11-2014";

	//Niveles de estudio
	public static final String NIVEL_EDUC_INICIAL_GRUPO3 = "Educación Inicial";
	public static final String NIVEL_PRIMERO_BASICA = "Primer año Básica";
	public static final Integer CODIGO_NIVEL_EDUCACION_INICIAL_GRUPO3=43;	
	public static final Integer CODIGO_NIVEL_EDUCACION_INICIAL_GRUPO4=44;
	public static final Integer CODIGO_NIVEL_PRIMERO_BASICA=45;
	
	//Para acceder remotamente si estan en un mismo servidor
	public static final String URL_SEGURIDADES = "seguridades-educacion-ear/seguridades-educacion-ejb";
	
	//Opciones página Mapa
	public static final String TIPO_VIVIENDA = "TIVI";
	public static final String FORMA_VIVIENDA = "FOVI";
	public static final String CLASE_VIVIENDA = "CLVI";
	public static final String URBANO = "URBANA";
	public static final String AREA = "AREA";
	
	//Códigos
	public static final String NUMERO_INICIAL_CELULAR = "09";
	public static final String NEMONICO_SEDE = "SEDE";
	public static final String NEMONICO_BATCH = "BATH";
	public static final String NEMONICO_SERVICIO_RECEPCION = "URWS";
	public static final String NUMERO_SECUENCIAL = "0000000";
	public static final boolean REGISTRO_ACTIVO_BOOLEANO = true;
	public static final int REGISTRO_ACTIVO_ENTERO = 1;
	public static final int REGISTRO_NO_ENVIADO = 0;
	public static final int REGISTRO_ENVIADO = 1;
	public static final Integer HERMANO_ACEPTA_SALIR = 0;
	public static final Integer HERMANO_NO_ACEPTA_SALIR = 1;
	public static final Integer CODIGO_PARENTESCO_AUTOREPRESENTACION = 60;
	public static final Integer CODIGO_PARENTESCO_OTRO = 86;

	// tamaño archivos
	public static final int TAMANIO_MEGA = 1048576;
	public static final int CANTIDAD_MEGA = 2;
	public static final String MENSAJE_TAMANIO_MEGA = "El archivo seleccionado a sobrepasado el tamaño máximo de ";
	
	//Años mínimos.
	public static final int ANIO_MINIMO = 15;
	//Rangos grados.
	public static final int RANGO_GRADO_MINIMO = 11;
	public static final int RANGO_GRADO_MAXIMO = 16;
	
	// Nemonicos FASES, relacionado con las reglas de negocio.
	public static final String NEMONICO_INSCRIPCION = "ME-FASE-01";
	public static final String NEMONICO_ASIGNACION = "ME-FASE-04";
	public static final String NEMONICO_RE_ASIGNACION = "ME-FASE-05";
	public static final String NEMONICO_TRASLADO = "ME-FASE-09";

	// Nemonicos Formularios, relacionado con los mensajes de las páginas.
	public static final String NEMONICO_INSCRIPCION_FORMULARIO = "INSCR";
	public static final String NEMONICO_ASIGNACION_AUTOMATICA = "ASGAU";
	public static final String NEMONICO_ASIGNACION_MANUAL = "ASGMA";
	
	// De la fase de inscripción.
	public static final String TITULO_PAGINA_INSCRIPCION = "Formulario de Inscripción Ofertas Educativas Extraordinarias";
	public static final String CAMPOS_OBLIGATORIOS = "Los campos con * son obligatorios. ";
	public static final String ERROR_NUMERO_CEDULA = "Error: Número de Cédula incorrecto. ";
	public static final String ERROR_NUMERO_CEDULA1 = "Error: El número de documento, no está registrado. ";
	public static final String ERROR_EDAD = "Error: La edad del estudiante no corresponde a la requerida en la Oferta. ";
	public static final String DOCUMENTO_CARGO = "El documento se cargo correctamente. ";
	public static final String DOCUMENTO_NO_CARGO = "Error: El documento no se cargo. ";
	public static final String INSCRIPCION_REGISTRADA = "La inscripción se encuentra registrada. Por favor verifique. ";
	public static final String PRESENTAR_DOCUMENTACION = "No se ha registrado la inscripción, el aspirante debe presentar la documentación completa.";
	public static final String DEBE_TENER_IDENTIFICACION = "El aspirante debe presentar su identificación.";
	public static final String NO_GUARDO_INSCRIPCION = "No se guardó la Inscripción... Datos incorrectos... Contactarse con el Administrador!";
	public static final String NO_EXISTE_REGLA_NEGOCIO = "No existe reglas de negocio de la Oferta.";
	public static final String FECHA_FASE_FUERA_RANGO = "Fechas de la FASE fuera de rango.";
	public static final String MENSAJE_CONTINUAR = "Por favor, re-ingresar Oferta Educativa y Documento Presentado para <<Continuar>>";

	//Condición de Fallecido.
	public static final String CONDICION_FALLECIDO_MSG = "El número de documento, tiene condición de fallecido. ";
	public static final Integer CONDICION_FALLECIDO_INT = 7;
	
	// seguridades
	public static final String PREFIJO_APLICACION_EBJA_WEB = "SGE";
	public static final String ESTILO_DEFECTO_SUB_MENU_VERTICAL = "button-menu-style";
	public static final String ESTILO_DEFECTO_ITEM_MENU_VERTICAL = "button-submenu-style";
	public static final String NOMBRE_DEFECTO_SUB_MENU = "Default subMenú";
	public static final String NOMBRE_DEFECTO_ITEM_MENU = "Default item";
	public static final String ICONO_DEFECTO_MENU = "ui-icon-tag";
	public static final String PAGINA_DEFECTO_MENU = "/faces/paginas/principal.xhtml";
	public static final String PATH_MENSAJE_INICIO="/condiciones/mensajeInicio.html";
	public static final String pathArchivoLocal = "C:\\OPT\\DOCUMENTACION\\EBJA\\";
	public static final String pathArchivoServer = "/opt/documentacion/ebja/";
	
	public static final String nombreArchivoJRXML_ASIG_PAR = "ListaEstudianteParalelo";
	public static final String nombreArchivoJRXML_NO_ASIG = "EstudiantesNoMatriculados";
	public static final String nombreReportePorParalelo = "Lista Estudiantes Por Paralelo";
	public static final String nombreReportePorInstitucion = "Lista Estudiantes Por Institución";
	public static final String nombreReporteNoMatriculados = "Lista Estudiantes No Matriculados";
	
	//Mensajes generles:
	public static final String PROCESO_CORRECTO = "El proceso se realizó correctamente";
	public static final String PROCESO_NO_CORRECTO = "El proceso NO se realizó correctamente";
	public static final String NO_EXISTE_REGISTROS = "No existen registros para procesar ";
	public static final String NO_GENERO_ARCHIVO = "No se generó el archivo. ";
	public static final String NO_EXISTE_INSTITUCIONES = "No existe instituciones para la Oferta o seleccionadas. ";
	public static final String PROBLEMAS_BASE_DATOS = "Problemas en BASE DE DATOS, contactese con el administrador.";
	public static final String TEXTO_IMAGEN_INCORRECTO = "El texto de la Imágen es incorrecto.";
	public static final String INGRESE_DATOS = "Ingrese los datos solicitados.";
	public static final String ESTUDIANTE_NO_MATRICULADO = "Estudiante no está matriculado.";
	public static final String CUPO_NO_EXISTE = "Cupo no disponible en la Institución.";
	public static final String CUE_NO_EXISTE = "Código CUE no existe.";
	
	// De la fase de asignación automática y manual.
	public static final String TITULO_PAGINA_ASIGNACION_AUTOMATICA = "Asignación/Matrícula Automática - Estudiante - Institución";
	public static final String TITULO_PAGINA_ASIGNACION_MANUAL = "Asignación/Matrícula Manual - Estudiante - Institución";
	public static final int NUMERO_PROVINCIAS_ECUADOR = 24;
}