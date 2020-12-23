/*=============================================================================================*/
/*============== SCRIPT DE CREACION DE LAS TABLAS DEL SISTEMA EBJA ============================*/
/*============== FECHA CREACION: 23/NOV/2018 ==================================================*/
/*============== NUMERO DE TABLAS: 32 =========================================================*/
/*=============================================================================================*/


CREATE SCHEMA ebja
    AUTHORIZATION postgres;

/*==============================================================*/
/* Table: acuerdo                                               */
/*==============================================================*/
create table ebja.acuerdo (
   id			serial not null,
   nombre               character varying(100) not null,
   nemonico             character varying(30) not null,
   archivo_pdf          character varying(150) null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_acuerdo primary key (id),
   constraint uk_acuerdo_nemonico unique (nemonico)
);

ALTER TABLE ebja.acuerdo 
    OWNER to carmenta;

GRANT SELECT ON TABLE ebja.acuerdo TO backup;

GRANT ALL ON TABLE ebja.acuerdo TO carmenta;

GRANT SELECT ON TABLE ebja.acuerdo TO rol_lectura;

comment on table ebja.acuerdo is
'Tabla que contiene la información de los Acuerdos relacionados, documento legal habilitante para creación de los distintos programas';

comment on column ebja.acuerdo.id is
'Clave Primaria del Acuerdo.';

comment on column ebja.acuerdo.nombre is
'Nombre del Acuerdo.';

comment on column ebja.acuerdo.nemonico is
'Nemónico para identificar el Acuerdo.';

comment on column ebja.acuerdo.archivo_pdf is
'Nombre del Archivo pdf que contiene el acuerdo.';

comment on column ebja.acuerdo.id_usuario_creacion is
'Usuario de creación del programa.';

comment on column ebja.acuerdo.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.acuerdo.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema.';

comment on column ebja.acuerdo.estado is
'Estado del registro (0=inactivo, 1=activo).';

/*==============================================================*/
/* Table: asignatura                                            */
/*==============================================================*/
create table ebja.asignatura (
   id	serial               not null,
   id_area              int4                 not null,
   id_materia           int4                 not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_asignatura primary key (id)
);

ALTER TABLE ebja.asignatura 
    OWNER to carmenta;

GRANT SELECT ON TABLE ebja.asignatura TO backup;

GRANT ALL ON TABLE ebja.asignatura TO carmenta;

GRANT SELECT ON TABLE ebja.asignatura TO rol_lectura;

comment on table ebja.asignatura is
'Tabla que contiene la información de las Asignaturas.';

comment on column ebja.asignatura.id is
'Clave Primaria de la Asignatura.';

comment on column ebja.asignatura.id_area is
'Identificador único de la Tabla Area. (Esquema Public)';

comment on column ebja.asignatura.id_materia is
'Identificador único de la Tabla Materia. (Esquema Public)';

comment on column ebja.asignatura.id_usuario_creacion is
'Codigo del usuario que registra la transacción.';

comment on column ebja.asignatura.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.asignatura.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema.';

comment on column ebja.asignatura.estado is
'Estado del registro (0=inactivo, 1=activo)';

/*==============================================================*/
/* Table: catalogo_ebja                                         */
/*==============================================================*/
create table ebja.catalogo_ebja (
   id	serial               not null,
   id_tipo_catalogo_ebja int4                 not null,
   nombre               character varying(255) not null,
   descripcion          character varying(800) null,
   nemonico             character varying(25) null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_catalogo_ebja primary key (id)
);

ALTER TABLE ebja.catalogo_ebja 
    OWNER to carmenta;

GRANT SELECT ON TABLE ebja.catalogo_ebja TO backup;

GRANT ALL ON TABLE ebja.catalogo_ebja TO carmenta;

GRANT SELECT ON TABLE ebja.catalogo_ebja TO rol_lectura;


comment on table ebja.catalogo_ebja is
'Tabla que contiene la información de los Catálogos relacionados a EBJA.';

comment on column ebja.catalogo_ebja.id is
'Clave Primaria del Catálogo_Ebja.';

comment on column ebja.catalogo_ebja.id_tipo_catalogo_ebja is
'Identificador único de la Tabla de Catálogo_Ebja. (Clave Foránea)';

comment on column ebja.catalogo_ebja.nombre is
'Nombre del Catálogo.';

comment on column ebja.catalogo_ebja.descripcion is
'Descripción del contenido del Catálogo.';

comment on column ebja.catalogo_ebja.nemonico is
'Descripción del nemónico para identificar un Catálogo.';

comment on column ebja.catalogo_ebja.id_usuario_creacion is
'Usuario de cración.';

comment on column ebja.catalogo_ebja.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.catalogo_ebja.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema.';

comment on column ebja.catalogo_ebja.estado is
'Estado del registro (0=inactivo, 1=activo)';

/*==============================================================*/
/* Table: curso_paralelo                                        */
/*==============================================================*/
create table ebja.curso_paralelo (
   id	serial               not null,
   id_programa_institucion int4                 not null,
   id_paralelo          int4                 not null,
   aforo                int2                 not null,
   numero_banca         int2                 not null,
   numero_matriculado   int2                 not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_curso_paralelo primary key (id)
);

ALTER TABLE ebja.curso_paralelo 
    OWNER to carmenta;

GRANT SELECT ON TABLE ebja.curso_paralelo TO backup;

GRANT ALL ON TABLE ebja.curso_paralelo TO carmenta;

GRANT SELECT ON TABLE ebja.curso_paralelo TO rol_lectura;


comment on table ebja.curso_paralelo is
'Tabla que contiene la información de la definición de los Paralelos al Curso.';

comment on column ebja.curso_paralelo.id is
'Clave Primaria del Curso_Paralelo.';

comment on column ebja.curso_paralelo.id_programa_institucion is
'Clave Primaria del Programa_Institución.';

comment on column ebja.curso_paralelo.id_paralelo is
'Identificador único de la Tabla Paralelo.';

comment on column ebja.curso_paralelo.aforo is
'Número de cupos asignados para el curso.';

comment on column ebja.curso_paralelo.numero_banca is
'Número de bancas físicas disponibles en el curso.';

comment on column ebja.curso_paralelo.numero_matriculado is
'Número de estudiantes matrículados en el curso.';

comment on column ebja.curso_paralelo.id_usuario_creacion is
'Codigo del usuario que registra la transacción.';

comment on column ebja.curso_paralelo.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.curso_paralelo.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema';

comment on column ebja.curso_paralelo.estado is
'Estado del registro (0=inactivo, 1=activo).';

/*==============================================================*/
/* Table: dato_familiar                                         */
/*==============================================================*/
create table ebja.dato_familiar (
   id	serial not null,
   id_registro_estudiante int4                 not null,
   id_dato_familiar_catalogo int4                 not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_dato_familiar primary key (id),
   constraint uk_dato_familiar unique (id_registro_estudiante, id_dato_familiar_catalogo)
);

ALTER TABLE ebja.dato_familiar 
    OWNER to carmenta;

GRANT SELECT ON TABLE ebja.dato_familiar TO backup;

GRANT ALL ON TABLE ebja.dato_familiar TO carmenta;

GRANT SELECT ON TABLE ebja.dato_familiar TO rol_lectura;


comment on table ebja.dato_familiar is
'Tabla con la información del  número de hijos por estudiante.
';

comment on column ebja.dato_familiar.id is
'Clave primaria del Dato_Familiar';

comment on column ebja.dato_familiar.id_registro_estudiante is
'Identificador único  de la Tabla Registro_Estudiante. (Clave Foránea)';

comment on column ebja.dato_familiar.id_dato_familiar_catalogo is
'Identificador único   de la Tabla Dato_Familiar. (Clave Foránea)';

comment on column ebja.dato_familiar.id_usuario_creacion is
'Identificador del usuario de creación del registro.';

comment on column ebja.dato_familiar.fecha_creacion is
'Fecha de registro de la transacción.';

comment on column ebja.dato_familiar.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema.';

comment on column ebja.dato_familiar.estado is
'Estado del registro (1=activo, 0=inactivo)';

/*==============================================================*/
/* Table: detalle_regla_negocio                                 */
/*==============================================================*/
create table ebja.detalle_regla_negocio (
   id	serial               not null,
   id_regla_negocio     int4                 not null,
   id_regla_catalogo    int4                 not null,
   valor_regla          character varying(250) not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_detalle_regla_negocio primary key (id)
);

ALTER TABLE ebja.detalle_regla_negocio 
    OWNER to carmenta;

GRANT SELECT ON TABLE ebja.detalle_regla_negocio TO backup;

GRANT ALL ON TABLE ebja.detalle_regla_negocio TO carmenta;

GRANT SELECT ON TABLE ebja.detalle_regla_negocio TO rol_lectura;


