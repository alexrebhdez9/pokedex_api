package org.pokedex.domain.services.favourites;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.pokedex.domain.entity.Pokemon;
import org.pokedex.domain.exception.PokedexAddFavouritesException;
import org.pokedex.domain.repository.PokedexFavouritesRepository;
import org.pokedex.domain.repository.PokedexSearchRepository;
import org.pokedex.infrastructure.springdata.dbo.PokemonEntity;
import org.pokedex.infrastructure.springdata.mapper.PokemonEntityMapper;
import org.pokedex.mother.PokemonMother;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class PokedexFavouritesServiceImpTest {

    @Mock
    private PokedexFavouritesRepository pokedexFavouritesRepository;

    @Mock
    private PokedexSearchRepository pokedexSearchRepository;

    @InjectMocks
    private PokedexFavouritesServiceImp pokedexFavouritesService;

    private PokemonEntityMapper pokemonEntityMapper;

    @Test
    public void testAddPokemonFavourite() {

        // Arrange
        String pokemonName = "Bulbasur";
        Pokemon expectedPokemon = PokemonMother.buildPokemon(pokemonName, 100, 0, 50L, 80, 60L);
        Mockito.when(pokedexSearchRepository.findByName(pokemonName)).thenReturn(expectedPokemon);

        // Act
        Pokemon result = pokedexFavouritesService.addPokemonFavourite(pokemonName);

        // Assert
        assertEquals(expectedPokemon, result);
        Mockito.verify(pokedexFavouritesRepository, Mockito.times(1)).addFavourite(expectedPokemon);
    }

    @Test
    public void testRemovePokemonFavourite() {

        // Arrange
        String pokemonName = "Bulbasur";
        Pokemon expectedPokemon = PokemonMother.buildPokemon(pokemonName, 100, 1, 50L, 80, 60L);
        Mockito.when(pokedexSearchRepository.findByName(pokemonName)).thenReturn(expectedPokemon);

        // Act
        Pokemon result = pokedexFavouritesService.removePokemonFavourite(pokemonName);

        // Assert
        assertEquals(expectedPokemon, result);
        Mockito.verify(pokedexFavouritesRepository, Mockito.times(1)).removeFavourity(expectedPokemon);
    }

    @Test
    public void testGetAllFavourites() {

        // Arrange
        Pokemon pokemon1 = PokemonMother.buildPokemon("Bulbasur", 100, 1, 50L, 80, 60L);
        Pokemon pokemon2 = PokemonMother.buildPokemon("Charmander", 90, 1, 60L, 70, 70L);
        Iterable<Pokemon> expectedPokemons = Arrays.asList(pokemon1, pokemon2);
        Mockito.when(pokedexFavouritesRepository.getAllFavourites()).thenReturn(expectedPokemons);

        // Act
        List<Pokemon> result = pokedexFavouritesService.getAllFavourites();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(pokemon1));
        assertTrue(result.contains(pokemon2));
    }

    @Test(expected = PokedexAddFavouritesException.class)
    public void testAddPokemonFavouriteNotFound() {

        // Arrange
        String pokemonName = "Pikachu";
        Mockito.when(pokedexSearchRepository.findByName(pokemonName)).thenReturn(null);

        // Act
        pokedexFavouritesService.addPokemonFavourite(pokemonName);

    }

}
