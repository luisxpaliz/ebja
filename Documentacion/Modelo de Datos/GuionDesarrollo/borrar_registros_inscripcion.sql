delete from ebja.discapacidad;
delete from ebja.dato_familiar;
delete from ebja.ubicacion;
delete from ebja.registro_estudiante;
delete from ebja.inscripcion;

SELECT setval('ebja.discapacidad_id_seq','1','t');
SELECT setval('ebja.dato_familiar_id_seq','1','t');
SELECT setval('ebja.ubicacion_id_seq','1','t');
SELECT setval('ebja.registro_estudiante_id_seq','1','t');
SELECT setval('ebja.inscripcion_id_seq','1','t');