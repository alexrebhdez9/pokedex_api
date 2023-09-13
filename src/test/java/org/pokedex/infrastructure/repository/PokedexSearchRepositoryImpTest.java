package org.pokedex.infrastructure.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.pokedex.application.dto.PokemonDetailsDto;
import org.pokedex.domain.entity.Pokemon;
import org.pokedex.domain.exception.PokedexPokemonNotFoundException;
import org.pokedex.infrastructure.springdata.config.PokedexPokemonSpringDataJpaRepository;
import org.pokedex.infrastructure.springdata.dbo.PokemonEntity;
import org.pokedex.infrastructure.springdata.mapper.PokemonEntityMapper;
import org.pokedex.infrastructure.springdata.repository.PokedexSearchRepositoryImp;
import org.pokedex.mother.PokemonDetailsMother;
import org.pokedex.mother.PokemonMother;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PokedexSearchRepositoryImpTest {

    @Mock
    private PokedexPokemonSpringDataJpaRepository pokedexPokemonSpringDataJpaRepository;

    @InjectMocks
    private PokedexSearchRepositoryImp pokedexSearchRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private Mapper mapper;

    private PokemonEntityMapper pokemonEntityMapper;

    @Test
    public void testFindById() {
        // Arrange
        Long pokemonId = 1L;
        Pokemon pokemon = PokemonMother.buildPokemon("Pikachu", 100, 0, 50L, 80, 60L);

        PokemonEntity expectedPokemon = modelMapper.map(pokemon, PokemonEntity.class);
        when(pokedexPokemonSpringDataJpaRepository.findById(pokemonId)).thenReturn(Optional.ofNullable(expectedPokemon));

        // Act
        Pokemon result = pokedexSearchRepository.findById(pokemonId);

        // Assert
//        assertTrue(result.isPresent());
        assertEquals(pokemonEntityMapper.toDomain(expectedPokemon), result);
    }

    @Test(expected = PokedexPokemonNotFoundException.class)
    public void testFindByIdNotFound() {

        // Arrange
        Long pokemonId = 1L;
        when(pokedexPokemonSpringDataJpaRepository.findById(pokemonId)).thenReturn(Optional.empty());

        // Act
        pokedexSearchRepository.findById(pokemonId);
    }

    @Test
    public void testFindByName() {
        // Arrange
        String pokemonName = "Pikachu";
        Pokemon expectedPokemon = PokemonMother.buildPokemon(pokemonName, 100, 0, 50L, 80, 60L);
        when(pokedexPokemonSpringDataJpaRepository.findByName(pokemonName)).thenReturn(expectedPokemon);

        // Act
        Pokemon result = pokedexSearchRepository.findByName(pokemonName);

        // Assert
        assertEquals(expectedPokemon, result);
    }

    @Test(expected = PokedexPokemonNotFoundException.class)
    public void testFindByNameNotFound() {
        // Arrange
        String pokemonName = "Pikachu";
        when(pokedexPokemonSpringDataJpaRepository.findByName(pokemonName)).thenReturn(null);

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
        when(pokedexPokemonSpringDataJpaRepository.findByTypesType(pokemonType)).thenReturn(expectedPokemons);

        // Act
        List<Pokemon> result = pokedexSearchRepository.findByType(pokemonType);

        // Assert
        assertEquals(expectedPokemons, result);
    }

    @Test(expected = PokedexPokemonNotFoundException.class)
    public void testFindByTypeNotFound() {
        // Arrange
        String pokemonType = "Fire";
        when(pokedexPokemonSpringDataJpaRepository.findByTypesType(pokemonType)).thenReturn(Collections.emptyList());

        // Act
        pokedexSearchRepository.findByType(pokemonType);
    }

    @Test
    public void testGetAllPokemom() {
        // Arrange
        List<PokemonEntity> expectedPokemons = Arrays.asList(
                pokemonEntityMapper.toDbo(PokemonMother.buildPokemon("Pikachu", 100, 0, 50L, 80, 60L)),
                pokemonEntityMapper.toDbo(PokemonMother.buildPokemon("Charmander", 90, 0, 60L, 70, 70L))
        );
        when(pokedexPokemonSpringDataJpaRepository.findAll()).thenReturn(expectedPokemons);

        // Act
        Iterable<Pokemon> result = pokedexSearchRepository.getAllPokemon();

        // Assert
        assertEquals(expectedPokemons, StreamSupport
                .stream(result.spliterator(), true)
                .map(pokemon -> pokemonEntityMapper.toDbo(pokemon))
                .collect(Collectors.toList()));
    }

    @Test(expected = PokedexPokemonNotFoundException.class)
    public void testGetAllPokemomEmpty() {

        // Arrange
        when(pokedexPokemonSpringDataJpaRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        pokedexSearchRepository.getAllPokemon();

    }

    @Test
    public void testFindDetailsByPokemon() {

        // Arrange
        String pokemonName = "Pikachu";
        PokemonDetailsDto expectedDetails = PokemonDetailsMother.buildPokemonDetails(100, 50L, 80, 60L);
        when(pokedexPokemonSpringDataJpaRepository.getPokemonDetails(pokemonName)).thenReturn(expectedDetails);

        // Act
        PokemonDetailsDto result = pokedexSearchRepository.findDetailsByPokemon(pokemonName);

        // Assert
        assertEquals(expectedDetails, result);

    }

    @Test(expected = PokedexPokemonNotFoundException.class)
    public void testFindDetailsByPokemonNotFound() {

        // Arrange
        String pokemonName = "Pikachu";
        when(pokedexPokemonSpringDataJpaRepository.getPokemonDetails(pokemonName)).thenReturn(null);

        // Act
        pokedexSearchRepository.findDetailsByPokemon(pokemonName);

    }

}
