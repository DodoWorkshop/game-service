FROM openjdk:17-alpine

# [Labels]
LABEL maintainer="Donovan Persent"
LABEL description="Game service"

# [Environment variables]
ENV SERVER_PORT=8080

# [Arguments]
ARG JAR_FILE=target/game-service-*.jar

# [User]
RUN addgroup --system user \
    && adduser --system --ingroup user user

USER user

# [Actions]
WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

# [Ports]
EXPOSE ${SERVER_PORT}

# [Entry point]
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom","app.jar"]