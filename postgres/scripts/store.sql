CREATE DATABASE store_manager;
\c store_manager;

DROP TABLE IF EXISTS public.store_transaction;
CREATE TABLE public.store_transaction (
    id            serial,
    user_id       integer,
    card_id	      integer,
    action	      varchar(30),
    timestamp	  date,
    CONSTRAINT PK_STORE_TRANSACTION PRIMARY KEY (id)
);

DROP TABLE IF EXISTS public.store_transaction_temp;
CREATE TABLE public.store_transaction_temp (
    id            serial,
    user_id       integer,
    card_id	      integer,
    action	      varchar(30),
    timestamp	  date,
    CONSTRAINT PK_STORE_TRANSACTION_TEMP PRIMARY KEY (id)
);