comment on table ebja.detalle_regla_negocio is
'Tabla que contiene la información del Detalle de la Regla de Negocio de un Programa.';

comment on column ebja.detalle_regla_negocio.id is
'Clave Primaria del Detalle_Regla_Negocio.';

comment on column ebja.detalle_regla_negocio.id_regla_negocio is
'Identificador único de la Tabla Regla_Negocio. (Clave Foránea)';

comment on column ebja.detalle_regla_negocio.id_regla_catalogo is
'Identificador único de la Tabla Regla_Catalogo. (Clave Foránea)';

comment on column ebja.detalle_regla_negocio.valor_regla is
'Valor para definir la regla de negocio.';

comment on column ebja.detalle_regla_negocio.id_usuario_creacion is
'Usuario de creación de la regla de negocio.';

comment on column ebja.detalle_regla_negocio.fecha_creacion is
'Fecha de creación de la regla de negocio.';

comment on column ebja.detalle_regla_negocio.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema.';

comment on column ebja.detalle_regla_negocio.estado is
'Estado del registro (0=inactivo, 1=activo).';

/*==============================================================*/
/* Table: discapacidad                                          */
/*==============================================================*/
create table ebja.discapacidad (
   id	serial not null,
   id_registro_estudiante int4                 not null,
   id_tipo_discapacidad_catalogo int4                 not null,
   carnet_conadis       character varying(20) null,
   porcentaje           int2                 not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_discapacidad primary key (id)
);

ALTER TABLE ebja.discapacidad 
    OWNER to carmenta;

GRANT SELECT ON TABLE ebja.discapacidad TO backup;

GRANT ALL ON TABLE ebja.discapacidad TO carmenta;

GRANT SELECT ON TABLE ebja.discapacidad TO rol_lectura;

comment on table ebja.discapacidad is
'Tabla con información de la discapacidad que posee el estudiante
';

comment on column ebja.discapacidad.id is
'Clave Primaria de la Discapacidad.';

comment on column ebja.discapacidad.id_registro_estudiante is
'Identificador único de la Tabla Registro_Estudiante. (Clave Foránea)';

comment on column ebja.discapacidad.id_tipo_discapacidad_catalogo is
'Identificador único de la Tabla Catálogo, cuando es  Tipo Discapacidad(225).';

comment on column ebja.discapacidad.carnet_conadis is
'Número del carnet de conadis.';

comment on column ebja.discapacidad.porcentaje is
'Indica el porcentaje de la discapaciad.';

comment on column ebja.discapacidad.id_usuario_creacion is
'Codigo del usuario que registra la transacción.';

comment on column ebja.discapacidad.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.discapacidad.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema.';

comment on column ebja.discapacidad.estado is
'Estado del registro (0=inactivo, 1=activo).';

/*==============================================================*/
/* Table: docente_curso                                         */
/*==============================================================*/
create table ebja.docente_curso (
   id	serial               not null,
   id_programa_institucion int4                 not null,
   id_curso_paralelo    int4                 null,
   id_docente           int4                 not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_docente_curso primary key (id)
);

ALTER TABLE ebja.docente_curso 
    OWNER to carmenta;

GRANT SELECT ON TABLE ebja.docente_curso TO backup;

GRANT ALL ON TABLE ebja.docente_curso TO carmenta;

GRANT SELECT ON TABLE ebja.docente_curso TO rol_lectura;


comment on table ebja.docente_curso is
'Tabla que contiene la información del Docente asignado a un Curso.';

comment on column ebja.docente_curso.id is
'Clave Primaria del Docente_Curso';

comment on column ebja.docente_curso.id_programa_institucion is
'Identificador del Programa Institución.';

comment on column ebja.docente_curso.id_curso_paralelo is
'Identificador único de la Tabla Curso_Paralelo. (Clave Foránea';

comment on column ebja.docente_curso.id_docente is
'Identificador de la Tabla Docente. (Clave Foránea)';

comment on column ebja.docente_curso.id_usuario_creacion is
'Codigo del usuario que registra la transacción.';

comment on column ebja.docente_curso.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.docente_curso.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema';

comment on column ebja.docente_curso.estado is
'Estado del registro (0=inactivo, 1=activo)';

/*==============================================================*/
/* Table: estudiante                                            */
/*==============================================================*/
create table ebja.estudiante (
   id	serial               not null,
   id_inscripcion       int4                 not null,
   id_registro_estudiante int4                 not null,
   codigo_estudiante    character varying(20) not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_estudiante primary key (id)
);

ALTER TABLE ebja.estudiante 
    OWNER to carmenta;

GRANT SELECT ON TABLE ebja.estudiante TO backup;

GRANT ALL ON TABLE ebja.estudiante TO carmenta;

GRANT SELECT ON TABLE ebja.estudiante TO rol_lectura;


comment on table ebja.estudiante is
'Tabla que contiene la información del Estudiante.';

comment on column ebja.estudiante.id is
'Clave Primaria del Estudiante.';

comment on column ebja.estudiante.id_inscripcion is
'Identificador único de la Tabla Inscripción. (Clave Foránea)';

comment on column ebja.estudiante.id_registro_estudiante is
'Identificador único de la Tabla Registro_Estudiante. (Clave Foránea)';

comment on column ebja.estudiante.codigo_estudiante is
'Identificador único de la Tabla Estudiante. (Esquema Public)';

comment on column ebja.estudiante.id_usuario_creacion is
'Codigo del usuario que registra la transacción.';

comment on column ebja.estudiante.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.estudiante.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema';

comment on column ebja.estudiante.estado is
'Estado del registro (0=inactivo, 1=activo)';

/*==============================================================*/
/* Table: fase                                                  */
/*==============================================================*/
create table ebja.fase (
   id	serial not null,
   id_fase_padre        int4                 null,
   nemonico             character varying(30) not null,
   nombre               character varying(255) not null,
   observacion          character varying(1000) null,
   orden                smallint             not null,
   estado               character(1)         not null,
   constraint pk_fase primary key (id)
);

ALTER TABLE ebja.fase 
    OWNER to carmenta;

GRANT SELECT ON TABLE ebja.fase TO backup;

GRANT ALL ON TABLE ebja.fase TO carmenta;

GRANT SELECT ON TABLE ebja.fase TO rol_lectura;


comment on table ebja.fase is
'Tabla con la definición de las fases que intervienen en el proceso de  Ofertas Extraordinarias. Desde la Inscripción hasta la Acreditación.
';

comment on column ebja.fase.id is
'Clave Primaria de la Fase.';

comment on column ebja.fase.id_fase_padre is
'Identificador único de la Fase. (Clave Foránea)';

comment on column ebja.fase.nemonico is
'Descripción del nemónico que identifica a la Fase.';

comment on column ebja.fase.nombre is
'Nombre de la Fase.';

comment on column ebja.fase.observacion is
'Descripción de la Observación de la Fase.';

comment on column ebja.fase.orden is
'Indica el orden que se deben ejecutar las fases de un programa.';

comment on column ebja.fase.estado is
'Estado del registro (0=inactivo, 1=activo).';

/*==============================================================*/
/* Table: formulario                                            */
/*==============================================================*/
create table ebja.formulario (
   id                   serial               not null,
   id_fase              int4                 null,
   nombre               character varying(250) not null,
   nemonico             character varying(30) not null,
   observacion          character varying(500) null,
   estado               character(1)         not null,
   constraint pk_formulario primary key (id),
   constraint uk_formulario_nemonico unique (nemonico)
);

ALTER TABLE ebja.formulario 
    OWNER to carmenta;


GRANT SELECT ON TABLE ebja.formulario TO backup;

GRANT ALL ON TABLE ebja.formulario TO carmenta;

GRANT SELECT ON TABLE ebja.formulario TO rol_lectura;


comment on table ebja.formulario is
'Tabla que contiene la información del nombre del formulario donde se deben desplegar los mensajes informativos.';

comment on column ebja.formulario.id is
'Clave Primaria del Formulario.';

comment on column ebja.formulario.id_fase is
'Identificador único de la Tabla Fase.';

comment on column ebja.formulario.nombre is
'Nombre del formulario.';

comment on column ebja.formulario.nemonico is
'Identificador único del formulario.';

comment on column ebja.formulario.observacion is
'Observación adicional al Formulario.';

comment on column ebja.formulario.estado is
'Estado del registro (0=inactivo, 1=activo).';

/*==============================================================*/
/* Table: inscripcion                                           */
/*==============================================================*/
create table ebja.inscripcion (
   id	serial not null,
   id_programa_ebja     int4                 not null,
   nemonico             character varying(50) not null,
   id_grado_aprobado    int4                 null,
   presenta_certificado character(1)         not null,
   id_rezago_catalogo   int4                 not null,
   apellidos_nombres_usuario character varying(400) null,
   numero_identificacion_usuario character varying(30) null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_inscripcion primary key (id),
   constraint uk_inscripcion_nemonico unique (nemonico)
);

