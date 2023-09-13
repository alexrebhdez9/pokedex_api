package org.pokedex.infrastructure.services.favourites;

import org.pokedex.domain.entity.Pokemon;
import org.pokedex.domain.exception.PokedexAddFavouritesException;
import org.pokedex.domain.exception.PokedexPokemonNotFoundException;
import org.pokedex.domain.exception.PokedexRemoveFavouritesException;

import java.util.List;

public interface PokedexFavouritesService {

        Pokemon addPokemonFavourite(String pokemonName) throws PokedexAddFavouritesException;

        Pokemon removePokemonFavourite(String pokemonName) throws PokedexRemoveFavouritesException;

        List<Pokemon> getAllFavourites() throws PokedexPokemonNotFoundException;
}
