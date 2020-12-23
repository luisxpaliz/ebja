/*=============================================================================================*/
/*============== SCRIPT DE CREACION DE LAS TABLAS DE AUDITORIA DEL SISTEMA EBJA ===============*/
/*============== FECHA CREACION: 23/NOV/2018 ==================================================*/
/*=============================================================================================*/

/*==================================================================*/
/* -- CREACION DEL ESQUEMA PARA AUDITORIA -- */
/*==================================================================*/

CREATE SCHEMA ebja_audit
    AUTHORIZATION postgres;
/*=============== FIN TABLA CREACION ESQUEMA========================*/



/*==================================================================*/
/*============== INICIO TABLA ebja_audit.acuerdo_log ===============*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.acuerdo_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.acuerdo_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.acuerdo_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.acuerdo_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.acuerdo_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: acuerdo_audit -- */
/*==================================================================*/
CREATE TRIGGER acuerdo_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.acuerdo
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','acuerdo_log');
/*=============== FIN TABLA ebja_audit.acuerdo_log ================*/

/*==================================================================*/
/*============ INICIO TABLA ebja_audit.asignatura_log ==============*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.asignatura_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.asignatura_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.asignatura_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.asignatura_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.asignatura_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: asignatura_audit -- */
/*==================================================================*/
CREATE TRIGGER asignatura_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.asignatura
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','asignatura_log');
/*============= FIN TABLA ebja_audit.asignatura_log ================*/


/*==================================================================*/
/*========== INICIO TABLA ebja_audit.catalogo_ebja_log =============*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.catalogo_ebja_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.catalogo_ebja_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.catalogo_ebja_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.catalogo_ebja_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.catalogo_ebja_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: catalogo_ebja_audit -- */
/*==================================================================*/
CREATE TRIGGER catalogo_ebja_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.catalogo_ebja
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','catalogo_ebja_log');
/*============= FIN TABLA ebja_audit.catalogo_ebja_log =============*/

/*==================================================================*/
/*========== INICIO TABLA ebja_audit.curso_paralelo_log ============*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.curso_paralelo_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.curso_paralelo_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.curso_paralelo_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.curso_paralelo_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.curso_paralelo_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: curso_paralelo_audit -- */
/*==================================================================*/
CREATE TRIGGER curso_paralelo_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.curso_paralelo
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','curso_paralelo_log');
/*=========== FIN TABLA ebja_audit.curso_paralelo_log =============*/

/*==================================================================*/
/*========== INICIO TABLA ebja_audit.dato_familiar_log =============*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.dato_familiar_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.dato_familiar_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.dato_familiar_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.dato_familiar_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.dato_familiar_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: dato_familiar_audit -- */
/*==================================================================*/
CREATE TRIGGER dato_familiar_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.dato_familiar
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','dato_familiar_log');
/*=========== FIN TABLA ebja_audit.dato_familiar_log ==============*/

/*==================================================================*/
/*====== INICIO TABLA ebja_audit.detalle_regla_negocio_log =========*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.detalle_regla_negocio_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.detalle_regla_negocio_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.detalle_regla_negocio_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.detalle_regla_negocio_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.detalle_regla_negocio_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: detalle_regla_negocio_audit -- */
/*==================================================================*/
CREATE TRIGGER detalle_regla_negocio_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.detalle_regla_negocio
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','detalle_regla_negocio_log');
/*===== FIN TABLA ebja_audit.detalle_regla_negocio_log =============*/

/*==================================================================*/
/*========== INICIO TABLA ebja_audit.discapacidad_log ==============*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.discapacidad_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.discapacidad_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.discapacidad_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.discapacidad_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.discapacidad_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: discapacidad_audit -- */
/*==================================================================*/
CREATE TRIGGER discapacidad_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.discapacidad
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','discapacidad_log');
/*=========== FIN TABLA ebja_audit.discapacidad_log ===============*/

/*==================================================================*/
/*========== INICIO TABLA ebja_audit.docente_curso_log =============*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.docente_curso_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.docente_curso_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.docente_curso_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.docente_curso_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.docente_curso_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: docente_curso_audit -- */
/*==================================================================*/
CREATE TRIGGER docente_curso_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.docente_curso
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','docente_curso_log');
/*=========== FIN TABLA ebja_audit.docente_curso_log ==============*/

/*==================================================================*/
/*========== INICIO TABLA ebja_audit.estudiante_log ================*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.estudiante_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.estudiante_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.estudiante_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.estudiante_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.estudiante_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: estudiante_audit -- */
/*==================================================================*/
CREATE TRIGGER estudiante_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.estudiante
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','estudiante_log');
/*============= FIN TABLA ebja_audit.estudiante_log ================*/

