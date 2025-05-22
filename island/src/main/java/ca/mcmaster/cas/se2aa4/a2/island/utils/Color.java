package ca.mcmaster.cas.se2aa4.a2.island.utils;

public record Color(int r, int g, int b) {
    @Override
    public String toString() {
        return r + "," + g + "," + b;
    }
}
