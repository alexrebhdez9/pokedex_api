package org.pokedex.domain.exception;


public class PokedexRemoveFavouritesException extends RuntimeException {
    private String message;

    public PokedexRemoveFavouritesException(String message) {
        super(message);
        this.message = message;
    }

    public PokedexRemoveFavouritesException() {
    }
}

