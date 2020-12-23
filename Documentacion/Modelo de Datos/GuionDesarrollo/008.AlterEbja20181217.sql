-- Script del 17-Dic-2018

-- 1. 
-- Inserta un nuevo tipo de catálogo para manejo de asistencias.
--Catalogo TIPO ASISTENCIA
-- Observación: Catálogo de los tipos de asistencia

INSERT INTO ebja.tipo_catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.tipo_catalogo_ebja),'TIPO ASISTENCIA', 'ESPECIFICA EL TIPO DE ASISTENCIA','TIPASIS','0', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'UNIDOCENTE',null,'UD', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'POR DOCENTE',null,'MD', 1,  now(), '1',1);

-- Setear secuenciales
SELECT setval('ebja.tipo_catalogo_ebja_id_seq',(select max(id) from ebja.tipo_catalogo_ebja),'t');
SELECT setval('ebja.catalogo_ebja_id_seq',(select max(id) from ebja.catalogo_ebja),'t');


--2.
-- Inserta un nuevo campo para registrar el motivo por el que no se realizó la matrícula del estudiante
ALTER TABLE  ebja.registro_estudiante 
ADD COLUMN motivo_no_matricula  character varying(150) ;

ALTER TABLE ebja.registro_estudiante
    OWNER to carmenta;
COMMENT ON COLUMN ebja.registro_estudiante.motivo_no_matricula
    IS 'Indica el motivo por el que no se pudo realizar la matrícula o asignación  del estudiante dentro del proceso autimático de asignación.';


