-- FUNCTION: ebja.fu_asignacion_automatica(integer, integer, integer, character varying)

-- DROP FUNCTION ebja.fu_asignacion_automatica(integer, integer, integer, character varying);

CREATE OR REPLACE FUNCTION ebja.fu_asignacion_automatica(
	id_programa_ebja_in integer,
	id_zona_in integer,
	id_usuario_creacion_in integer,
	ip_usuario_in character varying,
	OUT num_inscrito integer,
	OUT num_estudiante integer,
	OUT num_matricula integer,
	OUT num_no_matricula integer)
    RETURNS record
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
AS $BODY$

DECLARE
	-- Definicion de variables.
	registro_estudiante RECORD;
	registro_institucion RECORD;
	max_estudiante integer;
	max_matricula integer;
	
	codigo_estudiante text;
	longitud integer;
	
	-- Definicion de cursores.
	cur_estudiante cursor is
		select distinct ins.id AS id_inscripcion, rest.id AS id_registro_estudiante, ins.id_grado_aprobado, ins.id_programa_ebja,  
			   ubi.codigo_postal, ubi.id_circuito, ubi.id_parroquia, ubi.calle_principal AS direccion,
			   rest.numero_identificacion, rest.apellidos_nombres, rest.fecha_nacimiento
		  from ebja.inscripcion ins
			   inner join ebja.ubicacion ubi ON (ins.id=ubi.id_inscripcion)
			   inner join ebja.registro_estudiante rest ON (ins.id=rest.id_inscripcion)
			   
			   inner join ebja.programa_institucion pi ON (ins.id_programa_ebja=pi.id_programa_ebja)
			   inner join instituc_establec ie on (ie.id=pi.id_instituc_establec)
			   inner join circuito_parroquia cp on (ie.id_circuito_parroquia=cp.id
			                                    and cp.id_circuito=ubi.id_circuito)
			   inner join circuito c on (c.id=ubi.id_circuito)
			   inner join distrito d on (d.id=c.id_distrito)
		 where ins.estado='1'
		   and rest.estado_asignacion='0'
		   and ubi.id_circuito is not null
		   and ubi.id_parroquia is not null
		   and ins.id_programa_ebja=id_programa_ebja_in
		   and d.id_zona=id_zona_in;
	
	cur_programa_institucion cursor is
		select distinct pi.id as id_programa_institucion, cir.id as id_circuito, cp.id_parroquia as id_parroquia, pi.cupo_disponible 
		  from ebja.programa_institucion pi
			   inner join ebja.programa_ebja pe ON (pi.id_programa_ebja=pe.id)
			   inner join instituc_establec ie on (ie.id=pi.id_instituc_establec)
			   inner join circuito_parroquia cp on (ie.id_circuito_parroquia=cp.id)
			   inner join circuito cir on (cp.id_circuito=cir.id)
			   inner join distrito d on (d.id=cir.id_distrito)
		 where pi.estado='1'
		   and pe.estado='1'
		   and pi.id_programa_ebja=id_programa_ebja_in
		   and d.id_zona=id_zona_in;
