CREATE TABLE IF NOT EXISTS person (
	id uuid NOT NULL,
	birth timestamp NULL,
	name varchar(255) NULL,
	phones jsonb NULL,
	CONSTRAINT person_pkey PRIMARY KEY (id)
);