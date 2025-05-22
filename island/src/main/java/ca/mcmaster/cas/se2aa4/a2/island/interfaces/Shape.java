package ca.mcmaster.cas.se2aa4.a2.island.interfaces;

import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;

public abstract class Shape {
    abstract public boolean isInShape(Coordinate coordinate);

    public double nearestDistance(Coordinate coordinate) {
        boolean current = isInShape(coordinate);
        for (int i = 10; ; i += 10) {
            double circumference = 2 * Math.PI * i;
            double angleSteps = 360 / (circumference / 10);
            for (double angle = 0; angle < 360; angle += angleSteps) {
                Coordinate newCoordinate = new Coordinate(i * Math.cos(Math.toRadians(angle)) + coordinate.x(), i * Math.sin(Math.toRadians(angle)) + coordinate.y());
                if (current != isInShape(newCoordinate)) {
                    return newCoordinate.distanceTo(coordinate);
                }
            }
            if (i > 2000) {
                return 2000;
            }
        }
    }
}
