package org.pokedex.domain.exception;


public class PokedexPokemonNotFoundException extends RuntimeException {
    private String message;

    public PokedexPokemonNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    public PokedexPokemonNotFoundException() {
    }
}