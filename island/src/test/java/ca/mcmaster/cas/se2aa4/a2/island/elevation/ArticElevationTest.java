package ca.mcmaster.cas.se2aa4.a2.island.elevation;

import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Elevation;
import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Shape;
import ca.mcmaster.cas.se2aa4.a2.island.shape.CircleShape;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArticElevationTest {
    @Test
    public void testElevationAt() {
        Shape circle = new CircleShape(100, new Coordinate(0, 0));
        Elevation elevation = new ArticElevation(circle,100);

        assertEquals(20, elevation.elevationAt(new Coordinate(95, 0)));
        assertEquals(100, elevation.elevationAt(new Coordinate(0, 0)));
    }
}
