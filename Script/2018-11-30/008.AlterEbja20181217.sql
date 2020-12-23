-- Script del 17-Dic-2018

-- 1. 

-- Inserta un nuevo campo para registrar el motivo por el que no se realizó la matrícula del estudiante
ALTER TABLE  ebja.registro_estudiante 
ADD COLUMN motivo_no_matricula  character varying(150) ;

ALTER TABLE ebja.registro_estudiante
    OWNER to carmenta;
COMMENT ON COLUMN ebja.registro_estudiante.motivo_no_matricula
    IS 'Indica el motivo por el que no se pudo realizar la matrícula o asignación  del estudiante dentro del proceso autimático de asignación.';


