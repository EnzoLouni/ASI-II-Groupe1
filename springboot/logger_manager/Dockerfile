FROM openjdk:11
VOLUME /tmp
EXPOSE 8086
ENV spring.activemq.broker-url=tcp://activemq:61616
ADD target/logger_manager.jar logger_manager.jar
ENTRYPOINT ["java","-jar","/logger_manager.jar"]