BEGIN
	RAISE INFO '@@ id_programa_ebja_in:(%)', id_programa_ebja_in;
	RAISE INFO '@@ id_zona_in:(%)', id_zona_in;
	num_inscrito := 0; 
	num_estudiante := 0; 
	num_matricula := 0; 
	num_no_matricula := 0;
	
	open cur_estudiante;
  	loop
    	fetch from cur_estudiante into registro_estudiante;
    		EXIT when not found;
        	RAISE NOTICE 'id_inscripcion: %', registro_estudiante.id_inscripcion;
			num_inscrito := num_inscrito + 1;
			
			--Tratar instituciones del programa ebja, por cada estudiante.
			open cur_programa_institucion;
			loop
    			fetch from cur_programa_institucion into registro_institucion;
    				EXIT when not found;
        			RAISE NOTICE 'id_programa_institucion: %', registro_institucion.id_programa_institucion;
        			RAISE NOTICE 'cupo_disponible: %', registro_institucion.cupo_disponible;
					
					--Verificar datos de circuito y distrito.
					--if (registro_estudiante.id_parroquia = registro_institucion.id_parroquia and 
					if (registro_estudiante.id_circuito = registro_institucion.id_circuito and
					    registro_institucion.cupo_disponible > 0) then
                    	RAISE NOTICE 'Circuito y parroquia iguales';
					
						--Obtener el máximo + 1 de tabla estudiante.
						select max(id) + 1 into max_estudiante from ebja.estudiante;
						--Crear código del estudiante.
 						longitud := 15 - (length(ltrim(to_char(max_estudiante, '9999999'))));
 						codigo_estudiante := rpad('EE', longitud, '0') || ltrim(to_char(max_estudiante, '9999999'));

						--Guardar datos de estudiante.
						insert into ebja.estudiante (id, id_inscripcion, id_registro_estudiante, codigo_estudiante, fecha_creacion, estado, id_usuario_creacion, ip_usuario)
                             				 values ( max_estudiante, registro_estudiante.id_inscripcion, registro_estudiante.id_registro_estudiante,  codigo_estudiante,  current_date,  '1', id_usuario_creacion_in, ip_usuario_in);
						--Setear el secuencial de la tabla estudiante.
						perform setval('ebja.estudiante_id_seq',(select max(id) from ebja.estudiante), 't');
						RAISE NOTICE 'Guardo estudiante id = %', max_estudiante;
						num_estudiante := num_estudiante + 1;

						--Obtener el máximo + 1 de tabla matricula.
						select max(id) + 1 into max_matricula from ebja.matricula;						
						
						--Guardar datos de matricula.
						insert into ebja.matricula (id, id_estudiante, id_programa_institucion, id_curso_paralelo, promover, id_tipo_proceso_catalogo, fecha_proceso, fecha_creacion, estado, id_usuario_creacion, ip_usuario)
                             				values ( max_matricula, max_estudiante, registro_institucion.id_programa_institucion, null, '0', 25, current_date, current_date,  '1', id_usuario_creacion_in, ip_usuario_in);
						
						--Setear el secuencial de la tabla matricula.
						perform setval('ebja.matricula_id_seq',(select max(id) from ebja.matricula), 't');
						RAISE NOTICE 'Guardo matricula id = %', max_matricula;
						num_matricula := num_matricula + 1;
						
						--Actualizar el cupo en -1 de la tabla programa_institucion.
						update ebja.programa_institucion set cupo_disponible = cupo_disponible - 1 where id = registro_institucion.id_programa_institucion;
						RAISE NOTICE 'Actualizo cupo en programa_institucion id = %', registro_institucion.id_programa_institucion;
						
						--Actualizar el estado de la tabla registro_estudiante.
						update ebja.registro_estudiante set estado_asignacion = '1', motivo_no_matricula = '' where id = registro_estudiante.id_registro_estudiante;
						RAISE NOTICE 'Actualizo el estado_asignacion del registro_estudiante id = %', registro_institucion.id_programa_institucion;
						
						--Salir del loop de instituciones.
						exit;
					else
						if (registro_institucion.cupo_disponible > 0) then
							--Actualizar el motivo_no_matricula de la tabla registro_estudiante.
							update ebja.registro_estudiante set motivo_no_matricula = 'La ubicación del aspirante no coincide...' where id = registro_estudiante.id_registro_estudiante;
							RAISE NOTICE 'Actualizo el motivo_no_matricula del registro_estudiante id = %', registro_institucion.id_programa_institucion;						
						else
							--Actualizar el motivo_no_matricula de la tabla registro_estudiante.
							update ebja.registro_estudiante set motivo_no_matricula = 'No existe cupo disponible...' where id = registro_estudiante.id_registro_estudiante;
							RAISE NOTICE 'Actualizo el motivo_no_matricula del registro_estudiante id = %', registro_institucion.id_programa_institucion;						

						end if;
					end if;
  			end loop;
			close cur_programa_institucion;
  	end loop;
	close cur_estudiante;

	RAISE INFO 'num_inscrito:(%)', num_inscrito;
	RAISE INFO 'num_estudiante:(%)', num_estudiante;
	RAISE INFO 'num_matricula:(%)', num_matricula;
	num_no_matricula = num_inscrito - num_matricula;
	RAISE INFO 'num_no_matricula:(%)', num_no_matricula;
  	--  RETURN address;
END;

$BODY$;

ALTER FUNCTION ebja.fu_asignacion_automatica(integer, integer, integer, character varying)
    OWNER TO carmenta;

GRANT EXECUTE ON FUNCTION ebja.fu_asignacion_automatica(integer, integer, integer, character varying) TO PUBLIC;

GRANT EXECUTE ON FUNCTION ebja.fu_asignacion_automatica(integer, integer, integer, character varying) TO carmenta;

GRANT EXECUTE ON FUNCTION ebja.fu_asignacion_automatica(integer, integer, integer, character varying) TO rol_lectura;

