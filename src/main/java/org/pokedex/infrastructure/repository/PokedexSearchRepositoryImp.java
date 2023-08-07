package org.pokedex.infrastructure.repository;

import org.pokedex.application.dto.PokemonDetailsDto;
import org.pokedex.domain.entity.Pokemon;
import org.pokedex.domain.exception.PokedexPokemonNotFoundException;
import org.pokedex.domain.repository.PokedexSearchRepository;
import org.pokedex.infrastructure.repository.jpa.PokedexPokemonSpringDataJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Primary
public class PokedexSearchRepositoryImp implements PokedexSearchRepository {


    private final PokedexPokemonSpringDataJpaRepository pokedexPokemonSpringDataJpaRepository;

    @Autowired
    public PokedexSearchRepositoryImp(final PokedexPokemonSpringDataJpaRepository pokedexPokemonSpringDataJpaRepository) {
        this.pokedexPokemonSpringDataJpaRepository = pokedexPokemonSpringDataJpaRepository;
    }

    /**
     *
     * @param pokemonId
     * @return
     */
    @Override
    public Optional<Pokemon> findById(Long pokemonId) {

        Optional<Pokemon> pokemonOptional = pokedexPokemonSpringDataJpaRepository.findById(pokemonId);

        if (pokemonOptional.isEmpty()) {

            throw new PokedexPokemonNotFoundException();

        } else {

            return  pokemonOptional;

        }
    }

    /**
     *
     * @param pokemonName
     * @return
     */
    @Override
    public Pokemon findByName(String pokemonName) {

        Pokemon pokemon;

        pokemon = pokedexPokemonSpringDataJpaRepository.findByName(pokemonName);

        if ( pokemon == null) {

            throw new PokedexPokemonNotFoundException();

        } else {

            return  pokemon;

        }

    }

    /**
     *
     * @param text
     * @return
     */
    @Override
    public List<Pokemon> findByText(String text) {

        List<Pokemon> pokemonList = pokedexPokemonSpringDataJpaRepository.findPokemonByNameContains(text);

        if ( pokemonList.isEmpty()) {

            throw new PokedexPokemonNotFoundException();

        } else {

            return pokemonList;

        }

    }

    /**
     *
     * @param pokemonType
     * @return
     */
    @Override
    public List<Pokemon> findByType(String pokemonType) {

        List<Pokemon> pokemonList = pokedexPokemonSpringDataJpaRepository.findByTypesType(pokemonType);

        if (pokemonList.isEmpty()) {

            throw new PokedexPokemonNotFoundException();

        } else {

            return  pokemonList;

        }

    }


    /**
     *
     * @return
     */
    @Override
    public Iterable<Pokemon> getAllPokemon() {

        Iterable<Pokemon> pokemonList = pokedexPokemonSpringDataJpaRepository.findAll();

        if (!pokemonList.iterator().hasNext()) {

            throw new PokedexPokemonNotFoundException();

        } else {

            return  pokemonList;

        }

    }

    /**
     *
     * @param pokemonName
     * @return
     */
    @Override
    public PokemonDetailsDto findDetailsByPokemon(String pokemonName) {

        PokemonDetailsDto pokemonDetailsDto = pokedexPokemonSpringDataJpaRepository.getPokemonDetails(pokemonName);

        if (pokemonDetailsDto == null) {

            throw new PokedexPokemonNotFoundException();

        } else {

            return pokemonDetailsDto;

        }
    }

}