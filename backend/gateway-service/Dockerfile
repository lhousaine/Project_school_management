FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD /target/gateway-service.jar gateway-service.jar
ENTRYPOINT ["java", "-jar", "gateway-service.jar"]
EXPOSE 8080
