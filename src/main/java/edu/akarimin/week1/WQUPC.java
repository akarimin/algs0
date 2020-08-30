package edu.akarimin.week1;

/**
 * Improvement-2: Path Compression Approach - Halve the path, connect each node to its grandparent
 * Ideal: Weighted Quick Union With Path Compression (WQUPC) -> N + M * (Lg*) N
 * Lg* -> {
 * Practically Linear
 *     1 ->        0
 *     2 ->        1
 *     4 ->        2
 *     16 ->       3
 *     65536 ->    4
 *     2^ 65536 -> 5
 * }
 */
public class WQUPC {

    private final int[] id;

    public WQUPC(int n) {                 // N array accesses
        id = new int[n];
        for (int i = 0; i < n; i++)
            id[i] = i;
    }

    private int root(int i) {                    // Depth of i array accesses
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    private boolean connected(int p, int q) {    // Depth of p and q array accesses (N worst case)
        return root(p) == root(q);
    }

    private void union(int p, int q) {           // Depth of p and q array accesses (N worst case)
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }

    private int find(int i) {
        int[] highests = new int[id.length];
        int highest = 0;
        for(int j : id) {
            if (connected(i, j) && j > highest)
                highest = j;
        }
        return highest;
    }
}
