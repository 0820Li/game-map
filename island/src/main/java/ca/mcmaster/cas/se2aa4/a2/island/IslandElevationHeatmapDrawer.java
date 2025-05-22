package ca.mcmaster.cas.se2aa4.a2.island;

import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Elevation;
import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Shape;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Color;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Segment;

public class IslandElevationHeatmapDrawer extends IslandDrawer {
    private final Shape islandShape;
    private final Shape lagoonShape;
    private final Elevation elevation;

    public IslandElevationHeatmapDrawer(Shape islandShape, Shape lagoonShape, Elevation elevation) {
        this.islandShape = islandShape;
        this.lagoonShape = lagoonShape;
        this.elevation = elevation;
    }

    protected Color getColorForPolygon(Coordinate coordinate) {
        if (islandShape.isInShape(coordinate) && !lagoonShape.isInShape(coordinate)) {
            double elevation = this.elevation.elevationAt(coordinate);
            elevation /= 1000;
            return new Color((int) (elevation * 255), (int) (elevation * 255), (int) (elevation * 255));
        } else {
            return new Color(48, 98, 179);
        }
    }

    @Override
    protected Color getColorForVertex(Coordinate coordinate) {
        return null;
    }

    @Override
    protected Double getThicknessForVertex(Coordinate coordinate) {
        return null;
    }

    @Override
    protected Color getColorForSegment(Segment segment) {
        return null;
    }

    @Override
    protected Double getThicknessForSegment(Segment segment) {
        return null;
    }
}
