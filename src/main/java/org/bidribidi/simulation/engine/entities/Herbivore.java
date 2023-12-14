package org.bidribidi.simulation.engine.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bidribidi.simulation.engine.map.Coordinates;
import org.bidribidi.simulation.engine.map.WorldMap;

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
    public void loseHealth(int healthPoint) {
        super.loseHealth(healthPoint);
    }

    @Override
    public <T extends Eatable> void eat(T food) {
        if (food instanceof Grass) {
            this.healthPoint += 1;
            food.eaten();
        }
    }

    @Override
    public boolean eaten() {
        return healthPoint <= 0;
    }
}
