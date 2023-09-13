package org.pokedex.domain.repository;

import org.pokedex.application.dto.PokemonDetailsDto;
import org.pokedex.domain.entity.Pokemon;

import java.util.List;

public interface PokedexSearchRepository {

    Pokemon findById(Long pokemonId);

    Pokemon findByName(String pokemonName);

    List<Pokemon> findByText(String text);

    List<Pokemon> findByType(String pokemonType);

    Iterable<Pokemon> getAllPokemon();

    PokemonDetailsDto findDetailsByPokemon(String pokemonName);

}
