/*=============================================================================================*/
/*============== SCRIPT DE ACTUALIZACION DE LAS TABLAS DEL SISTEMA EBJA ============================*/
/*============== FECHA EDICIÓN: 07/06/2019 ==================================================*/
/*============== NUMERO DE TABLAS: 5 =========================================================*/
/*=============================================================================================*/


/*Se van a borrar las siguientes tablas*/
/*  1.- ebja.modelo_evaluacion   */
/*  2.- ebja.modelo_evaluacion_parcial   */
/*  3.- ebja.modelo_evaluacion_quimestre   */

DROP TABLE IF EXISTS ebja.modelo_evaluacion_parcial;
DROP TABLE IF EXISTS ebja.modelo_evaluacion_quimestre;
DROP TABLE IF EXISTS ebja.modelo_evaluacion;
DROP TABLE IF EXISTS ebja.plantilla_nota;
DROP TABLE IF EXISTS ebja.tipo_nota;
DROP TABLE IF EXISTS ebja.notas;
DROP TABLE IF EXISTS ebja.programa_educativo;
DROP TABLE IF EXISTS ebja.plantilla_estudiante;
DROP TABLE IF EXISTS ebja.grupo_fase_programa;

/*==============================================================*/
/* Table: modelo_evaluacion                                     */
/*==============================================================*/
/*==============================================================*/
/* Table: modelo_evaluacion                                     */
/*==============================================================*/

create table ebja.modelo_evaluacion (
   id_modelo_evaluacion serial not null,
   porcentaje_examen    int2                 null,
   numero_decimal_calificacion int2                 not null,
   fecha_inicio         timestamp            null,
   fecha_fin            timestamp            null,
   id_usuario_creacion  int4                 null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_modelo_evaluacion primary key (id_modelo_evaluacion)
);

ALTER TABLE ebja.modelo_evaluacion 
    OWNER to carmenta;

GRANT SELECT ON TABLE ebja.modelo_evaluacion TO backup;

GRANT ALL ON TABLE ebja.modelo_evaluacion TO carmenta;

GRANT SELECT ON TABLE ebja.modelo_evaluacion TO rol_lectura;

comment on table ebja.modelo_evaluacion is
'Tabla que contiene la información del Modelo de Evaluación de un Programa.';

comment on column ebja.modelo_evaluacion.id_modelo_evaluacion is
'Clave Primaria del Modelo_Evaluación.';

comment on column ebja.modelo_evaluacion.porcentaje_examen is
'Definición del porcentaje que representa el examen.';

comment on column ebja.modelo_evaluacion.numero_decimal_calificacion is
'Definición del número de decimales que se ocupan para los cálculos de una calificación.';

comment on column ebja.modelo_evaluacion.fecha_inicio is
'Fecha de Inicio de nota.';

comment on column ebja.modelo_evaluacion.fecha_fin is
'Fecha de Finalización nota.';

comment on column ebja.modelo_evaluacion.id_usuario_creacion is
'Codigo del usuario que registra la transacción.';

comment on column ebja.modelo_evaluacion.fecha_creacion is
'Fecha de creación del registro.';

comment on column ebja.modelo_evaluacion.ip_usuario is
'Dirección ip del  usuario que ingreso al sistema';

comment on column ebja.modelo_evaluacion.estado is
'Estado del registro (0=inactivo, 1=activo).';


/*==============================================================*/
/* Table: plantilla_nota                                        */
/*==============================================================*/

create table ebja.plantilla_nota (
   id_plantilla_nota    serial               not null,
   id_tipo_nota         int4                 not null,
   nemonico_plantilla   varchar(20)          not null,
   nombre_nota          varchar(50)          not null,
   nombre_plantilla     varchar(50)          not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           varchar(30)          not null,
   id_malla             int4                 not null,
   estado               char(1)              not null,
   constraint pk_plantilla_nota primary key (id_plantilla_nota)
);

ALTER TABLE ebja.plantilla_nota 
    OWNER to carmenta;
	
GRANT SELECT ON TABLE ebja.plantilla_nota TO backup;

GRANT ALL ON TABLE ebja.plantilla_nota TO carmenta;

GRANT SELECT ON TABLE ebja.plantilla_nota TO rol_lectura;
	

/*==============================================================*/
/* Table: tipo_nota                                             */
/*==============================================================*/

create table ebja.tipo_nota (
   id_tipo_nota         serial               not null,
   id_tipo_nota_padre   int4                 null,
   id_grado             int4                 not null,
   nombre               varchar(100)         not null,
   nemonico             varchar(20)          not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_tipo_nota primary key (id_tipo_nota)
);

ALTER TABLE ebja.tipo_nota
    OWNER to carmenta;

GRANT SELECT ON TABLE ebja.tipo_nota TO backup;

GRANT ALL ON TABLE ebja.tipo_nota TO carmenta;

GRANT SELECT ON TABLE ebja.tipo_nota TO rol_lectura;


/*==============================================================*/
/* Table: notas                                                 */
/*==============================================================*/

create table ebja.notas (
   id_notas             int4                 not null,
   id_plantilla_nota    int4                 not null,
   nemonico             character varying(30) not null,
   id_modelo_evaluacion int4                 not null,
   id_plantilla_estudiante int4                 null,
   nota                 numeric(4,2)         not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_notas primary key (id_notas)
);

ALTER TABLE ebja.notas 
    OWNER to carmenta;

GRANT SELECT ON TABLE ebja.notas TO backup;

GRANT ALL ON TABLE ebja.notas TO carmenta;

GRANT SELECT ON TABLE ebja.notas TO rol_lectura;


/*==============================================================*/
/* Table: programa_educativo                                    */
/*==============================================================*/

create table ebja.programa_educativo (
   id_programa_educativo serial               not null,
   nemonico             varchar(20)          not null,
   nombre_programa      varchar(50)         not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_programa_educativo primary key (id_programa_educativo)
);

ALTER TABLE ebja.programa_educativo 
    OWNER to carmenta;

GRANT SELECT ON TABLE ebja.programa_educativo TO backup;

GRANT ALL ON TABLE ebja.programa_educativo TO carmenta;

GRANT SELECT ON TABLE ebja.programa_educativo TO rol_lectura;



/*==============================================================*/
/* Table: plantilla_estudiante                                  */
/*==============================================================*/

create table ebja.plantilla_estudiante (
   id_plantilla_estudiante serial            not null,
   id                   int4                 not null,
   id_malla_docente     int4                 not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   constraint pk_plantilla_estudiante primary key (id_plantilla_estudiante)
);

ALTER TABLE ebja.plantilla_estudiante 
    OWNER to carmenta;

GRANT SELECT ON TABLE ebja.plantilla_estudiante TO backup;

GRANT ALL ON TABLE ebja.plantilla_estudiante TO carmenta;

GRANT SELECT ON TABLE ebja.plantilla_estudiante TO rol_lectura;

  
alter table ebja.notas
   add constraint fk_notas_reference_plantill_estu foreign key (id_plantilla_estudiante)
      references ebja.plantilla_estudiante (id_plantilla_estudiante)
      on delete restrict on update restrict;	  

alter table ebja.notas
   add constraint fk_notas_reference_modelo_e foreign key (id_modelo_evaluacion)
      references ebja.modelo_evaluacion (id_modelo_evaluacion)
      on delete restrict on update restrict;
	  
alter table ebja.notas
   add constraint fk_notas_reference_plantill foreign key (id_plantilla_nota)
      references ebja.plantilla_nota (id_plantilla_nota)
      on delete restrict on update restrict;

alter table ebja.plantilla_nota
   add constraint fk_plantill_reference_tipo_not foreign key (id_tipo_nota)
      references ebja.tipo_nota (id_tipo_nota)
      on delete restrict on update restrict;
	  
alter table ebja.plantilla_nota
   add constraint fk_plantill_reference_malla foreign key (id_malla)
      references ebja.malla (id)
      on delete restrict on update restrict;


alter table ebja.tipo_nota
   add constraint fk_tipo_not_reference_tipo_not foreign key (id_tipo_nota_padre)
      references ebja.tipo_nota (id_tipo_nota)
      on delete restrict on update restrict;

alter table ebja.plantilla_estudiante
   add constraint fk_plantill_reference_malla_do foreign key (id_malla_docente)
      references ebja.malla_docente (id)
      on delete restrict on update restrict;

alter table ebja.plantilla_estudiante
   add constraint fk_plantill_reference_matricul foreign key (id)
      references ebja.matricula (id)
      on delete restrict on update restrict;



/*agregar catalogo de grados unificados*/

/* script para admintiracion de ofertas*/

