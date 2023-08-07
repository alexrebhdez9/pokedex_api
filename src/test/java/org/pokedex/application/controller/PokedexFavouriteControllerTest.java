package org.pokedex.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.pokedex.domain.entity.Pokemon;
import org.pokedex.domain.services.favourites.PokedexFavouritesService;
import org.pokedex.mother.PokemonMother;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(PokedexFavouriteController.class)
public class PokedexFavouriteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokedexFavouritesService pokedexFavouritesService;

    @Test
    public void testAddFavourite() throws Exception {

        // Arrange
        String pokemonName = "Bulbasur";
        Pokemon expectedPokemon = PokemonMother.buildPokemon(pokemonName, 100, 0, 50L, 80, 60L);
        Mockito.when(pokedexFavouritesService.addPokemonFavourite(pokemonName)).thenReturn(expectedPokemon);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/favourite/add/{pokemonName}", pokemonName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(expectedPokemon)));
    }

    @Test
    public void testQuitFavourite() throws Exception {

        // Arrange
        String pokemonName = "Bulbasur";
        Pokemon expectedPokemon = PokemonMother.buildPokemon(pokemonName, 100, 1, 50L, 80, 60L);
        Mockito.when(pokedexFavouritesService.removePokemonFavourite(pokemonName)).thenReturn(expectedPokemon);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/favourite/remove/{pokemonName}", pokemonName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(expectedPokemon)));
    }

    @Test
    public void testGetFavourites() throws Exception {

        // Arrange
        Pokemon pokemon1 = PokemonMother.buildPokemon("Bulbasur", 100, 1, 50L, 80, 60L);
        Pokemon pokemon2 = PokemonMother.buildPokemon("Charmander", 90, 1, 60L, 70, 70L);
        List<Pokemon> expectedPokemons = Arrays.asList(pokemon1, pokemon2);

        Mockito.when(pokedexFavouritesService.getAllFavourites()).thenReturn(expectedPokemons);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/favourite/getFavourites"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(expectedPokemons)));

    }

    // Helper method to convert objects to JSON string
    private String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
