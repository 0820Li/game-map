package ca.mcmaster.cas.se2aa4.a4.pathfinder.algorithms;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DijkstraAlgorithm<N, E> implements PathFindingAlgorithm<N, E> {
    private N findNodeOfLowestCost(HashMap<N, Double> cost, List<N> queue) {
        double lowestCost = Double.MAX_VALUE;
        N lowestCostNode = null;
        for (N node : queue) {
            if (cost.getOrDefault(node, Double.MAX_VALUE) < lowestCost) {
                lowestCost = cost.get(node);
                lowestCostNode = node;
            }
        }
        return lowestCostNode;
    }

    @Override
    public List<E> findPath(Graph<N, E> graph, N start, N end) {
        HashMap<N, N> path = new HashMap<>();
        HashMap<N, Double> cost = new HashMap<>();

        path.put(start, start);
        cost.put(start, 0.0);

        List<N> queue = new ArrayList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            N m = findNodeOfLowestCost(cost, queue);
            queue.remove(m);
            for (E edge : graph.getEdges(m)) {
                Pair<N> nodes = graph.getNodes(edge);
                N n = nodes.getOther(m);
                if (cost.getOrDefault(m, Double.MAX_VALUE) + graph.getWeight(edge) < cost.getOrDefault(n, Double.MAX_VALUE)) {
                    path.put(n, m);
                    cost.put(n, cost.get(m) + graph.getWeight(edge));
                    queue.add(n);
                }
            }
        }

        List<E> pathEdges = new ArrayList<>();
        N current = end;
        while (!current.equals(start)) {
            N previous = path.get(current);
            if (previous == null) return null;
            for (E edge : graph.getEdges(previous)) {
                Pair<N> nodes = graph.getNodes(edge);
                if (nodes.getOther(previous).equals(current)) {
                    pathEdges.add(edge);
                    break;
                }
            }
            current = previous;
        }
        return pathEdges;
    }
}
