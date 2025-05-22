package ca.mcmaster.cas.se2aa4.a2.island.city;

import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Cities;
import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Shape;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomCities implements Cities {
    private final Map<Coordinate, Double> cities = new HashMap<>();

    public RandomCities(Shape islandShape, Shape lagoonShape, int cityCount, List<Coordinate> vertices, long seed) {
        Random random = new Random(seed);
        while (cities.size() < cityCount) {
            Coordinate coordinate = vertices.get(random.nextInt(vertices.size()));
            if (islandShape.isInShape(coordinate) && !lagoonShape.isInShape(coordinate) && islandShape.nearestDistance(coordinate) > 20) {
                cities.put(coordinate, random.nextDouble());
            }
        }
    }

    @Override
    public boolean isCity(Coordinate coordinate) {
        return cities.containsKey(coordinate);
    }

    @Override
    public double getCitySize(Coordinate coordinate) {
        return cities.getOrDefault(coordinate, 0.0);
    }

    @Override
    public Map<Coordinate, Double> getAllCities() {
        return cities;
    }
}
