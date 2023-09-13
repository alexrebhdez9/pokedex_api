package org.pokedex.infrastructure.springdata.repository;

import org.pokedex.application.dto.PokemonDetailsDto;
import org.pokedex.domain.entity.Pokemon;
import org.pokedex.domain.exception.PokedexPokemonNotFoundException;
import org.pokedex.domain.repository.PokedexSearchRepository;
import org.pokedex.infrastructure.springdata.config.PokedexPokemonSpringDataJpaRepository;
import org.pokedex.infrastructure.springdata.dbo.PokemonEntity;
import org.pokedex.infrastructure.springdata.mapper.PokemonEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class PokedexSearchRepositoryImp implements PokedexSearchRepository {


    private final PokedexPokemonSpringDataJpaRepository pokedexPokemonSpringDataJpaRepository;

    private PokemonEntityMapper pokemonEntityMapper;

    public PokedexSearchRepositoryImp(PokedexPokemonSpringDataJpaRepository pokedexPokemonSpringDataJpaRepository,
                                      PokemonEntityMapper pokemonEntityMapper) {
        this.pokedexPokemonSpringDataJpaRepository = pokedexPokemonSpringDataJpaRepository;
        this.pokemonEntityMapper = pokemonEntityMapper;
    }
    /**
     * @param pokemonId
     * @return
     */
    @Override
    public Pokemon findById(Long pokemonId) {

        Optional<PokemonEntity> pokemonOptional = pokedexPokemonSpringDataJpaRepository.findById(pokemonId);

        if (pokemonOptional.isEmpty()) {

            throw new PokedexPokemonNotFoundException();

        } else {

            Pokemon pokemon = pokemonEntityMapper.toDomain(pokemonOptional.get());

            return pokemon;

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

            return pokemon;

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

        Iterable<PokemonEntity> pokemonList = pokedexPokemonSpringDataJpaRepository.findAll();

        if (!pokemonList.iterator().hasNext()) {

            throw new PokedexPokemonNotFoundException();

        } else {

            return  StreamSupport
                    .stream(pokemonList.spliterator(), true)
                    .map(pokemon -> pokemonEntityMapper.toDomain(pokemon))
                    .collect(Collectors.toList());

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