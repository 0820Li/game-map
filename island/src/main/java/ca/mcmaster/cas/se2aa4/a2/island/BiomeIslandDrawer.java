package ca.mcmaster.cas.se2aa4.a2.island;

import ca.mcmaster.cas.se2aa4.a2.island.interfaces.*;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Color;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Segment;

public class BiomeIslandDrawer extends IslandDrawer {
    private final Shape islandShape;
    private final Shape lagoonShape;
    private final Elevation elevation;
    private final SoilAbsorption soilAbsorption;
    private final Cities cities;
    private final Roads roads;

    private final static Color BEACH_COLOR = new Color(240, 245, 110);
    private final static Color LAGOON_COLOR = new Color(119, 187, 247);
    private final static Color SEA_COLOR = new Color(48, 98, 179);
    private final static Color FOREST_COLOR = new Color(28, 166, 28);
    private final static Color FIELD_COLOR = new Color(91, 222, 91);
    private final static Color TUNDRA_COLOR = new Color(161, 88, 5);
    private final static Color CITY_COLOR = new Color(0, 0, 0);
    private final static Color ROAD_COLOR = new Color(0, 0, 0);


    public BiomeIslandDrawer(Shape islandShape, Shape lagoonShape, Elevation elevation, SoilAbsorption soilAbsorption, Cities cities, Roads roads) {
        this.islandShape = islandShape;
        this.lagoonShape = lagoonShape;
        this.elevation = elevation;
        this.soilAbsorption = soilAbsorption;
        this.cities = cities;
        this.roads = roads;
    }

    protected Color getColorForPolygon(Coordinate coordinate) {
        if (islandShape.isInShape(coordinate)) {
            if (lagoonShape.isInShape(coordinate)) {
                return LAGOON_COLOR;
            } else {
                double distanceToLagoon = lagoonShape.nearestDistance(coordinate);
                if (distanceToLagoon < 75) {
                    return BEACH_COLOR;
                }

                double distanceToSea = islandShape.nearestDistance(coordinate);
                double elevation = this.elevation.elevationAt(coordinate);
                if (distanceToSea < 75 && elevation < 200) {
                    return BEACH_COLOR;
                }

                double temperature = -0.0062 * elevation + 31.518;
                double soilAbsorption = this.soilAbsorption.getAbsorptionAt(coordinate);
                if (temperature < 0 && soilAbsorption < 0.2) {
                    return TUNDRA_COLOR;
                }
                if (soilAbsorption > 0.5) {
                    return FOREST_COLOR;
                } else {
                    return FIELD_COLOR;
                }

            }
        } else {
            return SEA_COLOR;
        }
    }

    @Override
    protected Color getColorForVertex(Coordinate coordinate) {
        return cities.isCity(coordinate) ? CITY_COLOR : null;
    }

    @Override
    protected Double getThicknessForVertex(Coordinate coordinate) {
        return cities.isCity(coordinate) ? (cities.getCitySize(coordinate) * 20 + 5) : null;
    }

    @Override
    protected Color getColorForSegment(Segment segment) {
        return roads.isRoad(segment) ? ROAD_COLOR : null;
    }

    @Override
    protected Double getThicknessForSegment(Segment segment) {
        return roads.isRoad(segment) ? 3.0 : null;
    }
}
