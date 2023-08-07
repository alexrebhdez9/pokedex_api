package org.pokedex.infrastructure.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.pokedex.domain.entity.Pokemon;
import org.pokedex.application.dto.PokemonDetailsDto;
import org.pokedex.domain.exception.PokedexPokemonNotFoundException;
import org.pokedex.infrastructure.repository.jpa.PokedexPokemonSpringDataJpaRepository;
import org.pokedex.mother.PokemonDetailsMother;
import org.pokedex.mother.PokemonMother;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class PokedexSearchRepositoryImpTest {

    @Mock
    private PokedexPokemonSpringDataJpaRepository pokedexPokemonSpringDataJpaRepository;

    @InjectMocks
    private PokedexSearchRepositoryImp pokedexSearchRepository;

    @Test
    public void testFindById() {
        // Arrange
        Long pokemonId = 1L;
        Pokemon expectedPokemon = PokemonMother.buildPokemon("Pikachu", 100, 0, 50L, 80, 60L);
        Mockito.when(pokedexPokemonSpringDataJpaRepository.findById(pokemonId)).thenReturn(Optional.of(expectedPokemon));

        // Act
        Optional<Pokemon> result = pokedexSearchRepository.findById(pokemonId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedPokemon, result.get());
    }

    @Test(expected = PokedexPokemonNotFoundException.class)
    public void testFindByIdNotFound() {

        // Arrange
        Long pokemonId = 1L;
        Mockito.when(pokedexPokemonSpringDataJpaRepository.findById(pokemonId)).thenReturn(Optional.empty());

        // Act
        pokedexSearchRepository.findById(pokemonId);
    }

    @Test
    public void testFindByName() {
        // Arrange
        String pokemonName = "Pikachu";
        Pokemon expectedPokemon = PokemonMother.buildPokemon(pokemonName, 100, 0, 50L, 80, 60L);
        Mockito.when(pokedexPokemonSpringDataJpaRepository.findByName(pokemonName)).thenReturn(expectedPokemon);

        // Act
        Pokemon result = pokedexSearchRepository.findByName(pokemonName);

        // Assert
        assertEquals(expectedPokemon, result);
    }

    @Test(expected = PokedexPokemonNotFoundException.class)
    public void testFindByNameNotFound() {
        // Arrange
        String pokemonName = "Pikachu";
        Mockito.when(pokedexPokemonSpringDataJpaRepository.findByName(pokemonName)).thenReturn(null);

        // Act
        pokedexSearchRepository.findByName(pokemonName);
    }

    @Test
    public void testFindByType() {
        // Arrange
        String pokemonType = "Electric";
        List<Pokemon> expectedPokemons = Arrays.asList(
                PokemonMother.buildPokemon("Pikachu", 100, 0, 50L, 80, 60L),
                PokemonMother.buildPokemon("Raichu", 120, 0, 70L, 90, 80L)
        );
        Mockito.when(pokedexPokemonSpringDataJpaRepository.findByTypesType(pokemonType)).thenReturn(expectedPokemons);

        // Act
        List<Pokemon> result = pokedexSearchRepository.findByType(pokemonType);

        // Assert
        assertEquals(expectedPokemons, result);
    }

    @Test(expected = PokedexPokemonNotFoundException.class)
    public void testFindByTypeNotFound() {
        // Arrange
        String pokemonType = "Fire";
        Mockito.when(pokedexPokemonSpringDataJpaRepository.findByTypesType(pokemonType)).thenReturn(Collections.emptyList());

        // Act
        pokedexSearchRepository.findByType(pokemonType);
    }

    @Test
    public void testGetAllPokemom() {
        // Arrange
        List<Pokemon> expectedPokemons = Arrays.asList(
                PokemonMother.buildPokemon("Pikachu", 100, 0, 50L, 80, 60L),
                PokemonMother.buildPokemon("Charmander", 90, 0, 60L, 70, 70L)
        );
        Mockito.when(pokedexPokemonSpringDataJpaRepository.findAll()).thenReturn(expectedPokemons);

        // Act
        Iterable<Pokemon> result = pokedexSearchRepository.getAllPokemon();

        // Assert
        assertEquals(expectedPokemons, result);
    }

    @Test(expected = PokedexPokemonNotFoundException.class)
    public void testGetAllPokemomEmpty() {

        // Arrange
        Mockito.when(pokedexPokemonSpringDataJpaRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        pokedexSearchRepository.getAllPokemon();

    }

    @Test
    public void testFindDetailsByPokemon() {

        // Arrange
        String pokemonName = "Pikachu";
        PokemonDetailsDto expectedDetails = PokemonDetailsMother.buildPokemonDetails(100, 50L, 80, 60L);
        Mockito.when(pokedexPokemonSpringDataJpaRepository.getPokemonDetails(pokemonName)).thenReturn(expectedDetails);

        // Act
        PokemonDetailsDto result = pokedexSearchRepository.findDetailsByPokemon(pokemonName);

        // Assert
        assertEquals(expectedDetails, result);

    }

    @Test(expected = PokedexPokemonNotFoundException.class)
    public void testFindDetailsByPokemonNotFound() {

        // Arrange
        String pokemonName = "Pikachu";
        Mockito.when(pokedexPokemonSpringDataJpaRepository.getPokemonDetails(pokemonName)).thenReturn(null);

        // Act
        pokedexSearchRepository.findDetailsByPokemon(pokemonName);

    }

}
