package ca.mcmaster.cas.se2aa4.a2.island.interfaces;

import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;

import java.util.Map;

public interface Cities {
    boolean isCity(Coordinate coordinate);
    double getCitySize(Coordinate coordinate);
    Map<Coordinate, Double> getAllCities();
}
