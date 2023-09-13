package org.pokedex.domain.repository;

import org.pokedex.domain.entity.Pokemon;

public interface PokedexFavouritesRepository {

    void addFavourite(Pokemon pokemon);

    void removeFavourity(Pokemon pokemon);

    Iterable<Pokemon> getAllFavourites();

}
