package ca.mcmaster.cas.se2aa4.a2.island.elevation;

import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Elevation;
import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Shape;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;

public class VolcanoElevation implements Elevation {
    private final Coordinate center;
    private final double maxHeight;
    private final Shape islandShape;

    public VolcanoElevation(Coordinate center, Shape islandShape, double maxHeight) {
        this.center = center;
        this.islandShape = islandShape;
        this.maxHeight = maxHeight;
    }

    @Override
    public double elevationAt(Coordinate coordinate) {
        double distanceToCenter = coordinate.distanceTo(center);
        double distanceToSea = islandShape.nearestDistance(coordinate);
        return (distanceToSea) / (distanceToSea + distanceToCenter) * maxHeight;
    }
}
