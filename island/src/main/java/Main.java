import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.island.IslandDrawer;
import ca.mcmaster.cas.se2aa4.a2.island.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Coordinate;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Segment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {
        Configuration config = new Configuration(args);
        Map<String, String> options = config.export();
        String inputFileName = options.get(Configuration.INPUT_FILENAME);
        Structs.Mesh mesh = new MeshFactory().read(inputFileName);


        double width = Double.MIN_VALUE;
        double height = Double.MIN_VALUE;
        for (Structs.Vertex vertex : mesh.getVerticesList()) {
            if (vertex.getX() > width) {
                width = vertex.getX();
            }
            if (vertex.getY() > height) {
                height = vertex.getY();
            }
        }

        List<Coordinate> potentialCityVertices = new ArrayList<>();
        List<Segment> segments = new ArrayList<>();
        for (Structs.Segment segment : mesh.getSegmentsList()) {
            segments.add(new Segment(
                    Coordinate.fromVertex(mesh.getVertices(segment.getV1Idx())),
                    Coordinate.fromVertex(mesh.getVertices(segment.getV2Idx()))
            ));
            potentialCityVertices.add(Coordinate.fromVertex(mesh.getVertices(segment.getV1Idx())));
            potentialCityVertices.add(Coordinate.fromVertex(mesh.getVertices(segment.getV2Idx())));
        }

        IslandDrawer drawer = IslandDrawer.islandDrawerFactory(
                width,
                height,
                potentialCityVertices,
                segments,
                options.get(Configuration.SHAPE),
                options.get(Configuration.ALTITUDE),
                Integer.parseInt(options.get(Configuration.LAKES)),
                Integer.parseInt(options.get(Configuration.AQUIFERS)),
                Integer.parseInt(options.get(Configuration.CITIES)),
                Long.parseLong(options.getOrDefault(Configuration.SEED, new Random().nextLong() + "")),
                options.getOrDefault(Configuration.HEATMAP, "")
        );


        Structs.Mesh outputMesh = drawer.drawIsland(mesh);
        new MeshFactory().write(outputMesh, config.export(Configuration.OUTPUT_FILENAME));
    }
}
