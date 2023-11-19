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


CREATE DATABASE chatHisto_manager;
\c chatHisto_manager;

DROP TABLE IF EXISTS public.chat_historique ;
CREATE TABLE public.chat_historique (
    id bigserial NOT NULL,
    user_id bigint NULL,
    date_receive timestamp NULL,
    msg character text NULL,
    login varchar(50) NULL,
CONSTRAINT PK_chat_historique  PRIMARY KEY 
(
	id
));
