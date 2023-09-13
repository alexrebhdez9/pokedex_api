package org.pokedex.domain.services.favourites;

import lombok.RequiredArgsConstructor;
import org.pokedex.domain.entity.Pokemon;
import org.pokedex.domain.exception.PokedexAddFavouritesException;
import org.pokedex.domain.exception.PokedexPokemonNotFoundException;
import org.pokedex.domain.exception.PokedexRemoveFavouritesException;
import org.pokedex.domain.repository.PokedexFavouritesRepository;
import org.pokedex.domain.repository.PokedexSearchRepository;
import org.pokedex.infrastructure.springdata.mapper.PokemonEntityMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PokedexFavouritesServiceImp implements PokedexFavouritesService {

    private PokedexFavouritesRepository pokedexFavouritesRepository;

    private PokedexSearchRepository pokedexSearchRepository;

    private PokemonEntityMapper pokemonEntityMapper;


    public PokedexFavouritesServiceImp(PokedexFavouritesRepository pokedexFavouritesRepository,
                                       PokedexSearchRepository pokedexSearchRepository,
                                       PokemonEntityMapper pokemonEntityMapper) {
        this.pokedexFavouritesRepository = pokedexFavouritesRepository;
        this.pokedexSearchRepository = pokedexSearchRepository;
        this.pokemonEntityMapper = pokemonEntityMapper;
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

            return StreamSupport
                    .stream(iterator.spliterator(), true)
                    .collect(Collectors.toList());

        }
    }
}