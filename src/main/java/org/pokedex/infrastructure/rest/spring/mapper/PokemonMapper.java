package org.pokedex.infrastructure.rest.spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.pokedex.domain.entity.Pokemon;
import org.pokedex.infrastructure.rest.spring.dto.PokemonDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PokemonMapper {

    PokemonDto toPokemonDto (Pokemon pokemon);

    Pokemon toPokemonDomain (PokemonDto pokemonDto);

}
