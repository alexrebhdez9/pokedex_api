# POKEDEX API
## Application Specifications:

The application use the next architecture and technologies:

    - Java 17

    - Spring Boot version 6.0.9

    - Maven with pom file
    
    - Spring Data JPA

    - Architecture Domain Driven Design
        - Application Layer:
            It includes Controllers and Dto
        - Domain Driver:
            It includes entity, exception, repository Interfaces and domain services
        - Infrastructure Layer:
            It contains configurations with aspects and Bean confing and repository implementation
    
    - The class to initialize the app is out of the DDD infrastruture

    - Testing using Mokito and Junit

    - BBDD: H2 in file
    
    - Java AOP (Aspects) to logging

    - Docker to deploy application

    - Shell script to deploy application

## Deploy and execute application:

Only you need to go to run directory and execute the next command:
sh execute_pokedex_api.sh

If is the first time you execute the command, the script:
- Validate if exists docker image
- Validate if exists docker container
- Compile the application. When the application compile must pass all test.
- Move jar to docker path
- Create docker image
- Run docker container
- Remove jar from docker path

The next times if the container is running, the execution shell don't do anything, if the container is stopped, the shell will start it.

Application endpoints are: Before the endpoint you use (in application.poperties you can change the port with property
server.port) you must use this header http://localhost:8080

/v1/search/pokemonById/<pokemonId>
==> Example: http://localhost:8080/v1/search/pokemonById/1

/v1/search/pokemonByName/<pokemonName>
==> Example: http://localhost:8080/v1/search/pokemonByName/Charizard

/v1/search/pokemonByText/<text>
==> Example: http://localhost:8080/v1/search/pokemonByText/Char

/v1/search/pokemonByType/<pokemonType>
==> Example: http://localhost:8080/v1/search/pokemonByType/Fire

/v1/search/getAllPokemon
==> Example: http://localhost:8080/v1/search/getAllPokemon

/v1/favourite/getFavourites
==> Example: http://localhost:8080/v1/favourite/getFavourites

/v1/search/getPokemonDetails/<pokemonName>
==> Example: http://localhost:8080/v1/search/getPokemonDetails/Metapod

/v1/favourite/add/<pokemonName>
==> Example: http://localhost:8080/v1/favourite/add/Charmander

/v1/favourite/remove/<pokemonName>
==> Example: http://localhost:8080/v1/favourite/remove/Charizard

You can access to H2 console using http://localhost:8080/h2-console use the values to connect in application.properties:

- spring.datasource.url

- spring.datasource.username

- spring.datasource.password

When application run load initial data is in data.sql file in the path resources, after BBDD persist all changes.


### BBDD
How is an application to run in docker, it's configure to load mysql data when started, normally you have data in other system
like a MongoDb or Oracle and you don't need to have data files in your configurations. After you run the container the data persists
and you don't have to do nothing, obviously if you delete container you will lost your data.


### Docker:
When you run the docker container logs and BBDD data file are in the same directories inside the /app directory.

### IDE
If you load the project in an IDE like Intellij the application generate in data path the BBDD file and in logs path application logs,

##### Personal notes
There are multiples implementations, I did the projec and the deploy thinking the best implementation according to the documentation and specifications.
I hope you like it and have a conversation to speak about it. I enjoyed so much doing it. Thanks for the opportunity.