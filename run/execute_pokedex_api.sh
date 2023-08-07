#!/bin/bash


IMAGE_NAME=pokedex-api
DOCKER_CONTAINER=pokedex_api
IMAGE_VERSION=1.0.0

if [ $( docker images | grep pokedex-api | wc -l ) -gt 0 ]; then

    echo "Image exists locally"

    if [ $( docker ps | grep pokedex_api | wc -l ) -eq 0 ]; then


      echo "Start docker container"
      docker start pokedex_api

      echo "Pokedex API start"

    else

      echo "Pokedex API container is running"

    fi

else

    echo "Image doesn't exist locally"

    echo "Compile app"
    cd ..
    mvn clean install

    echo "move application jar file to docker path"
    mv target/pokedex_api-1.0.0.jar docker/pokedex_api-1.0.0.jar

    echo "go to docker path"
    cd docker

    echo "Creating image pokedex-api"
    docker build --tag=pokedex-api:1.0.0 .

    echo "Run docker containaer"
    docker run -p 8080:8080 -d --name pokedex_api pokedex-api:1.0.0

    echo "remove aalication jar"
    rm pokedex_api-1.0.0.jar

    echo "Pokedex API is create and running"

fi