insert into ebja.programa_educativo values (1,'EBJASEMIPRESENCIAL','EBJA',616999,now(),'200.107.59.150','1');
insert into ebja.programa_educativo values (2,'CPL','CPL',616999,now(),'200.107.59.150','1');
insert into ebja.programa_educativo values (3,'PCEI','PCEI',616999,now(),'200.107.59.150','1');
--insert into ebja.programa_educativo values (4,'VIRTUAL','VIRTUAL',1,now(),'1','1');
	  
/*añadir la relacion programa_educativo con oferta */

ALTER TABLE ebja.programa_ebja ADD column id_programa_educativo int4;

/*añadir todos los programas antes creado al programa EBJA*/

update ebja.programa_ebja set id_programa_educativo = 1;
	  
/*Añadir restriccion de not null para programa educativo en oferta */

ALTER TABLE ebja.programa_ebja ALTER column id_programa_educativo SET NOT NULL;

/*añadir la relacion entre programa educativo a oferta*/

alter table ebja.programa_ebja
   add constraint fk_programa_reference_programa foreign key (id_programa_educativo)
      references ebja.programa_educativo (id_programa_educativo)
      on delete restrict on update restrict;

/*Añadir Tipo programa Ninguno*/

insert into ebja.tipo_programa values (5,'ME-TP-05','NINGUNO',1);
	  
/*==================================================================*/
/* -- PIVOT NOTAS -- */
/*==================================================================*/
CREATE OR REPLACE FUNCTION ebja.dynamic_pivot(central_query text, headers_query text, nameCursor refcursor)
 RETURNS int AS
$$
DECLARE
  left_column text;
  header_column text;
  value_column text;
  h_value text;
  headers_clause text;
  query text;
  j json;
  r record;
  i int:=1;
BEGIN
  -- find the column names of the source query
  EXECUTE 'select row_to_json(_r.*) from (' ||  central_query || ') AS _r' into j;
  FOR r in SELECT * FROM json_each_text(j)
  LOOP
    IF (i=1) THEN left_column := r.key;
      ELSEIF (i=2) THEN header_column := r.key;
      ELSEIF (i=3) THEN value_column := r.key;
    END IF;
    i := i+1;
  END LOOP;

  --  build the dynamic transposition query (based on the canonical model)
  FOR h_value in EXECUTE headers_query
  LOOP
    headers_clause := concat(headers_clause,
     format(chr(10)||',min(case when %I=%L then %I::text end) as %I',
           header_column,
	   h_value,
	   value_column,
	   h_value ));
  END LOOP;

  query := format('SELECT %I %s FROM (select *,row_number() over() as rn from (%s) AS _c) as _d GROUP BY %I order by min(rn)',
       left_column,
	   headers_clause,
	   central_query,
	   left_column);

  -- open the cursor so the caller can FETCH right away
  OPEN nameCursor FOR execute query;
  RETURN NULL;
END 
$$ LANGUAGE plpgsql;
  
/*==================================================================*/
/*==================================================================*/  
/*--------------------ACTUALIZACION ESQUEM MALLA APUNTANDO A PROGRAMA GRADO-----*/

--ALTER TABLE ebja.plantilla_nota drop constraint fk_malla_programa_ebja;
ALTER TABLE ebja.malla drop column id_programa_ebja;
ALTER TABLE ebja.malla drop column id_grado;
ALTER TABLE ebja.malla add COLUMN id_programa_grado INT4 NOT NULL;
alter table ebja.malla
   add constraint fk_malla_reference_programa foreign key (id_programa_grado)
      references ebja.programa_grado (id)
      on delete restrict on update restrict;
	  
	  
	  
/*==================================================================*/
/*==================================================================*/  
/*--------------------ACTUALIZACION DE PROGRAMA-GRADO---------------*/	  


/*añadir los campos id_programa_grado en la tabla inscripcion y tabla programa_institucion*/

ALTER TABLE ebja.inscripcion drop constraint fk_programa_ebja_inscripcion;
ALTER TABLE ebja.programa_institucion drop constraint fk_curso_programa_ebja;

ALTER TABLE ebja.inscripcion RENAME COLUMN id_programa_ebja TO id_programa_grado;
ALTER TABLE ebja.programa_institucion RENAME COLUMN id_programa_ebja TO id_programa_grado;

alter table ebja.inscripcion
   add constraint fk_inscripc_reference_programa foreign key (id_programa_grado)
      references ebja.programa_grado (id)
      on delete restrict on update restrict;
	  
alter table ebja.programa_institucion
   add constraint fk_programa_reference_programa foreign key (id_programa_grado)
      references ebja.programa_grado (id)
      on delete restrict on update restrict;
	  
	  
/* ************* Actulizacion grupo_fase_programa *****************************************/

/*==============================================================*/
/* Table: grupo_fase_programa                                   */
/*==============================================================*/

create table ebja.grupo_fase_programa (
   id_grupo_fase_programa serial not null,
   id_programa_educativo int4                 null,
   nombre               character varying(100) not null,
   nemonico             character varying(30) not null,
   id_usuario_creacion  int4                 not null,
   fecha_creacion       timestamp            not null,
   ip_usuario           character varying(30) not null,
   estado               character(1)         not null,
   fecha_inicio         timestamp            not null,
   fecha_fin            timestamp            not null,
   constraint pk_grupo_fase_programa primary key (id_grupo_fase_programa),
   constraint uk_grupo_fase_programa_nemonico unique (nemonico)
);

/* **eliminar relaciones**/
ALTER TABLE ebja.programa_ebja drop constraint fk_programa_reference_programa;
	  
alter table ebja.grupo_fase_programa
   add constraint fk_grupo_fa_reference_programa foreign key (id_programa_educativo)
      references ebja.programa_educativo (id_programa_educativo)
      on delete restrict on update restrict;
	  
/* añadir columna referencia en programa_ebja */


ALTER TABLE ebja.programa_ebja DROP COLUMN id_programa_educativo;
ALTER TABLE ebja.programa_ebja ADD column id_grupo_fase_programa INT4 NULL;

ALTER TABLE ebja.programa_ebja ADD column secuencia_programa INT4 NULL;
ALTER TABLE ebja.programa_ebja ADD column es_pack INT4 NULL;
ALTER TABLE ebja.programa_ebja ADD column secuencia_inscripcion INT4 NULL;


alter table ebja.programa_ebja
   add constraint fk_programa_reference_grupo_fa foreign key (id_grupo_fase_programa)
      references ebja.grupo_fase_programa (id_grupo_fase_programa)
      on delete restrict on update restrict;

ALTER TABLE ebja.programa_grado drop constraint uk_programa_grado;
ALTER TABLE ebja.programa_grado ADD COLUMN secuencia_grado INT4 NULL;
ALTER TABLE ebja.programa_grado ADD COLUMN id_pack INT4 NULL;
ALTER TABLE ebja.programa_grado ADD COLUMN grado_inicial INT4 NULL;


/* se agrega campos a tabla inscripcion*/

ALTER TABLE ebja.inscripcion ADD column par_observacion varchar(300) NULL;
ALTER TABLE ebja.inscripcion ADD column ins_observacion varchar(200) NULL;
ALTER TABLE ebja.inscripcion ADD column cod_inscripcion INT4 NULL;

ALTER TABLE ebja.registro_estudiante ADD column ins_observacion varchar(200) NULL;
ALTER TABLE ebja.registro_estudiante ADD column cod_inscripcion_r INT4 NULL;


/*agrega campo para saber si es fase antigua*/

ALTER TABLE ebja.grupo_fase_programa ADD column fase_externa INT2 NULL;


/*agrega una columna 'visible' para que no se muestre la informacion en el combo pero este en estado activo */
ALTER TABLE ebja.programa_ebja ADD column visible INT2 NULL;
update ebja.programa_ebja set visible = 1;

/*agrega una columna 'visible' para que no se muestre la informacion en el combo pero este en estado activo */
ALTER TABLE ebja.programa_grado ADD column visible INT2 NULL;
update ebja.programa_grado set visible = 1;

/*añado la secuencia inscripcion para los datos anteriores*/

update ebja.programa_ebja set secuencia_inscripcion = 0;


/*agregar catalogos de grados*/

/*persona en entra por primera vez a un sistema educativo ebja*/

insert into ebja.grupo_fase_programa values (1,1,'NINGUNO','EBJANINGUNO',616999,now(),'200.107.59.150','1','1900/05/01 00:00:00.00','2040/09/30 00:00:00.00',1);
insert into ebja.grupo_fase_programa values (2,1,'ORDINARIA','EBJAORDI',616999,now(),'200.107.59.150','1','1900/09/01 00:00:00.00','2100/11/01 00:00:00.00',1); 
insert into ebja.grupo_fase_programa values (3,1,'EXTRAORDINARIA','EBJAEXTRAORDI',616999,now(),'200.107.59.150','1','1900/09/01 00:00:00.00','2100/11/01 00:00:00.00',1); 

