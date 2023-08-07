package org.pokedex.domain.services.favourites;

import org.pokedex.domain.entity.Pokemon;
import org.pokedex.domain.exception.PokedexAddFavouritesException;
import org.pokedex.domain.exception.PokedexPokemonNotFoundException;
import org.pokedex.domain.exception.PokedexRemoveFavouritesException;
import org.pokedex.domain.repository.PokedexFavouritesRepository;
import org.pokedex.domain.repository.PokedexSearchRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PokedexFavouritesServiceImp implements PokedexFavouritesService {

    private final PokedexFavouritesRepository pokedexFavouritesRepository;
    private final PokedexSearchRepository pokedexSearchRepository;

    public PokedexFavouritesServiceImp(PokedexFavouritesRepository pokedexFavouritesRepository, PokedexSearchRepository pokedexSearchRepository) {
        this.pokedexFavouritesRepository = pokedexFavouritesRepository;
        this.pokedexSearchRepository = pokedexSearchRepository;
    }

    /**
     *
     * @param pokemonName
     * @return
     */
    @Override
    public Pokemon addPokemonFavourite(String pokemonName) {

        Pokemon pokemon = pokedexSearchRepository.findByName(pokemonName);

        if (pokemon == null) {

            throw new PokedexAddFavouritesException();

        } else {

            pokedexFavouritesRepository.addFavourite(pokemon);

            return pokemon;
        }

    }

    /**
     *
     * @param pokemonName
     * @return
     */
    @Override
    public Pokemon removePokemonFavourite(String pokemonName) {

        Pokemon pokemon = pokedexSearchRepository.findByName(pokemonName);

        if (pokemon == null) {

            throw new PokedexRemoveFavouritesException();

        } else {

            pokedexFavouritesRepository.removeFavourity(pokemon);

            return pokemon;
        }

    }

    /**
     *
     * @return
     * @throws PokedexPokemonNotFoundException
     */
    @Override
    public List<Pokemon> getAllFavourites() throws PokedexPokemonNotFoundException {

        Iterable<Pokemon> iterator = pokedexFavouritesRepository.getAllFavourites();

        if (!iterator.iterator().hasNext()) {

            throw new PokedexPokemonNotFoundException();

        } else {

            return StreamSupport.stream(iterator.spliterator(), false)
                    .collect(Collectors.toList());

        }
    }
}