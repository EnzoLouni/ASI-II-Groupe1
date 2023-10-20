FROM postgres:latest

ENV POSTGRES_DB=postgres \
      POSTGRES_USER=git \
      POSTGRES_PASSWORD=guth
	  
VOLUME /var/lib/postgresql/data

COPY scripts/create_tables.sql /docker-entrypoint-initdb.d
COPY scripts/data.sql /docker-entrypoint-initdb.d