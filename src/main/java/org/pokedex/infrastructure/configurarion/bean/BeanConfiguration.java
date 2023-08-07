package org.pokedex.infrastructure.configurarion.bean;

import org.pokedex.domain.repository.PokedexFavouritesRepository;
import org.pokedex.domain.repository.PokedexSearchRepository;
import org.pokedex.domain.services.favourites.PokedexFavouritesService;
import org.pokedex.domain.services.favourites.PokedexFavouritesServiceImp;
import org.pokedex.domain.services.search.PokedexSearchService;
import org.pokedex.domain.services.search.PokedexSearchServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    PokedexSearchService pokedexSearchService(PokedexSearchRepository pokedexSearchRepository) {

        return new PokedexSearchServiceImp(pokedexSearchRepository);

    }

    @Bean
    PokedexFavouritesService pokedexFavouritesService(PokedexFavouritesRepository pokedexFavouritesRepository, PokedexSearchRepository pokedexSearchRepository) {

        return new PokedexFavouritesServiceImp(pokedexFavouritesRepository, pokedexSearchRepository);

    }

}