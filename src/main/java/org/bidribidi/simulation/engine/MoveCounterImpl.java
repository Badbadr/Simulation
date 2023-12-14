package org.bidribidi.simulation.engine;

public class MoveCounterImpl implements MoveCounter {

    private static int moveNumber;

    public static int getMoveNumber() {
        return moveNumber;
    }

    @Override
    public int increment() {
        return moveNumber++;
    }
}
