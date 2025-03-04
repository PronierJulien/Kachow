FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml . 
COPY api-auth/pom.xml api-auth/ 
COPY api-invocations/pom.xml api-invocations/
COPY api-joueur/pom.xml api-joueur/
COPY api-monstres/pom.xml api-monstres/
RUN mvn dependency:go-offline -B

COPY . . 
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/api-auth/target/*.jar api-auth.jar
COPY --from=build /app/api-invocations/target/*.jar api-invocations.jar
COPY --from=build /app/api-joueur/target/*.jar api-joueur.jar
COPY --from=build /app/api-monstres/target/*.jar api-monstres.jar

# Copie d'un script d'initialisation pour démarrer les services
COPY start-services.sh /app/start-services.sh
RUN chmod +x /app/start-services.sh

EXPOSE 8081 8082 8083 8084

CMD ["/app/start-services.sh"]
