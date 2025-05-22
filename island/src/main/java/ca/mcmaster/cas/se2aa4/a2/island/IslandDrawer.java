package ca.mcmaster.cas.se2aa4.a2.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.island.city.RandomCities;
import ca.mcmaster.cas.se2aa4.a2.island.elevation.ArticElevation;
import ca.mcmaster.cas.se2aa4.a2.island.elevation.VolcanoElevation;
import ca.mcmaster.cas.se2aa4.a2.island.interfaces.*;
import ca.mcmaster.cas.se2aa4.a2.island.road.StarNetwork;
import ca.mcmaster.cas.se2aa4.a2.island.shape.*;
import ca.mcmaster.cas.se2aa4.a2.island.soilabsorption.IslandSoilAbsorption;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Color;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Segment;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.algorithms.DijkstraAlgorithm;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.algorithms.PathFindingAlgorithm;

import java.util.List;
import java.util.Random;

public abstract class IslandDrawer {
    public Structs.Mesh drawIsland(Structs.Mesh mesh) {
        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        List<Structs.Vertex> vertices = mesh.getVerticesList();

        for (Structs.Polygon poly : mesh.getPolygonsList()) {
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(poly);
            String color = getColorForPolygon(Coordinate.fromVertex(vertices.get(poly.getCentroidIdx()))).toString();
            Structs.Property p = Structs.Property.newBuilder()
                    .setKey("rgb_color")
                    .setValue(color)
                    .build();
            pc.addProperties(p);
            clone.addPolygons(pc);
        }

        for (Structs.Vertex vertex : vertices) {
            Structs.Vertex.Builder vc = Structs.Vertex.newBuilder(vertex);
            Color color = getColorForVertex(Coordinate.fromVertex(vertex));
            if (color != null) {
                Structs.Property p = Structs.Property.newBuilder()
                        .setKey("rgb_color")
                        .setValue(color.toString())
                        .build();
                vc.addProperties(p);
            }
            Double thickness = getThicknessForVertex(Coordinate.fromVertex(vertex));
            if (thickness != null) {
                Structs.Property p = Structs.Property.newBuilder()
                        .setKey("thickness")
                        .setValue(thickness.toString())
                        .build();
                vc.addProperties(p);
            }

            clone.addVertices(vc);
        }

        for (Structs.Segment segment : mesh.getSegmentsList()) {
            Structs.Segment.Builder sc = Structs.Segment.newBuilder(segment);
            Segment segmentADT = new Segment(
                    Coordinate.fromVertex(vertices.get(segment.getV1Idx())),
                    Coordinate.fromVertex(vertices.get(segment.getV2Idx()))
            );
            Color color = getColorForSegment(segmentADT);
            if (color != null) {
                Structs.Property p = Structs.Property.newBuilder()
                        .setKey("rgb_color")
                        .setValue(color.toString())
                        .build();
                sc.addProperties(p);
            }
            Double thickness = getThicknessForSegment(segmentADT);
            if (thickness != null) {
                Structs.Property p = Structs.Property.newBuilder()
                        .setKey("thickness")
                        .setValue(thickness.toString())
                        .build();
                sc.addProperties(p);
            }

            clone.addSegments(sc);
        }

        return clone.build();
    }

    protected abstract Color getColorForPolygon(Coordinate coordinate);

    protected abstract Color getColorForVertex(Coordinate coordinate);

    protected abstract Double getThicknessForVertex(Coordinate coordinate);

    protected abstract Color getColorForSegment(Segment segment);

    protected abstract Double getThicknessForSegment(Segment segment);

    public static IslandDrawer islandDrawerFactory(
            double width,
            double height,
            List<Coordinate> potentialCityVertices,
            List<Segment> segments,
            String shape,
            String altitude,
            int maxLakes,
            int aquifers,
            int cities,
            long seed,
            String heatmap
    ) {
        Random random = new Random(seed);
        double shortestSide = Math.min(width, height);
        Shape islandShape = switch (shape) {
            case "hourglass" -> {
                Shape triangleShape = new TriangleShape(new Coordinate(width / 2, height / 2), shortestSide * 0.45);
                Shape mirroredTriangleShape = new MirroredShape(triangleShape, height);
                yield new CombinedShape(triangleShape, mirroredTriangleShape);
            }
            case "circle" -> new CircleShape(shortestSide * 0.45, new Coordinate(width / 2, height / 2));
            default -> throw new IllegalArgumentException("Unexpected value: " + shape);
        };
        Elevation elevation = switch (altitude) {
            case "artic" -> new ArticElevation(islandShape, 300);
            case "volcano" -> new VolcanoElevation(new Coordinate(width / 2, height / 2), islandShape, 3000);
            default -> throw new IllegalArgumentException("Unexpected value: " + altitude);
        };
        int lakeCount = random.nextInt(maxLakes);
        Shape lagoonShape = new RandomCircleShape(width, height, lakeCount, islandShape, true, seed);
        Shape aquiferShape = new RandomCircleShape(width, height, aquifers, islandShape, false, seed);
        SoilAbsorption soilAbsorption = new IslandSoilAbsorption(islandShape, lagoonShape, aquiferShape);

        Cities city = new RandomCities(islandShape, lagoonShape, cities, potentialCityVertices, seed);
        PathFindingAlgorithm<Coordinate, Segment> pathFinder = new DijkstraAlgorithm<>();
        Roads roads = new StarNetwork(segments, islandShape, lagoonShape, city, elevation, pathFinder);

        return switch (heatmap) {
            case "elevation" -> new IslandElevationHeatmapDrawer(islandShape, lagoonShape, elevation);
            case "soil" -> new IslandSoilAbsorptionHeatmapDrawer(islandShape, lagoonShape, soilAbsorption);
            default -> new BiomeIslandDrawer(islandShape, lagoonShape, elevation, soilAbsorption, city, roads);
        };
    }
}
