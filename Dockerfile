FROM openjdk:8-alpine
COPY target/*.jar app.jar
EXPOSE 9002
ENTRYPOINT ["java","-jar","app.jar"]