FROM postgres:latest

ENV POSTGRES_DB=postgres \
      POSTGRES_USER=sa \
      POSTGRES_PASSWORD=sa
	  
VOLUME /var/lib/postgresql/data

COPY create_tables.sql /docker-entrypoint-initdb.d
COPY data.sql /docker-entrypoint-initdb.d