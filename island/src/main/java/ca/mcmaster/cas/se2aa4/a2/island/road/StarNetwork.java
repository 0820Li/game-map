package ca.mcmaster.cas.se2aa4.a2.island.road;

import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Cities;
import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Elevation;
import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Roads;
import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Shape;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Segment;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.algorithms.PathFindingAlgorithm;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StarNetwork implements Roads {
    Set<Segment> roads = new HashSet<>();

    private static class RoadGraph implements Graph<Coordinate, Segment> {
        List<Segment> segments;
        Shape islandShape;
        Shape lagoonShape;
        Elevation elevation;

        RoadGraph(List<Segment> segments, Elevation elevation, Shape islandShape, Shape lagoonShape) {
            this.segments = segments;
            this.elevation = elevation;
            this.islandShape = islandShape;
            this.lagoonShape = lagoonShape;
        }

        @Override
        public Set<Segment> getEdges(Coordinate node) {
            Set<Segment> edges = new HashSet<>();
            for (Segment segment : segments) {
                if ((segment.start().equals(node) || segment.end().equals(node)) &&
                        (islandShape.isInShape(segment.start()) && islandShape.isInShape(segment.end())) &&
                        (!lagoonShape.isInShape(segment.start()) && !lagoonShape.isInShape(segment.end()))
                ) {
                    edges.add(segment);
                }
            }
            return edges;
        }

        @Override
        public Pair<Coordinate> getNodes(Segment edge) {
            return new Pair<>(edge.start(), edge.end());
        }

        @Override
        public double getWeight(Segment edge) {
            double distance = edge.start().distanceTo(edge.end());
            double heightDifference = Math.abs(elevation.elevationAt(edge.start()) - elevation.elevationAt(edge.end()));
            return Math.sqrt(distance * distance + heightDifference * heightDifference * 2);
        }
    }

    public StarNetwork(List<Segment> segments, Shape islandShape, Shape lagoonShape, Cities cities, Elevation elevation, PathFindingAlgorithm<Coordinate, Segment> pathFinder) {
        Coordinate largestCity = null;
        double largestCitySize = 0;
        for (Map.Entry<Coordinate, Double> cityEntry : cities.getAllCities().entrySet()) {
            if (cityEntry.getValue() > largestCitySize) {
                largestCity = cityEntry.getKey();
                largestCitySize = cityEntry.getValue();
            }
        }

        RoadGraph graph = new RoadGraph(segments, elevation, islandShape, lagoonShape);
        for (Coordinate city : cities.getAllCities().keySet()) {
            if (city.equals(largestCity)) {
                continue;
            }
            List<Segment> path = pathFinder.findPath(graph, largestCity, city);
            if (path != null) {
                for (int i = 0; i < path.size() - 1; i++) {
                    Segment a = path.get(i);
                    Segment b = path.get(i + 1);
                    if (!a.start().equals(b.start()) && !a.start().equals(b.end()) && !a.end().equals(b.start()) && !a.end().equals(b.end())) {
                        throw new IllegalStateException("Path is not continuous");
                    }
                }

                roads.addAll(path);
            }
        }
    }

    @Override
    public boolean isRoad(Segment segment) {
        return roads.contains(segment);
    }
}
