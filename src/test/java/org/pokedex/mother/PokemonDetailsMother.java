package org.pokedex.mother;

import org.pokedex.application.dto.PokemonDetailsDto;

public class PokemonDetailsMother {
    public static PokemonDetailsDto buildPokemonDetails(Integer cp, Long height, Integer hp, Long weight) {

        return new PokemonDetailsDto(cp, height, hp, weight);

    }
}
