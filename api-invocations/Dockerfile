FROM maven:3.9.9-amazoncorretto-21-al2023 AS build
RUN mkdir -p /api
WORKDIR /api
COPY pom.xml /api
COPY src /api/src
RUN mvn -f pom.xml clean package -Dmaven.test.skip=true

FROM maven:3.9.9-amazoncorretto-21-al2023
COPY --from=build /api/target/*.jar api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/api.jar"]