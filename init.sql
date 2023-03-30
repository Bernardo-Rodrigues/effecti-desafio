CREATE TABLE IF NOT EXISTS public.users
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.modalities
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT modalities_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.bids
(
    id integer NOT NULL,
    description text COLLATE pg_catalog."default",
    entity character varying(255) COLLATE pg_catalog."default",
    local text COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    opening_date date,
    sector character varying(255) COLLATE pg_catalog."default",
    value double precision,
    modality_id integer,
    CONSTRAINT bids_pkey PRIMARY KEY (id),
    CONSTRAINT fkf009gm6glwsgpfdf6utyxvpt7 FOREIGN KEY (modality_id)
        REFERENCES public.modalities (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.edicts
(
    id integer NOT NULL,
    link character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    bid_id integer,
    CONSTRAINT edicts_pkey PRIMARY KEY (id),
    CONSTRAINT fk49bh0el3uo62lmak0uj8p86pn FOREIGN KEY (bid_id)
        REFERENCES public.bids (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.readings
(
    user_id integer NOT NULL,
    bid_id integer NOT NULL,
    CONSTRAINT readings_pkey PRIMARY KEY (bid_id, user_id),
    CONSTRAINT fk4kbo107j7yeobynsudfmrmafi FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fktb04lmwcldfj0h1pnxa7k4mvs FOREIGN KEY (bid_id)
        REFERENCES public.bids (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);