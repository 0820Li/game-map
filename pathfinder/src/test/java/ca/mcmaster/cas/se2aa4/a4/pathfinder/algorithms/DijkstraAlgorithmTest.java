package ca.mcmaster.cas.se2aa4.a4.pathfinder.algorithms;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.GraphADT;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DijkstraAlgorithmTest {
    @Test
    public void shouldFindPath() {
        GraphADT graph = new GraphADT();
        graph.nodes.add(new GraphADT.Node(0));
        graph.nodes.add(new GraphADT.Node(1));
        graph.nodes.add(new GraphADT.Node(2));
        graph.nodes.add(new GraphADT.Node(3));
        graph.nodes.add(new GraphADT.Node(4));
        graph.nodes.add(new GraphADT.Node(5));
        graph.nodes.add(new GraphADT.Node(6));
        graph.nodes.add(new GraphADT.Node(7));
        graph.nodes.add(new GraphADT.Node(8));
        graph.nodes.add(new GraphADT.Node(9));

        GraphADT.Edge edge = new GraphADT.Edge(
                graph.nodes.get(6),
                graph.nodes.get(9),
                4
        );
        graph.edges.add(edge);
        graph.nodes.get(6).edges.add(edge);
        graph.nodes.get(9).edges.add(edge);

        GraphADT.Edge edge0 = new GraphADT.Edge(
                graph.nodes.get(6),
                graph.nodes.get(5),
                3
        );
        graph.edges.add(edge0);
        graph.nodes.get(6).edges.add(edge0);
        graph.nodes.get(5).edges.add(edge0);

        edge = new GraphADT.Edge(
                graph.nodes.get(9),
                graph.nodes.get(5),
                8
        );
        graph.edges.add(edge);
        graph.nodes.get(9).edges.add(edge);
        graph.nodes.get(5).edges.add(edge);

        GraphADT.Edge edge1 = new GraphADT.Edge(
                graph.nodes.get(1),
                graph.nodes.get(5),
                2
        );
        graph.edges.add(edge1);
        graph.nodes.get(1).edges.add(edge1);
        graph.nodes.get(5).edges.add(edge1);

        edge = new GraphADT.Edge(
                graph.nodes.get(8),
                graph.nodes.get(5),
                7
        );
        graph.edges.add(edge);
        graph.nodes.get(8).edges.add(edge);
        graph.nodes.get(5).edges.add(edge);

        GraphADT.Edge edge2 = new GraphADT.Edge(
                graph.nodes.get(1),
                graph.nodes.get(8),
                4
        );
        graph.edges.add(edge2);
        graph.nodes.get(1).edges.add(edge2);
        graph.nodes.get(8).edges.add(edge2);

        edge = new GraphADT.Edge(
                graph.nodes.get(9),
                graph.nodes.get(0),
                11
        );
        graph.edges.add(edge);
        graph.nodes.get(9).edges.add(edge);
        graph.nodes.get(0).edges.add(edge);

        edge = new GraphADT.Edge(
                graph.nodes.get(4),
                graph.nodes.get(0),
                13
        );
        graph.edges.add(edge);
        graph.nodes.get(4).edges.add(edge);
        graph.nodes.get(0).edges.add(edge);

        edge = new GraphADT.Edge(
                graph.nodes.get(0),
                graph.nodes.get(7),
                12
        );
        graph.edges.add(edge);
        graph.nodes.get(0).edges.add(edge);
        graph.nodes.get(7).edges.add(edge);

        edge = new GraphADT.Edge(
                graph.nodes.get(2),
                graph.nodes.get(7),
                7
        );
        graph.edges.add(edge);
        graph.nodes.get(2).edges.add(edge);
        graph.nodes.get(7).edges.add(edge);

        edge = new GraphADT.Edge(
                graph.nodes.get(0),
                graph.nodes.get(3),
                9
        );
        graph.edges.add(edge);
        graph.nodes.get(0).edges.add(edge);
        graph.nodes.get(3).edges.add(edge);

        GraphADT.Edge edge3 = new GraphADT.Edge(
                graph.nodes.get(8),
                graph.nodes.get(3),
                1
        );
        graph.edges.add(edge3);
        graph.nodes.get(8).edges.add(edge3);
        graph.nodes.get(3).edges.add(edge3);

        edge = new GraphADT.Edge(
                graph.nodes.get(2),
                graph.nodes.get(3),
                5
        );
        graph.edges.add(edge);
        graph.nodes.get(2).edges.add(edge);
        graph.nodes.get(3).edges.add(edge);

        PathFindingAlgorithm<GraphADT.Node, GraphADT.Edge> algorithm = new DijkstraAlgorithm<>();
        List<GraphADT.Edge> path = algorithm.findPath(graph, graph.nodes.get(3), graph.nodes.get(6));
        assertEquals(4, path.size());
        assertEquals(edge0, path.get(0));
        assertEquals(edge1, path.get(1));
        assertEquals(edge2, path.get(2));
        assertEquals(edge3, path.get(3));
    }

    @Test
    public void sameDestination() {
        GraphADT graph = new GraphADT();
        graph.nodes.add(new GraphADT.Node(0));
        graph.nodes.add(new GraphADT.Node(1));
        graph.nodes.add(new GraphADT.Node(2));
        graph.nodes.add(new GraphADT.Node(3));
        graph.nodes.add(new GraphADT.Node(4));
        graph.nodes.add(new GraphADT.Node(5));
        graph.nodes.add(new GraphADT.Node(6));
        graph.nodes.add(new GraphADT.Node(7));
        graph.nodes.add(new GraphADT.Node(8));
        graph.nodes.add(new GraphADT.Node(9));

        GraphADT.Edge edge = new GraphADT.Edge(
                graph.nodes.get(6),
                graph.nodes.get(9),
                4
        );
        graph.edges.add(edge);
        graph.nodes.get(6).edges.add(edge);
        graph.nodes.get(9).edges.add(edge);

        GraphADT.Edge edge0 = new GraphADT.Edge(
                graph.nodes.get(6),
                graph.nodes.get(5),
                3
        );
        graph.edges.add(edge0);
        graph.nodes.get(6).edges.add(edge0);
        graph.nodes.get(5).edges.add(edge0);

        edge = new GraphADT.Edge(
                graph.nodes.get(9),
                graph.nodes.get(5),
                8
        );
        graph.edges.add(edge);
        graph.nodes.get(9).edges.add(edge);
        graph.nodes.get(5).edges.add(edge);

        GraphADT.Edge edge1 = new GraphADT.Edge(
                graph.nodes.get(1),
                graph.nodes.get(5),
                2
        );
        graph.edges.add(edge1);
        graph.nodes.get(1).edges.add(edge1);
        graph.nodes.get(5).edges.add(edge1);

        edge = new GraphADT.Edge(
                graph.nodes.get(8),
                graph.nodes.get(5),
                7
        );
        graph.edges.add(edge);
        graph.nodes.get(8).edges.add(edge);
        graph.nodes.get(5).edges.add(edge);

        GraphADT.Edge edge2 = new GraphADT.Edge(
                graph.nodes.get(1),
                graph.nodes.get(8),
                4
        );
        graph.edges.add(edge2);
        graph.nodes.get(1).edges.add(edge2);
        graph.nodes.get(8).edges.add(edge2);

        edge = new GraphADT.Edge(
                graph.nodes.get(9),
                graph.nodes.get(0),
                11
        );
        graph.edges.add(edge);
        graph.nodes.get(9).edges.add(edge);
        graph.nodes.get(0).edges.add(edge);

        edge = new GraphADT.Edge(
                graph.nodes.get(4),
                graph.nodes.get(0),
                13
        );
        graph.edges.add(edge);
        graph.nodes.get(4).edges.add(edge);
        graph.nodes.get(0).edges.add(edge);

        edge = new GraphADT.Edge(
                graph.nodes.get(0),
                graph.nodes.get(7),
                12
        );
        graph.edges.add(edge);
        graph.nodes.get(0).edges.add(edge);
        graph.nodes.get(7).edges.add(edge);

        edge = new GraphADT.Edge(
                graph.nodes.get(2),
                graph.nodes.get(7),
                7
        );
        graph.edges.add(edge);
        graph.nodes.get(2).edges.add(edge);
        graph.nodes.get(7).edges.add(edge);

        edge = new GraphADT.Edge(
                graph.nodes.get(0),
                graph.nodes.get(3),
                9
        );
        graph.edges.add(edge);
        graph.nodes.get(0).edges.add(edge);
        graph.nodes.get(3).edges.add(edge);

        GraphADT.Edge edge3 = new GraphADT.Edge(
                graph.nodes.get(8),
                graph.nodes.get(3),
                1
        );
        graph.edges.add(edge3);
        graph.nodes.get(8).edges.add(edge3);
        graph.nodes.get(3).edges.add(edge3);

        edge = new GraphADT.Edge(
                graph.nodes.get(2),
                graph.nodes.get(3),
                5
        );
        graph.edges.add(edge);
        graph.nodes.get(2).edges.add(edge);
        graph.nodes.get(3).edges.add(edge);

        PathFindingAlgorithm<GraphADT.Node, GraphADT.Edge> algorithm = new DijkstraAlgorithm<>();
        List<GraphADT.Edge> path = algorithm.findPath(graph, graph.nodes.get(3), graph.nodes.get(3));
        assertEquals(0, path.size());
    }

    @Test
    public void noPath() {
        GraphADT graph = new GraphADT();
        graph.nodes.add(new GraphADT.Node(0));
        graph.nodes.add(new GraphADT.Node(1));

        PathFindingAlgorithm<GraphADT.Node, GraphADT.Edge> algorithm = new DijkstraAlgorithm<>();
        List<GraphADT.Edge> path = algorithm.findPath(graph, graph.nodes.get(0), graph.nodes.get(1));
        assertNull(path);
    }
}