/*==================================================================*/
/*========== INICIO TABLA ebja_audit.formulario_log ================*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.formulario_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.formulario_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.formulario_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.formulario_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.formulario_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: formulario_audit -- */
/*==================================================================*/
CREATE TRIGGER formulario_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.formulario
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','formulario_log');
/*============= FIN TABLA ebja_audit.formulario_log ================*/

/*==================================================================*/
/*========== INICIO TABLA ebja_audit.inscripcion_log ================*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.inscripcion_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.inscripcion_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.inscripcion_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.inscripcion_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.inscripcion_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: inscripcion_audit -- */
/*==================================================================*/
CREATE TRIGGER inscripcion_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.inscripcion
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','inscripcion_log');
/*============= FIN TABLA ebja_audit.inscripcion_log ===============*/

/*==================================================================*/
/*========== INICIO TABLA ebja_audit.malla_docente_log =============*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.malla_docente_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.malla_docente_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.malla_docente_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.malla_docente_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.malla_docente_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: malla_docente_audit -- */
/*==================================================================*/
CREATE TRIGGER malla_docente_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.malla_docente
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','malla_docente_log');
/*============= FIN TABLA ebja_audit.malla_docente_log ==============*/

/*==================================================================*/
/*=============== INICIO TABLA ebja_audit.malla_log ================*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.malla_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.malla_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.malla_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.malla_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.malla_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: malla_audit -- */
/*==================================================================*/
CREATE TRIGGER malla_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.malla
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','malla_log');
/*=============== FIN TABLA ebja_audit.malla_log ==================*/


/*==================================================================*/
/*============ INICIO TABLA ebja_audit.matricula_log ===============*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.matricula_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.matricula_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.matricula_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.matricula_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.matricula_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: matricula_audit -- */
/*==================================================================*/
CREATE TRIGGER matricula_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.matricula
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','matricula_log');
/*============= FIN TABLA ebja_audit.matricula_log ==============*/

/*==================================================================*/
/*============= INICIO TABLA ebja_audit.mensaje_log ================*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.mensaje_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.mensaje_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.mensaje_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.mensaje_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.mensaje_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: mensaje_audit -- */
/*==================================================================*/
CREATE TRIGGER mensaje_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.mensaje
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','mensaje_log');
/*============== FIN TABLA ebja_audit.mensaje_log ===============*/

/*==================================================================*/
/*======== INICIO TABLA ebja_audit.modelo_asistencia_log ===========*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.modelo_asistencia_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.modelo_asistencia_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.modelo_asistencia_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.modelo_asistencia_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.modelo_asistencia_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: modelo_asistencia_audit -- */
/*==================================================================*/
CREATE TRIGGER modelo_asistencia_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.modelo_asistencia
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','modelo_asistencia_log');
/*======= FIN TABLA ebja_audit.modelo_asistencia_log =============*/

/*==================================================================*/
/*======== INICIO TABLA ebja_audit.modelo_evaluacion_log ===========*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.modelo_evaluacion_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.modelo_evaluacion_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.modelo_evaluacion_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.modelo_evaluacion_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.modelo_evaluacion_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: modelo_evaluacion_audit -- */
/*==================================================================*/
CREATE TRIGGER modelo_evaluacion_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.modelo_evaluacion
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','modelo_evaluacion_log');
/*======= FIN TABLA ebja_audit.modelo_evaluacion_log =============*/

/*==================================================================*/
/*==== INICIO TABLA ebja_audit.modelo_evaluacion_parcial_log =======*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.modelo_evaluacion_parcial_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.modelo_evaluacion_parcial_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.modelo_evaluacion_parcial_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.modelo_evaluacion_parcial_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.modelo_evaluacion_parcial_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: modelo_evaluacion_parcial_audit -- */
/*==================================================================*/
CREATE TRIGGER modelo_evaluacion_parcial_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.modelo_evaluacion_parcial
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','modelo_evaluacion_parcial_log');
/*==== FIN TABLA ebja_audit.modelo_evaluacion_parcial_log ==========*/

/*==================================================================*/
/*=== INICIO TABLA ebja_audit.modelo_evaluacion_quimestre_log ======*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.modelo_evaluacion_quimestre_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.modelo_evaluacion_quimestre_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.modelo_evaluacion_quimestre_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.modelo_evaluacion_quimestre_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.modelo_evaluacion_quimestre_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: modelo_evaluacion_quimestre_audit -- */
/*==================================================================*/
CREATE TRIGGER modelo_evaluacion_quimestre_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.modelo_evaluacion_quimestre
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','modelo_evaluacion_quimestre_log');
/*==== FIN TABLA ebja_audit.modelo_evaluacion_quimestre_log ========*/

