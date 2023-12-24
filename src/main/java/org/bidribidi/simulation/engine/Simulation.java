package org.bidribidi.simulation.engine;

import org.bidribidi.simulation.engine.map.WorldMap;

public interface Simulation {

    void startSimulation();

    void pauseSimulation();

    void nextTurn();

    WorldMap getMap();
}
