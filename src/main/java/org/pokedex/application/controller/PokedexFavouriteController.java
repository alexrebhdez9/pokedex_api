package org.pokedex.application.controller;

import org.pokedex.domain.exception.PokedexAddFavouritesException;
import org.pokedex.domain.exception.PokedexRemoveFavouritesException;
import org.pokedex.infrastructure.services.favourites.PokedexFavouritesService;
import org.pokedex.infrastructure.configurarion.aspects.PokedexLoggingAspect;
import org.pokedex.infrastructure.rest.spring.dto.PokemonDto;
import org.pokedex.infrastructure.rest.spring.mapper.PokemonMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RestController
@RequestMapping(value = "/v1/favourite")
public class PokedexFavouriteController {

    private final PokedexFavouritesService pokedexFavouritesService;

    private PokemonMapper pokemonMapper;

    public PokedexFavouriteController(PokedexFavouritesService pokedexFavouritesService, PokemonMapper pokemonMapper) {
        this.pokedexFavouritesService = pokedexFavouritesService;
        this.pokemonMapper = pokemonMapper;
    }

    /**
     * Add a Pokemon to favourites
     * @param pokemonName: Pokemon Name
     * @return Json
     * @throws PokedexAddFavouritesException PokedexAddFavouritesException
     */
    @GetMapping(value = {"/add/{pokemonName}"}, produces = "application/json; charset=utf-8")
    @PokedexLoggingAspect
    PokemonDto addFavourite(@PathVariable String pokemonName) throws PokedexAddFavouritesException {

        return pokemonMapper.toPokemonDto(pokedexFavouritesService.addPokemonFavourite(pokemonName));

    }


    /**
     * Delete a Pokémon from favourites
     * @param pokemonName pokemon Name
     * @return Json
     * @throws PokedexRemoveFavouritesException PokedexRemoveFavouritesException
     */
    @GetMapping(value = {"/remove/{pokemonName}"}, produces = "application/json; charset=utf-8")
    @PokedexLoggingAspect
    PokemonDto quitFavourite(@PathVariable String pokemonName) throws PokedexRemoveFavouritesException {

        return pokemonMapper.toPokemonDto(pokedexFavouritesService.removePokemonFavourite(pokemonName));

    }

    /**
     * Retrieve all favourites Pokemons
     * @return Json
     * @throws PokedexRemoveFavouritesException PokedexRemoveFavouritesException
     */
    @GetMapping(value = {"/getFavourites"}, produces = "application/json; charset=utf-8")
    @PokedexLoggingAspect
    List<PokemonDto> getFavourites() throws PokedexRemoveFavouritesException {

        return pokedexFavouritesService.getAllFavourites().stream()
                .map(pokemonMapper::toPokemonDto)
                .collect(Collectors.toList());

    }
}
