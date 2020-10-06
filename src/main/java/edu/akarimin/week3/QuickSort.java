package edu.akarimin.week3;


import edu.akarimin.week2.InsertionSort;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

import static java.util.Collections.swap;

/**
 * Recursive, invented in 1961 by Hoare
 * Used in many languages such as Java, C++, Python.
 * Java used for primitives.
 * 1. Shuffle the array
 * 2. Partition the array in a way that for j, there no larger entry to the left and no smaller entry to the right of j
 * 3. Sort each piece recursively
 * Worst-case: ~1/2 N^2
 * Best-case: ~2NLgN
 * Average number of comparisons: ~ 1.39NLgN
 * 39% more compares than merge-sort but faster bc less data movement
 */
public class QuickSort {

    public static void sort(Comparable[] a) {   // Shuffle needed for performance guarantee
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        int j = partition(a, lo, hi);
        sort(a, lo, j);
        sort(a, j + 1, hi);
    }

    /**
     * @return index of item now known to be in place
     */
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (InsertionSort.less(a[++i], a[lo]))
                if (i == hi)
                    break;
            while (InsertionSort.less(a[lo], a[--j]))
                if (j == lo)
                    break;
            if (i >= j)                         // Check if pointers cross
                break;
            InsertionSort.exchange(a, i, j);
        }

        InsertionSort.exchange(a, lo, j);       // swap with partitioning item
        return j;
    }

    private static final int CUTOFF = 10;

    /**
     * improvement: even Quick-Sort has over-head for small array
     */
    private static void cutoffSort(Comparable[] a, int lo, int hi) {
        if (hi <= lo + CUTOFF - 1) {
            Insertion.sort(a, lo, hi);
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j);
        sort(a, j + 1, hi);
    }

    /**
     * improvement: Take the best choice of pivot item = median
     */
    private static void medianSort(Comparable[] a, int lo, int hi) {
        if (hi <= lo + CUTOFF - 1)
            return;
        int m = medianOf3(a, lo, lo + (hi - lo) / 2, hi);
        swap(Arrays.asList(a), lo, m); // not optimized - implement it
        int j = partition(a, lo, hi);
        sort(a, lo, j);
        sort(a, j + 1, hi);
    }

    private static int medianOf3(Comparable[] a, int lo, int mid, int hi) {
        return (lo + mid + hi) / 3;
    }
}
