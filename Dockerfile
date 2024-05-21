FROM openjdk:17-alpine

# [Labels]
LABEL maintainer="Donovan Persent"
LABEL description="Game service"

# [Environment variables]
ENV SERVER_PORT=8080

# [Actions]
WORKDIR /app
COPY . /app

RUN ./mvnw  -B clean package

# [Ports]
EXPOSE ${SERVER_PORT}

# [Entry point]
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom","target/game-service-0.0.1-SNAPSHOT.jar"]
