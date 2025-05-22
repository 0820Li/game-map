package ca.mcmaster.cas.se2aa4.a2.island.shape;

import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Shape;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;

public class CombinedShape extends Shape {
    private final Shape[] shapes;

    public CombinedShape(Shape... shapes) {
        this.shapes = shapes;
    }

    @Override
    public boolean isInShape(Coordinate coordinate) {
        for (Shape shape : shapes) {
            if (shape.isInShape(coordinate)) {
                return true;
            }
        }
        return false;
    }
}
