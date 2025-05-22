package ca.mcmaster.cas.se2aa4.a2.island.utils;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorTest {
    @Test
    public void testValue() {
        Color color = new Color(0, 0, 0);
        assertEquals(0, color.r());
        assertEquals(0, color.g());
        assertEquals(0, color.b());
    }

    @Test
    public void testToString() {
        Color color = new Color(2, 3, 4);
        assertEquals("2,3,4", color.toString());
    }
}
