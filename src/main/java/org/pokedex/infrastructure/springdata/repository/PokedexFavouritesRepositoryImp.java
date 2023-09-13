package org.pokedex.infrastructure.springdata.repository;

import org.pokedex.domain.entity.Pokemon;
import org.pokedex.domain.exception.PokedexAddFavouritesException;
import org.pokedex.domain.exception.PokedexPokemonNotFoundException;
import org.pokedex.domain.exception.PokedexRemoveFavouritesException;
import org.pokedex.domain.repository.PokedexFavouritesRepository;
import org.pokedex.infrastructure.springdata.config.PokedexPokemonSpringDataJpaRepository;
import org.pokedex.infrastructure.springdata.dbo.PokemonEntity;
import org.pokedex.infrastructure.springdata.mapper.PokemonEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PokedexFavouritesRepositoryImp implements PokedexFavouritesRepository {

    private final PokedexPokemonSpringDataJpaRepository pokedexPokemonSpringDataJpaRepository;

    private PokemonEntityMapper pokemonEntityMapper;

    @Autowired
    public PokedexFavouritesRepositoryImp(final PokedexPokemonSpringDataJpaRepository pokedexPokemonSpringDataJpaRepository) {
        this.pokedexPokemonSpringDataJpaRepository = pokedexPokemonSpringDataJpaRepository;
    }

    @Override
    public void addFavourite(Pokemon pokemon) {

        pokemon.setFavourite(1);

        try {

            pokedexPokemonSpringDataJpaRepository.save(pokemonEntityMapper.toDbo(pokemon));

        } catch (Exception e) {

            throw new PokedexAddFavouritesException();

        }


    }

    @Override
    public void removeFavourity(Pokemon pokemon)  {

        pokemon.setFavourite(0);

        try {

            pokedexPokemonSpringDataJpaRepository.save(pokemonEntityMapper.toDbo(pokemon));

        } catch (Exception e) {

            throw new PokedexRemoveFavouritesException();

        }

    }

    @Override
    public Iterable<Pokemon> getAllFavourites() {


        Iterable<Pokemon> pokemonList = pokedexPokemonSpringDataJpaRepository.findByFavourite(1);

        if (!pokemonList.iterator().hasNext()) {

            throw new PokedexPokemonNotFoundException();

        } else {

            return pokemonList;

        }

    }

}