ALTER TABLE ebja.inscripcion 
    OWNER to carmenta;


GRANT SELECT ON TABLE ebja.inscripcion TO backup;

GRANT ALL ON TABLE ebja.inscripcion TO carmenta;

GRANT SELECT ON TABLE ebja.inscripcion TO rol_lectura;


comment on table ebja.inscripcion is
'Tabla que contiene la información detallada de la Inscripción.';

comment on column ebja.inscripcion.id is
'Clave Primaria de la Inscripcion.';

comment on column ebja.inscripcion.id_programa_ebja is
'Identificador  único de la Tabla Programa_Ebja ';

comment on column ebja.inscripcion.nemonico is
'Descripción del nemónico del programa.';

comment on column ebja.inscripcion.id_grado_aprobado is
'Identificador único de la Tabla Grado.';

comment on column ebja.inscripcion.presenta_certificado is
'Presenta el certificado del grado aprovado = 1, No presenta el certificado del grado aprovado = 0';

comment on column ebja.inscripcion.id_rezago_catalogo is
'Identificador único de la Tabla Catálogo, cuando el Tipo Catalogo es  Años de rezago (226).';

comment on column ebja.inscripcion.apellidos_nombres_usuario is
'Descripción de los apellidos y nombres del usuario que registra la inscripción.';

comment on column ebja.inscripcion.numero_identificacion_usuario is
'Descripción del número de identificación del usuario que registra la inscripción.';

comment on column ebja.inscripcion.id_usuario_creacion is
'Usuario de creación de la inscripción.';

comment on column ebja.inscripcion.fecha_creacion is
'Fecha de  creación de la inscripción.';

comment on column ebja.inscripcion.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema.';

comment on column ebja.inscripcion.estado is
'Estado del registro (1=activo, 0=inactivo)';

/*==============================================================*/
/* Table: malla                                                 */
/*==============================================================*/
create table ebja.malla (
   id	serial               not null,
   id_programa_ebja     int4                 not null,
   id_asignatura        int4                 not null,
   descripcion          character varying(350) not null,
   horas_clase          int2                 not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_malla primary key (id)
);

ALTER TABLE ebja.malla 
    OWNER to carmenta;


GRANT SELECT ON TABLE ebja.malla TO backup;

GRANT ALL ON TABLE ebja.malla TO carmenta;

GRANT SELECT ON TABLE ebja.malla TO rol_lectura;


comment on table ebja.malla is
'Tabla que contiene la información de la Malla Curricular de un Programa. Con la definición de las asignaturas que tiene un  Programa.';

comment on column ebja.malla.id is
'Clave Primaria de la Malla.';

comment on column ebja.malla.id_programa_ebja is
'Identificador único del Programa_Ebja. (Clave Foránea)';

comment on column ebja.malla.id_asignatura is
'Identificador único de la Tabla Asignatura. (Clave Foránea)';

comment on column ebja.malla.descripcion is
'Descripción de la Malla.';

comment on column ebja.malla.horas_clase is
'Número de las horas definidas para dictar la clase.';

comment on column ebja.malla.id_usuario_creacion is
'Codigo del usuario que registra la transacción.';

comment on column ebja.malla.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.malla.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema';

comment on column ebja.malla.estado is
'Estado del registro (0=inactivo, 1=activo)';

/*==============================================================*/
/* Table: malla_docente                                         */
/*==============================================================*/
create table ebja.malla_docente (
   id	serial not null,
   id_docente_curso     int4                 not null,
   id_malla             int4                 not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_malla_docente primary key (id)
);

ALTER TABLE ebja.malla_docente                                         
    OWNER to carmenta;



GRANT SELECT ON TABLE ebja.malla_docente TO backup;

GRANT ALL ON TABLE ebja.malla_docente TO carmenta;

GRANT SELECT ON TABLE ebja.malla_docente TO rol_lectura;



comment on table ebja.malla_docente is
'Tabla que contiene la información del Docente asignado a una Malla.';

comment on column ebja.malla_docente.id is
'Clave Primaria de la Malla_Docente.';

comment on column ebja.malla_docente.id_docente_curso is
'Clave Foránea del Docente_Curso';

comment on column ebja.malla_docente.id_malla is
'Identificador único de la Tabla Malla. (Clave Foránea)';

comment on column ebja.malla_docente.id_usuario_creacion is
'Codigo del usuario que registra la transacción.';

comment on column ebja.malla_docente.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.malla_docente.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema';

comment on column ebja.malla_docente.estado is
'Estado del registro (0=inactivo, 1=activo).';

/*==============================================================*/
/* Table: matricula                                             */
/*==============================================================*/
create table ebja.matricula (
   id	serial               not null,
   id_estudiante        int4                 not null,
   id_programa_institucion int4                 not null,
   id_curso_paralelo    int4                 null,
   promover             character(1)         not null,
   id_tipo_proceso_catalogo int4                 not null,
   fecha_proceso        timestamp            not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_matricula primary key (id)
);

ALTER TABLE ebja.matricula 
    OWNER to carmenta;


GRANT SELECT ON TABLE ebja.matricula TO backup;

GRANT ALL ON TABLE ebja.matricula TO carmenta;

GRANT SELECT ON TABLE ebja.matricula TO rol_lectura;

comment on table ebja.matricula is
'Tabla que contiene la información de la Matricula del Estudiante.';

comment on column ebja.matricula.id is
'Clave Primaria de la Matrícula.';

comment on column ebja.matricula.id_estudiante is
'Identificador único de la Tabla Estudiante. (Clave Foránea)';

comment on column ebja.matricula.id_programa_institucion is
'Identificador del programa institución.(Clave Foránea)';

comment on column ebja.matricula.id_curso_paralelo is
'Identificador único de la Tabla Curso_Paralelo. (Clave Foránea)';

comment on column ebja.matricula.promover is
'Indica si el estudiante es promovido al programa siguiente: 0 = Asignado y 1 = Promovido';

comment on column ebja.matricula.id_tipo_proceso_catalogo is
'Tipo de proceso que se realiza en la matricula.';

comment on column ebja.matricula.fecha_proceso is
'Fecha en la que se ejecutó el proceso.';

comment on column ebja.matricula.id_usuario_creacion is
'Codigo del usuario que registra la transacción.';

comment on column ebja.matricula.fecha_creacion is
'Fecha de registro de la matrícula.';

comment on column ebja.matricula.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema';

comment on column ebja.matricula.estado is
'Estado del registro (0=inactivo, 1=activo)';

/*==============================================================*/
/* Table: mensaje                                               */
/*==============================================================*/
create table ebja.mensaje (
   id	serial not null,
   id_programa_ebja     int4                 not null,
   id_formulario        int4                 not null,
   cabecera             character varying(250) not null,
   nemonico             character varying(30) not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_mensaje primary key (id),
   constraint uk_mensaje_nemonico unique (nemonico)
);


ALTER TABLE ebja.mensaje 
    OWNER to carmenta;


GRANT SELECT ON TABLE ebja.mensaje TO backup;

GRANT ALL ON TABLE ebja.mensaje TO carmenta;

GRANT SELECT ON TABLE ebja.mensaje TO rol_lectura;


comment on table ebja.mensaje is
'Tabla que contiene la información de los Mensajes parametrizados de un Programa.';

comment on column ebja.mensaje.id is
'Clave Primaria del Mensaje.';

comment on column ebja.mensaje.id_programa_ebja is
'Identificador único de la Tabla Programa_Ebja. (Clave Foránea)';

comment on column ebja.mensaje.id_formulario is
'Identificador único de la Tabla Formulario. (Clave Foránea)';

comment on column ebja.mensaje.cabecera is
'Descripción del texto del mensaje que se despliega en la cabecera del formulario.';

comment on column ebja.mensaje.nemonico is
'Descripción específica del registro. ';

comment on column ebja.mensaje.id_usuario_creacion is
'Codigo del usuario que registra la transacción.';

comment on column ebja.mensaje.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.mensaje.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema.';

comment on column ebja.mensaje.estado is
'Estado del registro (0=inactivo, 1=activo).';

/*==============================================================*/
/* Table: modalidad                                             */
/*==============================================================*/
create table ebja.modalidad (
   id	serial               not null,
   nemonico             character varying(30) not null,
   nombre               character varying(150) not null,
   estado               character(1)         not null,
   constraint pk_modalidad primary key (id),
   constraint uk_modalidad_nemonico unique (nemonico)
);

ALTER TABLE ebja.modalidad 
    OWNER to carmenta;


GRANT SELECT ON TABLE ebja.modalidad TO backup;

GRANT ALL ON TABLE ebja.modalidad TO carmenta;

GRANT SELECT ON TABLE ebja.modalidad TO rol_lectura;


