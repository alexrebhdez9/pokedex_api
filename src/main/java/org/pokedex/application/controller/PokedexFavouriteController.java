package org.pokedex.application.controller;

import org.pokedex.domain.entity.Pokemon;
import org.pokedex.domain.exception.PokedexAddFavouritesException;
import org.pokedex.domain.exception.PokedexRemoveFavouritesException;
import org.pokedex.domain.services.favourites.PokedexFavouritesService;
import org.pokedex.infrastructure.configurarion.aspects.PokedexLoggingAspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Component
@RestController
@RequestMapping(value = "/v1/favourite")
public class PokedexFavouriteController {

    private PokedexFavouritesService pokedexFavouritesService;

    public PokedexFavouriteController(PokedexFavouritesService pokedexFavouritesService) {
        this.pokedexFavouritesService = pokedexFavouritesService;
    }

    /**
     * Add a Pokemon to favourites
     * @param pokemonName
     * @return
     * @throws PokedexAddFavouritesException
     */
    @GetMapping(value = {"/add/{pokemonName}"}, produces = "application/json; charset=utf-8")
    @PokedexLoggingAspect
    Pokemon addFavourite(@PathVariable String pokemonName) throws PokedexAddFavouritesException {

        return pokedexFavouritesService.addPokemonFavourite(pokemonName);

    }


    /**
     * Delete a Pokemon from favourites
     * @param pokemonName
     * @return
     * @throws PokedexRemoveFavouritesException
     */
    @GetMapping(value = {"/remove/{pokemonName}"}, produces = "application/json; charset=utf-8")
    @PokedexLoggingAspect
    Pokemon quitFavourite(@PathVariable String pokemonName) throws PokedexRemoveFavouritesException {

        return pokedexFavouritesService.removePokemonFavourite(pokemonName);

    }

    /**
     * Retrieve all favourites Pokemons
     * @return
     * @throws PokedexRemoveFavouritesException
     */
    @GetMapping(value = {"/getFavourites"}, produces = "application/json; charset=utf-8")
    @PokedexLoggingAspect
    List<Pokemon> getFavourites() throws PokedexRemoveFavouritesException {

        return pokedexFavouritesService.getAllFavourites();

    }
}
