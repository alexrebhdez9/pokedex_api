package org.pokedex.domain.services.favourites;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.pokedex.application.dto.PokemonDetailsDto;
import org.pokedex.domain.entity.Pokemon;
import org.pokedex.domain.exception.PokedexPokemonNotFoundException;
import org.pokedex.domain.repository.PokedexSearchRepository;
import org.pokedex.domain.services.search.PokedexSearchServiceImp;
import org.pokedex.mother.PokemonDetailsMother;
import org.pokedex.mother.PokemonMother;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PokedexSearchServiceImpTest {

    @Mock
    private PokedexSearchRepository pokedexSearchRepository;

    @InjectMocks
    private PokedexSearchServiceImp pokedexSearchService;

    @Test
    public void testSearchPokemonByName() {

        // Arrange
        String pokemonName = "Pikachu";
        Pokemon expectedPokemon = PokemonMother.buildPokemon(pokemonName, 100, 0, 50L, 80, 60L);
        when(pokedexSearchRepository.findByName(pokemonName)).thenReturn(expectedPokemon);

        // Act
        Pokemon result = pokedexSearchService.searchPokemonByName(pokemonName);

        // Assert
        assertEquals(expectedPokemon, result);
    }

    @Test(expected = RuntimeException.class)
    public void testSearchPokemonByNameNotFound() {

        // Arrange
        String pokemonName = "Pikachu";
        when(pokedexSearchRepository.findByName(pokemonName)).thenReturn(null);

        // Act
        pokedexSearchService.searchPokemonByName(pokemonName);
    }

    @Test
    public void testSearchPokemonById() {

        // Arrange
        Long pokemonId = 1L;
        Pokemon expectedPokemon = PokemonMother.buildPokemon("Pikachu", 100, 0, 50L, 80, 60L);
        when(pokedexSearchRepository.findById(pokemonId)).thenReturn(expectedPokemon);

        // Act
        Pokemon result = pokedexSearchService.searchPokemonById(pokemonId);

        // Assert
        assertEquals(expectedPokemon, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void testSearchPokemonByIdNotFound() {

        // Arrange
        Long pokemonId = 1L;
        when(pokedexSearchRepository.findById(pokemonId)).thenReturn(null);

        // Act
        pokedexSearchService.searchPokemonById(pokemonId);
    }

    @Test
    public void testSearchPokemonByType() {

        // Arrange
        String pokemonType = "Fire";
        List<Pokemon> expectedPokemons = Arrays.asList(
                PokemonMother.buildPokemon("Charmander", 100, 0, 50L, 80, 60L),
                PokemonMother.buildPokemon("Charizard", 120, 0, 70L, 90, 80L)
        );
        when(pokedexSearchRepository.findByType(pokemonType)).thenReturn(expectedPokemons);

        // Act
        List<Pokemon> result = pokedexSearchService.searchPokemonByType(pokemonType);

        // Assert
        assertEquals(expectedPokemons, result);
    }

    @Test(expected = PokedexPokemonNotFoundException.class)
    public void testSearchPokemonByTypeNotFound() {

        // Arrange
        String pokemonType = "Fire";
        when(pokedexSearchRepository.findByType(pokemonType)).thenReturn(Collections.emptyList());

        // Act
        pokedexSearchService.searchPokemonByType(pokemonType);
    }

    @Test
    public void testGetAllPokemon() {

        // Arrange
        Pokemon pokemon1 = PokemonMother.buildPokemon("Pikachu", 100, 0, 50L, 80, 60L);
        Pokemon pokemon2 = PokemonMother.buildPokemon("Charmander", 90, 0, 60L, 70, 70L);

        Iterable<Pokemon> expectedPokemons = Arrays.asList(pokemon1, pokemon2);
        when(pokedexSearchRepository.getAllPokemon()).thenReturn(expectedPokemons);

        // Act
        List<Pokemon> result = pokedexSearchService.getAllPokemon();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(pokemon1));
        assertTrue(result.contains(pokemon2));
    }

    @Test(expected = PokedexPokemonNotFoundException.class)
    public void testGetAllPokemonNotFound() {

        // Arrange
        when(pokedexSearchRepository.getAllPokemon()).thenReturn(Collections.emptyList());

        // Act
        pokedexSearchService.getAllPokemon();

    }

    @Test
    public void testSearchPokemonDetails() {

        // Arrange
        String pokemonName = "Pikachu";
        PokemonDetailsDto expectedDetails = PokemonDetailsMother.buildPokemonDetails(100, 50L, 80, 60L);
        when(pokedexSearchRepository.findDetailsByPokemon(pokemonName)).thenReturn(expectedDetails);

        // Act
        PokemonDetailsDto result = pokedexSearchService.searchPokemonDetails(pokemonName);

        // Assert
        assertEquals(expectedDetails, result);
    }

    @Test(expected = PokedexPokemonNotFoundException.class)
    public void testSearchPokemonDetailsNotFound() {

        // Arrange
        String pokemonName = "Pikachu";
        when(pokedexSearchRepository.findDetailsByPokemon(pokemonName)).thenReturn(null);

        // Act
        pokedexSearchService.searchPokemonDetails(pokemonName);
    }

}