comment on table ebja.modalidad is
'Tabla que contiene la información de la Modalidad del Programa. ';

comment on column ebja.modalidad.id is
'Clave Primaria de la Modalidad.';

comment on column ebja.modalidad.nemonico is
'Descripción del nemónico que identifica a la Modalidad.';

comment on column ebja.modalidad.nombre is
'Nombre de la Modalidad.';

comment on column ebja.modalidad.estado is
'Estado del registro (0=inactivo, 1=activo)';

/*==============================================================*/
/* Table: modelo_asistencia                                     */
/*==============================================================*/
create table ebja.modelo_asistencia (
   id	serial not null,
   id_programa_ebja     int4                 not null,
   id_tipo_asistencia_catalogo int4                 not null,
   porcentaje_asistencia_parcial int2                 not null,
   porcentaje_asistencia_materia int2                 not null,
   id_usuario_creacion__ int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_modelo_asistencia primary key (id)
);


ALTER TABLE ebja.modelo_asistencia 
    OWNER to carmenta;


GRANT SELECT ON TABLE ebja.modelo_asistencia TO backup;

GRANT ALL ON TABLE ebja.modelo_asistencia TO carmenta;

GRANT SELECT ON TABLE ebja.modelo_asistencia TO rol_lectura;

comment on table ebja.modelo_asistencia is
'Tabla que contiene la información del Modelo de Asistencia de un Programa.';

comment on column ebja.modelo_asistencia.id is
'Clave Primaria del Modelo_Asistencia.';

comment on column ebja.modelo_asistencia.id_programa_ebja is
'Identificador único de la Tabla Programa_Ebja. (Clave Foránea)';

comment on column ebja.modelo_asistencia.id_tipo_asistencia_catalogo is
'Identificador único de la Tabla Catálogo, cuando es  Tipo Asistencia ()';

comment on column ebja.modelo_asistencia.porcentaje_asistencia_parcial is
'Porcentaje de asistencia definido para el Programa.';

comment on column ebja.modelo_asistencia.porcentaje_asistencia_materia is
'Porcentaje de asistencia definido por Materia.';

comment on column ebja.modelo_asistencia.id_usuario_creacion__ is
'Codigo del usuario que registra la transacción.';

comment on column ebja.modelo_asistencia.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.modelo_asistencia.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema.';

comment on column ebja.modelo_asistencia.estado is
'Estado del registro (0=inactivo, 1=activo).';

/*==============================================================*/
/* Table: modelo_evaluacion                                     */
/*==============================================================*/
create table ebja.modelo_evaluacion (
   id	serial not null,
   id_programa_ebja     int4                 not null,
   numero_quimestre     int2                 not null,
   numero_parcial       int2                 not null,
   porcentaje_promedio_parcial int2                 not null,
   numero_examen        int2                 not null,
   porcentaje_examen    int2                 not null,
   numero_decimal_calificacion int2                 not null,
   existe_supletorio    character(1)         not null,
   existe_remedial      character(1)         not null,
   existe_examen_gracia character(1)         not null,
   fecha_inicio_supletorio timestamp            null,
   fecha_fin_supletorio timestamp            null,
   fecha_inicio_remedial timestamp            null,
   fecha_fin_remedial   timestamp            null,
   fecha_inicio_gracia  timestamp            null,
   fecha_fin_gracia     timestamp            null,
   id_usuario_creacion  int4                 null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_modelo_evaluacion primary key (id)
);

ALTER TABLE ebja.modelo_evaluacion 
    OWNER to carmenta;


GRANT SELECT ON TABLE ebja.modelo_evaluacion TO backup;

GRANT ALL ON TABLE ebja.modelo_evaluacion TO carmenta;

GRANT SELECT ON TABLE ebja.modelo_evaluacion TO rol_lectura;


comment on table ebja.modelo_evaluacion is
'Tabla que contiene la información del Modelo de Evaluación de un Programa.';

comment on column ebja.modelo_evaluacion.id is
'Clave Primaria del Modelo_Evaluación.';

comment on column ebja.modelo_evaluacion.id_programa_ebja is
'Identificador único de la Tabla Programa_Ebja. (Clave Foránea)';

comment on column ebja.modelo_evaluacion.numero_quimestre is
'Número de quimestre definidos para un Programa.';

comment on column ebja.modelo_evaluacion.numero_parcial is
'Número de parciales definidos para un Programa.';

comment on column ebja.modelo_evaluacion.porcentaje_promedio_parcial is
'Definición del valor del porcentaje promedio parcial para un programa.';

comment on column ebja.modelo_evaluacion.numero_examen is
'Definición del número de examenes que debe tener un programa.';

comment on column ebja.modelo_evaluacion.porcentaje_examen is
'Definición del porcentaje que representa el examen.';

comment on column ebja.modelo_evaluacion.numero_decimal_calificacion is
'Definición del número de decimales que se ocupan para los cálculos de una calificación.';

comment on column ebja.modelo_evaluacion.existe_supletorio is
'Define si existe el examen supletorio.';

comment on column ebja.modelo_evaluacion.existe_remedial is
'Define si existe el examen remedial.';

comment on column ebja.modelo_evaluacion.existe_examen_gracia is
'Define si existe el examen de gracias.';

comment on column ebja.modelo_evaluacion.fecha_inicio_supletorio is
'Fecha de Inicio del examen supletorio.';

comment on column ebja.modelo_evaluacion.fecha_fin_supletorio is
'Fecha de Finalización del examen supletorio.';

comment on column ebja.modelo_evaluacion.fecha_inicio_remedial is
'Fecha de Incio del examen remedial.';

comment on column ebja.modelo_evaluacion.fecha_fin_remedial is
'Fecha de Finalización del examen remedial.';

comment on column ebja.modelo_evaluacion.fecha_inicio_gracia is
'Fecha de Incio del examen de gracia.';

comment on column ebja.modelo_evaluacion.fecha_fin_gracia is
'Fecha de Finalización del examen de gracia.';

comment on column ebja.modelo_evaluacion.id_usuario_creacion is
'Codigo del usuario que registra la transacción.';

comment on column ebja.modelo_evaluacion.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.modelo_evaluacion.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema';

comment on column ebja.modelo_evaluacion.estado is
'Estado del registro (0=inactivo, 1=activo).';

/*==============================================================*/
/* Table: modelo_evaluacion_parcial                             */
/*==============================================================*/
create table ebja.modelo_evaluacion_parcial (
   id	serial               not null,
   id_modelo_evaluacion_quimestre int4                 not null,
   parcial              int2                 not null,
   fecha_inicio         timestamp            not null,
   fecha_fin            timestamp            not null,
   fecha_inicio_examen  timestamp            not null,
   fecha_fin_examen     timestamp            not null,
   fecha_inicio_ing_notas timestamp            not null,
   fecha_fin_ing_notas  timestamp            not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_modelo_evaluacion_parcial primary key (id),
   constraint uk_modelo_evaluacion_parcial unique (id_modelo_evaluacion_quimestre, parcial)
);

ALTER TABLE ebja.modelo_evaluacion_parcial 
    OWNER to carmenta;


GRANT SELECT ON TABLE ebja.modelo_evaluacion_parcial TO backup;

GRANT ALL ON TABLE ebja.modelo_evaluacion_parcial TO carmenta;

GRANT SELECT ON TABLE ebja.modelo_evaluacion_parcial TO rol_lectura;

comment on table ebja.modelo_evaluacion_parcial is
'Tabla que contiene la información del Modelo de Evaluación de los Parciales de un Quimestre.';

comment on column ebja.modelo_evaluacion_parcial.id is
'Clave Primaria del Modelo_Parcial.';

comment on column ebja.modelo_evaluacion_parcial.id_modelo_evaluacion_quimestre is
'Identificador único de la Tabla Modelo_Evaluacion_Quimestral';

comment on column ebja.modelo_evaluacion_parcial.parcial is
'Identificador del parcial';

comment on column ebja.modelo_evaluacion_parcial.fecha_inicio is
'Fecha de Inicio del Parcial.';

comment on column ebja.modelo_evaluacion_parcial.fecha_fin is
'Fecha de Finalización del Parcial.';

comment on column ebja.modelo_evaluacion_parcial.fecha_inicio_examen is
'Fecha de inicio del examen.';

comment on column ebja.modelo_evaluacion_parcial.fecha_fin_examen is
'Fecha de finalización del examen.';

comment on column ebja.modelo_evaluacion_parcial.fecha_inicio_ing_notas is
'Fecha de Inicio del ingreso de notas.';

comment on column ebja.modelo_evaluacion_parcial.fecha_fin_ing_notas is
'Fecha de Finalización del ingreso de notas.';

comment on column ebja.modelo_evaluacion_parcial.id_usuario_creacion is
'Codigo del usuario que registra la transacción.';

