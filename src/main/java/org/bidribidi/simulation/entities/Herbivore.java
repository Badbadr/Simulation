package org.bidribidi.simulation.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bidribidi.simulation.map.Coordinates;
import org.bidribidi.simulation.map.WorldMap;

import java.util.Collection;
import java.util.LinkedList;
import java.util.NoSuchElementException;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Herbivore extends Creature implements Eatable {
    private boolean eaten;

    @Override
    public LinkedList<Coordinates> findFood() throws NoSuchElementException {
        return WorldMap.findPath(getCoordinates(), Grass.class);
    }

    @Override
    public void eat(Entity food) {
        this.healthPoint += 1;
    }

    @Override
    public boolean validateFood(Entity possibleFood) {
        return possibleFood instanceof Grass;
    }

    @Override
    public void loseHealth(int healthPoint) {
        super.loseHealth(healthPoint);

    }

    @Override
    public boolean eaten() {
        return healthPoint <= 0;
    }
}
