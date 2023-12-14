package org.bidribidi.simulation.engine;

import lombok.NoArgsConstructor;
import org.bidribidi.simulation.engine.MoveCounter;
import org.bidribidi.simulation.engine.Simulation;
import org.bidribidi.simulation.engine.actions.Actions;
import org.bidribidi.simulation.engine.map.WorldMap;
import org.bidribidi.simulation.renderer.Renderer;

import java.util.List;

@NoArgsConstructor
public class SimulationImpl implements Simulation {
    private WorldMap map;
    private MoveCounter moveCounter;
    private Renderer renderer;
    private List<Actions> initActions;
    private List<Actions> turnActions;

    @Override
    public void startSimulation() {
        // initialize init actions
        for (Actions actions: initActions) {

        }
    }

    @Override
    public void pauseSimulation() {

    }

    @Override
    public void nextTurn() {
        moveCounter.increment();
    }
}