/*==================================================================*/
/*========== INICIO TABLA ebja_audit.programa_acuerdo_log ==========*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.programa_acuerdo_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.programa_acuerdo_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.programa_acuerdo_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.programa_acuerdo_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.programa_acuerdo_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: programa_acuerdo_audit -- */
/*==================================================================*/
CREATE TRIGGER programa_acuerdo_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.programa_acuerdo
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','programa_acuerdo_log');
/*=========== FIN TABLA ebja_audit.programa_acuerdo_log ===========*/


/*==================================================================*/
/*============ INICIO TABLA ebja_audit.programa_ebja_log ===========*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.programa_ebja_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.programa_ebja_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.programa_ebja_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.programa_ebja_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.programa_ebja_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: programa_ebja_audit -- */
/*==================================================================*/
CREATE TRIGGER programa_ebja_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.programa_ebja
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','programa_ebja_log');
/*============= FIN TABLA ebja_audit.programa_ebja_log ============*/

/*==================================================================*/
/*========== INICIO TABLA ebja_audit.programa_grado_log ============*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.programa_grado_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.programa_grado_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.programa_grado_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.programa_grado_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.programa_grado_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: programa_grado_audit -- */
/*==================================================================*/
CREATE TRIGGER programa_grado_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.programa_grado
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','programa_grado_log');
/*=========== FIN TABLA ebja_audit.programa_grado_log =============*/

/*==================================================================*/
/*====== INICIO TABLA ebja_audit.programa_institucion_log ==========*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.programa_institucion_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.programa_institucion_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.programa_institucion_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.programa_institucion_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.programa_institucion_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: programa_institucion_audit -- */
/*==================================================================*/
CREATE TRIGGER programa_institucion_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.programa_institucion
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','programa_institucion_log');
/*========= FIN TABLA ebja_audit.programa_institucion_log ==========*/

/*==================================================================*/
/*======= INICIO TABLA ebja_audit.registro_estudiante_log ==========*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.registro_estudiante_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.registro_estudiante_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.registro_estudiante_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.registro_estudiante_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.registro_estudiante_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: registro_estudiante_audit -- */
/*==================================================================*/
CREATE TRIGGER registro_estudiante_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.registro_estudiante
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','registro_estudiante_log');
/*========== FIN TABLA ebja_audit.registro_estudiante_log ==========*/

/*==================================================================*/
/*========== INICIO TABLA ebja_audit.regla_negocio_log =============*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.regla_negocio_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.regla_negocio_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.regla_negocio_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.regla_negocio_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.regla_negocio_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: regla_negocio_audit -- */
/*==================================================================*/
CREATE TRIGGER regla_negocio_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.regla_negocio
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','regla_negocio_log');
/*============ FIN TABLA ebja_audit.regla_negocio_log =============*/

/*==================================================================*/
/*========== INICIO TABLA ebja_audit.representante_log =============*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.representante_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.representante_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.representante_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.representante_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.representante_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: representante_audit -- */
/*==================================================================*/
CREATE TRIGGER representante_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.representante
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','representante_log');
/*============ FIN TABLA ebja_audit.representante_log =============*/


/*==================================================================*/
/*======= INICIO TABLA ebja_audit.tipo_catalogo_ebja_log ===========*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.tipo_catalogo_ebja_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.tipo_catalogo_ebja_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.tipo_catalogo_ebja_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.tipo_catalogo_ebja_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.tipo_catalogo_ebja_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: tipo_catalogo_ebja_audit -- */
/*==================================================================*/
CREATE TRIGGER tipo_catalogo_ebja_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.tipo_catalogo_ebja
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','tipo_catalogo_ebja_log');
/*========= FIN TABLA ebja_audit.tipo_catalogo_ebja_log ===========*/

/*==================================================================*/
/*============ INICIO TABLA ebja_audit.ubicacion_log ===============*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.ubicacion_log -- */
/*==================================================================*/
CREATE TABLE ebja_audit.ubicacion_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.ubicacion_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.ubicacion_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.ubicacion_log TO rol_lectura;
/*==================================================================*/
/* -- TRIGGER: ubicacion_audit -- */
/*==================================================================*/
CREATE TRIGGER ubicacion_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.ubicacion
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','ubicacion_log');
/*============ FIN TABLA ebja_audit.ubicacion_log =================*/

/*==================================================================*/
/*============ INICIO TABLA ebja_audit.detalle_traslado_log ===============*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.detalle_traslado_log -- */
/*==================================================================*/

CREATE TABLE ebja_audit.detalle_traslado_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.detalle_traslado_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.detalle_traslado_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.detalle_traslado_log TO rol_lectura;

/*==================================================================*/
/* -- TRIGGER: detalle_traslado -- */
/*==================================================================*/

CREATE TRIGGER detalle_traslado_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.detalle_traslado
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','detalle_traslado_log');
 
 /*============ FIN TABLA ebja_audit.detalle_traslado =================*/