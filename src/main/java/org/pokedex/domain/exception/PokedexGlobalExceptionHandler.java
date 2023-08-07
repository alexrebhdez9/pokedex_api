package org.pokedex.domain.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PokedexGlobalExceptionHandler {

    @Value(value = "${data.exception.message1}")
    private String message1;

    @Value(value = "${data.exception.message2}")
    private String message2;

    @Value(value = "${data.exception.message3}")
    private String message3;

    @ExceptionHandler(value = PokedexPokemonNotFoundException.class)
    public ResponseEntity pokedexPokemonNotFoundException(PokedexPokemonNotFoundException pokedexPokemonNotFoundException) {

        return new ResponseEntity<>(message1, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = PokedexAddFavouritesException.class)
    public ResponseEntity pokedexAddPokemonException(PokedexAddFavouritesException pokedexAddFavouritesException) {

        return new ResponseEntity(message2, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(value = PokedexRemoveFavouritesException.class)
    public ResponseEntity pokedexRemovePokemonException(PokedexAddFavouritesException pokedexAddFavouritesException) {

        return new ResponseEntity(message3, HttpStatus.FORBIDDEN);

    }
}