package ca.mcmaster.cas.se2aa4.a2.island.shape;

import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Shape;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;

public class TriangleShape extends Shape {
    private final Coordinate center;
    private final double sideLength;
    private final double height;
    private final double a;

    public TriangleShape(Coordinate center, double radius){
        this.center = center;
        this.sideLength = radius * Math.sqrt(3);
        this.height = sideLength / 2 * Math.sqrt(3);
        this.a = height - radius;
    }

    @Override
    public boolean isInShape(Coordinate coordinate) {
        double deltaX = Math.abs(coordinate.x() - center.x());
        double bottomY = center.y() - a;

        if (coordinate.y() < bottomY) {
            return false;
        } else if (coordinate.y() > bottomY + height) {
            return false;
        }
        double deltaY = Math.abs(coordinate.y() - bottomY);
        return !(deltaX > deltaY / height * sideLength / 2);
    }
}
