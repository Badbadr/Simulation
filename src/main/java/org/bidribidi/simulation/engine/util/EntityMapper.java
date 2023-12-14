package org.bidribidi.simulation.engine.util;

import org.bidribidi.simulation.engine.entities.*;

import java.util.Random;

public class EntityMapper {

    public static Entity getRandomEntity() {
        char[] randomSymbols = {'g', 'h', 'p', 't'};
        int randimI = new Random().ints(0, randomSymbols.length).findFirst().getAsInt();
        return map(randomSymbols[randimI]);
    }

    public static Entity map(char symbol) {
        return switch(symbol) {
            case 'g' -> new Grass();
            case 'h' -> new Herbivore();
            case 'p' -> new Predator();
            case 'r' -> new Rock();
            case 't' -> new Tree();
            default -> null;
        };
    }
}
