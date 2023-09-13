package org.pokedex.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.pokedex.infrastructure.springdata.dbo.PokemonEntity;

@Entity
@Table(name = "pokemon_type")
@Data
@Getter
@Setter
public class PokemonType {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pokemon_id")
    private PokemonEntity pokemon;

}
