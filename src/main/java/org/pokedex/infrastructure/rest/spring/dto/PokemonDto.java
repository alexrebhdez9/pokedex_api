package org.pokedex.infrastructure.rest.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pokedex.domain.entity.PokemonType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonDto {

    private Long id;

    private Integer cp;

    private Integer favourite;

    private Long height;

    private Integer hp;

    private String name;

    private Long weight;

    private List<PokemonType> types;

}
