package org.pokedex.infrastructure.repository.jpa;

import org.pokedex.domain.entity.PokemonType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PokedexPokemonTypeSpringDataJpaRepository extends CrudRepository<PokemonType, Long> {


}

