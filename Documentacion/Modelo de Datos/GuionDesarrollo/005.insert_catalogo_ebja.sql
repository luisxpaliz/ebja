-- Catalogo SITUACION LABORAL
-- Observación: El catálogo no existe, se crea.

INSERT INTO ebja.tipo_catalogo_ebja VALUES ((SELECT COALESCE(MAX(id),0) + 1 FROM ebja.tipo_catalogo_ebja), 'SITUACIÓN LABORAL', 'SITUACIÓN LABORAL DEL ASPIRANTE', 'SITLAB', '1', 1, now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT COALESCE(MAX(id),0) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'TRABAJA',null,'TRA', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'NO TRABAJA',null,'NOTRA', 1,  now(), '1',1);


-- Catalogo DATO FAMILIAR
-- Observación: El catálogo no existe, se crea.

INSERT INTO ebja.tipo_catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.tipo_catalogo_ebja), 'DATO FAMILIAR', 'ESPECIFICA EL RANGO DE EDAD DE LOS HIJOS','DATFAM','0', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'Tiene hijos de 0-5 años',null,'ENT0-5A', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'Tiene hijos de 6-17 años',null,'ENT6-17A', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'Tiene hijos de 18 años en adelante',null,'MAS18A', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'Está en estado de gestación',null,'ESTGES', 1,  now(), '1',1);

-- Catalogo ACTIVIDAD ECONOMICA
-- Observación: El catálogo no existe, se crea.

INSERT INTO ebja.tipo_catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.tipo_catalogo_ebja), 'ACTIVIDAD ECONOMICA', 'CATÁLOGO DE ACTIVIDADES ECONÓMICA','ACTECO','1', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'NINGUNA',null,'NIN', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'AGROPECUARIA',null,'AGR', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'ARTESANAL',null,'ART', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'COMERCIAL',null,'COM', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'SERVICIO',null,'SER', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'OTRA',null,'OTR', 1,  now(), '1',1);

-- Catalogo DISCAPACIDAD
-- Observación: Existe un catálogo con lista de discapacidades pero detalladas y en los RF está de forma general.
--				El catálogo se crea como DISCAPACIDAD GENERAL.

INSERT INTO ebja.tipo_catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.tipo_catalogo_ebja), 'DISCAPACIDAD GENERAL', 'DISCAPACIDAD GENERAL','DISGEN','1', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'FISICA',null,'FIS', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'AUDITIVA',null,'AUD', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'VISUAL',null,'VIS', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'LENGUAJE',null,'INT', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'MENTAL',null,'INT', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'PSICOLOGICA',null,'INT', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'PSICOSOCIAL',null,'INT', 1,  now(), '1',1);

-- Catalogo AÑOS DE REZAGO
-- Observación: El catálogo no existe, se crea.

INSERT INTO ebja.tipo_catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.tipo_catalogo_ebja), 'AÑOS DE REZAGO', 'CATÁLOGO DE RANGO DE AÑOS DE REZAGO','ANIREZ','1', 1, now(),1,1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), '3 años o más de rezago y se encuentra dentro del sistema educativo',null,'3ANIMASSE', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), '3 – 5 años de rezago, está fuera del sistema educativo',null,'3-5ANIFSE', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), '6 – 10 años de rezago, está fuera del sistema educativo',null,'6-10ANIFSE', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'Más de 10 años de rezago, está fuera del sistema educativo',null,'MAS10ANIFSE', 1,  now(), '1',1);

-- Catalogo ESTADO DE REGISTRO
-- Observación: El catálogo no existe, se crea por petición de Galo Ortega

INSERT INTO ebja.tipo_catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.tipo_catalogo_ebja), 'ESTADO DE REGISTRO', 'ESPECIFICA EL ESTADO DE LOS REGISTROS DE LAS TABLAS','ESTREG','0', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'ACTIVO',null,'1', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'ACTIVO',null,'A', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'INACTIVO',null,'0', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'INACTIVO',null,'I', 1,  now(), '1',1);

-- Catalogo TIPO DE PROCESO
-- Observación: Catálogo de tipos de procesos que se realizan con la matricula 

INSERT INTO ebja.tipo_catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.tipo_catalogo_ebja), 'TIPO DE PROCESO', 'ESPECIFICA EL TIPO DE PROCESO EN MATRICULA','TIPPRO','0', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'MATRICULA',null,'M', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'TRASLADO',null,'T', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'REASIGNACIÓN',null,'R', 1,  now(), '1',1);

--Catalogo DOCUMENTACION PRESENTADA
-- Observación: Catálogo de la documentación presentada para registrar la inscripción 

INSERT INTO ebja.tipo_catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.tipo_catalogo_ebja),'DOCUMENTACION PRESENTADA', 'ESPECIFICA EL TIPO DE DOCUMENTACION PRESENTADA EN LA INSCRIPCION','DOCPRE','0', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'Certificado de promoción último año (Para bachillerato es indispensable el certificado de promoción de 8vo, 9no, 10mo (EGB))',null,'C', 1,   now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'Título artesanal anterior al 2002 (Debe rendir examen de ubicación)',null,'L', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'Título artesanal del  2002 en adelante (Equivalencia a 10mo EGB)',null,'R', 1,  now(), '1',1);

--Catalogo MOTIVO DE DOCUMENTACION
-- Observación: Catálogo de los motivos de la documentación o certificados no presentados en la inscripción 

INSERT INTO ebja.tipo_catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.tipo_catalogo_ebja),'MOTIVO DE DOCUMENTACION', 'ESPECIFICA EL MOTIVO DE LA DOCUMENTACION NO PRESENTADA EN LA INSCRIPCION','MOTDOC','0', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'Debe rendir examen de ubicación',null,'D', 1,  now(), '1',1);
INSERT INTO ebja.catalogo_ebja VALUES ((SELECT MAX(id) + 1 FROM ebja.catalogo_ebja), (SELECT MAX(id) FROM ebja.tipo_catalogo_ebja), 'Presentará documentos',null,'P', 1,  now(), '1',1);


-- Setear secuenciales
SELECT setval('ebja.tipo_catalogo_ebja_id_seq',(select max(id) from ebja.tipo_catalogo_ebja),'t');
SELECT setval('ebja.catalogo_ebja_id_seq',(select max(id) from ebja.catalogo_ebja),'t');