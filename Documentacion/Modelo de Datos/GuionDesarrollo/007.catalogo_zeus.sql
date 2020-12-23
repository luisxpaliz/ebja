-- Catalogo TIPO DE DOCUMENTO
-- Observación: Crear el catalogo NO PRESENTA y Se agrega la descripcion correspondiente en cada tipo de documento

INSERT INTO catalogo VALUES ((SELECT MAX(id) + 1 FROM catalogo), 16, 'N','NO PRESENTA',null,null,now(),1,null);

UPDATE catalogo
SET descripcion = 'CEDULA'
WHERE id = 328 and nombre = 'C';

UPDATE catalogo
SET descripcion = 'PASAPORTE'
WHERE id = 329 and nombre = 'P';

UPDATE catalogo
SET descripcion = 'CARNET DE REFUGIADO'
WHERE id = 331 and nombre = 'R';

	UPDATE public.tipo_catalogo
	SET nemonico='TIPIDE'
	WHERE public.tipo_catalogo.id=16;
	
	UPDATE public.tipo_catalogo
	SET nemonico='SEX'
	WHERE public.tipo_catalogo.id=4;
	
	UPDATE public.tipo_catalogo
	SET nemonico='ESCI'
	WHERE public.tipo_catalogo.id=3;
	
	UPDATE public.tipo_catalogo
	SET nemonico='NACI'
	WHERE public.tipo_catalogo.id=25;



-- Catalogo GENERO
-- Observación: Ya existe el catálogo solo se consume el mismo.

-- Catalogo ESTADO CIVIL
-- Observación: Ya existe el catálogo solo se consume el mismo.

-- Catalogo NACIONALIDAD INDIGENA
-- Observación: Ya existe el catálogo solo se consume el mism