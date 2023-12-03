package org.bidribidi.simulation.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bidribidi.simulation.entities.Entity;
import org.bidribidi.simulation.entities.Rock;
import org.bidribidi.simulation.util.EntityMapper;

import java.util.*;

@AllArgsConstructor
public class WorldMap implements IWorldMap {

    @Getter
    private Map<Coordinates, Entity> mapOfSimulation;

    public WorldMap(char[][] srcArray) {
        mapOfSimulation = new HashMap<>();
        for (int i = 0; i < srcArray.length; i++) {
            for (int j = 0; j < srcArray[i].length; j++) {
                mapOfSimulation.put(new Coordinates(i, j), EntityMapper.map(srcArray[i][j]));
            }
        }
    }

    @Override
    public <T extends Entity> Collection<Coordinates> findPath(Coordinates root, Class<T> target)
            throws NoSuchElementException
    {
        Map<Coordinates, Coordinates> childToParent = new HashMap<>();
        LinkedList<Coordinates> queue = new LinkedList<>();
        Collection<Coordinates> markedPoints = new HashSet<>();
        Coordinates targetCoordinates = null;
        LinkedList<Coordinates> path = new LinkedList<>();

        queue.addFirst(root);

        while (!queue.isEmpty()) {
            Coordinates current = queue.pollFirst();
            if (markedPoints.contains(current)) {
                continue;
            }
            if (mapOfSimulation.get(current).getClass().equals(target)) {
                targetCoordinates = current;
                break;
            }
            getNeighbourCoordinatesOnMap(current).forEach(neighbour -> {
                if (!markedPoints.contains(neighbour) && !mapOfSimulation.get(neighbour).getClass().equals(Rock.class)) {
                    childToParent.put(neighbour, current);
                    queue.add(neighbour);
                }
            });
            markedPoints.add(current);
        }

        if (queue.isEmpty() && targetCoordinates == null) {
            throw new NoSuchElementException();
        } else {
            path.addFirst(targetCoordinates);
            while (!targetCoordinates.equals(root)) {
                targetCoordinates = childToParent.get(targetCoordinates);
                path.addFirst(targetCoordinates);
            }
        }
        return path;
    }

    public Collection<Coordinates> getNeighbourCoordinatesOnMap(Coordinates start) {
        Collection<Coordinates> res = new HashSet<>();

        for (Coordinates coord: start.getNeighbourCoordinates()) {
            if (mapOfSimulation.get(coord) != null) {
                res.add(coord);
            }
        }

        return res;
    }

}
