# === Stage 1 : Construction avec Maven ===
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Optimisation : Cache des dépendances
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copier les sources et construire le projet (artifactId: backend-ecomove)
COPY src ./src
RUN mvn clean package -DskipTests

# === Stage 2 : Image d'exécution légère ===
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# On récupère le JAR généré (nom basé sur l'artifactId du pom.xml)
COPY --from=build /app/target/backend-ecomove-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port d'écoute configuré
EXPOSE 8081

# Lancement de l'application
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
