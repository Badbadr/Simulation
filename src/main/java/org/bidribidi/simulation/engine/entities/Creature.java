package org.bidribidi.simulation.engine.entities;


import lombok.*;
import org.apache.commons.collections4.CollectionUtils;
import org.bidribidi.simulation.engine.map.Coordinates;
import org.bidribidi.simulation.engine.map.WorldMap;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class Creature extends Entity {
    protected int velocity = 1;
    protected int healthPoint = 5;
    private LinkedList<Coordinates> path;

    public void makeMove() {
        try {
            LinkedList<Coordinates> possiblyBetterPath = findFood();
            if (possiblyBetterPath.size() < path.size() || CollectionUtils.isEmpty(path)) {
                path = possiblyBetterPath;
            }

            if (path.size() == 2) {
                List<Entity> targetEntities = WorldMap.getEntityByCoordinates(path.getLast());
                for(Entity target: targetEntities) {
                    this.eat((Eatable) target);
                    if (((Eatable) target).eaten()) {
                        WorldMap.removeEntityByCoordinates(target, target.getCoordinates());
                    }
                }
            } else {
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

    public abstract <T extends Eatable> void eat(T food);

    public void moveToRandomPoint() {

    }

    public void followPath() {
        for (int i = 0; i < velocity; i++) {
            path.removeFirst();
        }
        WorldMap.updateEntityCoordinates(this, getCoordinates(), path.getFirst());
        setCoordinates(path.getFirst());
    }
}