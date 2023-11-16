FROM openjdk:11
VOLUME /tmp
EXPOSE 8083
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/store_manager?currentSchema=public \
      POSTGRES_USER=git \
      POSTGRES_PASSWORD=guth
CMD mvn clean install package
ADD target/store_manager.jar store_manager.jar
ENTRYPOINT ["java","-jar","/store_manager.jar"]