package ca.mcmaster.cas.se2aa4.a2.island.shape;

import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Shape;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CombinedShapeTest {
    @Test
    public void testIsInShape() {
        Shape shape1 = new Shape() {
            @Override
            public boolean isInShape(Coordinate coordinate) {
                return coordinate.x() > 0 && coordinate.y() > 0;
            }
        };
        Shape shape2 = new Shape() {
            @Override
            public boolean isInShape(Coordinate coordinate) {
                return coordinate.x() < 0 && coordinate.y() < 0;
            }
        };
        Shape combinedShape = new CombinedShape(shape1, shape2);

        assertTrue(combinedShape.isInShape(new Coordinate(1, 1)));
        assertTrue(combinedShape.isInShape(new Coordinate(-1, -1)));
        assertFalse(combinedShape.isInShape(new Coordinate(1, -1)));
        assertFalse(combinedShape.isInShape(new Coordinate(-1, 1)));
    }
}
