package org.pokedex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SpringBootApplication
@ConfigurationProperties
public class PokedexApp {

    public static void main(String[] args) {

        SpringApplication.run(PokedexApp.class, args);

    }


}