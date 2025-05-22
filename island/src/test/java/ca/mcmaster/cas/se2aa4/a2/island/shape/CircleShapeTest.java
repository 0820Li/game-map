package ca.mcmaster.cas.se2aa4.a2.island.shape;

import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CircleShapeTest {
    @Test
    public void testIsInShape() {
        CircleShape circleShape = new CircleShape(100, new Coordinate(50, 50));
        assertTrue(circleShape.isInShape(new Coordinate(50, 50)));
        assertTrue(circleShape.isInShape(new Coordinate(100, 50)));
        assertTrue(circleShape.isInShape(new Coordinate(50, 100)));
        assertFalse(circleShape.isInShape(new Coordinate(175, 50)));
        assertFalse(circleShape.isInShape(new Coordinate(50, 175)));
    }
}
