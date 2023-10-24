FROM openjdk:11
VOLUME /tmp
EXPOSE 8085
ADD target/notif_manager.jar notif_manager.jar
ENTRYPOINT ["java","-jar","/notif_manager.jar"]