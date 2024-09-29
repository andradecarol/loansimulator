# BUILD
FROM maven:3.9.8-amazoncorretto-17-debian as build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# RUN
FROM amazoncorretto:17-alpine3.18-jdk
WORKDIR /app
COPY --from=build /home/app/target/*.jar /app/run.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/run.jar"]