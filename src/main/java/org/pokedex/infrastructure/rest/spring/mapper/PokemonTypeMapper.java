package org.pokedex.infrastructure.rest.spring.mapper;

import org.mapstruct.Mapper;
import org.pokedex.domain.entity.PokemonType;
import org.pokedex.infrastructure.rest.spring.dto.PokemonTypeDto;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface PokemonTypeMapper {

    PokemonType toPokemonTypeDto (PokemonType pokemonType);

    PokemonTypeDto toPokemonTypeDomain (PokemonTypeDto pokemonTypeDto);


}
