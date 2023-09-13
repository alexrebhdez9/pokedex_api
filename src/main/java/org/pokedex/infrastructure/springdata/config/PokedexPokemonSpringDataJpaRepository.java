package org.pokedex.infrastructure.springdata.config;

import org.pokedex.application.dto.PokemonDetailsDto;
import org.pokedex.domain.entity.Pokemon;
import org.pokedex.infrastructure.springdata.model.PokemonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PokedexPokemonSpringDataJpaRepository extends CrudRepository<PokemonEntity, Long> {

    Pokemon findByName(String pokemonName);

    List<Pokemon> findPokemonByNameContains(String name);

    List<Pokemon> findByTypesType(String type);

    List<Pokemon> findByFavourite(Integer favourite);

    @Query("select new org.pokedex.application.dto.PokemonDetailsDto(p.cp, p.height, p.hp, p.weight) from Pokemon p where p.name = :pokemonName")
    PokemonDetailsDto getPokemonDetails(@Param("pokemonName") String pokemonName);

}

