package edu.akarimin.week3;

import edu.princeton.cs.algs4.Insertion;

import static edu.akarimin.week2.InsertionSort.isSorted;
import static edu.akarimin.week2.InsertionSort.less;

/**
 * Used for Objects in Java.
 * Divide & Conquer.
 * Good algorithms are better than supercomputers.
 * C(N) and A(N) = NLgN ~ 1/2 NLnN (2-based) => Linearithmic
 * Number of Compares and Accesses
 * Tip: MergeSort has too much overhead for tiny sub-arrays.
 * Cutoff to insertion sort for 7 items.
 */
public class MergeSort {

    private static final int CUTOFF = 7;

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);         // precondition: a[lo..mid]    sorted
        assert isSorted(a, mid + 1, hi);  // precondition: a[mid+1..hi]  sorted

        for (int k = lo; k <= hi; k++)       // copy to aux
            aux[k] = a[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {     // merge
            if (i > mid)                     // if i exhausted (first half) we increment j
                a[k] = aux[j++];
            else if (j > hi)                 // if j exhausted (first half) we increment i
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
        assert isSorted(a, lo, hi);          // post-condition: a[lo..hi]  sorted
    }

    // Not Improved
    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    // Improved (25%) to save space
    public static void sortCutOff(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo + CUTOFF - 1) {
            Insertion.sort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        // Check if already sorted.
        if (less(a[mid + 1], a[mid]))
            return;
        merge(a, aux, lo, mid, hi);
    }

    // Expert: Time could be improved by switching the role of input and auxiliary in each recursive calls.


    public static void sort(Comparable[] a) {
        sort(a, new Comparable[a.length], 0, a.length - 1);
    }

}
