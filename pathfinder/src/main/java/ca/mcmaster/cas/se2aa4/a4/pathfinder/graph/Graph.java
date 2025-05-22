package ca.mcmaster.cas.se2aa4.a4.pathfinder.graph;

import java.util.Set;

public interface Graph<N, E> {
    Set<E> getEdges(N node);

    Pair<N> getNodes(E edge);

    double getWeight(E edge);
}
