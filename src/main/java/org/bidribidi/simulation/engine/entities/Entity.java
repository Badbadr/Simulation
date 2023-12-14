package org.bidribidi.simulation.engine.entities;

import lombok.*;
import org.bidribidi.simulation.engine.map.Coordinates;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public abstract class Entity {
    private Coordinates coordinates;
}