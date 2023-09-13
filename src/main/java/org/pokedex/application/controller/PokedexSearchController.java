package org.pokedex.application.controller;

import org.pokedex.application.dto.PokemonDetailsDto;
import org.pokedex.domain.exception.PokedexPokemonNotFoundException;
import org.pokedex.infrastructure.services.search.PokedexSearchService;
import org.pokedex.infrastructure.configurarion.aspects.PokedexLoggingAspect;
import org.pokedex.infrastructure.rest.spring.dto.PokemonDto;
import org.pokedex.infrastructure.rest.spring.mapper.PokemonMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RestController
@RequestMapping(value = "/v1/search")
@ComponentScan("org.pokedex.domain.services")
public class PokedexSearchController {

    private PokedexSearchService pokedexSearchService;

    private PokemonMapper pokemonMapper;


    public PokedexSearchController(PokedexSearchService pokedexSearchService, PokemonMapper pokemonMapper) {
        this.pokedexSearchService = pokedexSearchService;
        this.pokemonMapper = pokemonMapper;
    }

    /**
     * Retrieve a Pokemon by name
     * @param pokemonName
     * @return
     * @throws PokedexPokemonNotFoundException
     */
    @GetMapping(value = {"/pokemonByName/{pokemonName}"}, produces = "application/json; charset=utf-8")
    @PokedexLoggingAspect
    PokemonDto searchPokemonByName(@PathVariable String pokemonName) throws PokedexPokemonNotFoundException {

        return pokemonMapper.toPokemonDto(pokedexSearchService.searchPokemonByName(pokemonName));

    }

    /**
     * Retrieve all Pokemons that contains scpecific text
     * @param text
     * @return
     * @throws PokedexPokemonNotFoundException
     */
    @GetMapping(value = {"/pokemonByText/{text}"}, produces = "application/json; charset=utf-8")
    @PokedexLoggingAspect
    List<PokemonDto> searchPokemonByText(@PathVariable String text) throws PokedexPokemonNotFoundException {

        return pokedexSearchService.searchPokemonByText(text).stream()
                .map(pokemonMapper::toPokemonDto)
                .collect(Collectors.toList());

    }

    /**
     * Retrieve a Pokemon by Id
     * @param pokemonId
     * @return
     * @throws PokedexPokemonNotFoundException
     */
    @GetMapping(value = {"/pokemonById/{pokemonId}"}, produces = "application/json; charset=utf-8")
    @PokedexLoggingAspect
    PokemonDto searchPokemonById(@PathVariable Long pokemonId) throws PokedexPokemonNotFoundException {

            return pokemonMapper.toPokemonDto(pokedexSearchService.searchPokemonById(pokemonId));

    }


    /**
     * Retrieve all Pokemons with specific type
     * @param pokemonType
     * @return
     * @throws PokedexPokemonNotFoundException
     */
    @GetMapping(value = {"/pokemonByType/{pokemonType}"}, produces = "application/json; charset=utf-8")
    @PokedexLoggingAspect
    List<PokemonDto> searchPokemonByType(@PathVariable String pokemonType) throws PokedexPokemonNotFoundException  {

            return pokedexSearchService.searchPokemonByType(pokemonType).stream()
                    .map(pokemonMapper::toPokemonDto)
                    .collect(Collectors.toList());

    }


    /**
     * Retrieve all Pokemon
     * @return
     * @throws PokedexPokemonNotFoundException
     */
    @GetMapping(value = {"/getAllPokemon"}, produces = "application/json; charset=utf-8")
    @PokedexLoggingAspect
    List<PokemonDto> getAllPokemon() throws PokedexPokemonNotFoundException {

        return pokedexSearchService.getAllPokemon().stream()
                .map(pokemonMapper::toPokemonDto)
                .collect(Collectors.toList());

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
