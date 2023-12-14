import org.bidribidi.simulation.engine.entities.Herbivore;
import org.bidribidi.simulation.engine.map.Coordinates;
import org.bidribidi.simulation.engine.map.WorldMap;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WorldMapTest {

    @Test
    public void testGetAllNodes_shouldReturn9() {
        char[][] test = {
                {'g', 'r', 'g'},
                {'g', 'g', 'g'},
                {'g', 'g', 'g'}
        };
        WorldMap map = new WorldMap(test);
        assertEquals(9, map.getMapOfSimulation().size());

    }

    @Test
    public void testGetAllNodes_shouldReturn3() {
        char[][] test = {
                {'g', 'r'},
                {'g'}
        };
        WorldMap map = new WorldMap(test);
        assertEquals(3, map.getMapOfSimulation().size());

    }

    @Test
    public void testFindPath_shouldSucceed() {
        char[][] test = {
                {'g', 'r', 'h'},
                {'g', 'g', 'g'},
                {'g', 'g', 'g'}
        };
        WorldMap map = new WorldMap(test);
        assertEquals(List.of(new Coordinates(0, 0), new Coordinates(1, 0), new Coordinates(1, 1),
                        new Coordinates(1, 2), new Coordinates(0, 2)),
                map.findPath(new Coordinates(0, 0), Herbivore.class));
    }

    @Test
    public void testFindPath_shouldThrow() {
        char[][] test = {
                {'g', 'r', 'h'},
                {'r', 'r', 'g'},
                {'g', 'g', 'g'}
        };
        WorldMap map = new WorldMap(test);
        assertThrows(NoSuchElementException.class, () -> map.findPath(new Coordinates(0, 0), Herbivore.class));
    }
}