package edu.akarimin.week1;

/**
 * Eager Approach - Faster find, not fast enough
 */
public class QuickFind {

    private final int[] id;

    public QuickFind(int n) {                 // N array accesses
        id = new int[n];
        for (int i = 0; i < n; i++)
            id[i] = i;
    }

    private boolean connected(int p, int q) {   // 2 array accesses
        return id[p] == id[q];
    }

    private void union(int p, int q) {          // 2N + 2 array accesses
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid)
                id[i] = qid;
        }
    }
}
