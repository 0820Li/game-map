package ca.mcmaster.cas.se2aa4.a2.island.elevation;

import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Elevation;
import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Shape;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;

public class ArticElevation implements Elevation {
    private final Shape islandShape;
    private final double maxHeight;

    public ArticElevation(Shape islandShape, double maxHeight) {
        this.islandShape = islandShape;
        this.maxHeight = maxHeight;
    }

    @Override
    public double elevationAt(Coordinate coordinate) {
        double distanceToSea = islandShape.nearestDistance(coordinate);
        return Math.min(maxHeight, distanceToSea * 2);
    }
}
