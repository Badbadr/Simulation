package org.bidribidi.simulation.entities;


import lombok.*;
import org.apache.commons.collections4.CollectionUtils;
import org.bidribidi.simulation.map.Coordinates;
import org.bidribidi.simulation.map.WorldMap;

import java.util.LinkedList;
import java.util.NoSuchElementException;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class Creature extends Entity {
    protected int velocity;
    protected int healthPoint;
    private LinkedList<Coordinates> path;

    public void makeMove() {
        try {
            if (CollectionUtils.isNotEmpty(path)) {
                if (getCoordinates().getNeighbourCoordinates().contains(path.getLast())) {
                    Entity entity = WorldMap.getEntityByCoordinates(path.getLast());
                    if (this instanceof Predator) {
                        if (entity instanceof Herbivore) {
                            ((Predator) this).attack((Herbivore) entity);
                            if (((Herbivore) entity).eaten()) {
                                this.eat(entity);
                            }
                        }
                    } else if (this instanceof Herbivore) {
                        if (entity instanceof Grass) {
                            this.eat(entity);
                        }
                    }
                } else {
                    LinkedList<Coordinates> newPath = findFood();
                    path = newPath.size() < path.size() ? newPath: path;
                    followPath();
                }
            } else {
                path = findFood();
                followPath();
            }
        } catch (NoSuchElementException e) {
            moveToRandomPoint();
        }
    }

    public void loseHealth(int healthPoint) {
        this.healthPoint -= healthPoint;
    }
    public abstract LinkedList<Coordinates> findFood() throws NoSuchElementException;

    public abstract void eat(Entity food);

    public void moveToRandomPoint() {

    }

    public void followPath() {
        for (int i = 0; i < velocity; i++) {
            path.removeFirst();
        }
        setCoordinates(path.getFirst());
    }

    abstract boolean validateFood(Entity possibleFood);
}