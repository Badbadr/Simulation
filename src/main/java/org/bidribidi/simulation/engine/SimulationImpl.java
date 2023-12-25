package org.bidribidi.simulation.engine;

import org.bidribidi.simulation.engine.actions.Action;
import org.bidribidi.simulation.engine.actions.InitMapAction;
import org.bidribidi.simulation.engine.actions.MakeMoveAction;
import org.bidribidi.simulation.engine.entities.Entity;
import org.bidribidi.simulation.engine.map.Coordinates;
import org.bidribidi.simulation.engine.map.WorldMap;
import org.bidribidi.simulation.renderer.Renderer;

import java.util.List;

public class SimulationImpl implements Simulation {
    private static Simulation singletone;
    private WorldMap map;
    private MoveCounter moveCounter;
    private Renderer renderer;
    private List<Action> initActions = List.of(new InitMapAction());
    private List<Action> turnActions = List.of(new MakeMoveAction());

    public static Simulation getInstance() {
        if (singletone == null) {
            singletone = new SimulationImpl();
        }
        return singletone;
    }

    @Override
    public WorldMap getMap() {
        return this.map;
    }

    @Override
    public void startSimulation() {
        // initialize init actions
//        char[][] initMap = {
//                {'-', 'r', 'h'},
//                {'t', '-', '-'},
//                {'p', 'g', '-'}
//        };
        map = new WorldMap();
        for (Action action: initActions) {
            action.act(map);
        }
    }

    @Override
    public void pauseSimulation() {

    }

    @Override
    public void nextTurn() {
        for (Action action: turnActions) {
            action.act(map);
        }
        moveCounter.increment();
    }

}
