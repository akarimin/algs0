package edu.akarimin.week1;

/**
 * Lazy approach - Faster Union, Still not fast enough
 */
public class QuickUnion {

    private final int[] id;

    public QuickUnion(int n) {                 // N array accesses
        id = new int[n];
        for (int i = 0; i < n; i++)
            id[i] = i;
    }

    private int root(int i) {                    // Depth of i array accesses
        while (i != id[i])
            i = id[i];
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

}