/*persona en entra por primera vez a un sistema educativo cpl*/

insert into ebja.grupo_fase_programa values (4,2,'NINGUNO','CPLNINGUNO',616999,now(),'200.107.59.150','1','1900/05/01 00:00:00.00','2040/09/30 00:00:00.00',1);
insert into ebja.grupo_fase_programa values (5,2,'ORDINARIA','CPLORDI',616999,now(),'200.107.59.150','1','1900/09/01 00:00:00.00','2100/11/01 00:00:00.00',1); 
insert into ebja.grupo_fase_programa values (6,2,'EXTRAORDINARIA','CPLEXTRAORDI',616999,now(),'200.107.59.150','1','1900/09/01 00:00:00.00','2100/11/01 00:00:00.00',1); 

/*persona en entra por primera vez a un sistema educativo pcei*/

insert into ebja.grupo_fase_programa values (7,3,'NINGUNO','PCEININGUNO',616999,now(),'200.107.59.150','1','1900/05/01 00:00:00.00','2040/09/30 00:00:00.00',1);
insert into ebja.grupo_fase_programa values (8,3,'ORDINARIA','PCEIORDI',616999,now(),'200.107.59.150','1','1900/09/01 00:00:00.00','2100/11/01 00:00:00.00',1); 
insert into ebja.grupo_fase_programa values (9,3,'EXTRAORDINARIA','PCEIEXTRAORDI',616999,now(),'200.107.59.150','1','1900/09/01 00:00:00.00','2100/11/01 00:00:00.00',1); 

/*persona en entra por primera vez a un sistema educativo VIRTUAL*/

/*insert into ebja.grupo_fase_programa values (10,4,'NINGUNO','VIRTUALNINGUNO',1,now(),'1','1','1900/05/01 00:00:00.00','2040/09/30 00:00:00.00',1);
insert into ebja.grupo_fase_programa values (11,4,'ORDINARIA','VIRTUALORDI',1,now(),'1','1','1900/09/01 00:00:00.00','2100/11/01 00:00:00.00',1); 
insert into ebja.grupo_fase_programa values (12,4,'EXTRAORDINARIA','VIRTUALEXTRAORDI',1,now(),'1','1','1900/09/01 00:00:00.00','2100/11/01 00:00:00.00',1); 
*/

/*Registro para validar datos anteriores EBJA*/
insert into ebja.grupo_fase_programa values (13,1,'INTENSIVO COSTA 2019','EBJACOSTA2019',616999,now(),'200.107.59.150','1','2019/04/28 00:00:00.00','2020/10/01 00:00:00.00',0);

/* nuevos datos ebja y demás proyectos*/
insert into ebja.grupo_fase_programa values (14,1,'INTENSIVO SIERRA 2019','EBJASIERRA2019',616999,now(),'200.107.59.150','1','2019/08/31 00:00:00.00','2020/08/02 00:00:00.00',0); 

insert into ebja.grupo_fase_programa values (15,2,'SIERRA 2019','CPLSIERRA2019',616999,now(),'200.107.59.150','1','2019/08/31 00:00:00.00','2020/07/02 00:00:00.00',0);
insert into ebja.grupo_fase_programa values (16,3,'INTENSIVO PCEI COSTA 2019','PCEIINTCOSTA2019',616999,now(),'200.107.59.150','1','2019/08/31 00:00:00.00','2020/02/02 00:00:00.00',0);
insert into ebja.grupo_fase_programa values (17,3,'INTENSIVO PCEI SIERRA 2019','PCEIINTSIERRA2019',616999,now(),'200.107.59.150','1','2019/08/31 00:00:00.00','2020/08/02 00:00:00.00',0);
insert into ebja.grupo_fase_programa values (18,3,'SIERRA 2019','PCEISIERRA2019',616999,now(),'200.107.59.150','1','2019/08/31 00:00:00.00','2020/07/02 00:00:00.00',0);
--insert into ebja.grupo_fase_programa values (19,4,'CONVOCATORIA 4','VIRTUALCONV4',1,now(),'1','1','2019/08/31 00:00:00.00','2020/02/02 00:00:00.00',0);

/*Datos anteriores para programa Ebja*/
update ebja.programa_ebja set id_grupo_fase_programa = 13;
update ebja.programa_ebja set es_pack = 1;
update ebja.programa_ebja set es_pack = 0 where nemonico = '006';
update ebja.programa_ebja set secuencia_programa = 0;
update ebja.programa_ebja set secuencia_inscripcion = 5 where id = 4;
update ebja.programa_ebja set secuencia_inscripcion = 0 where id = 5;
update ebja.programa_ebja set secuencia_inscripcion = 6 where id = 3;
update ebja.programa_ebja set secuencia_inscripcion = 0 where id = 6;


/*Datos anteriores para programa_grado*/
update ebja.programa_grado set id_pack = 0;
update ebja.programa_grado set grado_inicial = 1;
update ebja.programa_grado set secuencia_grado = 0;

/* EBJA Fase 4 Alfabetizacion abc4 grados pack*/
update ebja.programa_grado set secuencia_grado = 0 where id_programa_ebja = 4 and id_grado = 4;
update ebja.programa_grado set secuencia_grado = 6 where id_programa_ebja = 4 and id_grado = 5;
update ebja.programa_grado set secuencia_grado = 0 where id_programa_ebja = 4 and id_grado = 6;
update ebja.programa_grado set grado_inicial = 0 where id_programa_ebja = 4 and id_grado = 4;
update ebja.programa_grado set grado_inicial = 1 where id_programa_ebja = 4 and id_grado = 5;
update ebja.programa_grado set grado_inicial = 3 where id_programa_ebja = 4 and id_grado = 6;
update ebja.programa_grado set id_pack = 1 where id_programa_ebja = 4;

update ebja.programa_grado set secuencia_grado = 0 where id_programa_ebja = 5 and id_grado = 6;
update ebja.programa_grado set secuencia_grado = 8 where id_programa_ebja = 5 and id_grado = 7;
update ebja.programa_grado set secuencia_grado = 0 where id_programa_ebja = 5 and id_grado = 8;
update ebja.programa_grado set grado_inicial = 0 where id_programa_ebja = 5 and id_grado = 6; 
update ebja.programa_grado set grado_inicial = 1 where id_programa_ebja = 5 and id_grado = 7;
update ebja.programa_grado set grado_inicial = 3 where id_programa_ebja = 5 and id_grado = 8;
update ebja.programa_grado set id_pack = 2 where id_programa_ebja = 5;

update ebja.programa_grado set secuencia_grado = 0 where id_programa_ebja = 3 and id_grado = 10;
update ebja.programa_grado set secuencia_grado = 12 where id_programa_ebja = 3 and id_grado = 11;
update ebja.programa_grado set secuencia_grado = 13 where id_programa_ebja = 3 and id_grado = 12;
update ebja.programa_grado set secuencia_grado = 0 where id_programa_ebja = 3 and id_grado = 13;
update ebja.programa_grado set grado_inicial = 0 where id_programa_ebja = 3 and id_grado = 10;
update ebja.programa_grado set grado_inicial = 1 where id_programa_ebja = 3 and id_grado = 11;
update ebja.programa_grado set grado_inicial = 2 where id_programa_ebja = 3 and id_grado = 12;
update ebja.programa_grado set grado_inicial = 3 where id_programa_ebja = 3 and id_grado = 13;
update ebja.programa_grado set id_pack = 3 where id_programa_ebja = 3;

update ebja.programa_grado set secuencia_grado = 0 where id_programa_ebja = 6 and id_grado = 13;
update ebja.programa_grado set secuencia_grado = 15 where id_programa_ebja = 6 and id_grado = 14;
update ebja.programa_grado set secuencia_grado = 16 where id_programa_ebja = 6 and id_grado = 15;
update ebja.programa_grado set secuencia_grado = 0 where id_programa_ebja = 6 and id_grado = 16;
update ebja.programa_grado set grado_inicial = 0 where id_programa_ebja = 6 and id_grado = 13;
update ebja.programa_grado set grado_inicial = 1 where id_programa_ebja = 6 and id_grado = 14;
update ebja.programa_grado set grado_inicial = 2 where id_programa_ebja = 6 and id_grado = 15;
update ebja.programa_grado set grado_inicial = 3 where id_programa_ebja = 6 and id_grado = 16;
update ebja.programa_grado set id_pack = 4 where id_programa_ebja = 6;
update ebja.programa_grado set grado_inicial = 3 where id_programa_ebja = 6 and id_grado = 16;

