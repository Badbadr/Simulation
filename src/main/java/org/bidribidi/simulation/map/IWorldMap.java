package org.bidribidi.simulation.map;

import org.bidribidi.simulation.entities.Entity;

import java.util.Collection;
import java.util.NoSuchElementException;

public interface IWorldMap {
    <T extends Entity> Collection<Coordinates> findPath(Coordinates start, Class<T> entityClass)
            throws NoSuchElementException;
}
