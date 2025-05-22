package ca.mcmaster.cas.se2aa4.a4.pathfinder.algorithms;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;

import java.util.List;

public interface PathFindingAlgorithm<N, E> {
    List<E> findPath(Graph<N, E> graph, N start, N end);
}
