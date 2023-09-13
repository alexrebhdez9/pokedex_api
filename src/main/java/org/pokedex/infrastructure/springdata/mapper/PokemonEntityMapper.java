package org.pokedex.infrastructure.springdata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.pokedex.domain.entity.Pokemon;
import org.pokedex.infrastructure.springdata.dbo.PokemonEntity;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PokemonEntityMapper {

    Pokemon toDomain (PokemonEntity pokemonEntity);

    @Mapping(target = "types", ignore = true)
    PokemonEntity toDbo (Pokemon pokemon);

}
