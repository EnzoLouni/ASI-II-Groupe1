FROM openjdk:11
VOLUME /tmp
EXPOSE 8083
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/store_manager?currentSchema=public \
      POSTGRES_USER=git \
      POSTGRES_PASSWORD=guth \
      spring.activemq.broker-url=tcp://activemq:61616 \
      user.ribbon.listOfServers=userservice:8081 \
      card.ribbon.listOfServers=cardservice:8082
ADD target/store_manager.jar store_manager.jar
ENTRYPOINT ["java","-jar","/store_manager.jar"]