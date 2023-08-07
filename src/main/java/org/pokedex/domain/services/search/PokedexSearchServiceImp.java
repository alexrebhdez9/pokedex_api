package org.pokedex.domain.services.search;

import org.pokedex.application.dto.PokemonDetailsDto;
import org.pokedex.domain.entity.Pokemon;
import org.pokedex.domain.exception.PokedexPokemonNotFoundException;
import org.pokedex.domain.repository.PokedexSearchRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PokedexSearchServiceImp implements PokedexSearchService {

    private final PokedexSearchRepository pokedexSearchRepository;


    public PokedexSearchServiceImp(PokedexSearchRepository pokedexSearchRepository) {
        this.pokedexSearchRepository = pokedexSearchRepository;
    }

    /**
     *
     * @param pokemonName
     * @return
     * @throws PokedexPokemonNotFoundException
     */
    @Override
    public Pokemon searchPokemonByName(String pokemonName) throws PokedexPokemonNotFoundException {

        Pokemon pokemon= pokedexSearchRepository.findByName(pokemonName);

        if (pokemon == null) {

            throw new PokedexPokemonNotFoundException();

        } else {

            return pokemon;

        }

    }

    @Override
    public List<Pokemon> searchPokemonByText(String text) throws PokedexPokemonNotFoundException {

        List<Pokemon> pokemonList = pokedexSearchRepository.findByText(text);

        if (pokemonList.isEmpty()) {

            throw new PokedexPokemonNotFoundException();

        } else {

            return pokemonList;

        }

    }

    /**
     *
     * @param pokemonId
     * @return
     * @throws PokedexPokemonNotFoundException
     */
    @Override
    public Pokemon searchPokemonById(Long pokemonId) throws PokedexPokemonNotFoundException{

        return pokedexSearchRepository.findById(pokemonId).orElseThrow();

    }

    /**
     *
     * @param pokemonType
     * @return
     * @throws PokedexPokemonNotFoundException
     */
    @Override
    public List<Pokemon> searchPokemonByType(String pokemonType) throws PokedexPokemonNotFoundException{

        Iterable<Pokemon> iterator = pokedexSearchRepository.findByType(pokemonType);

        if (!iterator.iterator().hasNext()) {

            throw new PokedexPokemonNotFoundException();

        } else {

            return StreamSupport.stream(iterator.spliterator(), true)
                    .collect(Collectors.toList());

        }

    }

    /**
     *
     * @return
     * @throws PokedexPokemonNotFoundException
     */
    @Override
    public List<Pokemon> getAllPokemon() throws PokedexPokemonNotFoundException {

        Iterable<Pokemon> iterator = pokedexSearchRepository.getAllPokemon();

        if (!iterator.iterator().hasNext()) {

            throw new PokedexPokemonNotFoundException();

        } else {
            return StreamSupport.stream(iterator.spliterator(), true)
                    .collect(Collectors.toList());
        }

    }

    /**
     *
     * @param pokemonName
     * @return
     * @throws PokedexPokemonNotFoundException
     */
    @Override
    public PokemonDetailsDto searchPokemonDetails(String pokemonName) throws PokedexPokemonNotFoundException {

        PokemonDetailsDto pokemonDetails = pokedexSearchRepository.findDetailsByPokemon(pokemonName);

        if (pokemonDetails == null) {

            throw new PokedexPokemonNotFoundException();

        } else {

            return pokemonDetails;

        }

    }

}