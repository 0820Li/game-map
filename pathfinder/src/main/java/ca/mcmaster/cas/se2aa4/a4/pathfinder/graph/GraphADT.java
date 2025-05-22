package ca.mcmaster.cas.se2aa4.a4.pathfinder.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphADT implements Graph<GraphADT.Node, GraphADT.Edge> {
    @Override
    public Set<Edge> getEdges(Node node) {
        return node.edges;
    }

    @Override
    public Pair<Node> getNodes(Edge edge) {
        return new Pair<>(edge.first, edge.second);
    }

    @Override
    public double getWeight(Edge edge) {
        return edge.weight;
    }

    public static class Node {
        public int id;
        public Set<Edge> edges = new HashSet<>();

        public Node(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Node-" + id;
        }
    }

    public static class Edge {
        Node first;
        Node second;

        double weight;

        public Edge(Node first, Node second, double weight) {
            this.first = first;
            this.second = second;
            this.weight = weight;
        }
    }

    public List<Node> nodes = new ArrayList<>();
    public List<Edge> edges = new ArrayList<>();
}
