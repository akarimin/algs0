package edu.akarimin.week4;

import edu.akarimin.week2.InsertionSort;

import static edu.akarimin.week2.InsertionSort.less;

/**
 * Elementary operations of Priority Queue:
 * unordered array  (insert) -> 1, (delMax) -> N, (max) -> N
 * ordered array                N,             1,          1
 * goal                      logN,          logN,       logN
 */
public class UnorderedMaxPQ<Key extends Comparable<Key>> {

    private final Key[] pq;       // pq[i] = ith element on pq
    private int n;                // number of elements on pq

    @SuppressWarnings("unchecked")
    public UnorderedMaxPQ(int capacity) {
        this.pq = (Key[]) new Comparable[capacity];    // No Generic Array Creation
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void insert(Key x) {
        pq[n++] = x;
    }

    public Key delMax() {
        int max = 0;
        for (int i = 1; i < n; i++)
            if (less(max, i))
                max = i;
        InsertionSort.exchange(pq, max, n - 1);
        return pq[--n];    // Null out entry to prevent loitering
    }
}
