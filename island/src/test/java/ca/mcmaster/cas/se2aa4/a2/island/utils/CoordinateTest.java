package ca.mcmaster.cas.se2aa4.a2.island.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoordinateTest {
    @Test
    public void testValue() {
        Coordinate coordinate = new Coordinate(2, 3);
        assertEquals(2, coordinate.x());
        assertEquals(3, coordinate.y());
    }

    @Test
    public void testDistanceTo() {
        Coordinate coordinate1 = new Coordinate(0, 0);
        Coordinate coordinate2 = new Coordinate(3, 4);
        assertEquals(5,coordinate1.distanceTo(coordinate2));
    }
}
