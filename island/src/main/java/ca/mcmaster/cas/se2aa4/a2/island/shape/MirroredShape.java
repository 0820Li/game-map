package ca.mcmaster.cas.se2aa4.a2.island.shape;

import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Shape;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;

public class MirroredShape extends Shape {
    private final Shape shape;
    private final double height;

    public MirroredShape(Shape shape, double height) {
        this.shape = shape;
        this.height = height;
    }

    @Override
    public boolean isInShape(Coordinate coordinate) {
        Coordinate mirroredCoordinate = new Coordinate(coordinate.x(), height - coordinate.y());
        return shape.isInShape(mirroredCoordinate);
    }
}