/*activar para buscar en un gardo anterior a la oferta actual*/
insert into ebja.programa_ebja values (7,2,2,'POST ALFABETIZACION 6-7 EGB','007','2019/09/01 00:00:00.00','2019/09/02 00:00:00.00','2020/07/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,13,0,1,3,1);

update ebja.programa_grado set visible = 0,grado_inicial = 3,secuencia_grado = 11,id_programa_ebja = 7, estado='1' where id  = 15;


/*COSTA 2019 anterior*/
insert into ebja.programa_ebja values (8,5,2,'NINGUNO','NING001','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,13,0,0,4,1);
insert into ebja.programa_grado values (16,8,50,616999,now(),'200.107.59.150',1,0,5,3,1);


/* EBJA SIERRA 2019 nueva fase */ 
 
 /*Registros cuando la persona es nueva, no tiene ningun tipo de educacion*/
insert into ebja.programa_ebja values (9,5,2,'NINGUNO','NING002','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,1,0,0,0,1);
insert into ebja.programa_grado values (17,9,50,616999,now(),'200.107.59.150',1,0,6,3,1);

/*Ebja sierra 2019 ordinaria*/
insert into ebja.programa_ebja values (10,5,2,'NINGUNO','ORD001','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,2,0,0,0,1);
insert into ebja.programa_grado values (18,10,5,616999,now(),'200.107.59.150',1,0,7,1,1);
insert into ebja.programa_grado values (19,10,6,616999,now(),'200.107.59.150',1,0,7,2,1);
insert into ebja.programa_grado values (20,10,7,616999,now(),'200.107.59.150',1,0,7,2,1);
insert into ebja.programa_grado values (21,10,8,616999,now(),'200.107.59.150',1,0,7,2,1);
insert into ebja.programa_grado values (22,10,9,616999,now(),'200.107.59.150',1,0,7,2,1);
insert into ebja.programa_grado values (23,10,10,616999,now(),'200.107.59.150',1,0,7,2,1);
insert into ebja.programa_grado values (24,10,11,616999,now(),'200.107.59.150',1,0,7,2,1);
insert into ebja.programa_grado values (25,10,12,616999,now(),'200.107.59.150',1,0,7,2,1);
insert into ebja.programa_grado values (26,10,13,616999,now(),'200.107.59.150',1,0,7,2,1);
insert into ebja.programa_grado values (27,10,14,616999,now(),'200.107.59.150',1,0,7,2,1);
insert into ebja.programa_grado values (28,10,15,616999,now(),'200.107.59.150',1,0,7,3,1);


/*Ebja 2019 extraordinaria*/
insert into ebja.programa_ebja values (11,1,2,'ALFABETIZACION','EXT001','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,3,0,0,0,1);
insert into ebja.programa_grado values (29,11,5,616999,now(),'200.107.59.150',1,0,8,1,1);
insert into ebja.programa_grado values (30,11,6,616999,now(),'200.107.59.150',1,0,8,3,1);
insert into ebja.programa_ebja values (12,2,2,'POST ALFABETIZACION','EXT002','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,3,0,0,0,1);
insert into ebja.programa_grado values (31,12,7,616999,now(),'200.107.59.150',1,0,9,1,1);
insert into ebja.programa_grado values (32,12,8,616999,now(),'200.107.59.150',1,0,9,2,1);
insert into ebja.programa_grado values (33,12,9,616999,now(),'200.107.59.150',1,0,9,2,1);
insert into ebja.programa_grado values (34,12,10,616999,now(),'200.107.59.150',1,0,9,3,1);
insert into ebja.programa_ebja values (13,3,2,'BASICA SUPERIOR','EXT003','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,3,0,0,0,1);
insert into ebja.programa_grado values (35,13,11,616999,now(),'200.107.59.150',1,0,10,1,1);
insert into ebja.programa_grado values (36,13,12,616999,now(),'200.107.59.150',1,0,10,2,1);
insert into ebja.programa_grado values (37,13,13,616999,now(),'200.107.59.150',1,0,10,3,1);
insert into ebja.programa_ebja values (14,4,2,'BACHILLERATO INTENSIVO','EXT004','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',18,0,0,616999,now(),'200.107.59.150',1,3,0,0,0,1);
insert into ebja.programa_grado values (38,14,14,616999,now(),'200.107.59.150',1,0,11,1,1);
insert into ebja.programa_grado values (39,14,15,616999,now(),'200.107.59.150',1,0,11,3,1);



/*El siguiente registro es para que se busque ninguno en la fase sierra2019 y calcule el siguiente*/
insert into ebja.programa_ebja values (15,5,2,'NINGUNO','NING003','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,14,0,0,16,1);
insert into ebja.programa_grado values (40,15,50,616999,now(),'200.107.59.150',1,0,12,3,1);

insert into ebja.programa_ebja values (16,1,2,'ALFABETIZACION 2-3 EGB','008','2019/09/01 00:00:00.00','2019/09/02 00:00:00.00','2020/07/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,14,0,1,17,1);
insert into ebja.programa_grado values (41,16,5,616999,now(),'200.107.59.150',1,6,13,1,1);
insert into ebja.programa_grado values (42,16,6,616999,now(),'200.107.59.150',1,0,13,3,1);
insert into ebja.programa_ebja values (17,2,2,'POST ALFABETIZACION 4-5 EGB','009','2019/09/01 00:00:00.00','2019/09/02 00:00:00.00','2020/07/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,14,0,1,18,1);
insert into ebja.programa_grado values (43,17,7,616999,now(),'200.107.59.150',1,8,14,1,1);
insert into ebja.programa_grado values (44,17,8,616999,now(),'200.107.59.150',1,0,14,3,1);
insert into ebja.programa_ebja values (18,2,2,'POST ALFABETIZACION 6-7 EGB','010','2019/09/01 00:00:00.00','2019/09/02 00:00:00.00','2020/07/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,14,0,1,19,1);
insert into ebja.programa_grado values (45,18,9,616999,now(),'200.107.59.150',1,10,15,1,1);
insert into ebja.programa_grado values (46,18,10,616999,now(),'200.107.59.150',1,0,15,3,1);
insert into ebja.programa_ebja values (19,3,2,'BASICA SUPERIOR INTENSIVA 8-9-10 EGB','011','2019/09/01 00:00:00.00','2019/09/02 00:00:00.00','2020/08/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,14,0,1,20,1);
insert into ebja.programa_grado values (47,19,11,616999,now(),'200.107.59.150',1,12,16,1,1);
insert into ebja.programa_grado values (48,19,12,616999,now(),'200.107.59.150',1,13,16,2,1);
insert into ebja.programa_grado values (49,19,13,616999,now(),'200.107.59.150',1,0,16,3,1);
insert into ebja.programa_ebja values (20,4,2,'BACHILLERATO INTENSIVO 1-2-3 BGU','012','2019/09/01 00:00:00.00','2019/09/02 00:00:00.00','2020/02/01 00:00:00.00',18,0,0,616999,now(),'200.107.59.150',1,14,0,0,0,1);
insert into ebja.programa_grado values (50,20,14,616999,now(),'200.107.59.150',1,15,17,1,1);
insert into ebja.programa_grado values (51,20,15,616999,now(),'200.107.59.150',1,16,17,2,1);
insert into ebja.programa_grado values (52,20,16,616999,now(),'200.107.59.150',1,0,17,3,1);


/* CPL  */

/*Registros cuando la persona es nueva, no tiene ningun tipo de educacion*/
insert into ebja.programa_ebja values (21,5,2,'NINGUNO','NING004','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',18,0,0,616999,now(),'200.107.59.150',1,4,0,0,0,1);
insert into ebja.programa_grado values (53,21,50,616999,now(),'200.107.59.150',1,0,18,3,1);
/*El siguiente registro es para que se busque ninguno en la fase sierra2019 y calcule el siguiente*/

/*CPL sierra 2019 ordinaria*/
insert into ebja.programa_ebja values (22,5,2,'NINGUNO','ORD002','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',18,0,0,616999,now(),'200.107.59.150',1,5,0,0,0,1);
insert into ebja.programa_grado values (54,22,5,616999,now(),'200.107.59.150',1,0,19,1,1);
insert into ebja.programa_grado values (55,22,6,616999,now(),'200.107.59.150',1,0,19,2,1);
insert into ebja.programa_grado values (56,22,7,616999,now(),'200.107.59.150',1,0,19,2,1);
insert into ebja.programa_grado values (57,22,8,616999,now(),'200.107.59.150',1,0,19,2,1);
insert into ebja.programa_grado values (58,22,9,616999,now(),'200.107.59.150',1,0,19,2,1);
insert into ebja.programa_grado values (59,22,10,616999,now(),'200.107.59.150',1,0,19,2,1);
insert into ebja.programa_grado values (60,22,11,616999,now(),'200.107.59.150',1,0,19,2,1);
insert into ebja.programa_grado values (61,22,12,616999,now(),'200.107.59.150',1,0,19,2,1);
insert into ebja.programa_grado values (62,22,13,616999,now(),'200.107.59.150',1,0,19,2,1);
insert into ebja.programa_grado values (63,22,14,616999,now(),'200.107.59.150',1,0,19,2,1);
insert into ebja.programa_grado values (64,22,15,616999,now(),'200.107.59.150',1,0,19,3,1);


/*cpl 2019 extraordinaria*/
insert into ebja.programa_ebja values (23,1,2,'ALFABETIZACION','EXT005','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',18,0,0,616999,now(),'200.107.59.150',1,6,0,0,0,1);
insert into ebja.programa_grado values (65,23,5,616999,now(),'200.107.59.150',1,0,20,1,1);
insert into ebja.programa_grado values (66,23,6,616999,now(),'200.107.59.150',1,0,20,3,1);
insert into ebja.programa_ebja values (24,2,2,'POST ALFABETIZACION','EXT006','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',18,0,0,616999,now(),'200.107.59.150',1,6,0,0,0,1);
insert into ebja.programa_grado values (67,24,7,616999,now(),'200.107.59.150',1,0,21,1,1);
insert into ebja.programa_grado values (68,24,8,616999,now(),'200.107.59.150',1,0,21,2,1);
insert into ebja.programa_grado values (69,24,9,616999,now(),'200.107.59.150',1,0,21,2,1);
insert into ebja.programa_grado values (70,24,10,616999,now(),'200.107.59.150',1,0,21,3,1);
insert into ebja.programa_ebja values (25,3,2,'BASICA SUPERIOR','EXT007','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',18,0,0,616999,now(),'200.107.59.150',1,6,0,0,0,1);
insert into ebja.programa_grado values (71,25,11,616999,now(),'200.107.59.150',1,0,22,1,1);
insert into ebja.programa_grado values (72,25,12,616999,now(),'200.107.59.150',1,0,22,2,1);
insert into ebja.programa_grado values (73,25,13,616999,now(),'200.107.59.150',1,0,22,3,1);
insert into ebja.programa_ebja values (26,4,2,'BACHILLERATO INTENSIVO','EXT008','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',18,0,0,616999,now(),'200.107.59.150',1,6,0,0,0,1);
insert into ebja.programa_grado values (74,26,14,616999,now(),'200.107.59.150',1,0,23,1,1);
insert into ebja.programa_grado values (75,26,15,616999,now(),'200.107.59.150',1,0,23,3,1);


/*cpl sierra 2019*/
 /*Registros cuando la persona es nueva, no tiene ningun tipo de educacion*/
insert into ebja.programa_ebja values (27,5,2,'NINGUNO','NING005','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',18,0,0,616999,now(),'200.107.59.150',1,15,0,0,28,1);
insert into ebja.programa_grado values (76,27,50,616999,now(),'200.107.59.150',1,0,24,3,1);

insert into ebja.programa_ebja values (28,1,2,'ALFABETIZACION 2-3 EGB','013','2019/09/01 00:00:00.00','2019/09/02 00:00:00.00','2020/07/01 00:00:00.00',18,0,0,616999,now(),'200.107.59.150',1,15,0,1,29,1);
insert into ebja.programa_grado values (77,28,5,616999,now(),'200.107.59.150',1,0,25,1,1);
insert into ebja.programa_grado values (78,28,6,616999,now(),'200.107.59.150',1,0,25,3,1);
insert into ebja.programa_ebja values (29,2,2,'POST ALFABETIZACION 4-5 EGB','014','2019/09/01 00:00:00.00','2019/09/02 00:00:00.00','2020/07/01 00:00:00.00',18,0,0,616999,now(),'200.107.59.150',1,15,0,1,30,1);
insert into ebja.programa_grado values (79,29,7,616999,now(),'200.107.59.150',1,8,26,1,1);
insert into ebja.programa_grado values (80,29,8,616999,now(),'200.107.59.150',1,0,26,3,1);
insert into ebja.programa_ebja values (30,2,2,'POST ALFABETIZACION 6-7 EGB','015','2019/09/01 00:00:00.00','2019/09/02 00:00:00.00','2020/07/01 00:00:00.00',18,0,0,616999,now(),'200.107.59.150',1,15,0,1,31,1);
insert into ebja.programa_grado values (81,30,9,616999,now(),'200.107.59.150',1,10,27,1,1);
insert into ebja.programa_grado values (82,30,10,616999,now(),'200.107.59.150',1,0,27,3,1);
insert into ebja.programa_ebja values (31,3,2,'BASICA SUPERIOR 8-9-10 EGB','016','2019/09/01 00:00:00.00','2019/09/02 00:00:00.00','2020/07/01 00:00:00.00',18,0,0,616999,now(),'200.107.59.150',1,15,0,0,32,1);
insert into ebja.programa_grado values (83,31,11,616999,now(),'200.107.59.150',1,12,28,1,1);
insert into ebja.programa_grado values (84,31,12,616999,now(),'200.107.59.150',1,13,28,2,1);
insert into ebja.programa_grado values (85,31,13,616999,now(),'200.107.59.150',1,0,28,3,1);
insert into ebja.programa_ebja values (32,4,2,'BACHILLERATO 1-2-3 BGU','017','2019/09/01 00:00:00.00','2019/09/02 00:00:00.00','2020/07/01 00:00:00.00',18,0,0,616999,now(),'200.107.59.150',1,15,0,0,0,1);
insert into ebja.programa_grado values (86,32,14,616999,now(),'200.107.59.150',1,15,29,1,1);
insert into ebja.programa_grado values (87,32,15,616999,now(),'200.107.59.150',1,16,29,2,1);
insert into ebja.programa_grado values (88,32,16,616999,now(),'200.107.59.150',1,0,29,3,1);

/* PCEI INTSV1 */

/*Registros cuando la persona es nueva, no tiene ningun tipo de educacion*/
insert into ebja.programa_ebja values (33,5,2,'NINGUNO','NING006','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,7,0,0,0,1);
insert into ebja.programa_grado values (89,33,50,616999,now(),'200.107.59.150',1,0,30,3,1);
/*El siguiente registro es para que se busque ninguno en la fase sierra2019 y calcule el siguiente*/

/*PCEI sierra 2019 ordinaria*/
insert into ebja.programa_ebja values (34,5,2,'NINGUNO','ORD003','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,8,0,0,0,1);
insert into ebja.programa_grado values (90,34,5,616999,now(),'200.107.59.150',1,0,31,1,1);
insert into ebja.programa_grado values (91,34,6,616999,now(),'200.107.59.150',1,0,31,2,1);
insert into ebja.programa_grado values (92,34,7,616999,now(),'200.107.59.150',1,0,31,2,1);
insert into ebja.programa_grado values (93,34,8,616999,now(),'200.107.59.150',1,0,31,2,1);
insert into ebja.programa_grado values (94,34,9,616999,now(),'200.107.59.150',1,0,31,2,1);
insert into ebja.programa_grado values (95,34,10,616999,now(),'200.107.59.150',1,0,31,2,1);
insert into ebja.programa_grado values (96,34,11,616999,now(),'200.107.59.150',1,0,31,2,1);
insert into ebja.programa_grado values (97,34,12,616999,now(),'200.107.59.150',1,0,31,2,1);
insert into ebja.programa_grado values (98,34,13,616999,now(),'200.107.59.150',1,0,31,2,1);
insert into ebja.programa_grado values (99,34,14,616999,now(),'200.107.59.150',1,0,31,2,1);
insert into ebja.programa_grado values (100,34,15,616999,now(),'200.107.59.150',1,0,31,3,1);

/*PCEI 2019 extraordinaria*/
insert into ebja.programa_ebja values (35,1,2,'ALFABETIZACION','EXT009','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,9,0,0,0,1);
insert into ebja.programa_grado values (101,35,5,616999,now(),'200.107.59.150',1,0,32,1,1);
insert into ebja.programa_grado values (102,35,6,616999,now(),'200.107.59.150',1,0,32,3,1);
insert into ebja.programa_ebja values (36,2,2,'POST ALFABETIZACION','EXT010','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,9,0,0,0,1);
insert into ebja.programa_grado values (103,36,7,616999,now(),'200.107.59.150',1,0,33,1,1);
insert into ebja.programa_grado values (104,36,8,616999,now(),'200.107.59.150',1,0,33,2,1);
insert into ebja.programa_grado values (105,36,9,616999,now(),'200.107.59.150',1,0,33,2,1);
insert into ebja.programa_grado values (106,36,10,616999,now(),'200.107.59.150',1,0,33,3,1);
insert into ebja.programa_ebja values (37,3,2,'BASICA SUPERIOR','EXT011','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,9,0,0,0,1);
insert into ebja.programa_grado values (107,37,11,616999,now(),'200.107.59.150',1,0,34,1,1);
insert into ebja.programa_grado values (108,37,12,616999,now(),'200.107.59.150',1,0,34,2,1);
insert into ebja.programa_grado values (109,37,13,616999,now(),'200.107.59.150',1,0,34,3,1);
insert into ebja.programa_ebja values (38,4,2,'BACHILLERATO INTENSIVO','EXT012','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',18,0,0,616999,now(),'200.107.59.150',1,9,0,0,0,1);
insert into ebja.programa_grado values (110,38,14,616999,now(),'200.107.59.150',1,0,35,1,1);
insert into ebja.programa_grado values (111,38,15,616999,now(),'200.107.59.150',1,0,35,3,1);



 /*Registros cuando la persona es nueva, no tiene ningun tipo de educacion*/
insert into ebja.programa_ebja values (39,5,2,'NINGUNO','NING007','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,16,0,0,40,1);
insert into ebja.programa_grado values (112,39,50,616999,now(),'200.107.59.150',1,0,36,3,1);
/*intensivo costa*/
insert into ebja.programa_ebja values (40,3,2,'BASICA SUPERIOR INTENSIVA 8-9 EGB','018','2019/09/01 00:00:00.00','2019/09/02 00:00:00.00','2020/02/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,16,0,0,41,1);
/*insertar no pack grado anterior para busqueda de primer grado no visible*/
insert into ebja.programa_grado values (113,40,10,616999,now(),'200.107.59.150',1,11,37,1,0);--previo
insert into ebja.programa_grado values (114,40,11,616999,now(),'200.107.59.150',1,12,37,1,1);
insert into ebja.programa_grado values (115,40,12,616999,now(),'200.107.59.150',1,0,37,2,1);
insert into ebja.programa_grado values (116,40,13,616999,now(),'200.107.59.150',1,0,37,3,0);--no existe pero es necesario para ser previo 
insert into ebja.programa_ebja values (41,4,2,'BACHILLERATO INTENSIVO 1-2-3 BGU','019','2019/09/01 00:00:00.00','2019/09/02 00:00:00.00','2020/02/01 00:00:00.00',18,0,0,616999,now(),'200.107.59.150',1,16,0,0,0,1);
insert into ebja.programa_grado values (117,41,14,616999,now(),'200.107.59.150',1,15,38,1,1);
insert into ebja.programa_grado values (118,41,15,616999,now(),'200.107.59.150',1,16,38,2,1);
insert into ebja.programa_grado values (119,41,16,616999,now(),'200.107.59.150',1,0,38,3,1);

/*Registros cuando la persona es nueva, no tiene ningun tipo de educacion*/
insert into ebja.programa_ebja values (42,5,2,'NINGUNO','NING008','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,17,0,0,44	,1);
insert into ebja.programa_grado values (120,42,50,616999,now(),'200.107.59.150',1,0,39,3,1);
/*intensivo sierra 2019*/
insert into ebja.programa_ebja values (43,3,2,'BASICA SUPERIOR INTENSIVA 7 (previo) EGB','020','2019/09/01 00:00:00.00','2019/09/02 00:00:00.00','2020/08/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,17,0,1,44,0);
insert into ebja.programa_grado values (121,43,10,616999,now(),'200.107.59.150',1,11,40,3,0); --previo
insert into ebja.programa_ebja values (44,3,2,'BASICA SUPERIOR INTENSIVA 8-9-10 EGB','021','2019/09/01 00:00:00.00','2019/09/02 00:00:00.00','2020/08/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,17,0,1,45,1);
insert into ebja.programa_grado values (122,44,11,616999,now(),'200.107.59.150',1,12,41,1,1);
insert into ebja.programa_grado values (123,44,12,616999,now(),'200.107.59.150',1,13,41,2,1);
insert into ebja.programa_grado values (124,44,13,616999,now(),'200.107.59.150',1,0,41,3,1);
insert into ebja.programa_ebja values (45,4,2,'BACHILLERATO INTENSIVO 1-2-3 BGU','022','2019/09/01 00:00:00.00','2019/09/02 00:00:00.00','2020/02/01 00:00:00.00',18,0,0,616999,now(),'200.107.59.150',1,17,0,0,0,1);
insert into ebja.programa_grado values (125,45,14,616999,now(),'200.107.59.150',1,15,42,1,1);
insert into ebja.programa_grado values (126,45,15,616999,now(),'200.107.59.150',1,16,42,2,1);
insert into ebja.programa_grado values (127,45,16,616999,now(),'200.107.59.150',1,0,42,3,1);


/*Registros cuando la persona es nueva, no tiene ningun tipo de educacion*/
insert into ebja.programa_ebja values (46,5,2,'NINGUNO','NING009','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,18,0,0,47,1);
insert into ebja.programa_grado values (128,46,50,616999,now(),'200.107.59.150',1,0,43,3,1);
/*sierra 2019*/
insert into ebja.programa_ebja values (47,3,2,'BASICA SUPERIOR 8-9-10 EGB','023','2019/09/01 00:00:00.00','2019/09/02 00:00:00.00','2020/07/01 00:00:00.00',15,0,0,616999,now(),'200.107.59.150',1,18,0,0,48,1);
insert into ebja.programa_grado values (129,47,10,616999,now(),'200.107.59.150',1,11,44,1,0); --previo
insert into ebja.programa_grado values (130,47,11,616999,now(),'200.107.59.150',1,12,44,1,1);
insert into ebja.programa_grado values (131,47,12,616999,now(),'200.107.59.150',1,13,44,2,1);
insert into ebja.programa_grado values (132,47,13,616999,now(),'200.107.59.150',1,0,44,3,1);
insert into ebja.programa_ebja values (48,4,2,'BACHILLERATO 1-2-3 BGU','024','2019/09/01 00:00:00.00','2019/09/02 00:00:00.00','2020/07/01 00:00:00.00',18,0,0,616999,now(),'200.107.59.150',1,18,0,0,0,1);
insert into ebja.programa_grado values (133,48,14,616999,now(),'200.107.59.150',1,15,45,1,1);
insert into ebja.programa_grado values (134,48,15,616999,now(),'200.107.59.150',1,16,45,2,1);
insert into ebja.programa_grado values (135,48,16,616999,now(),'200.107.59.150',1,0,45,3,1);


/*VIRTUAL*/


/*Registros cuando la persona es nueva, no tiene ningun tipo de educacion*/
/*insert into ebja.programa_ebja values (48,5,2,'NINGUNO','NING009','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,1,616999,now(),'200.107.59.150',1,10,0,0,0,1);
insert into ebja.programa_grado values (133,48,50,616999,now(),'200.107.59.150',1,0,45,3,1);*/
/*El siguiente registro es para que se busque ninguno en la fase sierra2019 y calcule el siguiente*/

/*VIRTUAL sierra 2019 ordinaria*/
/*insert into ebja.programa_ebja values (49,5,2,'NINGUNO','ORD004','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,1,616999,now(),'200.107.59.150',1,11,0,0,0,1);

insert into ebja.programa_grado values (134,49,5,616999,now(),'200.107.59.150',1,0,46,1,1);
insert into ebja.programa_grado values (135,49,6,616999,now(),'200.107.59.150',1,0,46,2,1);
insert into ebja.programa_grado values (136,49,7,616999,now(),'200.107.59.150',1,0,46,2,1);
insert into ebja.programa_grado values (137,49,8,616999,now(),'200.107.59.150',1,0,46,2,1);
insert into ebja.programa_grado values (138,49,9,616999,now(),'200.107.59.150',1,0,46,2,1);
insert into ebja.programa_grado values (139,49,10,616999,now(),'200.107.59.150',1,0,46,2,1);
insert into ebja.programa_grado values (140,49,11,616999,now(),'200.107.59.150',1,0,46,2,1);
insert into ebja.programa_grado values (141,49,12,616999,now(),'200.107.59.150',1,0,46,2,1);
insert into ebja.programa_grado values (142,49,13,616999,now(),'200.107.59.150',1,0,46,2,1);
insert into ebja.programa_grado values (143,49,14,616999,now(),'200.107.59.150',1,0,46,2,1);
insert into ebja.programa_grado values (144,49,15,616999,now(),'200.107.59.150',1,0,46,3,1);*/


/*VIRTUAL 2019 extraordinaria*/
/*insert into ebja.programa_ebja values (50,1,2,'ALFABETIZACION','EXT013','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,1,616999,now(),'200.107.59.150',1,12,0,0,0,1);
insert into ebja.programa_grado values (145,50,5,616999,now(),'200.107.59.150',1,0,47,1,1);
insert into ebja.programa_grado values (146,50,6,616999,now(),'200.107.59.150',1,0,47,3,1);
insert into ebja.programa_ebja values (51,2,2,'POST ALFABETIZACION','EXT014','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,1,616999,now(),'200.107.59.150',1,12,0,0,0,1);
insert into ebja.programa_grado values (147,51,7,616999,now(),'200.107.59.150',1,0,48,1,1);
insert into ebja.programa_grado values (148,51,8,616999,now(),'200.107.59.150',1,0,48,2,1);
insert into ebja.programa_grado values (149,51,9,616999,now(),'200.107.59.150',1,0,48,2,1);
insert into ebja.programa_grado values (150,51,10,616999,now(),'200.107.59.150',1,0,48,3,1);
insert into ebja.programa_ebja values (52,3,2,'BASICA SUPERIOR','EXT015','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,1,616999,now(),'200.107.59.150',1,12,0,0,0,1);
insert into ebja.programa_grado values (151,52,11,616999,now(),'200.107.59.150',1,0,49,1,1);
insert into ebja.programa_grado values (152,52,12,616999,now(),'200.107.59.150',1,0,49,2,1);
insert into ebja.programa_grado values (153,52,13,616999,now(),'200.107.59.150',1,0,49,3,1);
insert into ebja.programa_ebja values (53,4,2,'BACHILLERATO INTENSIVO','EXT016','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',18,0,1,616999,now(),'200.107.59.150',1,12,0,0,0,1);
insert into ebja.programa_grado values (154,53,14,616999,now(),'200.107.59.150',1,0,50,1,1);
insert into ebja.programa_grado values (155,53,15,616999,now(),'200.107.59.150',1,0,50,3,1);*/


/*convocatoria 4*/

/*Registros cuando la persona es nueva, no tiene ningun tipo de educacion*/
/*insert into ebja.programa_ebja values (54,5,2,'NINGUNO','NING010','1900/09/01 00:00:00.00','1900/09/02 00:00:00.00','2100/11/01 00:00:00.00',15,0,1,616999,now(),'200.107.59.150',1,19,0,0,54,1);
insert into ebja.programa_grado values (156,54,50,616999,now(),'200.107.59.150',1,0,51,3,1);*/
/*sierra 2019*/
/*insert into ebja.programa_ebja values (55,1,2,'BASICA SUPERIOR INTENSIVA 8-9-10 EGB','023','2019/09/01 00:00:00.00','2019/09/02 00:00:00.00','2020/02/01 00:00:00.00',15,0,1,616999,now(),'200.107.59.150',1,19,0,0,55,1);
insert into ebja.programa_grado values (157,55,11,616999,now(),'200.107.59.150',1,12,52,1,1);
insert into ebja.programa_grado values (158,55,12,616999,now(),'200.107.59.150',1,13,52,2,1);
insert into ebja.programa_grado values (159,55,13,616999,now(),'200.107.59.150',1,0,52,3,1);
insert into ebja.programa_ebja values (56,2,2,'BACHILLERATO INTENSIVO 1-2-3 BGU','024','2019/09/01 00:00:00.00','2019/09/02 00:00:00.00','2020/02/01 00:00:00.00',18,0,1,616999,now(),'200.107.59.150',1,19,0,0,0,1);
insert into ebja.programa_grado values (160,56,14,616999,now(),'200.107.59.150',1,15,53,1,1);
insert into ebja.programa_grado values (161,56,15,616999,now(),'200.107.59.150',1,16,53,2,1);
insert into ebja.programa_grado values (162,56,16,616999,now(),'200.107.59.150',1,0,53,3,1);*/


update ebja.regla_negocio set fecha_inicio='2017/08/28 00:00:00.00',fecha_fin='2020/12/01 00:00:00.00' where id_programa_ebja=3; 
update ebja.regla_negocio set fecha_inicio='2017/08/28 00:00:00.00',fecha_fin='2020/12/01 00:00:00.00' where id_programa_ebja=4;
update ebja.regla_negocio set fecha_inicio='2017/08/28 00:00:00.00',fecha_fin='2020/12/01 00:00:00.00' where id_programa_ebja=5;
update ebja.regla_negocio set fecha_inicio='2017/08/28 00:00:00.00',fecha_fin='2020/12/01 00:00:00.00' where id_programa_ebja=6;

/*activa cuando sea preproduccion*/

--insert into ebja.regla_negocio values(7,16,1,'2017/08/31 00:00:00.00','2020/12/02 00:00:00.00',616999,now(),'200.107.59.150',1);
--insert into ebja.regla_negocio values(8,17,1,'2017/08/31 00:00:00.00','2020/12/02 00:00:00.00',616999,now(),'200.107.59.150',1);
--insert into ebja.regla_negocio values(9,18,1,'2017/08/31 00:00:00.00','2020/12/02 00:00:00.00',616999,now(),'200.107.59.150',1);
--insert into ebja.regla_negocio values(10,19,1,'2017/08/31 00:00:00.00','2020/12/02 00:00:00.00',616999,now(),'200.107.59.150',1);
--insert into ebja.regla_negocio values(11,20,1,'2017/08/31 00:00:00.00','2020/12/02 00:00:00.00',616999,now(),'200.107.59.150',1);

--insert into ebja.regla_negocio values(12,28,1,'2017/08/31 00:00:00.00','2020/12/02 00:00:00.00',616999,now(),'200.107.59.150',1);
--insert into ebja.regla_negocio values(13,29,1,'2017/08/31 00:00:00.00','2020/12/02 00:00:00.00',616999,now(),'200.107.59.150',1);
--insert into ebja.regla_negocio values(14,30,1,'2017/08/31 00:00:00.00','2020/12/02 00:00:00.00',616999,now(),'200.107.59.150',1);
--insert into ebja.regla_negocio values(15,31,1,'2017/08/31 00:00:00.00','2020/12/02 00:00:00.00',616999,now(),'200.107.59.150',1);
--insert into ebja.regla_negocio values(16,32,1,'2017/08/31 00:00:00.00','2020/12/02 00:00:00.00',616999,now(),'200.107.59.150',1);

--insert into ebja.regla_negocio values(17,40,1,'2017/08/31 00:00:00.00','2020/12/02 00:00:00.00',616999,now(),'200.107.59.150',1);
--insert into ebja.regla_negocio values(18,41,1,'2017/08/31 00:00:00.00','2020/12/02 00:00:00.00',616999,now(),'200.107.59.150',1);

--insert into ebja.regla_negocio values(19,43,1,'2017/08/31 00:00:00.00','2020/12/02 00:00:00.00',616999,now(),'200.107.59.150',1);
--insert into ebja.regla_negocio values(20,44,1,'2017/08/31 00:00:00.00','2020/12/02 00:00:00.00',616999,now(),'200.107.59.150',1);
--insert into ebja.regla_negocio values(21,45,1,'2017/08/31 00:00:00.00','2020/12/02 00:00:00.00',616999,now(),'200.107.59.150',1);

--insert into ebja.regla_negocio values(22,47,1,'2017/08/31 00:00:00.00','2020/12/02 00:00:00.00',616999,now(),'200.107.59.150',1);
--insert into ebja.regla_negocio values(23,48,1,'2017/08/31 00:00:00.00','2020/12/02 00:00:00.00',616999,now(),'200.107.59.150',1);


/*activar cuando sea produccion */
/*EBJA costa 2019 alfabetizacion abc4*/
update  ebja.regla_negocio set fecha_inicio = '2020/01/02 00:00:00.00',fecha_fin = '2020/01/17 00:00:00.00' where id_programa_ebja =4;
/*EBJA costa 2019 postalfabetizacion abc4*/
update  ebja.regla_negocio set fecha_inicio = '2020/01/02 00:00:00.00',fecha_fin = '2020/01/17 00:00:00.00' where id_programa_ebja =5;
/*EBJA costa 2019 basica superior abc4*/
update  ebja.regla_negocio set fecha_inicio = '2020/01/02 00:00:00.00',fecha_fin = '2020/01/17 00:00:00.00' where id_programa_ebja =3;
/*EBJA costa 2019 bachillerato abc4*/
update  ebja.regla_negocio set fecha_inicio = '2020/01/02 00:00:00.00',fecha_fin = '2020/01/17 00:00:00.00' where id_programa_ebja =6;


/*EBJA sierra 2019 alfabetizacion */
insert into ebja.regla_negocio values(6,16,1,'2019/12/09 00:00:00.00','2019/12/31 00:00:00.00',616999,now(),'200.107.59.150',1);
/*EBJA costa 2019 postalfabetizacion 4-5*/
insert into ebja.regla_negocio values(7,17,1,'2019/12/09 00:00:00.00','2019/12/31 00:00:00.00',616999,now(),'200.107.59.150',1);
/*EBJA costa 2019 postalfabetizacion 6-7*/
insert into ebja.regla_negocio values(8,18,1,'2019/12/09 00:00:00.00','2019/12/31 00:00:00.00',616999,now(),'200.107.59.150',1);
/*EBJA costa 2019 basica superior */
insert into ebja.regla_negocio values(9,19,1,'2019/12/09 00:00:00.00','2019/12/31 00:00:00.00',616999,now(),'200.107.59.150',1);
/*EBJA costa 2019 bachillerato */
insert into ebja.regla_negocio values(10,20,1,'2019/12/09 00:00:00.00','2019/12/31 00:00:00.00',616999,now(),'200.107.59.150',1);

/*CPL sierra 2019 alfabetizacion */
insert into ebja.regla_negocio values(11,28,1,'2020/02/03 00:00:00.00','2020/02/07 00:00:00.00',616999,now(),'200.107.59.150',1);
/*CPL sierra 2019 postalfabetizacion 4-5*/
insert into ebja.regla_negocio values(12,29,1,'2020/02/03 00:00:00.00','2020/02/07 00:00:00.00',616999,now(),'200.107.59.150',1);
/*CPL sierra 2019 postalfabetizacion 6-7*/
insert into ebja.regla_negocio values(13,30,1,'2020/02/03 00:00:00.00','2020/02/07 00:00:00.00',616999,now(),'200.107.59.150',1);
/*CPL sierra 2019 basica superior */
insert into ebja.regla_negocio values(14,31,1,'2020/02/03 00:00:00.00','2020/02/07 00:00:00.00',616999,now(),'200.107.59.150',1);
/*CPL sierra 2019 bachillerato */
insert into ebja.regla_negocio values(15,32,1,'2020/02/03 00:00:00.00','2020/02/07 00:00:00.00',616999,now(),'200.107.59.150',1);


/*PCEI intensivo costa 2019 basica superior */
insert into ebja.regla_negocio values(16,40,1,'2020/01/20 00:00:00.00','2020/01/30 00:00:00.00',616999,now(),'200.107.59.150',1);
/*PCEI intensivo costa 2019 bachillerato */
insert into ebja.regla_negocio values(17,41,1,'2020/01/20 00:00:00.00','2020/01/30 00:00:00.00',616999,now(),'200.107.59.150',1);

/*previo para PCEI intensivo sierra 2019 basica superior*/
insert into ebja.regla_negocio values(18,43,1,'2020/01/06 00:00:00.00','2020/01/17 00:00:00.00',616999,now(),'200.107.59.150',1);
/*PCEI intensivo sierra 2019 basica superior */
insert into ebja.regla_negocio values(19,44,1,'2020/01/06 00:00:00.00','2020/01/17 00:00:00.00',616999,now(),'200.107.59.150',1);
/*PCEI intensivo 2019 bachillerato */
insert into ebja.regla_negocio values(20,45,1,'2020/01/06 00:00:00.00','2020/01/17 00:00:00.00',616999,now(),'200.107.59.150',1);

/*PCEI sierra 2019 basica superior */
insert into ebja.regla_negocio values(21,47,1,'2020/02/26 00:00:00.00','2020/03/03 00:00:00.00',616999,now(),'200.107.59.150',1);
/*PCEI sierra 2019 bachillerato */
insert into ebja.regla_negocio values(22,48,1,'2020/02/26 00:00:00.00','2020/03/03 00:00:00.00',616999,now(),'200.107.59.150',1);

/*virtual convocatoria 4 basica superior */
--insert into ebja.regla_negocio values(17,54,1,'2020/01/15 00:00:00.00','2020/03/15 00:00:00.00',616999,now(),'200.107.59.150',1);
/*virtual convocatoria 4 bachillerato */
--insert into ebja.regla_negocio values(18,55,1,'2020/01/15 00:00:00.00','2020/03/15 00:00:00.00',616999,now(),'200.107.59.150',1);


/*
Actualizacion de registros de inscripcion
anterior  id=programa_ebja					actual id= programa_grado
id = 3 BÁSICA SUPERIOR INTENSIVA ABC 4      id = 5                        programa grado desha reg 15 estado 0 habilitar registro 6,7 y 5 a estado 1
id = 4 ALFABETIZACIÓN  ABC 4 				id = 1						  programa grado desha reg 11 estado 0 habilitar reg 1 y 2 estado 1
id = 5 POST ALFABETIZACIÓN ABC 4			id = 3                        programa grado desha reg 12 estado 0 habilitar registro 3 y 4  a estado 1
id = 6 BACHILLERATO INTENSIVO ABC 4			id = 8						  programa grado desha reg 13 estado 0 habilitar registro 8,9 y 10  a estado 1
*/

update ebja.programa_grado set estado = 1 where id = 5;
update ebja.programa_grado set estado = 1 where id = 6;
update ebja.programa_grado set estado = 1 where id = 7;
update ebja.programa_grado set estado = 1 where id = 1;
update ebja.programa_grado set estado = 1 where id = 2;
update ebja.programa_grado set estado = 1 where id = 3;
update ebja.programa_grado set estado = 1 where id = 4;
update ebja.programa_grado set estado = 1 where id = 8;
update ebja.programa_grado set estado = 1 where id = 9;
update ebja.programa_grado set estado = 1 where id = 10;
update ebja.programa_grado set estado = 0 where id = 11;
update ebja.programa_grado set estado = 0 where id = 12;
update ebja.programa_grado set estado = 0 where id = 13;

update ebja.inscripcion set id_programa_grado = 5 where id_programa_grado = 3;  
update ebja.inscripcion set id_programa_grado = 1 where id_programa_grado = 4;  
update ebja.inscripcion set id_programa_grado = 3 where id_programa_grado = 5;  
update ebja.inscripcion set id_programa_grado = 8 where id_programa_grado = 6; 

ALTER TABLE ebja.inscripcion ADD  id_fase_aprobado INTEGER  NULL;
ALTER TABLE ebja.inscripcion ADD  id_programa_aprobado INTEGER NULL;
 
update ebja.inscripcion set id_fase_aprobado = 0 where id_fase_aprobado is null ;
update ebja.inscripcion set id_programa_aprobado = 0 where id_programa_aprobado is null;


update ebja.catalogo_ebja set nombre='Certificado/s de los años aprobados' where id = 31;
update ebja.catalogo_ebja set nombre='Título Artesanal del 2002 en adelante' where id = 32;
update ebja.catalogo_ebja set nombre='Título Artesanal anterior al 2002' where id = 33;

update ebja.catalogo_ebja set nombre='No posee documentación, debe rendir examen de ubicación.' where id = 34;
update ebja.catalogo_ebja set nombre='Posee documentación, revisión pendiente en el distrito educativo' where id = 35;


/*==================================================================*/
/*============== INICIO TABLA ebja_audit.grupo_fase_programa_log ===============*/
/*==================================================================*/
/* -- CREAR TABLE: ebja_audit.grupo_fase_programa_log -- */
/*==================================================================*/
--DROP TABLE IF EXISTS ebja_audit.grupo_fase_programa_log;
/*CREATE TABLE ebja_audit.grupo_fase_programa_log(
log_id serial primary key not null,
audit_ts timestamptz not null default now(),
operation char(8)not null,
username varchar(50) not null default "current_user"(),
before jsonb, 
after jsonb
) TABLESPACE audit_tablespace;
ALTER TABLE ebja_audit.grupo_fase_programa_log
    OWNER to carmenta;
GRANT ALL ON TABLE ebja_audit.grupo_fase_programa_log TO carmenta;
GRANT SELECT ON TABLE ebja_audit.grupo_fase_programa_log TO rol_lectura;*/
/*==================================================================*/
/* -- TRIGGER: plantilla_estudiante_audit -- */
/*==================================================================*/

/*CREATE TRIGGER grupo_fase_programa_audit
 BEFORE INSERT OR UPDATE OR DELETE
 ON ebja.grupo_fase_programa
 FOR EACH ROW
 EXECUTE PROCEDURE public.table_log_trig('ebja_audit','grupo_fase_programa_log');*/
 

