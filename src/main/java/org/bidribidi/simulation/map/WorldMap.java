package org.bidribidi.simulation.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bidribidi.simulation.entities.Entity;
import org.bidribidi.simulation.entities.Rock;
import org.bidribidi.simulation.util.EntityMapper;

import java.util.*;

@AllArgsConstructor
public class WorldMap {

    @Getter
    private static Map<Coordinates, Entity> mapOfSimulation;

    public WorldMap(char[][] srcArray) {
        mapOfSimulation = new HashMap<>();
        for (int i = 0; i < srcArray.length; i++) {
            for (int j = 0; j < srcArray[i].length; j++) {
                mapOfSimulation.put(new Coordinates(i, j), EntityMapper.map(srcArray[i][j]));
            }
        }
    }

    public static Entity getEntityByCoordinates(Coordinates coordinates) {
        return mapOfSimulation.get(coordinates);
    }
    public static <T extends Entity> LinkedList<Coordinates> findPath(Coordinates root, Class<T> target)
            throws NoSuchElementException
    {
        Map<Coordinates, Coordinates> childToParent = new HashMap<>();
        LinkedList<Coordinates> queue = new LinkedList<>();
        Set<Coordinates> markedPoints = new HashSet<>();
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

    private static Collection<Coordinates> getNeighbourCoordinatesOnMap(Coordinates start) {
        Set<Coordinates> res = new HashSet<>();

        for (Coordinates coord: start.getNeighbourCoordinates()) {
            if (mapOfSimulation.get(coord) != null) {
                res.add(coord);
            }
        }

        return res;
    }

}
