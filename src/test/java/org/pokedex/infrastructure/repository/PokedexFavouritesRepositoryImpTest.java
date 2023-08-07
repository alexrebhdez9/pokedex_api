package org.pokedex.infrastructure.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.pokedex.domain.entity.Pokemon;
import org.pokedex.domain.exception.PokedexPokemonNotFoundException;
import org.pokedex.infrastructure.repository.jpa.PokedexPokemonSpringDataJpaRepository;
import org.pokedex.mother.PokemonMother;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PokedexFavouritesRepositoryImpTest {

    @Mock
    private PokedexPokemonSpringDataJpaRepository pokedexPokemonSpringDataJpaRepository;

    @InjectMocks
    private PokedexFavouritesRepositoryImp pokedexFavouritesRepository;

    @Test
    public void testAddFavourite() {

        // Arrange
        Pokemon pokemon = PokemonMother.buildPokemon("Pikachu", 100, 0, 50L, 80, 60L);

        // Act
        pokedexFavouritesRepository.addFavourite(pokemon);

        // Assert
        assertEquals(1, pokemon.getFavourite());
        Mockito.verify(pokedexPokemonSpringDataJpaRepository, Mockito.times(1)).save(pokemon);
    }

    @Test
    public void testRemoveFavourity() {

        // Arrange
        Pokemon pokemon = PokemonMother.buildPokemon("Pikachu", 100, 1, 50L, 80, 60L);

        // Act
        pokedexFavouritesRepository.removeFavourity(pokemon);

        // Assert
        assertEquals(0, pokemon.getFavourite());
        Mockito.verify(pokedexPokemonSpringDataJpaRepository, Mockito.times(1)).save(pokemon);
    }

    @Test
    public void testGetAllFavourites() {

        // Arrange
        Pokemon pokemon1 = PokemonMother.buildPokemon("Bulbasur", 100, 1, 50L, 80, 60L);
        Pokemon pokemon2 = PokemonMother.buildPokemon("Charmander", 90, 1, 60L, 70, 70L);
        List<Pokemon> expectedPokemons = Arrays.asList(pokemon1, pokemon2);
        Mockito.when(pokedexPokemonSpringDataJpaRepository.findByFavourite(1)).thenReturn(expectedPokemons);

        // Act
        Iterable<Pokemon> result = pokedexFavouritesRepository.getAllFavourites();

        // Assert
        assertEquals(expectedPokemons, result);
        Mockito.verify(pokedexPokemonSpringDataJpaRepository, Mockito.times(1)).findByFavourite(1);
    }

    @Test(expected = PokedexPokemonNotFoundException.class)
    public void testGetAllFavouritesEmpty() {
        // Arrange
        Mockito.when(pokedexPokemonSpringDataJpaRepository.findByFavourite(1)).thenReturn(Collections.emptyList());

        // Act
        pokedexFavouritesRepository.getAllFavourites();
    }

}
