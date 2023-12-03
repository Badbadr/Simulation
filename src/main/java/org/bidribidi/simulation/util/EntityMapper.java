package org.bidribidi.simulation.util;

import org.bidribidi.simulation.entities.*;

public class EntityMapper {

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
