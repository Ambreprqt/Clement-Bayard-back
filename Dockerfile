FROM openjdk:latest

# Créez le répertoire de travail dans le conteneur
WORKDIR /app

# Copiez le fichier JAR de votre application Spring Boot vers le conteneur
COPY target/clement_ws-0.0.1-SNAPSHOT.jar /app/clement_ws-0.0.1-SNAPSHOT.jar

# Exposez le port sur lequel votre application s'exécute (si nécessaire)
EXPOSE 8080

# Commande pour démarrer l'application Spring Boot une fois le conteneur lancé
CMD ["java", "-jar", "clement_ws-0.0.1-SNAPSHOT.jar"]
