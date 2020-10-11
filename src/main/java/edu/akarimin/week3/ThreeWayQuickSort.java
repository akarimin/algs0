package edu.akarimin.week3;

import edu.akarimin.week2.InsertionSort;

/**
 * To overcome quadratic time in case of duplicate keys.
 */
public class ThreeWayQuickSort {

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        int lt = lo;
        int gt = hi;
        Comparable v = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0)
                InsertionSort.exchange(a, lt++, i++);
            else if (cmp > 0)
                InsertionSort.exchange(a, i, gt--);
            else
                i++;
        }
        ThreeWayQuickSort.sort(a, lo, lt - 1);
        ThreeWayQuickSort.sort(a, gt + 1, hi);
    }
}

