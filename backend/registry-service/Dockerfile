FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD /target/registry-service.jar registry-service.jar
ENTRYPOINT ["java", "-jar", "registry-service.jar"]
EXPOSE 8761