comment on column ebja.modelo_evaluacion_parcial.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.modelo_evaluacion_parcial.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema';

comment on column ebja.modelo_evaluacion_parcial.estado is
'Estado del registro (0=inactivo, 1=activo).';

/*==============================================================*/
/* Table: modelo_evaluacion_quimestre                           */
/*==============================================================*/
create table ebja.modelo_evaluacion_quimestre (
   id	serial               not null,
   id_modelo_evaluacion int4                 not null,
   quimestre            int2                 not null,
   fecha_inicio_examen  timestamp            not null,
   fecha_fin_examen     timestamp            not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_modelo_evaluacion_quimestre primary key (id),
   constraint uk_modelo_evaluacion_quimestre unique (id_modelo_evaluacion, quimestre)
);

ALTER TABLE ebja.modelo_evaluacion_quimestre 
    OWNER to carmenta;


GRANT SELECT ON TABLE ebja.modelo_evaluacion_quimestre TO backup;

GRANT ALL ON TABLE ebja.modelo_evaluacion_quimestre TO carmenta;

GRANT SELECT ON TABLE ebja.modelo_evaluacion_quimestre TO rol_lectura;


comment on table ebja.modelo_evaluacion_quimestre is
'Tabla que contiene la información del Modelo de Evaluación por Quimestre.
';

comment on column ebja.modelo_evaluacion_quimestre.id is
'Clave Primaria del Modelo_Evaluacion_Quimestre.';

comment on column ebja.modelo_evaluacion_quimestre.id_modelo_evaluacion is
'Identificador único de la Tabla de Modelo_Evaluacion. (Clave Foránea)';

comment on column ebja.modelo_evaluacion_quimestre.quimestre is
'Descripción del quimestre del Modelo de Evaluación.';

comment on column ebja.modelo_evaluacion_quimestre.fecha_inicio_examen is
'Fecha de Inicio del examen del quimestre.';

comment on column ebja.modelo_evaluacion_quimestre.fecha_fin_examen is
'Fecha de Finalización del examen del quimestre,';

comment on column ebja.modelo_evaluacion_quimestre.id_usuario_creacion is
'Codigo del usuario que registra la transacción.';

comment on column ebja.modelo_evaluacion_quimestre.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.modelo_evaluacion_quimestre.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema.';

comment on column ebja.modelo_evaluacion_quimestre.estado is
'Estado del registro (0=inactivo, 1=activo).';

/*==============================================================*/
/* Table: pais                                                  */
/*==============================================================*/
create table ebja.pais (
   id	serial not null,
   nombre               character varying(150) not null,
   estado               character(1)         not null,
   constraint pk_pais primary key (id)
);

ALTER TABLE ebja.pais 
    OWNER to carmenta;


GRANT SELECT ON TABLE ebja.pais TO backup;

GRANT ALL ON TABLE ebja.pais TO carmenta;

GRANT SELECT ON TABLE ebja.pais TO rol_lectura;

comment on table ebja.pais is
'Tabla que contiene la información de los paises.';

comment on column ebja.pais.id is
'Clave Primaria del Pais.';

comment on column ebja.pais.nombre is
'Descripción del nombre del país.';

comment on column ebja.pais.estado is
'Estado del registro (1=activo, 0=inactivo)';

/*==============================================================*/
/* Table: programa_acuerdo                                      */
/*==============================================================*/
create table ebja.programa_acuerdo (
   id	serial not null,
   id_programa_ebja     int4                 not null,
   id_acuerdo           int4                 not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_programa_acuerdo primary key (id),
   constraint uk_programa_acuerdo unique (id_programa_ebja, id_acuerdo)
);

ALTER TABLE ebja.programa_acuerdo 
    OWNER to carmenta;


GRANT SELECT ON TABLE ebja.programa_acuerdo TO backup;

GRANT ALL ON TABLE ebja.programa_acuerdo TO carmenta;

GRANT SELECT ON TABLE ebja.programa_acuerdo TO rol_lectura;


comment on table ebja.programa_acuerdo is
'Tabla que contiene la información de los Acuerdos de los distintos Programas';

comment on column ebja.programa_acuerdo.id is
'Clave Prmaria del Programa_Acuerdo.';

comment on column ebja.programa_acuerdo.id_programa_ebja is
'Identificador único de la Tabla Programa_Ebja. (Clave Foránea)';

comment on column ebja.programa_acuerdo.id_acuerdo is
'Identificador único de la Tabla Acuerdo. (Clave Foránea)';

comment on column ebja.programa_acuerdo.id_usuario_creacion is
'Usuario de creación del programa acuerdo.';

comment on column ebja.programa_acuerdo.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.programa_acuerdo.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema.';

comment on column ebja.programa_acuerdo.estado is
'Estado del registro (0=inactivo, 1=activo).';

/*==============================================================*/
/* Table: programa_ebja                                         */
/*==============================================================*/
create table ebja.programa_ebja (
   id	serial not null,
   id_tipo_programa     int4                 not null,
   id_modalidad         int4                 not null,
   nombre               character varying(150) not null,
   nemonico             character varying(60) not null,
   fecha_inicio         timestamp            not null,
   fecha_fin            timestamp            not null,
   edad_minima          int2                 not null,
   rezago_minimo        int2                 not null,
   cobertura            character(1)         not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   fecha_inicio_clases  TIMESTAMP            not null,
   constraint pk_programa_ebja primary key (id),
   constraint uk_programa_nemonico unique (nemonico)
);

ALTER TABLE ebja.programa_ebja 
    OWNER to carmenta;


GRANT SELECT ON TABLE ebja.programa_ebja TO backup;

GRANT ALL ON TABLE ebja.programa_ebja TO carmenta;

GRANT SELECT ON TABLE ebja.programa_ebja TO rol_lectura;


comment on table ebja.programa_ebja is
'Tabla que contiene la información de los Programas creados  en función  de los Acuerdos registrados.';

comment on column ebja.programa_ebja.id is
'Clave Primaria del Programa_Ebja.';

comment on column ebja.programa_ebja.id_tipo_programa is
'Identificador único de la Tabla TipoPrograma. (Clave Foránea)';

comment on column ebja.programa_ebja.id_modalidad is
'Identificador único de la Tabla Modalidad (Clave Foránea)';

comment on column ebja.programa_ebja.nombre is
'Descripción del nombre del Programa.';

comment on column ebja.programa_ebja.nemonico is
'Descripción del nombre del Nemónico que identifica al Programa.';

comment on column ebja.programa_ebja.fecha_inicio is
'Fecha de Inicio del Programa.';

comment on column ebja.programa_ebja.fecha_fin is
'Fecha de Finalización del Programa.';

comment on column ebja.programa_ebja.edad_minima is
'Edad mínima establecida para la inscripción de los aspirantes.';

comment on column ebja.programa_ebja.rezago_minimo is
'Número de años de rezago establecidos para la inscripción de los aspirantes.';

comment on column ebja.programa_ebja.cobertura is
'Especifica el ámbito de cobertura del programa(módulo)  ( 0=Cobertura Nacional , 1=Cobertura Internacional)';

comment on column ebja.programa_ebja.id_usuario_creacion is
'Usuario que registro el programa.';

comment on column ebja.programa_ebja.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.programa_ebja.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema.
';

comment on column ebja.programa_ebja.estado is
'Estado del registro (1=activo, 0=inactivo)';

/*==============================================================*/
/* Table: programa_grado                                        */
/*==============================================================*/
create table ebja.programa_grado (
   id	serial not null,
   id_programa_ebja     int4                 not null,
   id_grado             int4                 not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_programa_grado primary key (id),
   constraint uk_programa_grado unique (id_grado, id_programa_ebja)
);

ALTER TABLE ebja.programa_grado 
    OWNER to carmenta;


GRANT SELECT ON TABLE ebja.programa_grado TO backup;

GRANT ALL ON TABLE ebja.programa_grado TO carmenta;

GRANT SELECT ON TABLE ebja.programa_grado TO rol_lectura;


comment on table ebja.programa_grado is
'Tabla que contiene la información de los grados definidos en en un programa.Ejemplo Alfabetización tiene dos grados (primero y segundo)';

comment on column ebja.programa_grado.id is
'Clave Primaria del Programa_Grado.';

comment on column ebja.programa_grado.id_programa_ebja is
'Identificador único de la Tabla Programa_Ebja . (Clave Foránea)';

comment on column ebja.programa_grado.id_grado is
'Identificador único de la Tabla Grado. (Esquema Public)';

comment on column ebja.programa_grado.id_usuario_creacion is
'Codigo del usuario que registra la transacción.';

comment on column ebja.programa_grado.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.programa_grado.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema.';

comment on column ebja.programa_grado.estado is
'Estado del registro (1=activo, 0=inactivo)';

