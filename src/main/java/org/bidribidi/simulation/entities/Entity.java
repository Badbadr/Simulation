package org.bidribidi.simulation.entities;

import lombok.*;
import org.bidribidi.simulation.map.Coordinates;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public abstract class Entity {
    private Coordinates coordinates;
}