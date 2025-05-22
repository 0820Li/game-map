package ca.mcmaster.cas.se2aa4.a2.island.elevation;

import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Elevation;
import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Shape;
import ca.mcmaster.cas.se2aa4.a2.island.shape.CircleShape;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VolcanoElevationTest {
    @Test
    public void testElevationAt() {
        Shape circle = new CircleShape(100, new Coordinate(0, 0));
        Elevation elevation = new VolcanoElevation(new Coordinate(0, 0), circle, 100);
        assertEquals(100, elevation.elevationAt(new Coordinate(0, 0)));
        assertEquals(54.54545454545454, elevation.elevationAt(new Coordinate(0, 50)));
        assertEquals(9.090909090909092, elevation.elevationAt(new Coordinate(0, 100)));
    }
}
