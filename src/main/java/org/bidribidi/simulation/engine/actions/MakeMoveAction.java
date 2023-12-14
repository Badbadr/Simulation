package org.bidribidi.simulation.engine.actions;

import org.bidribidi.simulation.engine.entities.Creature;
import org.bidribidi.simulation.engine.map.WorldMap;

public class MakeMoveAction extends Action {
    @Override
    public void act(WorldMap map) {
        WorldMap.getCreatures().forEach(Creature::makeMove);
    }
}
