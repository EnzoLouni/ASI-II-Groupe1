CREATE DATABASE user_manager;
\c user_manager;


DROP TABLE IF EXISTS public.user;
CREATE TABLE public.user (
    id BIGSERIAL NOT NULL,
    login VARCHAR(50) UNIQUE DEFAULT NULL,
    password VARCHAR(65) DEFAULT NULL,
    wallet FLOAT,
    lastName VARCHAR(100) DEFAULT NULL,
    firstName VARCHAR(100) DEFAULT NULL,
    CONSTRAINT PK_USER PRIMARY KEY (id)
);

CREATE DATABASE card_manager;
\c card_manager;

DROP TABLE IF EXISTS public.card_model;
CREATE TABLE public.card_model (
	id SERIAL,
	energy FLOAT,
	hp FLOAT,
	defense FLOAT,
	attack FLOAT,
	price FLOAT,
    user_id INTEGER DEFAULT NULL,
	card_reference_id INTEGER,
    CONSTRAINT PK_CARD_MODEL PRIMARY KEY (id)
);

DROP TABLE IF EXISTS public.card_reference;
CREATE TABLE public.card_reference(
	id SERIAL,
	name VARCHAR(50) UNIQUE DEFAULT NULL,
	description VARCHAR(3000) DEFAULT NULL,
	family VARCHAR(100) DEFAULT NULL,
	affinity VARCHAR(100) DEFAULT NULL,
	img_url VARCHAR(200) DEFAULT NULL,
	small_img_url VARCHAR(200) DEFAULT NULL,
    CONSTRAINT PK_CARD_REFERENCE PRIMARY KEY (id)
);

CREATE DATABASE store_manager;
\c store_manager;

DROP TABLE IF EXISTS public.store;
CREATE TABLE public.store_transaction (
    id            serial,
    user_id       integer,
    card_id	      integer,
    action	      varchar(30),
    timestamp	  date,
    CONSTRAINT PK_STORE_TRANSACTION PRIMARY KEY (id)
);
