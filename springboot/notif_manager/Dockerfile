FROM openjdk:11
VOLUME /tmp
EXPOSE 8085
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/notif_manager?currentSchema=public \
      POSTGRES_USER=git \
      POSTGRES_PASSWORD=guth
CMD mvn clean install package
ADD target/notif_manager.jar notif_manager.jar
ENTRYPOINT ["java","-jar","/notif_manager.jar"]