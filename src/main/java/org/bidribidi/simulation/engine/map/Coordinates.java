package org.bidribidi.simulation.engine.map;

import lombok.*;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Coordinates {
    private int x;
    private int y;

    public Collection<Coordinates> getNeighbourCoordinates() {
        return Stream.of(new Coordinates(x, y + 1), new Coordinates(x, y - 1), new Coordinates(x + 1, y),
                new Coordinates(x - 1, y)).collect(Collectors.toSet());
    }

}
