package org.bidribidi.simulation.engine.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bidribidi.simulation.engine.entities.Creature;
import org.bidribidi.simulation.engine.entities.Entity;
import org.bidribidi.simulation.engine.entities.Rock;
import org.bidribidi.simulation.engine.entities.Tree;
import org.bidribidi.simulation.engine.util.EntityMapper;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorldMap {

    @Getter
    private static Map<Coordinates, List<Entity>> mapOfSimulation;

    public WorldMap() {
        mapOfSimulation = new HashMap<>();
    }

    public WorldMap(char[][] srcArray) {
        mapOfSimulation = new HashMap<>();
        for (int i = 0; i < srcArray.length; i++) {
            for (int j = 0; j < srcArray[i].length; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                mapOfSimulation.put(coordinates, Stream.of(EntityMapper.map(srcArray[i][j], coordinates))
                        .collect(Collectors.toCollection(ArrayList::new)));
            }
        }
    }

    public static void addEntity(Coordinates coordinates, Entity entity) {
        mapOfSimulation.get(coordinates).add(entity);
    }

    public Collection<Entity> getEntities() {
        return mapOfSimulation.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
    }

    public static Collection<Creature> getCreatures() {
        return mapOfSimulation.values().stream().filter(entity -> entity instanceof Creature)
                .map(entity -> (Creature) entity).collect(Collectors.toSet());
    }

    public static List<Entity> getEntityByCoordinates(Coordinates coordinates) {
        return mapOfSimulation.get(coordinates);
    }

    public static void updateEntityCoordinates(Entity entity, Coordinates oldCoordinates, Coordinates newCoordinates) {
        List<Entity> entities = mapOfSimulation.get(oldCoordinates);
        Entity creature;
        for (Entity value : entities) {
            if (value.equals(entity)) {
                creature = value;
            }
        }
        entities.remove(entity);
        mapOfSimulation.get(newCoordinates).add(entity);
    }

    public static void removeEntityByCoordinates(Entity entity, Coordinates coordinates) {
        mapOfSimulation.get(coordinates).remove(entity);
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
                if (!markedPoints.contains(neighbour) &&
                        (!mapOfSimulation.get(neighbour).getClass().equals(Rock.class) &&
                        !mapOfSimulation.get(neighbour).getClass().equals(Tree.class))
                ) {
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
