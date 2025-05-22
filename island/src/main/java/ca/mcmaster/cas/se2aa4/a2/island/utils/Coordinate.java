package ca.mcmaster.cas.se2aa4.a2.island.utils;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public record Coordinate(double x, double y) {
    public double distanceTo(Coordinate other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    public static Coordinate fromVertex(Structs.Vertex vertex) {
        return new Coordinate(vertex.getX(), vertex.getY());
    }
}
