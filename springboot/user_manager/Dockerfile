FROM openjdk:11
VOLUME /tmp
EXPOSE 8081
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/user_manager?currentSchema=public \
      POSTGRES_USER=git \
      POSTGRES_PASSWORD=guth
ADD target/user_manager.jar user_manager.jar
ENTRYPOINT ["java","-jar","/user_manager.jar"]