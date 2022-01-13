-- public.cars definition

-- Drop table

-- DROP TABLE public.cars;

CREATE TABLE public.cars (
	id int8 NOT NULL,
	color varchar(255) NULL,
	"cost" int8 NULL,
	doors int8 NULL,
	factory_id int8 NULL,
	fuel varchar(255) NULL,
	model varchar(255) NULL,
	"year" int8 NULL,
	CONSTRAINT cars_pkey PRIMARY KEY (id)
);


-- public.factories definition

-- Drop table

-- DROP TABLE public.factories;

CREATE TABLE public.factories (
	id int8 NOT NULL,
	country_code int8 NULL,
	"name" varchar(255) NULL,
	CONSTRAINT factories_pkey PRIMARY KEY (id)
);

-- public.cars foreign keys

ALTER TABLE public.cars ADD CONSTRAINT fkhgyive11ti6l29wvh6uv6fe1y FOREIGN KEY (factory_id) REFERENCES public.factories(id);
