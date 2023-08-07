package org.pokedex.application.controller;

import org.pokedex.application.dto.PokemonDetailsDto;
import org.pokedex.domain.entity.Pokemon;
import org.pokedex.domain.exception.PokedexPokemonNotFoundException;
import org.pokedex.domain.services.search.PokedexSearchService;
import org.pokedex.infrastructure.configurarion.aspects.PokedexLoggingAspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Component
@RestController
@RequestMapping(value = "/v1/search")
public class PokedexSearchController {

    private PokedexSearchService pokedexSearchService;

    public PokedexSearchController(PokedexSearchService pokedexSearchService) {
        this.pokedexSearchService = pokedexSearchService;
    }


    /**
     * Retrieve a Pokemon by name
     * @param pokemonName
     * @return
     * @throws PokedexPokemonNotFoundException
     */
    @GetMapping(value = {"/pokemonByName/{pokemonName}"}, produces = "application/json; charset=utf-8")
    @PokedexLoggingAspect
    Pokemon searchPokemonByName(@PathVariable String pokemonName) throws PokedexPokemonNotFoundException {

        return pokedexSearchService.searchPokemonByName(pokemonName);

    }

    /**
     * Retrieve all Pokemons that contains scpecific text
     * @param text
     * @return
     * @throws PokedexPokemonNotFoundException
     */
    @GetMapping(value = {"/pokemonByText/{text}"}, produces = "application/json; charset=utf-8")
    @PokedexLoggingAspect
    List<Pokemon> searchPokemonByText(@PathVariable String text) throws PokedexPokemonNotFoundException {

        return pokedexSearchService.searchPokemonByText(text);

    }

    /**
     * Retrieve a Pokemon by Id
     * @param pokemonId
     * @return
     * @throws PokedexPokemonNotFoundException
     */
    @GetMapping(value = {"/pokemonById/{pokemonId}"}, produces = "application/json; charset=utf-8")
    @PokedexLoggingAspect
    Pokemon searchPokemonById(@PathVariable Long pokemonId) throws PokedexPokemonNotFoundException {

            return pokedexSearchService.searchPokemonById(pokemonId);

    }


    /**
     * Retrieve all Pokemons with specific type
     * @param pokemonType
     * @return
     * @throws PokedexPokemonNotFoundException
     */
    @GetMapping(value = {"/pokemonByType/{pokemonType}"}, produces = "application/json; charset=utf-8")
    @PokedexLoggingAspect
    List<Pokemon> searchPokemonByType(@PathVariable String pokemonType) throws PokedexPokemonNotFoundException  {

            return pokedexSearchService.searchPokemonByType(pokemonType);

    }


    /**
     * Retrieve all Pokemon
     * @return
     * @throws PokedexPokemonNotFoundException
     */
    @GetMapping(value = {"/getAllPokemon"}, produces = "application/json; charset=utf-8")
    @PokedexLoggingAspect
    List<Pokemon> getAllPokemon() throws PokedexPokemonNotFoundException {

        return pokedexSearchService.getAllPokemon();

    }


    /**
     * Retrieve a Pokemon using the name
     * @param pokemonName
     * @return
     * @throws PokedexPokemonNotFoundException
     */
    @GetMapping(value = {"/getPokemonDetails/{pokemonName}"}, produces = "application/json; charset=utf-8")
    @PokedexLoggingAspect
    PokemonDetailsDto getPokemonDetails(@PathVariable String pokemonName) throws PokedexPokemonNotFoundException  {

        return pokedexSearchService.searchPokemonDetails(pokemonName);

    }

}
