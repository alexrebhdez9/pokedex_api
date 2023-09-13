package org.pokedex.infrastructure.repository.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.pokedex.domain.entity.Pokemon;
import org.pokedex.application.dto.PokemonDetailsDto;
import org.pokedex.infrastructure.springdata.config.PokedexPokemonSpringDataJpaRepository;
import org.pokedex.infrastructure.springdata.mapper.PokemonEntityMapper;
import org.pokedex.mother.PokemonDetailsMother;
import org.pokedex.mother.PokemonMother;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PokedexPokemonSpringDataJpaRepositoryTest {

    @Mock
    private PokedexPokemonSpringDataJpaRepository repository;

    private PokemonEntityMapper pokemonEntityMapper;

    @Test
    public void testGetPokemonByName() {

        // Arrange
        String pokemonName = "Pikachu";
        Pokemon expectedPokemon = PokemonMother.buildPokemon(pokemonName, 100, 1, 50L, 80, 60L);
        Mockito.when(repository.findByName(pokemonName)).thenReturn(expectedPokemon);

        // Act
        Pokemon result = repository.findByName(pokemonName);

        // Assert
        assertEquals(expectedPokemon, result);

    }

    @Test
    public void testGetPokemonDetails() {

        // Arrange
        String pokemonName = "Pikachu";
        PokemonDetailsDto expectedDetails = PokemonDetailsMother.buildPokemonDetails(100, 50L, 80, 60L);
        Mockito.when(repository.getPokemonDetails(pokemonName)).thenReturn(expectedDetails);

        // Act
        PokemonDetailsDto result = repository.getPokemonDetails(pokemonName);

        // Assert
        assertEquals(expectedDetails, result);
    }

    @Test
    public void testFindByTypesType() {

        // Arrange
        String type = "Electric";
        List<Pokemon> expectedPokemons = new ArrayList<>();
        expectedPokemons.add(PokemonMother.buildPokemon("Pikachu", 100, 1, 50L, 80, 60L));
        expectedPokemons.add(PokemonMother.buildPokemon("Raichu", 120, 0, 70L, 90, 80L));
        Mockito.when(repository.findByTypesType(type)).thenReturn(expectedPokemons);

        // Act
        List<Pokemon> result = repository.findByTypesType(type);

        // Assert
        assertEquals(expectedPokemons, result);
    }

    @Test
    public void testFindByFavourite() {

        // Arrange
        Integer favourite = 1;
        List<Pokemon> expectedPokemons = new ArrayList<>();
        expectedPokemons.add(PokemonMother.buildPokemon("Pikachu", 100, 1, 50L, 80, 60L));
        expectedPokemons.add(PokemonMother.buildPokemon("Charmander", 90, 1, 60L, 70, 70L));
        Mockito.when(repository.findByFavourite(favourite)).thenReturn(expectedPokemons);

        // Act
        List<Pokemon> result = repository.findByFavourite(favourite);

        // Assert
        assertEquals(expectedPokemons, result);
    }
}
