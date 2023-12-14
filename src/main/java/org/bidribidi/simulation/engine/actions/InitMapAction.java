package org.bidribidi.simulation.engine.actions;

import org.bidribidi.simulation.engine.map.Coordinates;
import org.bidribidi.simulation.engine.map.WorldMap;
import java.util.Random;
import java.util.Set;

import static org.bidribidi.simulation.engine.util.EntityMapper.getRandomEntity;

public class InitMapAction extends Action {

    @Override
    public void act(WorldMap map) {
        Set<Coordinates> keys = WorldMap.getMapOfSimulation().keySet();
        int randimI = new Random().ints(0, keys.size()).findFirst().getAsInt();
        Coordinates randCoord = (Coordinates) keys.toArray()[randimI];
        WorldMap.addEntity(randCoord, getRandomEntity());
    }
}
