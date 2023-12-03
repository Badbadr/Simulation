package org.bidribidi.simulation.map;

import lombok.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Coordinates {
    private int x;
    private int y;

    public Collection<Coordinates> getNeighbourCoordinates() {
        return Stream.of(new Coordinates(x, y + 1), new Coordinates(x, y - 1), new Coordinates(x + 1, y),
                new Coordinates(x - 1, y)).collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
