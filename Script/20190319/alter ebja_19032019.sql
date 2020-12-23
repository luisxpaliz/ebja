ALTER TABLE ebja.malla ADD COLUMN nemonico VARCHAR(60) NOT NULL
ALTER TABLE ebja.programa_ebja ADD COLUMN fecha_inicio_clases timestamp(6) NOT NULL

    COMMENT ON COLUMN ebja.malla.nemonico
    IS 'Nemonico de la tabla' ;
	COMMENT ON COLUMN ebja.programa_ebja.fecha_inicio_clases
    IS 'Fecha en la que inicia clase cad oferta' ;

CREATE SEQUENCE ebja.detalle_traslado_id_seq;

ALTER SEQUENCE ebja.detalle_traslado_id_seq
    OWNER TO carmenta;

CREATE TABLE ebja.detalle_traslado
(
  id integer NOT NULL DEFAULT nextval('ebja.detalle_traslado_id_seq'::regclass),
  id_matricula   integer     NOT NULL,
  id_matricula_curso_paralelo     integer,
  id_matricula_programa_institucion     integer,
  id_matricula_tipo_proceso_origen      integer,
  suministro_luz    character varying(50),
  dpa_parroquia     character varying(6),
  id_usuario_creacion integer NOT NULL,
  fecha_creacion timestamp without time zone NOT NULL,
  ip_usuario character varying(30) COLLATE pg_catalog."default" NOT NULL,
  estado character(1) COLLATE pg_catalog."default" NOT NULL,
  CONSTRAINT pk_detalle_traslado PRIMARY KEY (id),
  CONSTRAINT fk_matricula FOREIGN KEY (id_matricula)
        REFERENCES ebja.matricula(id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
)

    COMMENT ON COLUMN ebja.detalle_traslado.id
    IS 'Codigo de identificación de la tabla ';
	COMMENT ON COLUMN ebja.detalle_traslado.id_matricula
    IS 'Codigo de la tabla foranea ebja.matricula ';
    COMMENT ON COLUMN ebja.detalle_traslado.id_matricula_curso_paralelo
    IS 'Codigo de la tabla foranea ebja.matricula_curso_paralelo ';
    COMMENT ON COLUMN ebja.detalle_traslado.id_matricula_programa_institucion
    IS 'Codigo de la tabla foranea ebja.matricula_programa_institucion ';
	COMMENT ON COLUMN ebja.detalle_traslado.id_matricula_tipo_proceso_origen
    IS 'Codigo de la tabla foranea ebja.matricula_tipo_proceso_origen ';
	COMMENT ON COLUMN ebja.detalle_traslado.suministro_luz
    IS 'Codigo dsuminisstro de luz ';
	COMMENT ON COLUMN ebja.detalle_traslado.dpa_parroquia
    IS 'Codigo dpa de la parroquia ';
	COMMENT ON COLUMN ebja.detalle_traslado.id_usuario_creacion
    IS 'Codigo del usuario de creación del registro ';
	COMMENT ON COLUMN ebja.detalle_traslado.fecha_creacion
    IS 'Fecha de creación del registro en la base de datos';
	COMMENT ON COLUMN ebja.detalle_traslado.ip_usuario
    IS 'IP de la maquina del usuario que creo el registro';
	COMMENT ON COLUMN ebja.detalle_traslado.estado
    IS 'Estado del registro en la base de datos 0 INACTIVO 1 ACTIVO';
	
	
Ejecutar en Auditoria.

/*==================================================================*/
/*========== INICIO TABLA ebja_audit.detalle_traslado_log =============*/
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
/* -- TRIGGER: catalogo_ebja_audit -- */
/*==================================================================*/
CREATE TRIGGER detalle_traslado_audit
BEFORE INSERT OR UPDATE OR DELETE
ON ebja.detalle_traslado
FOR EACH ROW
EXECUTE PROCEDURE public.table_log_trig('ebja_audit','detalle_traslado_log');
/*============= FIN TABLA ebja_audit.detalle_traslado_log =============*/
