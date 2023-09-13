package org.pokedex.application.controller;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.pokedex.application.dto.PokemonDetailsDto;
import org.pokedex.domain.entity.Pokemon;
import org.pokedex.domain.exception.PokedexPokemonNotFoundException;
import org.pokedex.domain.services.search.PokedexSearchService;
import org.pokedex.mother.PokemonDetailsMother;
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
@WebMvcTest(PokedexSearchController.class)
public class PokedexSearchControllerTest {


    @MockBean
    private PokedexSearchService pokedexSearchService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSearchPokemonByText() throws Exception {

        // Arrange
        String text = "Char";
        String pokemonName1 = "Charizard";
        String pokemonName2 = "Charmander";

        Pokemon expectedPokemon1 = PokemonMother.buildPokemon(pokemonName1, 100, 0, 50L, 80, 60L);
        Pokemon expectedPokemon2 = PokemonMother.buildPokemon(pokemonName2, 90, 0, 45L, 70, 55L);

        List<Pokemon> expectedPokemonList = Arrays.asList(expectedPokemon1, expectedPokemon2);

        Mockito.when(pokedexSearchService.searchPokemonByText(text)).thenReturn(expectedPokemonList);


        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/search/pokemonByText/{text}", text))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is(pokemonName1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cp", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].favourite", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].height", Matchers.is(50)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].hp", Matchers.is(80)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].weight", Matchers.is(60)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is(pokemonName2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].cp", Matchers.is(90)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].favourite", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].height", Matchers.is(45)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].hp", Matchers.is(70)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].weight", Matchers.is(55)));
    }


    @Test
    public void testSearchPokemonByName() throws Exception {

        // Arrange
        String pokemonName = "Bulbasur";
        Pokemon expectedPokemon = PokemonMother.buildPokemon(pokemonName, 100, 0, 50L, 80, 60L);
        Mockito.when(pokedexSearchService.searchPokemonByName(pokemonName)).thenReturn(expectedPokemon);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/search/pokemonByName/{pokemonName}", pokemonName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(pokemonName)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cp", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.favourite", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.height", Matchers.is(50)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hp", Matchers.is(80)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weight", Matchers.is(60)));
    }

    @Test
    public void testSearchPokemonByTextNotFound() throws Exception {

        // Arrange
        String pokemonName = "Charmander";
        Mockito.when(pokedexSearchService.searchPokemonByText(pokemonName)).thenThrow(new PokedexPokemonNotFoundException());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/search/pokemonByText/{text}", pokemonName))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testSearchPokemonByNameNotFound() throws Exception {

        // Arrange
        String pokemonName = "Charmander";
        Mockito.when(pokedexSearchService.searchPokemonByName(pokemonName)).thenThrow(new PokedexPokemonNotFoundException());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/search/pokemonByName/{pokemonName}", pokemonName))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testSearchPokemonById() throws Exception {

        // Arrange
        Long pokemonId = 1L;
        Pokemon expectedPokemon = PokemonMother.buildPokemon("Charizard", 100, 0, 50L, 80, 60L);
        Mockito.when(pokedexSearchService.searchPokemonById(pokemonId)).thenReturn(expectedPokemon);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/search/pokemonById/{pokemonId}", pokemonId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Charizard")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cp", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.favourite", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.height", Matchers.is(50)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hp", Matchers.is(80)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weight", Matchers.is(60)));
    }

    @Test
    public void testSearchPokemonByIdNotFound() throws Exception {

        // Arrange
        Long pokemonId = 1L;
        Mockito.when(pokedexSearchService.searchPokemonById(pokemonId)).thenThrow(new PokedexPokemonNotFoundException());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/search/pokemonById/{pokemonId}", pokemonId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testSearchPokemonByType() throws Exception {

        // Arrange
        String pokemonType = "Fire";
        Pokemon pokemon1 = PokemonMother.buildPokemon("Charmander", 100, 0, 50L, 80, 60L);
        Pokemon pokemon2 = PokemonMother.buildPokemon("Charizard", 90, 0, 60L, 70, 70L);
        List<Pokemon> expectedPokemons = Arrays.asList(pokemon1, pokemon2);
        Mockito.when(pokedexSearchService.searchPokemonByType(pokemonType)).thenReturn(expectedPokemons);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/search/pokemonByType/{pokemonType}", pokemonType))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Charmander")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cp", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].favourite", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].height", Matchers.is(50)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].hp", Matchers.is(80)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].weight", Matchers.is(60)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Charizard")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].cp", Matchers.is(90)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].favourite", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].height", Matchers.is(60)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].hp", Matchers.is(70)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].weight", Matchers.is(70)));

    }

    @Test
    public void testSearchPokemonByTypeNotFound() throws Exception {

        // Arrange
        String pokemonType = "Electric";
        Mockito.when(pokedexSearchService.searchPokemonByType(pokemonType)).thenThrow(new PokedexPokemonNotFoundException());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/search/pokemonByType/{pokemonType}", pokemonType))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetAllPokemon() throws Exception {

        // Arrange
        Pokemon pokemon1 = PokemonMother.buildPokemon("Charizard", 100, 0, 50L, 80, 60L);
        Pokemon pokemon2 = PokemonMother.buildPokemon("Charmander", 90, 0, 60L, 70, 70L);
        List<Pokemon> expectedPokemons = Arrays.asList(pokemon1, pokemon2);
        Mockito.when(pokedexSearchService.getAllPokemon()).thenReturn(expectedPokemons);


        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/search/getAllPokemon"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Charizard")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cp", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].favourite", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].weight", Matchers.is(60)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].hp", Matchers.is(80)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].height", Matchers.is(50)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Charmander")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].cp", Matchers.is(90)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].favourite", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].height", Matchers.is(60)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].hp", Matchers.is(70)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].weight", Matchers.is(70)));

    }

    @Test
    public void testGetAllPokemonNotFound() throws Exception {

        // Arrange
        Mockito.when(pokedexSearchService.getAllPokemon()).thenThrow(new PokedexPokemonNotFoundException());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/search/getAllPokemon"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void testGetPokemonDetails() throws Exception {

        // Arrange
        String pokemonName = "Pikachu";
        PokemonDetailsDto expectedDetails = PokemonDetailsMother.buildPokemonDetails(100, 60L, 80, 50L);
        Mockito.when(pokedexSearchService.searchPokemonDetails(pokemonName)).thenReturn(expectedDetails);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/search/getPokemonDetails/{pokemonName}", pokemonName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cp", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.height", Matchers.is(60)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hp", Matchers.is(80)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weight", Matchers.is(50)));

    }

    @Test
    public void testGetPokemonDetailsNotFound() throws Exception {

        // Arrange
        String pokemonName = "MissingNo";
        Mockito.when(pokedexSearchService.searchPokemonDetails(pokemonName)).thenThrow(new PokedexPokemonNotFoundException());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/search/getPokemonDetails/{pokemonName}", pokemonName))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }
}
