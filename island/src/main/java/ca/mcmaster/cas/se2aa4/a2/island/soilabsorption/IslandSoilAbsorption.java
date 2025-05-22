package ca.mcmaster.cas.se2aa4.a2.island.soilabsorption;

import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Shape;
import ca.mcmaster.cas.se2aa4.a2.island.interfaces.SoilAbsorption;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;

public class IslandSoilAbsorption implements SoilAbsorption {
    private final Shape islandShape;
    private final Shape lagoonShape;
    private final Shape aquiferShape;

    public IslandSoilAbsorption(Shape islandShape, Shape lagoonShape, Shape aquiferShape) {
        this.islandShape = islandShape;
        this.lagoonShape = lagoonShape;
        this.aquiferShape = aquiferShape;
    }

    @Override
    public double getAbsorptionAt(Coordinate coordinate) {
        if (aquiferShape.isInShape(coordinate)) {
            return 1;
        } else {
            double distanceToSea = islandShape.nearestDistance(coordinate);
            double distanceToLagoon = lagoonShape.nearestDistance(coordinate);
            double distanceToAquifer = aquiferShape.nearestDistance(coordinate);

            return Math.min(1,
                    Math.max(0, -0.005 * distanceToSea + 1) +
                            Math.max(0, -0.005 * distanceToLagoon + 1) +
                            Math.max(0, -0.05 * distanceToAquifer + 1)
            );
        }
    }
}
