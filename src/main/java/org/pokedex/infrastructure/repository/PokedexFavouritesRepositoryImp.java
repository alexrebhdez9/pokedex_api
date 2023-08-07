package org.pokedex.infrastructure.repository;

import org.pokedex.domain.entity.Pokemon;
import org.pokedex.domain.exception.PokedexAddFavouritesException;
import org.pokedex.domain.exception.PokedexPokemonNotFoundException;
import org.pokedex.domain.exception.PokedexRemoveFavouritesException;
import org.pokedex.domain.repository.PokedexFavouritesRepository;
import org.pokedex.infrastructure.repository.jpa.PokedexPokemonSpringDataJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PokedexFavouritesRepositoryImp implements PokedexFavouritesRepository {


    private final PokedexPokemonSpringDataJpaRepository pokedexPokemonSpringDataJpaRepository;

    @Autowired
    public PokedexFavouritesRepositoryImp(final PokedexPokemonSpringDataJpaRepository pokedexPokemonSpringDataJpaRepository) {
        this.pokedexPokemonSpringDataJpaRepository = pokedexPokemonSpringDataJpaRepository;
    }

    @Override
    public void addFavourite(Pokemon pokemon) {

        pokemon.setFavourite(1);

        try {

            pokedexPokemonSpringDataJpaRepository.save(pokemon);

        } catch (Exception e) {

            throw new PokedexAddFavouritesException();

        }


    }

    @Override
    public void removeFavourity(Pokemon pokemon)  {

        pokemon.setFavourite(0);

        try {

            pokedexPokemonSpringDataJpaRepository.save(pokemon);

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

            return  pokemonList;

        }

    }

}