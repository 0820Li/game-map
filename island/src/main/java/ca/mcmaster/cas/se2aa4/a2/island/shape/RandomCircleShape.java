package ca.mcmaster.cas.se2aa4.a2.island.shape;

import ca.mcmaster.cas.se2aa4.a2.island.interfaces.Shape;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomCircleShape extends Shape {
    private final Shape innerShape;

    public RandomCircleShape(double width, double height, int num, Shape islandShape, boolean allowOverlap, long seed) {
        List<CircleShape> circles = new ArrayList<>();
        Random random = new Random(seed);
        outer:
        while (circles.size() < num) {
            double x = random.nextDouble() * width;
            double y = random.nextDouble() * height;
            double radius = random.nextDouble() * 100;
            Coordinate center = new Coordinate(x, y);
            if (!islandShape.isInShape(center) || islandShape.nearestDistance(center) < radius || radius < 15) {
                continue;
            }
            if (!allowOverlap) {
                for (CircleShape otherCircle : circles) {
                    if (otherCircle.center.distanceTo(center) < radius+otherCircle.radius) {
                        continue outer;
                    }
                }
            }

            circles.add(new CircleShape(radius, center));
        }
        innerShape = new CombinedShape(circles.toArray(new Shape[0]));
    }

    @Override
    public boolean isInShape(Coordinate coordinate) {
        return innerShape.isInShape(coordinate);
    }
}
