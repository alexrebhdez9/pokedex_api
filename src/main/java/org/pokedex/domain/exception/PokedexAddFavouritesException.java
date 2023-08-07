package org.pokedex.domain.exception;


public class PokedexAddFavouritesException extends RuntimeException {
    private String message;

    public PokedexAddFavouritesException(String message) {
        super(message);
        this.message = message;
    }

    public PokedexAddFavouritesException() {
    }
}

