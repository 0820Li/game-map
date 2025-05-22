package ca.mcmaster.cas.se2aa4.a2.island.shape;

import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Shape;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShapeTest {
    @Test
    public void testNearestDistanceTo() {
        Shape shape = new Shape() {
            @Override
            public boolean isInShape(Coordinate coordinate) {
                return coordinate.x()>0 && coordinate.y()>0;
            }
        };

        Coordinate coordinate1 = new Coordinate(-50, -50);
        assertEquals(80, shape.nearestDistance(coordinate1));

        Coordinate coordinate2 = new Coordinate(20, 10);
        assertEquals(20, shape.nearestDistance(coordinate2));
    }
}
