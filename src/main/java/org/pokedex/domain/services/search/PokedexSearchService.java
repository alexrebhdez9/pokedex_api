package org.pokedex.domain.services.search;

import org.pokedex.application.dto.PokemonDetailsDto;
import org.pokedex.domain.entity.Pokemon;
import org.pokedex.domain.exception.PokedexPokemonNotFoundException;

import java.util.List;

public interface PokedexSearchService {

        Pokemon searchPokemonByName(String pokemonName) throws PokedexPokemonNotFoundException;

        List<Pokemon> searchPokemonByText(String pokemonName) throws PokedexPokemonNotFoundException;

        Pokemon searchPokemonById(Long pokemonId) throws PokedexPokemonNotFoundException;

        List<Pokemon> searchPokemonByType(String pokemonType) throws PokedexPokemonNotFoundException;

        List<Pokemon> getAllPokemon() throws PokedexPokemonNotFoundException;

        PokemonDetailsDto searchPokemonDetails(String pokemonName) throws PokedexPokemonNotFoundException;
}
