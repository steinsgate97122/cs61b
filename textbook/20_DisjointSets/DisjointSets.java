/**
 * Goal: Design an efficient DisjointSets implementation.
 * Number of elements N can be huge.
 * Number of method calls M can be huge.
 * Calls to methods may be interspersed (e.g. can’t assume that we stop getting connect calls after some point).
 */
public interface DisjointSets {
    /** Connects two items P and Q. */
    void connect(int p, int q);

    /** Checks to see if two items are connected. */
    boolean isConnected(int p, int q);
}