/*==============================================================*/
/* Table: programa_institucion                                  */
/*==============================================================*/
create table ebja.programa_institucion (
   id	serial               not null,
   id_programa_ebja     int4                 not null,
   id_instituc_establec int4                 not null,
   total_aforo          int2                 not null,
   total_banca          int2                 not null,
   cupo_disponible      int2                 not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_programa_institucion primary key (id)
);

ALTER TABLE ebja.programa_institucion 
    OWNER to carmenta;

GRANT SELECT ON TABLE ebja.programa_institucion TO backup;

GRANT ALL ON TABLE ebja.programa_institucion TO carmenta;

GRANT SELECT ON TABLE ebja.programa_institucion TO rol_lectura;


comment on table ebja.programa_institucion is
'Tabla que contiene la información de la asignación de una Institución a un Programa.';

comment on column ebja.programa_institucion.id is
'Clave Primaria del Programa_Institución.';

comment on column ebja.programa_institucion.id_programa_ebja is
'Identificador único de la Tabla Progrma_Ebja. (Clave Foránea)';

comment on column ebja.programa_institucion.id_instituc_establec  is
'Identificador único de la Tabla Institución. (Esquema Public)';

comment on column ebja.programa_institucion.total_aforo is
'Número Total de cupos asignados para la Institución y Programa definido. Sumatoria del Aforo de la Tabla Curso_Paralelo.';

comment on column ebja.programa_institucion.total_banca is
'Número Total de bancas disponibles para la Institución y Programa definido.Sumatorio de las Bancas de la Tabla Curso_Paralelo.';

comment on column ebja.programa_institucion.cupo_disponible is
'Número de Cupos disponibles para la Institución y Programa definido.';

comment on column ebja.programa_institucion.id_usuario_creacion is
'Codigo del usuario que registra la transacción.';

comment on column ebja.programa_institucion.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.programa_institucion.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema';

comment on column ebja.programa_institucion.estado is
'Estado del registro (0=inactivo, 1=activo).';

/*==============================================================*/
/* Table: registro_estudiante                                   */
/*==============================================================*/
create table ebja.registro_estudiante (
   id	serial               not null,
   id_pais              int4                 null,
   id_inscripcion       int4                 not null,
   id_tipo_identificacion_catalogo int4                 not null,
   id_genero_catalogo   int4                 not null,
   id_estado_civil_catalogo int4                 not null,
   id_etnia             int2                 not null,
   id_nacionalidad_catalogo int4                 null,
   id_situacion_laboral_catalogo int4                 null,
   id_actividad_economica_catalogo int4                 not null,
   numero_identificacion character varying(30) not null,
   apellidos_nombres    character varying(250) not null,
   fecha_nacimiento     timestamp            not null,
   edad                 int2                 not null,
   tiene_hijo           character(1)         not null,
   tiene_discapacidad   character(1)         not null,
   recibe_bono          character(1)         not null,
   nacionalidad_ecuatoriana character(1)         not null,
   telefono             character varying(20) not null,
   telefono_convencional character varying(20) null,
   correo_electronico   character varying(250) null,
   autorepresentado     character(1)         not null,
   estado_asignacion    character(1)         null,
   id_documento_catalogo int2                 null,
   id_motivo_catalogo    int2                 null,
   archivo_presentado   character varying(150) null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_registro_estudiante primary key (id),
   constraint uk_registro_estudiante unique (id_inscripcion)
);

ALTER TABLE ebja.registro_estudiante 
    OWNER to carmenta;

GRANT SELECT ON TABLE ebja.registro_estudiante TO backup;

GRANT ALL ON TABLE ebja.registro_estudiante TO carmenta;

GRANT SELECT ON TABLE ebja.registro_estudiante TO rol_lectura;


comment on table ebja.registro_estudiante is
'Tabla que contiene la información de los aspirantes.';

comment on column ebja.registro_estudiante.id is
'Clave Primaria del Registro_Estudiante';

comment on column ebja.registro_estudiante.id_pais is
'Identificador único de la Tabla Pais. (Clave Foránea)';

comment on column ebja.registro_estudiante.id_inscripcion is
'Identificador único de la Tabla  Inscripcion.(Clave Foránea)';

comment on column ebja.registro_estudiante.id_tipo_identificacion_catalogo is
'Identificador único del Tipo Identificación del documento legal presentado.';

comment on column ebja.registro_estudiante.id_genero_catalogo is
'Identificador único del Tipo Género del estudiante.';

comment on column ebja.registro_estudiante.id_estado_civil_catalogo is
'Identificador único del estado civil actual de estudiante.';

comment on column ebja.registro_estudiante.id_etnia is
'Identificador único de la etnia con la que se identifica.';

comment on column ebja.registro_estudiante.id_nacionalidad_catalogo is
'Identificador único de la nacionalidad a la que corresponde.';

comment on column ebja.registro_estudiante.id_situacion_laboral_catalogo is
'Identificador único de la  Situación Laboral, si trabaja o no.';

comment on column ebja.registro_estudiante.id_actividad_economica_catalogo is
'Identificador único de la   Actividad Económinca a la que se dedica.';

comment on column ebja.registro_estudiante.numero_identificacion is
'Descripción del número de identificación del estudiante.';

comment on column ebja.registro_estudiante.apellidos_nombres is
'Descripción de los apellidos y nombres del estudiante.';

comment on column ebja.registro_estudiante.fecha_nacimiento is
'Descripción  de la fecha de nacimiento del estudiante.';

comment on column ebja.registro_estudiante.edad is
'Descripción de la edad del estudiante a la fecha de la inscripción.';

comment on column ebja.registro_estudiante.tiene_hijo is
'Indica si el estudiante  tiene hijos, (0 =No tiene hijos , 1 = Tiene hijos)';

comment on column ebja.registro_estudiante.tiene_discapacidad is
'Indica si el estudiante  tiene discapacidad (0 =No tiene discapacidad , 1 = Tiene discapacidad )';

comment on column ebja.registro_estudiante.recibe_bono is
'Indica si el estudiante recibe el bono, (0 =No recibe , 1 = Si recibe)';

comment on column ebja.registro_estudiante.nacionalidad_ecuatoriana is
'Indica si el estudiante  tiene nacionalidad ecuatoriana, (0 =No tiene nacionalidad ecuatoriana ,  1 = Tiene nacionalidad ecuatoriana)';

comment on column ebja.registro_estudiante.telefono is
'Descripción del número de teléfono celular del estudiante.';

comment on column ebja.registro_estudiante.telefono_convencional is
'Descripción del número de teléfono convencional del estudiante.';

comment on column ebja.registro_estudiante.correo_electronico is
'Descripción del correo electrónico del estudiante.';

comment on column ebja.registro_estudiante.autorepresentado is
'Indica si el estudiante se representa a si mismo. (0 = No se representa, 1 = Se representa a si mismo).';

comment on column ebja.registro_estudiante.estado_asignacion is
'Indica si el estudiante esta matriculado o asignado . ( 0 = No asignado, 1= Asignado)';

comment on column ebja.registro_estudiante.id_documento_catalogo  is
'Identificador único del tipo de documento presentado durante la inscripcion.';

comment on column ebja.registro_estudiante.id_motivo_catalogo   is
'Identificador único del motivo de la documentacion no presentada en la inscripción';

comment on column ebja.registro_estudiante.archivo_presentado is
'Ubicación o path del archivo físico presentado.';

comment on column ebja.registro_estudiante.id_usuario_creacion is
'Usuario de creación del registro del estudiante.';

comment on column ebja.registro_estudiante.fecha_creacion is
'Fecha de creación del registro del estudiante.';

comment on column ebja.registro_estudiante.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema.';

comment on column ebja.registro_estudiante.estado is
'Estado del registro (1=activo, 0=inactivo).';

/*==============================================================*/
/* Table: regla_negocio                                         */
/*==============================================================*/
create table ebja.regla_negocio (
   id	serial not null,
   id_programa_ebja     int4                 not null,
   id_fase              int4                 not null,
   fecha_inicio         timestamp            not null,
   fecha_fin            timestamp            not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_regla_negocio primary key (id),
   constraint uk_regla_negocio unique (id_fase, id_programa_ebja)
);


ALTER TABLE ebja.regla_negocio 
    OWNER to carmenta;

GRANT SELECT ON TABLE ebja.regla_negocio TO backup;

GRANT ALL ON TABLE ebja.regla_negocio TO carmenta;

GRANT SELECT ON TABLE ebja.regla_negocio TO rol_lectura;


comment on table ebja.regla_negocio is
'Tabla que contiene la información de las Reglas de Negocio definidas para un Programa.';

comment on column ebja.regla_negocio.id is
'Clave Primaria de la Regla_Negocio.';

comment on column ebja.regla_negocio.id_programa_ebja is
'Identificador único de la Tabla Programa_Ebja. (Clave Foránea)';

