package ca.mcmaster.cas.se2aa4.a2.island.utils;

import java.util.Objects;

public record Segment(Coordinate start, Coordinate end) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment segment = (Segment) o;
        return Objects.equals(start, segment.start) && Objects.equals(end, segment.end) ||
                Objects.equals(end, segment.start) && Objects.equals(start, segment.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end) + Objects.hash(end, start);
    }
}
