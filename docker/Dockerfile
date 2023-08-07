FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app

EXPOSE 8080

ARG JAR_FILE=pokedex_api-1.0.0.jar

ADD ${JAR_FILE} pokedex_api-1.0.0.jar

ENTRYPOINT ["java","-jar","pokedex_api-1.0.0.jar"]