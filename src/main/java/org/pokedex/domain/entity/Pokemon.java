package org.pokedex.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "pokemon")
@Data
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Pokemon {

    @Id
    @GeneratedValue
    @Column(name = "pokemon_id", nullable = false)
    private Long id;

    @Column(name = "pokemon_cp", nullable = false)
    private Integer cp;

    @Column(name = "pokemon_favourite", nullable = false)
    private Integer favourite;

    @Column(name = "pokemon_height", nullable = false)
    private Long height;

    @Column(name = "pokemon_hp", nullable = false)
    private Integer hp;

    @Column(name = "pokemon_name", nullable = false)
    private String name;

    @Column(name = "pokemon_weight", nullable = false)
    private Long weight;

    @OneToMany(mappedBy = "pokemon")
    @JsonIgnore
    private List<PokemonType> types;

}