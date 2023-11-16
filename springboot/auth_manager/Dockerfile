FROM openjdk:11
VOLUME /tmp
EXPOSE 8084
ENV USER_RIBBON_LISTOFSERVERS=userservice:8081
CMD mvn clean install package
ADD target/auth_manager.jar auth_manager.jar
ENTRYPOINT ["java","-jar","/auth_manager.jar"]