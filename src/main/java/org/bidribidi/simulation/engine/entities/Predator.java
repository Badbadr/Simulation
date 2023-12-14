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
public class Predator extends Creature {
    private int hitPower = 1;

    @Override
    public LinkedList<Coordinates> findFood() throws NoSuchElementException {
        return WorldMap.findPath(getCoordinates(), Herbivore.class);
    }

    @Override
    public <T extends Eatable> void eat(T food) {
        if (food instanceof Herbivore && !food.eaten()) {
            attack((Creature) food);
        }
        if (food.eaten()) {
            this.healthPoint += 1;
        }
    }

    public void attack(Creature attackedCreature) {
        attackedCreature.loseHealth(this.hitPower);
    }
}
