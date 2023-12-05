CREATE DATABASE chat_manager;
\c chat_manager;

DROP TABLE IF EXISTS public.chat ;
CREATE TABLE public.chat (
    id bigserial NOT NULL,
    user_id bigint NULL,
    date_receive timestamp NULL,
    msg character text NULL,
    login varchar(50) NULL,
CONSTRAINT PK_CHAT PRIMARY KEY (id)
);