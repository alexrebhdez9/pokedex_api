package org.pokedex.mother;

import org.pokedex.domain.entity.Pokemon;

public class PokemonMother {
    public static Pokemon buildPokemon(String name, Integer cp, Integer favourite, Long height, Integer hp, Long weight) {

        Pokemon pokemon = new Pokemon();

        pokemon.setName(name);

        pokemon.setCp(cp);

        pokemon.setFavourite(favourite);

        pokemon.setHeight(height);

        pokemon.setHp(hp);

        pokemon.setWeight(weight);

        return pokemon;
    }
}
