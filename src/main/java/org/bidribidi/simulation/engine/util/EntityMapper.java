package org.bidribidi.simulation.engine.util;

import org.bidribidi.simulation.engine.entities.*;
import org.bidribidi.simulation.engine.map.Coordinates;

import java.util.Random;

public class EntityMapper {

    public static Entity getRandomEntity(Coordinates coordinates) {
        char[] randomSymbols = {'g', 'h', 'p', 't'};
        int randimI = new Random().ints(0, randomSymbols.length).findFirst().getAsInt();
        return map(randomSymbols[randimI], coordinates);
    }

    public static Entity map(char symbol, Coordinates coordinates) {
        Entity entity = switch(symbol) {
            case 'g' -> new Grass();
            case 'h' -> new Herbivore();
            case 'p' -> new Predator();
            case 'r' -> new Rock();
            case 't' -> new Tree();
            default -> null;
        };

        if (entity != null) {
            entity.setCoordinates(coordinates);
        }

        return entity;
    }
}
