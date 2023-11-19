FROM postgres:latest

ENV POSTGRES_DB=postgres \
      POSTGRES_USER=git \
      POSTGRES_PASSWORD=guth
	  
VOLUME /var/lib/postgresql/data

COPY scripts/user.sql /docker-entrypoint-initdb.d
COPY scripts/card.sql /docker-entrypoint-initdb.d
COPY scripts/store.sql /docker-entrypoint-initdb.d