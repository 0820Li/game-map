package ca.mcmaster.cas.se2aa4.a2.island.shape;

import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Shape;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MirroredShapeTest {
    @Test
    public void testIsInShape() {
        Shape shape = new Shape() {
            @Override
            public boolean isInShape(Coordinate coordinate) {
                return coordinate.y() > 10;
            }
        };
        Shape mirroredShape = new MirroredShape(shape, 20);

        assertTrue(mirroredShape.isInShape(new Coordinate(0, 5)));
        assertFalse(mirroredShape.isInShape(new Coordinate(0, 15)));
    }
}
