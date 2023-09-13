package org.pokedex.infrastructure.springdata.config;

import org.pokedex.domain.entity.PokemonType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PokedexPokemonTypeSpringDataJpaRepository extends CrudRepository<PokemonType, Long> {


}

