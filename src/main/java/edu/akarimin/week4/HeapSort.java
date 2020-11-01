package edu.akarimin.week4;

/**
 * In-place sort. first we max-heap with all n keys, then repeatedly remove the maximum key.
 * First we make sure items are heap-ordered.
 * Construction takes 2N compares and exchanges.
 * In-place sorting alg with NLogN worst-case.
 * Mergesort: no, linear extra space.
 * Quicksort: no, quadratic time in worst-case.
 * Heapsort: Yes ! while optimal for both time and space, its inner loop takes longer than Quicksort's,
 * and makes poor use of cache memory, and it is not stable.
 */
public class HeapSort<Key extends Comparable<Key>> {

    private Key[] pq;
    private int n;

    public void sort(Key[] pq) {
        for (int k = n/2; k >= 1; k--)
            sink(pq, k, n);
        while (n > 1) {
            exchange(pq, 1, n);
            sink(pq, 1, --n);
        }
    }

    private static void sink(Comparable[] pq, int k, int n) {
        /* as before but zero-based */
    }

    private static void exchange(Comparable[] pq, int k, int n) {
        /* as before but zero-based */
    }
}
