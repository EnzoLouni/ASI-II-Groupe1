FROM openjdk:11
VOLUME /tmp
EXPOSE 8082
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/card_manager?currentSchema=public \
      POSTGRES_USER=git \
      POSTGRES_PASSWORD=guth
CMD mvn clean install package
ADD target/card_manager.jar card_manager.jar
ENTRYPOINT ["java","-jar","/card_manager.jar"]