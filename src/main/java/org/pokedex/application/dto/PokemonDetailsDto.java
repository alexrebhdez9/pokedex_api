package org.pokedex.application.dto;

import lombok.*;

@Data
@Getter
@Setter
public class PokemonDetailsDto {

    private Integer cp;

    private Long height;

    private Integer hp;

    private Long weight;

    public PokemonDetailsDto(Integer cp, Long height, Integer hp, Long weight) {
        this.cp = cp;
        this.height = height;
        this.hp = hp;
        this.weight = weight;
    }

}