comment on column ebja.regla_negocio.id_fase is
'Identificador único de la Tabla Fase. (Clave Foránea)';

comment on column ebja.regla_negocio.fecha_inicio is
'Fecha de Inicio definido para la fase del Programa.';

comment on column ebja.regla_negocio.fecha_fin is
'Fecha de Finalización definido para la fase del Programa.';

comment on column ebja.regla_negocio.id_usuario_creacion is
'Codigo del usuario que registra la transacción.
Codigo del usuario que registra la transacción.';

comment on column ebja.regla_negocio.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.regla_negocio.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema.';

comment on column ebja.regla_negocio.estado is
'Estado del registro (0=inactivo, 1=activo).';

/*==============================================================*/
/* Table: representante                                         */
/*==============================================================*/
create table ebja.representante (
   id	serial not null,
   id_registro_estudiante int4                 not null,
   id_tipo_identificacion_catalogo int4                 not null,
   numero_identificacion character varying(30) null,
   apellido_nombre      character varying(400) not null,
   fecha_nacimiento     timestamp            not null,
   id_genero_catalogo   int4                 not null,
   id_estado_civil_catalogo int4                 not null,
   representante_legal  character(1)         not null,
   id_parentesco_catalogo int4                 not null,
   telefono_celular     character varying(20) null,
   telefono_convencional character varying(20) null,
   correo_electronico   character varying(250) null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   estado               character(1)         not null,
   constraint pk_representante primary key (id)
);

ALTER TABLE ebja.representante 
    OWNER to carmenta;

GRANT SELECT ON TABLE ebja.representante TO backup;

GRANT ALL ON TABLE ebja.representante TO carmenta;

GRANT SELECT ON TABLE ebja.representante TO rol_lectura;

comment on table ebja.representante is
'Tabla que contiene la información del representante del estudiante.';

comment on column ebja.representante.id is
'Clave Primaria del Representante.';

comment on column ebja.representante.id_registro_estudiante is
'Identificador único de la Tabla Registro_Estudiante. (Clave Foránea)';

comment on column ebja.representante.id_tipo_identificacion_catalogo is
'Identificador único de la Tabla Catálogo, cuando es  Tipo Identificación  (226).';

comment on column ebja.representante.numero_identificacion is
'Descripción del número de identificación.';

comment on column ebja.representante.apellido_nombre is
'Descripción del nombre y apellido del representante.';

comment on column ebja.representante.fecha_nacimiento is
'Descripción de la fecha de nacimiento.';

comment on column ebja.representante.id_genero_catalogo is
'Identificador único de la Tabla Catálogo, cuando es  Grupo Género  (4).';

comment on column ebja.representante.id_estado_civil_catalogo is
'Identificador único de la Tabla Catálogo, cuando es Estado Civil  (3).';

comment on column ebja.representante.representante_legal is
'Representante Legal. ( Si es Representante = 1, No es Representante = 0)';

comment on column ebja.representante.id_parentesco_catalogo is
'Identificador único de la Tabla Catálogo, cuando es Parentesco  (5).';

comment on column ebja.representante.telefono_celular is
'Descripción del número de teléfono celular.';

comment on column ebja.representante.telefono_convencional is
'Descripción del número de teléfono convencional.';

comment on column ebja.representante.correo_electronico is
'Descripción del correo electrónico.';

comment on column ebja.representante.id_usuario_creacion is
'Usuario de cración.';

comment on column ebja.representante.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.representante.estado is
'Estado del registro (1=activo, 0=inactivo)';

/*==============================================================*/
/* Table: tipo_catalogo_ebja                                    */
/*==============================================================*/
create table ebja.tipo_catalogo_ebja (
   id	serial               not null,
   nombre               character varying(100) not null,
   descripcion          character varying(255) null,
   nemonico             character varying(25) not null,
   editable             character(1)         not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_tipo_catalogo_ebja primary key (id),
   constraint uk_tipo_catalogo_nemonico unique (nemonico)
);


ALTER TABLE ebja.tipo_catalogo_ebja 
    OWNER to carmenta;

GRANT SELECT ON TABLE ebja.tipo_catalogo_ebja TO backup;

GRANT ALL ON TABLE ebja.tipo_catalogo_ebja TO carmenta;

GRANT SELECT ON TABLE ebja.tipo_catalogo_ebja TO rol_lectura;


comment on table ebja.tipo_catalogo_ebja is
'Tabla que contiene la información de los Tipos de Catálogos.';

comment on column ebja.tipo_catalogo_ebja.id is
'Clave Primaria del Tipo_Catalogo_Ebja.';

comment on column ebja.tipo_catalogo_ebja.nombre is
'Nombre del Catálogo.';

comment on column ebja.tipo_catalogo_ebja.descripcion is
'Descripción del contenido del Catálogo.';

comment on column ebja.tipo_catalogo_ebja.nemonico is
'Descripción del nemónico para identificar al Tipo_Catalogo_Ebja.';

comment on column ebja.tipo_catalogo_ebja.editable is
'Indica si se puede editar el Catálogo. (0=No se puede Editar, 1=Si se puede Editar)';

comment on column ebja.tipo_catalogo_ebja.id_usuario_creacion is
'Usuario de cración.';

comment on column ebja.tipo_catalogo_ebja.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.tipo_catalogo_ebja.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema.';

comment on column ebja.tipo_catalogo_ebja.estado is
'Estado del registro (0=inactivo, 1=activo)';

/*==============================================================*/
/* Table: tipo_programa                                         */
/*==============================================================*/
create table ebja.tipo_programa (
   id	serial not null,
   nemonico             character varying(30) not null,
   nombre               character varying(255) not null,
   estado               character(1)         not null,
   constraint pk_tipo_programa primary key (id),
   constraint uk_tipo_programa_nemonico unique (nemonico)
);

ALTER TABLE ebja.tipo_programa 
    OWNER to carmenta;


GRANT SELECT ON TABLE ebja.tipo_programa TO backup;

GRANT ALL ON TABLE ebja.tipo_programa TO carmenta;

GRANT SELECT ON TABLE ebja.tipo_programa TO rol_lectura;


comment on table ebja.tipo_programa is
'Tabla que contiene la información de los Tipos de Programa. ';

comment on column ebja.tipo_programa.id is
'Clave Primaria del Tipo_ Programa.';

comment on column ebja.tipo_programa.nemonico is
'Descripción del nemónico que identifica al Tipo del Programa.';

comment on column ebja.tipo_programa.nombre is
'Nombre del Tipo del Programa.';

comment on column ebja.tipo_programa.estado is
'Estado del registro (0=inactivo, 1=activo)';

/*==============================================================*/
/* Table: ubicacion                                             */
/*==============================================================*/
create table ebja.ubicacion (
   id	serial not null,
   id_pais              int4                 not null,
   id_inscripcion       int4                 not null,
   id_parroquia         int2                 null,
   id_circuito          int2                 null,
   numero_suministro    character varying(20) null,
   codigo_postal        character varying(15) null,
   calle_principal      character varying(1024) null,
   calle_interseccion   character varying(1024) null,
   referencia           character varying(300) null,
   coordenada_x         character varying(25) null,
   coordenada_y         character varying(25) null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_ubicacion primary key (id)
);

ALTER TABLE ebja.ubicacion 
    OWNER to carmenta;


GRANT SELECT ON TABLE ebja.ubicacion TO backup;

GRANT ALL ON TABLE ebja.ubicacion TO carmenta;

GRANT SELECT ON TABLE ebja.ubicacion TO rol_lectura;


comment on table ebja.ubicacion is
'Tabla que contiene la información de la ubicación del estudiante.';

comment on column ebja.ubicacion.id is
'Identificador único secuencial de la ubicación. (Clave Primaria)';

comment on column ebja.ubicacion.id_pais is
'Identificador único del código del país. (Clave Foránea)';

comment on column ebja.ubicacion.id_inscripcion is
'Identificador único del código de la inscripción (Clave Foránea)';

comment on column ebja.ubicacion.id_parroquia is
'Identificador único del código de la parroquia.';

comment on column ebja.ubicacion.id_circuito is
'Identificador único del código del circuito.';

comment on column ebja.ubicacion.numero_suministro is
'Número del suministro de la planilla eléctrica CUE (Código Unico Eléctrico) ';

comment on column ebja.ubicacion.codigo_postal is
'Código postal que identifica a la zona donde reside el aspirante.';

comment on column ebja.ubicacion.calle_principal is
'Nombre de la calle principal del aspirante.';

comment on column ebja.ubicacion.calle_interseccion is
'Nombre de la calle de intersección o secundaria del aspirante.';

comment on column ebja.ubicacion.referencia is
'Descripción de la referencia.';

comment on column ebja.ubicacion.coordenada_x is
'Descripción de la coordenada  de latitud de la ubicación.';

