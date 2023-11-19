CREATE DATABASE user_manager;
\c user_manager;

DROP TABLE IF EXISTS public.user;
DROP SEQUENCE IF EXISTS user_sequence;

CREATE TABLE public.user (
    id SERIAL,
    login VARCHAR(50) UNIQUE DEFAULT NULL,
    password VARCHAR(65) DEFAULT NULL,
    wallet FLOAT,
    lastName VARCHAR(100) DEFAULT NULL,
    firstName VARCHAR(100) DEFAULT NULL,
    CONSTRAINT PK_USER PRIMARY KEY (id)
);

\c user_manager;
INSERT INTO public.user(id,login,password,wallet,lastName,firstName) VALUES(1,'admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918',500.0,'Admin','Admin');

CREATE SEQUENCE user_sequence START 2;

