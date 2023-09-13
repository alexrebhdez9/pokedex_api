package org.pokedex.infrastructure.rest.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pokedex.domain.entity.Pokemon;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonTypeDto {

    private Long id;

    private String type;

    private Pokemon pokemon;

}