comment on column ebja.ubicacion.coordenada_y is
'Descripción de la coordenada longitud  de la ubicación.';

comment on column ebja.ubicacion.id_usuario_creacion is
'Identificador del usuario de creación de la inscripción.';

comment on column ebja.ubicacion.fecha_creacion is
'Fecha de la creación del registro.';

comment on column ebja.ubicacion.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema.';

comment on column ebja.ubicacion.estado is
'Indica el estado de la ubicación. Acitvo =1, Inactivo=0';


/*==============================================================*/
/* Table: detalle_traslado                                      */
/*==============================================================*/
create table detalle_traslado (
   id_detalle_traslado  SERIAL               not null,
   id_matricula         INT4                 not null,
   id_matricula_curso_paralelo INT4                 null,
   id_matricula_programa_instituci INT4                 null,
   id_matricula_tipo_proceso_orige INT4                 null,
   suministro_luz_      VARCHAR(50)          null,
   dpa_parroquia_       VARCHAR(6)           null,
   id_usuario_creacion  INT4                 not null,
   fecha_creacion       DATE                 not null,
   ip_usuario           VARCHAR(30)          not null,
   estado               CHAR(1)              not null
);

comment on table detalle_traslado is
'Tabla que contiene el detalle del traslado de un estudiante entre instuticiones';

alter table detalle_traslado
   add constraint PK_DETALLE_TRASLADO primary key (id_detalle_traslado);

drop table detalle_traslado;

alter table detalle_traslado
   add constraint fk_detalle_traslado_matricula foreign key (id_matricula)
      references matricula (id_matricula)
      on delete restrict on update restrict;

alter table ebja.catalogo_ebja
   add constraint fk_catalogo_ebja_tipo_cat_ebja foreign key (id_tipo_catalogo_ebja)
      references ebja.tipo_catalogo_ebja (id)
      on delete restrict on update restrict;

alter table ebja.curso_paralelo
   add constraint fk_curso_paral_progr_insti foreign key (id_programa_institucion)
      references ebja.programa_institucion (id)
      on delete restrict on update restrict;

alter table ebja.dato_familiar
   add constraint fk_dato_fam_registro_est foreign key (id_registro_estudiante)
      references ebja.registro_estudiante (id)
      on delete restrict on update restrict;

alter table ebja.detalle_regla_negocio
   add constraint fk_detalle_regla_neg_regla_neg foreign key (id_regla_negocio)
      references ebja.regla_negocio (id)
      on delete restrict on update restrict;

alter table ebja.discapacidad
   add constraint fk_discapacidad_registro_est foreign key (id_registro_estudiante)
      references ebja.registro_estudiante (id)
      on delete restrict on update restrict;

alter table ebja.docente_curso
   add constraint fk_docente_curso_curso_paralelo foreign key (id_curso_paralelo)
      references ebja.curso_paralelo (id)
      on delete restrict on update restrict;

alter table ebja.docente_curso
   add constraint fk_docen_curso_prog_institucion foreign key (id_programa_institucion)
      references ebja.programa_institucion (id)
      on delete restrict on update restrict;

alter table ebja.estudiante
   add constraint fk_estudiante_inscripcion foreign key (id_inscripcion)
      references ebja.inscripcion (id)
      on delete restrict on update restrict;

alter table ebja.estudiante
   add constraint fk_estudiante_registro_est foreign key (id_registro_estudiante)
      references ebja.registro_estudiante (id)
      on delete restrict on update restrict;

alter table ebja.fase
   add constraint fk_fase_rf_fase_f_fase foreign key (id_fase_padre)
      references ebja.fase (id)
      on delete restrict on update restrict;

alter table ebja.formulario
   add constraint fk_formulario_fase foreign key (id_fase)
      references ebja.fase (id)
      on delete restrict on update restrict;

alter table ebja.inscripcion
   add constraint fk_programa_ebja_inscripcion foreign key (id_programa_ebja)
      references ebja.programa_ebja (id)
      on delete restrict on update restrict;

alter table ebja.malla
   add constraint fk_malla_asignatura foreign key (id_asignatura)
      references ebja.asignatura (id)
      on delete restrict on update restrict;

alter table ebja.malla
   add constraint fk_malla_programa_ebja foreign key (id_programa_ebja)
      references ebja.programa_ebja (id)
      on delete restrict on update restrict;

alter table ebja.malla_docente
   add constraint fk_malla_docente_docente_curso foreign key (id_docente_curso)
      references ebja.docente_curso (id)
      on delete restrict on update restrict;

alter table ebja.malla_docente
   add constraint fk_malla_docente_malla foreign key (id_malla)
      references ebja.malla (id)
      on delete restrict on update restrict;

alter table ebja.matricula
   add constraint pk_matricula_curso_paralelo foreign key (id_curso_paralelo)
      references ebja.curso_paralelo (id)
      on delete restrict on update restrict;

alter table ebja.matricula
   add constraint fk_matricula_estudiante foreign key (id_estudiante)
      references ebja.estudiante (id)
      on delete restrict on update restrict;

alter table ebja.matricula
   add constraint fk_matri_programa_institucion foreign key (id_programa_institucion)
      references ebja.programa_institucion (id)
      on delete restrict on update restrict;

alter table ebja.mensaje
   add constraint fk_mensaje_formulario foreign key (id_formulario)
      references ebja.formulario (id)
      on delete restrict on update restrict;

alter table ebja.mensaje
   add constraint fk_programa_ebja_mensaje foreign key (id_programa_ebja)
      references ebja.programa_ebja (id)
      on delete restrict on update restrict;

alter table ebja.modelo_asistencia
   add constraint fk_modelo_asis_programa_ebja foreign key (id_programa_ebja)
      references ebja.programa_ebja (id)
      on delete restrict on update restrict;

alter table ebja.modelo_evaluacion
   add constraint fk_modelo_eval_programa_ebja foreign key (id_programa_ebja)
      references ebja.programa_ebja (id)
      on delete restrict on update restrict;

alter table ebja.modelo_evaluacion_parcial
   add constraint fk_mod_eval_par_mod_eval_qui foreign key (id_modelo_evaluacion_quimestre)
      references ebja.modelo_evaluacion_quimestre (id)
      on delete restrict on update restrict;

alter table ebja.modelo_evaluacion_quimestre
   add constraint fk_modelo_eval_qui_modelo_eval foreign key (id_modelo_evaluacion)
      references ebja.modelo_evaluacion (id)
      on delete restrict on update restrict;

alter table ebja.programa_acuerdo
   add constraint fk_acuerdo_programa_acuerdo foreign key (id_acuerdo)
      references ebja.acuerdo (id)
      on delete restrict on update restrict;

alter table ebja.programa_acuerdo
   add constraint fk_programa_acu_programa_ebja foreign key (id_programa_ebja)
      references ebja.programa_ebja (id)
      on delete restrict on update restrict;

alter table ebja.programa_ebja
   add constraint fk_programa_ebja_modalidad foreign key (id_modalidad)
      references ebja.modalidad (id)
      on delete restrict on update restrict;

alter table ebja.programa_ebja
   add constraint fk_tipo_programa_programa_ebja foreign key (id_tipo_programa)
      references ebja.tipo_programa (id)
      on delete restrict on update restrict;

alter table ebja.programa_grado
   add constraint fk_programa_ebja_programa_grado foreign key (id_programa_ebja)
      references ebja.programa_ebja (id)
      on delete restrict on update restrict;

alter table ebja.programa_institucion
   add constraint fk_curso_programa_ebja foreign key (id_programa_ebja)
      references ebja.programa_ebja (id)
      on delete restrict on update restrict;

alter table ebja.registro_estudiante
   add constraint fk_pais_registro_estudiante foreign key (id_pais)
      references ebja.pais (id)
      on delete restrict on update restrict;

alter table ebja.registro_estudiante
   add constraint fk_registro_est_inscripcion foreign key (id_inscripcion)
      references ebja.inscripcion (id)
      on delete restrict on update restrict;

alter table ebja.regla_negocio
   add constraint fk_fase_regla_regocio foreign key (id_fase)
      references ebja.fase (id)
      on delete restrict on update restrict;

alter table ebja.regla_negocio
   add constraint fk_programa_ebja_regla_negocio foreign key (id_programa_ebja)
      references ebja.programa_ebja (id)
      on delete restrict on update restrict;

alter table ebja.representante
   add constraint fk_representante_registro_est foreign key (id_registro_estudiante)
      references ebja.registro_estudiante (id)
      on delete restrict on update restrict;

alter table ebja.ubicacion
   add constraint fk_inscripcion_ubicacion foreign key (id_inscripcion)
      references ebja.inscripcion (id)
      on delete restrict on update restrict;

alter table ebja.ubicacion
   add constraint fk_pais_ubicacion foreign key (id_pais)
      references ebja.pais (id)
      on delete restrict on update restrict;

