CREATE DATABASE user_manager;
\c user_manager;


DROP TABLE IF EXISTS public.user;
CREATE TABLE public.user (
    id BIGSERIAL NOT NULL,
    login VARCHAR(50) DEFAULT NULL,
    password VARCHAR(65) DEFAULT NULL,
    wallet FLOAT,
    lastName VARCHAR(100) DEFAULT NULL,
    firstName VARCHAR(100) DEFAULT NULL,
    CONSTRAINT PK_USER PRIMARY KEY (id)
);

CREATE DATABASE card_manager;
\c card_manager;

DROP TABLE IF EXISTS public.card;
CREATE TABLE public.card (
	id  BIGSERIAL NOT NULL,
	name VARCHAR(50) DEFAULT NULL,
	description VARCHAR(3000) DEFAULT NULL,
	family VARCHAR(100) DEFAULT NULL,
	affinity VARCHAR(100) DEFAULT NULL,
	imgUrl VARCHAR(200) DEFAULT NULL,
	smallImgUrl VARCHAR(200) DEFAULT NULL,
	energy FLOAT,
	hp FLOAT,
	defence FLOAT,
	attack FLOAT,
	price FLOAT,
    CONSTRAINT PK_CARD PRIMARY KEY (id)
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
