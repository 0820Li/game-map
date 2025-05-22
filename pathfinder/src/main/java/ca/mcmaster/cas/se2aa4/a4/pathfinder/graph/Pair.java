package ca.mcmaster.cas.se2aa4.a4.pathfinder.graph;

public class Pair<A> {
    public final A first;
    public final A second;

    public Pair(A first, A second) {
        this.first = first;
        this.second = second;
    }

    public A getOther(A value) {
        if (value.equals(first)) {
            return second;
        } else if (value.equals(second)) {
            return first;
        } else {
            throw new IllegalArgumentException("value is not part of this pair");
        }
    }
}
