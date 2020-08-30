package edu.akarimin.week1;

/**
 * Improvement-1: Weighted Approach - Less tall trees
 */
public class WightedUnionFind {

    private final int[] id;
    private final int[] sz;

    public WightedUnionFind(int n) {                 // N array accesses
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i) {                    // Depth of i array accesses
        while (i != id[i])
            i = id[i];
        return i;
    }

    private boolean connected(int p, int q) {    // Depth of p and q array accesses (Log N (base-2) at worst case)
        int i = root(p);                         // i.e. 1000 -> 10, 1E6 -> 20, 1E9 -> 30
        return root(p) == root(q);
    }

    private void union(int p, int q) {           // Depth of p and q array accesses (Log N (base-2) at worst case)
        int i = root(p);                         // i.e. 1000 -> 10, 1E6 -> 20, 1E9 -> 30
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }

}
