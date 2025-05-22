package ca.mcmaster.cas.se2aa4.a2.island.shape;

import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Shape;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;

public class CircleShape extends Shape {
    public final double radius;
    public final Coordinate center;

    public CircleShape(double radius, Coordinate center) {
        this.radius = radius;
        this.center = center;
    }

    @Override
    public boolean isInShape(Coordinate coordinate) {
        return coordinate.distanceTo(center) <= radius;
    }
}
