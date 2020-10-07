package edu.akarimin.week3;

import edu.princeton.cs.algs4.StdRandom;


/**
 * Given an array of n items, find the kth largest.
 */
public class Selection {

    /**
     * Kind of Quick Sort but repeat in one sub-array, depending on j; finished when j equals k.
     * Average: Linear
     * Worst: Quadratic (Open problem to find a Linear worst-case algorithm)
     */
    private static class QuickSelect {

        public static Comparable select(Comparable[] a, int k) {
            StdRandom.shuffle(a);
            int lo = 0;
            int hi = a.length - 1;
            while (hi > lo) {
                int j = QuickSort.partition(a, lo, hi);
                if (j < k)
                    lo = j + 1;
                else if (j > k)
                    hi = j - 1;
                else
                    return a[k];
            }
            return a[k];
        }

    }
}
