FROM openjdk:11
VOLUME /tmp
EXPOSE 8084
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/auth_manager?currentSchema=public \
      POSTGRES_USER=git \
      POSTGRES_PASSWORD=guth
ADD target/auth_manager.jar auth_manager.jar
ENTRYPOINT ["java","-jar","/auth_manager.jar"]