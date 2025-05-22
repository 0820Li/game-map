package ca.mcmaster.cas.se2aa4.a2.island;

import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Shape;
import ca.mcmaster.cas.se2aa4.a2.island.interfaces.SoilAbsorption;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Color;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Segment;

public class IslandSoilAbsorptionHeatmapDrawer extends IslandDrawer {
    private final Shape islandShape;
    private final Shape lagoonShape;
    private final SoilAbsorption soilAbsorption;

    public IslandSoilAbsorptionHeatmapDrawer(Shape islandShape, Shape lagoonShape, SoilAbsorption soilAbsorption) {
        this.islandShape = islandShape;
        this.lagoonShape = lagoonShape;
        this.soilAbsorption = soilAbsorption;
    }

    protected Color getColorForPolygon(Coordinate coordinate) {
        if (islandShape.isInShape(coordinate) && !lagoonShape.isInShape(coordinate)) {
            double absorption = soilAbsorption.getAbsorptionAt(coordinate);
            return new Color((int) (absorption * 255), (int) (absorption * 255), (int) (absorption * 255));
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
