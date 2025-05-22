package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.ColorProperty;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.ThicknessProperty;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.Iterator;
import java.util.Optional;

public class GraphicRenderer implements Renderer {
    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.2f);
        canvas.setStroke(stroke);
        drawPolygons(aMesh, canvas);
        drawSegments(aMesh, canvas);
        drawVertices(aMesh, canvas);
    }

    private void drawPolygons(Mesh aMesh, Graphics2D canvas) {
        for (Structs.Polygon p : aMesh.getPolygonsList()) {
            drawAPolygon(p, aMesh, canvas);
        }
    }

    private void drawAPolygon(Structs.Polygon p, Mesh aMesh, Graphics2D canvas) {
        Hull hull = new Hull();
        for (Integer segmentIdx : p.getSegmentIdxsList()) {
            hull.add(aMesh.getSegments(segmentIdx), aMesh);
        }
        Path2D path = new Path2D.Float();
        Iterator<Vertex> vertices = hull.iterator();
        Vertex current = vertices.next();
        path.moveTo(current.getX(), current.getY());
        while (vertices.hasNext()) {
            current = vertices.next();
            path.lineTo(current.getX(), current.getY());
        }
        path.closePath();
        canvas.draw(path);
        Optional<Color> fill = new ColorProperty().extract(p.getPropertiesList());
        if (fill.isPresent()) {
            Color old = canvas.getColor();
            canvas.setColor(fill.get());
            canvas.fill(path);
            canvas.setColor(old);
        }
    }

    private void drawSegments(Mesh aMesh, Graphics2D canvas) {
        for (Structs.Segment s : aMesh.getSegmentsList()) {
            drawASegment(s, aMesh, canvas);
        }
    }

    private void drawASegment(Structs.Segment s, Mesh aMesh, Graphics2D canvas) {
        Vertex v1 = aMesh.getVertices(s.getV1Idx());
        Vertex v2 = aMesh.getVertices(s.getV2Idx());
        Line2D line = new Line2D.Float((float) v1.getX(), (float) v1.getY(), (float) v2.getX(), (float) v2.getY());

        Optional<Double> thickness = new ThicknessProperty().extract(s.getPropertiesList());
        if (thickness.isPresent()) {
            Stroke stroke = new BasicStroke(thickness.get().floatValue());
            canvas.setStroke(stroke);
        } else {
            return;
        }

        Optional<Color> fill = new ColorProperty().extract(s.getPropertiesList());
        if (fill.isPresent()) {
            canvas.setColor(fill.get());
        } else {
            return;
        }

        canvas.draw(line);
    }

    private void drawVertices(Mesh aMesh, Graphics2D canvas) {
        for (Structs.Vertex v : aMesh.getVerticesList()) {
            drawAVertex(v, aMesh, canvas);
        }
    }

    private void drawAVertex(Structs.Vertex v, Mesh aMesh, Graphics2D canvas) {
        Optional<Double> thickness = new ThicknessProperty().extract(v.getPropertiesList());
        Optional<Color> fill = new ColorProperty().extract(v.getPropertiesList());
        if (thickness.isEmpty() || fill.isEmpty())
            return;

        float radius = (float) (double) thickness.get();
        canvas.setColor(fill.get());
        Ellipse2D circle = new Ellipse2D.Float((float) v.getX() - radius, (float) v.getY() - radius,
                radius * 2, radius * 2);
        canvas.fill(circle);
    }
}
