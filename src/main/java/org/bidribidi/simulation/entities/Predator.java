package org.bidribidi.simulation.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bidribidi.simulation.map.Coordinates;
import org.bidribidi.simulation.map.WorldMap;

import java.util.LinkedList;
import java.util.NoSuchElementException;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Predator extends Creature {
    private int hitPower;

    @Override
    public LinkedList<Coordinates> findFood() throws NoSuchElementException {
        return WorldMap.findPath(getCoordinates(), Herbivore.class);
    }

    @Override
    public void eat(Entity food) {
        this.healthPoint += 1;
    }

    @Override
    public boolean validateFood(Entity possibleFood) {
        return validateFood(possibleFood) && possibleFood instanceof Herbivore;
    }

    public void attack(Creature attackedCreature) {
        attackedCreature.loseHealth(this.hitPower);
    }
}
